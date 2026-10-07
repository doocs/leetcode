function longestSubarray(nums: number[], k: number): number {
    const n = nums.length;
    const p = new Array<number>(n + 1).fill(0);
    for (let i = 0; i < n; ++i) {
        p[i + 1] = (p[i] + nums[i]) % k;
        if (p[i + 1] < 0) {
            p[i + 1] += k;
        }
    }

    const first = new Array<number>(k).fill(-1);
    for (let i = 0; i <= n; ++i) {
        if (first[p[i]] === -1) {
            first[p[i]] = i;
        }
    }

    const order = Array.from({ length: k }, (_, q) => q).filter(q => first[q] !== -1);

    order.sort((a, b) => first[a] - first[b]);

    const pos = new Array<number>(k).fill(0);
    const best = first.map(x => (x === -1 ? n + 1 : x));

    let ans = 0;
    for (let i = 0; i < n; ++i) {
        let a = nums[i] % k;
        if (a < 0) {
            a += k;
        }

        while (pos[a] < order.length && first[order[pos[a]]] <= i) {
            const q = order[pos[a]++];
            const t = (q + 2 * a) % k;
            best[t] = Math.min(best[t], first[q]);
        }

        const s = p[i + 1];
        if (best[s] !== n + 1) {
            ans = Math.max(ans, i + 1 - best[s]);
        }
    }
    return ans;
}
