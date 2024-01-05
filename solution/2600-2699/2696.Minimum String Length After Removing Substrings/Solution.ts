function minLength(s: string): number {
    const stk: string[] = [''];
    for (const c of s) {
        if (c === 'B' && stk.at(-1)! === 'A') {
            stk.pop();
        } else if (c === 'D' && stk.at(-1)! === 'C') {
            stk.pop();
        } else {
            stk.push(c);
        }
    }
    return stk.length - 1;
}
