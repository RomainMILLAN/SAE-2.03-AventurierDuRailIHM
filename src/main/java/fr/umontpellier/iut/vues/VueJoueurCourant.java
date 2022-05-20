package fr.umontpellier.iut.vues;

import fr.umontpellier.iut.IJeu;
import fr.umontpellier.iut.rails.CouleurWagon;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Cette classe présente les éléments appartenant au joueur courant.
 *
 * On y définit les bindings sur le joueur courant, ainsi que le listener à exécuter lorsque ce joueur change
 */
public class VueJoueurCourant extends BorderPane {
    private IJeu jeu;

    private Image imagePlayer;
    private SimpleStringProperty namePlayer;
    private Label pseudo;

    //TOP
    VBox top;
    //Avatar & Pseudo
    HBox nameandavatarimg;
    ImageView imgViewAvatar;
    //Destination, Gare & Wagons
    Label titledestination;
    HBox destigarewagon;
    VBox destinations;
    VBox allDestinations;
    VBox gareEtWagon;
    HBox gare;
    HBox wagon;
    ImageView gareImgView;
    Label gareLabel;
    ImageView wagonImgView;
    Label wagonLabel;

    //CENTER
    VBox center;
    Label cardTitle;
    VBox cardAll;

    //BOTTOM
    VBox bottom;
    Label cardSelectTitle;
    HBox allCardSelected;


    public VueJoueurCourant(IJeu jeu){
        this.jeu = jeu;
        this.setMaxHeight(500);
        this.setMinSize(200, 200);
        this.setPrefHeight(500);
        this.setPrefWidth(300);
        this.setStyle("-fx-background-color: green");
        this.setTranslateY(50);
        this.setTranslateX(5);

        /*
        TOP
        */
        top = new VBox();
        //NAME PLAYER & AVATAR
        nameandavatarimg = new HBox();
        String str = this.jeu.joueurCourantProperty().getName();
        if(str.equalsIgnoreCase("")){
            str = "Avatar";
        }
        pseudo = new Label(str);
        pseudo.setTextFill(Color.WHITE);
        pseudo.setFont(Font.font("Cabin", FontWeight.BOLD, 22));
        pseudo.setTranslateX(5);
        pseudo.setTranslateY(5);
        imgViewAvatar = new ImageView(new Image("images/avatars/avatar-ROSE.png"));
        imgViewAvatar.setFitHeight(55);
        imgViewAvatar.setFitWidth(45);
        imgViewAvatar.setTranslateX(5);
        nameandavatarimg.getChildren().addAll(imgViewAvatar, pseudo);


        //DESTINATION & GARE & WAGON
        destigarewagon = new HBox();

        //Destinations
        destinations = new VBox();
        //title destination
        titledestination = new Label("Destinations:");
        titledestination.setTextFill(Color.WHITE);
        titledestination.setFont(Font.font("QuickSand", FontWeight.MEDIUM, 17));
        titledestination.setStyle("-fx-underline: true;");
        //all destination
        allDestinations = new VBox();
        allDestinations.setTranslateX(10);

        destinations.getChildren().addAll(titledestination, allDestinations);
        destinations.setTranslateX(10);
        destinations.setTranslateY(15);


        //GARE & WAGON
        gareEtWagon = new VBox();
        //gare
        gare = new HBox();
        gareImgView = new ImageView(new Image("images/gares/gare-ROSE.png"));
        gareImgView.setFitWidth(25);
        gareImgView.setFitHeight(25);
        gareLabel = new Label("4");
        gareLabel.setFont(Font.font("QuickSand", FontWeight.MEDIUM, 20));
        gareLabel.setTextFill(Color.WHITE);
        gareLabel.setTranslateX(15);
        gare.getChildren().addAll(gareImgView, gareLabel);
        //wagon
        wagon = new HBox();
        wagonImgView = new ImageView(new Image("images/wagons/image-wagon-ROSE.png"));
        wagonImgView.setFitWidth(40);
        wagonImgView.setFitHeight(40);
        wagonLabel = new Label("4");
        wagonLabel.setFont(Font.font("QuickSand", FontWeight.MEDIUM, 20));
        wagonLabel.setTextFill(Color.WHITE);
        wagonLabel.setTranslateX(5);
        wagon.getChildren().addAll(wagonImgView, wagonLabel);
        //FINAL
        gareEtWagon.getChildren().addAll(gare, wagon);
        gareEtWagon.setTranslateX(75);
        destigarewagon.getChildren().addAll(destinations, gareEtWagon);

        //TOP
        top.getChildren().addAll(nameandavatarimg, destigarewagon);
        this.setTop(top);



        /*
        MIDDLE
         */
        center = new VBox();
        cardTitle = new Label("Vos Cartes:");
        cardTitle.setFont(Font.font("QuickSand", FontPosture.REGULAR, 18));
        cardTitle.setStyle("-fx-underline: true");
        cardTitle.setTextFill(Color.WHITE);
        cardAll = new VBox();
        cardAll.setMaxWidth(this.getWidth());
        cardAll.setPrefHeight(this.getWidth());
        cardAll.setMinWidth(this.getWidth());

        center.setTranslateY(20);
        center.setTranslateX(10);
        center.getChildren().addAll(cardTitle, cardAll);
        this.setCenter(center);


        /*
        BOTTOM
         */
        bottom = new VBox();
        cardSelectTitle = new Label("Cartes sélectionné:");
        cardSelectTitle.setFont(Font.font("QuickSand", FontWeight.MEDIUM, 18));
        cardSelectTitle.setStyle("-fx-underline: true");
        cardSelectTitle.setTextFill(Color.WHITE);
        allCardSelected = new HBox();
        allCardSelected.setTranslateX(5);

        bottom.getChildren().addAll(cardSelectTitle, allCardSelected);
        bottom.setTranslateY(-10);
        bottom.setTranslateX(10);
        this.setBottom(bottom);

        this.createBinding();
    }

    public String getNamePlayer() {
        return namePlayer.get();
    }

    public SimpleStringProperty namePlayerProperty() {
        return namePlayer;
    }

    private void createBinding(){
        /*this.widthProperty().addListener(e -> {
            this.gareEtWagon.setTranslateX(this.getWidth()/7.5);
        });

        this.heightProperty().addListener(e -> {
            this.setPrefHeight(this.getHeight()/1.5);
        });*/

        this.jeu.joueurCourantProperty().addListener(e -> {
            this.pseudo.setText(this.jeu.joueurCourantProperty().getValue().getNom());
            this.imgViewAvatar.setImage(new Image("images/avatars/avatar-" + this.jeu.joueurCourantProperty().getValue().getCouleur().toString() + ".png"));
            this.setStyle("-fx-background-color: " + this.convertFrenchColorToEnglishColor(this.jeu.joueurCourantProperty().getValue().getCouleur().toString()));
            this.wagonImgView.setImage(new Image("images/wagons/image-wagon-" + this.jeu.joueurCourantProperty().getValue().getCouleur().toString() + ".png"));
            this.gareImgView.setImage(new Image("images/gares/gare-" + this.jeu.joueurCourantProperty().getValue().getCouleur().toString() + ".png"));

            this.allDestinations.getChildren().clear();
            for(int i=0; i<this.jeu.joueurCourantProperty().getValue().getDestinations().size(); i++){
                Label destinationLabel = new Label(this.jeu.joueurCourantProperty().getValue().getDestinations().get(i).getNom());
                destinationLabel.setFont(Font.font("QuickSand", FontWeight.NORMAL, 16));
                destinationLabel.setTextFill(Color.WHITE);
                this.allDestinations.getChildren().add(destinationLabel);
            }

            Map<CouleurWagon, Integer> cartesJoueur = CouleurWagon.compteur(this.jeu.joueurCourantProperty().getValue().getCartesWagon());
            //DEBUG
            System.out.println("All Card: " + cartesJoueur.toString());
            //DEBUG
            HBox cardOneLigne = new HBox();
            HBox cardTwoLigne = new HBox();
            HBox cardThreeLigne = new HBox();

            int x=0;
            for(int i=0; i<3; i++){
                for(int j=0; j<3; j++){
                    VBox card = new VBox();
                    System.out.println("images/cartesWagons/carte-wagon-" + CouleurWagon.getCouleursNoGris().get(x).toString().toUpperCase() + ".png");
                    ImageView imageCard = new ImageView(new Image("images/cartesWagons/carte-wagon-" + CouleurWagon.getCouleursNoGris().get(x).toString().toUpperCase() + ".png"));
                    imageCard.setFitHeight(55);
                    imageCard.setFitWidth(85);
                    String str = String.valueOf(cartesJoueur.get(CouleurWagon.getCouleursNoGris().get(x)));
                    Label txt = new Label(str);
                    /*Circle backNumber = new Circle();
                    backNumber.setFill(Color.web("#4B4B4B"));
                    backNumber.setRadius(10);
                    StackPane stack = new StackPane();*/
                    if(x==0 || x==3 || x==6){
                        imageCard.setTranslateX(2.5);
                    }else if(x==1 || x==4 || x==7){
                        imageCard.setTranslateX(12.5);
                    }else if(x==2 || x==5 || x==8){
                        imageCard.setTranslateX(20);
                    }

                    //stack.getChildren().addAll(backNumber, txt);
                    System.out.println(cartesJoueur.get(CouleurWagon.getCouleursNoGris().get(x)).toString());
                    card.getChildren().addAll(imageCard, txt);

                    if(x>=0 && x<3){
                        cardOneLigne.getChildren().addAll(card);
                    }else if(x>=3 && x<6){
                        cardTwoLigne.getChildren().addAll(card);
                    }else {
                        cardThreeLigne.getChildren().addAll(card);
                    }

                    int finalX = x;
                    card.setOnMouseClicked(event -> {
                        if(cartesJoueur.get(CouleurWagon.getCouleursNoGris().get(finalX)) > 0){
                            for(int k=0; k<this.jeu.joueurCourantProperty().getValue().getCartesWagon().size(); k++){
                                if(this.jeu.joueurCourantProperty().getValue().getCartesWagon().get(k).equals(CouleurWagon.getCouleursNoGris().get(finalX))){
                                    this.jeu.joueurCourantProperty().getValue().getCartesWagon().remove(k);
                                    System.out.println("OK");
                                }
                            }
                        }else {
                            System.out.println("ERROR: Nombre de carte impossible à enlever dans les cartes du joueurs.");
                        }
                    });
                    x++;
                }
            }
            this.cardAll.getChildren().clear();
            this.cardAll.getChildren().addAll(cardOneLigne, cardTwoLigne, cardThreeLigne);
        });
    }

    private String convertFrenchColorToEnglishColor(String fc){
        String res = "white";
        if(fc.equalsIgnoreCase("rouge")){
            res = "#FF5C5C";
        }else if(fc.equalsIgnoreCase("vert")){
            res = "#00993D";
        }else if(fc.equalsIgnoreCase("rose")){
            res = "#7941B1";
        }else if(fc.equalsIgnoreCase("bleu")){
            res = "#605CFF";
        }else if(fc.equalsIgnoreCase("jaune")){
            res = "#E4CC09";
        }

        return res;
    }
}