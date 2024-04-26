impl Solution {
    pub fn exchange_bits(num: i32) -> i32 {
        let num = num as u32;
        (((num & 0x55555555) << 1) | ((num & 0xaaaaaaaa) >> 1)) as i32
    }
}
