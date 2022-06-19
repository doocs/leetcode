func longestSubsequence(s string, k int) int {
	ans := 0
	v := 0
	for i := len(s) - 1; i >= 0; i-- {
		if s[i] == '0' {
			ans++
		} else if ans < 32 && v+(1<<ans) <= k {
			v += 1 << ans
			ans++
		}
	}
	return ans
}