var p []int

func validateBinaryTreeNodes(n int, leftChild []int, rightChild []int) bool {
	p = make([]int, n)
	for i := 0; i < n; i++ {
		p[i] = i
	}
	vis := make([]bool, n)
	for i, t := 0, n; i < t; i++ {
		l, r := leftChild[i], rightChild[i]
		if l != -1 {
			if vis[l] || find(i) == find(l) {
				return false
			}
			vis[l] = true
			p[find(i)] = find(l)
			n--
		}
		if r != -1 {
			if vis[r] || find(i) == find(r) {
				return false
			}
			vis[r] = true
			p[find(i)] = find(r)
			n--
		}
	}
	return n == 1
}

func find(x int) int {
	if p[x] != x {
		p[x] = find(p[x])
	}
	return p[x]
}