

(function($){
    $.shmslidepanel = function(el, panelselector, direction, options){
        // To avoid scope issues, use 'base' instead of 'this'
        // to reference this class from internal events and functions.
        var base = this;
        
        // Access to jQuery and DOM versions of element
        base.$el = $(el);
        base.el = el;
        
        // Add a reverse reference to the DOM object
        base.$el.data("shmslidepanel", base);
        
        base.init = function(){
            base.panelselector = panelselector;
            base.$panel = $(base.panelselector);

            base.direction = direction;
            
            base.options = $.extend({},$.shmslidepanel.defaultOptions, options);
            
            // Initialization code
            base.$el.click(togglePannel);
        };
        
        // Sample Function, Uncomment to use
        // base.functionName = function(paramaters){
        // 
        // };
        var togglePannel = function() {
        	base.$panel.toggleClass('active');
        };

        // Run initializer
        base.init();
    };
    
    $.shmslidepanel.defaultOptions = {
    	width: 550
    };
    
    $.fn.shmslidepanel = function(panelselector, direction, options){
        return this.each(function(){
            (new $.shmslidepanel(this, panelselector, direction, options));
        });
    };
    
})(jQuery);

$('.shm-post').shmslidepanel('.shm-slide-panel', 'right');