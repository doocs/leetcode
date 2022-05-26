function calPoints(ops: string[]): number {
    const stack = [];
    for (const op of ops) {
        const n = stack.length;
        if (op === '+') {
            stack.push(stack[n - 1] + stack[n - 2]);
        } else if (op === 'D') {
            stack.push(stack[n - 1] * 2);
        } else if (op === 'C') {
            stack.pop();
        } else {
            stack.push(Number(op));
        }
    }
    return stack.reduce((p, v) => p + v);
}
