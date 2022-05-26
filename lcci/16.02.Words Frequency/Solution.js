/**
 * @param {string[]} book
 */
var WordsFrequency = function (book) {
    this.counter = {};
    for (const word of book) {
        this.counter[word] = (this.counter[word] || 0) + 1;
    }
};

/**
 * @param {string} word
 * @return {number}
 */
WordsFrequency.prototype.get = function (word) {
    return this.counter[word] || 0;
};

/**
 * Your WordsFrequency object will be instantiated and called as such:
 * var obj = new WordsFrequency(book)
 * var param_1 = obj.get(word)
 */
