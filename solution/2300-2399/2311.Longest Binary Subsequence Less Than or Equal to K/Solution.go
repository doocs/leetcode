func longestSubsequence(s string, k int) (ans int) {
	for i, v := len(s)-1, 0; i >= 0; i-- {
		if s[i] == '0' {
			ans++
		} else if ans < 30 && (v|1<<ans) <= k {
			v |= 1 << ans
			ans++
		}
	}
	return
}