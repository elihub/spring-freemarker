var crud = new (function() {
    //assign _root and config private variables
    var _root = this;
    var _cfg = {}

    /*
     INITIALIZE
     */
    this.init = function(opts) {
        _cfg = $.extend(_cfg, opts);
        //bind events on init
        $(document).ready(function() {
            //bind events
            _bindEvents();
        });
    }
    /*
     Bind Events
     */
    var _bindEvents = function() {
        var $body = $('body');
        var $modal = $('.modal');
        $(".icon-edit").click(function() {
            copyValuesForChange(this);
            $modal.modal('show');
        });
        $('.icon-remove').click(function() {
            _removePerson(_getProductDataBy(this, '.categoryId'))
        });
        $body.on('click', 'input', function(e){
            if (e.keyCode == 13) {
                $(e.target).parent().submit();
            }
        });

        $modal.on('hidden', function() {
            $('#editWithErrors').val("false")
            _setValuesOnChangeForm("", "", "");
        });
        $body.on('click', '.modal .modal-footer .btn-primary', function() {
            var form = document.forms["editCategoryForm"];
            form.submit();
        });
        if ($('#editWithErrors').val() == "true") {
            $modal.modal('show');
        }
    }

    var copyValuesForChange = function(source) {
        var hash = _getProductDataBy(source, '.categoryId');
        var desc = _getProductDataBy(source, '.categoryDesc');
        var name = _getProductDataBy(source, '.categoryName');
        
        _setValuesOnChangeForm(hash, desc, name);
    }

    var _setValuesOnChangeForm = function(id, desc, name) {
        var form = $('#editCategoryForm');
        if ($('#editWithErrors').val() != "true") {
            form.find('.error:first').parent().remove();
        }
        form.find('input[name="id"]').val(id);
        form.find('input[name="description"]').val(desc);
        form.find('input[name="name"]').val(name);
    }

    var _removePerson = function(selectedId) {
        var action="removeCategory/" + selectedId;
        $('<form action="'+ action +'" method="POST"></form>').appendTo('body').submit();
    }
    /*
    var _getChosenPersonHash = function(elem) {
        return _getPersonDataBy(elem, '.personHash');
    }

    var _getChosenPersonName = function(elem) {
        return _getPersonDataBy(elem, '.personName');
    }

    var _getChosenPersonBirthDate = function(elem) {
        return _getPersonDataBy(elem, '.personBirthDate');
    }

    var _getChosenPersonEmail = function(elem) {
        return _getPersonDataBy(elem, '.personEmail');
    }
    */
    var _getProductDataBy = function(elem, selector) {
        return $(elem).closest('.category-data').find(selector).val();
    }
})();

crud.init();