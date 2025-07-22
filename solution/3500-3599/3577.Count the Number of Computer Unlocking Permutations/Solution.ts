function countPermutations(complexity: number[]): number {
    const mod = 1e9 + 7;
    let ans = 1;
    for (let i = 1; i < complexity.length; i++) {
        if (complexity[i] <= complexity[0]) {
            return 0;
        }
        ans = (ans * i) % mod;
    }
    return ans;
}
