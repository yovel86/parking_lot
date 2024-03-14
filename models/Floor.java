package machine_coding.parking_lot.models;

import java.util.List;

public class Floor extends BaseModel {

    private int id;
    private int floorNum;
    private List<Section> sections;
    private FloorStatus floorStatus;

}
