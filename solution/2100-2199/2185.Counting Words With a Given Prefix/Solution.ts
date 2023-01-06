function prefixCount(words: string[], pref: string): number {
    const m = pref.length;
    let ans = 0;
    for (const w of words) {
        if (w.substring(0, m) === pref) {
            ++ans;
        }
    }
    return ans;
}
