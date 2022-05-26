function insertionSort(inputArr) {
    let len = inputArr.length;
    for (let i = 1; i <= len - 1; i++) {
        let temp = inputArr[i];
        let j = i - 1;
        while (j >= 0 && inputArr[j] > temp) {
            inputArr[j + 1] = inputArr[j];
            j--;
        }
        inputArr[j + 1] = temp;
    }
    return inputArr;
}

let arr = [6, 3, 2, 1, 5];
console.log(insertionSort(arr));
