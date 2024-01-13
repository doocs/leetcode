function countTime(time: string): number {
    const f = (s: string, m: number): number => {
        let cnt = 0;
        for (let i = 0; i < m; ++i) {
            const a = s[0] === '?' || s[0] === Math.floor(i / 10).toString();
            const b = s[1] === '?' || s[1] === (i % 10).toString();
            if (a && b) {
                ++cnt;
            }
        }
        return cnt;
    };
    return f(time.slice(0, 2), 24) * f(time.slice(3), 60);
}
