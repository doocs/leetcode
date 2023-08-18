function scoreOfStudents(s: string, answers: number[]): number {
    const n = s.length;
    const cal = (s: string): number => {
        let res = 0;
        let pre = s.charCodeAt(0) - '0'.charCodeAt(0);
        for (let i = 1; i < s.length; i += 2) {
            const cur = s.charCodeAt(i + 1) - '0'.charCodeAt(0);
            if (s[i] === '+') {
                res += pre;
                pre = cur;
            } else {
                pre *= cur;
            }
        }
        res += pre;
        return res;
    };
    const x = cal(s);
    const m = (n + 1) >> 1;
    const f: Set<number>[][] = Array(m)
        .fill(0)
        .map(() =>
            Array(m)
                .fill(0)
                .map(() => new Set()),
        );
    for (let i = 0; i < m; ++i) {
        f[i][i].add(s[i << 1].charCodeAt(0) - '0'.charCodeAt(0));
    }
    for (let i = m - 1; i >= 0; --i) {
        for (let j = i; j < m; ++j) {
            for (let k = i; k < j; ++k) {
                for (const l of f[i][k]) {
                    for (const r of f[k + 1][j]) {
                        const op = s[(k << 1) + 1];
                        if (op === '+' && l + r <= 1000) {
                            f[i][j].add(l + r);
                        } else if (op === '*' && l * r <= 1000) {
                            f[i][j].add(l * r);
                        }
                    }
                }
            }
        }
    }
    const cnt: number[] = Array(1001).fill(0);
    for (const v of answers) {
        ++cnt[v];
    }
    let ans = cnt[x] * 5;
    for (let i = 0; i <= 1000; ++i) {
        if (i !== x && f[0][m - 1].has(i)) {
            ans += cnt[i] << 1;
        }
    }
    return ans;
}
