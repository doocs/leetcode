function longestPrefix(s: string): string {
    const n = s.length;
    for (let i = n - 1; i >= 0; i--) {
        if (s.slice(0, i) === s.slice(n - i, n)) {
            return s.slice(0, i);
        }
    }
    return '';
}
