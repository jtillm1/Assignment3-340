<#import "layout.ftlh" as l>
<@l.page title=character.name>
  <p><a href="/">&larr; Back to gallery</a></p>

  <article class="details">
    <img class="details-img"
         src="<#if character.imageUrl?has_content>${character.imageUrl}<#else>/img/placeholder.svg</#if>"
         alt="${character.name}"
         onerror="this.onerror=null;this.src='/img/placeholder.svg'">
    <div>
      <h1>${character.name}</h1>
      <p class="description">${character.description}</p>
      <dl class="attrs">
        <dt>Universe</dt><dd>${character.universe!"Unknown"}</dd>
        <dt>Species</dt><dd>${character.species!"Unknown"}</dd>
        <dt>Role</dt><dd>${character.role!"Unknown"}</dd>
        <dt>First appearance</dt><dd>${character.firstAppearance!"Unknown"}</dd>
      </dl>

      <div class="actions">
        <a class="btn" href="/characters/${character.characterId?c}/edit">Edit</a>
        <form method="post" action="/characters/${character.characterId?c}/delete"
              onsubmit="return confirm('Delete ${character.name?js_string}? This cannot be undone.');">
          <button type="submit" class="btn btn-danger">Delete</button>
        </form>
      </div>
    </div>
  </article>
</@l.page>
