func smallestPalindrome(s string, k int) string {
	const limit = 1_000_001
	freq := [26]int{}
	for i := 0; i < len(s); i++ {
		freq[s[i]-'a']++
	}
	odd, mid := 0, 26
	for i, v := range freq {
		if v&1 == 1 {
			odd++
			mid = i
		}
	}
	if odd > 1 {
		return ""
	}
	half := [26]int{}
	halfLen := 0
	for i, v := range freq {
		half[i] = v / 2
		halfLen += half[i]
	}
	countPermutations := func(counts [26]int) int {
		remaining := 0
		for _, c := range counts {
			remaining += c
		}
		perms := 1
		for _, count := range counts {
			if count == 0 {
				continue
			}
			selected := count
			if remaining-count < selected {
				selected = remaining - count
			}
			combos := 1
			for step := 1; step <= selected; step++ {
				combos = combos * (remaining - step + 1) / step
				if combos >= limit {
					combos = limit
					break
				}
			}
			perms *= combos
			if perms >= limit {
				return limit
			}
			remaining -= count
		}
		return perms
	}
	if k > countPermutations(half) {
		return ""
	}
	n := len(s)
	pal := make([]byte, n)
	rank, pos := k, 0
	for t := 0; t < halfLen; t++ {
		for i := 0; i < 26; i++ {
			if half[i] == 0 {
				continue
			}
			half[i]--
			suffix := countPermutations(half)
			if suffix >= rank {
				pal[pos] = byte('a' + i)
				pos++
				break
			}
			rank -= suffix
			half[i]++
		}
	}
	if mid < 26 {
		pal[halfLen] = byte('a' + mid)
	}
	for i := 0; i < halfLen; i++ {
		pal[n-1-i] = pal[i]
	}
	return string(pal)
}
