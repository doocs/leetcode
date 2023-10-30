function sumCounts(nums: number[]): number {
    let ans = 0;
    const n = nums.length;
    for (let i = 0; i < n; ++i) {
        const s: number[] = Array(101).fill(0);
        let cnt = 0;
        for (const x of nums.slice(i)) {
            if (++s[x] === 1) {
                ++cnt;
            }
            ans += cnt * cnt;
        }
    }
    return ans;
}
