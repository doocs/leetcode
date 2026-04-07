func mirrorFrequency(s string) int {
	freq := make(map[rune]int)
	for _, c := range s {
		freq[c]++
	}

	ans := 0
	vis := make(map[rune]bool)

	for c, v := range freq {
		var m rune
		if c >= 'a' && c <= 'z' {
			m = 'a' + 25 - (c - 'a')
		} else {
			m = '0' + (9 - (c - '0'))
		}

		if vis[m] {
			continue
		}
		vis[c] = true

		mv := freq[m]
		ans += abs(v - mv)
	}

	return ans
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
