function findRepeatedDnaSequences(s: string): string[] {
    const n = s.length;
    const map = new Map<string, boolean>();
    const res = [];
    for (let i = 0; i <= n - 10; i++) {
        const key = s.slice(i, i + 10);
        if (map.has(key) && map.get(key)) {
            res.push(key);
        }
        map.set(key, !map.has(key));
    }
    return res;
}
