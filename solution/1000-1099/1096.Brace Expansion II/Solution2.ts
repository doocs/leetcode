function braceExpansionII(expression: string): string[] {
    let i = 0;
    const expr = (): Set<string> => {
        const res = term();
        while (i < expression.length && expression[i] === ',') {
            ++i;
            for (const w of term()) {
                res.add(w);
            }
        }
        return res;
    };
    const term = (): Set<string> => {
        let res = new Set<string>(['']);
        while (i < expression.length && expression[i] !== ',' && expression[i] !== '}') {
            let cur: Set<string>;
            if (expression[i] === '{') {
                ++i;
                cur = expr();
                ++i;
            } else {
                let j = i + 1;
                while (j < expression.length && expression[j] >= 'a' && expression[j] <= 'z') {
                    ++j;
                }
                cur = new Set([expression.slice(i, j)]);
                i = j;
            }
            const nxt = new Set<string>();
            for (const a of res) {
                for (const b of cur) {
                    nxt.add(a + b);
                }
            }
            res = nxt;
        }
        return res;
    };
    return Array.from(expr()).sort();
}
