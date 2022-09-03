function findLongestChain(pairs: number[][]): number {
    pairs.sort((a, b) => a[1] - b[1]);
    let res = 0;
    let pre = -Infinity;
    for (const [a, b] of pairs) {
        if (pre < a) {
            pre = b;
            res++;
        }
    }
    return res;
}
