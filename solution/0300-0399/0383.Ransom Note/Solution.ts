function canConstruct(ransomNote: string, magazine: string): boolean {
    const cnt = new Array(26).fill(0);
    for (const c of magazine) {
        ++cnt[c.charCodeAt(0) - 97];
    }
    for (const c of ransomNote) {
        if (--cnt[c.charCodeAt(0) - 97] < 0) {
            return false;
        }
    }
    return true;
}
