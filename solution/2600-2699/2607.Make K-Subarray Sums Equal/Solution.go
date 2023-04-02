func makeSubKSumEqual(arr []int, k int) (ans int64) {
	n := len(arr)
	g := gcd(n, k)
	for i := 0; i < g; i++ {
		t := []int{}
		for j := i; j < n; j += g {
			t = append(t, arr[j])
		}
		sort.Ints(t)
		mid := t[len(t)/2]
		for _, x := range t {
			ans += int64(abs(x - mid))
		}
	}
	return
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}

func gcd(a, b int) int {
	if b == 0 {
		return a
	}
	return gcd(b, a%b)
}