class Solution {
    func evalRPN(_ tokens: [String]) -> Int {
        var stack = [Int]()
        
        for token in tokens {
            if let num = Int(token) {
                stack.append(num)
            } else {
                let y = stack.removeLast()
                let x = stack.removeLast()
                switch token {
                case "+":
                    stack.append(x + y)
                case "-":
                    stack.append(x - y)
                case "*":
                    stack.append(x * y)
                case "/":
                    stack.append(x / y)
                default:
                    fatalError("Invalid operator")
                }
            }
        }
        
        return stack.removeLast()
    }
}