class SeatManager {
    private q: typeof MinPriorityQueue;
    constructor(n: number) {
        this.q = new MinPriorityQueue();
        for (let i = 1; i <= n; i++) {
            this.q.enqueue(i);
        }
    }

    reserve(): number {
        return this.q.dequeue().element;
    }

    unreserve(seatNumber: number): void {
        this.q.enqueue(seatNumber);
    }
}

/**
 * Your SeatManager object will be instantiated and called as such:
 * var obj = new SeatManager(n)
 * var param_1 = obj.reserve()
 * obj.unreserve(seatNumber)
 */
