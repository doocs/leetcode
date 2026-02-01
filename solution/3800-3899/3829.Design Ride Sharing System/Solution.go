type RideSharingSystem struct {
	t       int
	riders  *redblacktree.Tree[int, int]
	drivers *redblacktree.Tree[int, int]
	d       map[int]int
}

func Constructor() RideSharingSystem {
	return RideSharingSystem{
		t:       0,
		riders:  redblacktree.New[int, int](),
		drivers: redblacktree.New[int, int](),
		d:       make(map[int]int),
	}
}

func (this *RideSharingSystem) AddRider(riderId int) {
	this.d[riderId] = this.t
	this.riders.Put(this.t, riderId)
	this.t++
}

func (this *RideSharingSystem) AddDriver(driverId int) {
	this.drivers.Put(this.t, driverId)
	this.t++
}

func (this *RideSharingSystem) MatchDriverWithRider() []int {
	if this.riders.Empty() || this.drivers.Empty() {
		return []int{-1, -1}
	}

	driverTime, driverId := this.drivers.Left().Key, this.drivers.Left().Value
	riderTime, riderId := this.riders.Left().Key, this.riders.Left().Value

	this.drivers.Remove(driverTime)
	this.riders.Remove(riderTime)

	return []int{driverId, riderId}
}

func (this *RideSharingSystem) CancelRider(riderId int) {
	time, exists := this.d[riderId]
	if !exists {
		return
	}
	this.riders.Remove(time)
}

/**
 * Your RideSharingSystem object will be instantiated and called as such:
 * obj := Constructor();
 * obj.AddRider(riderId);
 * obj.AddDriver(driverId);
 * param_3 := obj.MatchDriverWithRider();
 * obj.CancelRider(riderId);
 */
