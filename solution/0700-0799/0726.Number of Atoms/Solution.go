func countOfAtoms(formula string) string {
	cnt := map[string]int{}
	var stack []int
	multiplier, freq := 1, 0
	for i := len(formula) - 1; i >= 0; i-- {
		c := formula[i]
		if c >= 'a' && c <= 'z' {
			end := i
			i--
			for i >= 0 && formula[i] >= 'a' && formula[i] <= 'z' {
				i--
			}
			cnt[formula[i:end+1]] += max(freq, 1) * multiplier
			freq = 0
		} else if c >= 'A' && c <= 'Z' {
			cnt[formula[i:i+1]] += max(freq, 1) * multiplier
			freq = 0
		} else if c >= '0' && c <= '9' {
			freq = int(c - '0')
			p := 10
			for i-1 >= 0 && formula[i-1] >= '0' && formula[i-1] <= '9' {
				i--
				freq += p * int(formula[i]-'0')
				p *= 10
			}
		} else if c == ')' {
			stack = append(stack, multiplier)
			multiplier *= max(freq, 1)
			freq = 0
		} else {
			multiplier = stack[len(stack)-1]
			stack = stack[:len(stack)-1]
		}
	}
	keys := make([]string, 0, len(cnt))
	for k := range cnt {
		keys = append(keys, k)
	}
	sort.Strings(keys)
	ans := []byte{}
	for _, key := range keys {
		ans = append(ans, key...)
		if cnt[key] > 1 {
			ans = append(ans, strconv.Itoa(cnt[key])...)
		}
	}
	return string(ans)
}
