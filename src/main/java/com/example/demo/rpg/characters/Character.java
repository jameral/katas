package com.example.demo.rpg.characters;

import com.example.demo.rpg.AbstractEntity;
import com.example.demo.rpg.Faction;

import java.util.HashSet;
import java.util.Set;

public abstract class Character extends AbstractEntity {

    Set<Faction> factions = new HashSet<>();

    public Character(){
        health = 1000;
        level = 1;
        alive = true;
    }

    public void attack(AbstractEntity objective, int damageDone){
        if (canAttack(objective)){
            damageDone = calculateDamageDone(damageDone, objective.getLevel());
            int newHealth = Math.max(0, objective.getHealth() - damageDone);
            objective.setHealth(newHealth);
            objective.setAlive(newHealth > 0);
        }
    }

    private boolean canAttack(AbstractEntity objective) {
        boolean isCharacter = objective instanceof Character;
        boolean isValidTarget =  (objective!= this) && (!isCharacter || !isInFaction((Character) objective));
        return isValidTarget && isInRange(objective);
    }

    protected abstract boolean isInRange(AbstractEntity target);

    private int calculateDamageDone(int damageDone, int level) {
        int diffInLevels = this.level - level;
        if (diffInLevels > 4){
            return damageDone + Math.round(damageDone * 0.5f);
        } else if (diffInLevels < -4){
            return damageDone - Math.round(damageDone * 0.5f);
        } else {
            return damageDone;
        }
    }

    public void heal(Character objective, int damageHealed){
        if (canHeal(objective)){
            int newHealth = Math.min(1000, objective.getHealth() + damageHealed);
            objective.setHealth(newHealth);
        }
    }

    protected boolean canHeal(AbstractEntity objective){
        boolean isCharacter = objective instanceof Character;
        boolean isValidTarget = isCharacter && (objective == this || isInFaction((Character)objective));
        return isValidTarget && objective.isAlive();
    }

    public int calculateDistance(AbstractEntity target){
        int [] targetPosition = target.getPosition();
        return Math.abs(this.position[0] - targetPosition[0]) + Math.abs(this.position[1] - targetPosition[1]);
    }

    public void joinFaction(Faction faction){
        factions.add(faction);
    }

    public void leaveFaction(Faction faction){
        factions.remove(faction);
    }

    public boolean isInFaction(Character character){
        return character.factions.stream().anyMatch(factions::contains);
    }

    public Set<Faction> getFactions(){
        return factions;
    }
}
