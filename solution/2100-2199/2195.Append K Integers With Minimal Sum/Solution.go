func minimalKSum(nums []int, k int) (ans int64) {
	nums = append(nums, []int{0, 2e9}...)
	sort.Ints(nums)
	for i, b := range nums[1:] {
		a := nums[i]
		m := max(0, min(k, b-a-1))
		ans += int64(a+1+a+m) * int64(m) / 2
		k -= m
	}
	return ans
}