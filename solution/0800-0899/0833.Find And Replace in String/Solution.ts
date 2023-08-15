function findReplaceString(
    s: string,
    indices: number[],
    sources: string[],
    targets: string[],
): string {
    const n = s.length;
    const d: number[] = Array(n).fill(-1);
    for (let k = 0; k < indices.length; ++k) {
        const [i, src] = [indices[k], sources[k]];
        if (s.startsWith(src, i)) {
            d[i] = k;
        }
    }
    const ans: string[] = [];
    for (let i = 0; i < n; ) {
        if (d[i] >= 0) {
            ans.push(targets[d[i]]);
            i += sources[d[i]].length;
        } else {
            ans.push(s[i++]);
        }
    }
    return ans.join('');
}
