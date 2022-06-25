func kthPalindrome(queries []int, intLength int) []int64 {
	l := (intLength + 1) >> 1
	start, end := int(math.Pow10(l-1)), int(math.Pow10(l))-1
	var ans []int64
	for _, q := range queries {
		v := start + q - 1
		if v > end {
			ans = append(ans, -1)
			continue
		}
		t := v
		if intLength%2 == 1 {
			t /= 10
		}
		for t > 0 {
			v = v*10 + t%10
			t /= 10
		}
		ans = append(ans, int64(v))
	}
	return ans
}