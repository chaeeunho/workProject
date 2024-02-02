// 타이핑 효과
var typingBool = false;
var avocadoIdx = 0;
var typingIdx = 0;

// 아보카도 이미지 요소를 가져온다
var avocadoImages = $(".loading_image_avocado");

if (!typingBool) {
  // 타이핑이 진행되지 않았다면
  typingBool = true;
  
  // 0.5초 간격으로 아보카도 이미지와 타이핑 효과를 동시에 시작
  var interval = setInterval(function() {
    showNextAvocado();
    typing();
  }, 500);
}

function showNextAvocado() {
  if (avocadoIdx < avocadoImages.length) {
    // 아보카도 이미지 개수만큼 반복
    $(avocadoImages[avocadoIdx]).css("display", "inline-block");
    avocadoIdx++;
  } else {
    // 이미지가 끝나면 반복 종료
    clearInterval(interval);
  }
}

function typing() {
  var typingTxt = $(".typing-txt").text();
  typingTxt = typingTxt.split("");

  if (typingIdx < typingTxt.length) {
    $(".typing").append(typingTxt[typingIdx]);
    typingIdx++;
  } else {
    clearInterval(tyInt);
  }
}