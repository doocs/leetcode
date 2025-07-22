class Solution {
    func game(_ guess: [Int], _ answer: [Int]) -> Int {
        var correctGuesses = 0
        for i in 0..<3 {
            if guess[i] == answer[i] {
                correctGuesses += 1
            }
        }
        return correctGuesses
    }
}
