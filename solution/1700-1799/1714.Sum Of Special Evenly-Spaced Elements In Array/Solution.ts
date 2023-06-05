function solve(nums: number[], queries: number[][]): number[] {
    const n = nums.length;
    const m = Math.floor(Math.sqrt(n));
    const mod = 10 ** 9 + 7;
    const suf: number[][] = Array(m + 1)
        .fill(0)
        .map(() => Array(n + 1).fill(0));
    for (let i = 1; i <= m; ++i) {
        for (let j = n - 1; j >= 0; --j) {
            suf[i][j] = (suf[i][Math.min(n, j + i)] + nums[j]) % mod;
        }
    }
    const ans: number[] = [];
    for (const [x, y] of queries) {
        if (y <= m) {
            ans.push(suf[y][x]);
        } else {
            let s = 0;
            for (let i = x; i < n; i += y) {
                s = (s + nums[i]) % mod;
            }
            ans.push(s);
        }
    }
    return ans;
}
