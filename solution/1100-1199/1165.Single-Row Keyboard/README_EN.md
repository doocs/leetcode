# [1165. Single-Row Keyboard](https://leetcode.com/problems/single-row-keyboard)

[中文文档](/solution/1100-1199/1165.Single-Row%20Keyboard/README.md)

## Description

<p>There is a special keyboard with <strong>all keys in a single row</strong>.</p>

<p>Given a string <code>keyboard</code> of length <code>26</code> indicating the layout of the keyboard (indexed from <code>0</code> to <code>25</code>). Initially, your finger is at index <code>0</code>. To type a character, you have to move your finger to the index of the desired character. The time taken to move your finger from index <code>i</code> to index <code>j</code> is <code>|i - j|</code>.</p>

<p>You want to type a string <code>word</code>. Write a function to calculate how much time it takes to type it with one finger.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> keyboard = &quot;abcdefghijklmnopqrstuvwxyz&quot;, word = &quot;cba&quot;
<strong>Output:</strong> 4
<strong>Explanation: </strong>The index moves from 0 to 2 to write &#39;c&#39; then to 1 to write &#39;b&#39; then to 0 again to write &#39;a&#39;.
Total time = 2 + 1 + 1 = 4. 
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> keyboard = &quot;pqrstuvwxyzabcdefghijklmno&quot;, word = &quot;leetcode&quot;
<strong>Output:</strong> 73
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>keyboard.length == 26</code></li>
	<li><code>keyboard</code> contains each English lowercase letter exactly once in some order.</li>
	<li><code>1 &lt;= word.length &lt;= 10<sup>4</sup></code></li>
	<li><code>word[i]</code> is an English lowercase letter.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def calculateTime(self, keyboard: str, word: str) -> int:
        index = {c: i for i, c in enumerate(keyboard)}
        res = t = 0
        for c in word:
            res += abs(index[c] - t)
            t = index[c]
        return res
```

### **Java**

```java
class Solution {
    public int calculateTime(String keyboard, String word) {
        Map<Character, Integer> index = new HashMap<>();
        for (int i = 0; i < keyboard.length(); ++i) {
            index.put(keyboard.charAt(i), i);
        }
        int res = 0, t = 0;
        for (char c : word.toCharArray()) {
            res += Math.abs(index.get(c) - t);
            t = index.get(c);
        }
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int calculateTime(string keyboard, string word) {
        unordered_map<char, int> index;
        for (int i = 0; i < keyboard.size(); ++i) {
            index[keyboard[i]] = i;
        }
        int res = 0, t = 0;
        for (char c : word) {
            res += abs(index[c] - t);
            t = index[c];
        }
        return res;
    }
};
```

### **Go**

```go
func calculateTime(keyboard string, word string) int {
	index := map[byte]int{}
	for i := 0; i < len(keyboard); i++ {
		index[keyboard[i]] = i
	}
	res := 0
	t := 0
	for i := 0; i < len(word); i++ {
		res += abs(index[word[i]] - t)
		t = index[word[i]]
	}
	return res
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

### **...**

```

```

<!-- tabs:end -->
