class Allocator {
    private m: number[];

    constructor(n: number) {
        this.m = Array(n).fill(0);
    }

    allocate(size: number, mID: number): number {
        let cnt = 0;
        for (let i = 0; i < this.m.length; i++) {
            if (this.m[i] > 0) {
                cnt = 0;
            } else if (++cnt === size) {
                for (let j = i - size + 1; j <= i; j++) {
                    this.m[j] = mID;
                }
                return i - size + 1;
            }
        }
        return -1;
    }

    freeMemory(mID: number): number {
        let ans = 0;
        for (let i = 0; i < this.m.length; i++) {
            if (this.m[i] === mID) {
                this.m[i] = 0;
                ans++;
            }
        }
        return ans;
    }
}

/**
 * Your Allocator object will be instantiated and called as such:
 * var obj = new Allocator(n)
 * var param_1 = obj.allocate(size,mID)
 * var param_2 = obj.freeMemory(mID)
 */
