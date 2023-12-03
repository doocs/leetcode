impl Solution {
    pub fn even_odd_bit(n: i32) -> Vec<i32> {
        let mask: i32 = 0x5555;
        let even = (n & mask).count_ones() as i32;
        let odd = (n & !mask).count_ones() as i32;
        vec![even, odd]
    }
}
