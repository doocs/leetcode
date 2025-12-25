class SeatManager {
    private q = new MinPriorityQueue<number>();
    constructor(n: number) {
        for (let i = 1; i <= n; i++) {
            this.q.enqueue(i);
        }
    }

    reserve(): number {
        return this.q.dequeue();
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
