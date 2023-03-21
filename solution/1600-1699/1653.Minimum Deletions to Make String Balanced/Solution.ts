function minimumDeletions(s: string): number {
    const n = s.length;
    let ans = 0,
        b = 0;
    for (let i = 0; i < n; ++i) {
        if (s.charAt(i) === 'b') {
            ++b;
        } else {
            ans = Math.min(ans + 1, b);
        }
    }
    return ans;
}
