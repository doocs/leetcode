func minMovesToMakePalindrome(s string) int {
	cs := []byte(s)
	ans, n := 0, len(s)
	for i, j := 0, n-1; i < j; i++ {
		even := false
		for k := j; k != i; k-- {
			if cs[i] == cs[k] {
				even = true
				for ; k < j; k++ {
					cs[k], cs[k+1] = cs[k+1], cs[k]
					ans++
				}
				j--
				break
			}
		}
		if !even {
			ans += n/2 - i
		}
	}
	return ans
}