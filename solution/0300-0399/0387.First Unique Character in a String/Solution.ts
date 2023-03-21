function firstUniqChar(s: string): number {
    const cnt = new Array(26).fill(0);
    for (const c of s) {
        cnt[c.charCodeAt(0) - 97]++;
    }
    for (let i = 0; i < s.length; i++) {
        if (cnt[s.charCodeAt(i) - 97] === 1) {
            return i;
        }
    }
    return -1;
}
