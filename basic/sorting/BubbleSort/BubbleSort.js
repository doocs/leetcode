function bubbleSort(inputArr) {
    let len = inputArr.length;
    let swapped = false;
    for (let i = 1; i <= len - 1; i++) {
        swapped = false;
        for (let j = 0; j < len - 1; j++) {
            if (inputArr[j] > inputArr[j + 1]) {
                let temp = inputArr[j];
                inputArr[j] = inputArr[j + 1];
                inputArr[j + 1] = temp;
                swapped = true;
            }
        }
        if (swapped === false) break;
    }
    return inputArr;
}

let arr = [6, 3, 2, 1, 5];
console.log(bubbleSort(arr));
