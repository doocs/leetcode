function maxLengthBetweenEqualCharacters(s: string): number {
    const n = s.length;
    const pos = new Array(26).fill(-1);
    let res = -1;
    for (let i = 0; i < n; i++) {
        const j = s[i].charCodeAt(0) - 'a'.charCodeAt(0);
        if (pos[j] === -1) {
            pos[j] = i;
        } else {
            res = Math.max(res, i - pos[j] - 1);
        }
    }
    return res;
}
