function countOperations(num1: number, num2: number): number {
    let ans = 0;
    for (; num1 && num2; ++ans) {
        if (num1 >= num2) {
            num1 -= num2;
        } else {
            num2 -= num1;
        }
    }
    return ans;
}
