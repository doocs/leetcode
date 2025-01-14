function checkInclusion(s1: string, s2: string): boolean {
    let need = 0;
    const cnt: number[] = Array(26).fill(0);
    const a = 'a'.charCodeAt(0);
    for (const c of s1) {
        if (++cnt[c.charCodeAt(0) - a] === 1) {
            need++;
        }
    }

    const [m, n] = [s1.length, s2.length];
    for (let i = 0; i < n; i++) {
        let c = s2.charCodeAt(i) - a;
        if (--cnt[c] === 0) {
            need--;
        }
        if (i >= m) {
            c = s2.charCodeAt(i - m) - a;
            if (++cnt[c] === 1) {
                need++;
            }
        }
        if (need === 0) {
            return true;
        }
    }
    return false;
}
