func smallestNumber(n int64) string {
	cnt := [10]int{}
	for i := 9; i > 1; i-- {
		for n%int64(i) == 0 {
			cnt[i]++
			n /= int64(i)
		}
	}
	if n != 1 {
		return "-1"
	}
	sb := &strings.Builder{}
	for i := 2; i < 10; i++ {
		for j := 0; j < cnt[i]; j++ {
			sb.WriteByte(byte(i) + '0')
		}
	}
	ans := sb.String()
	if len(ans) > 0 {
		return ans
	}
	return "1"
}