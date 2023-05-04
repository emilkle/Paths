package no.ntnu.idatg2001.paths.model.items.equipables.weapons;


/**
 * The Dagger class represents a dagger in the game. It extends the Weapon class.
 *
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen
 */
public class Dagger extends Weapon {

  /**
   * Constructor for the Dagger class.
   *
   * @param name the name of the item
   * @param itemScore the score of the item
   * @param goldValue the gold value of the item
   */
  public Dagger(String name, int itemScore, int goldValue, int damage) {
    super(name, itemScore, goldValue, damage);
  }
}
