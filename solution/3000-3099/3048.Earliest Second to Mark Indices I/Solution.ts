function earliestSecondToMarkIndices(nums: number[], changeIndices: number[]): number {
    const [n, m] = [nums.length, changeIndices.length];
    let [l, r] = [1, m + 1];
    const check = (t: number): boolean => {
        const last: number[] = Array(n + 1).fill(0);
        for (let s = 0; s < t; ++s) {
            last[changeIndices[s]] = s;
        }
        let [decrement, marked] = [0, 0];
        for (let s = 0; s < t; ++s) {
            const i = changeIndices[s];
            if (last[i] === s) {
                if (decrement < nums[i - 1]) {
                    return false;
                }
                decrement -= nums[i - 1];
                ++marked;
            } else {
                ++decrement;
            }
        }
        return marked === n;
    };
    while (l < r) {
        const mid = (l + r) >> 1;
        if (check(mid)) {
            r = mid;
        } else {
            l = mid + 1;
        }
    }
    return l > m ? -1 : l;
}
