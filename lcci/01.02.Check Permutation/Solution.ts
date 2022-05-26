function CheckPermutation(s1: string, s2: string): boolean {
    const n = s1.length;
    const m = s2.length;
    if (n !== m) {
        return false;
    }
    const map = new Map<string, number>();
    for (let i = 0; i < n; i++) {
        map.set(s1[i], (map.get(s1[i]) || 0) + 1);
        map.set(s2[i], (map.get(s2[i]) || 0) - 1);
    }
    for (const v of map.values()) {
        if (v !== 0) {
            return false;
        }
    }
    return true;
}
