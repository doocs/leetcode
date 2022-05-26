function lengthOfLongestSubstring(s: string): number {
    const n = s.length;
    const set = new Set<string>();
    let res = 0;
    let i = 0;
    for (let j = 0; j < n; j++) {
        const c = s[j];
        while (set.has(c)) {
            set.delete(s[i++]);
        }
        set.add(c);
        res = Math.max(res, set.size);
    }
    return res;
}
