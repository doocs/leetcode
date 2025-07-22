function largeGroupPositions(s: string): number[][] {
    const n = s.length;
    const ans: number[][] = [];

    for (let i = 0; i < n; ) {
        let j = i;
        while (j < n && s[j] === s[i]) {
            ++j;
        }
        if (j - i >= 3) {
            ans.push([i, j - 1]);
        }
        i = j;
    }

    return ans;
}
