func canMakePalindromeQueries(s string, queries [][]int) (ans []bool) {
	n := len(s)
	m := n / 2
	t := reverse(s[m:])
	s = s[:m]

	pre1 := make([][]int, m+1)
	pre2 := make([][]int, m+1)
	diff := make([]int, m+1)
	pre1[0] = make([]int, 26)
	pre2[0] = make([]int, 26)

	for i := 1; i <= m; i++ {
		pre1[i] = slices.Clone(pre1[i-1])
		pre2[i] = slices.Clone(pre2[i-1])
		pre1[i][int(s[i-1]-'a')]++
		pre2[i][int(t[i-1]-'a')]++
		diff[i] = diff[i-1]
		if s[i-1] != t[i-1] {
			diff[i]++
		}
	}
	for _, q := range queries {
		a, b := q[0], q[1]
		c, d := n-1-q[3], n-1-q[2]
		if a <= c {
			ans = append(ans, check(pre1, pre2, diff, a, b, c, d))
		} else {
			ans = append(ans, check(pre2, pre1, diff, c, d, a, b))
		}
	}
	return
}

func check(pre1, pre2 [][]int, diff []int, a, b, c, d int) bool {
	if diff[a] > 0 || diff[len(diff)-1]-diff[max(b, d)+1] > 0 {
		return false
	}

	if d <= b {
		return slices.Equal(count(pre1, a, b), count(pre2, a, b))
	}

	if b < c {
		return diff[c]-diff[b+1] == 0 && slices.Equal(count(pre1, a, b), count(pre2, a, b)) && slices.Equal(count(pre1, c, d), count(pre2, c, d))
	}

	cnt1 := sub(count(pre1, a, b), count(pre2, a, c-1))
	cnt2 := sub(count(pre2, c, d), count(pre1, b+1, d))

	return !slices.Equal(cnt1, []int{}) && !slices.Equal(cnt2, []int{}) && slices.Equal(cnt1, cnt2)
}

func count(pre [][]int, i, j int) []int {
	cnt := make([]int, 26)
	for k := 0; k < 26; k++ {
		cnt[k] = pre[j+1][k] - pre[i][k]
	}
	return cnt
}

func sub(cnt1, cnt2 []int) []int {
	cnt := make([]int, 26)
	for i := 0; i < 26; i++ {
		cnt[i] = cnt1[i] - cnt2[i]
		if cnt[i] < 0 {
			return []int{}
		}
	}
	return cnt
}

func reverse(s string) string {
	runes := []rune(s)
	for i, j := 0, len(runes)-1; i < j; i, j = i+1, j-1 {
		runes[i], runes[j] = runes[j], runes[i]
	}
	return string(runes)
}