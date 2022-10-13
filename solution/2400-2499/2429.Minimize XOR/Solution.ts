function minimizeXor(num1: number, num2: number): number {
    let ans = num1;
    let target = num1.toString(2).split('').reduce((a, c) => a + Number(c), 0);
    let count = num2.toString(2).split('').reduce((a, c) => a + Number(c), 0);
    while (count > target) {
        ans |= ans + 1;
        count -= 1;
    }
    while (count < target) {
        ans &= ans - 1;
        count += 1;
    }
    return ans;
};