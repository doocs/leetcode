function maxEqualAdjacentPairs(nums: number[]): number {
    const cnt = new Map<number, number>();
    let ans = 0;
    let mx = 0;

    for (let i = 0; i + 1 < nums.length; i++) {
        let x = nums[i];
        let y = nums[i + 1];
        if (x === y) {
            ans++;
        } else {
            if (x > y) {
                [x, y] = [y, x];
            }
            const key = x * 2 ** 30 + y;
            cnt.set(key, (cnt.get(key) || 0) + 1);
            mx = Math.max(mx, cnt.get(key)!);
        }
    }
    ans += mx;
    return ans;
}
