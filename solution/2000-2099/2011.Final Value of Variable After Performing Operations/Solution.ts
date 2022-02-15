function finalValueAfterOperations(operations: string[]): number {
    let ans = 0;
    for (let operation of operations) {
        ans += operation.includes('+') ? 1 : -1;
    }
    return ans;
}
