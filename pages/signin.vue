<template>
  <div id="container" style="background-color: #f6f6f6">
    <b-container>
      <div id="contain" class="d-flex flex-column justify-content-center align-items-center">
        <b-card class="shadow-lg p-0">
          <b-card-header class="d-flex justify-content-between" style="background: #D4E6FF; ">
            <div class="mr-5">
              <p class="fs-1 font-weight-normal" style="color: #458FF6;">
                Bienvenido de nuevo!
              </p>
              <p class="fs-4 font-weight-normal mb-5 md-sm-0" style="color: #458FF6;">
                Inicie sesion para continuar
              </p>
            </div>
            <div>
              <picture style="max-width: 175px">
                <img class="img-fluid" src="/assets/sign-in-header.svg">
              </picture>
            </div>
          </b-card-header>
          <b-card-body class="p-x position-relative">
            <img alt="avatar" class="position-relative avatar-sign-in px-2" src="/assets/feedback-default.png"
                 width="70px">
            <form @submit.prevent="signin">
              <b-row>
                <b-col class="col-12 px-5">
                  <label class="col-form-label fs-2 font-weight-light" for="email" style="color: rgba(73, 80, 87, 1);">Correo
                    electronico</label>
                  <input id="email" v-model="userEmail" class="form-control fs-2 my-3" placeholder="Igresa tu email"
                         required type="text">
                </b-col>
                <b-col class="col-12 px-5">
                  <label class="col-form-label fs-2 font-weight-light" for="password"
                         style="color: rgba(73, 80, 87, 1);">Contraseña</label>
                  <input id="password" v-model="userPassword" class="form-control fs-2 my-3"
                         placeholder="Ingresa tu contraseña"
                         required type="password">
                </b-col>
              </b-row>
              <b-row>
                <b-col class="col-12 px-5 d-flex flex-column align-items-center">
                  <b-button class="p-btn btn-outline-none border-0 fs-3 w-100" type="submit">Iniciar sesion</b-button>
                  <p v-if="error" class="alert-danger w-50 py-3 text-center fs-4 rounded-lg mt-4">Has introducido mal el
                    email o la contraseña.</p>
                </b-col>
              </b-row>
            </form>
          </b-card-body>
          <div class="my-4 d-flex flex-row justify-content-center align-items-center">
            <svg class="bi bi-lock-fill" fill="currentColor" height="16" viewBox="0 0 16 16" width="16"
                 xmlns="http://www.w3.org/2000/svg">
              <path
                d="M8 1a2 2 0 0 1 2 2v4H6V3a2 2 0 0 1 2-2zm3 6V3a3 3 0 0 0-6 0v4a2 2 0 0 0-2 2v5a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V9a2 2 0 0 0-2-2z"/>
            </svg>
            <nuxt-link class="text-decoration-none font-weight-light fs-4 mx-2" href="#" style="color: #495057" to="/">
              Olvidaste tu contraña?
            </nuxt-link>
          </div>
        </b-card>
        <div>
          <div class="fs-4 font-weight-normal my-3">No tienes una cuenta?
            <nuxt-link class="mx-2" to="/signup">Registrate</nuxt-link>
          </div>
        </div>
      </div>
    </b-container>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: "Signin",
  data: () => ({
    SIGN_IN_PATH: 'http://localhost:8080/auth/signin',
    userEmail: null,
    userPassword: null,
    error: false,
    data: {}
  }),
  methods: {
    authSignin(email, password) {
      const user = {
        'userEmail': email,
        'userPassword': password
      };
      return axios.post(this.SIGN_IN_PATH, user)
        .then(this.$router.push("/profile"))
    },
    async signin() {
      try {
        await this.authSignin(this.userEmail, this.userPassword)
          .then(res => (this.data = res.data))
        this.$cookies.set('userLogged', this.data)
        location.reload()
      } catch (e) {
        this.error = true;
      }
    },
    setUserLogged(userLogged) {
      this.$cookies.set('userLogged', userLogged);
    },
    getUserLogged() {
      return this.$cookies.get('userLogged');
    }
  }
}
</script>

<style scoped>

.card {
  border-radius: 10px !important;
}

.card-header {
  border-radius: 10px !important;
}

.card-body {
  padding: 0;
}

.header-img {
  width: 175px;
  height: 150px;
  background: url('/static/assets/sign-in-header.svg') no-repeat center;
}

.avatar-sign-in {
  top: -30px;
}

@media (max-height: 999px) {
  #contain {
    padding: 70px 0;
  }
}

@media (min-height: 1000px) {
  #contain {
    padding: 90px 0;
  }
}

</style>
