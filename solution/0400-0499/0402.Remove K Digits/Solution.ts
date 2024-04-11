function removeKdigits(num: string, k: number): string {
    const stk: string[] = [];
    for (const c of num) {
        while (k && stk.length > 0 && stk[stk.length - 1] > c) {
            stk.pop();
            k--;
        }
        stk.push(c);
    }
    while (k--) {
        stk.pop();
    }
    return stk.join('').replace(/^0*/g, '') || '0';
}
