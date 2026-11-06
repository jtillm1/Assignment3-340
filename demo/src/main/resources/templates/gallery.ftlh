<#import "layout.ftlh" as l>
<@l.page title="Home">
  <h1>Character Gallery</h1>

  <form method="get" action="/" class="search">
    <label>Name
      <input type="text" name="name" value="${name!''}" placeholder="Search by name">
    </label>
    <label>Universe
      <select name="universe">
        <option value="">All universes</option>
        <#list universes as u>
          <option value="${u}"<#if (universe!"") == u> selected</#if>>${u}</option>
        </#list>
      </select>
    </label>
    <button type="submit" class="btn">Search</button>
    <#if (name!"")?has_content || (universe!"")?has_content>
      <a class="btn btn-light" href="/">Clear</a>
    </#if>
  </form>

  <#if characters?has_content>
    <div class="grid">
      <#list characters as c>
        <a class="card" href="/characters/${c.characterId?c}">
          <img src="<#if c.imageUrl?has_content>${c.imageUrl}<#else>/img/placeholder.svg</#if>"
               alt="${c.name}" loading="lazy"
               onerror="this.onerror=null;this.src='/img/placeholder.svg'">
          <div class="card-body">
            <h2>${c.name}</h2>
            <p class="meta">${c.universe!"Unknown universe"} &middot; ${c.species!"Unknown species"}</p>
            <#if c.role?has_content><p class="meta">Role: ${c.role}</p></#if>
          </div>
        </a>
      </#list>
    </div>
  <#else>
    <div class="empty">
      <h2>No characters found</h2>
      <p>Try a different name or universe, or <a href="/characters/new">add a character</a>.</p>
    </div>
  </#if>
</@l.page>
