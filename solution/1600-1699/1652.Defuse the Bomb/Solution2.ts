function decrypt(code: number[], k: number): number[] {
    const n: number = code.length;
    const ans: number[] = Array(n).fill(0);

    if (k === 0) {
        return ans;
    }

    const s: number[] = Array((n << 1) | 1).fill(0);
    for (let i = 0; i < n << 1; ++i) {
        s[i + 1] = s[i] + code[i % n];
    }

    for (let i = 0; i < n; ++i) {
        if (k > 0) {
            ans[i] = s[i + k + 1] - s[i + 1];
        } else {
            ans[i] = s[i + n] - s[i + k + n];
        }
    }

    return ans;
}
