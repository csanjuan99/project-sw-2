<template>
  <div>
    <header>
      <b-navbar class="navbar-light bg-white shadow-sm fixed-top" toggleable="md">
        <div class="container px-5 py-1">
          <b-navbar-brand>
            <nuxt-link class="navbar-brand" to="/"><img alt="vip" src="/assets/brand.svg" width="70"></nuxt-link>
          </b-navbar-brand>
          <b-navbar-toggle class="navbar-toggler" target="nav-collapse" type="button">
            <span class="navbar-toggler-icon"></span>
          </b-navbar-toggle>
          <b-collapse id="nav-collapse" class="justify-content-end text-center text-lg-start my-2" is-nav>
            <b-navbar-nav class="d-flex align-items-center">
              <b-nav-item class="mx-4 text-s-title">
                <nuxt-link aria-current="page" class="nav-link text-mulish" to="/">Inicio</nuxt-link>
              </b-nav-item>
              <b-nav-item class="mx-4 text-s-title">
                <nuxt-link class="nav-link text-mulish" to="/news">Noticias</nuxt-link>
              </b-nav-item>
              <b-nav-item class="mx-4 text-s-title">
                <a class="nav-link text-mulish">Planes</a>
              </b-nav-item>
              <b-nav-item class="mx-4 text-s-title">
                <a class="nav-link text-mulish" target="_blank">Servicios</a>
              </b-nav-item>
              <b-nav-item-dropdown class="mx-4 text-s-title" text="Usuario">
                <b-dropdown-item>
                  <b-button v-if="getUserLogged == undefined"
                            class="btn-outline-none bg-transparent border-0 text-dark text-decoration-none"
                            style="font-size: 1.5rem"
                            to="/signin">
                    Iniciar sesion
                  </b-button>
                </b-dropdown-item>
                <b-dropdown-item v-if="getUserLogged != undefined">
                  <b-button class="btn-outline-none bg-transparent border-0 text-decoration-none text-dark"
                            style="font-size: 1.5rem"
                            to="/profile">
                    Perfil
                  </b-button>
                </b-dropdown-item>
                <b-dropdown-item v-if="getUserLogged != undefined">
                  <b-button class="btn-outline-none bg-transparent border-0 text-dark text-decoration-none"
                            style="font-size: 1.5rem" @click="deleteUserLogged">
                    Cerrar sesion
                  </b-button>
                </b-dropdown-item>
                <nuxt-link class="nav-link" to="/signin">
                </nuxt-link>
              </b-nav-item-dropdown>
            </b-navbar-nav>
          </b-collapse>
        </div>
      </b-navbar>
    </header>
    <Nuxt style="padding: 100px 0 25px 0"/>
    <footer class="text-center text-white"
            style="background: linear-gradient(183.41deg, #67C3F3 -8.57%, #5A98F2 82.96%)">
      <div class="container p-4">
        <section>
          <p class="d-flex justify-content-center align-items-center">
            <span class="text-s-title text-mulish font-weight-bold">¿Aún tienes dudas?</span>
          </p>
          <div id="socials" class="d-flex justify-content-center align-items-center">
            <a class="px-4" href="https://www.instagram.com/tarjetavipsalud/" target="_blank"><img
              alt="" src="/assets/footer-ig.svg"></a>
            <a class="px-4" href="https://www.facebook.com/tarjetavipsas" target="_blank"><img
              alt="" src="/assets/footer-fb.svg"></a>
            <a class="px-4"
               href="https://api.whatsapp.com/send?phone=573175862033&amp;&amp;text=Hola,%20quisiera%20mas%20informacion."
               target="_blank"><img alt="" src="/assets/footer-wapp.svg"></a>
            <a class="px-4" href="https://m.me/tarjetavipsas" target="_blank"><img
              alt="" src="/assets/footer-msg.svg"></a>
          </div>
          <div class="d-flex justify-content-center align-items-center">
            <div><img alt="camara de comercio aguachica" class="px-3" src="/assets/footer-cca.png"
                      style="max-width: 100px;"></div>
            <div><img alt="dian" class="px-3" src="/assets/footer-dian.png" style="max-width: 100px;"></div>
            <div><img alt="superintendencia de industria y comercio" class="px-3" src="/assets/footer-ic.png"
                      style="max-width: 100px;"></div>
          </div>
        </section>
        <div class="fs-3 text-center text-s-title text-mulish">
          © 2021 Copyright:
          <span class="text-white text-s-title text-mulish">Centro de Servicios Integrales V.I.P</span>
        </div>
      </div>
    </footer>
  </div>
</template>

<script>

export default {
  name: "Default",
  computed: {
    getUserLogged() {
      return this.$cookies.get('userLogged');
    },

  },
  methods: {
    deleteUserLogged() {
      window.location.href = "signin"
      return this.$cookies.remove('userLogged')
    }
  }
}
</script>

<style scoped>

.navbar .nav-item:hover {
  transform: scale(1.15);
  font-weight: bold;
  transition: all .2s ease-in-out;
}

</style>
