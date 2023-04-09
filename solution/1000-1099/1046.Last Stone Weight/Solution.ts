function lastStoneWeight(stones: number[]): number {
    const pq = new MaxPriorityQueue();
    for (const x of stones) {
        pq.enqueue(x);
    }
    while (pq.size() > 1) {
        const y = pq.dequeue().element;
        const x = pq.dequeue().element;
        if (x !== y) {
            pq.enqueue(y - x);
        }
    }
    return pq.isEmpty() ? 0 : pq.dequeue().element;
}
