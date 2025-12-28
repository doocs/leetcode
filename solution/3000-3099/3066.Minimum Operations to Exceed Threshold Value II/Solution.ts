function minOperations(nums: number[], k: number): number {
    const pq = new MinPriorityQueue<number>();
    for (const x of nums) {
        pq.enqueue(x);
    }
    let ans = 0;
    for (; pq.size() > 1 && pq.front() < k; ++ans) {
        const x = pq.dequeue();
        const y = pq.dequeue();
        pq.enqueue(x * 2 + y);
    }
    return ans;
}
