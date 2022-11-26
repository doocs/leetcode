function isAnagram(s: string, t: string): boolean {
    const n = s.length;
    const m = t.length;
    if (n !== m) {
        return false;
    }
    const count = new Array(26).fill(0);
    for (let i = 0; i < n; i++) {
        count[s.charCodeAt(i) - 'a'.charCodeAt(0)]++;
        count[t.charCodeAt(i) - 'a'.charCodeAt(0)]--;
    }
    return count.every(v => v === 0);
}
