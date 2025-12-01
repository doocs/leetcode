function maxSum(nums: number[], threshold: number[]): number {
    const n = nums.length;
    const idx = Array.from({ length: n }, (_, i) => i).sort((a, b) => threshold[a] - threshold[b]);
    const pq = new MaxPriorityQueue<number>();
    let ans = 0;
    for (let i = 0, step = 1; ; ++step) {
        while (i < n && threshold[idx[i]] <= step) {
            pq.enqueue(nums[idx[i]]);
            ++i;
        }
        if (pq.isEmpty()) {
            break;
        }
        ans += pq.dequeue();
    }
    return ans;
}
