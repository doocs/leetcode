function minStoneSum(piles: number[], k: number): number {
    const pq = new MaxPriorityQueue();
    for (const x of piles) {
        pq.enqueue(x);
    }
    while (k--) {
        pq.enqueue((pq.dequeue().element + 1) >> 1);
    }

    return pq.toArray().reduce((a, b) => a + b.element, 0);
}
