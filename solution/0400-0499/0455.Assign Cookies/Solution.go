/* 455 分发饼干
* 解法
* 每个小朋友只能最多拥有一块饼干，先满足胃口最大的小孩，剩下的就更容易分配
* 由于题目没有预先给 g、s 进行排序，需要自己实现快速排序，这里利用goroutine并行运算加速
* 
* 21/21 cases passed (40 ms)
* memory usage (6 MB)
*/ 

func findContentChildren(g []int, s []int) int {
	done1 := make(chan struct{})
	done2 := make(chan struct{})
	go quickSort_go(g, 0, len(g)-1, done1, 4)
	go quickSort_go(s, 0, len(s)-1, done2, 4)
	<-done1
	<-done2
	i, j, ret := len(g)-1, len(s)-1, 0
	for i >= 0 && j >= 0 {
		if g[i] > s[j] {
			i--
		} else {
			ret++
			i--
			j--
		}
	}
	return ret
}

* 快速排序
func quickSort(a []int, lo, hi int) {
	if lo >= hi {
		return
	}
	p := partition(a, lo, hi)
	quickSort(a, lo, p-1)
	quickSort(a, p+1, hi)
}
func partition(a []int, lo, hi int) int {
	pivot := a[hi]
	i := lo - 1
	for j := lo; j < hi; j++ {
		if a[j] < pivot {
			i++
			a[j], a[i] = a[i], a[j]
		}
	}
	a[i+1], a[hi] = a[hi], a[i+1]
	return i + 1
}
func quickSort_go(a []int, lo, hi int, done chan struct{}, depth int) {
	if lo >= hi {
		done <- struct{}{}
		return
	}
	depth--
	p := partition(a, lo, hi)
	if depth > 0 {
		childDone := make(chan struct{}, 2)
		go quickSort_go(a, lo, p-1, childDone, depth)
		go quickSort_go(a, p+1, hi, childDone, depth)
		<-childDone
		<-childDone
	} else {
		quickSort(a, lo, p-1)
		quickSort(a, p+1, hi)
	}
	done <- struct{}{}
}