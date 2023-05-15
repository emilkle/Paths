package no.ntnu.idatg2001.paths.model;

import static org.junit.jupiter.api.Assertions.*;

import no.ntnu.idatg2001.paths.model.Link;
import no.ntnu.idatg2001.paths.model.Passage;
import no.ntnu.idatg2001.paths.model.Story;
import no.ntnu.idatg2001.paths.model.units.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class StoryTest {
  private Story story;
  private Passage passage1;
  private Passage passage2;
  private Passage passage3;
  private Passage passage4;
  private Player player;
  private Link link1to2;
  private Link link1to3;
  private Link link2to3;
  private Link link3to4;

  @BeforeEach
  void setUp() {
    player = new Player.PlayerBuilder().withName("Erik").build();

    passage1 = new Passage("Passage 1", "This is passage 1");
    passage2 = new Passage("Passage 2", "This is passage 2");
    passage3 = new Passage("Passage 3", "This is passage 3");
    passage4 = new Passage("Passage 4", "This is passage 4");

    link1to2 = new Link("Link 1-2", passage2.getTitle());
    link1to3 = new Link("Link 1-3", passage3.getTitle());
    link2to3 = new Link("Link 2-3", passage3.getTitle());
    link3to4 = new Link("Link 3-4", passage4.getTitle());

    passage1.addLink(link1to2);
    passage2.addLink(link2to3);
    passage3.addLink(link3to4);

    story = new Story("Test story", passage1);
    story.addPassage(passage2);
    story.addPassage(passage3);
    // story.addPassage(passage4);
  }

  @AfterEach
  void tearDown() {}

  @Test
  void getOpeningPassage() {
    assertEquals(story.getOpeningPassage(), passage1);
  }

  @Test
  void setOpeningPassage() {
    story.setOpeningPassage(passage2);
    assertEquals(story.getOpeningPassage(), passage2);
  }

  @Test
  void addPassage() {
    story.addPassage(passage4);
    assertTrue(story.getPassages().contains(passage4));
  }

  @Test
  void getLinksConnectedWithPassage() {
    List<Link> linksConnectedWithPassage1 = story.getLinksConnectedWithPassage(passage1);
    System.out.println("Links connected with passage 1 in hashmap: " + linksConnectedWithPassage1);
    System.out.println("Links connected with passage 1 in parameter: " + passage1.getLinks());
    System.out.println("Link 1 to 2: " + link1to2);
    for (Link link : linksConnectedWithPassage1) {
      assertEquals(link, link1to2);
    }

    System.out.println(
        "\nLinks connected with passage 2 in hashmap: "
            + story.getLinksConnectedWithPassage(passage2));
    System.out.println("Links connected with passage 2 in parameter: " + passage2.getLinks());
    System.out.println("Link 1 to 2: " + link1to2);
    List<Link> linksConnectedWithPassage2 = story.getLinksConnectedWithPassage(passage2);
    for (Link link : linksConnectedWithPassage2) {
      assertEquals(link, link1to2);
    }
  }

  @Test
  void getPassages() {
    List<Passage> passages = story.getPassages();
    assertTrue(passages.contains(passage2));
    assertTrue(passages.contains(passage3));
  }

  @Test
  void removePassage() {}

  @Test
  void getBrokenLinks() {}
}
