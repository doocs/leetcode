function verifyPostorder(postorder: number[]): boolean {
    const dfs = (l: number, r: number): boolean => {
        if (l >= r) {
            return true;
        }
        const v = postorder[r];
        let i = l;
        while (i < r && postorder[i] < v) {
            ++i;
        }
        for (let j = i; j < r; ++j) {
            if (postorder[j] < v) {
                return false;
            }
        }
        return dfs(l, i - 1) && dfs(i, r - 1);
    };
    return dfs(0, postorder.length - 1);
}
