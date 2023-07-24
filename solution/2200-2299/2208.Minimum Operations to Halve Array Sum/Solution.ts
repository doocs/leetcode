function halveArray(nums: number[]): number {
    let s: number = nums.reduce((a, b) => a + b) / 2;
    const h = new MaxPriorityQueue();
    for (const v of nums) {
        h.enqueue(v, v);
    }
    let ans: number = 0;
    while (s > 0) {
        let { element: t } = h.dequeue();
        t /= 2;
        s -= t;
        h.enqueue(t, t);
        ans += 1;
    }
    return ans;
}
