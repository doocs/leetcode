function calPoints(operations: string[]): number {
    const stk: number[] = [];
    for (const op of operations) {
        if (op === '+') {
            stk.push(stk.at(-1)! + stk.at(-2)!);
        } else if (op === 'D') {
            stk.push(stk.at(-1)! << 1);
        } else if (op === 'C') {
            stk.pop();
        } else {
            stk.push(+op);
        }
    }
    return stk.reduce((a, b) => a + b, 0);
}
