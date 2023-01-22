func interchangeableRectangles(rectangles [][]int) int64 {
	ans := 0
	n := len(rectangles)
	cnt := map[int]int{}
	for _, e := range rectangles {
		w, h := e[0], e[1]
		g := gcd(w, h)
		w, h = w/g, h/g
		x := w*(n+1) + h
		ans += cnt[x]
		cnt[x]++
	}
	return int64(ans)
}

func gcd(a, b int) int {
	if b == 0 {
		return a
	}
	return gcd(b, a%b)
}