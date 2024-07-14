function halvesAreAlike(s: string): boolean {
    const vowels = new Set('aeiouAEIOU'.split(''));
    let cnt = 0;
    const n = s.length >> 1;
    for (let i = 0; i < n; ++i) {
        cnt += vowels.has(s[i]) ? 1 : 0;
        cnt -= vowels.has(s[n + i]) ? 1 : 0;
    }
    return cnt === 0;
}
