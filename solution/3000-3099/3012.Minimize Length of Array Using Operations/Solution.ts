function minimumArrayLength(nums: number[]): number {
    const mi = Math.min(...nums);
    let cnt = 0;
    for (const x of nums) {
        if (x % mi) {
            return 1;
        }
        if (x === mi) {
            ++cnt;
        }
    }
    return (cnt + 1) >> 1;
}
