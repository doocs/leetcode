class SmallestInfiniteSet {
    private pq: typeof MinPriorityQueue;
    private s: Set<number>;

    constructor() {
        this.pq = new MinPriorityQueue();
        this.s = new Set();
        for (let i = 1; i <= 1000; i++) {
            this.pq.enqueue(i, i);
            this.s.add(i);
        }
    }

    popSmallest(): number {
        const x = this.pq.dequeue()?.element;
        this.s.delete(x);
        return x;
    }

    addBack(num: number): void {
        if (!this.s.has(num)) {
            this.pq.enqueue(num, num);
            this.s.add(num);
        }
    }
}

/**
 * Your SmallestInfiniteSet object will be instantiated and called as such:
 * var obj = new SmallestInfiniteSet()
 * var param_1 = obj.popSmallest()
 * obj.addBack(num)
 */
