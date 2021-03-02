package inf112.skeleton.app.game;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import inf112.skeleton.app.game.objects.Card;

import com.esotericsoftware.kryonet.rmi.ObjectSpace;
import inf112.skeleton.app.libgdx.Game;
import inf112.skeleton.app.network.NetworkClient;
import inf112.skeleton.app.network.NetworkData;
import inf112.skeleton.app.network.cardList;

import java.util.ArrayList;

public class GameClient extends GamePlayer {

    public GameClient(NetworkClient network) {
        super.GamePlayer();
        client = network;
        connectionID = client.client.getID();
        new ObjectSpace(this).register(NetworkData.GameClient, this);
    }
    NetworkClient client;
    // Client connection ID
    int connectionID;

    /**
     * Send clients chosen card to NetworkHost
     */
    @Override
    public void registerChosenCards() {
        cardList listOfCards = new cardList();
        listOfCards.cardList = chosenCards;
        client.client.sendTCP(listOfCards);
    }

    @Override
    public void drawCards() {
        // nothing happens here :o
    }
}
