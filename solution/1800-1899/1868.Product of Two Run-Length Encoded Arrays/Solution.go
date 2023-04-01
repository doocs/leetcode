func findRLEArray(encoded1 [][]int, encoded2 [][]int) (ans [][]int) {
	j := 0
	for _, e := range encoded1 {
		vi, fi := e[0], e[1]
		for fi > 0 {
			f := min(fi, encoded2[j][1])
			v := vi * encoded2[j][0]
			if len(ans) > 0 && ans[len(ans)-1][0] == v {
				ans[len(ans)-1][1] += f
			} else {
				ans = append(ans, []int{v, f})
			}
			fi -= f
			encoded2[j][1] -= f
			if encoded2[j][1] == 0 {
				j++
			}
		}
	}
	return
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}