type SnapshotArray struct {
	arr [][][2]int
	i   int
}

func Constructor(length int) SnapshotArray {
	return SnapshotArray{make([][][2]int, length), 0}
}

func (this *SnapshotArray) Set(index int, val int) {
	this.arr[index] = append(this.arr[index], [2]int{this.i, val})
}

func (this *SnapshotArray) Snap() int {
	this.i++
	return this.i - 1
}

func (this *SnapshotArray) Get(index int, snap_id int) int {
	i := sort.Search(len(this.arr[index]), func(i int) bool { return this.arr[index][i][0] > snap_id }) - 1
	if i < 0 {
		return 0
	}
	return this.arr[index][i][1]
}

/**
 * Your SnapshotArray object will be instantiated and called as such:
 * obj := Constructor(length);
 * obj.Set(index,val);
 * param_2 := obj.Snap();
 * param_3 := obj.Get(index,snap_id);
 */