package Stonks;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

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

        Vector<Invite> invites= UserData.getUserInvites(UserData.getCurrentUser());
        int numberOfInvites = invites.size();

        Scene inviteScene = new Scene(inviteLayout, 600, 300);
        inviteWindow.setScene(inviteScene);
        inviteWindow.show();

        if(numberOfInvites == 0)
        {
            Label l = new Label("No Invitations Pending");
            inviteLayout.add(l, 0, 0);
        }

        else
        {
            Label receivedInvites[] = new Label[numberOfInvites];

            for(int i = 0; i < numberOfInvites; i++)
            {
                receivedInvites[i] = new Label();
                int senderId= invites.get(i).getSenderId(),recordId= invites.get(i).getRecordId();
                String senderName= UserData.getUsername(senderId), recordName= RecordData.getRecordName(recordId);
                receivedInvites[i].setText(senderName + " has sent you an invite to join " + recordName);
            }

            Button accept[] = new Button[numberOfInvites];
            Button reject[] = new Button[numberOfInvites];

            Label invitesIntro = new Label("Invitations received");

            inviteLayout.add(invitesIntro, 0, 0);

            for(int i = 0; i < numberOfInvites; i++)
            {
                int senderId= invites.get(i).getSenderId(),recordId= invites.get(i).getRecordId();
                String senderName= UserData.getUsername(senderId), recordName= RecordData.getRecordName(recordId);
                inviteLayout.add(receivedInvites[i], 0, i+1);
                accept[i] = new Button("Accept");
                reject[i] = new Button("Reject");
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
