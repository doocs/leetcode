N = int(input())
nums = list(map(int, input().split()))


def merge_sort(nums, low, high):
    if low >= high:
        return
    mid = (low + high) >> 1
    merge_sort(nums, low, mid)
    merge_sort(nums, mid + 1, high)
    tmp = []
    i, j = low, mid + 1
    while i <= mid and j <= high:
        if nums[i] <= nums[j]:
            tmp.append(nums[i])
            i += 1
        else:
            tmp.append(nums[j])
            j += 1
    while i <= mid:
        tmp.append(nums[i])
        i += 1
    while j <= high:
        tmp.append(nums[j])
        j += 1
    
    j = 0
    for i in range(low, high + 1):
        nums[i] = tmp[j]
        j += 1


merge_sort(nums, 0, N - 1)
print(' '.join(list(map(str, nums))))
