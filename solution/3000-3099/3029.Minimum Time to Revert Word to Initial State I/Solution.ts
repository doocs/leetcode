function minimumTimeToInitialState(word: string, k: number): number {
    const n = word.length;
    for (let i = k; i < n; i += k) {
        if (word.slice(i) === word.slice(0, -i)) {
            return Math.floor(i / k);
        }
    }
    return Math.floor((n + k - 1) / k);
}
