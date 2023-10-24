package com.nutrition.subReport;

import com.nutrition.subReport.model.Food;
import com.nutrition.subReport.model.Nutrition;
import com.nutrition.subReport.model.Vegetable;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class SubReportApplication {



	public static void main(String[] args) throws FileNotFoundException, JRException {
		SpringApplication.run(SubReportApplication.class, args);
		String path="C:/Users/uruj.fatima/Desktop/Projects/JasperSubReport/subReport/subReport/src/main/resources/static/nutrition.pdf";



		String filePath= "C:/Users/uruj.fatima/Desktop/Projects/JasperSubReport/subReport/subReport/src/main/resources/nutritionReport.jrxml";


		Nutrition protein=new Nutrition("Protein",62,134,"g");
		Nutrition carbohydrate=new Nutrition("Carbohydrate",52,84,"g");
		Nutrition fiber=new Nutrition("Fiber",20,38,"g");

		List<Nutrition> nutritionList=new ArrayList<>();
		nutritionList.add(protein);
		nutritionList.add(carbohydrate);
		nutritionList.add(fiber);

		JRBeanCollectionDataSource nutritionDataSource=new JRBeanCollectionDataSource(nutritionList);
		Map<String,Object> parameters=new HashMap<>();
		parameters.put("firstName","John");
		parameters.put("lastName","Smith");
		parameters.put("dob","07/09/2001");
		parameters.put("nutritionDataset",nutritionDataSource);
		parameters.put("vegetableReport",getVegetableReport());
		parameters.put("vegetableParameter",getVegetableParameter());
		parameters.put("foodReport",getFoodReport());
		parameters.put("foodParameter",getFoodParameter());


		JasperReport jasperReport= JasperCompileManager.compileReport(filePath);
		JasperPrint jasperPrint= JasperFillManager.fillReport(jasperReport,parameters,new JREmptyDataSource());
		JasperExportManager.exportReportToPdfFile(jasperPrint,path);
		System.out.println("Report Generated");
	}


	private static JRBeanCollectionDataSource getFoodDataSource() {
		List<Food> foodList = new ArrayList<>();

		Food banana=new Food("banana","breakfast",0,28,1);
		Food milk=new Food("milk","breakfast",8,12,8);
		Food chicken=new Food("chicken","lunch",2,0,26);

		foodList.add(banana);
		foodList.add(milk);
		foodList.add(chicken);

		JRBeanCollectionDataSource dataSource=new JRBeanCollectionDataSource(foodList);
		return dataSource;
	}
	private static Map getFoodParameter(){
		Map<String,Object> foodParameter=new HashMap<>();
		foodParameter.put("foodDataset",getFoodDataSource());
		return foodParameter;
	}
	private static JasperReport getFoodReport() throws FileNotFoundException {
		String file="C:/Users/uruj.fatima/Desktop/Projects/JasperSubReport/subReport/subReport/src/main/resources/foodNutrition.jrxml";
		JasperReport report=null;
		try{
			report=JasperCompileManager.compileReport(file);
		} catch (JRException e) {
			throw new RuntimeException(e);
		}
		return report;
	}
	private static JRBeanCollectionDataSource getVegetableDataSource() {
		List<Vegetable> vegetableList = new ArrayList<>();

		Vegetable potato=new Vegetable("potato","breakfast");
		Vegetable tomato=new Vegetable("tomato","breakfast");
		Vegetable chicken=new Vegetable("chicken","lunch");

		vegetableList.add(potato);
		vegetableList.add(tomato);
		vegetableList.add(chicken);

		JRBeanCollectionDataSource dataSource1=new JRBeanCollectionDataSource(vegetableList);
		return dataSource1;
	}
	private static Map getVegetableParameter(){
		Map<String,Object> vegetableParameter=new HashMap<>();
		vegetableParameter.put("vegetableDataset",getVegetableDataSource());
		return vegetableParameter;
	}
	private static JasperReport getVegetableReport() throws FileNotFoundException {
		String file="C:/Users/uruj.fatima/Desktop/Projects/JasperSubReport/subReport/subReport/src/main/resources/vegetableNutrition.jrxml";
		JasperReport report=null;
		try{
			report=JasperCompileManager.compileReport(file);
		} catch (JRException e) {
			throw new RuntimeException(e);
		}
		return report;
	}
}
