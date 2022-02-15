function reversePrefix(word: string, ch: string): string {
    let idx = word.indexOf(ch) + 1;
    if (!idx) return word;
    return [...word.substring(0, idx)].reverse().join('') + word.substring(idx);
}
