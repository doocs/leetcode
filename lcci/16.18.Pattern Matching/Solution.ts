function patternMatching(pattern: string, value: string): boolean {
    const cnt: number[] = [0, 0];
    for (const c of pattern) {
        cnt[c === 'a' ? 0 : 1]++;
    }
    const n = value.length;
    if (cnt[0] === 0) {
        return n % cnt[1] === 0 && value.slice(0, (n / cnt[1]) | 0).repeat(cnt[1]) === value;
    }
    if (cnt[1] === 0) {
        return n % cnt[0] === 0 && value.slice(0, (n / cnt[0]) | 0).repeat(cnt[0]) === value;
    }
    const check = (la: number, lb: number) => {
        let i = 0;
        let a = '';
        let b = '';
        for (const c of pattern) {
            if (c === 'a') {
                if (a && a !== value.slice(i, i + la)) {
                    return false;
                }
                a = value.slice(i, (i += la));
            } else {
                if (b && b !== value.slice(i, i + lb)) {
                    return false;
                }
                b = value.slice(i, (i += lb));
            }
        }
        return a !== b;
    };
    for (let la = 0; la <= n; ++la) {
        if (la * cnt[0] > n) {
            break;
        }
        if ((n - la * cnt[0]) % cnt[1] === 0) {
            const lb = ((n - la * cnt[0]) / cnt[1]) | 0;
            if (check(la, lb)) {
                return true;
            }
        }
    }
    return false;
}
