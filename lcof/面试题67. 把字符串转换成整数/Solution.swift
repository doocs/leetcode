class Solution {
    func strToInt(_ str: String) -> Int {
        let n = str.count
        if n == 0 {
            return 0
        }

        var index = str.startIndex
        while index != str.endIndex && str[index] == " " {
            index = str.index(after: index)
        }

        if index == str.endIndex {
            return 0
        }

        var sign = 1
        if str[index] == "-" {
            sign = -1
            index = str.index(after: index)
        } else if str[index] == "+" {
            index = str.index(after: index)
        }

        var result = 0
        let flag = Int(Int32.max) / 10

        while index != str.endIndex {
            let char = str[index]
            if char < "0" || char > "9" {
                break
            }

            if result > flag || (result == flag && char > "7") {
                return sign == 1 ? Int(Int32.max) : Int(Int32.min)
            }

            result = result * 10 + Int(char.asciiValue! - Character("0").asciiValue!)
            index = str.index(after: index)
        }

        return sign * result
    }
}