function majorityElement(nums: number[]): number {
    let cnt: number = 0;
    let m: number = 0;
    for (const x of nums) {
        if (cnt === 0) {
            m = x;
            cnt = 1;
        } else {
            cnt += m === x ? 1 : -1;
        }
    }
    return m;
}
