func fullJustify(words []string, maxWidth int) (ans []string) {
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
			continue
		}
		spaceWidth := maxWidth - (cnt - len(t) + 1)
		w := spaceWidth / (len(t) - 1)
		m := spaceWidth % (len(t) - 1)
		row := strings.Builder{}
		for j, s := range t[:len(t)-1] {
			row.WriteString(s)
			row.WriteString(strings.Repeat(" ", w))
			if j < m {
				row.WriteString(" ")
			}
		}
		row.WriteString(t[len(t)-1])
		ans = append(ans, row.String())
	}
	return
}