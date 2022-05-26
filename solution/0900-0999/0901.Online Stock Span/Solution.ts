class StockSpanner {
    stack: number[][];
    constructor() {
        this.stack = [];
    }

    next(price: number): number {
        let ans = 1;
        while (this.stack.length > 0 && this.stack[0][0] <= price) {
            let [p, c] = this.stack.shift();
            ans += c;
        }
        this.stack.unshift([price, ans]);
        return ans;
    }
}

/**
 * Your StockSpanner object will be instantiated and called as such:
 * var obj = new StockSpanner()
 * var param_1 = obj.next(price)
 */
