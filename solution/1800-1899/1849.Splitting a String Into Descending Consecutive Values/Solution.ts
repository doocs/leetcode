function splitString(s: string): boolean {
    const dfs = (i: number, x: number): boolean => {
        if (i >= s.length) {
            return true;
        }
        let y = 0;
        const r = x < 0 ? s.length - 1 : s.length;
        for (let j = i; j < r; ++j) {
            y = y * 10 + +s[j];
            if ((x < 0 || x - y === 1) && dfs(j + 1, y)) {
                return true;
            }
        }
        return false;
    };
    return dfs(0, -1);
}
