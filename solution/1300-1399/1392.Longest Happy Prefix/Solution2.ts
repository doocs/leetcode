function longestPrefix(s: string): string {
    s += '#';
    const n = s.length;
    const next: number[] = Array(n).fill(0);
    next[0] = -1;
    for (let i = 2, j = 0; i < n; ) {
        if (s[i - 1] === s[j]) {
            next[i++] = ++j;
        } else if (j > 0) {
            j = next[j];
        } else {
            next[i++] = 0;
        }
    }
    return s.slice(0, next[n - 1]);
}
