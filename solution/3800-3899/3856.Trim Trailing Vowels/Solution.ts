function trimTrailingVowels(s: string): string {
    let i = s.length - 1;
    while (i >= 0 && 'aeiou'.indexOf(s[i]) !== -1) {
        i--;
    }
    return s.slice(0, i + 1);
}
