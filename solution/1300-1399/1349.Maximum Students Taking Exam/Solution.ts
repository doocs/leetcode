function maxStudents(seats: string[][]): number {
    const m: number = seats.length;
    const n: number = seats[0].length;
    const ss: number[] = Array(m).fill(0);
    const f: number[][] = Array.from({ length: 1 << n }, () => Array(m).fill(-1));
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (seats[i][j] === '.') {
                ss[i] |= 1 << j;
            }
        }
    }

    const dfs = (seat: number, i: number): number => {
        if (f[seat][i] !== -1) {
            return f[seat][i];
        }
        let ans: number = 0;
        for (let mask = 0; mask < 1 << n; ++mask) {
            if ((seat | mask) !== seat || (mask & (mask << 1)) !== 0) {
                continue;
            }
            const cnt: number = mask.toString(2).split('1').length - 1;
            if (i === m - 1) {
                ans = Math.max(ans, cnt);
            } else {
                let nxt: number = ss[i + 1];
                nxt &= ~(mask >> 1);
                nxt &= ~(mask << 1);
                ans = Math.max(ans, cnt + dfs(nxt, i + 1));
            }
        }
        return (f[seat][i] = ans);
    };
    return dfs(ss[0], 0);
}
