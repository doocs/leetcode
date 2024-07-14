function maximumEnergy(energy: number[], k: number): number {
    const n = energy.length;
    let ans = -Infinity;
    for (let i = n - k; i < n; ++i) {
        for (let j = i, s = 0; j >= 0; j -= k) {
            s += energy[j];
            ans = Math.max(ans, s);
        }
    }
    return ans;
}
