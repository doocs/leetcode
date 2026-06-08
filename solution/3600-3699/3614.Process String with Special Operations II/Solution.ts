function processStr(s: string, k: number): string {
    let m = 0n;
    for (let i = 0; i < s.length; i++) {
        const c = s[i];
        if (c === '*') {
            const sub = m - 1n;
            m = sub > 0n ? sub : 0n;
        } else if (c === '#') {
            m <<= 1n;
        } else if (c !== '%') {
            m += 1n;
        }
    }
    if (BigInt(k) >= m) {
        return '.';
    }
    let bigK = BigInt(k);
    for (let i = s.length - 1; ; i--) {
        const c = s[i];
        if (c === '*') {
            m += 1n;
        } else if (c === '#') {
            m /= 2n;
            if (bigK >= m) {
                bigK -= m;
            }
        } else if (c === '%') {
            bigK = m - 1n - bigK;
        } else {
            m -= 1n;
            if (bigK === m) {
                return c;
            }
        }
    }
}
