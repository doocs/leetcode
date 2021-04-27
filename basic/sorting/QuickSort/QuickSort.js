function quickSort(arr) {
    let len = arr.length;
    return qSort(arr, 0, len - 1);
}

function qSort(arr, left, right) {
    if (left < right) {
        let index = partition(arr, left, right);
        qSort(arr, left, index - 1);
        qSort(arr, index + 1, right);
    }
    return arr;
}

function partition(arr, left, right) {
    let temp = arr[left];
    while (left < right) {
        while (left < right && arr[right] > temp) {
            right--;
        }
        arr[left] = arr[right];
        while (left < right && arr[left] <= temp) {
            left++;
        }
        arr[right] = arr[left];
    }
    arr[left] = temp;
    console.log(arr)
    return left;
}

arr = [3, 5, 6, 2, 1, 7, 4];
console.log(quickSort(arr))
