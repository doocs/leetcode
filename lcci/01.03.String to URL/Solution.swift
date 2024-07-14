class Solution {
    func replaceSpaces(_ S: String, _ length: Int) -> String {
        let substring = S.prefix(length)
        var result = ""
        
        for character in substring {
            if character == " " {
                result += "%20"
            } else {
                result.append(character)
            }
        }
        
        return result
    }
}