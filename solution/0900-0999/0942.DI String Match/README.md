# [942. 增减字符串匹配](https://leetcode-cn.com/problems/di-string-match)

[English Version](/solution/0900-0999/0942.DI%20String%20Match/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定只含&nbsp;<code>&quot;I&quot;</code>（增大）或 <code>&quot;D&quot;</code>（减小）的字符串&nbsp;<code>S</code>&nbsp;，令&nbsp;<code>N = S.length</code>。</p>

<p>返回&nbsp;<code>[0, 1, ..., N]</code>&nbsp;的任意排列&nbsp;<code>A</code>&nbsp;使得对于所有&nbsp;<code>i = 0,&nbsp;..., N-1</code>，都有：</p>

<ul>
	<li>如果&nbsp;<code>S[i] == &quot;I&quot;</code>，那么&nbsp;<code>A[i] &lt; A[i+1]</code></li>
	<li>如果&nbsp;<code>S[i] == &quot;D&quot;</code>，那么&nbsp;<code>A[i] &gt; A[i+1]</code></li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>&quot;IDID&quot;
<strong>输出：</strong>[0,4,1,3,2]
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>&quot;III&quot;
<strong>输出：</strong>[0,1,2,3]
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>&quot;DDI&quot;
<strong>输出：</strong>[3,2,0,1]</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= S.length &lt;= 10000</code></li>
	<li><code>S</code> 只包含字符&nbsp;<code>&quot;I&quot;</code>&nbsp;或&nbsp;<code>&quot;D&quot;</code>。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

类似贪心思想，如果当前字母是 `I`，我们只需要选择当前可选的最小数字，就能保证后面的数字无论怎么排列，当前数字和下一个数字一定是递增关系。`D` 同理，选择当前可选的最大数字即可

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def diStringMatch(self, s: str) -> List[int]:
        n = len(s)
        low, high = 0, n
        ans = []
        for i in range(n):
            if s[i] == 'I':
                ans.append(low)
                low += 1
            else:
                ans.append(high)
                high -= 1
        ans.append(low)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] diStringMatch(String s) {
        int n = s.length();
        int low = 0, high = n;
        int[] ans = new int[n + 1];
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'I') {
                ans[i] = low++;
            } else {
                ans[i] = high--;
            }
        }
        ans[n] = low;
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> diStringMatch(string s) {
        int n = s.size();
        int low = 0, high = n;
        vector<int> ans(n + 1);
        for (int i = 0; i < n; ++i) {
            if (s[i] == 'I') {
                ans[i] = low++;
            } else {
                ans[i] = high--;
            }
        }
        ans[n] = low;
        return ans;
    }
};
```

### **Go**

```go
func diStringMatch(s string) []int {
	n := len(s)
	low, high := 0, n
	var ans []int
	for i := 0; i < n; i++ {
		if s[i] == 'I' {
			ans = append(ans, low)
			low++
		} else {
			ans = append(ans, high)
			high--
		}
	}
	ans = append(ans, low)
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
