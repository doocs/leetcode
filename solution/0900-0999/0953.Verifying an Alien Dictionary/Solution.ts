function isAlienSorted(words: string[], order: string): boolean {
    const map = new Map();
    for (const c of order) {
        map.set(c, map.size);
    }
    const n = words.length;
    for (let i = 1; i < n; i++) {
        const s1 = words[i - 1];
        const s2 = words[i];
        const m = Math.min(s1.length, s2.length);
        let isEqual = false;
        for (let j = 0; j < m; j++) {
            if (map.get(s1[j]) > map.get(s2[j])) {
                return false;
            }
            if (map.get(s1[j]) < map.get(s2[j])) {
                isEqual = true;
                break;
            }
        }
        if (!isEqual && s1.length > s2.length) {
            return false;
        }
    }
    return true;
}
