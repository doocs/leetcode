func minimalKSum(nums []int, k int) int64 {
	nums = append(nums, 0, 2e9)
	sort.Ints(nums)
	ans := 0
	for i := 1; i < len(nums); i++ {
		a, b := nums[i-1], nums[i]
		n := min(k, b-a-1)
		if n <= 0 {
			continue
		}
		k -= n
		ans += (a + 1 + a + n) * n / 2
		if k == 0 {
			break
		}
	}
	return int64(ans)
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}