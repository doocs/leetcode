function letterCombinations(digits: string): string[] {
    if (digits.length == 0) {
        return [];
    }
    const ans: string[] = [''];
    const d = ['abc', 'def', 'ghi', 'jkl', 'mno', 'pqrs', 'tuv', 'wxyz'];
    for (const i of digits) {
        const s = d[parseInt(i) - 2];
        const t: string[] = [];
        for (const a of ans) {
            for (const b of s) {
                t.push(a + b);
            }
        }
        ans.splice(0, ans.length, ...t);
    }
    return ans;
}
