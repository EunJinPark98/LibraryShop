//상품 상태 변경
function updateStatus(status, itemCode){
    fetch('/admin/updateStatusFetch', { 			
        method: 'POST', 		
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        body: new URLSearchParams({
           // 컨트롤러에 전달할 데이터를 JSON 형식으로 이름 : 값
           'itemCode' : itemCode,
           'itemStatus' : status
        })
    })
    .then((response) => {
        if(!response.ok){
            //컨트롤러로 가다가 오류가 날 경우 실행
            return;
        }
        return response.text();		//컨트롤러에서 return하는 데이터가 없거나 int, String 일 때
        //return response.json(); 		//위의 경우 뺀 나머지 (객체, 리스트)
    })
    .then((data) => {
        alert('상품의 상태가 변경되었습니다.');
    })
    .catch(err=>{
        //컨트롤러 갔다가 돌아오는데 오류가 날 경우 실행
    });
}