Vue.component('globalpage-component-a', {
    template: ` 
    <div class="table-responsive">
        <table class="table table-striped table-sm">
        <thead>
            <tr>
            <th scope="col">Check</th>
            <th scope="col">순위</th>
            <th scope="col">포스터</th>
            <th scope="col">영화제목</th>
            <th scope="col">관람객수</th>
            <th scope="col">개봉일</th>              
            <th scope="col">삭제</th>
            </tr>
        </thead>
        <tbody>
            <tr v-for="(item, idx) in movieArr">
                <td><input type="checkbox" v-model="checkedValues[idx]" v-on:click="checkBoxNum"></td>
                <td>{{item.rank}}</td>
                <td><img v-bind:src="item.imgurl"></td>
                <td>
                    <a href="#" data-bs-toggle="modal" data-bs-target="#exampleModal" v-on:click="detailSearch">
                    {{item.movieNm}}
                    </a>
                </td>
                <td>{{item.audiAcc}} 명</td>
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
                <h1 class="modal-title fs-5" id="exampleModalLabel">영화 상세정보, movieNm</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                openDt 제작년도
                nations nation nationNm 제작국가
                directors director peopleNm 감독
                actors actor peopleNm 배우
                showTm 상영시간
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
            </div>
            </div>
        </div>
        </div>
    </div>
    `,
    components: {

    },
    data() {
        return {
            checkedValues: []
        }
    },

    props: {
        movieArr: Array
    },
    methods: {
        deleteClick: function (idx) {
            this.$delete(this.movieArr, idx);
        },

        checkBoxNum: function () {
            this.$emit("check", this.checkedValues)
        },

        detailSearch: function () {

            axios.get('http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieInfo.json', {

                params: {
                    key: '756e6c8584bda7b0084ecb882f94a26a',
                    movieCd: '20112207'
                },

                responseType: 'json'

            }).then((movieDetail) => {
                console.log('코비스세부 접속성공');
                this.movieDetailArr = movieDetail.data.movieInfoResult.movieInfo;
                console.log(this.movieDetailArr);

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
        checked: [],
    },

    methods: {

        onDeleteSelected: function (checked) {
            this.checkedValues = checked;

        },

        deleteCheckClick: function (checked) {
            // if (this.checkedValues = false) {
            //     checknum=1;
            // }
            // console.log(checknum);
            console.log(this.checkedValues);
            this.$delete(this.movieArr, this.checkedvalues = true);

            console.log('삭제');
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