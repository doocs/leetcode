func partitionString(s string) int {
	ans, v := 1, 0
	for _, c := range s {
		i := int(c - 'a')
		if v>>i&1 == 1 {
			v = 0
			ans++
		}
		v |= 1 << i
	}
	return ans
}