import { AvlTree } from 'datastructures-js';

function countPartitions(nums: number[], k: number): number {
    const mod = 1_000_000_007;
    const n = nums.length;

    const cnt = new Map<number, number>();
    const st = new AvlTree<number>((a, b) => a - b);

    function insert(x: number) {
        const c = (cnt.get(x) || 0) + 1;
        cnt.set(x, c);
        if (c === 1) {
            st.insert(x);
        }
    }

    function erase(x: number) {
        const c = cnt.get(x)! - 1;
        cnt.set(x, c);
        if (c === 0) {
            st.remove(x);
        }
    }

    const f = Array(n + 1).fill(0);
    const g = Array(n + 1).fill(0);
    f[0] = 1;
    g[0] = 1;

    for (let l = 1, r = 1; r <= n; ++r) {
        const x = nums[r - 1];
        insert(x);

        while (st.max()!.getValue() - st.min()!.getValue() > k) {
            erase(nums[l - 1]);
            l++;
        }

        f[r] = (g[r - 1] - (l >= 2 ? g[l - 2] : 0) + mod) % mod;
        g[r] = (g[r - 1] + f[r]) % mod;
    }

    return f[n];
}
