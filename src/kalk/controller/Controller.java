package kalk.controller;

import java.awt.event.*;
import kalk.model.Model;
import kalk.view.View;

public class Controller {

	private Model l_model;
	private View l_view;
	
	public Controller(Model model,View view) {
		l_model=model;
		l_view=view;
	}
}
