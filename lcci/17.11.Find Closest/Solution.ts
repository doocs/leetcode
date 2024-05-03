function findClosest(words: string[], word1: string, word2: string): number {
    let [i, j, ans] = [Infinity, -Infinity, Infinity];
    for (let k = 0; k < words.length; ++k) {
        if (words[k] === word1) {
            i = k;
        } else if (words[k] === word2) {
            j = k;
        }
        ans = Math.min(ans, Math.abs(i - j));
    }
    return ans;
}
