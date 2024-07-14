class Solution {
    func exchangeBits(_ num: Int) -> Int {
        let oddShifted = (num & 0x55555555) << 1
        
        let evenShifted = (num & 0xaaaaaaaa) >> 1
        
        return oddShifted | evenShifted
    }
}