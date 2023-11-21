function distinctNames(ideas: string[]): number {
    const s = new Set(ideas);
    const f: number[][] = Array(26)
        .fill(0)
        .map(() => Array(26).fill(0));
    for (const v of s) {
        const i = v.charCodeAt(0) - 'a'.charCodeAt(0);
        const t = [...v];
        for (let j = 0; j < 26; ++j) {
            t[0] = String.fromCharCode('a'.charCodeAt(0) + j);
            if (!s.has(t.join(''))) {
                f[i][j]++;
            }
        }
    }
    let ans = 0;
    for (const v of s) {
        const i = v.charCodeAt(0) - 'a'.charCodeAt(0);
        const t = [...v];
        for (let j = 0; j < 26; ++j) {
            t[0] = String.fromCharCode('a'.charCodeAt(0) + j);
            if (!s.has(t.join(''))) {
                ans += f[j][i];
            }
        }
    }
    return ans;
}
