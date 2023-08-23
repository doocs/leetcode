function countPairs(n: number, edges: number[][], queries: number[]): number[] {
    const cnt: number[] = new Array(n).fill(0);
    const g: Map<number, number> = new Map();
    for (const [a, b] of edges) {
        ++cnt[a - 1];
        ++cnt[b - 1];
        const k = Math.min(a - 1, b - 1) * n + Math.max(a - 1, b - 1);
        g.set(k, (g.get(k) || 0) + 1);
    }
    const s = cnt.slice().sort((a, b) => a - b);
    const search = (nums: number[], x: number, l: number): number => {
        let r = nums.length;
        while (l < r) {
            const mid = (l + r) >> 1;
            if (nums[mid] > x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    };
    const ans: number[] = [];
    for (const t of queries) {
        let res = 0;
        for (let j = 0; j < s.length; ++j) {
            const k = search(s, t - s[j], j + 1);
            res += n - k;
        }
        for (const [k, v] of g) {
            const a = Math.floor(k / n);
            const b = k % n;
            if (cnt[a] + cnt[b] > t && cnt[a] + cnt[b] - v <= t) {
                --res;
            }
        }
        ans.push(res);
    }
    return ans;
}
