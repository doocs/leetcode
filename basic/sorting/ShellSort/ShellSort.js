function shellSort(arr) {
    var len = arr.length;
    var gapSize = Math.floor(len / 2);
    while (gapSize > 0) {
        for (var i = gapSize; i < len; i++) {
            var temp = arr[i];
            var j = i;

            while (j >= gapSize && arr[j - gapSize] > temp) {
                arr[j] = arr[j - gapSize];
                j -= gapSize;
            }
            arr[j] = temp;
        }
        gapSize = Math.floor(gapSize / 2);
    }
    return arr;
}

let arr = [6, 3, 2, 1, 5];
console.log(shellSort(arr));
