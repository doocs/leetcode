function evaluateExpression(expression: string): number {
    function parse(i: number): [number, number] {
        if (/\d/.test(expression[i]) || expression[i] === '-') {
            let j = i;
            if (expression[j] === '-') {
                j++;
            }
            while (j < expression.length && /\d/.test(expression[j])) {
                j++;
            }
            const num = +expression.slice(i, j);
            return [num, j];
        }

        let j = i;
        while (expression[j] !== '(') {
            j++;
        }
        const op = expression.slice(i, j);
        j++;

        const [val1, nextJ1] = parse(j);
        j = nextJ1 + 1;

        const [val2, nextJ2] = parse(j);
        j = nextJ2 + 1;

        let res: number;
        switch (op) {
            case 'add':
                res = val1 + val2;
                break;
            case 'sub':
                res = val1 - val2;
                break;
            case 'mul':
                res = val1 * val2;
                break;
            case 'div':
                res = Math.floor(val1 / val2);
                break;
            default:
                res = 0;
        }

        return [res, j];
    }

    return parse(0)[0];
}
