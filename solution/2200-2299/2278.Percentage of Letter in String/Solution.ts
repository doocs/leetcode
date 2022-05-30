function percentageLetter(s: string, letter: string): number {
    let count = 0;
    let total = s.length;
    for (let i of s) {
        if (i === letter) count++;
    }
    return Math.floor((count / total) * 100);
}
