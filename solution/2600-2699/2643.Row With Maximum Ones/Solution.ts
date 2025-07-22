function rowAndMaximumOnes(mat: number[][]): number[] {
    const ans: number[] = [0, 0];
    for (let i = 0; i < mat.length; i++) {
        const cnt = mat[i].reduce((sum, num) => sum + num, 0);
        if (ans[1] < cnt) {
            ans[0] = i;
            ans[1] = cnt;
        }
    }
    return ans;
}
