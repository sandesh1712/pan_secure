package com.pan_secure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pan_secure.dto.CardCreateReponse;
import com.pan_secure.dto.CardGetReponse;
import com.pan_secure.entity.Card;
import com.pan_secure.service.CardService;

@RestController
@RequestMapping("/ps")
public class CardController {
	private CardService cardService;
    
	@Autowired
	public CardController(CardService cardService) {
		this.cardService = cardService;
	}
	
	@PostMapping("/tokenize")
	public ResponseEntity<CardCreateReponse> tokenize(@RequestBody Card c){
		Card newCard = this.cardService.create(c);
		CardCreateReponse cardCreateReponse = new CardCreateReponse();
		cardCreateReponse.setTokenId(newCard.getTokenId());
		cardCreateReponse.setToken(newCard.getToken());
		return new ResponseEntity<CardCreateReponse>(cardCreateReponse,HttpStatus.CREATED);
	}
	
	@PostMapping("/retrive")
	public ResponseEntity<CardGetReponse> retrive(@RequestBody Card c){
		String retrivedPan = this.cardService.retrivePan(c);
		CardGetReponse cardGetReponse = new CardGetReponse();
		cardGetReponse.setPan(retrivedPan);
		cardGetReponse.setTokenId(c.getTokenId());
		return new ResponseEntity<CardGetReponse>(cardGetReponse,HttpStatus.OK);
	}
	
	@DeleteMapping("/remove/{tokenId}")
	public ResponseEntity remove(@PathVariable int tokenId){
		this.cardService.delete(tokenId);
		return new ResponseEntity(HttpStatus.OK);
	}
		
}
