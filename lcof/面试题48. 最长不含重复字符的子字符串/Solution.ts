function lengthOfLongestSubstring(s: string): number {
    let res = 0;
    let l = 0;
    let set = new Set<string>();
    for (const c of s) {
        while (set.has(c)) {
            set.delete(s[l++]);
        }
        set.add(c);
        res = Math.max(res, set.size);
    }
    return res;
}
