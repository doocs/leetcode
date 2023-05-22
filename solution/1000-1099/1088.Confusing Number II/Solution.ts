function confusingNumberII(n: number): number {
    const s = n.toString();
    const d: number[] = [0, 1, -1, -1, -1, -1, 9, -1, 8, 6];
    const check = (x: number) => {
        let y = 0;
        for (let t = x; t > 0; t = Math.floor(t / 10)) {
            const v = t % 10;
            y = y * 10 + d[v];
        }
        return x !== y;
    };
    const dfs = (pos: number, limit: boolean, x: number): number => {
        if (pos >= s.length) {
            return check(x) ? 1 : 0;
        }
        const up = limit ? parseInt(s[pos]) : 9;
        let ans = 0;
        for (let i = 0; i <= up; ++i) {
            if (d[i] !== -1) {
                ans += dfs(pos + 1, limit && i === up, x * 10 + i);
            }
        }
        return ans;
    };
    return dfs(0, true, 0);
}
