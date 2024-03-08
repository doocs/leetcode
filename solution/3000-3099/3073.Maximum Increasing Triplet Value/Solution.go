func maximumTripletValue(nums []int) (ans int) {
	n := len(nums)
	right := make([]int, n)
	right[n-1] = nums[n-1]
	for i := n - 2; i >= 0; i-- {
		right[i] = max(nums[i], right[i+1])
	}
	ts := treemap.NewWithIntComparator()
	ts.Put(nums[0], nil)
	for j := 1; j < n-1; j++ {
		if right[j+1] > nums[j] {
			val, _ := ts.Floor(nums[j] - 1)
			if val != nil {
				ans = max(ans, val.(int)-nums[j]+right[j+1])
			}
		}
		ts.Put(nums[j], nil)
	}
	return
}