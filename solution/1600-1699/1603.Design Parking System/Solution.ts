class ParkingSystem {
    private count: [number, number, number];

    constructor(big: number, medium: number, small: number) {
        this.count = [big, medium, small];
    }

    addCar(carType: number): boolean {
        if (this.count[carType - 1] === 0) {
            return false;
        }
        this.count[carType - 1]--;
        return true;
    }
}

/**
 * Your ParkingSystem object will be instantiated and called as such:
 * var obj = new ParkingSystem(big, medium, small)
 * var param_1 = obj.addCar(carType)
 */
