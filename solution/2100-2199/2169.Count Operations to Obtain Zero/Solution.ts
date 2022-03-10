function countOperations(num1: number, num2: number): number {
    let ans = 0;
    while (num1 && num2) {
        [num1, num2] = [Math.min(num1, num2), Math.abs(num1 - num2)];
        ans++;
    }
    return ans;
}
