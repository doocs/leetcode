use std::cell::Cell;
use std::collections::HashMap;

struct Cashier {
    i: Cell<i32>,
    n: i32,
    discount: i32,
    d: HashMap<i32, i32>,
}

impl Cashier {
    fn new(n: i32, discount: i32, products: Vec<i32>, prices: Vec<i32>) -> Self {
        let mut d = HashMap::new();
        for i in 0..products.len() {
            d.insert(products[i], prices[i]);
        }
        Cashier {
            i: Cell::new(0),
            n,
            discount,
            d,
        }
    }

    fn get_bill(&self, product: Vec<i32>, amount: Vec<i32>) -> f64 {
        let mut x = 0i64;
        let mut i = self.i.get();
        i = (i + 1) % self.n;
        self.i.set(i);

        for j in 0..product.len() {
            x += (self.d[&product[j]] as i64) * (amount[j] as i64);
        }

        if i == 0 {
            return x as f64 - (self.discount as f64) * (x as f64) / 100.0;
        }
        x as f64
    }
}

// Your Cashier object will be instantiated and called as such:
// let obj = Cashier::new(n, discount, products, prices);
// let ret_1: f64 = obj.get_bill(product, amount);
