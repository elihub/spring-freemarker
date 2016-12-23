<#import "/spring.ftl" as spring />
<#include "macros/productFormInput.ftl"/>
<#include "macros/changeProductModal.ftl"/>
<!DOCTYPE HTML>
<html>
    <head>
        <title>Test</title>
        <link href="resources/css/bootstrap.min.css" rel="stylesheet">
        <link href="resources/css/bootstrap-responsive.min.css" rel="stylesheet">
        <link href="resources/css/style.css" rel="stylesheet">
        </head>
    <body>
        <div class="container">
            <h2>Product Admin</h2>
            <form id="mainForm" class=".form-inline">
                <label>Please add a Product</label>  
                <@productFormInput bean="product" withSubmit=true/>          

                </form>
            <ul id="detailsProducts">
              <#if (products?? && products?size != 0)>
            <#list products as product>
                <li class="product-row">
                    <div class="product-data">
                        <label class="force-middle resumen">${product.description} / ${product.code} / ${product.price} / ${product.amount} / ${product.category.name}</label>
                        <div class="force-middle">
                            <input type="hidden" class="prodHash" value="${product.hash}">
                            <input type="hidden" class="prodDesc" value="${product.description}">
                            <input type="hidden" class="prodCode" value="${product.code}">
                            <input type="hidden" class="prodPrice" value="${product.price}">
                            <input type="hidden" class="prodAmount" value="${product.amount}">
                            <input type="hidden" class="prodCatId" value="${product.category.id}">
                            <i class="icon-edit" title="Edit product"></i>
                            <i class="icon-remove" title="Remove product"></i>
                            </div>
                        </div>
                    </li>
            </#list>
        <#else>
                No product around.
        </#if>
                </ul>
            <@changeProductModal/>
            <h3><a href="<@spring.url '/'/>">Index</a></h3>           
            </div>
        <script>
            var contextPath = '<@spring.url '/'/>'; 
            </script>
        <script src="resources/js/jquery-2.0.2.min.js"></script>
        <script src="resources/js/bootstrap.min.js"></script>
        <script src="resources/js/codeProd.js"></script>
        <script>
               $(document).ready(function(){
                  $("#mainForm").submit(function(e) {
                         e.preventDefault();                           
                         doAjaxPost();                              
                   });                        
               });
                     
                        function doAjaxPost() {                                               
                            var url1=contextPath + 'product';
                            var product = $("#mainForm").serializeObject(); 
                            var categoryName = $("#mainForm").find("select[name='category.id'] option:selected").html();
                            //if(vuelo!="" && fecha!=""){
                                    $.ajax({  
                                            type: "POST",  
                                            url: url1,  
                                            data: JSON.stringify(product),
                                            headers: {
                                                "Content-Type":"application/json"
                                            },          
                                            success: function(response){
                                            var objResponse=JSON.parse(response);
                                            if(objResponse.status === "success"){                                               
                                                var resumen = product.description + ' / '
                                                    + product.code + ' / '
                                                    + product.price + ' / '
                                                    + product.amount + ' / '
                                                    + categoryName.trim();
                                                    $("#detailsProducts").append('<li class="product-row">'
                                                            +'<div class="product-data">'
                                                                +'<label class="force-middle resumen">'+resumen+'</label>'
                                                                    +'<div class="force-middle">'
                                                                        +'<input type="hidden" class="prodHash" value="' + objResponse.hash + '">'
                                                                        +'<input type="hidden" class="prodDesc" value="' + product.description + '">'
                                                                        +'<input type="hidden" class="prodCode" value="' + product.code + '">'
                                                                        +'<input type="hidden" class="prodPrice" value="' + product.price + '">'
                                                                        +'<input type="hidden" class="prodAmount" value="' + product.amount + '">'
                                                                        +'<input type="hidden" class="prodCatId" value="' + product.category.id + '">'
                                                                        +'<i class="icon-edit" title="Edit product"></i>'
                                                                        +'<i class="icon-remove" title="Remove product"></i>'                                                                   
                                                                    +'</div>'
                                                            +'</div>'
                                                        +'</li>');  
                                                        crud.init();
                                            }else{
                                                     alert('FAIL');
                                            }         
                                    },  
                                    error: function(e){  
                                            
                                    }  
                                    });                                    
                            
                        }
                        
                            $.fn.serializeObject = function ()
    {
        var product = {};
        var category = {};
        var a = this.serializeArray();
        product.description = a[1].value;
        product.code = a[2].value;
        product.hash = a[0].value;
        product.price = parseFloat(a[3].value);
        product.amount = parseFloat(a[4].value);
        category.id = a[5].value;
        product.category = category;
        console.log(JSON.stringify(product));
        return product;
    };
                        
            </script>
        </body>
    </html>