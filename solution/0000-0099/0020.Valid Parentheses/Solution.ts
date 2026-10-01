function isValid(s: string): boolean {
    const d = new Map<string, string>([
        ['(', ')'],
        ['[', ']'],
        ['{', '}'],
    ]);
    const stk: string[] = [];
    for (const c of s) {
        if (d.has(c)) {
            stk.push(d.get(c)!);
        } else if (stk.pop() !== c) {
            return false;
        }
    }
    return stk.length === 0;
}
