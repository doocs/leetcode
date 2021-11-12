function minimizedMaximum(n: number, quantities: number[]): number {
    const m = quantities.length;
    let left = 1, right = Math.max(...quantities);
    while (left <= right) {
        let mid = (left + right) >> 1;
        let sum = 0;
        for (let num of quantities) {
            let cur = Math.floor((num - 1) / mid) + 1;
            sum += cur;
        }
        if (sum > n) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }
    return left;
};