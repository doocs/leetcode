function verifyPostorder(postorder: number[]): boolean {
    const dfs = (start: number, end: number, maxVal: number) => {
        if (start > end) {
            return true;
        }
        const rootVal = postorder[end];
        for (let i = end; i >= start; i--) {
            const val = postorder[i];
            if (val > maxVal) {
                return false;
            }
            if (val < rootVal) {
                return dfs(start, i, rootVal) && dfs(i + 1, end - 1, maxVal);
            }
        }
        return dfs(start, end - 1, maxVal);
    };
    const n = postorder.length;
    return dfs(0, n - 1, Infinity);
}
