func checkSubarraySum(nums []int, k int) bool {
	d := map[int]int{0: -1}
	s := 0
	for i, x := range nums {
		s = (s + x) % k
		if _, ok := d[s]; !ok {
			d[s] = i
		} else if i-d[s] > 1 {
			return true
		}
	}
	return false
}