function largestSubmatrix(matrix: number[][]): number {
    for (let column = 0; column < matrix[0].length; column++) {
        for (let row = 0; row < matrix.length; row++) {
            let tempRow = row;
            let count = 0;

            while (tempRow < matrix.length && matrix[tempRow][column] === 1) {
                count++;
                tempRow++;
            }

            while (count !== 0) {
                matrix[row][column] = count;
                count--;
                row++;
            }
        }
    }

    for (let row = 0; row < matrix.length; row++) {
        matrix[row].sort((a, b) => a - b);
    }

    let maxSubmatrixArea = 0;

    for (let row = 0; row < matrix.length; row++) {
        for (let col = matrix[row].length - 1; col >= 0; col--) {
            maxSubmatrixArea = Math.max(
                maxSubmatrixArea,
                matrix[row][col] * (matrix[row].length - col),
            );
        }
    }

    return maxSubmatrixArea;
}
