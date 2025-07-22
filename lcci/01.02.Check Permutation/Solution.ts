function CheckPermutation(s1: string, s2: string): boolean {
    if (s1.length !== s2.length) {
        return false;
    }
    const cnt: Record<string, number> = {};
    for (const c of s1) {
        cnt[c] = (cnt[c] || 0) + 1;
    }
    for (const c of s2) {
        if (!cnt[c]) {
            return false;
        }
        cnt[c]--;
    }
    return true;
}
