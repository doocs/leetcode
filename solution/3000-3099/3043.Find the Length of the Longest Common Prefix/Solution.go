func longestCommonPrefix(arr1 []int, arr2 []int) (ans int) {
	s := map[int]bool{}
	for _, x := range arr1 {
		for ; x > 0; x /= 10 {
			s[x] = true
		}
	}
	for _, x := range arr2 {
		for ; x > 0; x /= 10 {
			if s[x] {
				ans = max(ans, int(math.Log10(float64(x)))+1)
				break
			}
		}
	}
	return
}