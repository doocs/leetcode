def bubbleSort(arr):
    hasChange = True
    n = len(arr)
    # Iterate over all array elements
    for i in range(n):
        if not hasChange:
            break

        hasChange = False
        # Last i elements are already in place
        for j in range(n - i - 1):
            if arr[j] > arr[j + 1]:
                arr[j], arr[j + 1] = arr[j + 1], arr[j]
                hasChange = True


arr = [64, 34, 25, 12, 22, 11, 90]
bubbleSort(arr)
print(arr)
