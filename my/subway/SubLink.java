package my.subway;

/**
 * @ClassName: SubLink
 * @Description: 两个站点路线类，如L1-1->L1-2类
 * @author 张亮
 * 
 */

public class SubLink {
    //两个站点路线中的起始
	public SubNode S_from;
	//两个站点路线中的目的
	public SubNode S_to;
	//所属的地铁路线
	public SubLine S_line;
	
	public SubLink(SubNode from , SubNode to ,SubLine line){
		this.S_from = from;
		this.S_to = to;
		this.S_line = line;
	}
	
	public String toString(){
		return "";
	}

	protected SubNode getS_from() {
		return S_from;
	}

	protected void setS_from(SubNode s_from) {
		S_from = s_from;
	}

	protected SubNode getS_to() {
		return S_to;
	}

	protected void setS_to(SubNode s_to) {
		S_to = s_to;
	}

	protected SubLine getS_line() {
		return S_line;
	}

	protected void setS_line(SubLine s_line) {
		S_line = s_line;
	}
	
	
}
