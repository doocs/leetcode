var f [30][30][31]int

func earliestAndLatest(n int, firstPlayer int, secondPlayer int) []int {
	return dfs(firstPlayer-1, secondPlayer-1, n)
}

func dfs(l, r, n int) []int {
	if f[l][r][n] != 0 {
		return decode(f[l][r][n])
	}
	if l+r == n-1 {
		f[l][r][n] = encode(1, 1)
		return []int{1, 1}
	}

	min, max := 1<<30, -1<<31
	m := n >> 1

	for i := 0; i < (1 << m); i++ {
		win := make([]bool, n)
		for j := 0; j < m; j++ {
			if (i>>j)&1 == 1 {
				win[j] = true
			} else {
				win[n-1-j] = true
			}
		}
		if n&1 == 1 {
			win[m] = true
		}
		win[n-1-l] = false
		win[n-1-r] = false
		win[l] = true
		win[r] = true

		a, b, c := 0, 0, 0
		for j := 0; j < n; j++ {
			if j == l {
				a = c
			}
			if j == r {
				b = c
			}
			if win[j] {
				c++
			}
		}

		t := dfs(a, b, c)
		if t[0]+1 < min {
			min = t[0] + 1
		}
		if t[1]+1 > max {
			max = t[1] + 1
		}
	}

	f[l][r][n] = encode(min, max)
	return []int{min, max}
}

func encode(x, y int) int {
	return (x << 8) | y
}

func decode(val int) []int {
	return []int{val >> 8, val & 255}
}
