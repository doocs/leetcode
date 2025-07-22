function longestCommonSubsequence(arrays: number[][]): number[] {
    const cnt: number[] = Array(101).fill(0);
    for (const row of arrays) {
        for (const x of row) {
            ++cnt[x];
        }
    }
    const ans: number[] = [];
    for (let i = 0; i < 101; ++i) {
        if (cnt[i] === arrays.length) {
            ans.push(i);
        }
    }
    return ans;
}
