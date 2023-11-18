function maximumSum(nums: number[]): number {
    const d: number[] = Array(100).fill(0);
    let ans = -1;
    for (const v of nums) {
        let x = 0;
        for (let y = v; y; y = (y / 10) | 0) {
            x += y % 10;
        }
        if (d[x]) {
            ans = Math.max(ans, d[x] + v);
        }
        d[x] = Math.max(d[x], v);
    }
    return ans;
}
