function scoreOfString(s: string): number {
    let ans = 0;
    const n = s.length;
    let prev = s.charCodeAt(0);

    for (let i = 1; i < n; ++i) {
        const curr = s.charCodeAt(i);
        ans += Math.abs(curr - prev);
        prev = curr;
    }
    return ans;
}
