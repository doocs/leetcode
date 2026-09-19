function countOfAtoms(formula: string): string {
    const cnt = new Map<string, number>();
    const stack: number[] = [];
    let multiplier = 1;
    let freq = 0;
    for (let i = formula.length - 1; i >= 0; --i) {
        const ch = formula.charCodeAt(i);
        if (ch >= 97 && ch <= 122) {
            const end = i--;
            while (i >= 0 && formula.charCodeAt(i) >= 97 && formula.charCodeAt(i) <= 122) {
                --i;
            }
            const key = formula.slice(i, end + 1);
            cnt.set(key, (cnt.get(key) ?? 0) + Math.max(freq, 1) * multiplier);
            freq = 0;
        } else if (ch >= 65 && ch <= 90) {
            const key = formula[i];
            cnt.set(key, (cnt.get(key) ?? 0) + Math.max(freq, 1) * multiplier);
            freq = 0;
        } else if (ch >= 48 && ch <= 57) {
            freq = ch - 48;
            let p = 10;
            while (
                i - 1 >= 0 &&
                formula.charCodeAt(i - 1) >= 48 &&
                formula.charCodeAt(i - 1) <= 57
            ) {
                freq += p * (formula.charCodeAt(--i) - 48);
                p *= 10;
            }
        } else if (formula[i] === ')') {
            stack.push(multiplier);
            multiplier *= Math.max(freq, 1);
            freq = 0;
        } else {
            multiplier = stack.pop()!;
        }
    }
    return [...cnt.entries()]
        .sort(([a], [b]) => a.localeCompare(b))
        .map(([k, v]) => (v > 1 ? k + v : k))
        .join('');
}
