/*
    This file is part of Foobar.

    Foobar is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Foobar is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Foobar.  If not, see <https://www.gnu.org/licenses/>.
 */
package application;

import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.Set;


import com.opencsv.CSVWriter;
import com.sun.javafx.scene.control.skin.TableViewSkin;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.css.PseudoClass;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableRow;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Main extends Application {
	
	static Boolean check = false;
	
	Stage window;
	Scene scene1,scene2,scene3,sceneThree;
	private HBox hbox2;
	private FileChooser fc;
	private Button browser;
	private File file;
	private Pagination pagination;
	private Pagination pagination2;
	private Pagination paginationThree;
	
	private TableView<ObservableList> tableview;
	private ObservableList<ObservableList> data;
	private ObservableList<ObservableList> data3;
	private ObservableList<ObservableList> orgdata;
	private ObservableList<ObservableList> cngdata;
	
	
	private ObservableList<ObservableList> errdata;
	private ObservableList<String> row;
	private ObservableList<String> row3;
	
	private ObservableList<String> cngrow;
	private ObservableList<String> orgrow;
	private ObservableList<String> err2row;
	
	private String fileName;
	private TableColumn combcol2;
	
	private AnchorPane anchor;
	private AnchorPane anchor2;
	
	private List<String[]> dataList ;
	private List<String[]> dataList3 ;
	private List<String[]> orgDataList;
	private List<String[]> cngDataList;
	private List<String[]> errDataList;
	
	
	private int comboNo;
	private List<String> comboitemSeqList ;
	private VBox vbox;
	private VBox vboxThree;
	private HBox s2hbox;
	private HBox testbox, nexthbox2, testbox2;
	private VBox bottomHbox;
	private String orgcngPath;
	private HBox s2hbox2;
	private int colCount;
	
	private TableView<ObservableList> orgtableview;
	private TableView<ObservableList> cngtableview;
	private int page;
	private TableRow<ObservableList> sorgRow;
	private TableRow<ObservableList> scngRow;
	MainController mc = new MainController();
	private String errFile;
	File file3;
	private String csvFileName;
	private int pageCount, pageCount1;
	private Label botinfo, botinfo2, botinfo3, botinfo4;
	private StackPane sp;
	private Double tablewidth;
	private Stage stage;
	@Override
	public void start(Stage primaryStage) {
		
		stage = primaryStage;
		
		window = primaryStage;
		tableview = new TableView<>();
		orgtableview = new TableView<>();
		cngtableview = new TableView<>();
		tableview.setPlaceholder(new Label("선택된 CSV 파일이 없습니다."));
		data = FXCollections.observableArrayList();
		row = FXCollections.observableArrayList();
		
		data3 = FXCollections.observableArrayList();
		row3 = FXCollections.observableArrayList();
		
		errdata = FXCollections.observableArrayList();
		tablewidth = 0.0;
		
		// 창크기 및 타이틀 설정
		window.getIcons().add(new Image(Main.class.getResourceAsStream("icon.jpg")));
		window.setTitle("공공데이터 용어형식 표준화 정비도구");
		
		
		window.setWidth(1024);
		window.setHeight(680);
		data.add(row);
		
		// 변수명 : textField 
		// 파일 열기 실행 후, 파일의 경로와 파일명을 보여준다.
		TextField textField = new TextField();
		textField.setDisable(true);
		textField.setMinWidth(300);
		
		//여부 컬럼의 데이터를 중복없이 보여주는 TextField
		TextField txf = new TextField();
		txf.setMinWidth(900);
		
		dataList = new ArrayList<String[]>();
		dataList3 = new ArrayList<String[]>();
		
		
		//파일 선택하는 기능
		// All Files *.*은 모든 파일 
		// TEXT *.txt 등과 같은 형식으로 설정가능
		fc = new FileChooser();
		fc.getExtensionFilters().addAll(new ExtensionFilter("All Files", "*.*"));
		
		//버튼 
		browser = new Button("열기");
		browser.setPadding(new Insets(3, 3, 3, 3));
		
		//버튼클릭 시, 이벤트 발생 후 내용 실행
		browser.setOnAction(e -> {
			try {
				
				
				
				tableview.getColumns().clear();
				data.removeAll(data);
				row.removeAll(row);
				

				//파일 열기창 오픈
				file = fc.showOpenDialog(window);
				dataList.removeAll(dataList);
				
				//csv data를 읽어 컬렉션에 저장
				int countt =0;
				for (String[] data : mc.readerCSV(file.getPath())) {

					dataList.add(data);
					countt++;
				}


			
				//첫화면 테이블 컬럼 생성
				TableColumn col;
				int coldiv = 1;

				for (int i = 0; i < dataList.get(0).length; i++) {
					
					// coldiv 테이블에 몇개의 컬럼까지 보여줄지
					if(dataList.get(0).length>=10) {
						coldiv = 10;
					}else {
						coldiv = dataList.get(0).length;
					}
					
					
					final int j = i;
					
					//컬럼 i번째마다 dataList에서 컬럼명을 지정
					col = new TableColumn(dataList.get(0)[i]);
					
					//테이블에 각 컬럼에 데이터 넣어서 보여주기
					col.setCellValueFactory(
							new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
								public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
									return new SimpleStringProperty(param.getValue().get(j).toString());
								}
							});
					
					
					//컬럼사이즈 조절
					col.prefWidthProperty().bind(tableview.widthProperty().divide(coldiv));
					

					//테이블에 컬럼을 넣기
					tableview.getColumns().addAll(col);

				}

				// 하나의 데이터를 row라는 리스트에 집어넣은뒤 하나의 row는 행이 되며, 그 행들을 data에 집어넣는다.
				for (int i = 1; i < dataList.size(); i++) {
					row = FXCollections.observableArrayList();
					for (int j = 0; j < dataList.get(i).length; j++) {

						row.add(dataList.get(i)[j]);


					}
					data.add(row);
				}
				

				tableview.prefHeightProperty().bind(primaryStage.heightProperty());
				tableview.prefWidthProperty().bind(primaryStage.widthProperty());
				
				//테이블에 data를 넣기
				tableview.setItems(data);
				textField.setText(file.getPath());
				textField.setDisable(true);
				tableview.getColumns().removeAll();
				browser.setDisable(true);
				
			}catch(Exception eev) {
				
			}
			
			
	        
		});
		
		// 이전(첫화면으로 이동)
		Button prvBtn = new Button("이전");
		prvBtn.setPadding(new Insets(3, 3, 3, 3));
		prvBtn.setOnAction(event->{
			window.setScene(scene1);
		});
		
		
		// 행 번호 선택
		ComboBox<Integer> headerCombo = new ComboBox<Integer>(
				FXCollections.observableArrayList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15));
		// ComboBox 첫번째 값 선택
		headerCombo.getSelectionModel().selectFirst();
		
		
		// ComboBox Font 설정
		headerCombo.setStyle("-fx-font: 13px \"Serif\";");
		
		//Button 생성
		Button headerBtn = new Button("시작");
		headerBtn.setPadding(new Insets(5.5, 5.5, 5.5, 5.5));
		headerBtn.setOnAction(e -> {
			try {
				vbox.getChildren().remove(pagination);
				vbox.getChildren().remove(bottomHbox);
				vbox.getChildren().remove(botinfo3);
				vbox.getChildren().remove(botinfo4);
				vbox.getChildren().remove(testbox2);
				check = true;
				
				hbox2.getChildren().clear();
				
				tableview.getColumns().removeAll(tableview.getColumns());
				data.removeAll(data);
				data.clear();
				row.removeAll(row);
				row.clear();

				dataList.removeAll(dataList);
				
				
				comboNo = headerCombo.getSelectionModel().getSelectedItem();
				ObservableList<ObservableList> comb = FXCollections.observableArrayList();
				ObservableList<String> comb2 = FXCollections.observableArrayList("A", "B", "C");
				comb.add(comb2);
				
				for (String[] data : mc.readerCSV(file.getPath())) {
					dataList.add(data);
				}

				
				if(comboNo<dataList.size()) {
					for (int i = comboNo + 1; i < dataList.size(); i++) {
						row = FXCollections.observableArrayList();
						for (int j = 0; j < dataList.get(i).length; j++) {
							row.add(dataList.get(i)[j]);
						}
						data.add(row);
					}
					
					
					if(data.size()%rowsPerPage()==0) {
						if(data.size()!=0) {
							pagination = new Pagination((data.size() / rowsPerPage()), 0);
						}else {
							pagination = new Pagination(1,0);
						}
						
					} else {
						pagination = new Pagination((data.size() / rowsPerPage() + 1), 0);
					}
					
					
			        pagination.setPageFactory(new Callback<Integer, Node>() {
			            @Override
			            public Node call(Integer pageIndex) {
			                if (pageIndex > data.size() / rowsPerPage() + 1) {
			                    return null;
			                } else {
			                    return createPage2(pageIndex);
			                }
			            }
			        });
			        
			        anchor = new AnchorPane();
			        AnchorPane.setTopAnchor(pagination, 10.0);
			        AnchorPane.setRightAnchor(pagination, 10.0);
			        AnchorPane.setBottomAnchor(pagination, 10.0);
			        AnchorPane.setLeftAnchor(pagination, 10.0);
			        anchor.getChildren().addAll(pagination);
			        
			        vbox.getChildren().remove(tableview);
			        vbox.getChildren().addAll(pagination, bottomHbox);
			        
			        
			        
				} else {
					
					for (int i = 1; i < dataList.size(); i++) {
						row = FXCollections.observableArrayList();
						for (int j = 0; j < dataList.get(i).length; j++) {
							row.add(dataList.get(i)[j]);

						}
						data.add(row);
					}
					
					if(data.size()%rowsPerPage()==0) {
						pagination = new Pagination((data.size() / rowsPerPage()), 0);
					} else {
						pagination = new Pagination((data.size() / rowsPerPage() + 1), 0);
					}
					
					
			        pagination.setPageFactory(new Callback<Integer, Node>() {
			            @Override
			            public Node call(Integer pageIndex) {
			                if (pageIndex > data.size() / rowsPerPage() + 1) {
			                    return null;
			                } else {
			                    return createPage(pageIndex);
			                }
			            }
			        });
			        
			        anchor = new AnchorPane();
			        AnchorPane.setTopAnchor(pagination, 10.0);
			        AnchorPane.setRightAnchor(pagination, 10.0);
			        AnchorPane.setBottomAnchor(pagination, 10.0);
			        AnchorPane.setLeftAnchor(pagination, 10.0);
			        anchor.getChildren().addAll(pagination);
			       
			        
			        vbox.getChildren().remove(tableview);
			     
			        vbox.getChildren().addAll(pagination, bottomHbox);
					
			        
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("주의");
					alert.setHeaderText("행의 개수보다 숫자가 큽니다.");
					alert.setContentText(
							"헤더 번호가 행의 개수보다 큽니다. 행의 개수를 체크하신 뒤 숫자를 선택해주세요.");

					alert.showAndWait();
				}
				
				
				
				
				
			}catch (Exception ee){
				
			}
		});
		
		Button homeBtn = new Button("홈");
    	homeBtn.setOnAction(evnet->{
    		dataList3.clear();
    		window.setScene(scene1);
    	});
    	Button prvBtn2 = new Button("이전");
    	prvBtn2.setOnAction(e -> {
    		dataList3.clear();
    		window.setScene(scene2);
    		
    	});
		
		Button orgDownloadCSV = new Button("CSV다운로드");
		orgDownloadCSV.setPadding(new Insets(3, 3, 3, 3));
		orgDownloadCSV.setOnAction(event -> {
			if(check==true) {
				FileChooser fcCSV= new FileChooser();
				FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
				fcCSV.getExtensionFilters().add(extFilter);
				fcCSV.setInitialFileName(csvFileName);
				File filefc = fcCSV.showSaveDialog(window);
				
				FileWriter filewriter = null;
				CSVWriter writer = null;
				try {
					filefc.createNewFile();
					filewriter = new FileWriter(filefc);
					writer = new CSVWriter(filewriter);
					String dataRow[] = new String[row.size()];
					for(int i=0;i<dataList.get(0).length;i++) {
						dataRow[i]=dataList.get(0)[i];
					}
					writer.writeNext(dataRow);
					
					
					for (int i = 0; i < data.size(); i++) {
						for (int j = 0; j < data.get(i).size(); j++) {
							dataRow[j] = (String) data.get(i).get(j);
						}
						writer.writeNext(dataRow);
					}
					
					writer.flush();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						if (writer != null)
							writer.close();
						if (filewriter != null)
							filewriter.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}

				}
			}

		});
		
		Button cngDownloadCSV = new Button("CSV다운로드");
		cngDownloadCSV.setPadding(new Insets(3, 3, 3, 3));
		cngDownloadCSV.setOnAction(event -> {
			if(check==true) {
				FileChooser fcCSV= new FileChooser();
				FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
				fcCSV.getExtensionFilters().add(extFilter);
				fcCSV.setInitialFileName(csvFileName+"_cng");
				File filefc = fcCSV.showSaveDialog(window);
				
				FileWriter filewriter = null;
				CSVWriter writer = null;
				try {
					filefc.createNewFile();
					filewriter = new FileWriter(filefc);
					writer = new CSVWriter(filewriter);
					String dataRow[] = new String[row.size()];
					for(int i=0;i<dataList.get(0).length;i++) {
						dataRow[i]=dataList.get(0)[i];
					}
					writer.writeNext(dataRow);
					
					
					for (int i = 0; i < cngdata.size(); i++) {
						for (int j = 0; j < cngdata.get(i).size(); j++) {
							dataRow[j] = (String) cngdata.get(i).get(j);
						}
						writer.writeNext(dataRow);
					}
					
					writer.flush();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						if (writer != null)
							writer.close();
						if (filewriter != null)
							filewriter.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}

				}
			}

		});
		
		
		Button errDownloadCSV = new Button("CSV다운로드");
		errDownloadCSV.setPadding(new Insets(3, 3, 3, 3));
		errDownloadCSV.setOnAction(event -> {
			if(check==true) {

				FileChooser fcCSV= new FileChooser();
				FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
				fcCSV.getExtensionFilters().add(extFilter);
				fcCSV.setInitialFileName(csvFileName+"_err");
				File filefc = fcCSV.showSaveDialog(window);
				
				FileWriter filewriter = null;
				CSVWriter writer = null;
				try {
					filefc.createNewFile();
					filewriter = new FileWriter(filefc);
					writer = new CSVWriter(filewriter);
					String dataRow[] = new String[dataList3.get(0).length];
					for(int i=0;i<dataList3.get(0).length;i++) {
						dataRow[i]=dataList3.get(0)[i];
					}
					writer.writeNext(dataRow);
					
					for (int i = 0; i < data3.size(); i++) {
							for (int j = 0; j < data3.get(i).size(); j++) {
								dataRow[j] = (String) data3.get(i).get(j);
							}
							writer.writeNext(dataRow);
						}
					
					
					writer.flush();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						if (writer != null)
							writer.close();
						if (filewriter != null)
							filewriter.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}

				}
			}

		});
		

		
		// Nodes
				Text txtstate = new Text();
				txtstate.setFont(Font.font(18));
				txtstate.setFill(Color.BLUE);
		
		// ProgressBar
				ProgressBar pBar = new ProgressBar(0);
				pBar.indeterminateProperty().addListener(new ChangeListener<Boolean>() {
		
					@Override
					public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
						if (t1) {
							txtstate.setText("계산 중입니다.");
						} else {
							txtstate.setText("처리 중입니다.");
						}
		
					}
		
				});
		
				pBar.progressProperty().addListener(new ChangeListener<Number>() {
		
					@Override
					public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
						if (t1.doubleValue() == 1) {
							txtstate.setText("Work Done");
							txtstate.setFill(Color.GREEN);
							
							window.setScene(scene2);
							
						}
					}
				});
		
		// ProgressIndicator
				ProgressIndicator pind = new ProgressIndicator(0);
				pind.indeterminateProperty().addListener(new ChangeListener<Boolean>() {
		
					@Override
					public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
		
						if (t1) {
							txtstate.setText("계산 중입니다.");
							txtstate.setFill(Color.BLUE);
						} else {
							txtstate.setText("처리 중입니다.");
						}
		
					}
		
				});
		
		
		pageCount1 = 0;
		
		Button saveBtn = new Button("진단 및 정비");
		saveBtn.setPadding(new Insets(3, 3, 3, 3));
		saveBtn.setOnAction(event -> {
			
			if (check == false) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("주의");
				alert.setHeaderText("먼저 다시불러오기 기능을 먼저 실행해주세요.");
				alert.setContentText(
						"해당 기능은 다시불러오기 기능을 선택한 후 헤더명을 매칭시키고 이용합니다. 변경 사항이 없을 경우, 0을 선택 후 다시불러오기 버튼을 클릭한 뒤, [저장] 버튼을 클릭합니다.");

				alert.showAndWait();
			}else {
				headerCombo.setDisable(true);
				headerBtn.setDisable(true);
				if(pageCount1==0) {
					Task task = taskCreator(dataList.size()-1-comboNo);
					pBar.progressProperty().unbind();
					pBar.progressProperty().bind(task.progressProperty());
					pind.progressProperty().unbind();
					pind.progressProperty().bind(task.progressProperty());
					new Thread(task).start();
					
					window.setScene(scene3);

					

					
			        window.setTitle("공공데이터 용어형식 표준화 정비도구  [ 파일명 : " + file.getName() + " ]");
					
					pageCount1++;
				}else {
					window.setScene(scene2);
				}
				
				
				
				
				
				
				
				
		       

				
				
			}
		});
		
		
		
		
		pageCount = 0;
		//2페이지 -> 3페이지 넘어가는 버튼
		Button btnNext = new Button("저장");
		btnNext.setOnAction(event->{
			
			vboxThree.getChildren().removeAll(vboxThree, paginationThree, testbox, nexthbox2);
			
			
			data3.clear();
			
			if(pageCount==0) {
				file3 = new File(errFile);

				
				for (int i = 1; i < dataList3.size(); i++) {
					row3 = FXCollections.observableArrayList();
					for (int j = 0; j < dataList3.get(i).length; j++) {

						row3.add(dataList3.get(i)[j]);


					}
					data3.add(row3);
				}
				
				if(data3.size()%rowsPerPage()==0) {
					if(data3.size()==0) {
						paginationThree = new Pagination(1, 0);
					}else {
						paginationThree = new Pagination((data3.size() / rowsPerPage()), 0);
					}
					
					
				} else {
					paginationThree = new Pagination((data3.size() / rowsPerPage() + 1), 0);
				}
				
				paginationThree.setPageFactory(new Callback<Integer, Node>() {
		            @Override
		            public Node call(Integer pageIndex) {
		                if (pageIndex > data3.size() / rowsPerPage() + 1) {
		                    return null;
		                } else {
		                    return createPageThree(pageIndex);
		                }
		            }
		        });
				pageCount++;
			}
			
			
			anchor2 = new AnchorPane();
	        AnchorPane.setTopAnchor(paginationThree, 0.0);
	        AnchorPane.setRightAnchor(paginationThree, 0.0);
	        AnchorPane.setBottomAnchor(paginationThree, 0.0);
	        AnchorPane.setLeftAnchor(paginationThree, 0.0);
	        anchor2.getChildren().addAll(paginationThree);
	        
	        String str = file.getName();
			String fileName = str.substring(0,str.length()-4);
	        
	        
	        Label orgFileName = new Label("원본  :  " + fileName + ".csv");
	        Label cngFileName = new Label("표준화 완료   :  " + fileName + "_cng.csv");
	        Label msgFileName = new Label("정비불가 리스트 :  " + fileName + "_msg.csv");
	        
	        orgFileName.setFont(new Font(15));
	        cngFileName.setFont(new Font(15));
	        msgFileName.setFont(new Font(15));
	        
	        orgFileName.setId("title");
	        cngFileName.setId("title");
	        msgFileName.setId("title");
	        
	        orgFileName.setPadding(new Insets(0,115,0,0));
	        cngFileName.setPadding(new Insets(0,40,0,0));
	        msgFileName.setPadding(new Insets(0,18,0,0));
	        
	        HBox hboxThree1 = new HBox();
	        hboxThree1.setPadding(new Insets(5, 0, 0, 0));
	        hboxThree1.getChildren().addAll(orgFileName, orgDownloadCSV);
	        
	        HBox hboxThree2 = new HBox();
	        hboxThree2.setPadding(new Insets(10, 0, 0, 0));
	        hboxThree2.getChildren().addAll(cngFileName, cngDownloadCSV);
	        
	        HBox hboxThree3 = new HBox();
	        hboxThree3.setPadding(new Insets(10, 0, 0, 0));
	        hboxThree3.getChildren().addAll(msgFileName, errDownloadCSV);
	        
	        VBox vb1 = new VBox(15);
	        vb1.setPadding(new Insets(27,0,0,50));
	        vb1.getChildren().addAll(hboxThree1, hboxThree2, hboxThree3);
	        
	        
	        int biznocount = 0;
            int numcount = 0;
            int yncount = 0;
            int datecount = 0;
            int telcount = 0;
            int zipcount = 0;
            
            for(int i=0; i<data3.size();i++) {
            	if(data3.get(i).get(3).equals("숫자")&&data3.get(i).get(7).equals("")) {
                	numcount++;
                }else if(data3.get(i).get(3).equals("여부") && data3.get(i).get(7).equals("")) {
                	yncount++;
                }else if(data3.get(i).get(3).toString().length()>5 && data3.get(i).get(7).equals("")) {
                	datecount++;
                }else if(data3.get(i).get(3).equals("전화번호") && data3.get(i).get(7).equals("")) {
                	telcount++;
                }else if(data3.get(i).get(3).equals("우편번호") && data3.get(i).get(7).equals("")) {
                	zipcount++;
                }else if(data3.get(i).get(3).equals("사업자번호") && data3.get(i).get(7).equals("")) {
                	biznocount++;
                }
            }
            
            
            
            ObservableList<CngData> popdata = FXCollections.observableArrayList(
            		new CngData("변경 " + Integer.toString(numcount) + " 건","변경 " +  Integer.toString(yncount) + " 건", "변경 " + Integer.toString(datecount) + " 건", "변경 " + Integer.toString(telcount) + " 건", "변경 " + Integer.toString(zipcount) + " 건", "변경 " + Integer.toString(biznocount) + " 건")
            		);
            
            
            TableView tbpop = new TableView();
            TableColumn popcol1 = new TableColumn("숫자");
            popcol1.setCellValueFactory(
            		new PropertyValueFactory<CngData, String>("numcount")
            		);
            TableColumn popcol2 = new TableColumn("여부");
            popcol2.setCellValueFactory(
            		new PropertyValueFactory<CngData, String>("yncount")
            		);
            TableColumn popcol3 = new TableColumn("날짜");
            popcol3.setCellValueFactory(
            		new PropertyValueFactory<CngData, String>("datecount")
            		);
            TableColumn popcol4 = new TableColumn("전화번호");
            popcol4.setCellValueFactory(
            		new PropertyValueFactory<CngData, String>("telcount")
            		);
            TableColumn popcol5 = new TableColumn("우편번호");
            popcol5.setCellValueFactory(
            		new PropertyValueFactory<CngData, String>("zipcount")
            		);
            TableColumn popcol6 = new TableColumn("사업자번호");
            popcol6.setCellValueFactory(
            		new PropertyValueFactory<CngData, String>("biznocount")
            		);
            
            popcol1.setCellFactory(new Callback<TableColumn, TableCell>() {

                @Override
                public TableCell call(TableColumn param) 
                {
                    return new TableCell<CngData, String>() 
                    {
                        @Override
                        public void updateItem(String item, boolean empty) {
                            super.updateItem(item, empty);

                            if(isEmpty())
                            {
                                setText("");
                            }
                            else
                            {

                                //setTextFill(Color.BLUE);
                                setFont(Font.font ("Verdana", 13));
                                setText(item);
                            }
                        }
                    };
                }
            });
            popcol2.setCellFactory(new Callback<TableColumn, TableCell>() {

                @Override
                public TableCell call(TableColumn param) 
                {
                    return new TableCell<CngData, String>() 
                    {
                        @Override
                        public void updateItem(String item, boolean empty) {
                            super.updateItem(item, empty);

                            if(isEmpty())
                            {
                                setText("");
                            }
                            else
                            {

                               // setTextFill(Color.BLUE);
                                setFont(Font.font ("Verdana", 13));
                                setText(item);
                            }
                        }
                    };
                }
            });
            popcol3.setCellFactory(new Callback<TableColumn, TableCell>() {

                @Override
                public TableCell call(TableColumn param) 
                {
                    return new TableCell<CngData, String>() 
                    {
                        @Override
                        public void updateItem(String item, boolean empty) {
                            super.updateItem(item, empty);

                            if(isEmpty())
                            {
                                setText("");
                            }
                            else
                            {

//                                setTextFill(Color.BLUE);
                                setFont(Font.font ("Verdana", 13));
                                setText(item);
                            }
                        }
                    };
                }
            });
            popcol4.setCellFactory(new Callback<TableColumn, TableCell>() {

                @Override
                public TableCell call(TableColumn param) 
                {
                    return new TableCell<CngData, String>() 
                    {
                        @Override
                        public void updateItem(String item, boolean empty) {
                            super.updateItem(item, empty);

                            if(isEmpty())
                            {
                                setText("");
                            }
                            else
                            {

//                                setTextFill(Color.BLUE);
                                setFont(Font.font ("Verdana", 13));
                                setText(item);
                            }
                        }
                    };
                }
            });
            popcol5.setCellFactory(new Callback<TableColumn, TableCell>() {

                @Override
                public TableCell call(TableColumn param) 
                {
                    return new TableCell<CngData, String>() 
                    {
                        @Override
                        public void updateItem(String item, boolean empty) {
                            super.updateItem(item, empty);

                            if(isEmpty())
                            {
                                setText("");
                            }
                            else
                            {

//                                setTextFill(Color.BLUE);
                                setFont(Font.font ("Verdana", 13));
                                setText(item);
                            }
                        }
                    };
                }
            });
            popcol6.setCellFactory(new Callback<TableColumn, TableCell>() {

                @Override
                public TableCell call(TableColumn param) 
                {
                    return new TableCell<CngData, String>() 
                    {
                        @Override
                        public void updateItem(String item, boolean empty) {
                            super.updateItem(item, empty);

                            if(isEmpty())
                            {
                                setText("");
                            }
                            else
                            {

                                setFont(Font.font ("Verdana", 13));
                                setText(item);
                            }
                        }
                    };
                }
            });
            
            
            tbpop.setItems(popdata);
            tbpop.getColumns().addAll(popcol1, popcol2, popcol3, popcol4, popcol5, popcol6);
	        
	        tbpop.setMaxHeight(49.5);
	        tbpop.setMaxWidth(460.5);
	        
	        
	        
	        //변경

	        int biznoerrcount = 0;
            int numerrcount = 0;
            int ynerrcount = 0;
            int dateerrcount = 0;
            int telerrcount = 0;
            int ziperrcount = 0;
            
            for(int i=0; i<data3.size();i++) {
            	if(data3.get(i).get(3).equals("숫자") && data3.get(i).get(7).toString().length()>0) {
            		numerrcount++;
                }else if(data3.get(i).get(3).equals("여부") && data3.get(i).get(7).toString().length()>0) {
                	ynerrcount++;
                }else if(data3.get(i).get(3).toString().length()>5 && data3.get(i).get(7).toString().length()>0) {
                	dateerrcount++;
                }else if(data3.get(i).get(3).equals("전화번호") && data3.get(i).get(7).toString().length()>0) {
                	telerrcount++;
                }else if(data3.get(i).get(3).equals("우편번호") && data3.get(i).get(7).toString().length()>0) {
                	ziperrcount++;
                }else if(data3.get(i).get(3).equals("사업자번호") && data3.get(i).get(7).toString().length()>0) {
                	biznoerrcount++;
                }
            }
            
            
            
            ObservableList<ErrData> poperrdata = FXCollections.observableArrayList(
            		new ErrData("에러 " + Integer.toString(numerrcount) + " 건","에러 " +  Integer.toString(ynerrcount) + " 건", "에러 " + Integer.toString(dateerrcount) + " 건", "에러 " + Integer.toString(telerrcount) + " 건", "에러 " + Integer.toString(ziperrcount) + " 건", "에러 " + Integer.toString(biznoerrcount) + " 건")
            		);
            
            
            TableView errtbpop = new TableView();
            TableColumn errpopcol1 = new TableColumn("숫자");
            errpopcol1.setCellValueFactory(
            		new PropertyValueFactory<ErrData, String>("numcount")
            		);
            TableColumn errpopcol2 = new TableColumn("여부");
            errpopcol2.setCellValueFactory(
            		new PropertyValueFactory<ErrData, String>("yncount")
            		);
            TableColumn errpopcol3 = new TableColumn("날짜");
            errpopcol3.setCellValueFactory(
            		new PropertyValueFactory<ErrData, String>("datecount")
            		);
            TableColumn errpopcol4 = new TableColumn("전화번호");
            errpopcol4.setCellValueFactory(
            		new PropertyValueFactory<ErrData, String>("telcount")
            		);
            TableColumn errpopcol5 = new TableColumn("우편번호");
            errpopcol5.setCellValueFactory(
            		new PropertyValueFactory<ErrData, String>("zipcount")
            		);
            TableColumn errpopcol6 = new TableColumn("사업자번호");
            errpopcol6.setCellValueFactory(
            		new PropertyValueFactory<ErrData, String>("biznocount")
            		);
            
            errpopcol1.setCellFactory(new Callback<TableColumn, TableCell>() {

                @Override
                public TableCell call(TableColumn param) 
                {
                    return new TableCell<ErrData, String>() 
                    {
                        @Override
                        public void updateItem(String item, boolean empty) {
                            super.updateItem(item, empty);

                            if(isEmpty())
                            {
                                setText("");
                            }
                            else
                            {

                                //setTextFill(Color.BLUE);
                                setFont(Font.font ("Verdana", 13));
                                setText(item);
                            }
                        }
                    };
                }
            });
            errpopcol2.setCellFactory(new Callback<TableColumn, TableCell>() {

                @Override
                public TableCell call(TableColumn param) 
                {
                    return new TableCell<ErrData, String>() 
                    {
                        @Override
                        public void updateItem(String item, boolean empty) {
                            super.updateItem(item, empty);

                            if(isEmpty())
                            {
                                setText("");
                            }
                            else
                            {

                               // setTextFill(Color.BLUE);
                                setFont(Font.font ("Verdana", 13));
                                setText(item);
                            }
                        }
                    };
                }
            });
            errpopcol3.setCellFactory(new Callback<TableColumn, TableCell>() {

                @Override
                public TableCell call(TableColumn param) 
                {
                    return new TableCell<ErrData, String>() 
                    {
                        @Override
                        public void updateItem(String item, boolean empty) {
                            super.updateItem(item, empty);

                            if(isEmpty())
                            {
                                setText("");
                            }
                            else
                            {

//                                setTextFill(Color.BLUE);
                                setFont(Font.font ("Verdana", 13));
                                setText(item);
                            }
                        }
                    };
                }
            });
            errpopcol4.setCellFactory(new Callback<TableColumn, TableCell>() {

                @Override
                public TableCell call(TableColumn param) 
                {
                    return new TableCell<ErrData, String>() 
                    {
                        @Override
                        public void updateItem(String item, boolean empty) {
                            super.updateItem(item, empty);

                            if(isEmpty())
                            {
                                setText("");
                            }
                            else
                            {

//                                setTextFill(Color.BLUE);
                                setFont(Font.font ("Verdana", 13));
                                setText(item);
                            }
                        }
                    };
                }
            });
            errpopcol5.setCellFactory(new Callback<TableColumn, TableCell>() {

                @Override
                public TableCell call(TableColumn param) 
                {
                    return new TableCell<ErrData, String>() 
                    {
                        @Override
                        public void updateItem(String item, boolean empty) {
                            super.updateItem(item, empty);

                            if(isEmpty())
                            {
                                setText("");
                            }
                            else
                            {

//                                setTextFill(Color.BLUE);
                                setFont(Font.font ("Verdana", 13));
                                setText(item);
                            }
                        }
                    };
                }
            });
            errpopcol6.setCellFactory(new Callback<TableColumn, TableCell>() {

                @Override
                public TableCell call(TableColumn param) 
                {
                    return new TableCell<ErrData, String>() 
                    {
                        @Override
                        public void updateItem(String item, boolean empty) {
                            super.updateItem(item, empty);

                            if(isEmpty())
                            {
                                setText("");
                            }
                            else
                            {

                                setFont(Font.font ("Verdana", 13));
                                setText(item);
                            }
                        }
                    };
                }
            });
            
            
            errtbpop.setItems(poperrdata);
            errtbpop.getColumns().addAll(errpopcol1, errpopcol2, errpopcol3, errpopcol4, errpopcol5, errpopcol6);
	        
	        errtbpop.setMaxHeight(49.5);
	        errtbpop.setMaxWidth(460.5);
	        
	        
	        
	        
	        VBox vb2 = new VBox(5);
	        vb2.setPadding(new Insets(40, 0, 0, 130));
	        vb2.getChildren().addAll(tbpop, errtbpop);
	        
	        testbox = new HBox(15);
	        testbox.getChildren().addAll(vb1,vb2);
	        
	        
	    	homeBtn.setAlignment(Pos.CENTER);
	    	homeBtn.setId("allBtn");
			prvBtn2.setId("allBtn");
			
			nexthbox2 = new HBox(5);
			nexthbox2.setPadding(new Insets(15,0,0,0));
			nexthbox2.getChildren().addAll(homeBtn, prvBtn2);
			nexthbox2.setAlignment(Pos.CENTER);
	        
	        vboxThree.getChildren().addAll(paginationThree, testbox, nexthbox2);
	       
	        //에러파일 삭제
	        
	        
	        
	        
	        
			window.setScene(sceneThree);
			
			
		});
		
		
		
		
		
		
		

		HBox hbox = new HBox(5);
		hbox.setPadding(new Insets(15, 0, 10, 0));
		hbox.getChildren().addAll(textField, browser);
		
		

		
		
		hbox2 = new HBox(5);
		hbox2.setPadding(new Insets(15, 0, 0, 0));
		
		
		Label rowLabel = new Label("행 번호 선택");
		rowLabel.setPadding(new Insets(5,0,0,0));
		rowLabel.getStyleClass().remove("label");
		rowLabel.getStyleClass().add("myclass");
		
		HBox hbox3 = new HBox(5);
		hbox3.setPadding(new Insets(0, 0, 0, 0));
		hbox3.getChildren().addAll(rowLabel, headerCombo, headerBtn);
		
		
		
		

		
		
		
		//프로그램 재시작 버튼 (초기화)
		final Button restartButton = new Button( "재시작" );
		restartButton.setOnAction( __ ->
	    {
	      primaryStage.close();
	      Platform.runLater( () -> new Main().start( new Stage() ) );
	    } );
		
		
		botinfo = new Label("※ [열기] 버튼을 클릭해 CSV 파일을 선택합니다. 그 후 행번호를 콤보박스에서 선택한 후 [시작]버튼을 클릭합니다.");
		botinfo.setPadding(new Insets(0, 0, 0, 0));
		botinfo.setMaxHeight(Double.MAX_VALUE);
		

		botinfo2 = new Label("컬럼 헤더 아래의 콤보박스에서 데이터타입을 선택한 뒤, [진단 및 정비] 버튼을 클릭해 정비를 시작합니다.");
		botinfo2.setPadding(new Insets(0, 0, 0, 15));
		botinfo2.setMaxHeight(Double.MAX_VALUE);
		
		VBox botBox = new VBox(5);
		botBox.getChildren().addAll(botinfo,botinfo2);
		
		HBox botBtn =new HBox(5);
		botBtn.setPadding(new Insets(5, 0, 15, 0));
		botBtn.getChildren().addAll(saveBtn, restartButton);
		botBtn.setAlignment(Pos.CENTER);
		
		bottomHbox = new VBox(5);
		bottomHbox.setPadding(new Insets(15, 0, 0, 0));
		bottomHbox.getChildren().addAll(botBtn);
		bottomHbox.getChildren().add(botBox);
		bottomHbox.setAlignment(Pos.CENTER);
		
		
		
		
		botinfo3 = new Label("※ [열기] 버튼을 클릭해 CSV 파일을 선택합니다. 그 후 행번호를 콤보박스에서 선택한 후 [시작]버튼을 클릭합니다.");
		botinfo3.setPadding(new Insets(110, 0, 0, 0));
		

		botinfo4 = new Label("컬럼 헤더 아래의 콤보박스에서 데이터타입을 선택한 뒤, [진단 및 정비] 버튼을 클릭해 정비를 시작합니다.");
		botinfo4.setPadding(new Insets(0, 0, 0, 15));
		
		
		FlowPane fp = new FlowPane();
		fp.getChildren().addAll(botinfo3, botinfo4);
		
		testbox2 = new HBox();
		testbox2.getChildren().addAll(fp);

		
		Label s1Header = new Label(" ");
        
		vbox = new VBox();
		vbox.getChildren().addAll(s1Header ,hbox,hbox3,tableview,testbox2);
		vbox.setPadding(new Insets(10, 10, 10, 10));
		vbox.setPrefSize(800, 600);
		
		Label s3Header = new Label("변경 및 오류 내역");
		s3Header.setMaxWidth(Double.MAX_VALUE);
		s3Header.setAlignment(Pos.CENTER);
		s3Header.setId("title");
		s3Header.setPadding(new Insets(0, 0, 10, 0));
		
		vboxThree = new VBox();
		vboxThree.getChildren().addAll(s3Header);
		vboxThree.setPadding(new Insets(10, 10, 10, 10));
		vboxThree.setPrefSize(800, 600);
		

		sp = new StackPane();
		sp.getChildren().addAll(vbox);
		
		sp.setStyle("-fx-background-color: #FFFFFF;");
	
		sp.autosize();
		scene1 = new Scene(sp);


		
		
		browser.setId("allBtn");
		headerBtn.setId("allBtn");
		orgDownloadCSV.setId("allBtn");
		saveBtn.setId("allBtn");
		restartButton.setId("allBtn");
		cngDownloadCSV.setId("allBtn");
		errDownloadCSV.setId("allBtn");
		

		
		s2hbox = new HBox(5);
		s2hbox.setPadding(new Insets(10,0,0,10));

		
		Button preBtn = new Button("이전 페이지로 돌아가기");
		preBtn.setOnAction(e->{
			window.setScene(scene1);
		});
		
		
		s2hbox2 = new HBox(5);
		s2hbox2.setPadding(new Insets(10,10,0,10));
		s2hbox2.isResizable();
		s2hbox2.autosize();
		

		
		ProgressBar pb = new ProgressBar(0);
		ProgressIndicator pi = new ProgressIndicator(0);
		TextField txtf2 = new TextField("2페이지입니다.");
		GridPane gp = new GridPane();

		gp.setConstraints(s2hbox2,0,1);
		
		gp.setAlignment(Pos.CENTER_LEFT);

		gp.getChildren().addAll( s2hbox2);
		gp.autosize();
		gp.setAlignment(Pos.CENTER);
		
		AnchorPane apne = new AnchorPane();
		apne.getChildren().add(s2hbox2);
		apne.autosize();
		
		StackPane spane = new StackPane();
		spane.getChildren().add(s2hbox2);
		spane.autosize();
		spane.isResizable();
		spane.setAlignment(Pos.CENTER);
		
		btnNext.setAlignment(Pos.CENTER);
		btnNext.setId("allBtn");
		prvBtn.setId("allBtn");
		HBox nexthbox = new HBox(5);
		nexthbox.setPadding(new Insets(0,0,0,0));
		nexthbox.getChildren().addAll(btnNext, prvBtn);
		nexthbox.setAlignment(Pos.CENTER);
		
		Region filler = new Region();
		
		
		HBox blank = new HBox(5);
		blank.setPadding(new Insets(0, 482, 0, 0));
		
		GridPane botgp = new GridPane();
		//botgp.setConstraints(blank,0,0);
		botgp.setConstraints(nexthbox,3,0);
		botgp.getChildren().addAll(nexthbox);
		botgp.setAlignment(Pos.CENTER);
		
		
		Label helpinfo3 = new Label("※ 원본의");
		Label helpinfo = new Label("초록색");
		helpinfo.setTextFill(Color.SEAGREEN);
		
		Label helpinfo2 = new Label("글자는 변경 전의 데이터를 나타내며, ");
		Label helpinfo4 = new Label("빨간색");
		helpinfo4.setTextFill(Color.RED);
		
		Label helpinfo5 = new Label("글자는 선택하신 데이터 타입으로 값 판단 여부가 불확실한 경우 입니다. (정비 불가)");
		
		HBox infobox = new HBox(5);
		infobox.setPadding(new Insets(15, 0, 0, 0));
		infobox.getChildren().addAll(helpinfo3, helpinfo, helpinfo2, helpinfo4, helpinfo5);
		infobox.setAlignment(Pos.BOTTOM_LEFT);
		
		
		Label help2info = new Label("변경의");
		Label help2info2 = new Label("파란색");
		help2info2.setTextFill(Color.BLUE);
		Label help2info3 = new Label("글자는 원본의 값에서 데이터 정비 후 변경된 값을 나타내며, ");
		Label help2info4 = new Label("빨간색");
		help2info4.setTextFill(Color.RED);
		
		Label help2info5 = new Label("글자는 선택하신 데이터 타입으로 값 판단 여부가 불확실한 경우 입니다. (정비 불가)");
		
		
		HBox infobox2 = new HBox(5);
		infobox2.setPadding(new Insets(0, 0, 0, 15));
		infobox2.getChildren().addAll(help2info, help2info2, help2info3, help2info4, help2info5);
		
		VBox vbox2 = new VBox();
		//vbox.getChildren().addAll(s1Header ,hbox,headerGP,tableview);
//		spane.setMaxWidth(vbox2.getWidth());
		vbox2.setVgrow(s2hbox2, Priority.ALWAYS);
		vbox2.getChildren().addAll(s2hbox2,botgp, infobox, infobox2);
		vbox2.setPadding(new Insets(70, 10, 10, 10));
		//vbox2.setPrefSize(800, 600);
		
		vbox2.setStyle("-fx-background-color: #FFFFFF;");
		vbox2.autosize();
		
		
		scene2 = new Scene(vbox2);
		
		
		
		HBox hbox2 = new HBox(15);
    	hbox2.getChildren().addAll(pBar, pind, txtstate);
    	hbox2.setPadding(new Insets(320,0,0,370));
		hbox2.setAlignment(Pos.CENTER);
		
		Group root2 = new Group();
    	root2.getChildren().addAll(hbox2);
		
    	scene3 = new Scene(root2);
    	
    	
    	

    	
    	StackPane sp2 = new StackPane();
		sp2.getChildren().addAll(vboxThree);
		
		sp2.setStyle("-fx-background-color: #FFFFFF;");
	
		sp2.autosize();
    	sceneThree = new Scene(sp2);
    	
    	
		window.setScene(scene1);
		scene1.getStylesheets().add("application/New.css");
		scene2.getStylesheets().add("application/New.css");
		sceneThree.getStylesheets().add("application/New2.css");
		window.show();
	}

	
	
	
	public static void main(String[] args) {
		launch(args);
	}

	
	
	
	// csv 파일을 읽어 들여 List로 리턴

	
	
	
	public int itemsPerPage() {
        return 1;
    }

	
    public int rowsPerPage() {
        return 10000;
    }

    
public VBox createPage(int pageIndex) {
	
		
		
        int lastIndex = 0;
        int displace = data.size() % rowsPerPage();
        if (displace > 0) {
            lastIndex = data.size() / rowsPerPage();
        } else {
            lastIndex = data.size() / rowsPerPage()-1 ;

        }
       
        VBox box = new VBox(5);
        int page = pageIndex * itemsPerPage();

        for (int i = page; i < page + 1; i++) {
            tableview = new TableView();

            
			TableColumn col;
			int coldiv = 1;
			for (int j = 0; j < dataList.get(0).length; j++) {
				
				if(dataList.get(0).length>=10) {
					coldiv = 10;
				}else {
					coldiv = dataList.get(0).length;
				}
				
				
				final int k = j;
				col = new TableColumn(dataList.get(0)[j]);

				col.setCellValueFactory(
						new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
							public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
								return new SimpleStringProperty(param.getValue().get(k).toString());
							}
						});


				col.prefWidthProperty().bind(tableview.widthProperty().divide(coldiv+0.05));
				tableview.getColumns().addAll(col);

			}
			
			tableview.setItems(data);
			
			if(displace==0) {
				if (lastIndex+1 == pageIndex) {
	            	tableview.setItems(FXCollections.observableArrayList(data.subList(pageIndex * rowsPerPage(), (pageIndex * rowsPerPage()) + displace)));
	            } else {
	            	tableview.setItems(FXCollections.observableArrayList(data.subList(pageIndex * rowsPerPage(), pageIndex * rowsPerPage() + rowsPerPage())));
	            }
			} else {
				if (lastIndex == pageIndex) {
	            	tableview.setItems(FXCollections.observableArrayList(data.subList(pageIndex * rowsPerPage(), (pageIndex * rowsPerPage()) + displace)));
	            } else {
	            	tableview.setItems(FXCollections.observableArrayList(data.subList(pageIndex * rowsPerPage(), pageIndex * rowsPerPage() + rowsPerPage())));
	            }
			}
			
			
            tableview.autosize();
            
            box.getChildren().add(tableview);
            box.autosize();
            
        }
        return box;
    }
    


public VBox createPage2(int pageIndex) {
	
	comboitemSeqList = new ArrayList<String>();
	
	ObservableList<String> comboitemList = FXCollections.observableArrayList("문자열","숫자", "여부", "날짜 > YYYY-MM-DD HH24:MI:SS", "날짜 > YYYY-MM-DD HH24:MI", "날짜 > YYYY-MM-DD HH24", "날짜 > MM-DD HH24:MI", "날짜 > HH24:MI:SS","날짜 > YYYY-MM-DD","날짜 > HH24:MI","날짜 > YYYY-MM","날짜 > MM-DD","날짜 > HH24","날짜 > YYYY","날짜 > DD","날짜 > MI","날짜 > MM","날짜 > SS", "전화번호", "우편번호", "사업자번호");
	ComboBox<Integer> headerCombo = new ComboBox<Integer>(
			FXCollections.observableArrayList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15));
	// ComboBox Font 설정
	headerCombo.setStyle("-fx-font: 13px \"Serif\";");
	
	
	
	ObservableList<ObservableList> comb = FXCollections.observableArrayList();
	ObservableList<String> comb2 = FXCollections.observableArrayList("문자열","숫자", "여부", "날짜 > YYYY-MM-DD HH24:MI:SS", "날짜 > YYYY-MM-DD HH24:MI", "날짜 > YYYY-MM-DD HH24", "날짜 > MM-DD HH24:MI", "날짜 > HH24:MI:SS","날짜 > YYYY-MM-DD","날짜 > HH24:MI","날짜 > YYYY-MM","날짜 > MM-DD","날짜 > HH24","날짜 > YYYY","날짜 > DD","날짜 > MI","날짜 > MM","날짜 > SS", "전화번호", "우편번호", "사업자번호");
	comb.add(comb2);
	Set<Integer> combList = new HashSet<>();
	
	
    int lastIndex = 0;
    int displace = data.size() % rowsPerPage();
    if (displace > 0) {
        lastIndex = data.size() / rowsPerPage();
    } else {
        lastIndex = data.size() / rowsPerPage()-1 ;

    }
   
    VBox box = new VBox(5);
    page = pageIndex * itemsPerPage();
    for (int i = page; i < page + 1; i++) {
        tableview = new TableView();
        
        TableColumn col2;
		int coldiv = 1;
		for (int j = 0; j < dataList.get(comboNo).length; j++) {
			
			if(dataList.get(0).length>=10) {
				coldiv = 10;
			}else {
				coldiv = dataList.get(0).length;
			}
			
			
			final int k = j;
			col2 = new TableColumn(dataList.get(comboNo)[j]);

			col2.setCellValueFactory(
					new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
						public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
							return new SimpleStringProperty(param.getValue().get(k).toString());
						}
					});
			col2.prefWidthProperty().bind(tableview.widthProperty().divide(coldiv));


			
			
			
			ComboBox<String> cbb = new ComboBox<String>(comboitemList);
			
			
			
			
			
			comboitemSeqList.add("0");
			combcol2 = new TableColumn();
			combcol2.setGraphic(cbb);
			cbb.setOnAction(e -> {
				if (cbb.getValue().equals("문자열")) {
					comboitemSeqList.set(k, "0");
					combList.add(k);
					
				}else if(cbb.getValue().equals("숫자")) {
					comboitemSeqList.set(k, "1");
					combList.remove(k);
				}else if(cbb.getValue().equals("여부")) {
					comboitemSeqList.set(k, "2");
					combList.remove(k);
				} else if(cbb.getValue().equals("날짜 > YYYY-MM-DD HH24:MI:SS")) {
					comboitemSeqList.set(k, "3-1");
					combList.remove(k);
				}else if(cbb.getValue().equals("날짜 > YYYY-MM-DD HH24:MI")) {
					comboitemSeqList.set(k, "3-2");
					combList.remove(k);
				}else if(cbb.getValue().equals("날짜 > YYYY-MM-DD HH24")) {
					comboitemSeqList.set(k, "3-3");
					combList.remove(k);
				}else if(cbb.getValue().equals("날짜 > MM-DD HH24:MI")) {
					comboitemSeqList.set(k, "3-4");
					combList.remove(k);
				}else if(cbb.getValue().equals("날짜 > HH24:MI:SS")) {
					comboitemSeqList.set(k, "3-5");
					combList.remove(k);
				}else if(cbb.getValue().equals("날짜 > YYYY-MM-DD")) {
					comboitemSeqList.set(k, "3-6");
					combList.remove(k);
				}else if(cbb.getValue().equals("날짜 > HH24:MI")) {
					comboitemSeqList.set(k, "3-7");
					combList.remove(k);
				}else if(cbb.getValue().equals("날짜 > YYYY-MM")) {
					comboitemSeqList.set(k, "3-8");
					combList.remove(k);
				}else if(cbb.getValue().equals("날짜 > MM-DD")) {
					comboitemSeqList.set(k, "3-9");
					combList.remove(k);
				}else if(cbb.getValue().equals("날짜 > HH24")) {
					comboitemSeqList.set(k, "3-10");
					combList.remove(k);
				}else if(cbb.getValue().equals("날짜 > YYYY")) {
					comboitemSeqList.set(k, "3-11");
					combList.remove(k);
				}else if(cbb.getValue().equals("날짜 > DD")) {
					comboitemSeqList.set(k, "3-12");
					combList.remove(k);
				}else if(cbb.getValue().equals("날짜 > MI")) {
					comboitemSeqList.set(k, "3-13");
					combList.remove(k);
				}else if(cbb.getValue().equals("날짜 > MM")) {
					comboitemSeqList.set(k, "3-14");
					combList.remove(k);
				}else if(cbb.getValue().equals("날짜 > SS")) {
					comboitemSeqList.set(k, "3-15");
					combList.remove(k);
				}else if(cbb.getValue().equals("전화번호")) {
					comboitemSeqList.set(k, "4");
					combList.remove(k);
				}else if(cbb.getValue().equals("우편번호")) {
					comboitemSeqList.set(k, "5");
					combList.remove(k);
				}else if(cbb.getValue().equals("사업자번호")) {
					comboitemSeqList.set(k, "6");
					combList.remove(k);
				}
			});
			
			if(page==0) {
				cbb.getSelectionModel().selectFirst();
			}else if(!comboitemSeqList.get(j).equals("0")){
				int z = 0;
				if(comboitemSeqList.get(j).equals("1")) {
					z = 1;
				} else if(comboitemSeqList.get(j).equals("2")) {
					z = 2;
				} else if(comboitemSeqList.get(j).equals("3-1")) {
					z = 3;
				} else if(comboitemSeqList.get(j).equals("3-2")) {
					z = 4;
				} else if(comboitemSeqList.get(j).equals("3-3")) {
					z = 5;
				} else if(comboitemSeqList.get(j).equals("3-4")) {
					z = 6;
				} else if(comboitemSeqList.get(j).equals("3-5")) {
					z = 7;
				} else if(comboitemSeqList.get(j).equals("3-6")) {
					z = 8;
				} else if(comboitemSeqList.get(j).equals("3-7")) {
					z = 9;
				} else if(comboitemSeqList.get(j).equals("3-8")) {
					z = 10;
				} else if(comboitemSeqList.get(j).equals("3-9")) {
					z = 11;
				} else if(comboitemSeqList.get(j).equals("3-10")) {
					z = 12;
				} else if(comboitemSeqList.get(j).equals("3-11")) {
					z = 13;
				} else if(comboitemSeqList.get(j).equals("3-12")) {
					z = 14;
				} else if(comboitemSeqList.get(j).equals("3-13")) {
					z = 15;
				} else if(comboitemSeqList.get(j).equals("3-14")) {
					z = 16;
				} else if(comboitemSeqList.get(j).equals("3-15")) {
					z = 17;
				} else if(comboitemSeqList.get(j).equals("4")) {
					z = 18;
				} else if(comboitemSeqList.get(j).equals("5")) {
					z = 19;
				} else if(comboitemSeqList.get(j).equals("6")) {
					z = 20;
				}
				
				cbb.getSelectionModel().select(z);
			}
			combcol2.prefWidthProperty().bind(tableview.widthProperty().divide(coldiv));
			//combcol2.prefWidthProperty().bindBidirectional(tableview.prefWidthProperty().as);
			


			combcol2.setCellValueFactory(
					new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
						public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
							return new SimpleStringProperty(param.getValue().get(k).toString());
						}
					});
			
			col2.getColumns().add(combcol2);
			
			tableview.getColumns().addAll(col2);

		}
		
		tableview.prefHeightProperty().bind(stage.heightProperty());
		tableview.prefWidthProperty().bind(stage.widthProperty());
		tableview.setItems(data);
		
		if(displace==0) {
			if (lastIndex+1 == pageIndex) {
            	tableview.setItems(FXCollections.observableArrayList(data.subList(pageIndex * rowsPerPage(), (pageIndex * rowsPerPage()) + displace)));
            } else {
            	tableview.setItems(FXCollections.observableArrayList(data.subList(pageIndex * rowsPerPage(), pageIndex * rowsPerPage() + rowsPerPage())));
            }
		} else {
			if (lastIndex == pageIndex) {
            	tableview.setItems(FXCollections.observableArrayList(data.subList(pageIndex * rowsPerPage(), (pageIndex * rowsPerPage()) + displace)));
            } else {
            	tableview.setItems(FXCollections.observableArrayList(data.subList(pageIndex * rowsPerPage(), pageIndex * rowsPerPage() + rowsPerPage())));
            }
		}
		
		
        


        box.getChildren().add(tableview);
    }
    return box;
}









	//원본데이터
	public VBox createPageOrg(int pageIndex) {
		
		
		
	    int lastIndex = 0;
	    int displace = orgdata.size() % rowsPerPage();
	    if (displace > 0) {
	        lastIndex = orgdata.size() / rowsPerPage();
	    } else {
	        lastIndex = orgdata.size() / rowsPerPage()-1 ;
	
	    }
	    
	    VBox box = new VBox(5);
	    page = pageIndex * itemsPerPage();
	    for (int i = page; i < page + 1; i++) {
	        orgtableview = new TableView();
	        orgtableview.setPrefWidth(490);
	        orgtableview.setMinWidth(490);
	        
			TableColumn col;
			int coldiv = 1;
			for (int j = 0; j < orgDataList.get(comboNo).length; j++) {
				
				if(orgDataList.get(0).length>=6) {
					coldiv = 6;
				}else {
					coldiv = orgDataList.get(0).length;
				}
				
				
				final int k = j;
				col = new TableColumn(dataList.get(comboNo)[j]);
	
				col.setCellValueFactory(
						new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
							public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
								if(tablewidth!=0.0) {
									orgtableview.setMaxWidth(s2hbox2.getWidth());
							    	cngtableview.setMaxWidth(s2hbox2.getWidth());
								}
								return new SimpleStringProperty(param.getValue().get(k).toString());
							}
						});
				
				PseudoClass specialClass = PseudoClass.getPseudoClass("special");


					col.setCellFactory(tc -> new TextFieldTableCell<ObservableList, String>(TextFormatter.IDENTITY_STRING_CONVERTER) {
					    @Override
					    public void updateItem(String item, boolean empty) {
					        super.updateItem(item, empty);
					        boolean condition = empty;
					        if (!isEmpty()) {
			                    this.setTextFill(Color.BLACK);
			                    ObservableList list = getTableView().getItems().get(getIndex());
			                    if(!item.equals(cngDataList.get(getIndex()+(rowsPerPage()*page))[k])) {
			                    	this.setTextFill(Color.SEAGREEN);
			                    	
			                    }else if(!errDataList.get(getIndex()+(rowsPerPage()*page))[k].equals("")) {
			                    	this.setTextFill(Color.RED);
			                    }
			                    
			                        
			                    setText(item);
			
			                }
					        pseudoClassStateChanged(specialClass, condition);
					    }
					});

					col.setPrefWidth(180);
				
				col.prefWidthProperty().bind(orgtableview.widthProperty().divide(coldiv));

				orgtableview.getColumns().addAll(col);


	
			}
			orgtableview.setItems(orgdata);
			
			if(displace==0) {
				if (lastIndex+1 == pageIndex) {
					cngtableview.setItems(FXCollections.observableArrayList(cngdata.subList(pageIndex * rowsPerPage(), (pageIndex * rowsPerPage()) + displace)));
					orgtableview.setItems(FXCollections.observableArrayList(orgdata.subList(pageIndex * rowsPerPage(), (pageIndex * rowsPerPage()) + displace)));
	            } else {
	            	cngtableview.setItems(FXCollections.observableArrayList(cngdata.subList(pageIndex * rowsPerPage(), pageIndex * rowsPerPage() + rowsPerPage())));
	            	orgtableview.setItems(FXCollections.observableArrayList(orgdata.subList(pageIndex * rowsPerPage(), pageIndex * rowsPerPage() + rowsPerPage())));
	            }
			} else {
				if (lastIndex == pageIndex) {
					cngtableview.setItems(FXCollections.observableArrayList(cngdata.subList(pageIndex * rowsPerPage(), (pageIndex * rowsPerPage()) + displace)));
					orgtableview.setItems(FXCollections.observableArrayList(orgdata.subList(pageIndex * rowsPerPage(), (pageIndex * rowsPerPage()) + displace)));
	            } else {
	            	cngtableview.setItems(FXCollections.observableArrayList(cngdata.subList(pageIndex * rowsPerPage(), pageIndex * rowsPerPage() + rowsPerPage())));
	            	orgtableview.setItems(FXCollections.observableArrayList(orgdata.subList(pageIndex * rowsPerPage(), pageIndex * rowsPerPage() + rowsPerPage())));
	            }
			}
			
			
			orgtableview.autosize();
			
			
			
			orgtableview.setRowFactory( tv -> {
				TableRow<ObservableList> sorgRow = new TableRow<ObservableList>();
			    sorgRow.setOnMouseClicked(event -> {
			        if (event.getClickCount() == 1 && (! sorgRow.isEmpty()) ) {
			        	ObservableList rowData = sorgRow.getItem();
			            
						cngtableview.getSelectionModel().select(sorgRow.getIndex());
			            
			            
			        }
			    });
			    
				Platform.runLater(new Runnable()
				{
				    @Override
				    public void run()
				    {
				    	
				    	
				    	ScrollBar bar = getVerticalScrollbar(orgtableview);
				    	ScrollBar bar2 = getVerticalScrollbar(cngtableview);
				    	
				    	ScrollBar bar3 = getHorizontalScrollbar(orgtableview);
				    	ScrollBar bar4 = getHorizontalScrollbar(cngtableview);
				    			
				    	
				    	bar.valueProperty().bindBidirectional(bar2.valueProperty());
				    	bar3.valueProperty().bindBidirectional(bar4.valueProperty());
//				    	orgtableview.refresh();
//				    	cngtableview.refresh();
				    	orgtableview.setMaxWidth(s2hbox2.getWidth());
				    	cngtableview.setMaxWidth(s2hbox2.getWidth());
				    	orgtableview.prefHeightProperty().bind(stage.heightProperty());
				    	cngtableview.prefHeightProperty().bind(stage.heightProperty());
//						tableview.prefWidthProperty().bind(stage.widthProperty());
				    	
				    	
				    }
				});
			    return sorgRow;
			});
			
			
			
			
			Label orgLabel = new Label("원본 테이블");
			orgLabel.setMaxWidth(Double.MAX_VALUE);
			orgLabel.setAlignment(Pos.CENTER);
			orgLabel.getStyleClass().remove("label");
			orgLabel.getStyleClass().add("myOrg");
			

		        
	        
	        box.getChildren().addAll(orgLabel, orgtableview);
	        box.autosize();
	        
	    }
	    return box;
	}
	//변경 데이터
	public VBox createPageCng(int pageIndex) {
		
		
		
	    int lastIndex = 0;
	    int displace = cngdata.size() % rowsPerPage();
	    if (displace > 0) {
	        lastIndex = cngdata.size() / rowsPerPage();
	    } else {
	        lastIndex = cngdata.size() / rowsPerPage()-1 ;
	
	    }
	    List<String> setData = new ArrayList<String>();
	    VBox box = new VBox(5);
	    

	    for (int i = page; i < page + 1; i++) {
	        cngtableview = new TableView();
	        cngtableview.setPrefWidth(490);
	        cngtableview.setMinWidth(490);
	        
	        colCount = 0;
	        
			TableColumn col;
			int coldiv = 1;
			for (int j = 0; j < cngDataList.get(0).length; j++) {
				
				if(cngDataList.get(0).length>=6) {
					coldiv = 6;
				}else {
					coldiv = cngDataList.get(0).length;
				}
				
				
				final int k = j;
				col = new TableColumn(dataList.get(comboNo)[j]);
	
				col.setCellValueFactory(
						new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
							public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
								setData.add("셋밸류 "+param.getValue().get(k).toString());
								return new SimpleStringProperty(param.getValue().get(k).toString());
							}
						});
				
				col.setCellFactory(TextFieldTableCell.<ObservableList>forTableColumn());
				
				col.setOnEditCommit(new EventHandler<CellEditEvent<ObservableList, String>>() {
					public void handle(CellEditEvent<ObservableList, String> t) {
						((ObservableList) t.getTableView().getItems().get(t.getTablePosition().getRow())).set(k,t.getNewValue()); // .getValue().set(j,t.getNewValue());
					}
				});
				
				
				
				PseudoClass specialClass = PseudoClass.getPseudoClass("special");


					col.setCellFactory(tc -> new TextFieldTableCell<ObservableList, String>(TextFormatter.IDENTITY_STRING_CONVERTER) {
					    @Override
					    public void updateItem(String item, boolean empty) {
					        super.updateItem(item, empty);
					        boolean condition = empty;
					        if (!isEmpty()) {
			                    this.setTextFill(Color.BLACK);
			                    ObservableList list = getTableView().getItems().get(getIndex());
			                    
			                    
			                    if(!item.equals(orgDataList.get(getIndex()+(rowsPerPage()*page))[k])) {
			                    	this.setTextFill(Color.BLUE);
			                    }
			                    else if(!errDataList.get(getIndex()+(rowsPerPage()*page))[k].equals("")) {
			                    	this.setTextFill(Color.RED);
			                    }
			                    
			                        
			                    setText(item);
			
			                }
					        pseudoClassStateChanged(specialClass, condition);
					    }
					});

				
					
					col.setMinWidth(100);
//					col.setPrefWidth(100);
				
					colCount++;
				
				col.prefWidthProperty().bind(cngtableview.widthProperty().divide(coldiv));
				cngtableview.getColumns().addAll(col);
	
			}
//			autoFitTable(cngtableview);
			cngtableview.setItems(cngdata);
			
			if(displace==0) {
				if (lastIndex+1 == pageIndex) {
					cngtableview.setItems(FXCollections.observableArrayList(cngdata.subList(pageIndex * rowsPerPage(), (pageIndex * rowsPerPage()) + displace)));
					orgtableview.setItems(FXCollections.observableArrayList(orgdata.subList(pageIndex * rowsPerPage(), (pageIndex * rowsPerPage()) + displace)));
	            } else {
	            	cngtableview.setItems(FXCollections.observableArrayList(cngdata.subList(pageIndex * rowsPerPage(), pageIndex * rowsPerPage() + rowsPerPage())));
	            	orgtableview.setItems(FXCollections.observableArrayList(orgdata.subList(pageIndex * rowsPerPage(), pageIndex * rowsPerPage() + rowsPerPage())));
	            }
			} else {
				if (lastIndex == pageIndex) {
					cngtableview.setItems(FXCollections.observableArrayList(cngdata.subList(pageIndex * rowsPerPage(), (pageIndex * rowsPerPage()) + displace)));
					orgtableview.setItems(FXCollections.observableArrayList(orgdata.subList(pageIndex * rowsPerPage(), (pageIndex * rowsPerPage()) + displace)));
	            } else {
	            	cngtableview.setItems(FXCollections.observableArrayList(cngdata.subList(pageIndex * rowsPerPage(), pageIndex * rowsPerPage() + rowsPerPage())));
	            	orgtableview.setItems(FXCollections.observableArrayList(orgdata.subList(pageIndex * rowsPerPage(), pageIndex * rowsPerPage() + rowsPerPage())));
	            }
			}
			
			
			cngtableview.setRowFactory( tv -> {
				TableRow<ObservableList> sorgRow = new TableRow<ObservableList>();
			    sorgRow.setOnMouseClicked(event -> {
			        if (event.getClickCount() == 1 && (! sorgRow.isEmpty()) ) {
			        	ObservableList rowData = sorgRow.getItem();
			            

						orgtableview.getSelectionModel().select(sorgRow.getIndex());
			            
			            
			        }
			    });
			    
			    return sorgRow;
			});
			
			Label cngLabel = new Label("변경 테이블");
			cngLabel.setMaxWidth(Double.MAX_VALUE);
			cngLabel.setAlignment(Pos.CENTER);
			cngLabel.getStyleClass().remove("label");
			cngLabel.getStyleClass().add("myCng");
			
			
			cngtableview.autosize();
	        cngtableview.setEditable(true);
	        
	        box.getChildren().addAll(cngLabel, cngtableview);
	        box.autosize();
	        
	    }
	    return box;
	}
	
	public VBox createPageThree(int pageIndex) {
	
		
		
        int lastIndex = 0;
        int displace = data3.size() % rowsPerPage();
        if (displace > 0) {
            lastIndex = data3.size() / rowsPerPage();
        } else {
            lastIndex = data3.size() / rowsPerPage()-1 ;

        }
       
        VBox box = new VBox(5);
        int page = pageIndex * itemsPerPage();
        for (int i = page; i < page + 1; i++) {
            tableview = new TableView();
            tableview.setMinWidth(980);
            
			TableColumn col;
			int coldiv = 1;
			for (int j = 0; j < dataList3.get(0).length; j++) {
				
				if(dataList3.get(0).length>=10) {
					coldiv = 10;
				}else {
					coldiv = dataList3.get(0).length;
				}
				
				
				final int k = j;
				col = new TableColumn(dataList3.get(0)[j]);

				col.setCellValueFactory(
						new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
							public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
								return new SimpleStringProperty(param.getValue().get(k).toString());
							}
						});


				col.prefWidthProperty().bind(tableview.widthProperty().divide(coldiv+0.3));
				tableview.getColumns().addAll(col);

			}
			
			tableview.setItems(data);
			
			if(displace==0) {
				if (lastIndex+1 == pageIndex) {
	            	tableview.setItems(FXCollections.observableArrayList(data3.subList(pageIndex * rowsPerPage(), (pageIndex * rowsPerPage()) + displace)));
	            } else {
	            	tableview.setItems(FXCollections.observableArrayList(data3.subList(pageIndex * rowsPerPage(), pageIndex * rowsPerPage() + rowsPerPage())));
	            }
			} else {
				if (lastIndex == pageIndex) {
	            	tableview.setItems(FXCollections.observableArrayList(data3.subList(pageIndex * rowsPerPage(), (pageIndex * rowsPerPage()) + displace)));
	            } else {
	            	tableview.setItems(FXCollections.observableArrayList(data3.subList(pageIndex * rowsPerPage(), pageIndex * rowsPerPage() + rowsPerPage())));
	            }
			}
			
			tableview.prefHeightProperty().bind(stage.heightProperty());
			tableview.prefWidthProperty().bind(stage.widthProperty());
			
            tableview.autosize();
            
            box.getChildren().add(tableview);
            box.autosize();
            
        }
        return box;
    }
	


	
	public static ScrollBar getVerticalScrollbar(Node table) {
	    ScrollBar result = null;
	    for(Node n : table.lookupAll(".scroll-bar")) {
	        if(n instanceof ScrollBar) {
	            ScrollBar bar = (ScrollBar) n;
	            if(bar.getOrientation().equals(Orientation.VERTICAL)) {
	                result = bar;
	            }
	        }
	    }
	    return result;
	}	
	
	public static ScrollBar getHorizontalScrollbar(Node table) {
	    ScrollBar result = null;
	    for(Node n : table.lookupAll(".scroll-bar")) {
	        if(n instanceof ScrollBar) {
	            ScrollBar bar = (ScrollBar) n;
	            if(bar.getOrientation().equals(Orientation.HORIZONTAL)) {
	                result = bar;
	            }
	        }
	    }
	    return result;
	}
	    
	//Create a New Task
    private Task taskCreator(int seconds) {
    	
    	return new Task() {

			@Override
			protected Object call() throws Exception {
				
				data.clear();
				
				orgDataList = new ArrayList<String[]>();
				cngDataList = new ArrayList<String[]>();
				errDataList = new ArrayList<String[]>();
				
				orgdata = FXCollections.observableArrayList();
				orgrow = FXCollections.observableArrayList();
				
				cngdata = FXCollections.observableArrayList();
				cngrow = FXCollections.observableArrayList();
				
				errdata = FXCollections.observableArrayList();
				err2row = FXCollections.observableArrayList();
				
				List<Map<String,String>> list = new ArrayList<Map<String,String>>();
				
				
				for(int i=0;i<dataList.get(0).length;i++) {
					Map<String, String> map = new HashMap<String, String>();
							map.put(i+"header",dataList.get(0)[i]);
							map.put(i+"datatype",comboitemSeqList.get(i));
							list.add(map);
						
							
				}
				
				for (int i =0; i<list.size();i++) {
					if(list.get(i).get(i+"datatype").length()>1) {
						String str = list.get(i).get(i+"datatype");
						String word1 = str.split("-")[0];
						String word2 = str.split("-")[1];
					}
				}
				
				
				String str = file.getName();
				String fileName = str.substring(0,str.length()-4);
				try {
					orgcngPath = mc.FileInspection(file.getPath(),fileName,str,comboNo,list);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
				
				int count = 0;
				for (String[] data : mc.readerCSV2(orgcngPath)) {
					if(count==0) {
//						orgDataList.add(data);
//						cngDataList.add(data);
//						errDataList.add(data);
					}
					if(count>0) {
						if(count%3==1) {
							orgDataList.add(data);
						} else if(count%3==2) {
							cngDataList.add(data);
						} else if(count%3==0) {
							errDataList.add(data);
						}
					}


					count++;

				}
				
				
				
				
				
				for (int i = 0; i < orgDataList.size(); i++) {
					orgrow = FXCollections.observableArrayList();
					cngrow = FXCollections.observableArrayList();
					err2row = FXCollections.observableArrayList();
					for (int j = 0; j < orgDataList.get(i).length; j++) {

						orgrow.add(orgDataList.get(i)[j]);
						cngrow.add(cngDataList.get(i)[j]);
						err2row.add(errDataList.get(i)[j]);

					}
					orgdata.add(orgrow);
					cngdata.add(cngrow);
					errdata.add(err2row);
					
				}
				
				//원본테이블
				if(orgdata.size()%rowsPerPage()==0) {
					if(orgdata.size()!=0) {
						pagination = new Pagination((orgdata.size() / rowsPerPage()), 0);
					}else {
						pagination = new Pagination(1,0);
					}
					
				} else {
					pagination = new Pagination((orgdata.size() / rowsPerPage() + 1), 0);
				}
				
		        pagination.setPageFactory(new Callback<Integer, Node>() {
		            @Override
		            public Node call(Integer pageIndex) {
		            	
		                if (pageIndex > orgdata.size() / rowsPerPage() + 1) {
		                    return null;
		                } else {
		                    return createPageOrg(pageIndex);
		                }
		            }
		        });
				
		       
		        
		        //변경테이블
		        
		        if(cngdata.size()%rowsPerPage()==0) {
					if(cngdata.size()!=0) {
						pagination2 = new Pagination((cngdata.size() / rowsPerPage()), 0);
					}else {
						pagination2 = new Pagination(1,0);
					}
					
				} else {
					pagination2 = new Pagination((cngdata.size() / rowsPerPage() + 1), 0);
				}
				
		        pagination2.setPageFactory(new Callback<Integer, Node>() {
		            @Override
		            public Node call(Integer pageIndex) {
		            	pageIndex = pagination.getCurrentPageIndex();
		                if (pageIndex > cngdata.size() / rowsPerPage() + 1) {
		                    return null;
		                } else {
		                    return createPageCng(pageIndex);
		                }
		            }
		        });
		        //2019-08-02 setMaxWidth
		        pagination.setMaxWidth(Double.MAX_VALUE);
		        pagination2.setMaxWidth(Double.MAX_VALUE);
		        s2hbox2.setHgrow(pagination, Priority.ALWAYS);
		        s2hbox2.setHgrow(pagination2, Priority.ALWAYS);
		        s2hbox2.getChildren().addAll(pagination, pagination2);
		        
		        s2hbox2.autosize();
		        Platform.runLater(new Runnable()
				{
				    @Override
				    public void run()
				    {
				    	pagination.currentPageIndexProperty().bindBidirectional(pagination2.currentPageIndexProperty());
				    }
				});
		        
		        for(int i=0;i<orgDataList.size();i++) {
//		        	Thread.sleep(1000);
					updateProgress(i+1, seconds);
		        }
		        
		        String filepath2 = file.getPath().replace(str, "");
		        filepath2 = filepath2.replace("\\", "/");
		        filepath2 = filepath2 + "temp";
		        String cngFile = filepath2 + "/" + fileName + "_temp.csv";
		        errFile = filepath2 + "/" + fileName + "_msg.csv";
		        csvFileName = fileName;
		        File file = new File(cngFile);
		        
		         System.out.println("경로"+filepath2);
		        if( file.exists() ){
		            if(file.delete()){
		            }else{
		            }
		        }else{
		        }
		        
		        file3 = new File(errFile);
				for (String[] data : mc.readerCSV2(file3.getPath())) {
					dataList3.add(data);
				}
				File file2 = new File(errFile);
		        
		         
		        if( file2.exists() ){
		            if(file2.delete()){
		            }else{
		            }
		        }else{
		        }
		        
		       File folderDelete = new File(filepath2);
		       folderDelete.delete();
		        
				return true;
			}
    		
    	};
    }
    
    
    
    private static Method columnToFitMethod;

    static {
        try {
            columnToFitMethod = TableViewSkin.class.getDeclaredMethod("resizeColumnToFitContent", TableColumn.class, int.class);
            columnToFitMethod.setAccessible(true);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public static void autoFitTable(TableView tableView) {
        tableView.getItems().addListener(new ListChangeListener<Object>() {
            @Override
            public void onChanged(Change<?> c) {
                for (Object column : tableView.getColumns()) {
                    try {
                        columnToFitMethod.invoke(tableView.getSkin(), column, -1);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
    
    
}
