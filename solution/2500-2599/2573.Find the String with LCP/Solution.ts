function findTheString(lcp: number[][]): string {
    let i: number = 0;
    const n: number = lcp.length;
    let s: string = '\0'.repeat(n);
    for (let ascii = 97; ascii < 123; ++ascii) {
        const c: string = String.fromCharCode(ascii);
        while (i < n && s[i] !== '\0') {
            ++i;
        }
        if (i === n) {
            break;
        }
        for (let j = i; j < n; ++j) {
            if (lcp[i][j]) {
                s = s.substring(0, j) + c + s.substring(j + 1);
            }
        }
    }
    if (s.indexOf('\0') !== -1) {
        return '';
    }
    for (i = n - 1; ~i; --i) {
        for (let j = n - 1; ~j; --j) {
            if (s[i] === s[j]) {
                if (i === n - 1 || j === n - 1) {
                    if (lcp[i][j] !== 1) {
                        return '';
                    }
                } else if (lcp[i][j] !== lcp[i + 1][j + 1] + 1) {
                    return '';
                }
            } else if (lcp[i][j]) {
                return '';
            }
        }
    }
    return s;
}
