function combinationSum2(candidates: number[], target: number): number[][] {
    candidates.sort((a, b) => a - b);
    const n = candidates.length;
    const t: number[] = [];
    const res: number[][] = [];
    const dfs = (i: number, sum: number) => {
        if (sum > target) {
            return;
        }
        if (sum === target) {
            res.push([...t]);
            return;
        }
        for (let j = i; j < n; j++) {
            const num = candidates[j];
            if (j > i && num === candidates[j - 1]) {
                continue;
            }
            t.push(num);
            dfs(j + 1, sum + num);
            t.pop();
        }
    };
    dfs(0, 0);
    return res;
}
