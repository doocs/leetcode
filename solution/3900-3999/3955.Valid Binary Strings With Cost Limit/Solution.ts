function generateValidStrings(n: number, k: number): string[] {
    const ans: string[] = [];
    const path: string[] = [];

    const dfs = (i: number, tot: number): void => {
        if (i >= n) {
            ans.push(path.join(''));
            return;
        }

        path.push('0');
        dfs(i + 1, tot);
        path.pop();

        if ((path.length === 0 || path[path.length - 1] === '0') && tot + i <= k) {
            path.push('1');
            dfs(i + 1, tot + i);
            path.pop();
        }
    };

    dfs(0, 0);

    return ans;
}
