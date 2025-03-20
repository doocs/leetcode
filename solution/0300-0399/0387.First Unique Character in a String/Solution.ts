function firstUniqChar(s: string): number {
    const cnt = new Map<string, number>();
    for (const c of s) {
        cnt.set(c, (cnt.get(c) || 0) + 1);
    }
    for (let i = 0; i < s.length; ++i) {
        if (cnt.get(s[i]) === 1) {
            return i;
        }
    }
    return -1;
}
