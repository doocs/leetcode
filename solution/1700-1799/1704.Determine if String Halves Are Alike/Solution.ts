function halvesAreAlike(s: string): boolean {
    const set = new Set(['a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U']);
    const n = s.length >> 1;
    let count = 0;
    for (let i = 0; i < n; i++) {
        set.has(s[i]) && count++;
        set.has(s[n + i]) && count--;
    }
    return count === 0;
}
