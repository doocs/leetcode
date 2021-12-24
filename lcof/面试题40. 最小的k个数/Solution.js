/**
 * @param {number[]} arr
 * @param {number} k
 * @return {number[]}
 */
var getLeastNumbers = function (arr, k) {
    // 排序
    // return arr.sort((a,b)=>a-b).slice(0,k)
    // ==========================================
    // 快排思想
    let left = 0;
    let right = arr.length - 1;
    while (left < right) {
        let i = partition(left, right);
        if (i <= k) {
            left = i + 1;
        }
        if (i >= k) {
            right = i - 1;
        }
    }
    function partition(left, right) {
        let pivot = arr[left];
        while (left < right) {
            while (left < right && arr[right] >= pivot) {
                right--;
            }
            arr[left] = arr[right];
            while (left < right && arr[left] <= pivot) {
                left++;
            }
            arr[right] = arr[left];
        }
        arr[left] = pivot;
        return left;
    }
    return arr.slice(0, k);
};
