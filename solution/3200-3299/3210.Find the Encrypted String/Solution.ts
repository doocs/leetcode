function getEncryptedString(s: string, k: number): string {
    const cs: string[] = [];
    const n = s.length;
    for (let i = 0; i < n; ++i) {
        cs[i] = s[(i + k) % n];
    }
    return cs.join('');
}
