package view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

@SuppressWarnings("deprecation")
public class MainWindow extends Application {
	private String appTitle = "GERENCIADOR DE DOCENTES";
	private BorderPane root = new BorderPane();
	private  Label title = new Label();
	private VBox leftMenu = new VBox(10);
	private Button ClassesBtn = new Button("Disciplinas");
	private Button TeacherBtn = new Button("Professores");
	private Button CoursesBtn = new Button("Cursos");
	private Button SubsBtn = new Button("Inscrições");
	private BorderPane classesPane = new ClassesView();
	private BorderPane coursesPane = new CoursesView();
	private BorderPane teachersPane = new TeachersView();
	private BorderPane subsPane = new SubscriptionsView();
	
	@Override
    public void start(Stage primaryStage) {
        
        
        title.setText(appTitle);
        
        Button[] leftBtns = new Button[] {ClassesBtn, TeacherBtn, CoursesBtn, SubsBtn};
        for (Button button : leftBtns) {
			button.setMinWidth(128);
		}
        ClassesBtn.setOnMouseClicked((e) -> {
        	setContent(classesPane);
        });
        CoursesBtn.setOnMouseClicked((e) -> {
        	setContent(coursesPane);
        });
        TeacherBtn.setOnMouseClicked((e) -> {
        	setContent(teachersPane);
        });
        SubsBtn.setOnMouseClicked((e) -> {
        	setContent(subsPane);
        });
        
        leftMenu.getChildren().addAll(ClassesBtn, CoursesBtn, TeacherBtn, SubsBtn);
        
        BorderPane.setMargin(title, new Insets(10));
        BorderPane.setMargin(leftMenu, new Insets(10));
        
        root.setTop(title);
        root.setLeft(leftMenu);
        
        Scene scene = new Scene(root, 1280, 720);

        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.setMinWidth(1280);
        primaryStage.setMinHeight(720);
        primaryStage.setTitle(appTitle);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    private void setContent(Pane pane) {
    	this.root.setCenter(pane);
    }
}

