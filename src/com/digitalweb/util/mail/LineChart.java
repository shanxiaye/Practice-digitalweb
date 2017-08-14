package com.digitalweb.util.mail;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

	public class LineChart {  
		 public static void createLineChart(){  
		  DefaultCategoryDataset dataset = new DefaultCategoryDataset();  
		  dataset.addValue(100, "test1", "aaa");  
		  dataset.addValue(150, "test2", "bbb");  
		  dataset.addValue(300, "test3", "ccc");  
		  dataset.addValue(100, "test4", "ddd");  
		  // 三维折线图 createLineChart3D
		  JFreeChart chart = ChartFactory.createLineChart(  
		       "Line chart",                    // 标题
		       "name",                      // 横坐标
		       "value",                     // 纵坐标
		       dataset,                    // 数据
		       PlotOrientation.VERTICAL,   // 竖直图表
		       true,                       // 是否显示legend
		       false,                      // 是否显示tooltip
		       false                       // 是否使用url链接
		   );  
		  // 设置字体
//		  JfreeChinese.setChineseForXY(chart);  
		  FileOutputStream fos = null;  
		  try {  
		      fos = new FileOutputStream("src/poly.png");  
		      ChartUtilities.writeChartAsPNG(fos, chart, 400, 300);  
		  } catch (FileNotFoundException e) {  
		   e.printStackTrace();  
		  } catch (IOException e) {  
		   e.printStackTrace();  
		  } finally {  
		      try {  
		       if(fos != null){  
		        fos.close();  
		       }  
		   } catch (IOException e) {  
		    e.printStackTrace();  
		   }  
		  }  
		 }  
		   
		 public static void main(String[] args) {  
			 LineChart.createLineChart();  
		 }  
		}  

