class ParkingSystem {

    private int[] spaces = new int[3];

    public ParkingSystem(int big, int medium, int small) {
        spaces[0] = big;
        spaces[1] = medium;
        spaces[2] = small;
    }
    
    public boolean addCar(int carType) {
        if (spaces[carType - 1] <= 0) {
            return false;
        }
        --spaces[carType - 1];
        return true;
    }
}

/**
 * Your ParkingSystem object will be instantiated and called as such:
 * ParkingSystem obj = new ParkingSystem(big, medium, small);
 * boolean param_1 = obj.addCar(carType);
 */