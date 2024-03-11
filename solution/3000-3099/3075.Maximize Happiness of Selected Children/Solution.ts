function maximumHappinessSum(happiness: number[], k: number): number {
    happiness.sort((a, b) => b - a);
    let ans = 0;
    for (let i = 0; i < k; ++i) {
        const x = happiness[i] - i;
        ans += Math.max(x, 0);
    }
    return ans;
}
