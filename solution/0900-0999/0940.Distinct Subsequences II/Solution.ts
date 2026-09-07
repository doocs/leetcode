function distinctSubseqII(s: string): number {
    const mod = 1e9 + 7;
    const f: number[] = Array(26).fill(0);
    for (const c of s) {
        f[c.charCodeAt(0) - 97] = f.reduce((acc, v) => (acc + v) % mod, 1);
    }
    return f.reduce((acc, v) => (acc + v) % mod);
}
