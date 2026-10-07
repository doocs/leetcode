function minRotations(n: number, s: string): number {
    let total = 0;
    for (let i = 1; i < n; ++i) {
        const diff = Math.abs(s.charCodeAt(i) - s.charCodeAt(i - 1));
        total += Math.min(diff, 10 - diff);
    }

    const first = s[0];
    const last = s[n - 1];
    const toFirst = Math.min(first.charCodeAt(0) - 48, 10 - (first.charCodeAt(0) - 48));
    let ans = total + Math.min(last.charCodeAt(0) - 48, 10 - (last.charCodeAt(0) - 48));

    for (let i = 1; i < n; ++i) {
        const pre = s[i - 1];
        const cur = s[i];
        const diff = Math.abs(pre.charCodeAt(0) - cur.charCodeAt(0));
        const edge = Math.min(diff, 10 - diff);
        const toLastDiff = Math.abs(pre.charCodeAt(0) - last.charCodeAt(0));
        const toLast = Math.min(toLastDiff, 10 - toLastDiff);
        ans = Math.min(ans, total - edge + toFirst + toLast);
    }

    return ans;
}
