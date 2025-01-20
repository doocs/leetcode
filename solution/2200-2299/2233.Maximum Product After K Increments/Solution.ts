function maximumProduct(nums: number[], k: number): number {
    const pq = new MinPriorityQueue();
    nums.forEach(x => pq.enqueue(x));
    while (k--) {
        const x = pq.dequeue().element;
        pq.enqueue(x + 1);
    }
    let ans = 1;
    const mod = 10 ** 9 + 7;
    while (!pq.isEmpty()) {
        ans = (ans * pq.dequeue().element) % mod;
    }
    return ans;
}
