public class ParkingSystem {

    private List<int> cnt;
    
    public ParkingSystem(int big, int medium, int small) {
        cnt = new List<int>() {0 , big, medium, small};
    }
    
    public bool AddCar(int carType) {
        if (cnt[carType] == 0) {
            return false;
        }
        --cnt[carType];
        return true;
    }
}

/**
 * Your ParkingSystem object will be instantiated and called as such:
 * ParkingSystem obj = new ParkingSystem(big, medium, small);
 * bool param_1 = obj.AddCar(carType);
 */