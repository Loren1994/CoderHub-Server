<template lang="html">
  <div class="container">
    <!-- <home-header></home-header> -->
    <div id="main">
      <div id="sidebar">
        <home-personal></home-personal>
      </div>
      <div id="content">
        <home-content></home-content>
      </div>
    </div>

<div style="display:flex;direction-flex:row">
     <vue-markdown style="width:50%">
``` kotlin
fun test(){
  list.forEach{ it -> log()}
}
```
    </vue-markdown>

    <vue-markdown style="width:50%">
``` java
private void test(){
  Log.d("","");
  Log.d("","");
}
```
    </vue-markdown>
    </div>
<vue-markdown> ## kolin</vue-markdown>
    <div style="display:flex;direction-flex:row">
     <vue-markdown style="width:50%">
``` kotlin
fun test(){
  list.forEach{ it -> log()}
}
```
    </vue-markdown>

    <vue-markdown style="width:50%">

``` java
private void test(){
  Log.d("","");
  Log.d("","");
}
```
    </vue-markdown>
    </div>
  </div>
</template>

<script>
import HomeHeader from '../components/header'
import HomeContent from '../components/content'
import HomePersonal from '../components/personal'
import VueMarkdown from 'vue-markdown'

export default {
  data() {
    return {
      items: []
    }
  },
  components: {
    HomeHeader,
    HomeContent,
    HomePersonal,
    VueMarkdown
  }
}
</script>

<style lang="css" scoped>
.container {
  width: 100%;
  margin: 0 auto;
}
.panel .inner {
  line-height: 2em;
  padding: 10px;
  background-color: #fff;
  border-radius: 0 0 3px 3px;
}
.panel .inner.no-padding {
  padding: 0;
}
</style>
