package Stonks.Pages;

import Stonks.MemberConstants;
import Stonks.Records.RecordData;
import Stonks.Users.UserData;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Vector;

public class RecordMemberPage
{
    public static void display(Stage prev)
    {
        Stage recordMemberWindow = new Stage();

        GridPane topRecordMemberLayout = new GridPane();
        topRecordMemberLayout.setPadding(new Insets(10, 10, 10, 10));
        topRecordMemberLayout.setVgap(10);
        topRecordMemberLayout.setHgap(10);
        topRecordMemberLayout.setAlignment(Pos.CENTER);

        Label memberLabel = new Label("Members in this book");
        memberLabel.setTextFill(Color.rgb(189,183,107));
        memberLabel.setFont(new Font("Arial", 30));
        topRecordMemberLayout.add(memberLabel, 0, 0);


        GridPane centralRecordMemberLayout = new GridPane();
        centralRecordMemberLayout.setHgap(10);
        centralRecordMemberLayout.setVgap(10);
        centralRecordMemberLayout.setPadding(new Insets(10, 10, 10, 10));

        Vector<Integer> recordMemberId = RecordData.getRecordUsers(RecordData.getCurrentRecord());

        Label memberNames[] = new Label[recordMemberId.size()];
        Label memberStatus[] = new Label[recordMemberId.size()];


        for(int i = 0; i < recordMemberId.size(); i++)
        {
            //int cur= UserData.getCurrentUser(),tar= recordMemberId.get(i);
            int targetType= RecordData.getRecordMemberStatus(RecordData.getCurrentRecord(),recordMemberId.get(i));
            String memberName= UserData.getUsername(recordMemberId.get(i));
            memberNames[i] = new Label(memberName);
            memberNames[i].setTextFill(Color.rgb(189,183,107));
            centralRecordMemberLayout.add(memberNames[i], 0, i);
            String status = "Alpha";
            double p1=0.5,p2=0,p3=0;

            if(RecordData.getRecordMemberStatus(RecordData.getCurrentRecord(),recordMemberId.get(i))== MemberConstants.SIGMA){
                status= "Sigma";
                p1=0;
                p2=0;
                p3=0.5;
            }
            else if(RecordData.getRecordMemberStatus(RecordData.getCurrentRecord(),recordMemberId.get(i))==MemberConstants.OMEGA){
                status= "Omega";
                p1=0;
                p2=0.5;
                p3=0;
            }

            memberStatus[i] = new Label("("+status+")");
            memberStatus[i].setTextFill(Color.color(p1, p2, p3));

            if(RecordData.hasPromoteMemberAccess(RecordData.getCurrentRecord(), UserData.getCurrentUser(), targetType))
            {
                Button promote = new Button("Promote");
                promote.setStyle("-fx-font: 15 Serif; -fx-base: #32CD32; ");
                centralRecordMemberLayout.add(promote, 2, i);

                int finalI = i;
                promote.setOnAction(e -> {
                    RecordData.promoteRecordMember(RecordData.getCurrentRecord(), recordMemberId.get(finalI));
                    recordMemberWindow.close();
                    RecordMemberPage.display(prev);
                });
            }

            if(RecordData.hasDemoteMemberAccess(RecordData.getCurrentRecord(), UserData.getCurrentUser(), targetType))
            {
                Button demote = new Button("Demote");
                demote.setStyle("-fx-font: 15 Serif; -fx-base: #FF6347; ");
                centralRecordMemberLayout.add(demote, 3, i);

                int finalI = i;
                demote.setOnAction(e -> {
                    RecordData.demoteRecordMember(RecordData.getCurrentRecord(), recordMemberId.get(finalI));
                    recordMemberWindow.close();
                    RecordMemberPage.display(prev);
                });
            }

            if(RecordData.hasRemoveMemberAccess(RecordData.getCurrentRecord(), UserData.getCurrentUser(), targetType))
            {
                Button remove = new Button("Remove");
                centralRecordMemberLayout.add(remove, 4, i);

                int finalI = i;
                remove.setOnAction(e -> {
                    RecordData.removeRecordMember(RecordData.getCurrentRecord(), recordMemberId.get(finalI));
                    recordMemberWindow.close();
                    RecordMemberPage.display(prev);
                });
            }

            centralRecordMemberLayout.add(memberStatus[i], 1, i);
        }



        BorderPane finalRecordMemberLayout = new BorderPane();
        BorderPane.setAlignment(centralRecordMemberLayout, Pos.CENTER);

        finalRecordMemberLayout.setCenter(centralRecordMemberLayout);
        finalRecordMemberLayout.setTop(topRecordMemberLayout);


        Image img2 = null;
        try {
            img2 = new Image(new FileInputStream("Background3.jpg"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        BackgroundImage myBI2= new BackgroundImage(img2,
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        finalRecordMemberLayout.setBackground(new Background(myBI2));


        Scene recordMemberScene = new Scene(finalRecordMemberLayout, 600, 300);
        recordMemberWindow.setScene(recordMemberScene);
        recordMemberWindow.show();
    }
}
