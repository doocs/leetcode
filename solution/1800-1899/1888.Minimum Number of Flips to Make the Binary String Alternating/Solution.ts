function minFlips(s: string): number {
    const n: number = s.length;
    const target: string[] = ['0', '1'];
    let count: number = 0;
    for (let i: number = 0; i < n; ++i) {
        count += s.charAt(i) == target[i & 1] ? 0 : 1;
    }
    let res = Math.min(count, n - count);
    for (let i: number = 0; i < n; ++i) {
        count -= s.charAt(i) == target[i & 1] ? 0 : 1;
        count += s.charAt(i) == target[(i + n) & 1] ? 0 : 1;
        res = Math.min(res, count, n - count);
    }
    return res;
}
