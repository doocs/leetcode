function minElement(nums: number[]): number {
    let ans: number = 100;
    for (let x of nums) {
        let y = 0;
        for (; x; x = Math.floor(x / 10)) {
            y += x % 10;
        }
        ans = Math.min(ans, y);
    }
    return ans;
}
