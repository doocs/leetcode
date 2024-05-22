class Solution {
    func isNumber(_ s: String) -> Bool {
        let chars = Array(s)
        var i = 0, j = chars.count - 1

        // Trim leading spaces
        while i <= j && chars[i] == " " {
            i += 1
        }
        // Trim trailing spaces
        while i <= j && chars[j] == " " {
            j -= 1
        }
        if i > j {
            return false
        }

        var digit = false
        var dot = false
        var e = false

        while i <= j {
            let char = chars[i]
            if char == "+" || char == "-" {
                if i > 0 && chars[i - 1] != " " && chars[i - 1] != "e" && chars[i - 1] != "E" {
                    return false
                }
            } else if char.isWholeNumber {
                digit = true
            } else if char == "." {
                if dot || e {
                    return false
                }
                dot = true
            } else if char == "e" || char == "E" {
                if !digit || e {
                    return false
                }
                e = true
                digit = false
            } else {
                return false
            }
            i += 1
        }
        return digit
    }
}