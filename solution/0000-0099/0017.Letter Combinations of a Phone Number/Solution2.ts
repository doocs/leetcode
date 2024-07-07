function letterCombinations(digits: string): string[] {
    if (digits.length === 0) {
        return [];
    }
    const ans: string[] = [];
    const n = digits.length;
    const d: string[][] = ['abc', 'def', 'ghi', 'jkl', 'mno', 'pqrs', 'tuv', 'wxyz'].map(x => [
        ...x,
    ]);

    const dfs = (curr: string, start: number) => {
        if (curr.length === n) {
            ans.push(curr);
            return;
        }
        for (let i = start; i < n; i++) {
            for (const ch of d[+digits[i] - 2]) {
                dfs(curr + ch, i + 1);
            }
        }
    };
    dfs('', 0);
    return ans;
}
