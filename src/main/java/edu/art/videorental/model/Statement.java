package edu.art.videorental.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Statement {
    private String customerName;
    private List<Charge> chargeList = new ArrayList<>();
    private double totalCharge;

    public void addCharge(Charge charge) {
        chargeList.add(charge);
    }

    @Getter
    @Setter
    @Builder
    public static class Charge{
        private String movieName;
        private double charge;
    }

}
