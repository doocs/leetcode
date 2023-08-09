function smallestDivisor(nums: number[], threshold: number): number {
    let l = 1;
    let r = Math.max(...nums);
    while (l < r) {
        const mid = (l + r) >> 1;
        let s = 0;
        for (const x of nums) {
            s += Math.ceil(x / mid);
        }
        if (s <= threshold) {
            r = mid;
        } else {
            l = mid + 1;
        }
    }
    return l;
}
