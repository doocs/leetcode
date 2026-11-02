package main

import "fmt"

const N = 100010

var (
	size int
	h    []int
)

func up(u int) {
	for u/2 != 0 && h[u/2] > h[u] {
		h[u/2], h[u] = h[u], h[u/2]
		u /= 2
	}
}
func down(u int) {
	t := u
	if u*2 <= size && h[2*u] < h[t] {
		t = u * 2
	}
	if u*2+1 <= size && h[2*u+1] < h[t] {
		t = 2*u + 1
	}
	if u != t {
		h[u], h[t] = h[t], h[u]
		down(t)
	}
}
func main() {
	var n, m int
	h = make([]int, N)
	fmt.Scanf("%d%d", &n, &m)
	for i := 1; i <= n; i++ {
		fmt.Scanf("%d", &h[i])
	}
	size = n
	for i := n / 2; i != 0; i-- {
		down(i)
	}

	for ; m != 0; m-- {
		fmt.Printf("%d ", h[1])
		h[1] = h[size]
		size--
		down(1)
	}
}
