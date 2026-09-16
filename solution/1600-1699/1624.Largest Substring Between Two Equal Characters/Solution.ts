function maxLengthBetweenEqualCharacters(s: string): number {
    const d = Array(26).fill(-1);
    let ans = -1;
    for (let i = 0; i < s.length; ++i) {
        const j = s.charCodeAt(i) - 97;
        if (d[j] === -1) {
            d[j] = i;
        } else {
            ans = Math.max(ans, i - d[j] - 1);
        }
    }
    return ans;
}
