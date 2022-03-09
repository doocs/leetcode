function longestWord(words: string[]): string {
    words.sort((a, b) => {
        const n = a.length;
        const m = b.length;
        if (n === m) {
            return a < b ? -1 : 1;
        }
        return m - n;
    });
    for (const word of words) {
        let isPass = true;
        for (let i = 1; i <= word.length; i++) {
            if (!words.includes(word.slice(0, i))) {
                isPass = false;
                break;
            }
        }
        if (isPass) {
            return word;
        }
    }
    return '';
}
