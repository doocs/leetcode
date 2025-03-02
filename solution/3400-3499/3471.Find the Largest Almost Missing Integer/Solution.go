func largestInteger(nums []int, k int) int {
    if k == 1 {
        cnt := make(map[int]int)
        for _, x := range nums {
            cnt[x]++
        }
        ans := -1
        for x, v := range cnt {
            if v == 1 {
                ans = max(ans, x)
            }
        }
        return ans
    }

    n := len(nums)
    if k == n {
        return slices.Max(nums)
    }

    f := func(k int) int {
        for i, x := range nums {
            if i != k && x == nums[k] {
                return -1
            }
        }
        return nums[k]
    }

    return max(f(0), f(n-1))
}
