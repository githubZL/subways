package my.subway;

import java.util.ArrayList;

/**
 * @ClassName: SubNode
 * @Description: 地铁站点类
 * @author 张亮
 * 
 */

public class SubNode {
	//地铁站点名称 如 L1-1
	public String S_name;
	//当前站点的邻接表路线集合 如L1-1的是L1-1->L1-2和L1-1->L1-14
	public ArrayList<SubLink> links = new ArrayList<SubLink>();
	public SubNode(String name){
		S_name = name;
	}
	
	protected String getS_name() {
		return S_name;
	}

	protected void setS_name(String s_name) {
		S_name = s_name;
	}

	protected ArrayList<SubLink> getLinks() {
		return links;
	}
	
	
	
}
