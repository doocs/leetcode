function decrypt(code: number[], k: number): number[] {
    const n = code.length;
    if (k === 0) {
        return code.fill(0);
    }
    const isPrefix = k < 0;
    if (isPrefix) {
        k *= -1;
    }
    const map = new Map<number, [number, number]>();
    let prefix = 0;
    let suffix = 0;
    for (let i = 1; i <= k; i++) {
        prefix += code[n - i];
        suffix += code[i];
    }
    map.set(0, [prefix, suffix]);

    for (let i = 1; i < n; i++) {
        let [p, s] = map.get(i - 1);
        p -= code[n - k - 1 + i] ?? code[i - k - 1];
        p += code[i - 1];
        s -= code[i];
        s += code[i + k] ?? code[i + k - n];
        map.set(i, [p, s]);
    }
    for (let i = 0; i < n; i++) {
        code[i] = map.get(i)[Number(!isPrefix)];
    }
    return code;
}
