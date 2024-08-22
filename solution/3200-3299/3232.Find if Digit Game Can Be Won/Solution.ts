function canAliceWin(nums: number[]): boolean {
    let [a, b] = [0, 0];
    for (const x of nums) {
        if (x < 10) {
            a += x;
        } else {
            b += x;
        }
    }
    return a !== b;
}
