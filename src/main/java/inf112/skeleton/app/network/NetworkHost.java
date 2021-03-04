package inf112.skeleton.app.network;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import inf112.skeleton.app.game.objects.Card;
import inf112.skeleton.app.libgdx.NetworkDataWrapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class NetworkHost extends Network {

    Server server;
    public Connection[] connections;
    // Map connection ID's to cards players chose
    public HashMap<Integer, List<Card>> playerCards = new HashMap<>();

    // Random number, and a poor implementation
    // Just grabbing a random negative number so that it doesn't clash with connection.getID()
    public static int hostID = -230230;

    boolean Initialized = false;

    // Initialize internet
    @Override
    public boolean initialize(){
        server = new Server();
        registerClasses(server);
        server.start();
        server.addListener(new Listener() {
            @Override
            public void received (Connection c, Object object) {
                // Only cards get sent through here
                if (object instanceof CardList) {
                    System.out.println("Recieved cards from client.");
                    playerCards.put(c.getID(),((CardList) object).cardList);
                }
            }
        });

        try {
            this.server.bind(54555);
            Initialized = true;

            return true;
        }
        catch (IOException e){
            System.out.println("Encountered an exception during binding");
            return false;
        }
    }

    /**
     * Sends MapLayerWrapper to all clients
     * @param wrapper map layer data that should be sent
     */
    public void sendMapLayerWrapper(NetworkDataWrapper wrapper) {
        server.sendToAllTCP(wrapper);
    }

    /**
     * Prompts all connected clients to draw cards
     */
    public void promptCardDraw() {
        System.out.println("Sent it");
        server.sendToAllTCP("Draw cards!");
    }

    /**
     * Initializes the connections for the server. Call this only when all users are connected.
     * It also sends the IDs to the clients.
     */
    public void initConnections() {
        connections = server.getConnections();
        for (Connection c : connections) {
            server.sendToTCP(c.getID(), c.getID());
        }
    }

}
