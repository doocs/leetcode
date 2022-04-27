class RecentCounter {
    stack: Array<number>;
    cnt: number;
    constructor() {
        this.stack = [];
        this.cnt = 0;
    }

    ping(t: number): number {
        while (this.stack.length && this.stack[0] + 3000 < t) {
            this.cnt--;
            this.stack.shift();
        }
        this.cnt++;
        this.stack.push(t);
        return this.cnt;
    }
}
