function countDigitOccurrences(nums: number[], digit: number): number {
    let ans = 0;
    for (let x of nums) {
        for (; x; x = Math.floor(x / 10)) {
            if (x % 10 === digit) {
                ++ans;
            }
        }
    }
    return ans;
}
