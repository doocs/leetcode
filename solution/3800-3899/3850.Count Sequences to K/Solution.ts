function countSequences(nums: number[], k: number): number {
    const n = nums.length;
    const f = new Map<string, number>();

    function gcd(a: number, b: number): number {
        while (b !== 0) {
            const t = a % b;
            a = b;
            b = t;
        }
        return a;
    }

    function dfs(i: number, p: number, q: number): number {
        if (i === n) {
            return p === k && q === 1 ? 1 : 0;
        }

        const key = `${i},${p},${q}`;
        if (f.has(key)) return f.get(key)!;

        let res = dfs(i + 1, p, q);

        const x = nums[i];

        const g1 = gcd(p * x, q);
        res += dfs(i + 1, (p * x) / g1, q / g1);

        const g2 = gcd(p, q * x);
        res += dfs(i + 1, p / g2, (q * x) / g2);

        f.set(key, res);
        return res;
    }

    return dfs(0, 1, 1);
}
