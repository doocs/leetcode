function resultingString(s: string): string {
    const stk: string[] = [];
    const isContiguous = (a: string, b: string): boolean => {
        const x = Math.abs(a.charCodeAt(0) - b.charCodeAt(0));
        return x === 1 || x === 25;
    };
    for (const c of s) {
        if (stk.length && isContiguous(stk.at(-1)!, c)) {
            stk.pop();
        } else {
            stk.push(c);
        }
    }
    return stk.join('');
}
