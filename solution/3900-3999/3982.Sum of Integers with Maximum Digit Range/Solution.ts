function maxDigitRange(nums: number[]): number {
    let [ans, mx] = [0, 0];
    for (const x of nums) {
        let [a, b] = [10, 0];
        for (let y = x; y; y = (y / 10) | 0) {
            const v = y % 10;
            a = Math.min(a, v);
            b = Math.max(b, v);
        }
        const r = b - a;
        if (mx < r) {
            mx = r;
            ans = x;
        } else if (mx == r) {
            ans += x;
        }
    }
    return ans;
}
