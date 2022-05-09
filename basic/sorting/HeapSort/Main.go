package main

import "fmt"

const N = 100010

var (
	size int
	h    []int
)

func up(u int) {
	for u/2 != 0 && h[u/2] > h[u] { // 父节点比当前结点小
		h[u/2], h[u] = h[u], h[u/2] // 交换
		u /= 2
	}
}
func down(u int) {
	t := u                            // t 最小值
	if u*2 <= size && h[2*u] < h[t] { // 左孩子存在且小于t
		t = u * 2
	}
	if u*2+1 <= size && h[2*u+1] < h[t] { // 右孩子存在且小于t
		t = 2*u + 1
	}
	if u != t {
		h[u], h[t] = h[t], h[u]
		down(t) // 递归处理
	}
}
func main() {
	var n, m int
	h = make([]int, N)
	fmt.Scanf("%d%d", &n, &m)
	// 创建一维数组1
	for i := 1; i <= n; i++ {
		fmt.Scanf("%d", &h[i])
	}
	size = n
	// 一维数组变为小根堆
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
