function reverseWords(s: string): string {
    return s
        .split(' ')
        .map(t => t.split('').reverse().join(''))
        .join(' ');
}
