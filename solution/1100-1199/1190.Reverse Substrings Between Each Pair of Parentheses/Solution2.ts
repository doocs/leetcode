function reverseParentheses(s: string): string {
    const res: string[] = [];
    const n = s.length;
    const d = Array(n).fill(-1);
    const stk: number[] = [];

    for (let i = 0; i < n; i++) {
        if (s[i] === '(') stk.push(i);
        else if (s[i] === ')') {
            const j = stk.pop()!;
            d[i] = j;
            d[j] = i;
        }
    }

    for (let i = 0, forward = true; i < n; ) {
        const ch = s[i];

        switch (s[i]) {
            case '(':
            case ')':
                i = forward ? d[i] - 1 : d[i] + 1;
                forward = !forward;
                break;

            default:
                res.push(ch);
                i = forward ? i + 1 : i - 1;
        }
    }

    return res.join('');
}
