function selectionSort(inputArr) {
    let len = inputArr.length;
    for (let i = 0; i <= len - 2; i++) {
        let j = i;
        let min = j;
        while (j <= len - 1) {
            if (inputArr[j] < inputArr[min]) min = j;
            j++;
        }
        let temp = inputArr[i];
        inputArr[i] = inputArr[min];
        inputArr[min] = temp;
    }
    return inputArr;
}

let arr = [6, 3, 2, 1, 5];
console.log(selectionSort(arr));
