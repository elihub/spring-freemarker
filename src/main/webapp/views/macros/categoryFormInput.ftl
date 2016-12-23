<#import "/spring.ftl" as spring />
<#macro categoryFormInput bean withSubmit=false>
    <#assign id>${bean}.id</#assign>
    <@spring.formHiddenInput path=id/>
    <ul>       
        <li>
            <div class="row-fluid">
                <div>
                    <div class="input-prepend">
                        <span class="add-on">Name</span>
                        <#assign name>${bean}.name</#assign>
                        <@spring.formInput path=name attributes="class='input-large' placeholder='Name'"/>
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
    </ul>
    <#if withSubmit>
        <input type="submit" value="Submit" class="btn submitRight" />
    </#if>
</#macro>