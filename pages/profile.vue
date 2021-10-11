<template>
  <div>
    <b-container class="d-flex flex-column align-items-center justify-content-center py-5">
      <b-card class="shadow-lg p-3 w-50">
        <b-card-body class="d-flex flex-column justify-content-center align-items-center position-relative">
          <h1 class="font-weight-light text-center" style="font-size: calc(7rem - 1vw)">
            {{ updateData.userName }} {{ updateData.userLastname }}
          </h1>
          <div class="text-center font-weight-light" style="font-size: calc(4rem - 1vw)">
            Documento de identidad
            <p class="font-weight-light" style="font-size: 2rem">CC.{{ updateData.userDni }}</p>
          </div>
          <div class="text-center font-weight-light" style="font-size: calc(4rem - 1vw)">
            Correo electronico
            <p class="font-weight-light" style="font-size: 2rem">{{ updateData.userEmail }}</p>
          </div>
          <div class="d-flex flex-column align-items-center justify-content-center" style="font-size: 4rem">
            Mi plan
            <div>
              <p v-show="false">{{ getUser() }}</p>
              <div v-if="updateData.subscription != undefined">
                <p v-show="false">{{ updateData.subscription.subscriptionType }}</p>
                <div v-if="updateData.subscription.subscriptionType != undefined">
                  <p v-if="updateData.subscription.subscriptionType == 1" class="font-weight-light">Plan clasico</p>
                  <div v-if="updateData.subscription.subscriptionType == 2"
                       class="font-weight-light d-flex flex-column justify-content-center align-items-center">
                    <p>Plan plus</p>
                    <p class="text-center" style="font-size: 1.5rem;">Fecha de vencimiento:
                      {{ updateData.subscription.subscriptionExpiration }}</p>
                  </div>
                  <p v-if="updateData.subscription.subscriptionType == 3" class="font-weight-light">Plan familiar</p>
                </div>
              </div>
            </div>
          </div>
          <div v-if="active==true" class="position-relative w-100 h-100 rounded-lg p-3 my-3">
            <div class="header d-flex justify-content-end align-items-start">
              <b-button @click="isActive">X</b-button>
            </div>
            <div>
              <p class="text-center text-p-title font-weight-light">Actualizar Informacion</p>
            </div>
            <div>
              <form @submit.prevent="update">
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
                    <label class="col-form-label fs-2 font-weight-light" for="email"
                           style="color: rgba(73, 80, 87, 1);">Correo
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
                    <b-button class="p-btn btn-outline-none border-0 fs-3 w-100 my-3" type="submit">Actualizar
                    </b-button>
                    <p v-if="error" class="alert-danger w-50 py-3 text-center fs-4 rounded-lg mt-4">Has introducido mal
                      el
                      email o la contraseña.</p>
                  </b-col>
                </b-row>
              </form>
            </div>
          </div>
          <b-button v-show="active==false" @click="isActive">
            Editar informacion
          </b-button>
          <div class="d-flex flex-column align-items-center justify-content-center" style="font-size: 4rem">
            Mis beneficarios
            <div class="d-flex">
              <b-button v-show="activeCre==false" @click="getAllBeneficiary">
                Cargar
              </b-button>
              <b-button v-show="activeCre==false" class="mx-2" @click="activeCreate">
                Crear
              </b-button>
              <b-button v-show="activeRe==false" @click="activeRemove">
                Eliminar
              </b-button>
            </div>
            <form v-if="activeRe == true"
                  class="d-flex justify-content-center align-items-center flex-column py-5"
                  @submit.prevent="remove">
              <div class="header d-flex justify-content-end align-items-start">
                <b-button @click="activeRemove">X</b-button>
              </div>
              <label for="idB" class="col-form-label font-weight-light">Id del beneficiario</label>
              <input id="idB" v-model="beneficiaryId" type="number" class="form-control fs-2 my-3"
                     placeholder="Ingrese el identificador del beneficiario"
                     required>
              <b-button type="submit">
                Eliminar beneficiario
              </b-button>
            </form>
            <form v-show="activeCre == true" @submit.prevent="beneficiaryCreate">
              <div class="header d-flex justify-content-end align-items-start">
                <b-button @click="activeCreate">X</b-button>
              </div>
              <b-row>
                <b-col class="col-12 col-sm-6 px-5">
                  <label class="col-form-label fs-2 font-weight-light" for="bname" style="color: rgba(73, 80, 87, 1);">Nombres</label>
                  <input id="bname" v-model="beneficiaryName" class="form-control fs-2 my-3"
                         placeholder="Ingresa el nombre de tu beneficiario"
                         required type="text">
                </b-col>
                <b-col class="col-12 col-sm-6 px-5">
                  <label class="col-form-label fs-2 font-weight-light" for="blastname"
                         style="color: rgba(73, 80, 87, 1);">Apellidos</label>
                  <input id="blastname" v-model="beneficiaryLastname" class="form-control fs-2 my-3"
                         placeholder="Ingresa los apellidos de tu beneficiario"
                         required type="text">
                </b-col>
                <b-col class="col-12 px-5">
                  <label class="col-form-label fs-2 font-weight-light" for="bdni" style="color: rgba(73, 80, 87, 1);">Documento
                    de identidad</label>
                  <input id="bdni" v-model="beneficiaryDni" class="form-control fs-2 my-3"
                         placeholder="Ingresa el documento de tu beneficiario"
                         required type="number">
                </b-col>
              </b-row>
              <b-row>
                <b-col class="col-12 px-5 d-flex flex-column align-items-center">
                  <b-button class="p-btn btn-outline-none border-0 fs-3 w-100 my-3" type="submit">Crear
                  </b-button>
                </b-col>
              </b-row>
            </form>
          </div>
        </b-card-body>
      </b-card>
      <b-container class="d-flex justify-content-center my-3">
        <div v-show="items != null" class="w-50 rounded-lg" style="overflow-x: auto; border: 1px solid gray">
          <b-table :items="items"></b-table>
        </div>
      </b-container>
    </b-container>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "Profile",
  data: () => ({
    items: null,
    BENEFICIARY_GET_PATH: 'http://localhost:8080/api/affiliate/beneficiary/all',
    updateData: {},
    active: false,
    activeCre: false,
    activeRe: false,
    beneficiaryId: null,
    beneficiaryDni: null,
    beneficiaryName: null,
    beneficiaryLastname: null,
    userName: null,
    userLastname: null,
    userDni: null,
    userEmail: null,
    userPassword: null,
    USER_UPDATE_PATH: `http://localhost:8080/api/affiliate`,
    error: false
  }),
  computed: {
    getUserLogged() {
      return this.$cookies.get('userLogged')
    },
    getUserUpdateData() {
      const id = this.getUserLogged.userId;
      return axios.get(`http://localhost:8080/api/affiliate/read/${id}`)
    },
  },
  methods: {
    removeBeneficiary(id) {
      return axios.delete(`http://localhost:8080/api/affiliate/beneficiary/delete/${id}`)
    },
    async remove() {
      await this.removeBeneficiary(this.beneficiaryId)
      window.location.href = 'profile'
      this.$forceUpdate()
    },
    isActive() {
      this.active = !this.active
    },
    activeCreate() {
      this.activeCre = !this.activeCre
    },
    activeRemove() {
      this.activeRe = !this.activeRe
    },
    async getUser() {
      await this.getUserUpdateData
        .then(res => (this.updateData = res.data))
    },
    authBeneficiary(id, name, lastname, dni, email, password) {
      const user = {
        'userId': id,
        'userName': name,
        'userLastname': lastname,
        'userDni': dni,
        'userEmail': email,
        'userPassword': password,
      };
      return axios.post('http://localhost:8080/api/affiliate/beneficiary/all', user)
    },
    async getAllBeneficiary() {
      await this.authBeneficiary(this.updateData.userId, this.updateData.userName, this.updateData.userLastname, this.updateData.userDni, this.updateData.userEmail, this.updateData.userPassword)
        .then(res => (this.items = res.data))
      this.$forceUpdate()
    },
    authUpdate(id, name, lastname, dni, email, password) {
      const user = {
        'userId': id,
        'userName': name,
        'userLastname': lastname,
        'userDni': dni,
        'userEmail': email,
        'userPassword': password,
      };
      const idE = this.getUserLogged.userId;
      return axios.put(`http://localhost:8080/api/affiliate/update/${idE}`, user)
    },
    async update() {
      try {
        await this.authUpdate(this.$cookies.get('userLogged').userId, this.userName, this.userLastname, this.userDni, this.userEmail, this.userPassword)
        // this.$router.push("/profile")
        window.location.href = "profile"
      } catch (e) {
        this.error = true;
      }
    },
    createBeneificary(dni, name, lastname, uid, uname, ulastname, udni, uemail, upassword) {
      const beneficiary = {
        'beneficiaryDni': dni,
        'beneficiaryName': name,
        'beneficiaryLastname': lastname,
        'user': {
          'userId': uid,
          'userName': uname,
          'userLastname': ulastname,
          'userDni': udni,
          'userEmail': uemail,
          'userPassword': upassword,
        }
      }
      return axios.post('http://localhost:8080/api/affiliate/beneficiary/create', beneficiary)
    },
    async beneficiaryCreate() {
      try {
        await this.createBeneificary(this.beneficiaryDni, this.beneficiaryName, this.beneficiaryLastname, this.$cookies.get('userLogged').userId, this.$cookies.get('userLogged').userName, this.$cookies.get('userLogged').userLastname, this.$cookies.get('userLogged').userDni, this.$cookies.get('userLogged').userEmail, this.$cookies.get('userLogged').userPassword)
        window.location.href = "profile"
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
    padding: 70px 0;
  }
}

@media (min-height: 1000px) {
  #contain {
    padding: 90px 0;
  }
}
</style>
