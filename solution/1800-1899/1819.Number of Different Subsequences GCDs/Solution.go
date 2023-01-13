func countDifferentSubsequenceGCDs(nums []int) (ans int) {
	mx := 0
	for _, x := range nums {
		mx = max(mx, x)
	}
	vis := make([]bool, mx+1)
	for _, x := range nums {
		vis[x] = true
	}
	for x := 1; x <= mx; x++ {
		g := 0
		for y := x; y <= mx; y += x {
			if vis[y] {
				g = gcd(g, y)
				if g == x {
					ans++
					break
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

func gcd(a, b int) int {
	if b == 0 {
		return a
	}
	return gcd(b, a%b)
}