function probabilityOfHeads(prob: number[], target: number): number {
    const f = new Array(target + 1).fill(0);
    f[0] = 1;
    for (const p of prob) {
        for (let j = target; j >= 0; --j) {
            f[j] *= 1 - p;
            if (j > 0) {
                f[j] += f[j - 1] * p;
            }
        }
    }
    return f[target];
}
