function countNicePairs(nums: number[]): number {
    const rev = (x: number): number => {
        let y = 0;
        while (x) {
            y = y * 10 + (x % 10);
            x = Math.floor(x / 10);
        }
        return y;
    };
    const mod = 10 ** 9 + 7;
    const cnt = new Map<number, number>();
    let ans = 0;
    for (const x of nums) {
        const y = x - rev(x);
        ans = (ans + (cnt.get(y) ?? 0)) % mod;
        cnt.set(y, (cnt.get(y) ?? 0) + 1);
    }
    return ans;
}
