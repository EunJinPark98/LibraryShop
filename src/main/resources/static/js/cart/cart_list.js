//페이지가 열리면 바로 실행 함수
setTotalPrice();

// 전체 체크박스 선택&해제
function setCheck() {
    const allCheckbox = document.querySelector('#allCheckbox');
    const chks = document.querySelectorAll('.chk');
    if (allCheckbox.checked) {
        chks.forEach(function (element) {
            element.checked = true;
        });
    } else {
        chks.forEach(function (element) {
            element.checked = false;
        });
    }
    setTotalPrice();
}

// 구매 총 가격 (체크박스 되어있는 것!)
function setTotalPrice(){
    const checkedChks = document.querySelectorAll('.chk:checked');
    let totalPrice = 0;
    checkedChks.forEach(function(element, idx){
        totalPrice += parseInt(element.dataset.totalPrice);
    })
    document.querySelector('#total-price').innerHTML = '￦' + totalPrice.toLocaleString('ko-KR');
}

//선택한 아이템 삭제
function deleteCartItem(){
    const checkedChks = document.querySelectorAll('.chk:checked');

    if(checkedChks.length == 0){
        alert('삭제할 상품을 선택하세요.');
        return;
    }

    if(confirm('선택한 상품을 삭제하시겠습니다?')){
        let cartCodes = [];
        checkedChks.forEach(function(chk, idx){
            cartCodes.push(chk.value);
        })  
        
        location.href = `/cart/deleteCartItem?cartCodeList=${cartCodes}`;
    }
}


// 수량 변경하기
function setItemCnt(cartCode, selectedTag, itemPrice){
    const cartCnt = selectedTag.closest('.row').querySelector('input[type="number"]').value;
    fetch('/cart/updateCartItemCntFetch', { 			//컨트롤러로 갈 경로
        method: 'POST', 		//GET or POST
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        body: new URLSearchParams({
           'cartCode' : cartCode,
           'cartCnt' : cartCnt
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
        alert('수량이 변경되었습니다.');
        location.reload();
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
}

//선택한 상품 구매
function buyItem(){
        const chks = document.querySelectorAll('.chk:checked');
        const cartCodeArr = [];
        for(const chk of chks){
            cartCodeArr.push(chk.value);
        }

        location.href = `/buy/buyItem?cartCodeList=${cartCodeArr}`;

}

//선택한 상품 구매 fetch로
function buyItem1(){
    const chks = document.querySelectorAll('.chk:checked');
    const detailList = [];
    const cartCodeList = [];
    let buyTotalPrice = 0;

    for(let i=0; i<chks.length; i++){
        const detailInfo = {
            'itemCode' : chks[i].dataset.itemCode,
            'buyCnt' : chks[i].closest('tr').querySelector('input[type="number"]').value,
            'buyPrice' : chks[i].dataset.totalPrice
        }
        buyTotalPrice += parseInt(chks[i].dataset.totalPrice);
        detailList.push(detailInfo);
        cartCodeList.push(chks[i].value)
    };


    const data = {
        'buyTotalPrice' : buyTotalPrice,
        'detailList' : detailList,
        'cartCodeList' : cartCodeList
    };

    console.log(data);

    fetch('/buy/insertBuyFetch', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/json; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: JSON.stringify(data)
    })
    .then((response) => {
         return response.text(); //컨트롤러에서 return하는 데이터가 없거나 int, String 일 때 사용
    	//return response.json(); //나머지 경우에 사용
    })
    //fetch 통신 후 실행 영역
    .then((data) => {//data -> controller에서 리턴되는 데이터!
        
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });

}