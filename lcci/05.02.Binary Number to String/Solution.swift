class Solution {
    func printBin(_ num: Double) -> String {
        var num = num
        var ans = "0."
        
        while ans.count < 32 && num != 0 {
            num *= 2
            let x = Int(num)
            ans.append("\(x)")
            num -= Double(x)
        }
        
        return num != 0 ? "ERROR" : ans
    }
}