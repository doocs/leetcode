const mod int = 1e9 + 7
const n int = 1e5 + 10

var f = [n]int{1, 1, 2, 4}
var g = f

func init() {
	for i := 4; i < n; i++ {
		f[i] = (f[i-1] + f[i-2] + f[i-3]) % mod
		g[i] = (g[i-1] + g[i-2] + g[i-3] + g[i-4]) % mod
	}
}

func countTexts(pressedKeys string) int {
	ans := 1
	for i, j, n := 0, 0, len(pressedKeys); i < n; i++ {
		c := pressedKeys[i]
		j = i
		for j+1 < n && pressedKeys[j+1] == c {
			j++
		}
		cnt := j - i + 1
		if c == '7' || c == '9' {
			ans = ans * g[cnt] % mod
		} else {
			ans = ans * f[cnt] % mod
		}
		i = j
	}
	return ans
}