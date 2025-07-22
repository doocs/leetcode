function minLength(s: string): number {
    const stk: string[] = [];
    for (const c of s) {
        if ((stk.at(-1) === 'A' && c === 'B') || (stk.at(-1) === 'C' && c === 'D')) {
            stk.pop();
        } else {
            stk.push(c);
        }
    }
    return stk.length;
}
