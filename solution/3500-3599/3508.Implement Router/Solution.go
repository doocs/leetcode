type Router struct {
	lim int
	vis map[int64]struct{}
	q   [][3]int
	idx map[int]int
	d   map[int][]int
}

func Constructor(memoryLimit int) Router {
	return Router{
		lim: memoryLimit,
		vis: make(map[int64]struct{}),
		q:   make([][3]int, 0),
		idx: make(map[int]int),
		d:   make(map[int][]int),
	}
}

func (this *Router) f(a, b, c int) int64 {
	return int64(a)<<46 | int64(b)<<29 | int64(c)
}

func (this *Router) AddPacket(source int, destination int, timestamp int) bool {
	x := this.f(source, destination, timestamp)
	if _, ok := this.vis[x]; ok {
		return false
	}
	this.vis[x] = struct{}{}
	if len(this.q) >= this.lim {
		this.ForwardPacket()
	}
	this.q = append(this.q, [3]int{source, destination, timestamp})
	this.d[destination] = append(this.d[destination], timestamp)
	return true
}

func (this *Router) ForwardPacket() []int {
	if len(this.q) == 0 {
		return []int{}
	}
	packet := this.q[0]
	this.q = this.q[1:]
	s, d, t := packet[0], packet[1], packet[2]
	delete(this.vis, this.f(s, d, t))
	this.idx[d]++
	return []int{s, d, t}
}

func (this *Router) GetCount(destination int, startTime int, endTime int) int {
	ls := this.d[destination]
	k := this.idx[destination]
	i := sort.Search(len(ls)-k, func(i int) bool { return ls[i+k] >= startTime }) + k
	j := sort.Search(len(ls)-k, func(i int) bool { return ls[i+k] >= endTime+1 }) + k
	return j - i
}

/**
 * Your Router object will be instantiated and called as such:
 * obj := Constructor(memoryLimit)
 * param_1 := obj.AddPacket(source,destination,timestamp)
 * param_2 := obj.ForwardPacket()
 * param_3 := obj.GetCount(destination,startTime,endTime)
 */
