package com.depaseo.call;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.depaseo.call.model.cancel.RequestCancelEntity;
import com.depaseo.call.model.cancel.ResponseCancelEntity;
import com.depaseo.call.model.reserva.RequestReservaEntity;
import com.depaseo.call.model.reserva.ResponseReservaEntity;
import com.depaseo.call.model.transaction.RequestTransactionEntity;
import com.depaseo.call.model.transaction.ResponseTransactionEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ExperTicket {


	public static final String PARTNER_ID = "PartnerId=19cmekztdppqo";
	public static final String CATALOGO = "catalog";
	public static final String ENTRADA_DIURNA_ADULTO = "3oq3o65dfxq76";
	public static final String ENTRADA_DIURNA_NIÑO = "t6qko81kkt65g";
	public static final String ENTRADA_NOCTURNA_ADULTO_PREFERENTE = "aizs6aw4wj1iq";
	public static final String ENTRADA_NOCTURNA_NIÑO_PREFERENTE = "5oty4zsech6sw";
	public static final String ENTRADA_NOCTURNA_ADULTO_CLASICA = "r96bq5scoq3us";
	public static final String ENTRADA_NOCTURNA_NIÑO_CLASICA = "cbgeso9u9mcj4";
	
	
    public static ResponseReservaEntity reserva (RequestReservaEntity requestReserva,String url) {
    	ObjectMapper objectMapper = new ObjectMapper(); 
    	ResponseReservaEntity respuesta = new ResponseReservaEntity();
    	try {
    	OkHttpClient client = new OkHttpClient().newBuilder()
    				.connectTimeout(30, TimeUnit.SECONDS)
    				.readTimeout(30, TimeUnit.SECONDS)
    				.writeTimeout(30, TimeUnit.SECONDS)
    				.build();
    			MediaType mediaType = MediaType.parse("application/json");
    			RequestBody body;
					body = RequestBody.create(mediaType,new ObjectMapper().writeValueAsString(requestReserva));
					Request request = new Request.Builder()
							.url(url + "reservation")
							.method("POST", body)
							.addHeader("Content-Type", "application/json")
							.build();
					Response response = client.newCall(request).execute();
				
					 respuesta = objectMapper.readValue(response.body().string(), ResponseReservaEntity.class);
					return respuesta;
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					respuesta.setErrorMessage(e.getOriginalMessage());
					return respuesta;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					respuesta.setErrorMessage(e.getMessage());
					return respuesta;
				}
    }
    
    public static ResponseTransactionEntity transaccion (RequestTransactionEntity requestReserva,String url) {
    	ObjectMapper objectMapper = new ObjectMapper(); 
    	ResponseTransactionEntity respuesta = new ResponseTransactionEntity();
    	try {
    	OkHttpClient client = new OkHttpClient().newBuilder()
    				.connectTimeout(30, TimeUnit.SECONDS)
    				.readTimeout(30, TimeUnit.SECONDS)
    				.writeTimeout(30, TimeUnit.SECONDS)
    				.build();
    			MediaType mediaType = MediaType.parse("application/json");
    			RequestBody body;
					body = RequestBody.create(mediaType,new ObjectMapper().writeValueAsString(requestReserva));
					Request request = new Request.Builder()
							.url(url + "transaction")
							.method("POST", body)
							.addHeader("Content-Type", "application/json")
							.build();
					Response response = client.newCall(request).execute();
					 respuesta = objectMapper.readValue(response.body().string(), ResponseTransactionEntity.class);
					return respuesta;
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					respuesta.setErrorMessage(e.getOriginalMessage());
					return respuesta;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					respuesta.setErrorMessage(e.getMessage());
					return respuesta;
				}
    }
    
    
    public static String catalogo(String url) {
    	String respuesta = "";
    	try {
    	OkHttpClient client = new OkHttpClient().newBuilder()
    				.connectTimeout(30, TimeUnit.SECONDS)
    				.readTimeout(30, TimeUnit.SECONDS)
    				.writeTimeout(30, TimeUnit.SECONDS)
    				.build();
					Request request = new Request.Builder()
							.url(url + "catalog?PartnerId=19cmekztdppqo")
							.get()
							.addHeader("Content-Type", "application/json")
							.build();
					Response response = client.newCall(request).execute();
					//log.info("ExperTicket | transaccion response -> " + response.body().string());
					 respuesta = response.body().string();
					return respuesta;
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
					return respuesta;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
					return respuesta;
				}
    }
    

    public static String available(String url,String path2,String fecha) {
    	String path="availablecapacity?PartnerId=19cmekztdppqo" + path2;
    	String respuesta = "";
    	try {
    	OkHttpClient client = new OkHttpClient().newBuilder()
    				.connectTimeout(30, TimeUnit.SECONDS)
    				.readTimeout(30, TimeUnit.SECONDS)
    				.writeTimeout(30, TimeUnit.SECONDS)
    				.build();
					Request request = new Request.Builder()
							.url(url + path)
							.get()
							.addHeader("Content-Type", "application/json")
							.build();
					Response response = client.newCall(request).execute();

					 respuesta = response.body().string();
					return respuesta;
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
					return respuesta;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
					return respuesta;
				}
    }
    
    public static String session(String fecha,String url) {
    	String path="sessions?PartnerId=19cmekztdppqo&ToDate=" + fecha;
    	String respuesta = "";
    	try {
    	OkHttpClient client = new OkHttpClient().newBuilder()
    				.connectTimeout(30, TimeUnit.SECONDS)
    				.readTimeout(30, TimeUnit.SECONDS)
    				.writeTimeout(30, TimeUnit.SECONDS)
    				.build();
					Request request = new Request.Builder()
							.url(url + path)
							.get()
							.addHeader("Content-Type", "application/json")
							.build();
					Response response = client.newCall(request).execute();

					 respuesta = response.body().string();
					return respuesta;
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
					return respuesta;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
					return respuesta;
				}
    }
    
  
    
    public static ResponseCancelEntity cancel (RequestCancelEntity requestcancel,String url) {
    	ObjectMapper objectMapper = new ObjectMapper(); 
    	ResponseCancelEntity respuesta = new ResponseCancelEntity();
    	try {
    	OkHttpClient client = new OkHttpClient().newBuilder()
    				.connectTimeout(30, TimeUnit.SECONDS)
    				.readTimeout(30, TimeUnit.SECONDS)
    				.writeTimeout(30, TimeUnit.SECONDS)
    				.build();
    			MediaType mediaType = MediaType.parse("application/json");
    			RequestBody body;
					body = RequestBody.create(mediaType,new ObjectMapper().writeValueAsString(requestcancel));
					Request request = new Request.Builder()
							.url(url + "transaction")
							.method("DELETE", body)
							.addHeader("Content-Type", "application/json")
							.build();
					Response response = client.newCall(request).execute();
					//log.info("ExperTicket | cancel response-> " + response.body().string());
					 respuesta = objectMapper.readValue(response.body().string(), ResponseCancelEntity.class);
					return respuesta;
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					respuesta.setErrorMessage(e.getOriginalMessage());
					return respuesta;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					respuesta.setErrorMessage(e.getMessage());
					return respuesta;
				}
    }
}
