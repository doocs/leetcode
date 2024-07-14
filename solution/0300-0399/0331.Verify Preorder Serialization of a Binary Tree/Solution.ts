function isValidSerialization(preorder: string): boolean {
    const stk: string[] = [];
    for (const s of preorder.split(',')) {
        stk.push(s);
        while (stk.length >= 3 && stk.at(-1) === '#' && stk.at(-2) === '#' && stk.at(-3) !== '#') {
            stk.splice(-3, 3, '#');
        }
    }
    return stk.length === 1 && stk[0] === '#';
}
