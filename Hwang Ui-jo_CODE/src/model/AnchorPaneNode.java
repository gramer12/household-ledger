package model;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.time.LocalDate;

import controller.CalendarController;
import controller.HomeController;

/**
 * Create an anchor pane that can store additional data.
 */
public class AnchorPaneNode extends AnchorPane {

    // Date associated with this pane
    private LocalDate date;
    private HomeController homeCon;
    private CalendarController calendarCon;
    private boolean setClicked;

    /**
     * Create a anchor pane node. Date is not assigned in the constructor.
     * @param children children of the anchor pane
     */
    public AnchorPaneNode(Node... children) {
        super(children);
        // Add action handler for mouse clicked
        setStyle("-fx-border-color: white;");
        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
        	@Override
        	public void handle(MouseEvent ae) {
        		homeCon.date = date;
        		checkClicked();
        		if(getStyle()=="-fx-background-color: deepskyblue;-fx-border-color: white;") {
        			setStyle("-fx-background-color: deepskyblue;-fx-border-color: red;");
        		}else {
        			if(getStyle()=="-fx-background-color: yellow;-fx-border-color: white;") {
        				setStyle("-fx-background-color: yellow;-fx-border-color: red;");
        			}else {
        				if(getStyle()=="-fx-background-color: red;-fx-border-color: white;") {
        					setStyle("-fx-background-color: red;-fx-border-color: red;");
        				}else {
        			if(getStyle()=="-fx-background-color: #EDEDED;-fx-border-color: white;") {
        				setStyle("-fx-background-color: #EDEDED;-fx-border-color: red;");
        			}
        			else {
        				if(getStyle()=="-fx-background-color: white;-fx-border-color: white;") {
        					setStyle("-fx-background-color: white;-fx-border-color: red;");
        				}else {
        				setStyle("-fx-border-color: red;");
        				}
        			}
        				}
        		}
        		setClicked=true;
        	}
        	}
        });
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    public void checkClicked() {
    	for(int i=0;i<calendarCon.allCalendarDays.size();i++) {
    		if(calendarCon.allCalendarDays.get(i).setClicked) {
    			if(calendarCon.allCalendarDays.get(i).getStyle()=="-fx-background-color: deepskyblue;-fx-border-color: red;") {
    				calendarCon.allCalendarDays.get(i).setStyle("-fx-background-color: deepskyblue;-fx-border-color: white;");
    			}else {
    				if(calendarCon.allCalendarDays.get(i).getStyle()=="-fx-background-color: #EDEDED;-fx-border-color: red;") {
    					calendarCon.allCalendarDays.get(i).setStyle("-fx-background-color: #EDEDED;-fx-border-color: white;");
    				}else {
    					if(calendarCon.allCalendarDays.get(i).getStyle()=="-fx-background-color: white;-fx-border-color: red;") {
    						calendarCon.allCalendarDays.get(i).setStyle("-fx-background-color: white;-fx-border-color: white;");
    					}else {
    						if(calendarCon.allCalendarDays.get(i).getStyle()=="-fx-background-color: red;-fx-border-color: red;") {
    							calendarCon.allCalendarDays.get(i).setStyle("-fx-background-color: red;-fx-border-color: white;");
    						}else {
    							if(calendarCon.allCalendarDays.get(i).getStyle()=="-fx-background-color: yellow;-fx-border-color: white;") {
    								calendarCon.allCalendarDays.get(i).setStyle("-fx-background-color: yellow;-fx-border-color: white;"); 
    							}else {
    				                    	calendarCon.allCalendarDays.get(i).setStyle("-fx-border-color: white;"); 
    							}
    						}
    				}
    			}
    			calendarCon.allCalendarDays.get(i).setClicked = false;
    			break;
    		}
    	}
    }
}
}