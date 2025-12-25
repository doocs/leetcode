function halveArray(nums: number[]): number {
    let s: number = nums.reduce((a, b) => a + b) / 2;
    const pq = new MaxPriorityQueue<number>();
    for (const x of nums) {
        pq.enqueue(x);
    }
    let ans = 0;
    while (s > 0) {
        const t = pq.dequeue() / 2;
        s -= t;
        ++ans;
        pq.enqueue(t);
    }
    return ans;
}
