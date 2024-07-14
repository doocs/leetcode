function findClosest(words: string[], word1: string, word2: string): number {
    const d: Map<string, number[]> = new Map();
    for (let i = 0; i < words.length; ++i) {
        if (!d.has(words[i])) {
            d.set(words[i], []);
        }
        d.get(words[i])!.push(i);
    }
    let [i, j] = [0, 0];
    let ans = Infinity;
    while (i < d.get(word1)!.length && j < d.get(word2)!.length) {
        ans = Math.min(ans, Math.abs(d.get(word1)![i] - d.get(word2)![j]));
        if (d.get(word1)![i] < d.get(word2)![j]) {
            ++i;
        } else {
            ++j;
        }
    }
    return ans;
}
