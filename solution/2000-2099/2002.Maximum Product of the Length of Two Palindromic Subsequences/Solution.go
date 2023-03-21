func maxProduct(s string) (ans int) {
	n := len(s)
	p := make([]bool, 1<<n)
	for i := range p {
		p[i] = true
	}
	for k := 1; k < 1<<n; k++ {
		for i, j := 0, n-1; i < j; i, j = i+1, j-1 {
			for i < j && (k>>i&1) == 0 {
				i++
			}
			for i < j && (k>>j&1) == 0 {
				j--
			}
			if i < j && s[i] != s[j] {
				p[k] = false
				break
			}
		}
	}
	for i := 1; i < 1<<n; i++ {
		if p[i] {
			a := bits.OnesCount(uint(i))
			mx := (1<<n - 1) ^ i
			for j := mx; j > 0; j = (j - 1) & mx {
				if p[j] {
					b := bits.OnesCount(uint(j))
					ans = max(ans, a*b)
				}
			}
		}
	}
	return

}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}