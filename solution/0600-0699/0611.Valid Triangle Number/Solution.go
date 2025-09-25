func triangleNumber(nums []int) int {
	sort.Ints(nums)
	n := len(nums)
	ans := 0
	for i := 0; i < n-2; i++ {
		for j := i + 1; j < n-1; j++ {
			sum := nums[i] + nums[j]
			k := sort.SearchInts(nums[j+1:], sum) + j + 1 - 1
			if k > j {
				ans += k - j
			}
		}
	}
	return ans
}
