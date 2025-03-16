<template>
  <header id="header">
    <h1>Airport CGI</h1>
  </header>
  <router-view/>
</template>

<script>

export default {
  name: 'App',
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
#header {
  background-color: #42b983;
  color: white;
  padding: 20px;
  margin: -10px;
}

</style>
