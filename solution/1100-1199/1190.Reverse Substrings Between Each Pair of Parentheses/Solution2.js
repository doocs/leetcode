function reverseParentheses(s) {
    const res = [];
    const n = s.length;
    const pairs = Array(n).fill(-1);
    const stack = [];

    for (let i = 0; i < n; i++) {
        if (s[i] === '(') stack.push(i);
        else if (s[i] === ')') {
            const j = stack.pop();
            pairs[i] = j;
            pairs[j] = i;
        }
    }

    for (let i = 0, forward = true; i < n; ) {
        const ch = s[i];

        switch (s[i]) {
            case '(':
            case ')':
                i = forward ? pairs[i] - 1 : pairs[i] + 1;
                forward = !forward;
                break;

            default:
                res.push(ch);
                i = forward ? i + 1 : i - 1;
        }
    }

    return res.join('');
}
