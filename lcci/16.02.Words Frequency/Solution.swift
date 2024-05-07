class WordsFrequency {
    private var cnt: [String: Int] = [:]

    init(_ book: [String]) {
        for word in book {
            cnt[word, default: 0] += 1
        }
    }

    func get(_ word: String) -> Int {
        return cnt[word, default: 0]
    }
}
