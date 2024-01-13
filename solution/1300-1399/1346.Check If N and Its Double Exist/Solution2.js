/**
 * @param {number[]} arr
 * @return {boolean}
 */
var checkIfExist = function (arr) {
    let cnt = 0;
    for (const v of arr) {
        if (v == 0) {
            ++cnt;
            if (cnt > 1) {
                return true;
            }
        }
    }
    const n = arr.length;
    arr.sort((a, b) => a - b);
    for (const v of arr) {
        if (v != 0) {
            let left = 0,
                right = n;
            while (left < right) {
                const mid = (left + right) >> 1;
                if (arr[mid] >= v * 2) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            if (left != n && arr[left] == v * 2) {
                return true;
            }
        }
    }
    return false;
};
