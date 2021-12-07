import com.example.demo.rpg.Faction;
import com.example.demo.rpg.characters.Character;
import com.example.demo.rpg.characters.MeleeFighter;
import com.example.demo.rpg.characters.RangedFighter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RpgTests {


	@Test
	void createdCharacterHasDefaultValues() {
		Character character = new MeleeFighter();

		assertEquals(1000, character.getHealth());
		assertEquals(1, character.getLevel());
		assertTrue(character.isAlive());
		assertTrue(character.getFactions().isEmpty());
	}

	@Test
	void characterCanDoDamage(){
		//given
		Character attacker = new MeleeFighter();
		Character defender = new MeleeFighter();

		//when
		attacker.attack(defender, 10);

		//then
		assertEquals(990, defender.getHealth());
		assertTrue(defender.isAlive());
	}

	@Test
	void characterGetsKilledWhenHealthBecomesLessThanZero(){
		//given
		Character attacker = new MeleeFighter();
		Character defender = new MeleeFighter();
		defender.setHealth(1);

		//when
		attacker.attack(defender, 1000);

		//then
		assertEquals(0, defender.getHealth());
		assertFalse(defender.isAlive());
	}

	@Test
	void characterCanHeal(){
		//given
		Character healer = new MeleeFighter();

		//when
		healer.setHealth(500);
		healer.heal(healer, 50);

		//then
		assertEquals(550, healer.getHealth());
		assertTrue(healer.isAlive());
	}

	@Test
	void characterCantHealDeadCharacter(){
		//given
		Character healer = new MeleeFighter();
		healer.setHealth(0);
		healer.setAlive(false);

		//when
		healer.heal(healer, 1000);

		//then
		assertEquals(0, healer.getHealth());
		assertFalse(healer.isAlive());
	}

	@Test
	void characterCantHaveMoreThan1000HealthAfterBeingHealed(){
		//given
		Character healer = new MeleeFighter();
		healer.setHealth(900);

		//when
		healer.heal(healer, 1000);

		//then
		assertEquals(1000, healer.getHealth());
		assertTrue(healer.isAlive());
	}

	@Test
	void characterCantHurtHimself(){
		//given
		Character attacker = new MeleeFighter();

		//when
		attacker.attack(attacker, 10);

		//then
		assertEquals(1000, attacker.getHealth());
		assertTrue(attacker.isAlive());
	}

	@Test
	void characterCanOnlyHealHimself(){
		//given
		Character healer = new MeleeFighter();
		Character injuredCharacter = new MeleeFighter();

		//when
		injuredCharacter.setHealth(500);
		healer.heal(injuredCharacter, 50);

		//then
		assertEquals(500, injuredCharacter.getHealth());
		assertTrue(injuredCharacter.isAlive());
	}

	@Test
	void damageDoneToOverpoweredEnemiesIsReduced(){
		//given
		Character attacker = new MeleeFighter();
		Character highLevelEnemy = new MeleeFighter();

		//when
		highLevelEnemy.setLevel(6);
		highLevelEnemy.setHealth(50);
		attacker.attack(highLevelEnemy, 50);

		//then
		assertEquals(25, highLevelEnemy.getHealth());
		assertTrue(highLevelEnemy.isAlive());
	}

	@Test
	void damageDoneToWeakEnemiesIsAmplified(){
		//given
		Character attacker = new MeleeFighter();
		Character lowLevelEnemy = new MeleeFighter();

		//when
		attacker.setLevel(6);
		lowLevelEnemy.setLevel(1);
		lowLevelEnemy.setHealth(51);
		attacker.attack(lowLevelEnemy, 50);

		//then
		assertEquals(0, lowLevelEnemy.getHealth());
		assertFalse(lowLevelEnemy.isAlive());
	}

	@Test
	void characterMustBeInRange(){
		//given
		Character meleeFighter = new MeleeFighter();
		Character rangedFighter = new RangedFighter();
		Character closeFighter = new RangedFighter();
		Character distantFighterButStillInArcheryRange = new RangedFighter();
		Character distantFighter = new MeleeFighter();
		closeFighter.setPosition(1,1); //distancia 2
		distantFighterButStillInArcheryRange.setPosition(10,10);//distancia 20
		distantFighter.setPosition(11,10);

		//when
		meleeFighter.attack(closeFighter, 1);
		rangedFighter.attack(closeFighter, 10);
		meleeFighter.attack(distantFighterButStillInArcheryRange, 1);
		rangedFighter.attack(distantFighterButStillInArcheryRange, 10);
		meleeFighter.attack(distantFighter, 1);
		rangedFighter.attack(distantFighter, 10);

		assertEquals(989, closeFighter.getHealth());
		assertEquals(990, distantFighterButStillInArcheryRange.getHealth());
		assertEquals(1000, distantFighter.getHealth());
	}

	@Test
	public void characterCanJoinAndLeaveFactions(){
		//given
		Character characterWithTwoFactions = new MeleeFighter();
		Character characterJoinsAndLeavesFaction = new RangedFighter();

		//when
		characterWithTwoFactions.joinFaction(Faction.BOOTERS);
		characterWithTwoFactions.joinFaction(Faction.SPRINGERS);

		characterJoinsAndLeavesFaction.joinFaction(Faction.SPRINGERS);
		characterJoinsAndLeavesFaction.leaveFaction(Faction.SPRINGERS);
		characterJoinsAndLeavesFaction.leaveFaction(Faction.REBEL_SCUM);

		//then
		assertTrue(characterJoinsAndLeavesFaction.getFactions().isEmpty());
		assertTrue(characterWithTwoFactions.getFactions().contains(Faction.BOOTERS));
		assertTrue(characterWithTwoFactions.getFactions().contains(Faction.SPRINGERS));
		assertFalse(characterWithTwoFactions.getFactions().contains(Faction.REBEL_SCUM));
	}

	@Test
	public void characterCantHurtMembersOfHisOwnFaction(){
		//given
		Character attacker = new MeleeFighter();
		Character defenderOfSameFaction = new RangedFighter();
		Character defenderOfDifferentFaction = new RangedFighter();
		attacker.joinFaction(Faction.BOOTERS);
		defenderOfSameFaction.joinFaction(Faction.BOOTERS);
		defenderOfDifferentFaction.joinFaction(Faction.SPRINGERS);

		//when
		attacker.attack(defenderOfSameFaction, 10);
		attacker.attack(defenderOfDifferentFaction, 10);


		//then
		assertEquals(1000, defenderOfSameFaction.getHealth());
		assertEquals(990, defenderOfDifferentFaction.getHealth());
	}

	@Test
	public void characterCanHealMembersOfHisOwnFaction(){
		//given
		Character healer = new MeleeFighter();
		Character injuredCharacterOfSameFaction = new RangedFighter();
		Character injuredCharacterOfDifferentFaction = new RangedFighter();
		injuredCharacterOfSameFaction.setHealth(500);
		injuredCharacterOfDifferentFaction.setHealth(500);
		healer.joinFaction(Faction.BOOTERS);
		injuredCharacterOfSameFaction.joinFaction(Faction.BOOTERS);
		injuredCharacterOfDifferentFaction.joinFaction(Faction.SPRINGERS);

		//when
		healer.heal(injuredCharacterOfSameFaction, 10);
		healer.heal(injuredCharacterOfDifferentFaction, 10);


		//then
		assertEquals(510, injuredCharacterOfSameFaction.getHealth());
		assertEquals(500, injuredCharacterOfDifferentFaction.getHealth());
	}

}
