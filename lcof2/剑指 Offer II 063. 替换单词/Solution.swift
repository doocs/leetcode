class Solution {
    func replaceWords(_ dictionary: [String], _ sentence: String) -> String {
        let dictSet = Set(dictionary)
        var words = sentence.split(separator: " ").map { String($0) }

        for i in 0..<words.count {
            let word = words[i]
            for j in 1...word.count {
                let prefix = String(word.prefix(j))
                if dictSet.contains(prefix) {
                    words[i] = prefix
                    break
                }
            }
        }

        return words.joined(separator: " ")
    }
}