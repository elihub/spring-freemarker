var crud = new (function () {
    //assign _root and config private variables
    var _root = this;
    var _cfg = {}

    /*
     INITIALIZE
     */
    this.init = function (opts) {
        _cfg = $.extend(_cfg, opts);
        //bind events on init
        $(document).ready(function () {
            //bind events
            _bindEvents();
        });
    }
    /*
     Bind Events
     */
    var _bindEvents = function () {
        var $body = $('body');
        var $modal = $('.modal');
        var rowSelect;
        $(".icon-edit").click(function () {
            copyValuesForChange(this);
            rowSelect = this;
            $modal.modal('show');
        });
        $('.icon-remove').click(function () {
            _removePerson(_getProductDataBy(this, '.prodHash'))
        });
        $body.on('click', '.modal .modal-footer .btn-primary', function () {
            //var form = document.forms["editProductForm"];
            var url1 = contextPath + "product";
            var product = $("#editProductForm").serializeObject();
            var categoryName = $("#editProductForm").find("select[name='category.id'] option:selected").html();
            $.ajax({
                type: "PUT",
                url: url1,
                headers: {
                    "Content-Type": "application/json"
                },
                data: JSON.stringify(product),
                success: function (response) {
                    var objResponse = JSON.parse(response);
                    if (objResponse.status === "success") {
                        //alert("IF");
                        $modal.modal('hide');
                        var resumen = product.description + ' / '
                                + product.code + ' / '
                                + product.price + ' / '
                                + product.amount + ' / '
                                + categoryName.trim();
                        _setProductHtmlBy(rowSelect, '.resumen', resumen);
                        _setProductDataBy(rowSelect, '.prodHash', product.hash);
                        _setProductDataBy(rowSelect, '.prodDesc', product.description);
                        _setProductDataBy(rowSelect, '.prodCode', product.code);
                        _setProductDataBy(rowSelect, '.prodPrice', product.price);
                        _setProductDataBy(rowSelect, '.prodAmount', product.amount);
                        _setProductDataBy(rowSelect, '.prodCatId', product.category.id);
                    } else {
                        alert("Occurs an error");
                    }
                },
                error: function (e) {
                    //$('#origen').val("");
                    //$("#status").val(0);
                    //$("#aerolinea option[value=-]").attr("selected",true);
                    alert('Error: ' + e);
                }
            });
        });

        $modal.on('hidden', function () {
            $('#editWithErrors').val("false")
            _setValuesOnChangeForm("", "", "", "");
        });
        if ($('#editWithErrors').val() == "true") {
            $modal.modal('show');
        }
    }

    var copyValuesForChange = function (source) {
        var hash = _getProductDataBy(source, '.prodHash');
        var desc = _getProductDataBy(source, '.prodDesc');
        var code = _getProductDataBy(source, '.prodCode');
        var price = _getProductDataBy(source, '.prodPrice');
        var amount = _getProductDataBy(source, '.prodAmount');
        var catId = _getProductDataBy(source, '.prodCatId');

        _setValuesOnChangeForm(hash, desc, code, price, amount, catId);
    }

    var _setValuesOnChangeForm = function (hash, desc, code, price, amount, catId) {
        var form = $('#editProductForm');
        if ($('#editWithErrors').val() != "true") {
            form.find('.error:first').parent().remove();
        }
        form.find('input[name="hash"]').val(hash);
        form.find('input[name="description"]').val(desc);
        form.find('input[name="code"]').val(code);
        form.find('input[name="price"]').val(price);
        form.find('input[name="amount"]').val(amount);
        form.find('select[name="category.id"]').val(catId);
    }

    var _removePerson = function (selectedId) {
        var action = "removeProduct/" + selectedId;
        $('<form action="' + action + '" method="POST"></form>').appendTo('body').submit();
    }
    var _getProductDataBy = function (elem, selector) {
        return $(elem).closest('.product-data').find(selector).val();
    }
    var _setProductDataBy = function (elem, selector, value) {
        $(elem).closest('.product-data').find(selector).val(value);
    };
    var _setProductHtmlBy = function (elem, selector, value) {
        $(elem).closest('.product-data').find(selector).html(value);
    };
})();

crud.init();
