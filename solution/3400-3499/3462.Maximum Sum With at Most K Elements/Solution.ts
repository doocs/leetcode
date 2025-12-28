function maxSum(grid: number[][], limits: number[], k: number): number {
    const pq = new MinPriorityQueue<number>();
    const n = grid.length;
    for (let i = 0; i < n; i++) {
        const nums = grid[i];
        const limit = limits[i];
        nums.sort((a, b) => a - b);
        for (let j = 0; j < limit; j++) {
            pq.enqueue(nums[nums.length - j - 1]);
            if (pq.size() > k) {
                pq.dequeue();
            }
        }
    }
    let ans = 0;
    while (!pq.isEmpty()) {
        ans += pq.dequeue();
    }
    return ans;
}
