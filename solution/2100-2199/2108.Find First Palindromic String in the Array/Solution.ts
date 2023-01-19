function firstPalindrome(words: string[]): string {
    for (const word of words) {
        let left = 0;
        let right = word.length - 1;
        while (left < right) {
            if (word[left] !== word[right]) {
                break;
            }
            left++;
            right--;
        }
        if (left >= right) {
            return word;
        }
    }
    return '';
}
