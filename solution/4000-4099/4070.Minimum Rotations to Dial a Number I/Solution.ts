function minRotations(s: string): number {
    let ans = 0;
    let pre = 0;
    for (let i = 0; i < s.length; ++i) {
        const cur = s.charCodeAt(i) - 48;
        const diff = Math.abs(cur - pre);
        ans += Math.min(diff, 10 - diff);
        pre = cur;
    }
    return ans;
}
