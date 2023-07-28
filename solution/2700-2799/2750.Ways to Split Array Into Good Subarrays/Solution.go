func numberOfGoodSubarraySplits(nums []int) int {
	const mod int = 1e9 + 7
	ans, j := 1, -1
	for i, x := range nums {
		if x == 0 {
			continue
		}
		if j > -1 {
			ans = ans * (i - j) % mod
		}
		j = i
	}
	if j == -1 {
		return 0
	}
	return ans
}