# [409. 最长回文串](https://leetcode-cn.com/problems/longest-palindrome)

[English Version](/solution/0400-0499/0409.Longest%20Palindrome/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个包含大写字母和小写字母的字符串，找到通过这些字母构造成的最长的回文串。</p>

<p>在构造过程中，请注意区分大小写。比如&nbsp;<code>&quot;Aa&quot;</code>&nbsp;不能当做一个回文字符串。</p>

<p><strong>注意:</strong><br />
假设字符串的长度不会超过 1010。</p>

<p><strong>示例 1: </strong></p>

<pre>
输入:
&quot;abccccdd&quot;

输出:
7

解释:
我们可以构造的最长的回文串是&quot;dccaccd&quot;, 它的长度是 7。
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def longestPalindrome(self, s: str) -> int:
        n = len(s)
        counter = Counter(s)
        odd_cnt = sum(e % 2 for e in counter.values())
        return n if odd_cnt == 0 else n - odd_cnt + 1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int longestPalindrome(String s) {
        int[] counter = new int[128];
        for (char c : s.toCharArray()) {
            ++counter[c];
        }
        int oddCnt = 0;
        for (int e : counter) {
            oddCnt += (e % 2);
        }
        int n = s.length();
        return oddCnt == 0 ? n : n - oddCnt + 1;
    }
}
```

### **TypeScript**

```ts
function longestPalindrome(s: string): number {
    let n = s.length;
    let ans = 0;
    let record = new Array(128).fill(0);
    for (let i = 0; i < n; i++) {
        record[s.charCodeAt(i)]++;
    }
    for (let i = 65; i < 128; i++) {
        let count = record[i];
        ans += count % 2 == 0 ? count : count - 1;
    }
    return ans < s.length ? ans + 1 : ans;
}
```

### **C++**

```cpp
class Solution {
public:
    int longestPalindrome(string s) {
        vector<int> counter(128);
        for (char c : s) ++counter[c];
        int oddCnt = 0;
        for (int e : counter) oddCnt += e % 2;
        int n = s.size();
        return oddCnt == 0 ? n : n - oddCnt + 1;
    }
};
```

### **Go**

```go
func longestPalindrome(s string) int {
	counter := make([]int, 128)
	for _, c := range s {
		counter[c]++
	}
	oddCnt := 0
	for _, e := range counter {
		oddCnt += e % 2
	}
	n := len(s)
	if oddCnt == 0 {
		return n
	}
	return n - oddCnt + 1
}
```

### **...**

```

```

<!-- tabs:end -->
