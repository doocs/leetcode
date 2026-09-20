func lexPalindromicPermutation(s string, target string) string {
	build := func(left string, middle byte, n int) string {
		b := []byte(left)
		for i, j := 0, len(b)-1; i < j; i, j = i+1, j-1 {
			b[i], b[j] = b[j], b[i]
		}
		right := string(b)
		if n%2 == 1 {
			return left + string(middle) + right
		}
		return left + right
	}

	n := len(s)
	freq := make([]int, 26)
	for i := 0; i < n; i++ {
		freq[s[i]-'a']++
	}
	odd := 0
	var middle byte
	for i, v := range freq {
		if v%2 == 1 {
			odd++
			middle = byte('a' + i)
		}
	}
	if odd > 1 {
		return ""
	}
	half := make([]int, 26)
	for i, v := range freq {
		half[i] = v / 2
	}
	halfLen := n / 2
	targetHalf := target[:halfLen]
	remaining := append([]int(nil), half...)
	matched := 0
	for i := 0; i < halfLen; i++ {
		x := int(targetHalf[i] - 'a')
		if remaining[x] == 0 {
			break
		}
		remaining[x]--
		matched++
	}
	if matched == halfLen {
		cand := build(targetHalf, middle, n)
		if cand > target {
			return cand
		}
	}
	last := matched
	if matched == halfLen {
		last = halfLen - 1
	}
	for pos := last; pos >= 0; pos-- {
		rem := append([]int(nil), half...)
		valid := true
		for i := 0; i < pos; i++ {
			x := int(targetHalf[i] - 'a')
			if rem[x] == 0 {
				valid = false
				break
			}
			rem[x]--
		}
		if !valid {
			continue
		}
		targetChar := int(targetHalf[pos] - 'a')
		for c := targetChar + 1; c < 26; c++ {
			if rem[c] == 0 {
				continue
			}
			left := targetHalf[:pos] + string(byte('a'+c))
			rem[c]--
			for x := 0; x < 26; x++ {
				for rem[x] > 0 {
					left += string(byte('a' + x))
					rem[x]--
				}
			}
			cand := build(left, middle, n)
			if cand > target {
				return cand
			}
			rem = append([]int(nil), half...)
			for i := 0; i < pos; i++ {
				rem[targetHalf[i]-'a']--
			}
		}
	}
	return ""
}
