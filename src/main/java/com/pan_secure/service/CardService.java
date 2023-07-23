package com.pan_secure.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.pan_secure.entity.Card;
import com.pan_secure.exceptions.NotAllowedException;
import com.pan_secure.exceptions.NotFoundException;
import com.pan_secure.repository.CardRepository;

public class CardService {
	private CardRepository repo;
    
	@Autowired
	public CardService(CardRepository repo) {
		super();
		this.repo = repo;
	}
	
	public Card findById(int token_id) {
		Optional<Card> card = this.repo.findById(token_id);
		if(card.isEmpty())
			throw new NotFoundException("Invalid Id Provided");
		return card.get();
	}
	
	public Card create(Card card) {
		if(card.getTokenId()!=null)
			throw new NotAllowedException("Passing token_id Not Allowed!!");
	    if(card.getToken() != null)
	    	throw new NotAllowedException("Passing token Not Allowed!!");
	    if(card.getPan() ==null)
	    	throw new NotAllowedException("Pan can't be null!");
        
	    
	    
	    return this.repo.save(card);	
	}

}
