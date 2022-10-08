type FrontMiddleBackQueue struct{}

var a []int

func Constructor() (_ FrontMiddleBackQueue) {
	a = nil
	return
}

func (FrontMiddleBackQueue) PushFront(v int) {
	a = append([]int{v}, a...)
}

func (FrontMiddleBackQueue) PushMiddle(v int) {
	p := len(a) / 2
	a = append(a[:p], append([]int{v}, a[p:]...)...)
}

func (FrontMiddleBackQueue) PushBack(v int) {
	a = append(a, v)
}

func (FrontMiddleBackQueue) PopFront() (ans int) {
	if len(a) == 0 {
		return -1
	}
	ans = a[0]
	a = a[1:]
	return
}

func (FrontMiddleBackQueue) PopMiddle() (ans int) {
	if len(a) == 0 {
		return -1
	}
	p := (len(a) - 1) / 2
	ans = a[p]
	a = append(a[:p], a[p+1:]...)
	return
}

func (FrontMiddleBackQueue) PopBack() (ans int) {
	if len(a) == 0 {
		return -1
	}
	ans = a[len(a)-1]
	a = a[:len(a)-1]
	return
}