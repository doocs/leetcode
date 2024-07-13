func minKBitFlips(nums []int, k int) (ans int) {
	n := len(nums)
	d := make([]int, n+1)
	s := 0
	for i, x := range nums {
		s += d[i]
		if s%2 == x {
			if i+k > n {
				return -1
			}
			d[i]++
			d[i+k]--
			s++
			ans++
		}
	}
	return
}
