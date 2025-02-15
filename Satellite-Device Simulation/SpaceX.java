package unsw.blackout;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SpaceX extends Satellite {
    private List<Device> connectedDevices = new ArrayList<Device>();

    public SpaceX(String id, String type, double height, double position) {
        super(id, type, height, position);
        setVelocity(55.5);
        addSupportedDevice("HandheldDevice");
        addSupportedDevice("MobileXPhone");
    }
    @Override
    public void validateConnections(LocalTime currentTime) {
        Iterator<Device> itr = connectedDevices.iterator();
        while (itr.hasNext()) {
            Device d = itr.next();
            if (!d.inActivationPeriod(currentTime) || !isVisible(d)) {
                disconnect(d, currentTime, this);
                itr.remove();
            }
        }
    }

    @Override
    public void connect(LocalTime currentTime, Device d, int timeToConnect) {
        int ttc = calcTimetoConnect(timeToConnect);

        connection newConnection = new connection(d.getId(), currentTime, getId(), ttc);
        addConnection(newConnection);
        connectedDevices.add(d);
    }

    @Override
    public int calcTimetoConnect(int timeToConnect) {
        return 0;
    }
}
