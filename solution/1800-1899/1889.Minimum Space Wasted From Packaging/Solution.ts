function minWastedSpace(packages: number[], boxes: number[][]): number {
    const MOD = 10 ** 9 + 7;
    packages.sort((a, b) => a - b);
    const max_package = packages[packages.length - 1];
    const total = packages.reduce((a, c) => a + c, 0);
    let res = Infinity;
    for (let box of boxes) {
        box.sort((a, b) => a - b);
        if (max_package > box[box.length - 1]) continue;
        let left = 0,
            sum = 0;
        for (let capacity of box) {
            let right = searchRight(packages, capacity, left);
            sum += (right - left) * capacity;
            left = right;
        }
        res = Math.min(res, sum);
    }
    return res == Infinity ? -1 : (res - total) % MOD;
}

function searchRight(packages: number[], target: number, left: number): number {
    let right = packages.length;
    while (left < right) {
        let mid = (left + right) >> 1;
        if (packages[mid] <= target) {
            left = mid + 1;
        } else {
            right = mid;
        }
    }
    return left;
}
