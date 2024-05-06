class Solution {
    init() {}

    func minus(_ a: Int, _ b: Int) -> Int {
        var b = b
        let sign = b > 0 ? -1 : 1
        var result = a
        while b != 0 {
            result += sign
            b += sign
        }
        return result
    }

    func multiply(_ a: Int, _ b: Int) -> Int {
        if a == 0 || b == 0 {
            return 0
        }
        
        let absA = a > 0 ? a : minus(0, a)
        let absB = b > 0 ? b : minus(0, b)
        var result = 0
        
        for _ in 0..<absB {
            result += absA
        }
        
        if (a > 0) != (b > 0) {
            result = minus(0, result)
        }
        
        return result
    }

    func divide(_ a: Int, _ b: Int) -> Int {
        let absA = a > 0 ? a : minus(0, a)
        let absB = b > 0 ? b : minus(0, b)
        var result = 0
        var sum = absB
        
        while sum <= absA {
            result += 1
            sum += absB
        }
        
        if (a > 0) != (b > 0) {
            result = minus(0, result)
        }
        
        return result
    }
}