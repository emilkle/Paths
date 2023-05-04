package no.ntnu.idatg2001.paths.model.units;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/** The SpecialAttributes interface represents the special attributes of a unit. */
@Entity
public class Attributes {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  // S.P.E.C.I.A.L stats
  private int strength;
  private int perception;
  private int endurance;
  private int charisma;
  private int intelligence;
  private int agility;
  private int luck;

  /**
   * Instantiates a new Attributes.
   *
   * @param strength the strength
   * @param perception the perception
   * @param endurance the endurance
   * @param charisma the charisma
   * @param intelligence the intelligence
   * @param agility the agility
   * @param luck the luck
   */
  public Attributes(
      int strength,
      int perception,
      int endurance,
      int charisma,
      int intelligence,
      int agility,
      int luck) {
    this.strength = strength;
    this.perception = perception;
    this.endurance = endurance;
    this.charisma = charisma;
    this.intelligence = intelligence;
    this.agility = agility;
    this.luck = luck;
  }

  protected Attributes() {}

  public Attributes(DefaultAttributes defaultAttributes) {
    this.strength = defaultAttributes.getAttributes().getStrength();
    this.perception = defaultAttributes.getAttributes().getPerception();
    this.endurance = defaultAttributes.getAttributes().getEndurance();
    this.charisma = defaultAttributes.getAttributes().getCharisma();
    this.intelligence = defaultAttributes.getAttributes().getIntelligence();
    this.agility = defaultAttributes.getAttributes().getAgility();
    this.luck = defaultAttributes.getAttributes().getLuck();
  }

  /**
   * Gets strength.
   *
   * @return the strength
   */
  public int getStrength() {
    return strength;
  }

  /**
   * Sets strength.
   *
   * @param strength the strength
   */
  public void setStrength(int strength) {
    this.strength = strength;
  }

  /**
   * Gets perception.
   *
   * @return the perception
   */
  public int getPerception() {
    return perception;
  }

  /**
   * Sets perception.
   *
   * @param perception the perception
   */
  public void setPerception(int perception) {
    this.perception = perception;
  }

  /**
   * Gets endurance.
   *
   * @return the endurance
   */
  public int getEndurance() {
    return endurance;
  }

  /**
   * Sets endurance.
   *
   * @param endurance the endurance
   */
  public void setEndurance(int endurance) {
    this.endurance = endurance;
  }

  /**
   * Gets charisma.
   *
   * @return the charisma
   */
  public int getCharisma() {
    return charisma;
  }

  /**
   * Sets charisma.
   *
   * @param charisma the charisma
   */
  public void setCharisma(int charisma) {
    this.charisma = charisma;
  }

  /**
   * Gets intelligence.
   *
   * @return the intelligence
   */
  public int getIntelligence() {
    return intelligence;
  }

  /**
   * Sets intelligence.
   *
   * @param intelligence the intelligence
   */
  public void setIntelligence(int intelligence) {
    this.intelligence = intelligence;
  }

  /**
   * Gets agility.
   *
   * @return the agility
   */
  public int getAgility() {
    return agility;
  }

  /**
   * Sets agility.
   *
   * @param agility the agility
   */
  public void setAgility(int agility) {
    this.agility = agility;
  }

  /**
   * Gets luck.
   *
   * @return the luck
   */
  public int getLuck() {
    return luck;
  }

  /**
   * Sets luck.
   *
   * @param luck the luck
   */
  public void setLuck(int luck) {
    this.luck = luck;
  }

  /**
   * Merges two attributes together.
   *
   * @param attributes the attributes to merge
   */
  public void addAttributes(Attributes attributes) {
    this.setStrength(this.getStrength() + attributes.getStrength());
    this.setPerception(this.getPerception() + attributes.getPerception());
    this.setEndurance(this.getEndurance() + attributes.getEndurance());
    this.setCharisma(this.getCharisma() + attributes.getCharisma());
    this.setIntelligence(this.getIntelligence() + attributes.getIntelligence());
    this.setAgility(this.getAgility() + attributes.getAgility());
    this.setLuck(this.getLuck() + attributes.getLuck());
  }

  /**
   * Subtracts two attributes from each other.
   *
   * @param attributes the attributes to subtract
   */
  public void subtractAttributes(Attributes attributes) {
    this.setStrength(this.getStrength() - attributes.getStrength());
    this.setPerception(this.getPerception() - attributes.getPerception());
    this.setEndurance(this.getEndurance() - attributes.getEndurance());
    this.setCharisma(this.getCharisma() - attributes.getCharisma());
    this.setIntelligence(this.getIntelligence() - attributes.getIntelligence());
    this.setAgility(this.getAgility() - attributes.getAgility());
    this.setLuck(this.getLuck() - attributes.getLuck());
  }
}
