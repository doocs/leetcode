function solveEquation(equation: string): string {
    const [left, right] = equation.split('=');
    const createExpr = (s: string) => {
        let x = 0;
        let n = 0;
        let i = 0;
        let sym = 1;
        let cur = 0;
        let isX = false;
        for (const c of s) {
            if (c === '+' || c === '-') {
                if (isX) {
                    if (i === 0 && cur === 0) {
                        cur = 1;
                    }
                    x += cur * sym;
                } else {
                    n += cur * sym;
                }
                isX = false;
                cur = 0;
                i = 0;
                if (c === '+') {
                    sym = 1;
                } else {
                    sym = -1;
                }
            } else if (c === 'x') {
                isX = true;
            } else {
                i++;
                cur *= 10;
                cur += Number(c);
            }
        }
        if (isX) {
            if (i === 0 && cur === 0) {
                cur = 1;
            }
            x += cur * sym;
        } else {
            n += cur * sym;
        }
        return [x, n];
    };
    const lExpr = createExpr(left);
    const rExpr = createExpr(right);
    if (lExpr[0] === rExpr[0]) {
        if (lExpr[1] !== rExpr[1]) {
            return 'No solution';
        }
        return 'Infinite solutions';
    }
    return `x=${(lExpr[1] - rExpr[1]) / (rExpr[0] - lExpr[0])}`;
}
