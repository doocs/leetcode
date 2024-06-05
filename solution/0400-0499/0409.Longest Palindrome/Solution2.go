func longestPalindrome(s string) (ans int) {
	odd := [128]int{}
	cnt := 0
	for _, c := range s {
		odd[c] ^= 1
		cnt += odd[c]
		if odd[c] == 0 {
			cnt--
		}
	}
	if cnt > 0 {
		return len(s) - cnt + 1
	}
	return len(s)
}
