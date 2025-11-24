func maxBalancedSubarray(nums []int) (ans int) {
	d := map[int64]int{}
	a := 0
	b := len(nums)
	d[int64(b)] = -1
	for i, x := range nums {
		a ^= x
		if x%2 == 0 {
			b++
		} else {
			b--
		}
		key := int64(a)<<32 | int64(b)
		if j, ok := d[key]; ok {
			ans = max(ans, i-j)
		} else {
			d[key] = i
		}
	}
	return
}
