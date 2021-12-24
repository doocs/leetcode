# [16.02. Words Frequency](https://leetcode-cn.com/problems/words-frequency-lcci)

[中文文档](/lcci/16.02.Words%20Frequency/README.md)

## Description

<p>Design a method to find the frequency of occurrences of any given word in a book. What if we were running this algorithm multiple times?</p>

<p>You should implement following methods:</p>

<ul>
	<li><code>WordsFrequency(book)</code> constructor, parameter is a array of strings, representing the book.</li>
	<li><code>get(word)</code>&nbsp;get the frequency of <code>word</code> in the book.&nbsp;</li>
</ul>

<p><strong>Example: </strong></p>

<pre>

WordsFrequency wordsFrequency = new WordsFrequency({&quot;i&quot;, &quot;have&quot;, &quot;an&quot;, &quot;apple&quot;, &quot;he&quot;, &quot;have&quot;, &quot;a&quot;, &quot;pen&quot;});

wordsFrequency.get(&quot;you&quot;); //returns 0，&quot;you&quot; is not in the book

wordsFrequency.get(&quot;have&quot;); //returns 2，&quot;have&quot; occurs twice in the book

wordsFrequency.get(&quot;an&quot;); //returns 1

wordsFrequency.get(&quot;apple&quot;); //returns 1

wordsFrequency.get(&quot;pen&quot;); //returns 1

</pre>

<p><strong>Note: </strong></p>

<ul>
    <li><code>There are only lowercase letters in book[i].</code></li>
    <li><code>1 &lt;= book.length &lt;= 100000</code></li>
    <li><code>1 &lt;= book[i].length &lt;= 10</code></li>
    <li><code>get</code>&nbsp;function will not be called more than&nbsp;100000 times.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class WordsFrequency:

    def __init__(self, book: List[str]):
        self.counter = Counter(book)

    def get(self, word: str) -> int:
        return self.counter[word]

# Your WordsFrequency object will be instantiated and called as such:
# obj = WordsFrequency(book)
# param_1 = obj.get(word)
```

### **Java**

```java
class WordsFrequency {

    private Map<String, Integer> counter = new HashMap<>();

    public WordsFrequency(String[] book) {
        for (String word : book) {
            counter.put(word, counter.getOrDefault(word, 0) + 1);
        }
    }

    public int get(String word) {
        return counter.containsKey(word) ? counter.get(word) : 0;
    }
}

/**
 * Your WordsFrequency object will be instantiated and called as such:
 * WordsFrequency obj = new WordsFrequency(book);
 * int param_1 = obj.get(word);
 */
```

### **JavaScript**

```js
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
```

### **...**

```

```

<!-- tabs:end -->
