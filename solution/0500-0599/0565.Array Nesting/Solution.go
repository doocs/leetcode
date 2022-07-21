func arrayNesting(nums []int) int {
	ans, n := 0, len(nums)
	for i := range nums {
		cnt, j := 0, i
		for nums[j] != n {
			k := nums[j]
			nums[j] = n
			j = k
			cnt++
		}
		if ans < cnt {
			ans = cnt
		}
	}
	return ans
}