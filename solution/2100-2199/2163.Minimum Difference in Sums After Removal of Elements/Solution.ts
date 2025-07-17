function minimumDifference(nums: number[]): number {
    const m = nums.length;
    const n = Math.floor(m / 3);
    let s = 0;
    const pre: number[] = Array(m + 1);
    const q1 = new MaxPriorityQueue<number>();
    for (let i = 1; i <= n * 2; ++i) {
        const x = nums[i - 1];
        s += x;
        q1.enqueue(x);
        if (q1.size() > n) {
            s -= q1.dequeue();
        }
        pre[i] = s;
    }
    s = 0;
    const suf: number[] = Array(m + 1);
    const q2 = new MinPriorityQueue<number>();
    for (let i = m; i > n; --i) {
        const x = nums[i - 1];
        s += x;
        q2.enqueue(x);
        if (q2.size() > n) {
            s -= q2.dequeue();
        }
        suf[i] = s;
    }
    let ans = Number.MAX_SAFE_INTEGER;
    for (let i = n; i <= n * 2; ++i) {
        ans = Math.min(ans, pre[i] - suf[i + 1]);
    }
    return ans;
}
