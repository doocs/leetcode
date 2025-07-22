function countOperations(num1: number, num2: number): number {
    let ans = 0;
    while (num1 && num2) {
        if (num1 >= num2) {
            ans += (num1 / num2) | 0;
            num1 %= num2;
        } else {
            ans += (num2 / num1) | 0;
            num2 %= num1;
        }
    }
    return ans;
}
