class ParkingSystem {
    private cnt: [number, number, number, number];

    constructor(big: number, medium: number, small: number) {
        this.cnt = [0, big, medium, small];
    }

    addCar(carType: number): boolean {
        if (this.cnt[carType] === 0) {
            return false;
        }
        this.cnt[carType]--;
        return true;
    }
}

/**
 * Your ParkingSystem object will be instantiated and called as such:
 * var obj = new ParkingSystem(big, medium, small)
 * var param_1 = obj.addCar(carType)
 */
