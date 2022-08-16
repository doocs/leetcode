class OrderedStream {
    private ptr: number;
    private vals: string[];

    constructor(n: number) {
        this.ptr = 0;
        this.vals = new Array(n);
    }

    insert(idKey: number, value: string): string[] {
        this.vals[idKey - 1] = value;
        const res = [];
        while (this.vals[this.ptr] != null) {
            res.push(this.vals[this.ptr]);
            this.ptr++;
        }
        return res;
    }
}

/**
 * Your OrderedStream object will be instantiated and called as such:
 * var obj = new OrderedStream(n)
 * var param_1 = obj.insert(idKey,value)
 */
