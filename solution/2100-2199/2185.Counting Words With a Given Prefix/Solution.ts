function prefixCount(words: string[], pref: string): number {
    const n = pref.length;
    let ans = 0;
    for (let str of words) {
        if (str.substring(0, n) == pref) {
            ans++;
        }
    }
    return ans;
}
