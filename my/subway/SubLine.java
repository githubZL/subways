package my.subway;

/**
 * @ClassName: SubLine
 * @Description: 地铁线路类，如L1线类
 * @author 张亮
 * 
 */

public class SubLine {
	//地铁线路名称，如L1
	private String lineName;
	
	public SubLine(String line_name){
		this.lineName = line_name;
	}

	protected String getLineName() {
		return lineName;
	}

	protected void setLineName(String lineName) {
		this.lineName = lineName;
	}

	

	
}
