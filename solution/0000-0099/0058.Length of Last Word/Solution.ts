function lengthOfLastWord(s: string): number {
    s = s.trimEnd();
    const n = s.length;
    const index = s.lastIndexOf(' ');
    if (index !== -1) {
        return n - index - 1;
    }
    return n;
}
