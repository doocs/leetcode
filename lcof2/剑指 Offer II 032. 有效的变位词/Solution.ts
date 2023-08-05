function isAnagram(s: string, t: string): boolean {
    const m = s.length;
    const n = t.length;
    if (m !== n || s === t) {
        return false;
    }
    const cnt: number[] = new Array(26).fill(0);
    for (let i = 0; i < m; ++i) {
        ++cnt[s[i].charCodeAt(0) - 'a'.charCodeAt(0)];
        --cnt[t[i].charCodeAt(0) - 'a'.charCodeAt(0)];
    }
    return cnt.every(x => x === 0);
}
