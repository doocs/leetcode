class MedianFinder {
    #minQ = new MinPriorityQueue<number>();
    #maxQ = new MaxPriorityQueue<number>();

    addNum(num: number): void {
        const [minQ, maxQ] = [this.#minQ, this.#maxQ];
        maxQ.enqueue(num);
        minQ.enqueue(maxQ.dequeue().element);
        if (minQ.size() > maxQ.size()) {
            maxQ.enqueue(minQ.dequeue().element);
        }
    }

    findMedian(): number {
        const [minQ, maxQ] = [this.#minQ, this.#maxQ];
        const median =
            maxQ.size() === minQ.size()
                ? (maxQ.front().element + minQ.front().element) / 2
                : maxQ.front().element;

        return median;
    }
}
