function searchMatrix(matrix: number[][], target: number): boolean {
    const n = matrix[0].length;
    for (const row of matrix) {
        const j = _.sortedIndex(row, target);
        if (j < n && row[j] === target) {
            return true;
        }
    }
    return false;
}
