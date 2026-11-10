<#import "layout.ftlh" as l>
<@l.page title="About">
  <h1>About This Gallery</h1>

  <div class="about">
    <img src="/img/about.svg" alt="Illustration of framed character portraits on a wall">
    <div>
      <p>
        Character Gallery is a place to browse, search, and curate a collection of characters
        from your favorite shows and movies. Each character has a portrait, a short summary,
        and a full profile with its universe, species, role, and first appearance.
      </p>
      <p>
        Use the search bar on the home page to find a character by name, narrow the list to a
        single universe, or combine both. Anyone can add a new character, edit an existing
        profile, or remove one that no longer belongs.
      </p>
      <p>
        This collection started as a static website for a class project and grew into a
        full web application backed by a database.
      </p>
    </div>
  </div>

  <section class="how">
    <h2>How this app is built</h2>
    <p>The app is a single Spring Boot application organized in layers:</p>
    <ul>
      <li><strong>Entity</strong> (<code>TvCharacter</code>): describes a character and is stored in the <code>characters</code> table of a PostgreSQL database on Neon.</li>
      <li><strong>Repository</strong>: a Spring Data JPA interface that reads and writes the database.</li>
      <li><strong>Service</strong>: holds the app's logic (searching, creating, updating, deleting) and is the only layer that talks to the repository.</li>
      <li><strong>Controllers</strong>: a REST controller serves JSON at <code>/api/characters</code>, and a page controller builds the HTML pages you see here.</li>
      <li><strong>Templates</strong>: FreeMarker templates share one layout (the navigation and page frame) and render each page on the server.</li>
    </ul>
    <p>
      The front end and back end live in the same app. When you open a page, the page controller
      asks the service for data, puts it in a model, and FreeMarker turns it into HTML. Forms send
      data back to the controller, which validates it, saves it through the service, and redirects
      to a page with a confirmation message. The REST API uses the same service, so both share one set of rules.
    </p>
  </section>
</@l.page>
