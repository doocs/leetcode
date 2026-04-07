function generateString(s: string, t: string): string {
    const n = s.length,
        m = t.length;
    const ans: string[] = new Array(n + m - 1).fill('a');
    const fixed: boolean[] = new Array(n + m - 1).fill(false);

    for (let i = 0; i < n; i++) {
        if (s[i] !== 'T') continue;
        for (let j = 0; j < m; j++) {
            const k = i + j;
            if (fixed[k] && ans[k] !== t[j]) return '';
            ans[k] = t[j];
            fixed[k] = true;
        }
    }

    for (let i = 0; i < n; i++) {
        if (s[i] !== 'F') continue;

        let same = true;
        for (let j = 0; j < m; j++) {
            if (ans[i + j] !== t[j]) {
                same = false;
                break;
            }
        }
        if (!same) continue;

        let ok = false;
        for (let j = i + m - 1; j >= i; j--) {
            if (!fixed[j]) {
                ans[j] = 'b';
                ok = true;
                break;
            }
        }
        if (!ok) return '';
    }

    return ans.join('');
}
