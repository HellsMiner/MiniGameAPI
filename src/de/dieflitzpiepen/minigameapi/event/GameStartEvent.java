/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.dieflitzpiepen.minigameapi.event;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 *
 * @author Tammo
 */
public class GameStartEvent extends Event{
    private HandlerList handlers;

    public GameStartEvent(HandlerList handlers) {
        this.handlers = handlers;
    }
    
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    
    
}
