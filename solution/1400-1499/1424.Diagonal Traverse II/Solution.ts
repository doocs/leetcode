function findDiagonalOrder(nums: number[][]): number[] {
    const arr: number[][] = [];
    for (let i = 0; i < nums.length; ++i) {
        for (let j = 0; j < nums[i].length; ++j) {
            arr.push([i + j, j, nums[i][j]]);
        }
    }
    arr.sort((a, b) => (a[0] === b[0] ? a[1] - b[1] : a[0] - b[0]));
    return arr.map(x => x[2]);
}
