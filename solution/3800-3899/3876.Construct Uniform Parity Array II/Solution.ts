function uniformArray(nums1: number[]): boolean {
    let mn = Number.MAX_SAFE_INTEGER;
    for (const x of nums1) {
        if (x % 2 === 1) {
            mn = Math.min(mn, x);
        }
    }
    for (const x of nums1) {
        if (x % 2 === 0 && mn !== Number.MAX_SAFE_INTEGER && x < mn) {
            return false;
        }
    }
    return true;
}
