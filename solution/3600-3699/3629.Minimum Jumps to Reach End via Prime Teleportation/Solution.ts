const mx = 1000001;
const factors: number[][] = Array(mx);

for (let i = 0; i < mx; i++) {
    factors[i] = [];
}
for (let i = 2; i < mx; i++) {
    if (factors[i].length === 0) {
        for (let j = i; j < mx; j += i) {
            factors[j].push(i);
        }
    }
}

function minJumps(nums: number[]): number {
    const n = nums.length;
    const g = new Map<number, number[]>();
    for (let i = 0; i < n; i++) {
        const x = nums[i];
        for (const p of factors[x]) {
            if (!g.has(p)) {
                g.set(p, []);
            }
            g.get(p)!.push(i);
        }
    }
    let ans = 0;
    const vis = new Array(n).fill(false);
    vis[0] = true;
    let q: number[] = [0];
    while (true) {
        const nq: number[] = [];
        for (const i of q) {
            if (i === n - 1) {
                return ans;
            }
            const idx = [...(g.get(nums[i]) || [])];
            idx.push(i + 1);
            if (i > 0) {
                idx.push(i - 1);
            }
            for (const j of idx) {
                if (!vis[j]) {
                    vis[j] = true;
                    nq.push(j);
                }
            }
            if (g.has(nums[i])) {
                g.get(nums[i])!.length = 0;
            }
        }
        q = nq;
        ans++;
    }
}
