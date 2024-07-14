function isValid(word: string): boolean {
    if (word.length < 3) {
        return false;
    }
    let hasVowel: boolean = false;
    let hasConsonant: boolean = false;
    const vowels: Set<string> = new Set(['a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U']);
    for (const c of word) {
        if (!c.match(/[a-zA-Z0-9]/)) {
            return false;
        }
        if (/[a-zA-Z]/.test(c)) {
            if (vowels.has(c)) {
                hasVowel = true;
            } else {
                hasConsonant = true;
            }
        }
    }
    return hasVowel && hasConsonant;
}
