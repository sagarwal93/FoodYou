package com.ncr.foodyou.models;

/**
 * Created by Samarth on 9/4/15
 */
public class Reward {

    public enum RewardType {fee, coupon}

    private RewardType Type;
    private String Description;
    private double Amount;

    public Reward(RewardType rewardType, String description, double amount) {
        Type = rewardType;
        Description = description;
        Amount = amount;
    }

    public String getTypeString() {
        return "";
    }

    public String getDescription() {
        return Description;
    }
}
