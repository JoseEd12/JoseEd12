package com.depaseo.serviceImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

@Service
public class ReportEntradas {

	public String exportReport()  {
		try {
			Map<String,Object> parameters = new HashMap<>();
			parameters.put("nombre", "Cristian");
			parameters.put("apellido", "Garcia Jimenez");
			parameters.put("fecha", "09-04-2021");
			parameters.put("id_reserva", "46546");
			File file = ResourceUtils.getFile("classpath:ticket.jrxml");
			JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		//	JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource((Collection<?>) parameters);
			//JasperReport jasperReport = (JasperReport) JRLoader.loadObject(getClass().getResource("ticket.jasper"));
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,new JREmptyDataSource());
			JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\Users\\krist\\Desktop\\entradaAutobus.pdf");
				}catch (Exception fe) {
			// TODO Auto-generated catch block
			fe.printStackTrace();
		}
		
		
		return "C:\\Users\\krist\\Desktop\\entradaAutobus.pdf";
	}
}
