/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.blackjackgame;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    private final ArrayList<Card> cards;

    public Deck() {
        cards = new ArrayList<Card>();
        // populate deck with cards
        for (Suit suit : Suit.values()) {
            for (Face face : Face.values()) {
                cards.add(new Card(face, suit));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card draw() {
        return cards.remove(0);
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < cards.size(); i++) {
        result += (i + 1) + "/" + cards.size() + " " + cards.get(i) + "\n";
    }
        
        return result;
    }
}


