package com.green.Shop.item.vo;

import lombok.Data;

@Data
public class PageVO{
    private int nowPage;        //현재 페이지
    private int totalDataCnt;   //총 데이터 수
    private int displayDataCnt; //한 페이지에 보여지는 데이터 수
    private int beginPage;      //화면에 보이는 첫번째 페이지 번호
    private int endPage;        //화면에 보이는 마지막 페이지 번호
    private int totalPageCnt;   //전체 페이지 수
    private int displayPageCnt; //한 번에 보여지는 페이지 수
    private boolean prev;       //이전 버튼 유무
    private boolean next;       //다음 버튼 유무


    public PageVO(){
        nowPage = 1;
        displayPageCnt = 5;
        displayDataCnt = 12;
    }


    //모든 변수 값 세팅
    public void setPageInfo(){
        endPage = (int)Math.ceil(nowPage/(double)displayPageCnt) * displayPageCnt;
        beginPage = endPage - displayPageCnt + 1;
        totalPageCnt = (int)Math.ceil((totalDataCnt/(double)displayDataCnt));

        if(endPage<totalPageCnt){
            next = true;
        }else{
            next = false;
            endPage = totalPageCnt;
        }

        if (beginPage == 1){
            prev = false;
        }else{
            prev = true;
        }
    }
}