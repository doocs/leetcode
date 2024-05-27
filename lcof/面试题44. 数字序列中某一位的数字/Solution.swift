class Solution {
    func findNthDigit(_ n: Int) -> Int {
        var n = n
        var k = 1
        var count = 9
        
        while k * count < n {
            n -= k * count
            k += 1
            count *= 10
        }
        
        let num = Int(Double(10).power(Double(k - 1))) + (n - 1) / k
        let idx = (n - 1) % k
        let numString = String(num)
        let char = numString[numString.index(numString.startIndex, offsetBy: idx)]
        
        return char.wholeNumberValue!
    }
}

extension Double {
    func power(_ exponent: Double) -> Double {
        return pow(self, exponent)
    }
}