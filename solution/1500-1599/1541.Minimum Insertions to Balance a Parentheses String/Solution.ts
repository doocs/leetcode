function minInsertions(s: string): number {
    let ans = 0;
    let x = 0;
    const n = s.length;
    for (let i = 0; i < n; i++) {
        if (s[i] === '(') {
            x++;
        } else {
            if (i < n - 1 && s[i + 1] === ')') {
                i++;
            } else {
                ans++;
            }
            if (x === 0) {
                ans++;
            } else {
                x--;
            }
        }
    }
    ans += x << 1;
    return ans;
<<<<<<< HEAD
}
=======
}
>>>>>>> af91c82b5d8912f186732e254c0193bccbfc24ca
