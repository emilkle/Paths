package no.ntnu.idatg2001.goals;

import no.ntnu.idatg2001.Player;
import no.ntnu.idatg2001.goals.Goal;

/**
 *
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen.
 * @version 2023.02.06
 */
public class ScoreGoal implements Goal
{
    private int minimumPoints;

    public ScoreGoal(int minimumPoints){
        this.minimumPoints = minimumPoints;
    }

    @Override
    public boolean isFulfilled(Player player)
    {
        if (player.getScore() > 0){
            this.minimumPoints = minimumPoints;
            return true;
        }
        else
        {
            return false;
        }
    }
}
