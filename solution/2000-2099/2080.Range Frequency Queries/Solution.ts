class RangeFreqQuery {
    private g: Map<number, number[]> = new Map();

    constructor(arr: number[]) {
        for (let i = 0; i < arr.length; ++i) {
            if (!this.g.has(arr[i])) {
                this.g.set(arr[i], []);
            }
            this.g.get(arr[i])!.push(i);
        }
    }

    query(left: number, right: number, value: number): number {
        const idx = this.g.get(value);
        if (!idx) {
            return 0;
        }
        const l = _.sortedIndex(idx, left);
        const r = _.sortedIndex(idx, right + 1);
        return r - l;
    }
}

/**
 * Your RangeFreqQuery object will be instantiated and called as such:
 * var obj = new RangeFreqQuery(arr)
 * var param_1 = obj.query(left,right,value)
 */
