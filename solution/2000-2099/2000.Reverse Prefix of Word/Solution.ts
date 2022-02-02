function reversePrefix(word: string, ch: string): string {
    const chars = word.split('');
    const i = word.indexOf(ch);
    if (i !== -1) {
        let l = 0;
        let r = i;
        while (l < r) {
            [chars[l], chars[r]] = [chars[r], chars[l]];
            l++;
            r--;
        }
    }
    return chars.join('');
}
