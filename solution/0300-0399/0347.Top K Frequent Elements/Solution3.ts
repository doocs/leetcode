function topKFrequent(nums: number[], k: number): number[] {
    const ans = Array<number>(k);
    const cnt = new Map<number, number>();
    const minPQ = new MinPriorityQueue();

    for (const x of nums) {
        cnt.set(x, (cnt.get(x) ?? 0) + 1);
    }

    for (const [x, c] of cnt) {
        minPQ.enqueue(x, c);
        if (minPQ.size() > k) minPQ.dequeue();
    }

    while (k--) {
        ans[k] = minPQ.dequeue().element;
    }

    return ans;
}
