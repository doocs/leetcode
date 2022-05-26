function isFlipedString(s1: string, s2: string): boolean {
    return s1.length === s2.length && (s2 + s2).indexOf(s1) !== -1;
}
