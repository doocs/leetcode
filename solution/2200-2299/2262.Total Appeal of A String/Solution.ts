function appealSum(s: string): number {
    const pos: number[] = Array(26).fill(-1);
    const n = s.length;
    let ans = 0;
    let t = 0;
    for (let i = 0; i < n; ++i) {
        const c = s.charCodeAt(i) - 97;
        t += i - pos[c];
        ans += t;
        pos[c] = i;
    }
    return ans;
}
