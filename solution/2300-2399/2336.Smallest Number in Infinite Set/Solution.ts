class SmallestInfiniteSet {
    private hashMap: boolean[];

    constructor() {
        this.hashMap = new Array(1001).fill(true);
    }

    popSmallest(): number {
        for (let i = 1; i <= 1001; i++) {
            if (this.hashMap[i]) {
                this.hashMap[i] = false;
                return i;
            }
        }
        return -1;
    }

    addBack(num: number): void {
        if (!this.hashMap[num]) {
            this.hashMap[num] = true;
        }
    }
}

/**
 * Your SmallestInfiniteSet object will be instantiated and called as such:
 * var obj = new SmallestInfiniteSet()
 * var param_1 = obj.popSmallest()
 * obj.addBack(num)
 */
