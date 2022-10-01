func minimumOperations(nums []int) int {
	i, j := 0, len(nums)-1
	a, b := nums[i], nums[j]
	ans := 0
	for i < j {
		if a < b {
			i++
			a += nums[i]
			ans++
		} else if b < a {
			j--
			b += nums[j]
			ans++
		} else {
			i, j = i+1, j-1
			a, b = nums[i], nums[j]
		}
	}
	return ans
}