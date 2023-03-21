func maxNumOfMarkedIndices(nums []int) (ans int) {
	sort.Ints(nums)
	n := len(nums)
	for i, j := 0, (n+1)/2; j < n; i, j = i+1, j+1 {
		for j < n && nums[i]*2 > nums[j] {
			j++
		}
		if j < n {
			ans += 2
		}
	}
	return
}