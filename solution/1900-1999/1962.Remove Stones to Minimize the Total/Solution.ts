function minStoneSum(piles: number[], k: number): number {
    const pq = new MaxPriorityQueue<number>();
    for (const x of piles) {
        pq.enqueue(x);
    }
    while (k--) {
        pq.enqueue((pq.dequeue() + 1) >> 1);
    }
    return pq.toArray().reduce((a, b) => a + b, 0);
}
