# [16.02. Words Frequency](https://leetcode.cn/problems/words-frequency-lcci)

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
        self.cnt = Counter(book)

    def get(self, word: str) -> int:
        return self.cnt[word]


# Your WordsFrequency object will be instantiated and called as such:
# obj = WordsFrequency(book)
# param_1 = obj.get(word)
```

### **Java**

```java
class WordsFrequency {
    private Map<String, Integer> cnt = new HashMap<>();

    public WordsFrequency(String[] book) {
        for (String x : book) {
            cnt.merge(x, 1, Integer::sum);
        }
    }

    public int get(String word) {
        return cnt.getOrDefault(word, 0);
    }
}

/**
 * Your WordsFrequency object will be instantiated and called as such:
 * WordsFrequency obj = new WordsFrequency(book);
 * int param_1 = obj.get(word);
 */
```

### **C++**

```cpp
class WordsFrequency {
public:
    WordsFrequency(vector<string>& book) {
        for (auto& x : book) {
            ++cnt[x];
        }
    }

    int get(string word) {
        return cnt[word];
    }

private:
    unordered_map<string, int> cnt;
};

/**
 * Your WordsFrequency object will be instantiated and called as such:
 * WordsFrequency* obj = new WordsFrequency(book);
 * int param_1 = obj->get(word);
 */
```

### **Go**

```go
type WordsFrequency struct {
	cnt map[string]int
}

func Constructor(book []string) WordsFrequency {
	cnt := map[string]int{}
	for _, x := range book {
		cnt[x]++
	}
	return WordsFrequency{cnt}
}

func (this *WordsFrequency) Get(word string) int {
	return this.cnt[word]
}

/**
 * Your WordsFrequency object will be instantiated and called as such:
 * obj := Constructor(book);
 * param_1 := obj.Get(word);
 */
```

### **JavaScript**

```js
/**
 * @param {string[]} book
 */
var WordsFrequency = function (book) {
    this.cnt = new Map();
    for (const x of book) {
        this.cnt.set(x, (this.cnt.get(x) || 0) + 1);
    }
};

/**
 * @param {string} word
 * @return {number}
 */
WordsFrequency.prototype.get = function (word) {
    return this.cnt.get(word) || 0;
};

/**
 * Your WordsFrequency object will be instantiated and called as such:
 * var obj = new WordsFrequency(book)
 * var param_1 = obj.get(word)
 */
```

### **TypeScript**

```ts
class WordsFrequency {
    private cnt: Map<string, number>;

    constructor(book: string[]) {
        const cnt = new Map<string, number>();
        for (const word of book) {
            cnt.set(word, (cnt.get(word) ?? 0) + 1);
        }
        this.cnt = cnt;
    }

    get(word: string): number {
        return this.cnt.get(word) ?? 0;
    }
}

/**
 * Your WordsFrequency object will be instantiated and called as such:
 * var obj = new WordsFrequency(book)
 * var param_1 = obj.get(word)
 */
```

### **Rust**

```rust
use std::collections::HashMap;
struct WordsFrequency {
    counter: HashMap<String, i32>
}


/**
 * `&self` means the method takes an immutable reference.
 * If you need a mutable reference, change it to `&mut self` instead.
 */
impl WordsFrequency {

    fn new(book: Vec<String>) -> Self {
        let mut counter = HashMap::new();
        for word in book.into_iter() {
            *counter.entry(word).or_insert(0) += 1;
        }
        Self { counter }
    }

    fn get(&self, word: String) -> i32 {
        *self.counter.get(&word).unwrap_or(&0)
    }
}

/**
 * Your WordsFrequency object will be instantiated and called as such:
 * let obj = WordsFrequency::new(book);
 * let ret_1: i32 = obj.get(word);
 */
```

### **...**

```

```

<!-- tabs:end -->
