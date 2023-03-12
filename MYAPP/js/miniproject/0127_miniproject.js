Vue.component('globalpage-component-a', {
    template: ` 
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
            <tr v-for="(item, idx) in movieArr">
                <td><input type="checkbox" v-model="checkedValues[item.rnum - 1]"></td>
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

    
        <!-- Modal -->
        <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-scrollable">
            <div class="modal-content">
                  <div class="modal-header">
                      <h1 class="modal-title fs-5" id="exampleModalLabel">{{movieDetail.movieNm}}</h1>
                      <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                  </div>
                  <div class="modal-body">
                      <tr>제작년도 <span>: </span>{{movieDetail.prdtYear}}</tr>
                      <tr>제작국가 <span>: </span><span v-for="nations in movieDetail.nations"> {{nations.nationNm}}</span></tr>
                      <tr>감독 <span>: </span><span v-for="director in movieDetail.directors">  {{director.peopleNm}}</span></tr>
                      <tr>배우 <span>: </span><span v-for="actor in movieDetail.actors">  {{actor.peopleNm}}</span></tr>
                      <tr>상영시간 <span>: </span>{{movieDetail.showTm}}분</tr>
                  </div>
                  <div class="modal-footer">
                      <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                 </div>
            </div>
        </div>
        </div>
    </div>
    `,

    data() {
        return {
            movieDetail: {}
        }
    },

    props: {
        movieArr: Array,
        movieCd: String,
        checkedValues: Array
    },

    model: {
        prop: 'checkedValues',
        event: 'updatedChecked'
    },
    
    methods: {
        deleteClick: function (idx) {
            this.$delete(this.movieArr, idx);
        },

        detailSearch: function (movieCd) {
            axios.get('http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieInfo.json', {

                params: {
                    key: '756e6c8584bda7b0084ecb882f94a26a',
                    movieCd: movieCd
                },

                responseType: 'json'

            }).then((movieDetail) => {
                console.log('코비스세부 접속성공');
                this.movieDetail = movieDetail.data.movieInfoResult.movieInfo;

            }).catch(function (error) {
                console.log('코비스세부가 이상해요');
                console.log(error);
            });
        }
    }
});


new Vue({
    data: {
        searchDate: '',
        movieArr: [],
        checkedValues: []
    },

    methods: {

        onDeleteSelected: function (checked) {
            this.checkedValues = checked;

        },

        deleteCheckClick: function () {

            this.checkedValues.forEach((el, idx) => {

                if (el) {
                    this.movieArr = this.movieArr.filter(movie => Number.parseInt(movie.rnum) - 1 !== idx);
                }

            });
            this.checkedValues = [];

        },

        dateSearch: function () {

            const datenodash = this.searchDate.replace(/\-/g, "");

            axios.get('http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json', {

                params: {
                    key: '756e6c8584bda7b0084ecb882f94a26a',
                    targetDt: datenodash,
                },

                responseType: 'json'
            }).then((movieData) => {
                console.log('코비스 접속성공');
                this.movieArr = movieData.data.boxOfficeResult.dailyBoxOfficeList;
                this.movieArr.forEach((el) => {
                    axios.get('https://dapi.kakao.com/v2/search/image', {

                        headers: {
                            Authorization: 'KakaoAK 5559977ec58da2ce68a5aba6c2027c04',
                        },
                        params: {
                            query: el.movieNm
                        },
                        responseType: 'json'

                    }).then((kakaoData) => {
                        console.log('카카오접속성공');
                        let imgurl = kakaoData.data.documents[0].thumbnail_url;
                        Vue.set(el, 'imgurl', imgurl);

                    }).catch(function (error) {
                        console.log('카카오가 이상해요');
                        console.log(error);
                    });
                })

            }).catch(function (error) {
                console.log('코비스가 이상해요');
                console.log(error);
            });

        }
    }

}).$mount('#app');