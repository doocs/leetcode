function percentageLetter(s: string, letter: string): number {
    const count = s.split('').filter(c => c === letter).length;
    return Math.floor((100 * count) / s.length);
}
