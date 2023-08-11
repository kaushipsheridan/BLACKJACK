/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.blackjackgame;

import java.util.ArrayList;

public class Player {

    private final String nickname;

    private final ArrayList<Card> hand;

    public Player(String nickname) {
        this.nickname = nickname;
        this.hand = new ArrayList<Card>();
    }

    public String getNickname() {
        return nickname;
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public int getHandSum() {
        int handSum = 0;
        for (Card card : hand) {
            handSum += card.getFace().getValue();
        }
        return handSum;
    }

    public String getHandAsString(boolean hideCard) {
        String result = nickname + "\'s current hand:\n";
        for (int i = 0; i < hand.size(); i++) {
            if (i == 0 && hideCard) {
                result += "[Hidden card]\n";
            } else {
                result += hand.get(i);
                
            }
        }
        return result;
    }
}
