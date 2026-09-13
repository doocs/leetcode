const ps: number[][] = [[], []];

for (let i = 1; i <= 10 ** 5; i++) {
    const s = String(i);
    const t1 = [...s].reverse().join('');
    const t2 = [...s.slice(0, -1)].reverse().join('');
    const x = Number(s + t1);
    ps[x & 1].push(x);
    const y = Number(s + t2);
    ps[y & 1].push(y);
}
ps[0].sort((a, b) => a - b);
ps[1].sort((a, b) => a - b);

function minOperations(nums: number[]): number {
    let ans = 0;
    for (const x of nums) {
        const p = ps[x & 1];
        let l = 0;
        let r = p.length;
        while (l < r) {
            const m = (l + r) >> 1;
            if (p[m] < x) {
                l = m + 1;
            } else {
                r = m;
            }
        }
        let t = Number.MAX_SAFE_INTEGER;
        if (l < p.length) {
            t = p[l] - x;
        }
        if (l > 0) {
            t = Math.min(t, x - p[l - 1]);
        }
        ans += Math.floor(t / 2);
    }
    return ans;
}
