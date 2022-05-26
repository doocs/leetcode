func checkSubarraySum(nums []int, k int) bool {
	mp := map[int]int{0: -1}
	s := 0
	for i, v := range nums {
		s += v
		r := s % k
		if j, ok := mp[r]; ok && i-j >= 2 {
			return true
		}
		if _, ok := mp[r]; !ok {
			mp[r] = i
		}
	}
	return false
}