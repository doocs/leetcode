var spiralOrder = (matrix: number[][]): number[] => {
    let ans: number[] = [];
    if (matrix.length === 0) return ans;
    let top = 0,
        left = 0,
        bottom = matrix.length - 1,
        right = matrix[0].length - 1;
    while (true) {
        for (let i = left; i <= right; i++) ans.push(matrix[top][i]);
        top++;
        if (top > bottom) break;
        for (let i = top; i <= bottom; i++) ans.push(matrix[i][right]);
        right--;
        if (right < left) break;
        for (let i = right; i >= left; i--) ans.push(matrix[bottom][i]);
        bottom--;
        if (bottom < top) break;
        for (let i = bottom; i >= top; i--) ans.push(matrix[i][left]);
        left++;
        if (left > right) break;
    }
    return ans;
};
