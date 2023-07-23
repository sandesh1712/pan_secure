package com.pan_secure.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pan_secure.entity.Card;
import com.pan_secure.exceptions.NotAllowedException;
import com.pan_secure.exceptions.NotFoundException;
import com.pan_secure.exceptions.UnauthorizedException;
import com.pan_secure.helper.CardHelper;
import com.pan_secure.repository.CardRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CardService {
	private CardRepository repo;
	private CardHelper cardHelper;
    
	@Autowired
	public CardService(CardRepository repo,CardHelper cardHelper) {
		super();
		this.repo = repo;
		this.cardHelper = cardHelper;
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
	 
	    String panString =  card.getPan();
	    
	    if(panString == null)
	    	throw new NotAllowedException("Pan can't be null!");
	    
	    if(panString.length() < 14 || panString.length() > 16)
	    	throw new NotAllowedException("Invalid pan Number!");

	    String token = this.cardHelper.CardTokenize(panString);
	    card.setToken(token);
	    return this.repo.save(card);	
	}
	
	public void delete(Integer tokenId) {
	   Card card = this.findById(tokenId);
	   this.repo.delete(card);
	}
	
	public String retrivePan(Card card){
		Card oldCard = this.findById(card.getTokenId());
		if(!oldCard.getToken().equals(card.getToken()))
			throw new UnauthorizedException("Unauthorized");
		return oldCard.getPan();
	}
}
