



package hover_image;

import java.io.File;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;



public class Hover_image extends Application {

    private Image image1;
    private Image image2;
    private ImageView image_view1;
    private ImageView image_view2;
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        
//                            SETUP BUTTONS
        Button open_main=new Button("Open Main Image");
        Button open_hover=new Button("Open Hover Image");
        Button default_btn=new Button("Default Images");
        
//                            SETUP IMAGES AND IMAGE VIEWS
        image1=new Image("file:moutains.jpg");
        image2=new Image("file:lake.jpg");
        image_view1=new ImageView(image1);
        image_view2=new ImageView(image2);
        image_view2.setOpacity(0);
        
//                            SET OPACITY IMAGE VIEW1 ON MOUSE ENTERED              set on mouse opacity to display one image view or the other
        image_view1.setOnMouseEntered(event ->{                                      //needed if images are different sizes
            image_view1.setOpacity(0);
            image_view2.setOpacity(100);
        });
//                            SET OPACITY  VIEW2 ON MOUSE ENTERED                   set on mouse opacity to display one image view or the other
        image_view2.setOnMouseEntered(event ->{                                     //needed if images are different sizes
            image_view1.setOpacity(0);
            image_view2.setOpacity(100);
        });
//                            SET OPACITY IMAGE VIEW1 ON MOUSE EXITED               set on mouse opacity to display one image view or the other
        image_view1.setOnMouseExited(event ->{                                      //needed if images are different sizes
            image_view1.setOpacity(100);
            image_view2.setOpacity(0);
        });
//                            SET OPACITY IMAGE VIEW2 ON MOUSE EXITED               set on mouse opacity to display one image view or the other
        image_view2.setOnMouseExited(event ->{                                      //needed if images are different sizes
            image_view1.setOpacity(100);
            image_view2.setOpacity(0);
        });
        
//                            OPEN MAIN BUTTON LAMBDA EVENT
        open_main.setOnAction(event ->{
            image1=setNewImage(primaryStage, image1);                               //call setNewImage method to open file chooser and set image1
            
            double image1_width=image1.getWidth();                                  //get width & height of image
            double image1_height=image1.getHeight();
            checkImageSize(image1_width, image1_height,image1, image_view1);        //check image size, if bigger than width 500,height 350 then resize image
        });
        
//                            OPEN HOVER BUTTON LAMBDA EVENT
        open_hover.setOnAction(event ->{
            image2=setNewImage(primaryStage, image2);                               //call setNewImage method to open file chooser and set image2
            
            double image2_width=image2.getWidth();                                  //get width & height of image
            double image2_height=image2.getHeight();
            checkImageSize(image2_width, image2_height,image2, image_view2);        //check image size, if bigger than width 500,height 350  then resize image
            
        });
        
//                            DEFAULT BUTTON LAMBDA EVENT
        default_btn.setOnAction(event ->{
           image1= new Image("file:moutains.jpg");                                  //reset image1 to original file
           image_view1.setImage(image1);                                            //set image1 to image_view1
           image_view1.setFitWidth(0);                                              //reset image_view1 height to 0
           image_view1.setFitHeight(0);                                             //reset image_view1 width to 0
           
           image2=new Image("file:lake.jpg");                                       //reset image2 to original file
           image_view2.setImage(image2);                                            //set image2 to image_view2
           image_view2.setFitWidth(0);                                              //reset image_view2 height to 0
           image_view2.setFitWidth(0);                                              //reset image_view2 width to 0
        }); 
        
//                            SETUP GRIDPANE
        GridPane grid_pane=new GridPane();
        grid_pane.add(image_view1,0,0);                                             //image_view1 in grid 0, 0
        GridPane.setColumnSpan(image_view1,2);
        GridPane.setHalignment(image_view1,HPos.CENTER);
        
        grid_pane.add(image_view2,0,0);                                             //image_view2 also in grid 0, 0
        GridPane.setColumnSpan(image_view2,2);
        GridPane.setHalignment(image_view2,HPos.CENTER);
        
        grid_pane.setAlignment(Pos.CENTER);
//                           SETUP HBOX AND VBOX
        HBox hbox=new HBox(open_main, open_hover);
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets (40,0,10,0));                                    //set padding (top, right, bottom, left)
        hbox.setSpacing(15);
        
        VBox vbox=new VBox(grid_pane, hbox, default_btn);
        vbox.setAlignment(Pos.BOTTOM_CENTER);
        vbox.setPadding(new Insets(0,0,15,0));                                      //set padding (top, right, bottom, left)

        Scene scene = new Scene(vbox, 600, 600);
        scene.getStylesheets().add("styles.css"); 
        primaryStage.setTitle("Mouse Hover Images");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
    
//                        SET NEW IMAGE METHOD
    public Image setNewImage(Stage primaryStage, Image image){
       
       FileChooser file_chooser=new FileChooser(); 
       File selected_file=file_chooser.showOpenDialog(primaryStage);                //open file chooser
        if (selected_file != null){
            String file_name=selected_file.getPath();                               //get file path
            image=new Image("file:"+file_name);                                     //change image file path to file chooser file path
        }
        return image;
    }
    
//                        CHECK IMAGE SIZE METHOD
    public void checkImageSize(double image_width, double image_height, 
                                Image image, ImageView imageView){
        double image_width_ratio=500/image_width;                                   //set width ratio based off 500
        double image_height_ration=350/image_height;                                //set height ratio based off 350

        if (image_width>500 && image_height<350){                           
            imageView.setImage(image);
            imageView.setFitWidth(500);                                             //set image view width to 500
            imageView.setFitHeight(image_height*image_width_ratio);                 //scale down image view height
            }
            
        else if(image_height>350){
            imageView.setImage(image);
            imageView.setFitHeight(350);                                            //set image view height to 350
            imageView.setFitWidth(image_width*image_height_ration);                 //scale down image view width
        }  
        
        else{
            imageView.setImage(image);                                              //keep image size as is
            imageView.setFitHeight(0);                                              //set image view height to 0 (image will be original size)
            imageView.setFitWidth(0);                                               //set image view width to 0 (image will be original size)
        }
    }    

}
