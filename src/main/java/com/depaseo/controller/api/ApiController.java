package com.depaseo.controller.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.depaseo.model.Productos;
import com.depaseo.model.Productosbus;
import com.depaseo.model.RequestReservaTicket;
import com.depaseo.model.RequestTransactionTicket;
import com.depaseo.model.ResponseReservaTicket;
import com.depaseo.model.ResponseTransactionTicket;
import com.depaseo.model.Usuario;
import com.depaseo.service.AutobusService;
import com.depaseo.service.CancelService;
import com.depaseo.service.ExpectTicketService;
import com.depaseo.service.ReservasService;
import com.depaseo.service.TransaccionService;
import com.depaseo.serviceImpl.ReportEntradas;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.extern.slf4j.Slf4j;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.Authorization;
//import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController()
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE})
@RequestMapping("/api")
//@Api(value = "Gestion de compra Ticket puydefu", description = "Gestiona la resrva de ticket")
public class ApiController {
	

	@Autowired
	ReservasService  reservasService;
	@Autowired
	TransaccionService transaccionService;
	
	@Autowired
	CancelService cancelService;
	@Autowired
	ExpectTicketService expectTicketService;
	
	//@Autowired ReportEntradas report;
	@Autowired
	AutobusService autobusService;
	
	@PostMapping("/login")
	public void login(@ModelAttribute Usuario usuario){
		
		
	}
	
	@GetMapping("/catalogpuydufou")
	public String catalogpuydufou(){
		
		return expectTicketService.catalogo();
	}
	
	@GetMapping("/enviarmail")
	public void enviarmail(){
		
		//emailService.sendMail("kristian_vk_3332@hotmail.com", "url");
		//String urldoc = "url con todo";

	      //String comandoLinux="echo '¡ENHORABUENA POR COMPRAR TUS ENTRADAS!\nEn el siguiente enlace apareceran tus tickets de compra:\n'"+urldoc+"'\nUn saludo'| mail -s \"envio desde linux\" -a From:DePaseo\\<reservas@spaintourism.es\\> kristian_vk_3332@hotmail.com";
		// String comandoLinux="echo hola >> /tmp/prueba";
		 Process process = null;
         try
         { 
           process = Runtime.getRuntime().exec("/app/transportes/desarrollo/sendmail.sh url kristian_vk_3332@hotmail.com"); // for Linux
         //Process process = Runtime.getRuntime().exec("cmd /c dir"); //for Windows

         process.waitFor();
         BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
         String line;
            while ((line=reader.readLine())!=null)
            {
             log.info(line);   
             }
          }       
             catch(Exception e)
          { 
              System.out.println(e); 
          }
          finally
          {
            process.destroy();
          } 
		log.info("Se ha enviado el mail");
	}
	
	
	@GetMapping("/availablepuydufou/{fecha}")
	public String availablepuydufou(@Valid @RequestBody Productos products,@PathVariable(value = "fecha") String fecha){
		log.info("Llamda /api/availablepuydufou/" + fecha);
		if (products == null) {
			return "no has elegido ningun productId";
		}
		
		log.info("productos " + products.toString());
		
		return expectTicketService.available(products, fecha);
		
	}
	
	@GetMapping("/availablebus/{fecha}")
	public Productosbus availableBus(@PathVariable(value = "fecha") String fecha){
		log.info("Llamda /api/availablebus/" + fecha);
		Productosbus productodisponibles = new Productosbus();
		
		productodisponibles.setOrigen("Madrid/Toledo");
		productodisponibles.setProducts(autobusService.plazasLibres(fecha));
		return productodisponibles;
		
	}
	
	@GetMapping("/generateticket")
	public String generarfactura(){
		ReportEntradas report =new ReportEntradas();
		report.exportReport();
		return "Se ha enviado el ticket";
	}
	
	@GetMapping("/sessionpuydufou/{fecha}")
	public String sessionpuydufou(@PathVariable(value = "fecha") String fecha){
		log.info("Llamda /api/sessionpuydufou/" + fecha);
		return expectTicketService.sesiones(fecha);
	}
	
	@ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@ApiOperation(value = "Realiza la transaccion de los ticket", authorizations = { @Authorization(value="jwtToken") })
	@PostMapping("/transaction")
	public ResponseEntity<ResponseTransactionTicket> transaction(@Valid @RequestBody RequestTransactionTicket requestTransactionTicket,@RequestHeader("Authorization") String token){
		RequestTransactionTicket solicitud = requestTransactionTicket;
		ResponseTransactionTicket respuesta = new ResponseTransactionTicket();
		log.info("Llamda /api//transaction/");
		Claims claims = Jwts.parser()
	            .setSigningKey("depaseo".getBytes())	            
	            .parseClaimsJws(token.replace("Bearer ", "")).getBody();
	
		
		String partner = (String) claims.get("sub");
		
		log.info(solicitud.toString());

		
		respuesta = transaccionService.gestionTransaction(requestTransactionTicket, partner);
		return new ResponseEntity<ResponseTransactionTicket>(respuesta,HttpStatus.ACCEPTED);
	}
	@ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@ApiOperation(value = "Realiza la reserva de los ticket", notes = "" )
	@PostMapping("/reservation")
	public ResponseEntity<ResponseReservaTicket> reservar(@Valid @RequestBody RequestReservaTicket requestReservaTicket,@RequestHeader("Authorization") String token){
		RequestReservaTicket solicitud = requestReservaTicket;
		ResponseReservaTicket respuesta = new ResponseReservaTicket();
		log.info("Llamda /api//transaction/");
		Claims claims = Jwts.parser()
	            .setSigningKey("depaseo".getBytes())	            
	            .parseClaimsJws(token.replace("Bearer ", "")).getBody();
	
		
		String partner = (String) claims.get("sub");
		log.info("token: partner " + partner);
		log.info(solicitud.toString());

		
		respuesta = reservasService.gestionReserva(requestReservaTicket, partner);
		return new ResponseEntity<ResponseReservaTicket>(respuesta,HttpStatus.ACCEPTED);
	}
	/*
	@ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@ApiOperation(value = "Realiza el cancelado de los ticket", notes = "Return success si se ha cancelado correctamente" )
	@DeleteMapping("/reservation")
	public ResponseEntity<ResponseCancelTicket> cancelar(@Valid @RequestBody RequestTransactionTicket requestReservaTicket,@RequestHeader("Authorization") String token){
		RequestTransactionTicket solicitud = new RequestTransactionTicket();
		ResponseCancelTicket respuesta = new ResponseCancelTicket();

		Claims claims = Jwts.parser()
	            .setSigningKey("depaseo".getBytes())	            
	            .parseClaimsJws(token.replace("Bearer ", "")).getBody();
	
		
		String partner = (String) claims.get("sub");
		log.info("token: partner " + partner);
		log.info(solicitud.toString());

		
		respuesta = cancelService.cancelarReserva(requestReservaTicket, partner);
		return new ResponseEntity<ResponseCancelTicket>(respuesta,HttpStatus.ACCEPTED);
	}
	*/
}
