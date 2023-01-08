package liverary.view;

public enum LayoutsEnum {
	LoginLayout, /* 로그인 */
	UserMainLayout, /* [이용자] 메인 */
	MainLayout, /* [사서] 열람업무 - 반납/대출 (기본뷰) */
	RecentLogLayout, /* [사서] 열람업무 - 최근이용이력 */
	NoReturnedLayout, /* [사서] 열람업무 - 미반납자료조회/반납독촉 */
	AddNewBookLayout, /* [사서] 수서업무 - 자료입수 */
	GetDetailBookInfoLayout, /* [사서] 자료관리 - 자료상세조회 */
	RegisterStaffAccountLayout, /* [사서] 회원관리 - 직원등록 */
	GetDetailStaffAccountLayout /* [사서] 회원관리 - 직원조회/수정 */
}