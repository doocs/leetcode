function getMaxRepetitions(s1: string, n1: number, s2: string, n2: number): number {
    const n = s2.length;
    const d: number[][] = new Array(n).fill(0).map(() => new Array(2).fill(0));
    for (let i = 0; i < n; ++i) {
        let j = i;
        let cnt = 0;
        for (const c of s1) {
            if (c === s2[j]) {
                if (++j === n) {
                    j = 0;
                    ++cnt;
                }
            }
        }
        d[i] = [cnt, j];
    }
    let ans = 0;
    for (let j = 0; n1 > 0; --n1) {
        ans += d[j][0];
        j = d[j][1];
    }
    return Math.floor(ans / n2);
}
