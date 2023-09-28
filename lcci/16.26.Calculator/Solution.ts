function calculate(s: string): number {
    const n = s.length;
    let x = 0;
    let sign = '+';
    const stk: number[] = [];
    for (let i = 0; i < n; ++i) {
        if (!isNaN(Number(s[i])) && s[i] !== ' ') {
            x = x * 10 + s[i].charCodeAt(0) - '0'.charCodeAt(0);
        }
        if (i === n - 1 || (isNaN(Number(s[i])) && s[i] !== ' ')) {
            switch (sign) {
                case '+':
                    stk.push(x);
                    break;
                case '-':
                    stk.push(-x);
                    break;
                case '*':
                    stk.push(stk.pop()! * x);
                    break;
                default:
                    stk.push((stk.pop()! / x) | 0);
            }
            x = 0;
            sign = s[i];
        }
    }
    return stk.reduce((x, y) => x + y);
}
