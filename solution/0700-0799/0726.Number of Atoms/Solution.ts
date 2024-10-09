function countOfAtoms(formula: string): string {
    const getCount = (formula: string, factor = 1) => {
        const n = formula.length;
        const cnt: Record<string, number> = {};
        const s: string[] = [];
        let [atom, c] = ['', 0];

        for (let i = 0; i <= n; i++) {
            if (formula[i] === '(') {
                const stk: string[] = ['('];
                let j = i;
                while (stk.length) {
                    j++;
                    if (formula[j] === '(') stk.push('(');
                    else if (formula[j] === ')') stk.pop();
                }

                const molecule = formula.slice(i + 1, j);
                const nextFactor: string[] = [];

                while (isDigit(formula[++j])) {
                    nextFactor.push(formula[j]);
                }

                const nextC = getCount(molecule, +nextFactor.join('') || 1);
                for (const [atom, c] of Object.entries(nextC)) {
                    cnt[atom] = (cnt[atom] ?? 0) + c * factor;
                }

                i = j - 1;
                continue;
            }

            if (s.length && (!formula[i] || isUpper(formula[i]))) {
                [atom, c] = getAtom(s);

                c *= factor;
                cnt[atom] = (cnt[atom] ?? 0) + c;
                s.length = 0;
            }

            s.push(formula[i]);
        }

        return cnt;
    };

    return Object.entries(getCount(formula))
        .sort(([a], [b]) => a.localeCompare(b))
        .map(([a, b]) => (b > 1 ? a + b : a))
        .join('');
}

const regex = {
    atom: /(\D+)(\d+)?/,
    isUpper: /[A-Z]+/,
};
const getAtom = (s: string[]): [string, number] => {
    const [_, atom, c] = regex.atom.exec(s.join(''))!;
    return [atom, c ? +c : 1];
};
const isDigit = (ch: string) => !Number.isNaN(Number.parseInt(ch));
const isUpper = (ch: string) => regex.isUpper.test(ch);
