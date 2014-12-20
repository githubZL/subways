package my.subway;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @ClassName: SubGraph
 * @Description: 用邻接表构建地铁线路图方法，包括增加站点、站点之间路线、线路添加、寻找最短路径等实现方法
 * @author 张亮
 * 
 */

public class SubGraph {

	//用来存储整个地铁路图的路线
	private List<SubLine> lines;
	//用来存储整个地铁路图的站点
	private List<SubNode> nodes;
	//表示寻找最短路径的起始站点
	private SubNode startNode;
	//表示寻找最短路径的起始站点
	private SubNode endNode;
	//表示找到的最短路径
	private SubPath curPath;

	
	public SubGraph() {
		nodes = new ArrayList<SubNode>();
		lines = new ArrayList<SubLine>();
	}

	/**
	 * @Title: addLine
	 * @Description: 添加路线对象，例如L1，L2
	 * @param line_name  为线路名称
	 */
	public void addLine(String line_name) {
		SubLine line = new SubLine(line_name);
		//判断集合当中没有当前线路则添加到集合当中
		if (!lines.contains(line))
			lines.add(line);
	}

	/**
	 * @Title: addNode
	 * @Description: 添加站点对象，例如L1-1，L2-3，X3
	 * @param node_name  为站点名称
	 */
	
	public void addNode(String node_name) {
		SubNode node = new SubNode(node_name);
		//判断集合当中没有当前站点则添加到集合当中
		if (!nodes.contains(node))
			nodes.add(node);
	}

	/**
	 * @Title: addLink
	 * @Description: 添加站点之间路线，例如L1-1到L1-2
	 * @param fromName 为路线名称 ，toName 为路线终点名称 ，lineName 该路线在那条线路 
	 * 例如L1-1到L1-2，fromName为L1-1，toName为L1-2，lineName为L1
	 */
	
	
	public void addLink(String fromName, String toName, String lineName) {
		SubNode fromNode = null;
		SubNode toNode = null;
		SubLine line = null;
		//找到站点集合当中与参数名称相同的站点对象
		for (int i = 0; i < nodes.size(); i++) {
			if (nodes.get(i).getS_name().equals(fromName)) {
				fromNode = nodes.get(i);
			}
			if (nodes.get(i).getS_name().equals(toName)) {
				toNode = nodes.get(i);
			}
			if(fromNode != null && toNode != null){
				break;
			}
		}
		//找到与地铁线路名称相同的线路对象
		for (int j = 0; j < lines.size(); j++) {
			if (lines.get(j).getLineName().equals(lineName)) {
				line = lines.get(j);
			}
		}
		//将一条路线对象添加到站点邻接表
		fromNode.links.add(new SubLink(fromNode, toNode, line));
	}

	/**
	 * @Title: getTransfersubNode
	 * @Description: 找到两条线路可以换乘的站点，例如L1和L2可以换乘的站点是X2和X6
	 * @param line1 ，line2 为线路对象
	 */
	
	public List<SubNode> getTransfersubNode(SubLine line1, SubLine line2) {
		//用来存储可以换乘的站点
		List<SubNode> tem = new ArrayList<SubNode>();
		//循环遍历站点集合
		for (int i = 0; i < nodes.size(); i++) {
			//用来记录寻找目标线路的个数
			int count = 0;
			//存储每个站点的邻接站点路线
			List<SubLink> tempLinks = nodes.get(i).getLinks();
			//如果是换乘站点必定邻接站点路线大于2
			if (tempLinks.size() > 2) {
				for (int j = 0; j < tempLinks.size(); j++) {
					//找到相应线路则count加1，
					if (tempLinks.get(j).getS_line().equals(line1))
						count++;
					if (tempLinks.get(j).getS_line().equals(line2))
						count++;
					//如果是换乘站点则邻接站点的路线必定会有重复，如X2为换乘站点
					//X2到L1-4和X2到X3为一样的线路，所以count大于2时才是要找的换乘站点
					if (count > 2 && !tem.contains(nodes.get(i))) {
						tem.add(nodes.get(i));
					}
				}
			}
		}
		return tem;
	}

	/**
	 * @Title: FindPath 
	 * @Description: 寻找两个站点最短距离
	 * @param startNodeName 起始站点 ， endNodeName 目的站点
	 * @return 返回由总价、路径拼接起来的字符串，如寻找X3,L3-8，则返回字符串"=4.0:X3,X4,L3-8"
	 */
	
	public String FindPath(String startNodeName, String endNodeName) {
		SubNode startNode = null;
		SubNode endNode = null;	
		//找到名称相对应的站点对象
		for (int i = 0; i < nodes.size(); i++) {
			if (nodes.get(i).getS_name().equals(startNodeName)) {
				startNode = nodes.get(i);
			}
			if (nodes.get(i).getS_name().equals(endNodeName)) {
				endNode = nodes.get(i);
			}
			if (startNode != null && endNode != null)
				break;
		}
		if (startNode == null || endNode == null)
			return "站点输入有误";
		//调用FindPath方法寻找路径，并把返回值赋给路径对象全局变量curPath
		curPath = FindPath(startNode, endNode);
		//调用处理最短路径输出和总价printPath方法
		return printPath(curPath);

	}
	
	/**
	 * @Title: FindPath 
	 * @Description: 寻找两个站点最短距离
	 * @param start 起始站点对象 ， end 目的站点对象
	 * @return 返回最短路径
	 */
	
	public SubPath FindPath(SubNode start, SubNode end) {
		SubPath path = new SubPath();
		//起始点和重点相同则返回path
		if (start.equals(end))
			return path;
		//调用两个站点在一条线路的方法，如L-1和L1-13都在L1线，则调用以下方法并返回路径
		path = findDirectPath(start, end);
		if (path.getS_links().size() > 0) 
			return path;

		path = findOneTransferPath(start, end);
		if (path.getS_links().size() > 0) {
			return path;
		}
		List<SubPath> pathList = findShortestPaths(start, end, null);
		int minTransfer = Integer.MAX_VALUE;
		for (SubPath subPath : pathList) {
			int count = subPath.Transfers();
			if (count < minTransfer) {
				minTransfer = count;
				path = subPath;
			}

		}
		return path;
	}

	/**
	 * @Title: findDirectPath
	 * @Description: 两个站点同在一条线路，则直接调用此方法
	 * @param start 起始站点 ， end 目的站点
	 * @return 返回路径，将是不换乘的直接路线
	 */
	
	public SubPath findDirectPath(SubNode start, SubNode end) {
		SubPath path = new SubPath();
		//起始站点的邻接站点线路集合
		List<SubLink> startLinks = start.getLinks();
		//目的站点的邻接站点线路集合
		List<SubLink> endLinks = end.getLinks();
		//存储两个站点之间相同路线的集合，如X3，X5同时包括L1线和L5线
		List<SubLine> templines = new ArrayList<SubLine>();
		for (int i = 0; i < startLinks.size(); i++) {
			SubLine tempLine = startLinks.get(i).getS_line();
			for (int j = 0; j < endLinks.size(); j++) {
				if (tempLine.equals(endLinks.get(j).getS_line())
						&& !templines.contains(tempLine)) {
					templines.add(tempLine);
				}
			}
		}
		if (templines.size() == 0)
			return path;
		List<SubPath> pathList = new ArrayList<SubPath>();
		for (SubLine line : templines) {
			//将地铁线路集合中线路取出与起始站点和目的站点查找路径，存储到pathList集合中
			pathList.addAll(findShortestPaths(start, end, line));
		}
		//将pathList中路径进行遍历，找到最短路径并返回
		return getShortestPath(pathList);
	}

	/**
	 * @Title: findOneTransferPath
	 * @Description: 查找两个节点之间换乘一次走的路径
	 * @param start 起始站点 ， end 目的站点 
	 * @return 返回路径
	 */	
	
	public SubPath findOneTransferPath(SubNode start, SubNode end) {
		//存取起始站点的邻接表link
		List<SubLink> startLinks = start.getLinks();
		//存取目的站点的邻接表link
		List<SubLink> endLinks = end.getLinks();
		//存取从起始站点到目的站点所有的路径集合
		List<SubPath> pathList = new ArrayList<SubPath>();
		//用来存取起始节点所在的铁路线
		List<SubLine> startLines = new ArrayList<SubLine>();
		//用来存取目的节点所在的铁路线
		List<SubLine> endLines = new ArrayList<SubLine>();
		//用来存取起始站点到目的站点的换乘站点
		List<SubNode> transferNode = new ArrayList<SubNode>();
		//将起始link所在地铁路线存入startLines集合
		for (SubLink startlink : startLinks) {
			SubLine startline = startlink.getS_line();
			if (!startLines.contains(startline))
				startLines.add(startline);
		}
		//将目的link所在地铁路线存入endLines集合
		for (SubLink endLink : endLinks) {
			SubLine endlLine = endLink.getS_line();
			if (!endLines.contains(endlLine))
				endLines.add(endlLine);
		}
        
		for (SubLine startline : startLines) {
			for (SubLine endline : endLines) {
				//获得两条地铁线路所有的换乘站点
				transferNode.addAll(getTransfersubNode(startline, endline));
				//遍历换乘站点
				for (SubNode node : transferNode) {
					//得到从起始站点到换乘站点以起始站点的地铁线路的直达路线路径集合
					List<SubPath> startPathList = findShortestPaths(start,
							node, startline);
					//得到从换乘站点到目的站点以目的站点的地铁线路的直达路线路径集合
					List<SubPath> endPathList = findShortestPaths(node, end,
							endline);
					for (SubPath startpath : startPathList) {
						for (SubPath endPath : endPathList) {
							//合并从起点站点到换乘站点，再从换乘站点到目的节点的路径
							SubPath path = startpath.Merge(endPath);
							pathList.add(path);
						}
					}
				}
			}
		}
		//调用方法获取集合中最短路径
		return getShortestPath(pathList);
	}

	/**
	 * @Title: findShortestPaths
	 * @Description: 查找两个节点之间最短路径
	 * @param start 起始站点 ， end 目的站点 ， line 目标线路，null为不限制
	 * @return 返回路径集合
	 */	
	
	public List<SubPath> findShortestPaths(SubNode start, SubNode end,
			SubLine line) {
		List<SubPath> pathList = new ArrayList<SubPath>();
		if (start.equals(end))
			return pathList;
		//路径队列，用于遍历路径
		List<SubPath> pathQueue = new LinkedList<SubPath>();
		pathQueue.add(new SubPath());
		//遍历队列
		while (pathQueue.size() > 0) {
			//取出队头
			SubPath path = pathQueue.remove(0);
			//超过最短路径直接返回
			if (pathList.size() > 0
					&& path.getS_links().size() > pathList.get(0).getS_links()
							.size())
				continue;
			//在寻找最短路径的搜索的前一个节点，可以理解已经被访问过的节点
			SubNode prevNode = path.getS_links().size() > 0 ? path.getS_links()
					.get(path.getS_links().size() - 1).getS_from() : null;
			//在寻找最短路径的搜索的后一个节点
			SubNode lastNode = path.getS_links().size() > 0 ? path.getS_links()
					.get(path.getS_links().size() - 1).getS_to() : start;
			//遍历lastNode的邻接节点路线link
			for (SubLink link : lastNode.getLinks()) { 
				//判断当前路线的目的节点不能与 prevNode相同，如搜索L2-8到L2-3最短路径，
				//L2-8到L2-7为一个路线，然后再找L2-7的邻接节点，此时邻接节点路线有L2-7->L2-8和L2-7->L7-6，
				//显然前者路线L2-8访问过的，所以会忽略掉，直接读取后者路线
				if (!link.getS_to().equals(prevNode)
						&& (line == null || link.getS_line().equals(line))) {
					if (link.getS_to().equals(end)) {
						SubPath newPath = path.Append(link);
						// 找到一条路径并存入pathList集合中，如果路径长度相同也同时存入
						if (pathList.size() == 0
								|| newPath.getS_links().size() == pathList
										.get(0).getS_links().size()) {
							pathList.add(newPath);
						// 找到一条更短的路径
						} else if (newPath.getS_links().size() < pathList
								.get(0).getS_links().size()) {
							pathList.clear();
							pathList.add(newPath);
						} else
							break;
					} else if (!path.ContainsNode(link.getS_to())) {
						//将新的路径结存入队列，如第一次循环是L2-8->L2-7存入link再存入队列，当再对此
						//路径出对时，将会变成L2-8->L2-7，L2-7->L2-6存入link再存如队列，直到找到end
						pathQueue.add(path.Append(link));
					}
				}
			}
		}
		return pathList;
	}

	/**
	 * @Title: getShortestPath
	 * @Description: 得到pathList中最短路径
	 * @param pathList 路径集合
	 * @return 返回最短路径
	 */	

	
	public SubPath getShortestPath(List<SubPath> pathList) {
		//用于获取最短路径
		SubPath minPath = new SubPath();
		//路径最短长度，默认为int最大值
		int minLength = Integer.MAX_VALUE;
		for (SubPath path : pathList) {
			if (path.getS_links().size() < minLength) {
				minLength = path.getS_links().size();
				minPath = path;
			}
		}
		return minPath;
	}

	/**
	 * @Title: printPath
	 * @Description: 得到路径序列和乘坐总价的字符串
	 * @param path 最短路径
	 * @return 返回路径序列和乘坐总价的字符串
	 */	

	
	public String printPath(SubPath path) {
		//得到最短路径乘坐总价
		String price = Double.toString(caculatePrice(path));
		StringBuffer lineString = new StringBuffer();
		//先将"=总价:"拼接
		lineString.append("="+price+":");
		//一次对路径站点进行拼接
		for (int i = 0; i < path.getS_links().size(); i++) {
			SubLink link = path.getS_links().get(i);
			if(i == 0)
				lineString.append(link.getS_from().getS_name()+","+link.getS_to().getS_name());
			else{
				lineString.append("," + link.getS_to().getS_name());
			}
		}
		return lineString.toString();
	}

	/**
	 * @Title: caculatePrice
	 * @Description: 得到路径乘坐总价
	 * @param path 最短路径
	 * @return 返回总价
	 */	
	
	public double caculatePrice(SubPath path) {
		//总价默认为2元
		double totalPrice = 2;
		//判断走过的站点数量
		int count = 0;
		for (int i = 0; i < path.getS_links().size(); i++) {
			SubLine line = path.getS_links().get(i).getS_line();
			//判断走的路径是否已经大于0并且是否进行了换乘
			if (i > 0
					&& !(path.getS_links().get(i - 1).getS_line().equals(line))) {
				//换乘了，则加两元重新计价
				totalPrice += 2;
				//设为走过了一站地
				count = 0;
			}
			if (count > 4)
				//超过了5站地则每站加0.5
				totalPrice += 0.5;
			count++;
		}
		return totalPrice;
	}

	protected List<SubNode> getNodes() {
		return nodes;
	}

	protected void setNodes(List<SubNode> nodes) {
		this.nodes = nodes;
	}

	protected List<SubLine> getLines() {
		return lines;
	}

	protected void setLines(List<SubLine> lines) {
		this.lines = lines;
	}

	protected SubNode getStartNode() {
		return startNode;
	}

	protected void setStartNode(SubNode startNode) {
		this.startNode = startNode;
	}

	protected SubNode getEndNode() {
		return endNode;
	}

	protected void setEndNode(SubNode endNode) {
		this.endNode = endNode;
	}

	protected SubPath getCurPath() {
		return curPath;
	}

	protected void setCurPath(SubPath curPath) {
		this.curPath = curPath;
	}

}
