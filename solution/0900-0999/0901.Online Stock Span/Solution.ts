class StockSpanner {
    private stack: [number, number][];

    constructor() {
        this.stack = [[Infinity, -1]];
    }

    next(price: number): number {
        let res = 1;
        while (this.stack[this.stack.length - 1][0] <= price) {
            res += this.stack.pop()[1];
        }
        this.stack.push([price, res]);
        return res;
    }
}

/**
 * Your StockSpanner object will be instantiated and called as such:
 * var obj = new StockSpanner()
 * var param_1 = obj.next(price)
 */
