/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdc.assignment.gui.component;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author wease
 */
public final class RuleViewer extends JPanel{
    private final int compWidth = 500;
    private final int compHeight = 500;
    private final String[] RULES = {
        "\n* This is a level based game that has you fight monsters.",
        "\n* There is a level bar that shows up every new level.",
        "\n* Each monster is randomly generated with different stats.",
        "\n* You must defeat them in order to move on.",
        "\n* When defeated, monsters will drop loot that will boost stats.",
        "\n* Potions will go into your inventory' armour/weapons will auto-equip if better.",
        "\n* Each turn will charge up a super attack bar.",
        "\n* When maxed out, your next attack will deal double damage.",
        "\n* You cannot use a potion to go over max health/super attack.",
        "\n* You can use as many potions before attacking.",
        "\n* Enjoy!"
    };

    
    public RuleViewer() {
        for (String element : this.RULES) {
            JLabel ruleText = new JLabel();
            ruleText.setText(element);
            this.add(ruleText);
        }
        this.setLayout(new GridLayout(11,1));
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(compWidth, compHeight);
    }
}

