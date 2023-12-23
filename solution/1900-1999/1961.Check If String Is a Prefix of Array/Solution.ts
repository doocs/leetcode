function isPrefixString(s: string, words: string[]): boolean {
    const t: string[] = [];
    const n = s.length;
    let m = 0;
    for (const w of words) {
        m += w.length;
        if (m > n) {
            return false;
        }
        t.push(w);
        if (m === n) {
            return s === t.join('');
        }
    }
    return false;
}
