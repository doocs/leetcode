class Solution {
    func divide(_ a: Int, _ b: Int) -> Int {
        if b == 1 {
            return a
        }
        if a == Int32.min && b == -1 {
            return Int(Int32.max)
        }
        let sign = (a > 0 && b > 0) || (a < 0 && b < 0)
        var a = a > 0 ? -a : a
        let b = b > 0 ? -b : b
        var ans = 0
        while a <= b {
            var x = b
            var cnt = 1
            while x >= (Int32.min >> 1) && a <= (x << 1) {
                x <<= 1
                cnt <<= 1
            }
            ans += cnt
            a -= x
        }
        return sign ? ans : -ans
    }
}