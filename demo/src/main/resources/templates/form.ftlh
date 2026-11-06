<#import "layout.ftlh" as l>
<#macro err field>
  <#if errors[field]??><span class="error">${errors[field]}</span></#if>
</#macro>
<@l.page title=formTitle>
  <h1>${formTitle}</h1>

  <form method="post" action="${formAction}" class="form" novalidate>
    <label>Name *
      <input type="text" name="name" value="${character.name!''}">
      <@err "name"/>
    </label>

    <label>Description *
      <textarea name="description" rows="5">${character.description!''}</textarea>
      <@err "description"/>
    </label>

    <label>Universe
      <input type="text" name="universe" value="${character.universe!''}">
      <@err "universe"/>
    </label>

    <label>Species
      <input type="text" name="species" value="${character.species!''}">
      <@err "species"/>
    </label>

    <label>Role
      <input type="text" name="role" value="${character.role!''}">
      <@err "role"/>
    </label>

    <label>First appearance
      <input type="date" name="firstAppearance" value="${character.firstAppearance!''}">
      <@err "firstAppearance"/>
    </label>

    <label>Image URL
      <input type="text" name="imageUrl" value="${character.imageUrl!''}" placeholder="https://... or /img/name.jpg">
      <@err "imageUrl"/>
    </label>

    <div class="actions">
      <button type="submit" class="btn">Save</button>
      <a class="btn btn-light"
         href="<#if character.characterId??>/characters/${character.characterId?c}<#else>/</#if>">Cancel</a>
    </div>
  </form>
</@l.page>
