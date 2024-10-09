function removeAnagrams(words: string[]): string[] {
    const ans: string[] = [words[0]];
    const check = (s: string, t: string): boolean => {
        if (s.length !== t.length) {
            return true;
        }
        const cnt: number[] = Array(26).fill(0);
        for (const c of s) {
            ++cnt[c.charCodeAt(0) - 97];
        }
        for (const c of t) {
            if (--cnt[c.charCodeAt(0) - 97] < 0) {
                return true;
            }
        }
        return false;
    };
    for (let i = 1; i < words.length; ++i) {
        if (check(words[i - 1], words[i])) {
            ans.push(words[i]);
        }
    }
    return ans;
}
