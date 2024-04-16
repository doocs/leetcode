function reverseOnlyLetters(s: string): string {
    const cs = [...s];
    let [i, j] = [0, cs.length - 1];
    while (i < j) {
        while (!/[a-zA-Z]/.test(cs[i]) && i < j) {
            i++;
        }
        while (!/[a-zA-Z]/.test(cs[j]) && i < j) {
            j--;
        }
        [cs[i], cs[j]] = [cs[j], cs[i]];
        i++;
        j--;
    }
    return cs.join('');
}
