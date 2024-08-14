function minimumString(a: string, b: string, c: string): string {
    const f = (s: string, t: string): string => {
        if (s.includes(t)) {
            return s;
        }
        if (t.includes(s)) {
            return t;
        }
        const p = t + '#' + s + '$';
        const n = p.length;
        const next: number[] = Array(n).fill(0);
        next[0] = -1;
        for (let i = 2, j = 0; i < n; ) {
            if (p[i - 1] === p[j]) {
                next[i++] = ++j;
            } else if (j) {
                j = next[j];
            } else {
                next[i++] = 0;
            }
        }
        return s + t.slice(next[n - 1]);
    };
    const s: string[] = [a, b, c];
    const perm: number[][] = [
        [0, 1, 2],
        [0, 2, 1],
        [1, 0, 2],
        [1, 2, 0],
        [2, 0, 1],
        [2, 1, 0],
    ];
    let ans = '';
    for (const [i, j, k] of perm) {
        const t = f(f(s[i], s[j]), s[k]);
        if (ans === '' || t.length < ans.length || (t.length === ans.length && t < ans)) {
            ans = t;
        }
    }
    return ans;
}
