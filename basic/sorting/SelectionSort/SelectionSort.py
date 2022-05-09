def selectionSort(arr):
    n = len(arr)
    for i in range(n - 1):
        min_index = i
        for j in range(i + 1, n):
            if arr[j] < arr[min_index]:
                min_index = j
        arr[min_index], arr[i] = arr[i], arr[min_index]


arr = [26, 11, 99, 33, 69, 77, 55, 56, 67]
selectionSort(arr)
print(arr)
