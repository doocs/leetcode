function halveArray(nums: number[]): number {
    let s: number = nums.reduce((a, b) => a + b) / 2;
    const pq = new MaxPriorityQueue();
    for (const x of nums) {
        pq.enqueue(x, x);
    }
    let ans = 0;
    while (s > 0) {
        const t = pq.dequeue().element / 2;
        s -= t;
        ++ans;
        pq.enqueue(t, t);
    }
    return ans;
}
