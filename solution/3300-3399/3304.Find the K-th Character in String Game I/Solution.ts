function kthCharacter(k: number): string {
    const word: number[] = [0];
    while (word.length < k) {
        word.push(...word.map(x => (x + 1) % 26));
    }
    return String.fromCharCode(97 + word[k - 1]);
}
