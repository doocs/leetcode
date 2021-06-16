n, q = map(int, input().split())
nums = list(map(int, input().split()))

for _ in range(q):
    x = int(input())
    left, right = 0, n - 1
    while left < right:
        mid = (left + right) >> 1
        if nums[mid] >= x:
            right = mid
        else:
            left = mid + 1
    if nums[left] != x:
        print('-1 -1')
    else:
        t = left
        left, right = 0, n - 1
        while left < right:
            mid = (left + right + 1) >> 1
            if nums[mid] <= x:
                left = mid
            else:
                right = mid - 1
        print(f'{t} {left}')
