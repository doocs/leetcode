type Key struct {
	orderType string
	price     int
}

type OrderManagementSystem struct {
	orderTypeMap map[int]string
	priceMap     map[int]int
	t            map[Key][]int
}

func Constructor() OrderManagementSystem {
	return OrderManagementSystem{
		orderTypeMap: make(map[int]string),
		priceMap:     make(map[int]int),
		t:            make(map[Key][]int),
	}
}

func (this *OrderManagementSystem) AddOrder(orderId int, orderType string, price int) {
	this.orderTypeMap[orderId] = orderType
	this.priceMap[orderId] = price
	key := Key{orderType, price}
	this.t[key] = append(this.t[key], orderId)
}

func (this *OrderManagementSystem) ModifyOrder(orderId int, newPrice int) {
	orderType := this.orderTypeMap[orderId]
	oldPrice := this.priceMap[orderId]
	this.priceMap[orderId] = newPrice

	oldKey := Key{orderType, oldPrice}
	oldList := this.t[oldKey]
	for i, v := range oldList {
		if v == orderId {
			this.t[oldKey] = append(oldList[:i], oldList[i+1:]...)
			break
		}
	}

	newKey := Key{orderType, newPrice}
	this.t[newKey] = append(this.t[newKey], orderId)
}

func (this *OrderManagementSystem) CancelOrder(orderId int) {
	orderType := this.orderTypeMap[orderId]
	price := this.priceMap[orderId]

	delete(this.orderTypeMap, orderId)
	delete(this.priceMap, orderId)

	key := Key{orderType, price}
	list := this.t[key]
	for i, v := range list {
		if v == orderId {
			this.t[key] = append(list[:i], list[i+1:]...)
			break
		}
	}
}

func (this *OrderManagementSystem) GetOrdersAtPrice(orderType string, price int) []int {
	key := Key{orderType, price}
	return this.t[key]
}

/**
 * Your OrderManagementSystem object will be instantiated and called as such:
 * obj := Constructor();
 * obj.AddOrder(orderId,orderType,price);
 * obj.ModifyOrder(orderId,newPrice);
 * obj.CancelOrder(orderId);
 * param_4 := obj.GetOrdersAtPrice(orderType,price);
 */
