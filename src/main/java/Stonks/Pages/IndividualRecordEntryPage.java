package Stonks.Pages;

import Stonks.Entries.EntryData;
import Stonks.Entries.EntryView;
import Stonks.Records.RecordData;
import Stonks.Users.UserData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Vector;

public class IndividualRecordEntryPage
{
    public static void display()
    {
        Stage entryWindow = new Stage();

        HBox individualEntryLayout = new HBox();
        VBox entryLayout = new VBox();
        entryLayout.setPrefSize(800,800);
        VBox interactiveLayout = new VBox();
        interactiveLayout.setPrefSize(400,800);

        VBox buttonLayout = new VBox();
        buttonLayout.setPrefSize(400,300);
        VBox informationLayout = new VBox();
        informationLayout.setPrefSize(400,200);
        VBox visualizationLayout = new VBox();
        visualizationLayout.setPrefSize(400,300);

        Button addEntry = new Button("Add Entry");
        Button back = new Button("Back");
        Button deleteRecord = new Button("Delete Record");

        addEntry.setPrefSize(400,100);
        back.setPrefSize(400,100);
        deleteRecord.setPrefSize(400,100);

        addEntry.setStyle("-fx-font: 15 Serif; -fx-base: #32CD32; ");
        back.setStyle("-fx-font: 15 Serif; -fx-base: #708090; ");
        deleteRecord.setStyle("-fx-font: 15 Serif; -fx-base: #FF6347; ");

        buttonLayout.getChildren().addAll(addEntry,deleteRecord,back);

        addEntry.setOnAction(e -> {
            EntryInputWindow.display(entryWindow, "Individual");
        });

        back.setOnAction(e -> {
            MenuPage.display(entryWindow);
        });

        if(RecordData.hasDeleteRecordAccess(RecordData.getCurrentRecord(), UserData.getCurrentUser()))
        {
            deleteRecord.setOnAction(e -> {
                RecordData.deleteRecord(RecordData.getCurrentRecord());
                RecordData.setCurrentRecord(-1);
                //entryWindow.close();
                MenuPage.display(entryWindow);
            });
        }

        informationLayout.setPadding(new Insets(10, 100, 10, 150));
        Label totalCashIn = new Label("Total Cash In:");
        totalCashIn.setPrefSize(200,30);
        totalCashIn.setTextFill(Color.rgb(189,183,107));  //#EEE8AA

        Label totalCashOut = new Label("Total Cash out:");
        totalCashOut.setPrefSize(200,30);
        totalCashOut.setTextFill(Color.rgb(189,183,107));

        Label netCash = new Label("Net Cash:");
        netCash.setPrefSize(200,30);
        netCash.setTextFill(Color.rgb(189,183,107));

        int totalIn = EntryData.getRecordCashIn(RecordData.getCurrentRecord());
        int totalOut = EntryData.getRecordCashOut(RecordData.getCurrentRecord());
        int net = EntryData.getRecordNet(RecordData.getCurrentRecord());

        Label cashInAmount = new Label(String.valueOf(totalIn));
        cashInAmount.setTextFill(Color.rgb(144,238,144));
        cashInAmount.setPrefSize(200,30);

        Label cashOutAmount = new Label(String.valueOf(totalOut));
        cashOutAmount.setTextFill(Color.rgb(255,99,71));
        cashOutAmount.setPrefSize(200,30);

        Label netAmount = new Label(String.valueOf(net));
        if(net < 0)netAmount.setTextFill(Color.rgb(255,99,71));
        else netAmount.setTextFill(Color.rgb(144,238,144));
        netAmount.setPrefSize(200,30);

        Image img = null;
        try {
            img = new Image(new FileInputStream("Background2.jpg"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        BackgroundImage myBI= new BackgroundImage(img,
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        informationLayout.setBackground(new Background(myBI));

        //informationLayout.setStyle("-fx-background: rgb(255,215,0);\n -fx-background-color: rgb(255,215,0)");
        informationLayout.getChildren().addAll(totalCashIn,cashInAmount,totalCashOut,cashOutAmount,netCash,netAmount);


        Vector<Integer> recordEntries= EntryData.getRecordEntries(RecordData.getCurrentRecord());

        int numberOfEntries = recordEntries.size();

        Label entries[] = new Label[numberOfEntries];
        Button viewButtons[] = new Button[numberOfEntries];
        Label cashAmount[] = new Label[numberOfEntries];
        Label tags[] = new Label[numberOfEntries];

        HBox heading = new HBox();
        heading.setPadding(new Insets(10, 20, 10, 10));
        heading.setSpacing(10);

        Label entryHead = new Label("Remarks");
        entryHead.setPrefSize(400, 40);
        entryHead.setTextFill(Color.rgb(245,222,179));
        Label amountHead = new Label("Amount");
        amountHead.setPrefSize(150,40);
        amountHead.setTextFill(Color.rgb(245,222,179));
        Label tagHead = new Label("Tag");
        tagHead.setTextFill(Color.rgb(245,222,179));
        tagHead.setPrefSize(100,40);

        heading.getChildren().addAll(entryHead,amountHead,tagHead);

        entryLayout.getChildren().add(heading);

        if(numberOfEntries > 0)
        {
            HBox hboxEntries[] = new HBox[numberOfEntries];
            for (int i = 0; i < numberOfEntries; i++) {
                int id = recordEntries.get(i);
                String entryName = EntryData.getEntryName(id);

                entries[i] = new Label(entryName);
                entries[i].setPrefSize(400, 40);
                entries[i].setTextFill(Color.rgb(255,215,0));

                viewButtons[i] = new Button("View");
                viewButtons[i].setStyle("-fx-font: 15 Serif; -fx-base: #B0C4DE; ");
                viewButtons[i].setPrefSize(100,40);

                if (EntryData.isCashIn(id) == true) {
                    String t = String.valueOf(EntryData.getEntryAmount(id));
                    cashAmount[i] = new Label(t);
                    cashAmount[i].setPrefSize(150, 40);
                    cashAmount[i].setTextFill(Color.rgb(144,238,144));
                } else if (EntryData.isCashIn(id) == false) {
                    String t = String.valueOf(EntryData.getEntryAmount(id));
                    cashAmount[i] = new Label(t);
                    cashAmount[i].setPrefSize(150, 40);
                    cashAmount[i].setTextFill(Color.rgb(255,99,71));
                    //cashType[i].setStyle("-fx-background-color: #ff0000; ");
                }

                String temptag = EntryData.getEntryTag(id);

                tags[i] = new Label(temptag);
                tags[i].setPrefSize(100, 40);
                tags[i].setTextFill(Color.rgb(0,191,255));

                viewButtons[i].setOnAction(e -> {
                    EntryData.setCurrentEntry(id);
                    EntryView.display(entryWindow);
                });

                hboxEntries[i] = new HBox();
                hboxEntries[i].setPadding(new Insets(10, 20, 10, 10));
                hboxEntries[i].setSpacing(10);

                hboxEntries[i].getChildren().addAll(entries[i], cashAmount[i], tags[i], viewButtons[i]);
                entryLayout.getChildren().add(hboxEntries[i]);

              // hboxEntries[i].setStyle("-fx-background: rgb(147,112,219);\n -fx-background-color: rgb(147,112,219)");
                //else hboxEntries[i].setStyle("-fx-background: rgb(176,196,222);\n -fx-background-color: rgb(176,196,222)");
            }
        }

       Vector<Pair<String,Integer>> tagList = EntryData.getRecordCashInByTagNames(RecordData.getCurrentRecord());

        ObservableList<PieChart.Data> cashInChartData = FXCollections.observableArrayList();
        System.out.println(tagList.size());
        System.out.println(RecordData.getCurrentRecord());
        for(int i = 0; i < tagList.size(); i++)
        {
            cashInChartData.add(new PieChart.Data(tagList.get(i).getKey(),tagList.get(i).getValue()));
        }

        PieChart cashInChart = new PieChart(cashInChartData);
        cashInChart.setLabelLineLength(30);
        cashInChart.setLabelsVisible(true);
        cashInChart.setStartAngle(90);
        cashInChart.setPrefSize(300,300);

        Group g1 = new Group(cashInChart);
        visualizationLayout.getChildren().add(g1);

        Image img2 = null;
        try {
            img2 = new Image(new FileInputStream("Background3.jpg"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        BackgroundImage myBI2= new BackgroundImage(img2,
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        entryLayout.setBackground(new Background(myBI2));

        ScrollPane scrollEntryLayout = new ScrollPane();
        scrollEntryLayout.setContent(entryLayout);

        interactiveLayout.getChildren().addAll(buttonLayout,informationLayout,visualizationLayout);

        individualEntryLayout.getChildren().addAll(scrollEntryLayout, interactiveLayout);

        Scene individualEntryScene = new Scene(individualEntryLayout, 1200, 800);
        entryWindow.setScene(individualEntryScene);
        entryWindow.show();
    }
}
