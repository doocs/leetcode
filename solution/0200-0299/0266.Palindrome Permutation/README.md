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
        int[] counter = new int[26];
        for (char c : s.toCharArray()) {
            ++counter[c - 'a'];
        }
        int oddCnt = 0;
        for (int cnt : counter) {
            oddCnt += cnt % 2;
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
        vector<int> counter(26);
        for (auto& c : s) ++counter[c - 'a'];
        int oddCnt = 0;
        for (int& cnt : counter) oddCnt += cnt % 2;
        return oddCnt < 2;
    }
};
```

### **Go**

```go
func canPermutePalindrome(s string) bool {
	counter := make([]int, 26)
	for i := range s {
		counter[s[i]-'a']++
	}
	oddCnt := 0
	for _, cnt := range counter {
		oddCnt += cnt % 2
	}
	return oddCnt < 2
}
```

### **...**

```

```

<!-- tabs:end -->
