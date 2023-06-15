func rangeSum(nums []int, n int, left int, right int) (ans int) {
	var arr []int
	for i := 0; i < n; i++ {
		s := 0
		for j := i; j < n; j++ {
			s += nums[j]
			arr = append(arr, s)
		}
	}
	sort.Ints(arr)
	const mod int = 1e9 + 7
	for _, x := range arr[left-1 : right] {
		ans = (ans + x) % mod
	}
	return
}