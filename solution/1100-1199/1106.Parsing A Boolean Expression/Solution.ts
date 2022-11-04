function parseBoolExpr(expression: string): boolean {
    const expr = expression;
    const n = expr.length;
    let i = 0;
    const dfs = () => {
        let res: boolean[] = [];
        while (i < n) {
            const c = expr[i++];
            if (c === ')') {
                break;
            }

            if (c === '!') {
                res.push(!dfs()[0]);
            } else if (c === '|') {
                res.push(dfs().some(v => v));
            } else if (c === '&') {
                res.push(dfs().every(v => v));
            } else if (c === 't') {
                res.push(true);
            } else if (c === 'f') {
                res.push(false);
            }
        }
        return res;
    };
    return dfs()[0];
}
