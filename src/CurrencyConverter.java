import com.trolltech.qt.core.QRect;
import com.trolltech.qt.core.Qt;
import com.trolltech.qt.gui.QApplication;
import com.trolltech.qt.gui.QComboBox;
import com.trolltech.qt.gui.QHBoxLayout;
import com.trolltech.qt.gui.QLabel;
import com.trolltech.qt.gui.QLineEdit;
import com.trolltech.qt.gui.QMessageBox;
import com.trolltech.qt.gui.QPushButton;
import com.trolltech.qt.gui.QVBoxLayout;
import com.trolltech.qt.gui.QWidget;

/**
 * A basic currency converter
 * @author Mark Holland, Álvaro Ponce Arévalo
 * 2013
 */

public class CurrencyConverter extends QWidget {

    QLineEdit startEditText;
    QLineEdit endEditText;
    QComboBox startCombo;
    QComboBox endCombo;
    QPushButton convertButton;
    QPushButton reverseButton;
    int convertedValue;
    
    //Layout variables
    public QHBoxLayout globalHorizontalLayout;
    public QHBoxLayout horizontalLayout;
    public QVBoxLayout verticalLayout;
    
    //Currency rates
    public static final double USDxGBP = 0.65692;
    public static final double USDxEUR = 0.77385;
    public static final double USDxAUD = 0.95900;
    public static final double USDxCAD = 1.01380;
    public static final double GBPxUSD = 1.52225;
    public static final double GBPxEUR = 1.17799;
    public static final double GBPxAUD = 1.45984;
    public static final double GBPxCAD = 1.54326;
    public static final double EURxUSD = 1.29224;
    public static final double EURxGBP = 0.84890;
    public static final double EURxAUD = 1.23926;
    public static final double EURxCAD = 1.31007;
    public static final double AUDxUSD = 1.04275;
    public static final double AUDxGBP = 0.68501;
    public static final double AUDxEUR = 0.80693;
    public static final double AUDxCAD = 1.05714;
    public static final double CADxUSD = 0.98639;
    public static final double CADxGBP = 0.64798;
    public static final double CADxEUR = 0.76332;
    public static final double CADxAUD = 0.94595;

    
    
    public CurrencyConverter() {

        setWindowTitle("Currency Converter");

        //Set up window
        move(300, 300);
        
        initUI();
        
        show();
    }
    
    private void initUI() {

    	//Initialize widgets
    	startEditText = new QLineEdit(this);
        endEditText = new QLineEdit(this);
        convertButton = new QPushButton(this);
        reverseButton = new QPushButton(this);
        
        //Initialize layouts
        globalHorizontalLayout = new QHBoxLayout(this);
        horizontalLayout = new QHBoxLayout();
        verticalLayout = new QVBoxLayout();
        
        //Initialize both combo boxes
        startCombo = new QComboBox(this);
        endCombo = new QComboBox(this);
        //Add values to startComboBox
        startCombo.addItem("GBP");
        startCombo.addItem("USD");
        startCombo.addItem("EUR");
        startCombo.addItem("AUD");
        startCombo.addItem("CAD");
        //Add values to endComboBox
        endCombo.addItem("GBP");
        endCombo.addItem("USD");
        endCombo.addItem("EUR");
        endCombo.addItem("AUD");
        endCombo.addItem("CAD");
        
        //Align ui components
        startEditText.setText("1.0");
        convertButton.setText("Convert");
        reverseButton.setText("<>");
        
        //Layouts
        horizontalLayout.addWidget(startEditText);
        horizontalLayout.addWidget(startCombo);
        horizontalLayout.addWidget(reverseButton);
        horizontalLayout.addWidget(endCombo);
        horizontalLayout.addWidget(endEditText);
        verticalLayout.addLayout(horizontalLayout);
        verticalLayout.addWidget(convertButton);
        //Add to global layout
        globalHorizontalLayout.addLayout(verticalLayout);
        
        //Connect buttons
        convertButton.clicked.connect(this,"onClickConvert()");
        reverseButton.clicked.connect(this,"onClickReverse()");
    }
    
    /**
     * Converts a value in a currency to the selected new currency
     * The current and new currency is provided by the combo boxes
     * A non-numeric input is treated with an error message
     * A negative number is corrected
     */
    private void onClickConvert(){
    	String start = startCombo.currentText();
    	String end = endCombo.currentText();
    	
    	try{
    		double value = Double.parseDouble(startEditText.text());

    		//Fix negative entry
    		if(value < 0){
    			value = 0 - value;
    			startEditText.setText(""+value);
    		}	
    	
    		if(start.equals(end))
    			endEditText.setText(startEditText.text());
    		else{
    			if(start.equals("USD")){
    				if(end.equals("GBP"))
    					value *= USDxGBP;
    				if(end.equals("EUR"))
    					value *= USDxEUR;
    				if(end.equals("AUD"))
    					value *= USDxAUD;
    				if(end.equals("CAD"))
    					value *= USDxCAD;
    			}//end if USD
    			if(start.equals("GBP")){
    				if(end.equals("USD"))
    					value *= GBPxUSD;
    				if(end.equals("EUR"))
    					value *= GBPxEUR;
    				if(end.equals("AUD"))
    					value *= GBPxAUD;
    				if(end.equals("CAD"))
    					value *= GBPxCAD;
    			}//end if GBP
    			if(start.equals("EUR")){
    				if(end.equals("USD"))
    					value *= EURxUSD;
    				if(end.equals("GBP"))
    					value *= EURxGBP;
    				if(end.equals("AUD"))
    					value *= EURxAUD;
    				if(end.equals("CAD"))
    					value *= EURxCAD;
    			}//end if EUR
    			if(start.equals("AUD")){
    				if(end.equals("USD"))
    					value *= AUDxUSD;
    				if(end.equals("GBP"))
    					value *= AUDxGBP;
    				if(end.equals("EUR"))
    					value *= AUDxEUR;
    				if(end.equals("CAD"))
    					value *= AUDxCAD;
    			}//end if AUD
    			if(start.equals("CAD")){
    				if(end.equals("USD"))
    					value *= CADxUSD;
    				if(end.equals("GBP"))
    					value *= CADxGBP;
    				if(end.equals("EUR"))
    					value *= CADxEUR;
    				if(end.equals("AUD"))
    					value *= CADxAUD;
    			}//end if CAD
    		}//end else
    		
    		//Set converted value
        	endEditText.setText(""+value);
        	
    	}//end try
    	catch(NumberFormatException e){
    		QMessageBox.critical(this, "Error", "Invalid input format,\nOnly numbers are allowed");
    	}
    }
    
    //Inverts the selected currencies and then realizes the conversion
    public void onClickReverse(){
    	int startIndex = startCombo.currentIndex();
    	
    	startCombo.setCurrentIndex(endCombo.currentIndex());
    	endCombo.setCurrentIndex(startIndex);
    	
    	onClickConvert();
    }
    
    public static void main(String[] args) {
        QApplication.initialize(args);
        new CurrencyConverter();
        QApplication.exec();
    }
}