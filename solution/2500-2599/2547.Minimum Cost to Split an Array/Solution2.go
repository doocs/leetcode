func minCost(nums []int, k int) int {
	n := len(nums)
	f := make([]int, n+1)
	for i := n - 1; i >= 0; i-- {
		cnt := make([]int, n)
		one := 0
		ans := k + n + 1
		for j := i; j < n; j++ {
			cnt[nums[j]]++
			x := cnt[nums[j]]
			if x == 1 {
				one++
			} else if x == 2 {
				one--
			}
			ans = min(ans, k+j-i+1-one+f[j+1])
		}
		f[i] = ans
	}
	return f[0]
}
