function checkInclusion(s1: string, s2: string): boolean {
    const m = s1.length;
    const n = s2.length;
    if (m > n) {
        return false;
    }
    const cnt: number[] = new Array(26).fill(0);
    for (let i = 0; i < m; ++i) {
        --cnt[s1[i].charCodeAt(0) - 'a'.charCodeAt(0)];
        ++cnt[s2[i].charCodeAt(0) - 'a'.charCodeAt(0)];
    }
    let diff = 0;
    for (const x of cnt) {
        if (x !== 0) {
            ++diff;
        }
    }
    if (diff === 0) {
        return true;
    }
    for (let i = m; i < n; ++i) {
        const a = s2[i - m].charCodeAt(0) - 'a'.charCodeAt(0);
        const b = s2[i].charCodeAt(0) - 'a'.charCodeAt(0);
        if (cnt[a] === 0) {
            ++diff;
        }
        if (--cnt[a] === 0) {
            --diff;
        }
        if (cnt[b] === 0) {
            ++diff;
        }
        if (++cnt[b] === 0) {
            --diff;
        }
        if (diff === 0) {
            return true;
        }
    }
    return false;
}
