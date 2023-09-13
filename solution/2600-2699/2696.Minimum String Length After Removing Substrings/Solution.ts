function minLength(s: string): number {
    const stk: string[] = [''];
    for (const c of s) {
        if (c === 'B' && stk[stk.length - 1] === 'A') {
            stk.pop();
        } else if (c === 'D' && stk[stk.length - 1] === 'C') {
            stk.pop();
        } else {
            stk.push(c);
        }
    }
    return stk.length - 1;
}
