function possibleStringCount(word: string, k: number): number {
    const mod = 1_000_000_007;
    const nums: number[] = [];
    let ans = 1;
    let cur = 0;
    const n = word.length;

    for (let i = 0; i < n; i++) {
        cur++;
        if (i === n - 1 || word[i] !== word[i + 1]) {
            if (cur > 1) {
                if (k > 0) {
                    nums.push(cur - 1);
                }
                ans = (ans * cur) % mod;
            }
            cur = 0;
            k--;
        }
    }

    if (k < 1) {
        return ans;
    }

    const m = nums.length;
    const f: number[][] = Array.from({ length: m + 1 }, () => Array(k).fill(0));
    f[0][0] = 1;

    for (let i = 1; i <= m; i++) {
        const x = nums[i - 1];
        const s: number[] = Array(k + 1).fill(0);
        for (let j = 0; j < k; j++) {
            s[j + 1] = (s[j] + f[i - 1][j]) % mod;
        }
        for (let j = 0; j < k; j++) {
            const l = Math.max(0, j - x);
            f[i][j] = (s[j + 1] - s[l] + mod) % mod;
        }
    }

    let sum = 0;
    for (let j = 0; j < k; j++) {
        sum = (sum + f[m][j]) % mod;
    }

    return (ans - sum + mod) % mod;
}
