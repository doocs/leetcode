function takeCharacters(s: string, k: number): number {
    const getIndex = (c: string) => c.charCodeAt(0) - 'a'.charCodeAt(0);
    const count = [0, 0, 0];
    for (const c of s) {
        count[getIndex(c)]++;
    }
    if (count.some(v => v < k)) {
        return -1;
    }
    const n = s.length;
    let ans = 0;
    for (let i = 0, j = 0; j < n; j++) {
        count[getIndex(s[j])]--;
        while (count[getIndex(s[j])] < k) {
            count[getIndex(s[i])]++;
            i++;
        }
        ans = Math.max(ans, j - i + 1);
    }
    return n - ans;
}
