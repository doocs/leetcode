declare global {
    interface Array<T> {
        snail(rowsCount: number, colsCount: number): number[][];
    }
}

Array.prototype.snail = function (
    rowsCount: number,
    colsCount: number,
): number[][] {
    if (rowsCount * colsCount !== this.length) {
        return [];
    }
    const ans: number[][] = Array.from({ length: rowsCount }, () =>
        Array(colsCount),
    );
    for (let h = 0, i = 0, j = 0, k = 1; h < this.length; ++h) {
        ans[i][j] = this[h];
        i += k;
        if (i === rowsCount || i === -1) {
            i -= k;
            k = -k;
            ++j;
        }
    }
    return ans;
};

/**
 * const arr = [1,2,3,4];
 * arr.snail(1,4); // [[1,2,3,4]]
 */
