$(document).ready(function() {
  // 초기 설정
  var currentSlide = 3; // 시작 슬라이드 번호
  var totalSlides = 3; // 전체 슬라이드 개수

	// 모든 슬라이드를 숨김
  $(".slide").hide();
  
  // 슬라이드가 전환되는 함수
  function nextSlide() {
    // 현재 슬라이드 숨김
    $("#slide" + currentSlide).hide();

    // 다음 슬라이드로 이동
    currentSlide++;
    if (currentSlide > totalSlides) {
      currentSlide = 1; // 마지막 슬라이드 이후 첫 번째 슬라이드로 이동
    }

    // 다음 슬라이드 표시
    $("#slide" + currentSlide).show();
  }

  // 페이지가 로드될 때 초기 슬라이드 표시
  nextSlide();

  // 3초마다 nextSlide 함수 호출하여 슬라이드 전환 (무한 반복)
  setInterval(nextSlide, 3000);
});


/* 날씨 정보 */
// 날짜를 변수화
const date = new Date();
console.log(date);
let  year = date.getFullYear();        // 2024
let month = '0' + date.getMonth() + 1; // 011
let day = '0' + date.getDate();        // 025
	
month = month.substring(1);            // substring(인덱스) 인덱스부터 끝까지 추출
day = day.substring(1);

let today = year + month + day;        // 20240125

// 날씨 아이콘
var weatherIcon = {
    '01' : 'fas fa-sun',
    '02' : 'fas fa-cloud-sun',
    '03' : 'fas fa-cloud',
    '04' : 'fas fa-cloud-meatball',
    '09' : 'fas fa-cloud-sun-rain',
    '10' : 'fas fa-cloud-showers-heavy',
    '11' : 'fas fa-poo-storm',
    '13' : 'far fa-snowflake',
    '50' : 'fas fa-smog'
};

let lat = "";
let lon = "";

$.ajax({
	type: "GET",
	url: "http://api.openweathermap.org/data/2.5/weather?q=seoul&appid=5b751f8ace8e5530b9c88a40d48515ce",
  	success: function(data){
			var temp = String((data.main.temp - 272)).substring(0,3); // 온도
      var location = data.name; // 지역이름 
      $('#weahter_info').append('지역 ：' + location + ' 온도　：' + tempr　+ "도입니다. "+'\n');
			// 아이콘 취득 
      var imgURL = "http://openweathermap.org/img/w/" + data.weather[0].icon + ".png";
      // 아이콘 표시
      $('#img').attr("src", imgURL);
    },
    error: function(error){
		console.log(error);
	}
});