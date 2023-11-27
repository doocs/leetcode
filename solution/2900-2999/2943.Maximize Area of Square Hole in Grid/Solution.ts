function maximizeSquareHoleArea(n: number, m: number, hBars: number[], vBars: number[]): number {
    const f = (nums: number[]): number => {
        nums.sort((a, b) => a - b);
        let [ans, cnt] = [1, 1];
        for (let i = 1; i < nums.length; ++i) {
            if (nums[i] === nums[i - 1] + 1) {
                ans = Math.max(ans, ++cnt);
            } else {
                cnt = 1;
            }
        }
        return ans + 1;
    };
    return Math.min(f(hBars), f(vBars)) ** 2;
}
