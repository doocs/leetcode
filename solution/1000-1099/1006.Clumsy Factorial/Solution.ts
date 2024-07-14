function clumsy(n: number): number {
    const stk: number[] = [n];
    let k = 0;
    for (let x = n - 1; x; --x) {
        if (k === 0) {
            stk.push(stk.pop()! * x);
        } else if (k === 1) {
            stk.push((stk.pop()! / x) | 0);
        } else if (k === 2) {
            stk.push(x);
        } else {
            stk.push(-x);
        }
        k = (k + 1) % 4;
    }
    return stk.reduce((acc, cur) => acc + cur, 0);
}
