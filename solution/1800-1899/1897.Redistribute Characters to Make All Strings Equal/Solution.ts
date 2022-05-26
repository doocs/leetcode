function makeEqual(words: string[]): boolean {
    let n = words.length;
    let letters = new Array(26).fill(0);
    for (let word of words) {
        for (let i = 0; i < word.length; ++i) {
            ++letters[word.charCodeAt(i) - 97];
        }
    }

    for (let i = 0; i < letters.length; ++i) {
        if (letters[i] % n != 0) {
            return false;
        }
    }
    return true;
}
