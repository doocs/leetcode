function firstUniqChar(s: string): string {
    const map = new Map();
    for (const c of s) {
        map.set(c, !map.has(c));
    }
    for (const c of s) {
        if (map.get(c)) {
            return c;
        }
    }
    return ' ';
}
