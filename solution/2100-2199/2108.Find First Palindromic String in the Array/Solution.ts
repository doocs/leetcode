function firstPalindrome(words: string[]): string {
    return words.find(w => w === w.split('').reverse().join('')) || '';
}
