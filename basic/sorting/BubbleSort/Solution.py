def bubbleSort(arr):
    n = len(arr)
    # Iterate over all array elements
    for i in range(n):
        # Last i elements are already in place
        for j in range(n - i - 1):
            if arr[j] > arr[j + 1]:
                arr[j], arr[j + 1] = arr[j + 1], arr[j]


# 改进版本
def bubbleSort(arr):
    n = len(arr)
    for i in range(n - 1):
        has_change = False
        for j in range(n - i - 1):
            if arr[j] > arr[j + 1]:
                arr[j], arr[j + 1] = arr[j + 1], arr[j]
                has_change = True
        if not has_change:
            break


arr = [64, 34, 25, 12, 22, 11, 90]
bubbleSort(arr)
print(arr)
