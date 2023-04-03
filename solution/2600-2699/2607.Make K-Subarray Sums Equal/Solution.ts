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
    let f = 0;
    for (const c of s) {
        f = Math.max(f, 0) + d[c.charCodeAt(0) - 97];
        ans = Math.max(ans, f);
    }
    return ans;
}
