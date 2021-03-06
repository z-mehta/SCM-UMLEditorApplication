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

package com.horstmann.violet.product.diagram.state.node;

import com.horstmann.violet.framework.graphics.content.ContentBackground;
import com.horstmann.violet.framework.graphics.content.ContentInsideShape;
import com.horstmann.violet.framework.graphics.content.EmptyContent;
import com.horstmann.violet.framework.graphics.shape.ContentInsideEllipse;
import com.horstmann.violet.product.diagram.abstracts.node.AbstractNode;
import com.horstmann.violet.product.diagram.abstracts.node.INode;
import com.horstmann.violet.product.diagram.state.StateDiagramConstant;
import com.horstmann.violet.workspace.sidebar.colortools.ColorToolsBarPanel;

/**
 * An initial or final node_old (bull's eye) in a state or activity diagram.
 */
public class CircularInitialStateNode extends AbstractNode
{
    public CircularInitialStateNode()
    {
        super();
        createContentStructure();
    }

    protected CircularInitialStateNode(CircularInitialStateNode node) throws CloneNotSupportedException
    {
        super(node);
        createContentStructure();
    }

    @Override
    protected INode copy() throws CloneNotSupportedException
    {
        return new CircularInitialStateNode(this);
    }

    @Override
    protected void createContentStructure()
    {
        EmptyContent emptyContent = new EmptyContent();
        emptyContent.setMinHeight(DEFAULT_DIAMETER);
        emptyContent.setMinWidth(DEFAULT_DIAMETER);

        ContentInsideShape contentInsideShape = new ContentInsideEllipse(emptyContent, 1);

        ContentBackground contentBackground = new ContentBackground(contentInsideShape, ColorToolsBarPanel.PASTEL_GREY.getBorderColor());
        setContent(contentBackground);
    }

    @Override
    public String getToolTip()
    {
        return StateDiagramConstant.STATE_DIAGRAM_RESOURCE.getString("tooltip.scenario_start_node");
    }

    /**
     * Kept for compatibility with old versions
     * 
     * @param dummy
     */
    public void setFinal(boolean dummy)
    {
        // Nothing to do
    }

    /** default node_old diameter */
    private static int DEFAULT_DIAMETER = 12;

}
