func longestBalanced(s string) int {
	x := calc1(s)
	y := max(calc2(s, 'a', 'b'), calc2(s, 'b', 'c'), calc2(s, 'a', 'c'))
	z := calc3(s)
	return max(x, max(y, z))
}

func calc1(s string) int {
	res := 0
	n := len(s)
	i := 0
	for i < n {
		j := i + 1
		for j < n && s[j] == s[i] {
			j++
		}
		if j-i > res {
			res = j - i
		}
		i = j
	}
	return res
}

func calc2(s string, a, b byte) int {
	res := 0
	n := len(s)
	i := 0
	for i < n {
		for i < n && s[i] != a && s[i] != b {
			i++
		}
		pos := map[int]int{0: i - 1}
		d := 0
		for i < n && (s[i] == a || s[i] == b) {
			if s[i] == a {
				d++
			} else {
				d--
			}
			if prev, ok := pos[d]; ok {
				if i-prev > res {
					res = i - prev
				}
			} else {
				pos[d] = i
			}
			i++
		}
	}
	return res
}

type key struct {
	x, y int
}

func calc3(s string) int {
	pos := make(map[key]int)
	pos[key{0, 0}] = -1

	cnt := [3]int{}
	res := 0

	for i := 0; i < len(s); i++ {
		c := s[i]
		cnt[c-'a']++
		x := cnt[0] - cnt[1]
		y := cnt[1] - cnt[2]
		k := key{x, y}

		if j, ok := pos[k]; ok {
			if i-j > res {
				res = i - j
			}
		} else {
			pos[k] = i
		}
	}
	return res
}
