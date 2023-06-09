function areOccurrencesEqual(s: string): boolean {
    const cnt: number[] = new Array(26).fill(0);
    for (const c of s) {
        ++cnt[c.charCodeAt(0) - 'a'.charCodeAt(0)];
    }
    let x = 0;
    for (const v of cnt) {
        if (v) {
            if (!x) {
                x = v;
            } else if (x !== v) {
                return false;
            }
        }
    }
    return true;
}
