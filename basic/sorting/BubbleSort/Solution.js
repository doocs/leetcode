function bubbleSort(inputArr) {
    for (let i = inputArr.length - 1; i > 0; i--) {
        let hasChange = false;
        for (let j = 0; j < i; j++) {
            if (inputArr[j] > inputArr[j + 1]) {
                const temp = inputArr[j];
                inputArr[j] = inputArr[j + 1];
                inputArr[j + 1] = temp;
                hasChange = true;
            }
        }

        if (!hasChange) {
            break;
        }
    }

    return inputArr;
}

const arr = [6, 3, 2, 1, 5];
console.log(bubbleSort(arr));
