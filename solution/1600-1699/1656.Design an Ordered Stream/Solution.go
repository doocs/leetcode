type OrderedStream struct {
	ptr  int
	data []string
}

func Constructor(n int) OrderedStream {
	return OrderedStream{
		ptr:  1,
		data: make([]string, n+1),
	}
}

func (this *OrderedStream) Insert(idKey int, value string) []string {
	this.data[idKey] = value
	var ans []string
	for this.ptr < len(this.data) && this.data[this.ptr] != "" {
		ans = append(ans, this.data[this.ptr])
		this.ptr++
	}
	return ans
}

/**
 * Your OrderedStream object will be instantiated and called as such:
 * obj := Constructor(n);
 * param_1 := obj.Insert(idKey,value);
 */
