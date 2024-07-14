function minAddToMakeValid(s: string): number {
    const stk: string[] = [];
    for (const c of s) {
        if (c === ')' && stk.length > 0 && stk.at(-1)! === '(') {
            stk.pop();
        } else {
            stk.push(c);
        }
    }
    return stk.length;
}
