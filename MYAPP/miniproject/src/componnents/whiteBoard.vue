<template>
 <div class="table-responsive">
        <table class="table table-striped table-sm">
        <thead>
            <tr>
              <th>Check</th>
              <th>순위</th>
              <th>포스터</th>
              <th>영화제목</th>
              <th>관람객수</th>
              <th>개봉일</th>              
              <th>삭제</th>
            </tr>
        </thead>
        <tbody>
            <tr v-for="(item, idx) in movieArr" :key=(idx)>
                <!-- <td><input type="checkbox" v-model="checkedValues[item.rnum - 1]"></td> -->
                <td>{{item.rank}}</td>
                <td><img v-bind:src="item.imgurl"></td>
                <td>
                    <a href="#" data-bs-toggle="modal" data-bs-target="#exampleModal" v-on:click="detailSearch(item.movieCd)">
                    {{item.movieNm}}
                    </a>
                </td>
                <td>{{Number(item.audiAcc).toLocaleString()}} 명</td>
                <td>{{item.openDt}}</td>              
                <td><button v-on:click="deleteClick(idx)">삭제</button></td>
            </tr>
        </tbody>
        </table>
      <modal v-bind:movieDetail="movieDetail"></modal>
    </div>
</template>

<script>
import modal from "./modal.vue";

export default {
components: {
    modal,
  },

  data() {
    return {
       movieDetail: {}
    };
  },

  props: {
    movieArr: Array,
    movieCd: String,
    checkedValues: Array,
  },

  model: {
    prop: "checkedValues",
    event: "updatedChecked",
  },

  methods: {
    deleteClick: function (idx) {
      this.$delete(this.movieArr, idx);
    },

    detailSearch: function (movieCd) {
      axios
        .get(
          "http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieInfo.json",
          {
            params: {
              key: "756e6c8584bda7b0084ecb882f94a26a",
              movieCd: movieCd,
            },

            responseType: "json",
          }
        )
        .then((movieDetail) => {
          console.log("코비스세부 접속성공");
          this.movieDetail = movieDetail.data.movieInfoResult.movieInfo;
          console.log(this.movieDetail);
        })
        .catch(function (error) {
          console.log("코비스세부가 이상해요");
          console.log(error);
        });
    },
  },
};
</script>

<style>
</style>
