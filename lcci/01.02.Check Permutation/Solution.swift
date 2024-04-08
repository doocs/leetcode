func checkPermutation(_ s1: String, _ s2: String) -> Bool {
    if s1.count != s2.count {
        return false
    }
    
    var count = Array(repeating: 0, count: 26)
    
    for char in s1 {
        let index = Int(char.asciiValue! - Character("a").asciiValue!)
        count[index] += 1
    }
    
    for char in s2 {
        let index = Int(char.asciiValue! - Character("a").asciiValue!)
        count[index] -= 1
        if count[index] < 0 {
            return false
        }
    }
    
    return true
}
