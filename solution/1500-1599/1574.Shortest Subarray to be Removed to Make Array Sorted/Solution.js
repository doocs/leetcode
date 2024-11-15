/**
 * @param {number[]} arr
 * @return {number}
 */
var findLengthOfShortestSubarray = function(arr) {
    const n = arr.length;
        let i = 0, j = n - 1;

        while (i + 1 < n && arr[i] <= arr[i + 1]) {
            i++;
        }

        while (j - 1 >= 0 && arr[j - 1] <= arr[j]) {
            j--;
        }

        if (i >= j) {
            return 0;
        }

        let ans = Math.min(n - i - 1, j);

        for (let l = 0; l <= i; l++) {
            const r = bisectLeft(arr, arr[l], j, n);
            ans = Math.min(ans, r - l - 1);
        }

        return ans;
};

let bisectLeft = (arr, x, lo, hi)=>{
        while (lo < hi) {
            const mid = Math.floor((lo + hi) / 2);
            if (arr[mid] < x) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
}
