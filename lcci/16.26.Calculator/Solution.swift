class Solution {
    func calculate(_ s: String) -> Int {
        let n = s.count
        var x = 0
        var sign: Character = "+"
        var stk = [Int]()
        let sArray = Array(s)
        
        for i in 0..<n {
            let c = sArray[i]
            if c.isNumber {
                x = x * 10 + Int(String(c))!
            }
            if i == n - 1 || (!c.isNumber && c != " ") {
                switch sign {
                case "+":
                    stk.append(x)
                case "-":
                    stk.append(-x)
                case "*":
                    if let last = stk.popLast() {
                        stk.append(last * x)
                    }
                case "/":
                    if let last = stk.popLast() {
                        stk.append(last / x)
                    }
                default:
                    break
                }
                x = 0
                sign = c
            }
        }
        
        return stk.reduce(0, +)
    }
}