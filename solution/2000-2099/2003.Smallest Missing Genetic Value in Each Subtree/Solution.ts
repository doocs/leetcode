function smallestMissingValueSubtree(parents: number[], nums: number[]): number[] {
    const n = nums.length;
    const g: number[][] = Array.from({ length: n }, () => []);
    const vis: boolean[] = Array(n).fill(false);
    const has: boolean[] = Array(n + 2).fill(false);
    const ans: number[] = Array(n).fill(1);
    let idx = -1;
    for (let i = 0; i < n; ++i) {
        if (i) {
            g[parents[i]].push(i);
        }
        if (nums[i] === 1) {
            idx = i;
        }
    }
    if (idx === -1) {
        return ans;
    }
    const dfs = (i: number): void => {
        if (vis[i]) {
            return;
        }
        vis[i] = true;
        if (nums[i] < has.length) {
            has[nums[i]] = true;
        }
        for (const j of g[i]) {
            dfs(j);
        }
    };
    for (let i = 2; ~idx; idx = parents[idx]) {
        dfs(idx);
        while (has[i]) {
            ++i;
        }
        ans[idx] = i;
    }
    return ans;
}
