//장바구니 버튼 클릭 시 로그인 여부 체크
function isLogin(loginInfo, itemCode){
    if(loginInfo == null){
        if(confirm('로그인 후 사용가능합니다.\n로그인 페이지로 이동하시겠습니까?')){
            location.href = '/member/loginForm';
        }
    }else{
        fetch('/cart/insertCartFetch', { 	
            method: 'POST', 		
            cache: 'no-cache',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
            },
            body: new URLSearchParams({
                'itemCode' : itemCode,
                'cartCnt' : document.querySelector('input[type="number"]').value
            })
        })
        .then((response) => {
            if(!response.ok){
                alert('fetch error!\n컨트롤러로 통신중에 오류가 발생했습니다.');
                return;
            }
            return response.text();		//컨트롤러에서 return하는 데이터가 없거나 int, String 일 때
            //return response.json(); 		//위의 경우 뺀 나머지 (객체, 리스트)
        })
        //fetch 통신 후 실행 영역
        .then((data) => {//data -> controller에서 리턴되는 데이터!
            if(data == 1){
                if(confirm('상품이 장바구니에 담겼습니다.\n장바구니로 이동하시겠습니까?')){
                    location.href = '/cart/list';
                }
            }else{
                alert('일시적인 오류가 발생했습니다. 다시 시도해주세요.');
            }
        })
        //fetch 통신 실패 시 실행 영역
        .catch(err=>{
            alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
            console.log(err);
        });
    }
}

/////////////////////////////////////////
///////이벤트 영역////////////////////////
/////////////////////////////////////////

//상품 수량이 변경될 때 마다 실행되는 이벤트
const cartCntInput = document.querySelector('input[type="number"]');
cartCntInput.addEventListener('input', function(e){
    if(e.target.value == ''){
        e.target.value = 1;
    }
    const itemPrice = e.target.dataset.itemPrice;
    const cnt = e.target.value;
    const total = itemPrice * cnt;

    document.querySelector('#total-price-span').innerHTML = '￦' + total.toLocaleString('ko-KR');

    document.querySelector('input[name="buyPrice"]').value = total;

    
});


//바로구매 클릭
function goBuyForm(loginInfo) {
    if (loginInfo == null) {
        if (confirm('로그인 후 사용가능합니다.\n로그인 페이지로 이동하시겠습니까?')) {
            location.href = '/member/loginForm';
        }
    } else {
        if (confirm('해당 상품을 구매하시겠습니까?')) { 
            document.querySelector('#buyInfoForm').submit();
        }
    }
}