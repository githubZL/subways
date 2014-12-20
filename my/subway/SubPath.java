package my.subway;

import java.util.ArrayList;

/**
 * @ClassName: SubPath
 * @Description: 地铁路径类
 * @author 张亮
 * 
 */

public class SubPath {
	//存储从起始点到目的点路径
	private ArrayList<SubLink> s_links = new ArrayList<SubLink>();
	//获得此次路径换乘次数
	public int Transfers(){
		if(s_links.size() < 2) 
			return 0;
		int count = 0;
		SubLine line = getS_links().get(0).getS_line();
		for(int i = 1; i < s_links.size(); i++){
			if(!getS_links().get(i).getS_line() .equals(line)){
				line = getS_links().get(i).getS_line();
				count++;
			}
			
		}
		return count;
	}
	
	//判断此路径中节点是否包含node
	public boolean ContainsNode(SubNode node){
		for(int i = 0;i < s_links.size(); i++){
			if(s_links.get(i).getS_from().equals(node) || s_links.get(i).getS_to().equals(node)){
				return true;
			}
		}
		return false;
	} 
	
	//追加link	
	public SubPath Append(SubLink link){
		SubPath newpPath = new SubPath();
		newpPath.s_links.addAll(s_links);
		newpPath.s_links.add(link);
		return newpPath;
	}
    //合并路径
	public SubPath Merge(SubPath path){
		SubPath newpaPath = new SubPath();
		newpaPath.s_links.addAll(s_links);
		newpaPath.s_links.addAll(path.getS_links());
		return newpaPath;
	}
	
	public ArrayList<SubLink> getS_links() {
		return s_links;
	}

	public void setS_links(ArrayList<SubLink> s_links) {
		this.s_links = s_links;
	}
	
	
}
