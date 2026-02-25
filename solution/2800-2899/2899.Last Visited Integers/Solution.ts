function lastVisitedIntegers(nums: number[]): number[] {
    const seen: number[] = [];
    const ans: number[] = [];
    let k = 0;

    for (const x of nums) {
        if (x === -1) {
            if (++k > seen.length) {
                ans.push(-1);
            } else {
                ans.push(seen.at(-k)!);
            }
        } else {
            k = 0;
            seen.push(x);
        }
    }

    return ans;
}
