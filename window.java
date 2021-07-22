package com.langweenee.www;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class window {

	protected Shell shlWindowsSleeper;
	private Text hour;
	private Text minutes;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			window window = new window();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlWindowsSleeper.open();
		shlWindowsSleeper.layout();
		while (!shlWindowsSleeper.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlWindowsSleeper = new Shell();
		shlWindowsSleeper.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		shlWindowsSleeper.setImage(SWTResourceManager.getImage("D:\\EclipseWorkspace\\WindowsSleeper\\src\\com\\langweenee\\www\\background.png"));
		shlWindowsSleeper.setSize(600, 300);
		shlWindowsSleeper.setText("Windows Sleeper");
		MessageBox dialog = new MessageBox(shlWindowsSleeper, SWT.OK);
		
		Label lblTimeSetFor = new Label(shlWindowsSleeper, SWT.NONE);
		lblTimeSetFor.setBounds(382, 79, 130, 21);
		lblTimeSetFor.setText("Timer not set");
		
		Label label = new Label(shlWindowsSleeper, SWT.SEPARATOR | SWT.VERTICAL);
		label.setBounds(294, 48, 18, 125);
		
		DateTime dateTime = new DateTime(shlWindowsSleeper, SWT.BORDER | SWT.TIME);
		dateTime.setBounds(28, 48, 114, 34);
		
		hour = new Text(shlWindowsSleeper, SWT.BORDER);
		hour.setToolTipText("Hours");
		hour.setBounds(28, 109, 76, 21);
		hour.addVerifyListener(new VerifyListener() {
	        @Override
	        public void verifyText(VerifyEvent e) {
	            
	            Text text = (Text)e.getSource();
	            
	            final String old = text.getText();
	            String newString = old.substring(0, e.start) + e.text + old.substring(e.end);
	            
	            try
	            {
	                Integer.parseInt(newString);
	            }
	            catch(NumberFormatException ex)
	            {
	                e.doit = false;
	            }
	            
	            System.out.println(newString);
	        }
	    });
		
		
		
		minutes = new Text(shlWindowsSleeper, SWT.BORDER);
		minutes.setToolTipText("Minutes");
		minutes.setBounds(27, 136, 76, 21);
		
		
		
		minutes.addVerifyListener(new VerifyListener() {
	        @Override
	        public void verifyText(VerifyEvent e) {
	            
	            Text text = (Text)e.getSource();
	            
	            final String old = text.getText();
	            String newString = old.substring(0, e.start) + e.text + old.substring(e.end);
	            
	            try
	            {
	                Integer.parseInt(newString);
	            }
	            catch(NumberFormatException ex)
	            {
	                e.doit = false;
	            }
	            
	            System.out.println(newString);
	        }
	    });
		
		Button endTimerButton = new Button(shlWindowsSleeper, SWT.NONE);
		endTimerButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				component.endTimer();
				lblTimeSetFor.setText("Timer not set");
			}
		});
		endTimerButton.setBounds(382, 109, 75, 25);
		endTimerButton.setText("End timer");
		
		
		
		Button btnUntilTime = new Button(shlWindowsSleeper, SWT.CHECK);
		btnUntilTime.setBounds(173, 48, 93, 31);
		btnUntilTime.setText("Until this time");
		
		Button btnUntilAllotedTime = new Button(shlWindowsSleeper, SWT.CHECK);
		btnUntilAllotedTime.setText("Until alloted time");
		btnUntilAllotedTime.setBounds(173, 106, 115, 25);
		
		btnUntilTime.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				btnUntilAllotedTime.setSelection(false);
			}
		});
		
		btnUntilAllotedTime.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				btnUntilTime.setSelection(false);
			}
		});
		
		Label lblHr = new Label(shlWindowsSleeper, SWT.NONE);
		lblHr.setBounds(119, 111, 23, 15);
		lblHr.setText("HR");
		
		Label lblMin = new Label(shlWindowsSleeper, SWT.NONE);
		lblMin.setBounds(114, 138, 28, 15);
		lblMin.setText("MIN");
		
		Button btnStart = new Button(shlWindowsSleeper, SWT.NONE);
		btnStart.setBounds(28, 194, 75, 25);
		btnStart.setText("Start timer");
		
		Label lblTimeSetFor_1 = new Label(shlWindowsSleeper, SWT.NONE);
		lblTimeSetFor_1.setBounds(382, 56, 66, 15);
		lblTimeSetFor_1.setText("Time set for:");
		btnStart.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				if(btnUntilTime.getSelection()) {
					
					LocalTime now = LocalTime.now();
					LocalTime end = LocalTime.of(dateTime.getHours(), dateTime.getMinutes(), 00);
					
					long hours = ChronoUnit.HOURS.between(now, end);
					long mins = ChronoUnit.MINUTES.between(now, end);
					
					int finalTime = (int) ((hours * 3600) + (mins * 60)); 
					component.startTimer(finalTime);
					lblTimeSetFor.setText(end.toString());
					
				}else if(btnUntilAllotedTime.getSelection()) {
					int hours = 0;
					int mins = 0;
					
					if(hour.getText() == "" && minutes.getText() == "") {
						dialog.setText("Error");
						dialog.setMessage("Please enter values for HR and MIN");
						dialog.open();
					}else if(minutes.getText() == "") {
						hours = Integer.parseInt(hour.getText());
						runTimerComponent(hours, mins);
						LocalTime now = LocalTime.now().plus(hours, ChronoUnit.HOURS).plus(mins, ChronoUnit.MINUTES);
						lblTimeSetFor.setText(now.toString());
					}else if(minutes.getText() == "") {
						mins = Integer.parseInt(minutes.getText());
						runTimerComponent(hours, mins);
						LocalTime now = LocalTime.now().plus(hours, ChronoUnit.HOURS).plus(mins, ChronoUnit.MINUTES);
						lblTimeSetFor.setText(now.toString());
					}else {
						hours = Integer.parseInt(hour.getText());
						mins = Integer.parseInt(minutes.getText());
						runTimerComponent(hours, mins);
						LocalTime now = LocalTime.now().plus(hours, ChronoUnit.HOURS).plus(mins, ChronoUnit.MINUTES);
						lblTimeSetFor.setText(now.toString());
					}
					
				}else {
					dialog.setText("Error");
					dialog.setMessage("Please select a method of timer conclusion.");
					dialog.open();
				}
			}
		});

	}
	
	public void runTimerComponent(int hours, int mins) {
		int finalTime = (hours * 3600) + (mins * 60); 
		component.startTimer(finalTime);
	}
}
