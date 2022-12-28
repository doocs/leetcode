func maxRepOpt1(text string) (ans int) {
	cnt := [26]int{}
	for _, c := range text {
		cnt[c-'a']++
	}
	n := len(text)
	for i, j := 0, 0; i < n; i = j {
		j = i
		for j < n && text[j] == text[i] {
			j++
		}
		l := j - i
		k := j + 1
		for k < n && text[k] == text[i] {
			k++
		}
		r := k - j - 1
		ans = max(ans, min(l+r+1, cnt[text[i]-'a']))
	}
	return
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}