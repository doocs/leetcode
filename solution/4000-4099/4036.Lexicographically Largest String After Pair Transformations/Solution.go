func largestString(nums []int) []string {
	ans := make([]string, 0, len(nums))
	for _, x := range nums {
		s := []byte{}
		for j := 25; j >= 0; j-- {
			for t := x >> j; t > 0; t-- {
				s = append(s, byte('a'+j))
			}
			x &= (1 << j) - 1
		}
		ans = append(ans, string(s))
	}
	return ans
}
