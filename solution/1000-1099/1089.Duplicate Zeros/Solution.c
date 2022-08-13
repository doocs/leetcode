void duplicateZeros(int* arr, int arrSize) {
    int i = 0;
    int j = 0;
    while (j < arrSize) {
        if (arr[i] == 0) {
            j++;
        }
        i++;
        j++;
    }
    i--;
    j--;
    while (i >= 0) {
        if (arr[i] == 0) {
            if (j < arrSize) {
                arr[j] = arr[i];
            }
            j--;
        }
        arr[j] = arr[i];
        i--;
        j--;
    }
}