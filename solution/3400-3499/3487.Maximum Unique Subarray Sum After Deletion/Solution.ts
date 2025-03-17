function maxSum(nums: number[]): number {
    const mx = Math.max(...nums);
    if (mx <= 0) {
        return mx;
    }
    const s = new Set<number>();
    let ans: number = 0;
    for (const x of nums) {
        if (x < 0 || s.has(x)) {
            continue;
        }
        ans += x;
        s.add(x);
    }
    return ans;
}
