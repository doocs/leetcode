function minMirrorPairDistance(nums: number[]): number {
    const n = nums.length;
    const pos = new Map<number, number>();
    let ans = n + 1;
    const reverse = (x: number) => {
        let y = 0;
        for (; x > 0; x = Math.floor(x / 10)) {
            y = y * 10 + (x % 10);
        }
        return y;
    };
    for (let i = 0; i < n; ++i) {
        if (pos.has(nums[i])) {
            const j = pos.get(nums[i])!;
            ans = Math.min(ans, i - j);
        }
        pos.set(reverse(nums[i]), i);
    }
    return ans > n ? -1 : ans;
}
