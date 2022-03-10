function wordPattern(pattern: string, s: string): boolean {
    let n = pattern.length;
    let values = s.split(' ');
    if (n != values.length) return false;
    let table = new Array(128);
    for (let i = 0; i < n; i++) {
        let k = pattern.charCodeAt(i),
            v = values[i];
        if (!table[k]) {
            if (table.includes(v)) return false;
            table[k] = v;
        } else {
            if (table[k] != v) return false;
        }
    }
    return true;
}
