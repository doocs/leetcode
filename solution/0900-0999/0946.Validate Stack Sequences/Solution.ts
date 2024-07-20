function validateStackSequences(pushed: number[], popped: number[]): boolean {
    const stk: number[] = [];
    let i = 0;
    for (const x of pushed) {
        stk.push(x);
        while (stk.length && stk.at(-1)! === popped[i]) {
            stk.pop();
            i++;
        }
    }
    return i === popped.length;
}
