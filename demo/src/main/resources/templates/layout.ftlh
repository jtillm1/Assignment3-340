<#-- Shared layout: every page wraps itself in <@l.page title="..."> -->
<#macro page title>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>${title} | Character Gallery</title>
  <link rel="stylesheet" href="/css/style.css">
</head>
<body>
  <header class="site-header">
    <div class="container nav-wrap">
      <a class="brand" href="/">Character Gallery</a>
      <nav>
        <a href="/">Home</a>
        <a href="/about">About</a>
        <a href="/characters/new">Add a Character</a>
      </nav>
    </div>
  </header>
  <main class="container">
    <#if message??>
      <div class="flash" role="status">${message}</div>
    </#if>
    <#nested>
  </main>
  <footer class="site-footer">
    <div class="container">Character Gallery &middot; CSC340</div>
  </footer>
</body>
</html>
</#macro>
