function breakPalindrome(palindrome: string): string {
    const n = palindrome.length;
    if (n === 1) {
        return '';
    }
    const s = palindrome.split('');
    let i = 0;
    while (i < n >> 1 && s[i] === 'a') {
        i++;
    }
    if (i == n >> 1) {
        s[n - 1] = 'b';
    } else {
        s[i] = 'a';
    }
    return s.join('');
}
