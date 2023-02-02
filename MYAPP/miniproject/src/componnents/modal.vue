<template>
  <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-scrollable">
            <div class="modal-content">
                  <div class="modal-header">
                      <h1 class="modal-title fs-5" id="exampleModalLabel">{{movieDetail.movieNm}}</h1>
                      <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                  </div>
                  <div class="modal-body">
                      <tr>제작년도 <span>: </span>{{movieDetail.prdtYear}}</tr>
                      <tr>제작국가 <span>: </span><span v-for="(nations, index) in movieDetail.nations" :key="index"> {{nations.nationNm}}</span></tr>
                      <tr>감독 <span>: </span><span v-for="(director, index) in movieDetail.directors" :key="index">  {{director.peopleNm}}</span></tr>
                      <tr>배우 <span>: </span><span v-for="(actor, index) in movieDetail.actors" :key="index">  {{actor.peopleNm}}</span></tr>
                      <tr>상영시간 <span>: </span>{{movieDetail.showTm}}분</tr>
                  </div>
                  <div class="modal-footer">
                      <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                 </div>
            </div>
        </div>
        </div>
    </div>
</template>

<script>
export default {
  data() {
    return {
    };
  },
  props: {
    movieDetail: Object
  },

  methods: {
    // start(movieDetail) {
    //   console.log(movieDetail);
    //   console.log("클릭");
    // },
  },
};
</script>

<style>
</style>
