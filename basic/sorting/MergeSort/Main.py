N = int(input())
nums = list(map(int, input().split()))


def merge_sort(nums, left, right):
    if left >= right:
        return
    mid = (left + right) >> 1
    merge_sort(nums, left, mid)
    merge_sort(nums, mid + 1, right)
    tmp = []
    i, j = left, mid + 1
    while i <= mid and j <= right:
        if nums[i] <= nums[j]:
            tmp.append(nums[i])
            i += 1
        else:
            tmp.append(nums[j])
            j += 1
    while i <= mid:
        tmp.append(nums[i])
        i += 1
    while j <= right:
        tmp.append(nums[j])
        j += 1

    j = 0
    for i in range(left, right + 1):
        nums[i] = tmp[j]
        j += 1


merge_sort(nums, 0, N - 1)
print(' '.join(list(map(str, nums))))
