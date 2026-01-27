use std::collections::HashMap;

struct OrderManagementSystem {
    orders: HashMap<i32, (String, i32)>,
    t: HashMap<(String, i32), Vec<i32>>,
}

impl OrderManagementSystem {

    fn new() -> Self {
        Self {
            orders: HashMap::new(),
            t: HashMap::new(),
        }
    }

    fn add_order(&mut self, order_id: i32, order_type: String, price: i32) {
        self.orders.insert(order_id, (order_type.clone(), price));
        self.t
            .entry((order_type, price))
            .or_insert_with(Vec::new)
            .push(order_id);
    }

    fn modify_order(&mut self, order_id: i32, new_price: i32) {
        if let Some((order_type, old_price)) = self.orders.get(&order_id).cloned() {
            self.orders.insert(order_id, (order_type.clone(), new_price));

            if let Some(v) = self.t.get_mut(&(order_type.clone(), old_price)) {
                if let Some(pos) = v.iter().position(|&x| x == order_id) {
                    v.remove(pos);
                }
            }

            self.t
                .entry((order_type, new_price))
                .or_insert_with(Vec::new)
                .push(order_id);
        }
    }

    fn cancel_order(&mut self, order_id: i32) {
        if let Some((order_type, price)) = self.orders.remove(&order_id) {
            if let Some(v) = self.t.get_mut(&(order_type, price)) {
                if let Some(pos) = v.iter().position(|&x| x == order_id) {
                    v.remove(pos);
                }
            }
        }
    }

    fn get_orders_at_price(&self, order_type: String, price: i32) -> Vec<i32> {
        self.t
            .get(&(order_type, price))
            .cloned()
            .unwrap_or_default()
    }
}

/**
 * Your OrderManagementSystem object will be instantiated and called as such:
 * let obj = OrderManagementSystem::new();
 * obj.add_order(orderId, orderType, price);
 * obj.modify_order(orderId, newPrice);
 * obj.cancel_order(orderId);
 * let ret_4: Vec<i32> = obj.get_orders_at_price(orderType, price);
 */
