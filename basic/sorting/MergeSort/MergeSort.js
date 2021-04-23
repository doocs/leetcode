function mergeSort(arr) {
    if (arr.length < 2) return arr;
    let mid = Math.ceil(arr.length / 2);
    let arrLeft = mergeSort(arr.splice(0,mid));
    let arrRight = mergeSort(arr.splice(-mid));
    return merge(arrLeft,arrRight);

}

function merge(arr1, arr2) {
    let arr = [];
    while (arr1.length && arr2.length) {
        if (arr1[0] < arr2[0]) {
            arr.push(arr1.shift());
        } else {
            arr.push(arr2.shift());
        }
    }
    return [...arr, ...arr1, ...arr2];
}

arr = [3,5,6,2,1,7,4];
console.log(mergeSort(arr));