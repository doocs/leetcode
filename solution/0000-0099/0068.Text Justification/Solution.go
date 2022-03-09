func fullJustify(words []string, maxWidth int) []string {
	partition := func(n, cnt int) []string {
		var res []string
		base, mod := n/cnt, n%cnt
		for i, j := 0, 0; i < cnt; i, j = i+1, j+1 {
			t := strings.Repeat(" ", base)
			if j < mod {
				t += " "
			}
			res = append(res, t)
		}
		return res
	}

	var ans []string
	for i, n := 0, len(words); i < n; {
		t := []string{words[i]}
		cnt := len(words[i])
		i++
		for i < n && cnt+1+len(words[i]) <= maxWidth {
			cnt += 1 + len(words[i])
			t = append(t, words[i])
			i++
		}
		if i == n || len(t) == 1 {
			left := strings.Join(t, " ")
			right := strings.Repeat(" ", maxWidth-len(left))
			ans = append(ans, left+right)
			if i == n {
				break
			}
			continue
		}
		wordsWidth := cnt - len(t) + 1
		spaceWidth := maxWidth - wordsWidth
		spaces := partition(spaceWidth, len(t)-1)
		sb := t[0]
		for j := 0; j < len(t)-1; j++ {
			sb += spaces[j] + t[j+1]
		}
		ans = append(ans, sb)
	}
	return ans
}