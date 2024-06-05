class Solution {
    func evalRPN(_ tokens: [String]) -> Int {
        var stk = [Int]()

        for token in tokens {
            if let num = Int(token) {
                stk.append(num)
            } else {
                let y = stk.removeLast()
                let x = stk.removeLast()
                switch token {
                case "+":
                    stk.append(x + y)
                case "-":
                    stk.append(x - y)
                case "*":
                    stk.append(x * y)
                default:
                    stk.append(x / y)
                }
            }
        }

        return stk.removeLast()
    }
}
