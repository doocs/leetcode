const map = {
    '2': ['a', 'b', 'c'],
    '3': ['d', 'e', 'f'],
    '4': ['g', 'h', 'i'],
    '5': ['j', 'k', 'l'],
    '6': ['m', 'n', 'o'],
    '7': ['p', 'q', 'r', 's'],
    '8': ['t', 'u', 'v'],
    '9': ['w', 'x', 'y', 'z'],
};

function letterCombinations(digits: string): string[] {
    const n = digits.length;
    if (n === 0) {
        return [];
    }
    const res = [];
    const dfs = (i: number, str: string) => {
        if (i === n) {
            res.push(str);
            return;
        }
        for (const c of map[digits[i]]) {
            dfs(i + 1, str + c);
        }
    };
    dfs(0, '');
    return res;
}
