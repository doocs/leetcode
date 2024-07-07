function letterCombinations(digits: string): string[] {
    if (digits.length === 0) {
        return [];
    }
    const ans: string[] = [];
    const n = digits.length;
    const map: Record<string, string[]> = {
        2: [...'abc'],
        3: [...'def'],
        4: [...'ghi'],
        5: [...'jkl'],
        6: [...'mno'],
        7: [...'pqrs'],
        8: [...'tuv'],
        9: [...'wxyz'],
    };

    const dfs = (curr: string, start: number) => {
        if (curr.length === n) {
            ans.push(curr);
            return;
        }
        for (let i = start; i < n; i++) {
            for (const ch of map[digits[i]]) {
                dfs(curr + ch, i + 1);
            }
        }
    };
    dfs('', 0);
    return ans;
}
