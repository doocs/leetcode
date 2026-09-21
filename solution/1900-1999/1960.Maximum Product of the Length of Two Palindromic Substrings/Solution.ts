function maxProduct(s: string): number {
    const n = s.length;
    const hlen = Array(n).fill(0);
    let center = 0;
    let right = 0;
    for (let i = 0; i < n; ++i) {
        if (i < right) {
            hlen[i] = Math.min(right - i, hlen[2 * center - i]);
        }
        while (
            i - 1 - hlen[i] >= 0 &&
            i + 1 + hlen[i] < n &&
            s[i - 1 - hlen[i]] === s[i + 1 + hlen[i]]
        ) {
            ++hlen[i];
        }
        if (right < i + hlen[i]) {
            center = i;
            right = i + hlen[i];
        }
    }
    const prefix = Array(n).fill(0);
    const suffix = Array(n).fill(0);
    for (let i = 0; i < n; ++i) {
        prefix[i + hlen[i]] = Math.max(prefix[i + hlen[i]], 2 * hlen[i] + 1);
        suffix[i - hlen[i]] = Math.max(suffix[i - hlen[i]], 2 * hlen[i] + 1);
    }
    for (let i = 1; i < n; ++i) {
        prefix[n - i - 1] = Math.max(prefix[n - i - 1], prefix[n - i] - 2);
        suffix[i] = Math.max(suffix[i], suffix[i - 1] - 2);
    }
    for (let i = 1; i < n; ++i) {
        prefix[i] = Math.max(prefix[i - 1], prefix[i]);
        suffix[n - i - 1] = Math.max(suffix[n - i - 1], suffix[n - i]);
    }
    let ans = 0;
    for (let i = 1; i < n; ++i) {
        ans = Math.max(ans, prefix[i - 1] * suffix[i]);
    }
    return ans;
}
