function findMismatchedSegments(s1: string, s2: string): string[][] {
    const ans: string[][] = [];
    const n = s1.length;
    let i = 0;
    while (i < n) {
        if (s1[i] === s2[i]) {
            i++;
            continue;
        }
        let j = i + 1;
        while (j < n && s1[j] !== s2[j]) {
            j++;
        }
        ans.push([s1.slice(i, j), s2.slice(i, j)]);
        i = j;
    }
    return ans;
}
