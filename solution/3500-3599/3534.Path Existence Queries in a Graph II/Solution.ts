function pathExistenceQueries(
    n: number,
    nums: number[],
    maxDiff: number,
    queries: number[][],
): number[] {
    const pairs: number[][] = [];
    for (let i = 0; i < n; i++) {
        pairs.push([nums[i], i]);
    }
    pairs.sort((a, b) => a[0] - b[0]);

    const m = 20;
    const f = Array.from({ length: n }, () => Array(m).fill(0));

    let r = n - 1;
    for (let l = n - 1; l >= 0; l--) {
        while (pairs[r][0] - pairs[l][0] > maxDiff) {
            r--;
        }
        let i = pairs[l][1],
            j = pairs[r][1];
        f[i][0] = j;
        for (let k = 1; k < m; k++) {
            f[i][k] = f[f[i][k - 1]][k - 1];
        }
    }

    const ans: number[] = [];
    for (const q of queries) {
        let i = q[0],
            j = q[1];
        if (nums[i] > nums[j]) {
            [i, j] = [j, i];
        }
        if (i === j) {
            ans.push(0);
            continue;
        }
        if (nums[i] === nums[j]) {
            ans.push(1);
            continue;
        }
        let d = 0;
        for (let k = m - 1; k >= 0; k--) {
            if (nums[f[i][k]] < nums[j]) {
                d |= 1 << k;
                i = f[i][k];
            }
        }
        if (nums[f[i][0]] < nums[j]) {
            ans.push(-1);
        } else {
            ans.push(d + 1);
        }
    }
    return ans;
}
