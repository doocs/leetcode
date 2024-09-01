func factorial(n int) []int64 {
	fac := make([]int64, n+1)
	fac[0] = 1
	for i := 1; i <= n; i++ {
		fac[i] = fac[i-1] * int64(i)
	}
	return fac
}

func countGoodIntegers(n int, k int) (ans int64) {
	fac := factorial(n)
	vis := make(map[string]bool)
	base := int(math.Pow(10, float64((n-1)/2)))

	for i := base; i < base*10; i++ {
		s := strconv.Itoa(i)
		rev := reverseString(s)
		s += rev[n%2:]
		num, _ := strconv.ParseInt(s, 10, 64)
		if num%int64(k) != 0 {
			continue
		}
		bs := []byte(s)
		slices.Sort(bs)
		t := string(bs)

		if vis[t] {
			continue
		}
		vis[t] = true
		cnt := make([]int, 10)
		for _, c := range t {
			cnt[c-'0']++
		}
		res := (int64(n) - int64(cnt[0])) * fac[n-1]
		for _, x := range cnt {
			res /= fac[x]
		}
		ans += res
	}

	return
}

func reverseString(s string) string {
	t := []byte(s)
	for i, j := 0, len(t)-1; i < j; i, j = i+1, j-1 {
		t[i], t[j] = t[j], t[i]
	}
	return string(t)
}
