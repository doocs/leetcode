function findLongestChain(pairs: number[][]): number {
    pairs.sort((a, b) => a[1] - b[1]);
    let [ans, pre] = [0, -Infinity];
    for (const [a, b] of pairs) {
        if (pre < a) {
            ++ans;
            pre = b;
        }
    }
    return ans;
}
