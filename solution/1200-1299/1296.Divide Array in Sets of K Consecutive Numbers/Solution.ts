function isPossibleDivide(nums: number[], k: number): boolean {
    if (nums.length % k !== 0) {
        return false;
    }
    const cnt = new Map<number, number>();
    for (const x of nums) {
        cnt.set(x, (cnt.get(x) || 0) + 1);
    }
    nums.sort((a, b) => a - b);
    for (const x of nums) {
        if (cnt.get(x)! > 0) {
            for (let y = x; y < x + k; y++) {
                if ((cnt.get(y) || 0) === 0) {
                    return false;
                }
                cnt.set(y, cnt.get(y)! - 1);
            }
        }
    }
    return true;
}
