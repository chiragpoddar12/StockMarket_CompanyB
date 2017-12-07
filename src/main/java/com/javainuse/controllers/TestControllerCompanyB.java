package com.javainuse.controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.javainuse.model.Company;;

@RestController
public class TestControllerCompanyB {

	@RequestMapping(value = "/SAP", method = RequestMethod.GET)
	public Company firstPage() {
		Company cmp = new Company();
		cmp.setName("SAP");
		cmp.setNumberOfShares(2000);
		cmp.setPriceOfShares(60);
		cmp.setSharesDoublesWhen(800);
		return cmp;
	}
	@RequestMapping(value = "/SAP", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ResponseUpgrade> update(@RequestBody ResponseUpgrade response) {
//	    ResponseUpgrade response= null;
		if (response != null) {
	    	jdbcConnection jdbc=new jdbcConnection();
    		Connection conn=jdbc.startConnection();
			try {
				//getting the seller shares
				String q="select Number_of_shares from company_b WHERE name='"+response.getSellerName()+"'";
				Statement st=conn.createStatement();
				ResultSet rs1=st.executeQuery(q);
				int seller_shares_og=0;
				while(rs1.next()) {
				seller_shares_og=rs1.getInt(1);}
				int seller_shares_after= seller_shares_og-response.getNumberOfShares();
				//updating the seller shares 	
				String r="UPDATE company_b SET Number_of_shares="+seller_shares_after+" WHERE name='"+response.getSellerName()+"'";
				Statement st1=conn.createStatement();
				st1.executeUpdate(r);		
				rs1.close();
				st.close();
	    		st1.close();
				//getting the buyer shares
				String s="SELECT Number_of_shares from company_b WHERE name='"+response.getBuyerName()+"'";
				Statement st2=conn.createStatement();
				ResultSet rs3=st2.executeQuery(s);
				int buyer_shares_og=0;
				while(rs3.next()) {
				buyer_shares_og=rs3.getInt(1);}
				int buyer_shares_after= buyer_shares_og+response.getNumberOfShares();
				rs3.close();
				st2.close();
				//updating the buyer shares
				String t="UPDATE company_b SET Number_of_shares="+buyer_shares_after+" WHERE name='"+response.getBuyerName()+"'";
				Statement st3=conn.createStatement();
				st3.executeUpdate(t);
				st3.close();
				conn.close();
	    	} catch (SQLException e) {
	    
				// TODO Auto-generated catch block
				e.printStackTrace();
				}   
	    }
	    return new ResponseEntity<ResponseUpgrade>(response, HttpStatus.OK);
	}

}
