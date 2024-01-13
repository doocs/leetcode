func findErrorNums(nums []int) []int {
	n := len(nums)
	cnt := map[int]int{}
	for _, x := range nums {
		cnt[x]++
	}
	ans := make([]int, 2)
	for x := 1; x <= n; x++ {
		if cnt[x] == 2 {
			ans[0] = x
		} else if cnt[x] == 0 {
			ans[1] = x
		}
	}
	return ans
}