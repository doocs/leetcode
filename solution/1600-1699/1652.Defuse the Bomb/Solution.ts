function decrypt(code: number[], k: number): number[] {
    const n: number = code.length;
    const ans: number[] = Array(n).fill(0);

    if (k === 0) {
        return ans;
    }

    for (let i = 0; i < n; ++i) {
        if (k > 0) {
            for (let j = i + 1; j < i + k + 1; ++j) {
                ans[i] += code[j % n];
            }
        } else {
            for (let j = i + k; j < i; ++j) {
                ans[i] += code[(j + n) % n];
            }
        }
    }

    return ans;
}
