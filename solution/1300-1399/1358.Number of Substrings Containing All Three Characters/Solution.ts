function numberOfSubstrings(s: string): number {
    const d: number[] = [-1, -1, -1];
    let ans = 0;

    for (let i = 0; i < s.length; i++) {
        const c = s.charCodeAt(i) - 97;
        d[c] = i;

        ans += Math.min(d[0], Math.min(d[1], d[2])) + 1;
    }

    return ans;
}
