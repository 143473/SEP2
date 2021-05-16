package client.views.search_for_rental;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewmodel.seatch_for_rental.SearchForRentalViewModel;
import com.sun.javafx.scene.control.LabeledText;
import javafx.animation.PathTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import org.controlsfx.control.GridCell;
import org.controlsfx.control.GridView;
import org.controlsfx.control.InfoOverlay;
import org.controlsfx.control.PopOver;
import org.controlsfx.control.cell.ColorGridCell;
import org.controlsfx.control.cell.ImageGridCell;
import org.controlsfx.validation.decoration.GraphicValidationDecoration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Controller
{
  @FXML private FlowPane flowPane;

  //private GridCell<Image> image;
  //private GridView<Image> list;
  private ArrayList<Color> colorList = new ArrayList<>();
  private ArrayList<Image> images;
  private ArrayList<Picture> pictures;
  private ObservableList<Image> observableImages;
  private ObservableList<Color> list;
  private InfoOverlay infoOverlay;
  private PopOver popOver;
  //private Callback<GridView<Image>, GridCell<Image>> factory;
  private ViewHandler viewHandler;
  private SearchForRentalViewModel searchForRentalViewModel;


  public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory)
      throws IOException
  {
    searchForRentalViewModel = viewModelFactory.getSearchForRentalViewModel();
    this.viewHandler = viewHandler;
    loadImages();
    for (int i = 0; i < pictures.size(); i++)
    {
      flowPane.getChildren().add(new StackPane(new InfoOverlay(new ImageView(pictures.get(i).getImage()),pictures.get(i).toString())));
      //flowPane.getChildren().get(i).setOnMouseClicked(searchForRentalViewModel::fireProperty);
      flowPane.getChildren().get(i).addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            try
            {
              getPicture(event.getTarget());
              viewHandler.setView(viewHandler.menu(), viewHandler.viewRental());
            }
            catch (IOException e)
            {
              e.printStackTrace();
            }
          });
    }
      /*flowPane.getChildren().get(i).addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
          getPicture((ImageView)mouseEvent.getTarget());
          try
          {
            viewHandler.setView(viewHandler.menu(), viewHandler.viewRental());
          }
          catch (IOException e)
          {
            e.printStackTrace();
          }
        }
      });
    }*/
    /*GridView<Image> myGrid = new GridView<>(observableImages);
    myGrid.setCellFactory(new Callback<GridView<Image>, GridCell<Image>>() {
      public GridCell<Image> call(GridView<Image> gridView) {
        return new ImageGridCell();
      }
    });

    for(int i = 0; i < pictures.size(); i++) {
      observableImages.add(pictures.get(i).getImage());
    }
    list = new GridView<Image>();
    loadImages();
    for(int i = 0; i < images.size(); i++) {
      list.getItems().addAll(images.get(i));
    }

    list.setCellFactory(factory);
    list =FXCollections.observableArrayList(colorList);

    GridView<Color> myGrid = new GridView<Color>(list);
    myGrid.setCellFactory(new Callback<GridView<Color>, GridCell<Color>>() {
      public GridCell<Color> call(GridView<Color> gridView) {
        return new ColorGridCell();
      }
    });
    Random r = new Random(System.currentTimeMillis());
    for(int i = 0; i < 5; i++) {
      list.add(new Color(r.nextDouble(), r.nextDouble(), r.nextDouble(), 1.0));
    }
    this.flowPane.getChildren().setAll(myGrid);*/
  }

  public Picture getPicture(Object object)
  {
    if (object instanceof ImageView)
    {
      for (int i = 0; i < pictures.size(); i++)
      {
        if (((ImageView) object).getImage().getUrl()
            .equals(pictures.get(i).getImage().getUrl()))
        {
          System.out.println(pictures.get(i).toString());
          return pictures.get(i);
        }
      }
    }
    else if (object instanceof LabeledText)
    {
      for (int i = 0; i < pictures.size(); i++)
      {
        if (((LabeledText) object).getText().equals(pictures.get(i).toString()))
        {
          System.out.println(pictures.get(i));
          return pictures.get(i);
        }
      }
    }

    return null;
  }

  public void loadImages()
  {
    Picture picture1 = new Picture("chisels",
        "woodwork bla blabla blablalbal la", "/Pictures/chisels.jpg");
    Picture picture2 = new Picture("hammer",
        "woodwork bla blabla blablalbal la", "/Pictures/hammer.jpg");
    Picture picture3 = new Picture("swiss", "woodwork bla blabla blablalbal la",
        "/Pictures/swiss.jpg");
    Picture picture4 = new Picture("torque_wrench",
        "woodwork bla blabla blablalbal la", "/Pictures/torque_wrench.jpg");
    Picture picture5 = new Picture("wrenches",
        "woodwork bla blabla blablalbal la", "/Pictures/wrenches.jpg");
    pictures = new ArrayList<>();
    pictures.add(picture1);
    pictures.add(picture2);
    pictures.add(picture3);
    pictures.add(picture4);
    pictures.add(picture5);
    pictures.add(picture2);
    pictures.add(picture5);
    pictures.add(picture1);

    images = new ArrayList<Image>();
    images.add(picture1.getImage());
    images.add(picture2.getImage());
    images.add(picture3.getImage());
    images.add(picture4.getImage());
    images.add(picture5.getImage());
    observableImages = FXCollections.observableArrayList(images);
  }
}
