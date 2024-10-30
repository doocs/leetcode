function numberOfWays(s: string): number {
    const n = s.length;
    const l: number[] = [0, 0];
    const r: number[] = [s.split('').filter(c => c === '0').length, 0];
    r[1] = n - r[0];
    let ans: number = 0;
    for (const c of s) {
        const x = c === '0' ? 0 : 1;
        r[x]--;
        ans += l[x ^ 1] * r[x ^ 1];
        l[x]++;
    }
    return ans;
}
