function compressedString(word: string): string {
    let res = '';

    for (let i = 1, j = 0; i <= word.length; i++) {
        if (word[i] !== word[j] || i - j === 9) {
            res += i - j + word[j];
            j = i;
        }
    }

    return res;
}
