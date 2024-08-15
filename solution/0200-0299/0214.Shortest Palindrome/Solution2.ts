function shortestPalindrome(s: string): string {
    const rev = s.split('').reverse().join('');
    const t = s + '#' + rev + '$';
    const n = t.length;
    const next: number[] = Array(n).fill(0);
    next[0] = -1;
    for (let i = 2, j = 0; i < n; ) {
        if (t[i - 1] === t[j]) {
            next[i++] = ++j;
        } else if (j > 0) {
            j = next[j];
        } else {
            next[i++] = 0;
        }
    }
    return rev.slice(0, -next[n - 1]) + s;
}
