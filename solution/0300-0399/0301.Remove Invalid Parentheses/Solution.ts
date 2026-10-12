function removeInvalidParentheses(s: string): string[] {
    const n = s.length;
<<<<<<< HEAD
<<<<<<< HEAD
    let l = 0, r = 0;
=======
    let l = 0,
        r = 0;
>>>>>>> af91c82b5d8912f186732e254c0193bccbfc24ca
=======
    let l = 0,
        r = 0;
>>>>>>> dda3fb9a1b3657c336e2f84c75dd8601846e6fbf
    for (const c of s) {
        if (c === '(') {
            l++;
        } else if (c === ')') {
            if (l > 0) {
                l--;
            } else {
                r++;
            }
        }
    }

    const ans = new Set<string>();

    const dfs = (i: number, l: number, r: number, lcnt: number, rcnt: number, t: string): void => {
        if (i === n) {
            if (l === 0 && r === 0) {
                ans.add(t);
            }
            return;
        }
        if (n - i < l + r || lcnt < rcnt) {
            return;
        }
        const c = s[i];
        if (c === '(' && l > 0) {
            dfs(i + 1, l - 1, r, lcnt, rcnt, t);
        }
        if (c === ')' && r > 0) {
            dfs(i + 1, l, r - 1, lcnt, rcnt, t);
        }
        const x = c === '(' ? 1 : 0;
        const y = c === ')' ? 1 : 0;
        dfs(i + 1, l, r, lcnt + x, rcnt + y, t + c);
    };

    dfs(0, l, r, 0, 0, '');
    return Array.from(ans);
<<<<<<< HEAD
<<<<<<< HEAD
}
=======
}
>>>>>>> af91c82b5d8912f186732e254c0193bccbfc24ca
=======
}
>>>>>>> dda3fb9a1b3657c336e2f84c75dd8601846e6fbf
