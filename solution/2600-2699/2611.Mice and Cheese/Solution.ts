function miceAndCheese(
    reward1: number[],
    reward2: number[],
    k: number,
): number {
    const n = reward1.length;
    const idx = Array.from({ length: n }, (_, i) => i);
    idx.sort((i, j) => reward1[j] - reward2[j] - (reward1[i] - reward2[i]));
    let ans = 0;
    for (let i = 0; i < k; ++i) {
        ans += reward1[idx[i]];
    }
    for (let i = k; i < n; ++i) {
        ans += reward2[idx[i]];
    }
    return ans;
}
