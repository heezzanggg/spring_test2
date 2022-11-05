package com.green.pds.service.impl;

import com.green.pds.vo.PdsPagingVo;
import com.green.pds.vo.PdsVo;

public class BoardPaging {

	// Fields
	private  int      totalCount;       // 전체 Row 수
	private  int      nowPage;          // 현재 페이지 번호
	private  int      pageCount;        // 한 페이지에 출력될 row(line) 수 
	private  int      pageTotalCount;   // 한화면에 보여줄 총 페이지 번호 숫
	private  int      pageGrpNum;  
	    // 한화면에 보여질 페이지 그룹의 페이지 숫자
		
	private  int      prevNowPage;      // 이전페이지 번호
	private  int      nextNowPage;      // 다음페이지 번호
	
	private  int      startPageNum;    // 페이지 시작번호
	private  int      endPageNum;      // 페이지 끝번호
	
	private  int      totalRecordPageCount;  // totalCount / pageCount;
	  // 총 row 수 ( 전채자료수 )로 표현가능한 총 페이지 수 
	
	private  boolean  isShowPagePrev = true ;   
	private  boolean  isShowPageNext = true ;
	
	// 기본 생성자
	public BoardPaging() {
	}
	
	public BoardPaging(int nowpage, int pagecount, int totalcount,
			int pagetotalcount, int pagegrpnum) {
		this.nowPage        = nowpage;
		this.pageCount      = pagecount;
		this.totalCount     = totalcount;
		this.pageTotalCount = pagetotalcount;
		this.pageGrpNum     = pagegrpnum;		
	}

	// Getter / Setter
	
	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getNowPage() {
		return nowPage;
	}

	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}

	public int getPrevNowPage() {
		return prevNowPage;
	}

	public void setPrevNowPage(int prevNowPage) {
		this.prevNowPage = prevNowPage;
	}

	public int getNextNowPage() {
		return nextNowPage;
	}

	public void setNextNowPage(int nextNowPage) {
		this.nextNowPage = nextNowPage;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getPageTotalCount() {
		return pageTotalCount;
	}

	public void setPageTotalCount(int pageTotalCount) {
		this.pageTotalCount = pageTotalCount;
	}

	public int getPageGrpNum() {
		return pageGrpNum;
	}

	public void setPageGrpNum(int pageGrpNum) {
		this.pageGrpNum = pageGrpNum;
	}

	public int getStartPageNum() {
		return startPageNum;
	}

	public void setStartPageNum(int startPageNum) {
		this.startPageNum = startPageNum;
	}

	public int getEndPageNum() {
		return endPageNum;
	}

	public void setEndPageNum(int endPageNum) {
		this.endPageNum = endPageNum;
	}

	public int getTotalRecordPageCount() {
		return totalRecordPageCount;
	}

	public void setTotalRecordPageCount(int totalRecordPageCount) {
		this.totalRecordPageCount = totalRecordPageCount;
	}

	public boolean isShowPagePrev() {
		return isShowPagePrev;
	}

	public void setShowPagePrev(boolean isShowPagePrev) {
		this.isShowPagePrev = isShowPagePrev;
	}

	public boolean isShowPageNext() {
		return isShowPageNext;
	}

	public void setShowPageNext(boolean isShowPageNext) {
		this.isShowPageNext = isShowPageNext;
	}

	// toString	

	@Override
	public String toString() {
		return "BoardPaging [totalCount=" + totalCount + ", nowPage=" + nowPage + ", prevNowPage=" + prevNowPage
				+ ", nextNowPage=" + nextNowPage + ", pageCount=" + pageCount + ", pageTotalCount=" + pageTotalCount
				+ ", pageGrpNum=" + pageGrpNum + ", startPageNum=" + startPageNum + ", endPageNum=" + endPageNum
				+ ", totalRecordPageCount=" + totalRecordPageCount + ", isShowPagePrev=" + isShowPagePrev
				+ ", isShowPageNext=" + isShowPageNext + "]";
	}

    //------------------------------------------
	
	
	// Method
	public PdsPagingVo getPdsPagingInfo() {
		PdsPagingVo   vo = new PdsPagingVo();
		
		// 76.0 /10.0 =? Math.ceil(7.6)
		this.totalRecordPageCount = 
			(int) Math.ceil ((double) totalCount / (double) pageCount) ;	
			
		//   startPageNum                       endPageNum
		//       1      2   3  4  5  6  7  8  9   10         ▶
		//  ◀   11     12  13 14 15 16 17 18 19   20         ▶
		//  ◀   21     22  23 24 25 26            30
		// 페이지 시작 번호
		this.startPageNum = 
			(pageGrpNum-1) * pageTotalCount + 1;	
					    
		// 페이지 끝 번호
		this.endPageNum   = 
			( totalRecordPageCount < (pageGrpNum * pageTotalCount) ) ?   		
			 totalRecordPageCount  :  (pageGrpNum * pageTotalCount);	
		
		// ◀ [이전 10 개] [다음 10개] ▶ 출력 여부
		if( startPageNum == 1 ) 
			this.isShowPagePrev = false;
		if( endPageNum == totalRecordPageCount ) 
			this.isShowPageNext = false;
		
		// ◀  [이전 10 개] 클릭시 이동할 페이지 번호 계산
		this.prevNowPage = startPageNum - 1;
		// ▶  [다음 10 개] 클릭시 이동할 페이지 번호 계산
		this.nextNowPage = endPageNum   + 1;
		// 게산끝
		
		// 계산된 값을 vo 에 저장
		vo.setNowpage(this.nowPage);
		vo.setPrevnowpage(this.prevNowPage);
		vo.setNextnowpage(this.nextNowPage);
		
		vo.setTotalcount(this.totalCount);
		vo.setTotalpagecount(this.pageTotalCount);
		
		vo.setPagestartnum(this.startPageNum);
		vo.setPageendnum(this.endPageNum);
		
		vo.setPagecount(this.pageCount);
		vo.setPagegrpnum(this.pageGrpNum);
		
		vo.setIsshowpagenext(this.isShowPageNext);
		vo.setIsshowpageprev(this.isShowPagePrev);
		
	//	vo.setTotalRecordPageCount(this.totalRecordPageCount);
		
		return  vo;
	}

}
























