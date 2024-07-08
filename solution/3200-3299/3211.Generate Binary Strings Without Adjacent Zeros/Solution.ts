function validStrings(n: number): string[] {
    const ans: string[] = [];
    const t: string[] = [];
    const dfs = (i: number) => {
        if (i >= n) {
            ans.push(t.join(''));
            return;
        }
        for (let j = 0; j < 2; ++j) {
            if ((j == 0 && (i == 0 || t[i - 1] == '1')) || j == 1) {
                t.push(j.toString());
                dfs(i + 1);
                t.pop();
            }
        }
    };
    dfs(0);
    return ans;
}
