class DataStream {
    private val: number;
    private k: number;
    private cnt: number;

    constructor(value: number, k: number) {
        this.val = value;
        this.k = k;
        this.cnt = 0;
    }

    consec(num: number): boolean {
        this.cnt = this.val === num ? this.cnt + 1 : 0;
        return this.cnt >= this.k;
    }
}

/**
 * Your DataStream object will be instantiated and called as such:
 * var obj = new DataStream(value, k)
 * var param_1 = obj.consec(num)
 */
