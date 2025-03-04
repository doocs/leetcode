function minOperations(nums: number[], k: number): number {
    const pq = new MinPriorityQueue();
    for (const x of nums) {
        pq.enqueue(x);
    }
    let ans = 0;
    for (; pq.size() > 1 && pq.front().element < k; ++ans) {
        const x = pq.dequeue().element;
        const y = pq.dequeue().element;
        pq.enqueue(x * 2 + y);
    }
    return ans;
}
