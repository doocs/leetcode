function combinationSum(candidates: number[], target: number): number[][] {
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
            t.push(candidates[j]);
            dfs(j, sum + candidates[j]);
            t.pop();
        }
    };
    dfs(0, 0);
    return res;
}
