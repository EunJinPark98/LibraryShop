const memberId = document.querySelector('#memberId');
const memberPw = document.querySelector('#memberPw');
fetch('/member/login', {
    method: 'POST',
    cache: 'no-cache',
    headers: {
        'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
    },
    body: new URLSearchParams({
       'memberId' : memberId,
       'memberPw' : memberPw
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
    
})
.catch(err=>{
    //컨트롤러 갔다가 돌아오는데 오류가 날 경우 실행
});