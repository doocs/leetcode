function shiftDistance(s: string, t: string, nextCost: number[], previousCost: number[]): number {
    const m = 26;
    const s1: number[] = Array((m << 1) + 1).fill(0);
    const s2: number[] = Array((m << 1) + 1).fill(0);
    for (let i = 0; i < m << 1; i++) {
        s1[i + 1] = s1[i] + nextCost[i % m];
        s2[i + 1] = s2[i] + previousCost[(i + 1) % m];
    }
    let ans = 0;
    const a = 'a'.charCodeAt(0);
    for (let i = 0; i < s.length; i++) {
        const x = s.charCodeAt(i) - a;
        const y = t.charCodeAt(i) - a;
        const c1 = s1[y + (y < x ? m : 0)] - s1[x];
        const c2 = s2[x + (x < y ? m : 0)] - s2[y];
        ans += Math.min(c1, c2);
    }
    return ans;
}
