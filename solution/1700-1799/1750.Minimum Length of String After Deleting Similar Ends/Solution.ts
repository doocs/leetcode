function minimumLength(s: string): number {
    let i = 0;
    let j = s.length - 1;
    while (i < j && s[i] === s[j]) {
        while (i + 1 < j && s[i + 1] === s[i]) {
            ++i;
        }
        while (i < j - 1 && s[j - 1] === s[j]) {
            --j;
        }
        ++i;
        --j;
    }
    return Math.max(0, j - i + 1);
}
