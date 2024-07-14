function sumOfEncryptedInt(nums: number[]): number {
    const encrypt = (x: number): number => {
        let [mx, p] = [0, 0];
        for (; x > 0; x = Math.floor(x / 10)) {
            mx = Math.max(mx, x % 10);
            p = p * 10 + 1;
        }
        return mx * p;
    };
    return nums.reduce((acc, x) => acc + encrypt(x), 0);
}
