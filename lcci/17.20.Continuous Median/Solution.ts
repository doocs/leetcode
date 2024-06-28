class MedianFinder {
    #minQ = new MinPriorityQueue();
    #maxQ = new MaxPriorityQueue();

    addNum(num: number): void {
        const [minQ, maxQ] = [this.#minQ, this.#maxQ];
        maxQ.enqueue(num);
        minQ.enqueue(maxQ.dequeue().element);
        if (minQ.size() - maxQ.size() > 1) {
            maxQ.enqueue(minQ.dequeue().element);
        }
    }

    findMedian(): number {
        const [minQ, maxQ] = [this.#minQ, this.#maxQ];
        if (minQ.size() === maxQ.size()) {
            return (minQ.front().element + maxQ.front().element) / 2;
        }
        return minQ.front().element;
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * var obj = new MedianFinder()
 * obj.addNum(num)
 * var param_2 = obj.findMedian()
 */
