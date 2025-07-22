function totalReplacements(ranks: number[]): number {
    let [ans, cur] = [0, ranks[0]];
    for (const x of ranks) {
        if (x < cur) {
            cur = x;
            ans++;
        }
    }
    return ans;
}
