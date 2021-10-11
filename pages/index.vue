<template>
  <index/>
</template>

<script>
import index from "~/pages/sections/index";

export default {
  components: {index},
}
</script>

<style>
* {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
}

html {
  font-size: 62.5%;
  font-family: 'Mulish', sans-serif !important;
}

.text-s-title {
  font-size: calc(1.3rem + 0.5vw);
}

.text-p-title {
  font-size: calc(5rem + 0.1vw);
}

.text-description {
  font-size: 2rem;
}

.text-mulish {
  font-family: 'Mulish', sans-serif !important;
}

.element {
  border-radius: 10px;
  border: 1px solid black;
  width: 70px;
}

.p-btn {
  background-color: #458FF6;
}

.p-btn:hover {
  background-color: #3f84e8;
}

::-webkit-scrollbar {
  width: 12px;
  background-color: #ffffff;
}

::-webkit-scrollbar-track {
  border-radius: 10px;
  background-color: #efefef;
}

::-webkit-scrollbar-thumb {
  border-radius: 10px;
  background-color: #dce6fc;
}
</style>
