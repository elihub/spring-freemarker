<#import "/spring.ftl" as spring />
<#include "macros/categoryFormInput.ftl"/>
<#include "macros/changeCategoryModal.ftl"/>
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
    <h2>Category Admin</h2>
    <form id="mainForm" class=".form-inline" action="saveCategory" method="POST">
        <label>Please add a Category</label>
        <@categoryFormInput bean="category" withSubmit=true/>
    </form>
    <h3>Category history</h3>
    <ul class="unstyled prettyprint">
        <#if (categories?? && categories?size != 0)>
            <#list categories as category>
                <li class="category-row">
                    <label class="force-middle">${category.name} / ${category.description} </label>
                    <div class="force-middle category-data">
                        <input type="hidden" class="categoryId" value="${category.id}">
                        <input type="hidden" class="categoryDesc" value="${category.description}">
                        <input type="hidden" class="categoryName" value="${category.name}">                    
                        <i class="icon-edit" title="Edit Category"></i>
                        <i class="icon-remove" title="Remove Category"></i>
                    </div>
                </li>
            </#list>
        <#else>
            No product around.
        </#if>
    </ul>
    <h3><a href="<@spring.url '/'/>">Index</a></h3>
<@changeCategoryModal/>
</div>
<script src="resources/js/jquery-2.0.2.min.js"></script>
<script src="resources/js/bootstrap.min.js"></script>
<script src="resources/js/codeCategory.js"></script>
</body>
</html>