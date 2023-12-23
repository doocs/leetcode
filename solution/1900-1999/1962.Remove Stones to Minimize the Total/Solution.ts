function minStoneSum(piles: number[], k: number): number {
    const pq = new MaxPriorityQueue();
    for (const x of piles) {
        pq.enqueue(x);
    }
    while (k--) {
        const x = pq.dequeue().element;
        pq.enqueue(x - ((x / 2) | 0));
    }
    let ans = 0;
    while (pq.size()) {
        ans += pq.dequeue().element;
    }
    return ans;
}
