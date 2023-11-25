function decodeAtIndex(s: string, k: number): string {
    let m = 0n;
    for (const c of s) {
        if (c >= '1' && c <= '9') {
            m *= BigInt(c);
        } else {
            ++m;
        }
    }
    for (let i = s.length - 1; ; --i) {
        if (k >= m) {
            k %= Number(m);
        }
        if (k === 0 && s[i] >= 'a' && s[i] <= 'z') {
            return s[i];
        }
        if (s[i] >= '1' && s[i] <= '9') {
            m = (m / BigInt(s[i])) | 0n;
        } else {
            --m;
        }
    }
}
