func shortestBeautifulSubstring(s string, k int) (ans string) {
	i, j, cnt := 0, 0, 0
	n := len(s)
	for j < n {
		cnt += int(s[j] - '0')
		for cnt > k || (i < j && s[i] == '0') {
			cnt -= int(s[i] - '0')
			i++
		}
		j++
		t := s[i:j]
		if cnt == k && (ans == "" || j-i < len(ans) || (j-i == len(ans) && t < ans)) {
			ans = t
		}
	}
	return
}