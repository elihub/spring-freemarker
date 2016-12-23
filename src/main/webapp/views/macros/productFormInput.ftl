<#import "/spring.ftl" as spring />
<#macro productFormInput bean withSubmit=false>
    <#assign hash>${bean}.hash</#assign>
    <@spring.formHiddenInput path=hash/>
<ul>
    <li>
        <div class="row-fluid">
            <div>
                <div class="input-prepend">
                    <span class="add-on">Description</span>
                        <#assign description>${bean}.description</#assign>
                        <@spring.formInput path=description attributes="class='input-large' placeholder='Description'"/>
                    </div>
                </div>
            <div>
                    <@spring.showErrors separator="<br>" classOrStyle="error"/>
                </div>
            </div>
        </li>
    <li>
        <div class="row-fluid">
            <div>
                <div class="input-prepend">
                    <span class="add-on">Code</span>
                        <#assign code>${bean}.code</#assign>
                        <@spring.formInput path=code attributes="class='input-large' placeholder='Code'"/>
                    </div>
                </div>
            <div>
                    <@spring.showErrors separator="<br>" classOrStyle="error"/>
                </div>
            </div>
        </li>
    <li>
        <div class="row-fluid">
            <div>
                <div class="input-prepend">
                    <span class="add-on">Price</span>
                        <#assign price>${bean}.price</#assign>
                        <@spring.formInput path=price attributes="class='input-large' placeholder='Price'"/>
                    </div>
                </div>
            <div>   
                    <@spring.showErrors separator="<br>" classOrStyle="error"/>
                </div>
            </div>
        </li>
    <li>
        <div class="row-fluid">
            <div>
                <div class="input-prepend">
                    <span class="add-on">Amount</span>
                        <#assign amount>${bean}.amount</#assign>
                        <@spring.formInput path=amount attributes="class='input-large' placeholder='Amount'"/>
                    </div>
                </div>
            <div>   
                    <@spring.showErrors separator="<br>" classOrStyle="error"/>
                </div>
            </div>
        </li>
    <li>
        <div class="row-fluid">
            <div>
                <div class="input-prepend">
                    <span class="add-on">Category</span>
                    <select name="category.id">
					<#list categories as one>
                        <option value="${one.getId()}">
							${one.getName()}
                            </option>
					</#list>
                        </select>
                    </div>
                </div>
            <div>   
			<@spring.showErrors separator="<br>" classOrStyle="error"/>
                </div>
            </div>
        </li>

    </ul>
    <#if withSubmit>
<input type="submit" value="Submit" class="btn submitRight" />
    </#if>
</#macro>