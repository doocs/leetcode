function areOccurrencesEqual(s: string): boolean {
    const cnt: number[] = new Array(26).fill(0);
    for (const c of s) {
        ++cnt[c.charCodeAt(0) - 'a'.charCodeAt(0)];
    }
    const x = cnt.find(v => v);
    return cnt.every(v => !v || v === x);
}
