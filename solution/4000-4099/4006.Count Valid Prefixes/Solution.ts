function countValidPrefixes(s: string): number {
    let ans = 0;
    let t = 0;
    for (const c of s) {
        t += c === '1' ? 1 : -1;
        if (Math.abs(t) <= 1) {
            ans++;
        }
    }
    return ans;
}
