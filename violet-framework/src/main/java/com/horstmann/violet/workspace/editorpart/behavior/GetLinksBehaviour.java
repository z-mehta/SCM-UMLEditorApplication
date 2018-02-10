package com.horstmann.violet.workspace.editorpart.behavior;

import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;


import com.horstmann.violet.framework.dialog.DialogFactory;
import com.horstmann.violet.framework.injection.bean.ManiocFramework.BeanInjector;
import com.horstmann.violet.framework.injection.bean.ManiocFramework.InjectedBean;
import com.horstmann.violet.framework.injection.resources.ResourceBundleInjector;
import com.horstmann.violet.framework.injection.resources.annotation.ResourceBundleBean;
import com.horstmann.violet.product.diagram.abstracts.IGraph;
import com.horstmann.violet.product.diagram.abstracts.edge.IEdge;
import com.horstmann.violet.product.diagram.abstracts.node.INode;
import com.horstmann.violet.workspace.editorpart.IEditorPart;
import com.horstmann.violet.workspace.editorpart.IEditorPartBehaviorManager;
import com.horstmann.violet.workspace.editorpart.IEditorPartSelectionHandler;

public class GetLinksBehaviour extends AbstractEditorPartBehavior {
	public GetLinksBehaviour(IEditorPart editorPart) 
	{
		BeanInjector.getInjector().inject(this);
        ResourceBundleInjector.getInjector().inject(this);
		this.editorPart = editorPart;
		this.graph = editorPart.getGraph();
		this.selectionHandler = editorPart.getSelectionHandler();
		this.behaviorManager = editorPart.getBehaviorManager();
	}
	
	public void getLinksForNode() {
		
		final Object edited = selectionHandler.isNodeSelectedAtLeast() ? selectionHandler.getLastSelectedNode() : selectionHandler.getLastSelectedEdge();
        INode node=null;
		if (edited == null)
        {
            return;
        }
        
        if(edited instanceof INode)
        {
        	node=selectionHandler.getSelectedNodes().get(0);
        }
        
		ArrayList<String> listOfLinks = new ArrayList<>();
		for (IEdge iEdge : graph.getAllEdges()) {
			if(iEdge.getStartNode().equals(node))
			{
				listOfLinks.add(iEdge.getClass().getName() +" out");
			}
			else if(iEdge.getEndNode().equals(node))
			{
				listOfLinks.add(iEdge.getClass().getName()+" in");
			}
		}
		if(listOfLinks.size()==0)
		{
			return;
		}
		
		String result="<html>";
		for (String string : listOfLinks) {
			result+=string.substring(string.lastIndexOf(".")+1)+"<br>";
		}
		result+="</html>";
        this.dialogFactory.showInformationDialog(result);
        this.selectionHandler.clearSelection();
		
	}

	private IEditorPartSelectionHandler selectionHandler;
	private IEditorPart editorPart;
	private IGraph graph;
	private IEditorPartBehaviorManager behaviorManager;

	@InjectedBean
	private DialogFactory dialogFactory;

	@ResourceBundleBean(key = "links.properties.title")
	private String dialogTitle;
}
