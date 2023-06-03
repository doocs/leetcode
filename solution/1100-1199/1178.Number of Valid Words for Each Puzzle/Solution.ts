function findNumOfValidWords(words: string[], puzzles: string[]): number[] {
    const cnt: Map<number, number> = new Map();
    for (const w of words) {
        let mask = 0;
        for (const c of w) {
            mask |= 1 << (c.charCodeAt(0) - 97);
        }
        cnt.set(mask, (cnt.get(mask) || 0) + 1);
    }
    const ans: number[] = [];
    for (const p of puzzles) {
        let mask = 0;
        for (const c of p) {
            mask |= 1 << (c.charCodeAt(0) - 97);
        }
        let x = 0;
        const i = p.charCodeAt(0) - 97;
        for (let j = mask; j; j = (j - 1) & mask) {
            if ((j >> i) & 1) {
                x += cnt.get(j) || 0;
            }
        }
        ans.push(x);
    }
    return ans;
}
