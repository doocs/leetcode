function minimumDeletions(s: string): number {
    let lb = 0,
        ra = 0;
    const n = s.length;
    for (let i = 0; i < n; ++i) {
        if (s.charAt(i) === 'a') {
            ++ra;
        }
    }
    let ans = n;
    for (let i = 0; i < n; ++i) {
        ra -= s.charAt(i) === 'a' ? 1 : 0;
        ans = Math.min(ans, lb + ra);
        lb += s.charAt(i) === 'b' ? 1 : 0;
    }
    return ans;
}
