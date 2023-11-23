function maxGcdSum(nums: number[], k: number): number {
    const n: number = nums.length;
    const s: number[] = Array(n + 1).fill(0);
    for (let i = 1; i <= n; i++) {
        s[i] = s[i - 1] + nums[i - 1];
    }

    let f: [number, number][] = [];
    let ans: number = 0;

    for (let i = 0; i < n; ++i) {
        const g: [number, number][] = [];
        for (const [j, x] of f) {
            const y: number = gcd(x, nums[i]);
            if (g.length === 0 || g.at(-1)[1] !== y) {
                g.push([j, y]);
            }
        }
        f = g;
        f.push([i, nums[i]]);
        for (const [j, x] of f) {
            if (i - j + 1 >= k) {
                ans = Math.max(ans, (s[i + 1] - s[j]) * x);
            }
        }
    }

    return ans;
}

function gcd(a: number, b: number): number {
    return b === 0 ? a : gcd(b, a % b);
}
