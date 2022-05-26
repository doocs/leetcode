function evalRPN(tokens: string[]): number {
    const stack = [];
    for (const token of tokens) {
        if (/\d/.test(token)) {
            stack.push(Number(token));
        } else {
            const a = stack.pop();
            const b = stack.pop();
            switch (token) {
                case '+':
                    stack.push(b + a);
                    break;
                case '-':
                    stack.push(b - a);
                    break;
                case '*':
                    stack.push(b * a);
                    break;
                case '/':
                    stack.push(~~(b / a));
                    break;
            }
        }
    }
    return stack[0];
}
