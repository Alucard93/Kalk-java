package kalk;

import java.util.*;

import kalk.model.ColorModel;
import kalk.model.IllegalColorException;
import kalk.model.Model;

public class Use {
	static boolean exit=false;
	static Scanner inTerminal = new Scanner(System.in);
	static String[] aMenu = {"Nuova (default)","Cronologia","Chiudi"};
	static Vector<String> menu = new Vector<String>(Arrays.asList(aMenu));
	static ColorModel model = new ColorModel();

	public static void main(String[] args)
	{
		//menù
		while(!exit) {
			showList(menu);
			int choice = getChoice();
			execute(choice);
		}
	}

	private static void execute(int choice) {
		switch(choice) {
		case 1:
			showVector(model.getHistory());
			break;
		case 2:
			exit = true;
			break;
		default:
			newOperation();
			break;				
		}

	}

	private static void newOperation() {
		showList(model.allAvailableTypes());
		int leftSize = model.setLeftType(model.allAvailableTypes().elementAt(getChoice()));
		boolean success = false;
		while(!success) {
			try {
				model.setLeftValues(getValues(leftSize));
				success = true;
			} catch (IllegalColorException e) {
				success = false;
				System.out.println(e.what());
			}	
		}

		showList(model.permittedOperations());
		model.setOp(model.permittedOperations().elementAt(getChoice()));
		if(!model.permittedTypes().lastElement().equals("none")) {
			showList(model.permittedTypes());
			int rightSize = model.setLeftType(model.allAvailableTypes().elementAt(getChoice()));
			success = false;
			while(!success) {
				try {
					model.setRightValues(getValues(rightSize));
					success = true;
				} catch (IllegalColorException e) {
					success = false;
					System.out.println(e.what());
				}	
			}
		}
		try {
			model.execute();
		} catch (IllegalColorException | CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Risultato");
		showVector(model.getResult());

	}

	private static Vector<String> getValues(int size){
		Vector<String> values = new Vector<String>(size);
		System.out.println("Questo elemento ha bisogno di "+size+" input");
		for(int i=0;i<size;i++)
			values.add(i, inTerminal.nextLine());
		return values;
	}

	private static void showVector(Vector<String> vector) {
		for(String element : vector)
			System.out.println(element);
	}

	private static int getChoice() {
		return inTerminal.nextInt();
	}

	public static void showList(Vector<String> list) {
		int i=0;
		System.out.println("Selezionare una di queste voci");
		for(String line:list) {
			System.out.println(i+"."+line);
			i++;
		}

	}
}

/**
 * #include "consoleview.h"
#include <iostream>
#include <QApplication>

QVector<QString> ConsoleView::menu={"Nuovo(default)","Cronologia","Chiudi"};


ConsoleView::ConsoleView(const ConsoleView& console){
    l_types=console.l_types;
    r_types=console.r_types;
    l_size=console.l_size;
    l_size=console.l_size;
}


 * @brief ConsoleView::setLeftTypes
 * @param types
 * sets up l_types variable

void ConsoleView::setLeftTypes(const QVector<QString> types){
    l_types=types;
}

/**
 * @brief ConsoleView::setRightTypes
 * @param types
 * sets up r_types variable

void ConsoleView::setRightTypes(const QVector<QString> types){
    r_types=types;
}
/**
 * @brief ConsoleView::setLeftFields
 * @param fields
 * sets up l_size variable

void ConsoleView::setLeftFields(const int& fields, const QVector<QString>& limits){
    l_size=fields;
}
/**
 * @brief ConsoleView::setRightFields
 * @param fields
 * sets up r_size variable

void ConsoleView::setRightFields(const int& fields,const QVector<QString>& limits){
    r_size=fields;
}
/**
 * @brief ConsoleView::setAvailableOperations
 * @param opt
 * sets up all operations that are available not really required

void ConsoleView::setAvailableOperations(QVector<QString> opt){
    allOpts=opt;
}
/**
 * @brief ConsoleView::setPermittedOperations
 * @param opt
 * sets up all operations that the user can execute

void ConsoleView::setPermittedOperations(QVector<QString> opt){
    permittedOpts=opt;
}

/**
 * @brief ConsoleView::setResult
 * @param result
 * sets up local_result variable

void ConsoleView::setResult(QVector<QString> result){
    local_result=result;
}

/**
 * @brief ConsoleView::setResultFields
 * @param fields
 * does nothing because the resultFields are the same as left operand


void ConsoleView::setResultFields(const int& fields){
    //DO NOTHING
}

void ConsoleView::error(const QString& error_message){
    std::cout<<error_message.toStdString();
}

/**
 * @brief ConsoleView::show
 * inizialize the view inside the terminal


void ConsoleView::show(){
    bool exit=false;
    while(!exit){
        showMenu();
        QString choice = consoleInput(1)[0];
        switch (choice.toInt()) {
        case 1:
            emit getHistory();
            break;
        case 2:
            exit = true;
            break;
        default:
            newOperation();
            break;
        }
    }
}

/**
 * @brief ConsoleView::newOperation
 * Shows the interface for new operation



void ConsoleView::newOperation()
{
    showChoices(l_types);

    emit leftTypeIsSet(l_types[consoleInput(1).first().toInt()]);
    std::cout<<'\n'<<"richiede "<<l_size<<" elementi"<<'\n';
    emit leftValuesAreSet(consoleInput(l_size));
    showChoices(permittedOpts);
    emit operationIsSet(permittedOpts[consoleInput(1).first().toInt()]);
    if(r_types.last()!="none"){
        showChoices(r_types);
        emit rightTypeIsSet(r_types[consoleInput(1).first().toInt()]);
        std::cout<<'\n'<<"richiede "<<r_size<<" elementi"<<'\n';
        emit rightValuesAreSet(consoleInput(r_size));
    }
    std::cout<<'\n'<<"Risultato"<<'\n';
    emit getResult();
    showString(local_result);

}

/**
 * @brief ConsoleView::consoleInput
 * @param n
 * @return user input in QVector<QString>
 * used to read input from user

QVector<QString> ConsoleView::consoleInput(int n){

    QVector<QString> toReturn;
    while(toReturn.size()<n){
        char c_string[64];
        std::cin>>c_string;
        toReturn.push_back(c_string);
    }
    return  toReturn;
}

void ConsoleView::showChoices(const QVector<QString>& s_vector){
    std::cout<<'\n'<<"Selezione una voce"<<'\n';
    QString line;
    int number = 0;
    foreach(line,s_vector){
        std::cout<<number<<'.'<<line.toStdString()<<'\n';
        number++;
    }
    std::cout<<'\n';
}
/**
 * @brief ConsoleView::showString
 * @param s_vector
 * prints on screen the content inside a QVector<QString>

void ConsoleView::showString(const QVector<QString>& s_vector){
    QString line;
    foreach(line,s_vector){
        std::cout<<line.toStdString()<<'\n';
    }
    std::cout<<'\n';
}
/**
 * @brief ConsoleView::setHistory
 * @param history
 * shows history on terminal


void ConsoleView::setHistory(const QVector<QVector<QString>>& history){
    QVector<QString> lines;
    int op=history.size();
    foreach(lines, history){
        std::cout<<"Operazione n."<<op<<"\n";
        showString(lines);
    }

}
/**
 * @brief ConsoleView::showMenu
 * shows basic operation the menù in the terminal


void ConsoleView::showMenu(){
    showChoices(menu);
}

/**
 * @brief ConsoleView::update
 * useless

void ConsoleView::update(){
    l_update=true;
}
 */
