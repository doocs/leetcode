func countOppositeParity(nums []int) []int {
    cnt := [2]int{}
    for _, x := range nums {
        cnt[x&1]++
    }
    n := len(nums)
    ans := make([]int, n)
    for i, x := range nums {
        cnt[x&1]--
        ans[i] = cnt[x&1^1]
    }
    return ans
}
