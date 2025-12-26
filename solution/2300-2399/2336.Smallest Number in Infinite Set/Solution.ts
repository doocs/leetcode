class SmallestInfiniteSet {
    private pq = new MinPriorityQueue<number>();
    private s = new Set<number>();

    constructor() {
        for (let i = 1; i <= 1000; i++) {
            this.pq.enqueue(i);
            this.s.add(i);
        }
    }

    popSmallest(): number {
        const x = this.pq.dequeue();
        this.s.delete(x);
        return x;
    }

    addBack(num: number): void {
        if (!this.s.has(num)) {
            this.pq.enqueue(num);
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
