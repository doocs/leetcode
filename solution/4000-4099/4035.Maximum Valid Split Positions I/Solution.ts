function maxValidSplits(nums: number[]): number {
    const n = nums.length;
    const gcd = (a: number, b: number): number => (b === 0 ? a : gcd(b, a % b));
    const calc = (arr: number[]): number => {
        const m = arr.length;
        const pre: number[] = Array(m).fill(0);
        const suf: number[] = Array(m).fill(0);
        pre[0] = arr[0];
        for (let i = 1; i < m; ++i) {
            pre[i] = gcd(pre[i - 1], arr[i]);
        }
        suf[m - 1] = arr[m - 1];
        for (let i = m - 2; i >= 0; --i) {
            suf[i] = gcd(suf[i + 1], arr[i]);
        }
        let ans = 0;
        for (let i = 0; i < m - 1; ++i) {
            if (pre[i] === suf[i + 1]) {
                ++ans;
            }
        }
        return ans;
    };
    let ans = 0;
    for (let del = -1; del < n; ++del) {
        const arr: number[] = [];
        for (let i = 0; i < n; ++i) {
            if (i !== del) {
                arr.push(nums[i]);
            }
        }
        ans = Math.max(ans, calc(arr));
    }
    return ans;
}
