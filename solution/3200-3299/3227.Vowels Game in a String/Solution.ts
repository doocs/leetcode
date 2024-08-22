function doesAliceWin(s: string): boolean {
    const vowels = 'aeiou';
    for (const c of s) {
        if (vowels.includes(c)) {
            return true;
        }
    }
    return false;
}
