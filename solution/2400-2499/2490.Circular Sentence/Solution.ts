function isCircularSentence(sentence: string): boolean {
    const ss = sentence.split(' ');
    const n = ss.length;
    if (ss[0][0] !== ss[n - 1][ss[n - 1].length - 1]) {
        return false;
    }
    for (let i = 0; i < n - 1; i++) {
        if (ss[i][ss[i].length - 1] !== ss[i + 1][0]) {
            return false;
        }
    }
    return true;
}
