# [3019. 按键变更的次数](https://leetcode.cn/problems/number-of-changing-keys)

[English Version](/solution/3000-3099/3019.Number%20of%20Changing%20Keys/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从<strong> 0</strong> 开始的字符串 <code>s</code> ，该字符串由用户输入。按键变更的定义是：使用与上次使用的按键不同的键。例如 <code>s = "ab"</code> 表示按键变更一次，而 <code>s = "bBBb"</code> 不存在按键变更。</p>

<p>返回用户输入过程中按键变更的次数。</p>

<p><strong>注意：</strong><code>shift</code> 或 <code>caps lock</code> 等修饰键不计入按键变更，也就是说，如果用户先输入字母 <code>'a'</code> 然后输入字母 <code>'A'</code> ，不算作按键变更。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "aAbBcC"
<strong>输出：</strong>2
<strong>解释：</strong> 
从 s[0] = 'a' 到 s[1] = 'A'，不存在按键变更，因为不计入 caps lock 或 shift 。
从 s[1] = 'A' 到 s[2] = 'b'，按键变更。
从 s[2] = 'b' 到 s[3] = 'B'，不存在按键变更，因为不计入 caps lock 或 shift 。
从 s[3] = 'B' 到 s[4] = 'c'，按键变更。
从 s[4] = 'c' 到 s[5] = 'C'，不存在按键变更，因为不计入 caps lock 或 shift 。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "AaAaAaaA"
<strong>输出：</strong>0
<strong>解释：</strong> 不存在按键变更，因为这个过程中只按下字母 'a' 和 'A' ，不需要进行按键变更。<!-- notionvc: 8849fe75-f31e-41dc-a2e0-b7d33d8427d2 -->
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>s</code> 仅由英文大写字母和小写字母组成。</li>
</ul>

## 解法

### 方法一

<!-- tabs:start -->

```python
class Solution:
    def countKeyChanges(self, s: str) -> int:
        return sum(s.lower()[i] != s.lower()[i-1] for i in range(1, len(s)))
```

```java
class Solution {
    public int countKeyChanges(String s) {
        int n = s.length();
        int count = 0;
        String lowerCaseString = s.toLowerCase();
        for (int i = 0; i < n - 1; i++) {
            if (lowerCaseString.charAt(i) != lowerCaseString.charAt(i + 1)) {
                count++;
            }
        }
        return count;
    }
}
```

```cpp
class Solution {
public:
    int countKeyChanges(string s) {
        int n = s.size();
        int c = 0;
        transform(s.begin(), s.end(), s.begin(), ::tolower);
        for (int i = 0; i < n - 1; i++) {
            if (s[i] != s[i + 1]) {
                c++;
            }
        }
        return c;
    }
};
```

```go
func countKeyChanges(s string) int {
	n := len(s)
	count := 0
	s = strings.ToLower(s)
	for i := 0; i < n-1; i++ {
		if s[i] != s[i+1] {
			count++
		}
	}
	return count
}
```

<!-- tabs:end -->

<!-- end -->
