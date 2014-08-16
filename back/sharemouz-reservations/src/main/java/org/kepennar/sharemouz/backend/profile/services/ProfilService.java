package org.kepennar.sharemouz.backend.profile.services;

import org.kepennar.sharemouz.backend.model.User;
import org.kepennar.sharemouz.backend.offer.events.OfferEvent;
import org.kepennar.sharemouz.backend.security.AuthenticatedUserProvider;
import org.kepennar.sharemouz.backend.security.repository.UserRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Optional;

import static org.springframework.data.mongodb.core.query.Criteria.where;

/**
 * Created by kepennar on 14/08/14.
 */
@Service
public class ProfilService {
    private final UserRepository repo;
    private final MongoTemplate template;

    private final AuthenticatedUserProvider authenticatedUserProvider;

    @Inject
    public ProfilService(UserRepository userRepository, MongoTemplate mongoTemplate, AuthenticatedUserProvider authenticatedUserProvider) {
        this.repo = userRepository;
        this.template = mongoTemplate;
        this.authenticatedUserProvider= authenticatedUserProvider;
    }

    public User getCurrent() {
        return this.authenticatedUserProvider.getCurrent();
    }

    public Optional<User> find(String userId) {
        return Optional.ofNullable(repo.findOne(userId));
    }
    public void updateOffersCounter(User user, OfferEvent.Action action) {

        String userId = user.getId();
        Integer amount;
        if (OfferEvent.Action.CREATE.equals(action)) {
            amount= 1;
        } else if(OfferEvent.Action.CANCEL.equals(action)) {
            amount= -1;
        } else {
            throw new UnsupportedOperationException("The '" + action + "' action is not implemented");
        }
        this.template.updateFirst(new Query(
                        where("_id").is(userId)),
                        new Update().inc("nbOffers", amount),
                User.class);
    }
}
