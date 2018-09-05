package kalk;

import java.util.*;
import kalk.model.ColorModel;
import kalk.model.IllegalColorException;

public class Use {
	static boolean firstrun=false;
	static Scanner inTerminal = new Scanner(System.in);
	static String[] menu = {"Nuova (default)","Chiudi"};
	
	public static void main(String[] args) {
		boolean ans = false;
		ColorModel current = null;
		boolean exit = false;
		while(!exit) {
			
		}
	}
	
	public static String showMenu() {
		showChoices(menu);
		return menu[getChoices()];
	}
	
	public static void showChoices(String[] choices) {
		int n=0;
		System.out.println("Scegli una delle seguenti operazioni");
		for(String choice:choices) {
			System.out.println(n+" "+choice);
			n++;
		}
		
	}
	
	public static int getChoices() {
		return inTerminal.nextInt();
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
