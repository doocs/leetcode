func totalStrength(strength []int) int {
	n := len(strength)
	left := make([]int, n)
	right := make([]int, n)
	for i := range left {
		left[i] = -1
		right[i] = n
	}
	stk := []int{}
	for i, v := range strength {
		for len(stk) > 0 && strength[stk[len(stk)-1]] >= v {
			stk = stk[:len(stk)-1]
		}
		if len(stk) > 0 {
			left[i] = stk[len(stk)-1]
		}
		stk = append(stk, i)
	}
	stk = []int{}
	for i := n - 1; i >= 0; i-- {
		for len(stk) > 0 && strength[stk[len(stk)-1]] > strength[i] {
			stk = stk[:len(stk)-1]
		}
		if len(stk) > 0 {
			right[i] = stk[len(stk)-1]
		}
		stk = append(stk, i)
	}
	mod := int(1e9) + 7
	s := make([]int, n+1)
	for i, v := range strength {
		s[i+1] = (s[i] + v) % mod
	}
	ss := make([]int, n+2)
	for i, v := range s {
		ss[i+1] = (ss[i] + v) % mod
	}
	ans := 0
	for i, v := range strength {
		l, r := left[i]+1, right[i]-1
		a := (ss[r+2] - ss[i+1]) * (i - l + 1)
		b := (ss[i+1] - ss[l]) * (r - i + 1)
		ans = (ans + v*((a-b)%mod)) % mod
	}
	return (ans + mod) % mod
}