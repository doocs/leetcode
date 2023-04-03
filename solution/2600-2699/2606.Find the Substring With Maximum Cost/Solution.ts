function maximumCostSubstring(
    s: string,
    chars: string,
    vals: number[],
): number {
    const d: number[] = Array.from({ length: 26 }, (_, i) => i + 1);
    for (let i = 0; i < chars.length; ++i) {
        d[chars.charCodeAt(i) - 97] = vals[i];
    }
    let ans = 0;
    let tot = 0;
    let mi = 0;
    for (const c of s) {
        tot += d[c.charCodeAt(0) - 97];
        ans = Math.max(ans, tot - mi);
        mi = Math.min(mi, tot);
    }
    return ans;
}
