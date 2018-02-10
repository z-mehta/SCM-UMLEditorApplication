/*
 Violet - A program for editing UML diagrams.

 Copyright (C) 2007 Cay S. Horstmann (http://horstmann.com)
 Alexandre de Pellegrin (http://alexdp.free.fr);

 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation; either version 2 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program; if not, write to the Free Software
 Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package com.horstmann.violet.application.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.swing.*;

import com.horstmann.violet.application.gui.MainFrame;
import com.horstmann.violet.application.help.AboutDialog;
import com.horstmann.violet.application.help.HelpManager;
import com.horstmann.violet.application.help.ShortcutDialog;
import com.horstmann.violet.framework.injection.resources.ResourceBundleInjector;
import com.horstmann.violet.framework.injection.resources.annotation.ResourceBundleBean;
import com.horstmann.violet.product.diagram.abstracts.AbstractGraph;
import com.horstmann.violet.product.diagram.abstracts.edge.IEdge;
import com.horstmann.violet.product.diagram.abstracts.node.INode;
import com.horstmann.violet.workspace.editorpart.behavior.AddEdgeBehavior;

import static com.horstmann.violet.product.diagram.abstracts.AbstractGraph.alNodes;

/**
 * Help menu
 * 
 * @author Alexandre de Pellegrin
 * 
 */
@ResourceBundleBean(resourceReference = MenuFactory.class)
public class HelpMenu extends JMenu
{

    /**
     * Default constructor
     * 
     * @param mainFrame where this menu is atatched
     * @param factory to access to external resources such as texts, icons
     */
    @ResourceBundleBean(key = "help")
    public HelpMenu(MainFrame mainFrame)
    {
        ResourceBundleInjector.getInjector().inject(this);
        this.mainFrame = mainFrame;
        this.createMenu();
    }

    /**
     * Initializes menu
     */
    private void createMenu()
    {

        userGuideItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                HelpManager.getInstance().openUserGuide();
            }

        });
        this.add(userGuideItem);

        homepageItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                HelpManager.getInstance().openHomepage();
            }

        });
        this.add(homepageItem);
        
        aboutItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {


                AboutDialog dialog = new AboutDialog(mainFrame);
                dialog.setVisible(true);
            }

        });
        this.add(aboutItem);

        shortcutItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ShortcutDialog dialog = new ShortcutDialog(mainFrame);
                dialog.setVisible(true);
            }
        });
        this.add(shortcutItem);




        // add toogle button in the help menu
        Action toggleAction = new AbstractAction("Enable Feature 1") {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractButton button = (AbstractButton)e.getSource();
                if (button.isSelected()) {
                    button.setText("Disable Feature 1");
                    // Start the action here

                    for (int x=0; x<alNodes.size();x++){
                    if(Collections.frequency(alNodes,alNodes.get(x)) > 1){
                        JOptionPane.showMessageDialog(null, "Graph is Incorrect : has Invalid Recursive Relationship(s)", "Warning!", JOptionPane.WARNING_MESSAGE);
                        break;
                        }
                    }

                    AbstractGraph.enable=true;
                } else {
                    button.setText("Enable Feature 1");
                    // Stop the action here
                    AbstractGraph.enable=false;
                }
            }
        };
        this.add(new JToggleButton(toggleAction));



        // add toogle button in the help menu
        Action toggleAction1 = new AbstractAction("Enable Feature 2") {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractButton button1 = (AbstractButton)e.getSource();
                if (button1.isSelected()) {
                    button1.setText("Disable Feature 2");
                    // Start the action here1
            //    AddEdgeBehaviour.Feature2=true;
                    
                    
                } else {
                    button1.setText("Enable Feature 2");
                    // Stop the action here
               //     AddEdgeBehaviour.Feature2=false;
                    
                }
            }
        };
        this.add(new JToggleButton(toggleAction1));


    }

    

    /**
     * Main app frame where this menu is attached to
     */
    private JFrame mainFrame;
    
    @ResourceBundleBean(key = "help.userguide")
    private JMenuItem userGuideItem;
    
    @ResourceBundleBean(key = "help.homepage")
    private JMenuItem homepageItem;
    
    @ResourceBundleBean(key = "help.about")
    private JMenuItem aboutItem;

    @ResourceBundleBean(key = "help.shortcut")
    private JMenuItem shortcutItem;

    @ResourceBundleBean(key = "help.validateDiagram")
    private JMenuItem validateDiagram;


}
