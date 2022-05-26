function firstUniqChar(s: string): number {
    let record = new Map();
    for (let cur of [...s]) {
        record.set(cur, record.has(cur));
    }
    for (let i = 0; i < s.length; i++) {
        if (!record.get(s[i])) return i;
    }
    return -1;
}
