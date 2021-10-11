<template>
  <div>
    <div class="response"></div>
    <b-container>
      <b-form-row>
        <b-col class="col-lg-12 col-md-12 col-sm-12 col-12 my-auto">
          <b-container>
            <h1 class="text-p-title font-weight-normal">Lo mas nuevo</h1>
            <div class="element my-5"></div>
            <p class="text-s-title text-justify text-mulish font-weight-light">Junto a nuestras novedades, estaras
              enterados de
              jornadas, citas medicas y grandes promociones.</p>
          </b-container>
        </b-col>
        <b-col class="col-lg-12 col-md-12 col-sm-12 col-12 my-auto">
          <b-container>
            <b-form-row>
              <b-col class="col-md-6 col-sm-12 col-12 d-flex justify-content-center align-items-center">
                <p v-show="false">{{ news.data }}</p>
                <div v-if="typeof news.data != 'undefined'">
                  <div v-if="typeof news.data[getLength - 1] != 'undefined' ">
                    <p v-show="false">{{ news.data[getLength - 1].image }}</p>
                    <div v-if="typeof news.data[getLength - 1].image != 'undefined' ">
                      <div v-if="typeof news.data[getLength - 1].image[0].formats != 'undefined' ">
                        <div v-if="news.data[getLength - 1].image[0].formats['small'].url != 'undefined' ">
                          <img :src="URL_BASE+news.data[getLength - 1].image[0].formats['small'].url" alt="noticia"
                               class="img-fluid"
                               style="border-radius: 10px">
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </b-col>
              <b-col class="col-md-6 col-sm-12 col-12">
                <p v-show="false">{{ news.data }}</p>
                <div v-if="typeof news.data != 'undefined'">
                  <div v-if="typeof news.data[getLength - 1] != 'undefined' ">
                    <h1 class="text-description font-weight-light text-mulish" style="font-size: calc(3.9rem + 0.5vw)">
                      {{ news.data[getLength - 1].title }}</h1>
                    <p class="text-description font-weight-light">
                      <strong>Fecha: </strong>{{ news.data[getLength - 1].date }}</p>
                    <p class="text-description font-weight-light">
                      <strong>Contacto: </strong>{{ news.data[getLength - 1].email }}</p>
                    <p class="text-description font-weight-light text-mulish text-justify"
                       v-html="news.data[getLength - 1].content"></p>
                  </div>
                </div>
              </b-col>
            </b-form-row>
          </b-container>
        </b-col>
      </b-form-row>
    </b-container>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "News",
  data: () => ({
    URL_BASE: 'https://api.tarjeta-vip.com',
    news: {},
    last_news: null
  }),
  computed: {
    getLength() {
      return this.news.data.length
    }
  },
  mounted() {
    axios
      .get(`${this.URL_BASE}/noticias`)
      .then(response => (this.news = response))
  },

  methods: {
    getPicture() {
      return `${this.URL_BASE}/${this.last_news}`
    }
  }
}
</script>
