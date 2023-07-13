declare global {
    interface Array<T> {
        upperBound(target: number): number;
    }
}

Array.prototype.upperBound = function (target: number) {
    let left = 0;
    let right = this.length;
    while (left < right) {
        const mid = (left + right) >> 1;
        if (this[mid] > target) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    return left > 0 && this[left - 1] == target ? left - 1 : -1;
};

// [3,4,5].upperBound(5); // 2
// [1,4,5].upperBound(2); // -1
// [3,4,6,6,6,6,7].upperBound(6) // 5
