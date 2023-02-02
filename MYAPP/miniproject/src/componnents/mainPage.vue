<template>
  <div id="app">
    <header
      class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow"
    >
      <a class="navbar-brand col-md-3 col-lg-2 me-0 px-3 fs-6" href="#"
        >BoxOffice</a
      >
      <button
        class="navbar-toggler position-absolute d-md-none collapsed"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#sidebarMenu"
        aria-controls="sidebarMenu"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-icon"></span>
      </button>
      <input
        v-model="searchDate"
        class="form-control form-control-dark w-100 rounded-0 border-0"
        type="date"
        max="9999-12-31"
        placeholder="Search"
        aria-label="Search"
      />
      <div class="navbar-nav">
        <div class="nav-item text-nowrap">
          <a class="nav-link px-3" href="#" v-on:click="dateSearch">Search!!</a>
        </div>
      </div>
    </header>

    <div class="container-fluid">
      <div class="row">
        <nav
          id="sidebarMenu"
          class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse"
        >
          <div class="position-sticky pt-3 sidebar-sticky">
            <ul class="nav flex-column">
              <li class="nav-item">
                <a class="nav-link active" aria-current="page" href="#">
                  <span data-feather="home" class="align-text-bottom"></span>
                  날짜별 BoxOffice 순위
                </a>
              </li>
            </ul>
          </div>
        </nav>

        <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
          <h2>영화순위</h2>
          <button v-on:click="deleteCheckClick" class="btn btn-primary">
            선택 삭제
          </button>
          <whiteBoard
            v-bind:movie-arr="movieArr"
            v-on:check="onDeleteSelected"
          ></whiteBoard>
        </main>
      </div>
    </div>
  </div>
</template>

<script>
import whiteBoard from "./whiteBoard.vue";

export default {
  components: {
    whiteBoard,
  },

  data: function () {
    return {
      searchDate: "",
      movieArr: [],
      checkedValues: [],
    };
  },

  methods: {
    onDeleteSelected: function (checked) {
      this.checkedValues = checked;
    },

    deleteCheckClick: function () {
      this.checkedValues.forEach((el, idx) => {
        if (el) {
          this.movieArr = this.movieArr.filter(
            (movie) => Number.parseInt(movie.rnum) - 1 !== idx
          );
        }
      });
      this.checkedValues = [];
    },

    dateSearch: function () {
      const datenodash = this.searchDate.replace(/\-/g, "");

      axios
        .get(
          "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json",
          {
            params: {
              key: "756e6c8584bda7b0084ecb882f94a26a",
              targetDt: datenodash,
            },

            responseType: "json",
          }
        )
        .then((movieData) => {
          console.log("코비스 접속성공");
          this.movieArr = movieData.data.boxOfficeResult.dailyBoxOfficeList;
          this.movieArr.forEach((el) => {
            axios
              .get("https://dapi.kakao.com/v2/search/image", {
                headers: {
                  Authorization: "KakaoAK 5559977ec58da2ce68a5aba6c2027c04",
                },
                params: {
                  query: el.movieNm,
                },
                responseType: "json",
              })
              .then((kakaoData) => {
                console.log("카카오접속성공");
                let imgurl = kakaoData.data.documents[0].thumbnail_url;
                // console.log(this.movieArr);
                Vue.set(el, "imgurl", imgurl);
              })
              .catch(function (error) {
                console.log("카카오가 이상해요");
                console.log(error);
              });
          });
        })
        .catch(function (error) {
          console.log("코비스가 이상해요");
          console.log(error);
        });
    },
  },
};
</script>

<style>
</style>
