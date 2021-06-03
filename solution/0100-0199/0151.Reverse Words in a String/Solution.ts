function reverseWords(s: string): string {
    let words: string[] = s.trim().split(/\s+/g);
    words.reverse();
    return words.join(' ');
};