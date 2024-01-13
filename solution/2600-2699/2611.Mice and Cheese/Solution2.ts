function miceAndCheese(reward1: number[], reward2: number[], k: number): number {
    const n = reward1.length;
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        ans += reward2[i];
        reward1[i] -= reward2[i];
    }
    reward1.sort((a, b) => b - a);
    for (let i = 0; i < k; ++i) {
        ans += reward1[i];
    }
    return ans;
}
