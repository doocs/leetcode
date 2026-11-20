import "sort"

func maxSelectedElements(nums []int) int {
    sort.Ints(nums)
    dp := map[int]int{}
    ans := 0

    for _, x := range nums {
        dp[x+1] = max(dp[x+1], dp[x]+1)
        dp[x] = max(dp[x], dp[x-1]+1)
        ans = max(ans, dp[x], dp[x+1])
    }

    return ans
}
