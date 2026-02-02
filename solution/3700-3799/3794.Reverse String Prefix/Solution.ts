function reversePrefix(s: string, k: number): string {
    return s.slice(0, k).split('').reverse().join('') + s.slice(k);
}
