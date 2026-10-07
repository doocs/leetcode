function minimumScore(nums: number[], edges: number[][]): number {
    const n = nums.length;
    const g: number[][] = Array.from({ length: n }, () => []);
    for (const [a, b] of edges) {
        g[a].push(b);
        g[b].push(a);
    }
    const s = nums.reduce((a, b) => a ^ b, 0);
    const componentXor = (root: number, ban: number): number => {
        const sub = Array(n).fill(0);
        const stk: number[][] = [[root, ban, 0]];
        while (stk.length) {
            const [i, fa, state] = stk.pop()!;
            if (state === 0) {
                stk.push([i, fa, 1]);
                for (const j of g[i]) {
                    if (j !== fa) {
                        stk.push([j, i, 0]);
                    }
                }
            } else {
                let res = nums[i];
                for (const j of g[i]) {
                    if (j !== fa) {
                        res ^= sub[j];
                    }
                }
                sub[i] = res;
            }
        }
        return sub[root];
    };
    const collect = (root: number, ban: number, s1: number): number => {
        let ans = Number.MAX_SAFE_INTEGER;
        const sub = Array(n).fill(0);
        const stk: number[][] = [[root, ban, 0]];
        while (stk.length) {
            const [i, fa, state] = stk.pop()!;
            if (state === 0) {
                stk.push([i, fa, 1]);
                for (const j of g[i]) {
                    if (j !== fa) {
                        stk.push([j, i, 0]);
                    }
                }
            } else {
                let res = nums[i];
                for (const j of g[i]) {
                    if (j !== fa) {
                        const s2 = sub[j];
                        res ^= s2;
                        const mx = Math.max(s ^ s1, s2, s1 ^ s2);
                        const mn = Math.min(s ^ s1, s2, s1 ^ s2);
                        ans = Math.min(ans, mx - mn);
                    }
                }
                sub[i] = res;
            }
        }
        return ans;
    };
    let ans = Number.MAX_SAFE_INTEGER;
    for (let i = 0; i < n; ++i) {
        for (const j of g[i]) {
            const s1 = componentXor(i, j);
            ans = Math.min(ans, collect(i, j, s1));
        }
    }
    return ans;
}
