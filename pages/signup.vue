<template>
  <div id="container" style="background-color: #f6f6f6">
    <b-container>
      <div id="contain" class="d-flex flex-column justify-content-center align-items-center">
        <b-card class="shadow-lg p-0">
          <b-card-header class="d-flex justify-content-between" style="background: #D4E6FF; ">
            <div class="mr-5">
              <p class="fs-1 font-weight-normal" style="color: #458FF6;">
                No tienes una cuenta?
              </p>
              <p class="fs-4 font-weight-normal mb-5 md-sm-0" style="color: #458FF6;">
                Registrate para continuar
              </p>
            </div>
            <div>
              <picture style="max-width: 175px">
                <img class="img-fluid" src="/assets/sign-in-header.svg">
              </picture>
            </div>
          </b-card-header>
          <b-card-body class="p-x position-relative">
            <form @submit.prevent="signup">
              <b-row>
                <b-col class="col-12 col-sm-6 px-5">
                  <label class="col-form-label fs-2 font-weight-light" for="name" style="color: rgba(73, 80, 87, 1);">Nombres</label>
                  <input id="name" v-model="userName" class="form-control fs-2 my-3" placeholder="Ingresa tu nombre"
                         required type="text">
                </b-col>
                <b-col class="col-12 col-sm-6 px-5">
                  <label class="col-form-label fs-2 font-weight-light" for="lastname"
                         style="color: rgba(73, 80, 87, 1);">Apellidos</label>
                  <input id="lastname" v-model="userLastname" class="form-control fs-2 my-3"
                         placeholder="Ingresa tu nombre"
                         required type="text">
                </b-col>
                <b-col class="col-12 px-5">
                  <label class="col-form-label fs-2 font-weight-light" for="dni" style="color: rgba(73, 80, 87, 1);">Documento
                    de identidad</label>
                  <input id="dni" v-model="userDni" class="form-control fs-2 my-3"
                         placeholder="Ingresa el numero de documento de identidad"
                         required type="number">
                </b-col>
                <b-col class="col-12 px-5">
                  <label class="col-form-label fs-2 font-weight-light" for="email" style="color: rgba(73, 80, 87, 1);">Correo
                    electronico</label>
                  <input id="email" v-model="userEmail" class="form-control fs-2 my-3" placeholder="Ingresa tu email"
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
                  <b-button class="p-btn btn-outline-none border-0 fs-3 w-100 my-3" type="submit">Registrarse</b-button>
                  <p v-if="error" class="alert-danger w-50 py-3 text-center fs-4 rounded-lg mt-4">Has introducido mal el
                    email o la contraseña.</p>
                </b-col>
              </b-row>
            </form>
          </b-card-body>
        </b-card>
      </div>
    </b-container>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: "Signup",
  data: () => ({
    userName: null,
    userLastname: null,
    userDni: null,
    userEmail: null,
    userPassword: null,
    SIGN_UP_PATH: 'http://localhost:8080/auth/signup',
    error: false
  }),
  methods: {
    authSignup(email, password, name, lastname, dni) {
      const user = {
        'userEmail': email,
        'userPassword': password,
        'userName': name,
        'userLastname': lastname,
        'userDni': dni
      }
      return axios.post(this.SIGN_UP_PATH, user)
    },
    async signup() {
      try {
        await this.authSignup(this.userEmail, this.userPassword, this.userName, this.userLastname, this.userDni)
        this.$router.push("/signin")
      } catch (e) {
        this.error = true;
      }
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
    padding: 90px 0;
  }
}

@media (min-height: 1000px) {
  #contain {
    padding: 90px 0;
  }
}
</style>
