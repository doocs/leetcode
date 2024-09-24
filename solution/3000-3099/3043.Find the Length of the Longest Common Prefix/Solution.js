var longestCommonPrefix = function(arr1, arr2) {
    let set = new Set();

    for (let x of arr1) {
        while (x > 0) {
            set.add(x);
            x = Math.floor(x / 10);
        }
    }

    let ans = 0;

    for (let x of arr2) {
        while (x > 0) {
            if (set.has(x)) {
                ans = Math.max(ans, Math.floor(Math.log10(x)) + 1);
                break;
            }
            x = Math.floor(x / 10);
        }
    }

    return ans;
};
