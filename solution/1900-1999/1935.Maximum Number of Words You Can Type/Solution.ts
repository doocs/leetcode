function canBeTypedWords(text: string, brokenLetters: string): number {
    const s: boolean[] = Array(26).fill(false);
    for (const c of brokenLetters) {
        s[c.charCodeAt(0) - 'a'.charCodeAt(0)] = true;
    }
    let ans = 0;
    for (const w of text.split(' ')) {
        for (const c of w) {
            if (s[c.charCodeAt(0) - 'a'.charCodeAt(0)]) {
                --ans;
                break;
            }
        }
        ++ans;
    }
    return ans;
}
