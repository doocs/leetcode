function circularPermutation(n: number, start: number): number[] {
    const ans: number[] = [];
    for (let i = 0; i < 1 << n; ++i) {
        ans.push(i ^ (i >> 1) ^ start);
    }
    return ans;
}
