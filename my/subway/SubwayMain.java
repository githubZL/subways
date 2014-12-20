package my.subway;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

/**
 * @ClassName: Subway
 * @Description: 主类，用于构建地铁线路邻接表，文件读写，调用最短路径方法
 * @author 张亮
 * 
 */

public class SubwayMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//构建地铁路线
		SubGraph graph = creatGraph();
		//读取in.txt计算出价格和路径并写入到out.txt
		readAndWriteTxtFile("src/in.txt","src/out.txt",graph);
	}

	/**
	 * @Title: readAndWriteTxtFile
	 * @Description: 读取in.txt计算出价格和路径并写入到out.txt
	 * @param infilePath 读取文件路径 ， outfilePath 写入文件的路径， graph 生成的地铁路线对象
	 */
	
	
	public static void readAndWriteTxtFile(String infilePath,String outfilePath, SubGraph graph){
        try {
                String encoding="GBK";
                FileOutputStream outFile=null;
                outFile=new FileOutputStream(outfilePath);
                File file=new File(infilePath);
                if(file.isFile() && file.exists()){ 
                	//判断文件是否存在
                    InputStreamReader read = new InputStreamReader(
                    new FileInputStream(file),encoding);
                    //考虑到编码格式
                    BufferedReader bufferedReader = new BufferedReader(read);
                    String lineTxt = null;
                    while((lineTxt = bufferedReader.readLine()) != null){
                    	 //以","分割将数据存入数组
                         String[] arr = lineTxt.split(",");   
                         //将总价和路径写入out.txt文件
                         outFile.write((lineTxt + graph.FindPath(arr[0], arr[1])).getBytes());
                         //回车
                         outFile.write("\r\n".getBytes());
                    }
                    read.close();
        }else{
            System.out.println("找不到指定的文件");
        }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
     
    }

	/**
	 * @Title: creatGraph
	 * @Description: 创建地铁线路图
	 * @param
	 * @return SubGraph
	 */
	
	public static SubGraph creatGraph(){
		SubGraph graph = new SubGraph();
		
		addStation(8,"X",graph);
		addStation(14, "L1-", graph);
		addStation(16, "L2-", graph);
		addStation(13, "L3-", graph);
		addStation(16, "L4-", graph);
		addStation(10, "L5-", graph);	
		
		graph.addLine("L1");
		graph.addLine("L2");
		graph.addLine("L3");
		graph.addLine("L4");
		graph.addLine("L5");
		
		graph.addLink("L1-1", "L1-2", "L1");
		graph.addLink("L1-1", "L1-14", "L1");
		graph.addLink("L1-2", "L1-1", "L1");
		graph.addLink("L1-2", "X1", "L1");
		graph.addLink("X1", "L1-3", "L1");
		graph.addLink("X1", "L1-2", "L1");
		graph.addLink("L1-3", "X1", "L1");
		graph.addLink("L1-3", "L1-4", "L1");
		graph.addLink("L1-4", "X2", "L1");
		graph.addLink("L1-4", "L1-3", "L1");	
		graph.addLink("X2", "L1-4", "L1");
		graph.addLink("X2", "X3", "L1");
		graph.addLink("X3", "X2", "L1");
		graph.addLink("X3", "L1-5", "L1");
		graph.addLink("L1-5", "X3", "L1");
		graph.addLink("L1-5", "L1-6", "L1");
		graph.addLink("L1-6", "L1-5", "L1");
		graph.addLink("L1-6", "L1-7", "L1");
		graph.addLink("L1-7", "L1-6", "L1");
		graph.addLink("L1-7", "X8", "L1");
		graph.addLink("X8", "L1-7", "L1");
		graph.addLink("X8", "L1-8", "L1");
		graph.addLink("L1-8", "L1-9", "L1");
		graph.addLink("L1-8", "X8", "L1");
		graph.addLink("L1-9", "L1-10", "L1");
		graph.addLink("L1-9", "L1-8", "L1");
		graph.addLink("L1-10", "X7", "L1");
		graph.addLink("L1-10", "L1-9", "L1");
		graph.addLink("X7", "L1-10", "L1");
		graph.addLink("X7", "L1-11", "L1");
		graph.addLink("L1-11", "X7", "L1");
		graph.addLink("L1-11", "X5", "L1");
		graph.addLink("X5", "L1-11", "L1");
		graph.addLink("X5", "L1-12", "L1");
		graph.addLink("L1-12", "X5", "L1");
		graph.addLink("L1-12", "L1-13", "L1");
		graph.addLink("L1-13", "L1-12", "L1");
		graph.addLink("L1-13", "X6", "L1");
		graph.addLink("X6", "L1-13", "L1");
		graph.addLink("X6", "L1-14", "L1");
		graph.addLink("L1-14", "X6", "L1");
		graph.addLink("L1-14", "L1-1", "L1");
		
		graph.addLink("L2-1", "L2-2", "L2");
		graph.addLink("L2-2", "L2-1", "L2");
		graph.addLink("L2-2", "L2-3", "L2");
		graph.addLink("L2-3", "L2-2", "L2");
		graph.addLink("L2-3", "L2-4", "L2");
		graph.addLink("L2-4", "L2-3", "L2");
		graph.addLink("L2-4", "L2-5", "L2");
		graph.addLink("L2-5", "L2-4", "L2");
		graph.addLink("L2-5", "L2-6", "L2");
		graph.addLink("L2-6", "L2-5", "L2");
		graph.addLink("L2-6", "L2-7", "L2");
		graph.addLink("L2-7", "L2-6", "L2");
		graph.addLink("L2-7", "L2-8", "L2");
		graph.addLink("L2-8", "L2-7", "L2");
		graph.addLink("L2-8", "X2", "L2");	
		graph.addLink("X2", "L2-8", "L2");
		graph.addLink("X2", "X4", "L2");
		graph.addLink("X4", "X2", "L2");
		graph.addLink("X4", "L2-9", "L2");
		graph.addLink("L2-9", "X4", "L2");
		graph.addLink("L2-9", "L2-10", "L2");
		graph.addLink("L2-10", "L2-9", "L2");
		graph.addLink("L2-10", "L2-11", "L2");
		graph.addLink("L2-11", "L2-10", "L2");
		graph.addLink("L2-11", "L2-12", "L2");
		graph.addLink("L2-12", "L2-11", "L2");
		graph.addLink("L2-12", "X6", "L2");
		graph.addLink("X6", "L2-12", "L2");
		graph.addLink("X6", "L2-13", "L2");
		graph.addLink("L2-13", "X6", "L2");
		graph.addLink("L2-13", "L2-14", "L2");
		graph.addLink("L2-14", "L2-13", "L2");
		graph.addLink("L2-14", "L2-15", "L2");
		graph.addLink("L2-15", "L2-14", "L2");
		graph.addLink("L2-15", "L2-16", "L2");
		
		graph.addLink("L3-1", "L3-2", "L3");
		graph.addLink("L3-2", "L3-1", "L3");
		graph.addLink("L3-2", "L3-3", "L3");
		graph.addLink("L3-3", "L3-2", "L3");
		graph.addLink("L3-3", "X1", "L3");
		graph.addLink("X1", "L3-3", "L3");
		graph.addLink("X1", "L3-4", "L3");
		graph.addLink("L3-4", "X1", "L3");
		graph.addLink("L3-4", "L3-5", "L3");
		graph.addLink("L3-5", "L3-4", "L3");
		graph.addLink("L3-5", "L3-6", "L3");
		graph.addLink("L3-6", "L3-5", "L3");
		graph.addLink("L3-6", "L3-7", "L3");
		graph.addLink("L3-7", "L3-6", "L3");
		graph.addLink("L3-7", "X4", "L3");
		graph.addLink("X4", "L3-7", "L3");
		graph.addLink("X4", "L3-8", "L3");
		graph.addLink("L3-8", "L3-7", "L3");
		graph.addLink("L3-8", "L3-9", "L3");
		graph.addLink("L3-9", "L3-8", "L3");
		graph.addLink("L3-9", "X8", "L3");
		graph.addLink("X8", "L3-9", "L3");
		graph.addLink("X8", "L3-10", "L3");
		graph.addLink("L3-10", "X8", "L3");
		graph.addLink("L3-10", "L3-11", "L3");
		graph.addLink("L3-11", "L3-10", "L3");
		graph.addLink("L3-11", "L3-12", "L3");
		graph.addLink("L3-12", "L3-11", "L3");
		graph.addLink("L3-12", "L3-13", "L3");
		graph.addLink("L3-13", "L3-12", "L3");
		
		graph.addLink("L4-1", "L4-2", "L4");
		graph.addLink("L4-2", "L4-1", "L4");
		graph.addLink("L4-2", "L4-3", "L4");
		graph.addLink("L4-3", "L4-2", "L4");
		graph.addLink("L4-3", "L4-4", "L4");
		graph.addLink("L4-4", "L4-3", "L4");
		graph.addLink("L4-4", "L4-5", "L4");
		graph.addLink("L4-5", "L4-4", "L4");
		graph.addLink("L4-5", "X7", "L4");
		graph.addLink("X7", "L4-6", "L4");
		graph.addLink("X7", "L4-5", "L4");
		graph.addLink("L4-6", "X7", "L4");
		graph.addLink("L4-6", "L4-7", "L4");
		graph.addLink("L4-7", "L4-6", "L4");
		graph.addLink("L4-7", "L4-8", "L4");
		graph.addLink("L4-8", "L4-7", "L4");
		graph.addLink("L4-8", "X8", "L4");
		graph.addLink("X8", "L4-8", "L4");
		graph.addLink("X8", "L4-9", "L4");
		graph.addLink("L4-9", "X8", "L4");
		graph.addLink("L4-9", "L4-10", "L4");
		graph.addLink("L4-10", "L4-9", "L4");
		graph.addLink("L4-10", "L4-11", "L4");
		graph.addLink("L4-11", "L4-10", "L4");
		graph.addLink("L4-11", "L4-12", "L4");
		graph.addLink("L4-12", "L4-11", "L4");
		graph.addLink("L4-12", "L4-13", "L4");
		graph.addLink("L4-13", "L4-12", "L4");
		graph.addLink("L4-13", "L4-14", "L4");
		graph.addLink("L4-14", "L4-13", "L4");
		graph.addLink("L4-14", "L4-15", "L4");
		graph.addLink("L4-15", "L4-14", "L4");
		graph.addLink("L4-15", "L4-16", "L4");
		graph.addLink("L4-16", "L4-15", "L4");
		
		graph.addLink("L5-1", "L5-2", "L5");
		graph.addLink("L5-2", "L5-1", "L5");
		graph.addLink("L5-2", "L5-3", "L5");
		graph.addLink("L5-3", "L5-2", "L5");
		graph.addLink("L5-3", "L5-4", "L5");
		graph.addLink("L5-4", "L5-3", "L5");
		graph.addLink("L5-4", "X5", "L5");
		graph.addLink("X5", "L5-5", "L5");
		graph.addLink("X5", "L5-4", "L5");
		graph.addLink("L5-5", "X5", "L5");
		graph.addLink("L5-5", "L5-6", "L5");
		graph.addLink("L5-6", "L5-5", "L5");
		graph.addLink("L5-6", "X4", "L5");
		graph.addLink("X4", "L5-6", "L5");
		graph.addLink("X4", "X3", "L5");
		graph.addLink("X3", "L5-7", "L5");
		graph.addLink("X3", "X4", "L5");
		graph.addLink("L5-7", "X3", "L5");
		graph.addLink("L5-7", "L5-8", "L5");
		graph.addLink("L5-8", "L5-7", "L5");
		graph.addLink("L5-8", "L5-9", "L5");
		graph.addLink("L5-9", "L5-8", "L5");
		graph.addLink("L5-9", "L5-10", "L5");
		graph.addLink("L5-10", "L5-9", "L5");
		//graph.getTransfersubNode("L4", "L5");
		return graph;
	}
	
	public static void addStation(int j, String temp, SubGraph g){
		for (int i = 1; i <=j ; i++) {
			String tempStr = temp + Integer.toString(i);
			g.addNode(tempStr);
		}
	}
}
