package Stonks.Pages;

import Stonks.Records.RecordData;
import Stonks.Users.Invite;
import Stonks.Users.UserData;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.Vector;

public class InvitePage
{
    public static void display()
    {
        Stage inviteWindow = new Stage();

        GridPane inviteLayout = new GridPane();
        inviteLayout.setVgap(10);
        inviteLayout.setHgap(10);
        inviteLayout.setPadding(new Insets(10, 10, 10, 10));
        inviteLayout.setAlignment(Pos.CENTER);

        Image img2 = null;
        img2 = new Image("Background3.jpg");
        BackgroundImage myBI2= new BackgroundImage(img2,
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        inviteLayout.setBackground(new Background(myBI2));

        Vector<Invite> invites= UserData.getUserInvites(UserData.getCurrentUser());
        int numberOfInvites = invites.size();

        Scene inviteScene = new Scene(inviteLayout, 600, 600);
        inviteWindow.setScene(inviteScene);
        inviteWindow.show();

        if(numberOfInvites == 0)
        {
            Label l = new Label("No Invitations Pending");
            l.setTextFill(Color.rgb(189,183,107));
            inviteLayout.add(l, 0, 0);
        }

        else
        {
            Label receivedInvites[] = new Label[numberOfInvites];

            for(int i = 0; i < numberOfInvites; i++)
            {
                receivedInvites[i] = new Label();
                receivedInvites[i].setTextFill(Color.rgb(189,183,107));
                int senderId= invites.get(i).getSenderId(),recordId= invites.get(i).getRecordId();
                String senderName= UserData.getUsername(senderId), recordName= RecordData.getRecordName(recordId);
                receivedInvites[i].setText(senderName + " has sent you an invite to join " + recordName);
            }

            Button accept[] = new Button[numberOfInvites];
            Button reject[] = new Button[numberOfInvites];

            Label invitesIntro = new Label("Invitations received");
            invitesIntro.setTextFill(Color.rgb(189,183,107));



            inviteLayout.add(invitesIntro, 0, 0);

            for(int i = 0; i < numberOfInvites; i++)
            {
                int senderId= invites.get(i).getSenderId(),recordId= invites.get(i).getRecordId();
                String senderName= UserData.getUsername(senderId), recordName= RecordData.getRecordName(recordId);
                inviteLayout.add(receivedInvites[i], 0, i+1);
                accept[i] = new Button("Accept");
                accept[i].setStyle("-fx-font: 15 Serif; -fx-base: #32CD32; ");
                reject[i] = new Button("Reject");
                reject[i].setStyle("-fx-font: 15 Serif; -fx-base: #FF6347; ");
                inviteLayout.add(accept[i], 1,i+1);
                inviteLayout.add(reject[i], 2, i+1);

                int finalI = i;
                accept[i].setOnAction(e -> {
                    RecordData.grantRecordAccess(recordId,UserData.getCurrentUser());
                    invites.remove(finalI);
                    inviteWindow.close();
                    InvitePage.display();
                });

                reject[i].setOnAction(e -> {
                    invites.remove(finalI);
                    inviteWindow.close();
                    InvitePage.display();
                });
            }
        }
    }
}
