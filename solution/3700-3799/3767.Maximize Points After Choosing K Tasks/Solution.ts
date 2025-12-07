function maxPoints(technique1: number[], technique2: number[], k: number): number {
    const n = technique1.length;
    const idx = Array.from({ length: n }, (_, i) => i);

    idx.sort((i, j) => technique1[j] - technique2[j] - (technique1[i] - technique2[i]));

    let ans = technique2.reduce((sum, x) => sum + x, 0);

    for (let i = 0; i < k; i++) {
        const index = idx[i];
        ans -= technique2[index];
        ans += technique1[index];
    }

    for (let i = k; i < n; i++) {
        const index = idx[i];
        if (technique1[index] >= technique2[index]) {
            ans -= technique2[index];
            ans += technique1[index];
        }
    }

    return ans;
}
