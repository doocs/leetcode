# [面试题 16.02. 单词频率](https://leetcode.cn/problems/words-frequency-lcci)

[English Version](/lcci/16.02.Words%20Frequency/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>设计一个方法，找出任意指定单词在一本书中的出现频率。</p>
<p>你的实现应该支持如下操作：</p>
<ul>
<li><code>WordsFrequency(book)</code>构造函数，参数为字符串数组构成的一本书</li>
<li><code>get(word)</code>查询指定单词在数中出现的频率</li>
</ul>
<p><strong>示例：</strong></p>
<pre>WordsFrequency wordsFrequency = new WordsFrequency({"i", "have", "an", "apple", "he", "have", "a", "pen"});
wordsFrequency.get("you"); //返回0，"you"没有出现过
wordsFrequency.get("have"); //返回2，"have"出现2次
wordsFrequency.get("an"); //返回1
wordsFrequency.get("apple"); //返回1
wordsFrequency.get("pen"); //返回1
</pre>
<p><strong>提示：</strong></p>
<ul>
<li><code>book[i]</code>中只包含小写字母</li>
<li><code>1 <= book.length <= 100000</code></li>
<li><code>1 <= book[i].length <= 10</code></li>
<li><code>get</code>函数的调用次数不会超过100000</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：哈希表**

我们用哈希表 `cnt` 统计每个单词出现的次数，`get` 函数直接返回 `cnt[word]` 即可。

初始化哈希表 `cnt` 的时间复杂度为 $O(n)$，其中 $n$ 为 `book` 的长度。`get` 函数的时间复杂度为 $O(1)$。空间复杂度为 $O(n)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
