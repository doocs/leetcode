function minimumFlips(n: number): number {
    const s = n.toString(2);
    const m = s.length;
    let cnt = 0;
    for (let i = 0; i < m / 2; i++) {
        if (s[i] !== s[m - i - 1]) {
            cnt++;
        }
    }
    return cnt * 2;
}
