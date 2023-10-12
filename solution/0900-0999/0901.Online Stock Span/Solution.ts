class StockSpanner {
    private stk: number[][];

    constructor() {
        this.stk = [];
    }

    next(price: number): number {
        let cnt = 1;
        while (this.stk.length && this.stk.at(-1)[0] <= price) {
            cnt += this.stk.pop()[1];
        }
        this.stk.push([price, cnt]);
        return cnt;
    }
}

/**
 * Your StockSpanner object will be instantiated and called as such:
 * var obj = new StockSpanner()
 * var param_1 = obj.next(price)
 */
