# [266. 回文排列](https://leetcode-cn.com/problems/palindrome-permutation)

[English Version](/solution/0200-0299/0266.Palindrome%20Permutation/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个字符串，判断该字符串中是否可以通过重新排列组合，形成一个回文字符串。</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入:</strong> <code>&quot;code&quot;</code>
<strong>输出:</strong> false</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入:</strong> <code>&quot;aab&quot;</code>
<strong>输出:</strong> true</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入:</strong> <code>&quot;carerac&quot;</code>
<strong>输出:</strong> true</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

利用 HashMap（字典表）统计每个字符出现的频率，至多有一个字符出现奇数次数即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def canPermutePalindrome(self, s: str) -> bool:
        counter = Counter(s)
        return sum(e % 2 for e in counter.values()) < 2
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean canPermutePalindrome(String s) {
        Map<Character, Integer> counter = new HashMap<>();
        for (char c : s.toCharArray()) {
            counter.put(c, counter.getOrDefault(c, 0) + 1);
        }
        int oddCnt = 0;
        for (int e : counter.values()) {
            oddCnt += e % 2;
        }
        return oddCnt < 2;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool canPermutePalindrome(string s) {
        unordered_map<char, int> counter;
        for (char c : s) ++counter[c];
        int oddCnt = 0;
        for (auto& it : counter) oddCnt += it.second % 2;
        return oddCnt < 2;
    }
};
```

### **Go**

```go
func canPermutePalindrome(s string) bool {
    counter := make(map[rune]int)
    for _, c := range s {
        counter[c]++
    }
    oddCnt := 0
    for _, e := range counter {
        oddCnt += e % 2
    }
    return oddCnt < 2
}
```

### **...**

```

```

<!-- tabs:end -->
