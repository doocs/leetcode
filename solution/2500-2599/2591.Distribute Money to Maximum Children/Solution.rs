impl Solution {
    pub fn dist_money(money: i32, children: i32) -> i32 {
        if money < children {
            return -1;
        }

        if money > children * 8 {
            return children - 1;
        }

        if money == children * 8 - 4 {
            return children - 2;
        }

        (money - children) / 7
    }
}
