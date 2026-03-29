import (
	rbt "github.com/emirpasic/gods/v2/trees/redblacktree"
	"cmp"
)

type pair struct{ p, id int }

type EventManager struct {
	sl *rbt.Tree[pair, struct{}]
	d  map[int]int
}

func Constructor(events [][]int) EventManager {
	sl := rbt.NewWith[pair, struct{}](func(a, b pair) int {
		return cmp.Or(a.p-b.p, a.id-b.id)
	})
	d := make(map[int]int)

	for _, e := range events {
		eventId, priority := e[0], e[1]
		sl.Put(pair{-priority, eventId}, struct{}{})
		d[eventId] = priority
	}

	return EventManager{sl: sl, d: d}
}

func (this *EventManager) UpdatePriority(eventId int, newPriority int) {
	old := this.d[eventId]
	this.sl.Remove(pair{-old, eventId})
	this.sl.Put(pair{-newPriority, eventId}, struct{}{})
	this.d[eventId] = newPriority
}

func (this *EventManager) PollHighest() int {
	if this.sl.Size() == 0 {
		return -1
	}
	it := this.sl.Iterator()
	it.First()

	top := it.Key()
	eventId := top.id

	this.sl.Remove(top)
	delete(this.d, eventId)

	return eventId
}

/**
 * Your EventManager object will be instantiated and called as such:
 * obj := Constructor(events);
 * obj.UpdatePriority(eventId,newPriority);
 * param_2 := obj.PollHighest();
 */
