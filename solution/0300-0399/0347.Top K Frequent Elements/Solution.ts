function topKFrequent(nums: number[], k: number): number[] {
    const cnt = new Map<number, number>();
    for (const x of nums) {
        cnt.set(x, (cnt.get(x) ?? 0) + 1);
    }
    const pq = new MinPriorityQueue();
    for (const [x, c] of cnt) {
        pq.enqueue(x, c);
        if (pq.size() > k) {
            pq.dequeue();
        }
    }
    return pq.toArray().map(x => x.element);
}
