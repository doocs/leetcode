function lastStoneWeight(stones: number[]): number {
    const pq = new MaxPriorityQueue<number>();
    for (const x of stones) {
        pq.enqueue(x);
    }
    while (pq.size() > 1) {
        const y = pq.dequeue();
        const x = pq.dequeue();
        if (x !== y) {
            pq.enqueue(y - x);
        }
    }
    return pq.isEmpty() ? 0 : pq.dequeue();
}
