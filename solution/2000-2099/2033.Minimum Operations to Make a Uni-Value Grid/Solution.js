function minOperations(grid, x) {
    const arr = grid.flat(2);
    arr.sort((a, b) => a - b);
    const median = arr[Math.floor(arr.length / 2)];

    let res = 0;
    for (const val of arr) {
        const c = Math.abs(val - median) / x;
        if (c !== (c | 0)) return -1;
        res += c;
    }

    return res;
}
