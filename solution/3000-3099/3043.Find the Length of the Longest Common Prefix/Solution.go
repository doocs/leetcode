func longestCommonPrefix(arr1 []int, arr2 []int) int {
	s := map[int]bool{}
	for _, x := range arr1 {
		for ; x > 0; x /= 10 {
			s[x] = true
		}
	}
	mx := 0
	for _, x := range arr2 {
		for ; x > 0; x /= 10 {
			if s[x] {
				mx = max(mx, x)
				break
			}
		}
	}
	if mx > 0 {
		return len(strconv.Itoa(mx))
	}
	return 0
}
