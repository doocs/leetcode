function minimumRelativeLosses(
    prices: number[],
    queries: number[][],
): number[] {
    const n = prices.length;
    prices.sort((a, b) => a - b);
    const s: number[] = Array(n).fill(0);
    for (let i = 0; i < n; ++i) {
        s[i + 1] = s[i] + prices[i];
    }

    const search = (x: number): number => {
        let l = 0;
        let r = n;
        while (l < r) {
            const mid = (l + r) >> 1;
            if (prices[mid] > x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    };

    const f = (k: number, m: number): number => {
        let l = 0;
        let r = Math.min(search(k), m);
        while (l < r) {
            const mid = (l + r) >> 1;
            const right = m - mid;
            if (prices[mid] < 2 * k - prices[n - right]) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    };
    const ans: number[] = [];
    for (const [k, m] of queries) {
        const l = f(k, m);
        const r = m - l;
        ans.push(s[l] + 2 * k * r - (s[n] - s[n - r]));
    }
    return ans;
}
