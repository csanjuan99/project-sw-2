<template>
  <div>
    <div v-if="user.authorities != null" class="admin">
      <div v-if="user.authorities[0] != null">
        <div v-if="user.authorities[0].authority == 'ROLE_ADMIN' ">
          <b-container class="d-flex flex-column align-items-center justify-content-center">
            <div class="title">
              <h1 class="font-weight-light text-p-title">Panel de control de administracion</h1>
            </div>
            <div class="d-flex flex-column justify-content-center align-items-center">
              <p class="text-s-title font-weight-light m-0">Consulta de todos los afiliados</p>
              <div v-show="affiliates != null" class="w-50 rounded-lg" style="overflow-x: auto; border: 1px solid gray">
                <b-table :items="affiliates"></b-table>
              </div>
            </div>
            <div class="d-flex flex-column justify-content-center align-items-center">
              <p class="text-s-title font-weight-light m-0">Consulta de todos los beneficiarios</p>
              <div v-show="beneficiary != null" class="w-50 rounded-lg"
                   style="overflow-x: auto; border: 1px solid gray">
                <b-table :items="beneficiary"></b-table>
              </div>
            </div>
          </b-container>
        </div>
      </div>
    </div>
    <div v-if="user.authorities != null" class="no-admin">
      <div v-if="user.authorities[0] != null">
        <div v-if="user.authorities[0].authority == 'ROLE_USER' ">
          <b-container>
            <p class="font-weight-light text-p-title">No tienes acceso a este modulo</p>
          </b-container>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "Admin",
  data: () => ({
    user: {},
    affiliates: null,
    beneficiary: null
  }),
  mounted() {
    this.getUser()
    this.getAffiliate()
    this.getBeneficiary()
  },
  computed: {
    fetchAffiliate() {
      return axios.get('http://localhost:8080/api/affiliate/all')
    },
    fetchBeneficiary() {
      return axios.get('http://localhost:8080/api/affiliate/beneficiary/everyone')
    }
  },
  methods: {
    getUser() {
      this.user = this.$cookies.get('userLogged')
    },
    async getAffiliate() {
      await this.fetchAffiliate
        .then(res => console.log(this.affiliates = res.data))
    },
    async getBeneficiary() {
      await this.fetchBeneficiary
        .then(res => (this.beneficiary = res.data))
    }
  }
}
</script>

<style scoped>

</style>
