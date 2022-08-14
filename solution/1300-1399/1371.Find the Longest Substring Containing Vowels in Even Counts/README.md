# [1371. 每个元音包含偶数次的最长子字符串](https://leetcode.cn/problems/find-the-longest-substring-containing-vowels-in-even-counts)

[English Version](/solution/1300-1399/1371.Find%20the%20Longest%20Substring%20Containing%20Vowels%20in%20Even%20Counts/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串&nbsp;<code>s</code>&nbsp;，请你返回满足以下条件的最长子字符串的长度：每个元音字母，即&nbsp;&#39;a&#39;，&#39;e&#39;，&#39;i&#39;，&#39;o&#39;，&#39;u&#39; ，在子字符串中都恰好出现了偶数次。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = &quot;eleetminicoworoep&quot;
<strong>输出：</strong>13
<strong>解释：</strong>最长子字符串是 &quot;leetminicowor&quot; ，它包含 <strong>e，i，o</strong>&nbsp;各 2 个，以及 0 个 <strong>a</strong>，<strong>u </strong>。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = &quot;leetcodeisgreat&quot;
<strong>输出：</strong>5
<strong>解释：</strong>最长子字符串是 &quot;leetc&quot; ，其中包含 2 个 <strong>e</strong> 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = &quot;bcbcbc&quot;
<strong>输出：</strong>6
<strong>解释：</strong>这个示例中，字符串 &quot;bcbcbc&quot; 本身就是最长的，因为所有的元音 <strong>a，</strong><strong>e，</strong><strong>i，</strong><strong>o，</strong><strong>u</strong> 都出现了 0 次。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 5 x 10^5</code></li>
	<li><code>s</code>&nbsp;只包含小写英文字母。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

前缀异或 + 状态压缩。

相似题目：[1915. 最美子字符串的数目](/solution/1900-1999/1915.Number%20of%20Wonderful%20Substrings/README.md)

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findTheLongestSubstring(self, s: str) -> int:
        pos = [inf] * 32
        pos[0] = -1
        vowels = 'aeiou'
        state = ans = 0
        for i, c in enumerate(s):
            for j, v in enumerate(vowels):
                if c == v:
                    state ^= 1 << j
            ans = max(ans, i - pos[state])
            pos[state] = min(pos[state], i)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {

    public int findTheLongestSubstring(String s) {
        int[] pos = new int[32];
        Arrays.fill(pos, Integer.MAX_VALUE);
        pos[0] = -1;
        String vowels = "aeiou";
        int state = 0;
        int ans = 0;
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            for (int j = 0; j < 5; ++j) {
                if (c == vowels.charAt(j)) {
                    state ^= (1 << j);
                }
            }
            ans = Math.max(ans, i - pos[state]);
            pos[state] = Math.min(pos[state], i);
        }
        return ans;
    }
}

```

### **C++**

```cpp
class Solution {
public:
    int findTheLongestSubstring(string s) {
        vector<int> pos(32, INT_MAX);
        pos[0] = -1;
        string vowels = "aeiou";
        int state = 0, ans = 0;
        for (int i = 0; i < s.size(); ++i) {
            for (int j = 0; j < 5; ++j)
                if (s[i] == vowels[j])
                    state ^= (1 << j);
            ans = max(ans, i - pos[state]);
            pos[state] = min(pos[state], i);
        }
        return ans;
    }
};
```

### **Go**

```go
func findTheLongestSubstring(s string) int {
	pos := make([]int, 32)
	for i := range pos {
		pos[i] = math.MaxInt32
	}
	pos[0] = -1
	vowels := "aeiou"
	state, ans := 0, 0
	for i, c := range s {
		for j, v := range vowels {
			if c == v {
				state ^= (1 << j)
			}
		}
		ans = max(ans, i-pos[state])
		pos[state] = min(pos[state], i)
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
