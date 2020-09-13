package com.engg.digitalorg.managers;

import com.engg.digitalorg.model.entity.Card;
import com.engg.digitalorg.model.entity.Group;
import com.engg.digitalorg.model.response.CardResponse;
import com.engg.digitalorg.repository.CardRepository;
import com.engg.digitalorg.repository.GroupRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class EmailValidationManager {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private GroupRepository groupRepository;

    public List<Card> getAllCard() {
        return cardRepository.findAll();

    }
    public List<Group> getAllGroup() {
        return groupRepository.findAll();
    }
     public Map<String, List> getEmailValidationManager(String emailId) {
        Map<String, List> map = new HashMap();
         List<CardResponse> cardList = getAllCard().stream()
                 .collect(Collectors.mapping(p -> new ModelMapper().map(p, CardResponse.class), Collectors.toList()));


         List<CardResponse> cardResponseList = cardList.stream().map(cardResponse2 -> {
             if(cardResponse2.getCreated_by().equals(emailId)) {
                 cardResponse2.setHasAdmin(true);
             }
             else {
                 cardResponse2.setHasAdmin(false);
             }
             return  cardResponse2;
         }).collect(Collectors.toList());

         map.put("cards", cardResponseList);
         map.put("groups", getAllGroup());
        return map;
     }
}
