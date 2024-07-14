func isArraySpecial(nums []int, queries [][]int) (ans []bool) {
	n := len(nums)
	d := make([]int, n)
	for i := range d {
		d[i] = i
	}
	for i := 1; i < len(nums); i++ {
		if nums[i]%2 != nums[i-1]%2 {
			d[i] = d[i-1]
		}
	}
	for _, q := range queries {
		ans = append(ans, d[q[1]] <= q[0])
	}
	return
}