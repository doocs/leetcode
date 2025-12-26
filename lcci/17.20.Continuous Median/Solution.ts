class MedianFinder {
    #minQ = new MinPriorityQueue<number>();
    #maxQ = new MaxPriorityQueue<number>();

    addNum(num: number): void {
        const [minQ, maxQ] = [this.#minQ, this.#maxQ];
        maxQ.enqueue(num);
        minQ.enqueue(maxQ.dequeue());
        if (minQ.size() - maxQ.size() > 1) {
            maxQ.enqueue(minQ.dequeue());
        }
    }

    findMedian(): number {
        const [minQ, maxQ] = [this.#minQ, this.#maxQ];
        if (minQ.size() === maxQ.size()) {
            return (minQ.front() + maxQ.front()) / 2;
        }
        return minQ.front();
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * var obj = new MedianFinder()
 * obj.addNum(num)
 * var param_2 = obj.findMedian()
 */
