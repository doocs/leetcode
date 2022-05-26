func rangeSum(nums []int, n int, left int, right int) int {
	var arr []int
	for i := 0; i < n; i++ {
		s := 0
		for j := i; j < n; j++ {
			s += nums[j]
			arr = append(arr, s)
		}
	}
	sort.Ints(arr)
	mod := int(1e9) + 7
	ans := 0
	for i := left - 1; i < right; i++ {
		ans = (ans + arr[i]) % mod
	}
	return ans
}