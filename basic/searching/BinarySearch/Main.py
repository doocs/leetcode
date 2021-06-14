n, q = map(int, input().split())
nums = list(map(int, input().split()))

for _ in range(q):
    x = int(input())
    low, high = 0, n - 1
    while low < high:
        mid = (low + high) >> 1
        if nums[mid] >= x:
            high = mid
        else:
            low = mid + 1
    if nums[low] != x:
        print('-1 -1')
    else:
        t = low
        low, high = 0, n - 1
        while low < high:
            mid = (low + high + 1) >> 1
            if nums[mid] <= x:
                low = mid
            else:
                high = mid - 1
        print(f'{t} {low}')
