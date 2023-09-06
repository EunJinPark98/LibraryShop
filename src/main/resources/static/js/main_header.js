//주소 검색
function openPost(){
    new daum.Postcode({
        oncomplete: function(data) {
            document.querySelector('#memberAddr').value =  data.roadAddress;
            
        }
    }).open();
}

//회원가입 시 데이터 유효성 검사
function joinValidate(){
    
    const joinForm = document.querySelector('#joinForm');       //form 안에 요소들은 name으로 접근가능!

    if(joinForm.memberId.value == ''){
        inputInvalidate('#id-error-div', '아이디를 입력해주세요.');
        return;
    }else if(joinForm.memberId.value.length < 4){
        inputInvalidate('#id-error-div', '아이디는 4자 이상 입력해주세요.');
        return;
    }

    // 정규식 테스트
    var telRegex = /^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/;
    const tel = joinForm.memberTels[0].value + '-' + joinForm.memberTels[1].value + '-' + joinForm.memberTels[2].value;
    if(!(telRegex.test(tel))){
        inputInvalidate('#tel-error-div', '연락처를 다시 입력해주세요.');
        return;
    }

    var emailRegex = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;
    const email = joinForm.memberEmails[0].value + joinForm.memberEmails[1].value
    if(!(emailRegex.test(email))){
        inputInvalidate('#email-error-div', '연락처를 다시 입력해주세요.');
        return;
    }

    joinForm.submit();
}

//validate 실패 시 메세지 설정
function inputInvalidate(tagId, msg){
    document.querySelector(tagId).style.display = 'block';
    document.querySelector(tagId).textContent = msg;
}


//아이디 중복확인
function idCheck(){
    fetch('/member/idCheck', { 		
        method: 'POST', 		
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        body: new URLSearchParams({
            'memberId' : document.querySelector('#memberId').value
        })
    })
    .then((response) => {
        if(!response.ok){
            return;
        }
        //return response.text();		//컨트롤러에서 return하는 데이터가 없거나 int, String 일 때
        return response.json(); 		//위의 경우 뺀 나머지 (객체, 리스트)
    })
    .then((data) => {
        if(data){
            alert('사용 가능한 아이디입니다.');
            document.querySelector('#joinBtn').disabled = false;
        }else{
            alert('사용 불가능한 아이디입니다.');
        }
    })
    .catch(err=>{
    });
}

//가입버튼 비활성화
function setDisabled(){
    document.querySelector('#joinBtn').disabled = true;
}

//회원 가입 모달창 닫힐 때 마다 실행
const joinModal = document.querySelector('#join-modal');
joinModal.addEventListener('hidden.bs.modal', event => {
    document.querySelector('#joinForm').reset(); 
    document.querySelector('#joinBtn').disabled = true;
})

