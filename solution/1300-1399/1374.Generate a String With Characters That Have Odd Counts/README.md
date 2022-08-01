# [1374. 生成每种字符都是奇数个的字符串](https://leetcode.cn/problems/generate-a-string-with-characters-that-have-odd-counts)

[English Version](/solution/1300-1399/1374.Generate%20a%20String%20With%20Characters%20That%20Have%20Odd%20Counts/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数 <code>n</code>，请你返回一个含<em> <code>n</code> </em>个字符的字符串，其中每种字符在该字符串中都恰好出现 <strong>奇数次</strong> <em><strong>。</strong></em></p>

<p>返回的字符串必须只含小写英文字母。如果存在多个满足题目要求的字符串，则返回其中任意一个即可。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>n = 4
<strong>输出：</strong>&quot;pppz&quot;
<strong>解释：</strong>&quot;pppz&quot; 是一个满足题目要求的字符串，因为 &#39;p&#39; 出现 3 次，且 &#39;z&#39; 出现 1 次。当然，还有很多其他字符串也满足题目要求，比如：&quot;ohhh&quot; 和 &quot;love&quot;。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>n = 2
<strong>输出：</strong>&quot;xy&quot;
<strong>解释：</strong>&quot;xy&quot; 是一个满足题目要求的字符串，因为 &#39;x&#39; 和 &#39;y&#39; 各出现 1 次。当然，还有很多其他字符串也满足题目要求，比如：&quot;ag&quot; 和 &quot;ur&quot;。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>n = 7
<strong>输出：</strong>&quot;holasss&quot;
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 500</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def generateTheString(self, n: int) -> str:
        return 'a' * n if n & 1 else 'a' * (n - 1) + 'b'
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String generateTheString(int n) {
        return (n % 2 == 1) ? "a".repeat(n) : "a".repeat(n - 1) + "b";
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string generateTheString(int n) {
        string ans(n, 'a');
        if (n % 2 == 0) ans[0] = 'b';
        return ans;
    }
};
```

### **Go**

```go
func generateTheString(n int) string {
	ans := strings.Repeat("a", n-1)
	if n%2 == 0 {
		ans += "b"
	} else {
		ans += "a"
	}
	return ans
}
```

### **TypeScript**

```ts
function generateTheString(n: number): string {
    const res = new Array(n).fill('a');
    if (n % 2 === 0) {
        res[n - 1] = 'b';
    }
    return res.join('');
}
```

### **...**

```

```

<!-- tabs:end -->
