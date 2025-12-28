function countBalanced(low: number, high: number): number {
    if (high < 11) {
        return 0;
    }
    if (low < 11) {
        low = 11;
    }
    const base = 90;

    let num: string;
    let f: number[][];

    function dfs(pos: number, diff: number, lim: boolean): number {
        if (pos >= num.length) {
            return diff === 0 ? 1 : 0;
        }
        if (!lim && f[pos][diff + base] !== -1) {
            return f[pos][diff + base];
        }
        const up = lim ? num.charCodeAt(pos) - 48 : 9;
        let res = 0;
        for (let i = 0; i <= up; ++i) {
            res += dfs(pos + 1, diff + i * (pos % 2 === 0 ? 1 : -1), lim && i === up);
        }
        if (!lim) {
            f[pos][diff + base] = res;
        }
        return res;
    }

    num = String(low - 1);
    f = Array.from({ length: num.length }, () => Array((base << 1) | 1).fill(-1));
    const a = dfs(0, 0, true);

    num = String(high);
    f = Array.from({ length: num.length }, () => Array((base << 1) | 1).fill(-1));
    const b = dfs(0, 0, true);

    return b - a;
}
