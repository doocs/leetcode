# [面试题 17.05. 字母与数字](https://leetcode.cn/problems/find-longest-subarray-lcci)

[English Version](/lcci/17.05.Find%20Longest%20Subarray/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>给定一个放有字符和数字的数组，找到最长的子数组，且包含的字符和数字的个数相同。</p>

<p>返回该子数组，若存在多个最长子数组，返回左端点最小的。若不存在这样的数组，返回一个空数组。</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入: </strong>[&quot;A&quot;,&quot;1&quot;,&quot;B&quot;,&quot;C&quot;,&quot;D&quot;,&quot;2&quot;,&quot;3&quot;,&quot;4&quot;,&quot;E&quot;,&quot;5&quot;,&quot;F&quot;,&quot;G&quot;,&quot;6&quot;,&quot;7&quot;,&quot;H&quot;,&quot;I&quot;,&quot;J&quot;,&quot;K&quot;,&quot;L&quot;,&quot;M&quot;]

<strong>输出: </strong>[&quot;A&quot;,&quot;1&quot;,&quot;B&quot;,&quot;C&quot;,&quot;D&quot;,&quot;2&quot;,&quot;3&quot;,&quot;4&quot;,&quot;E&quot;,&quot;5&quot;,&quot;F&quot;,&quot;G&quot;,&quot;6&quot;,&quot;7&quot;]
</pre>

<p><strong>示例 2:</strong></p>

<pre><strong>输入: </strong>[&quot;A&quot;,&quot;A&quot;]

<strong>输出: </strong>[]
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>array.length &lt;= 100000</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

前缀和 + 哈希表。

遍历字符串数组 array，将数字视为 1，字母视为 -1（或者反过来），题目转换为元素和为 0 的最长子数组。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findLongestSubarray(self, array: List[str]) -> List[str]:
        seen = {0: -1}
        t = mx = 0
        ans = []
        for i, s in enumerate(array):
            t += 1 if s.isalpha() else -1
            if t in seen:
                if mx < i - seen[t]:
                    mx = i - seen[t]
                    ans = array[seen[t] + 1 : i + 1]
            else:
                seen[t] = i
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String[] findLongestSubarray(String[] array) {
        Map<Integer, Integer> seen = new HashMap<>();
        seen.put(0, -1);
        int t = 0, mx = 0;
        int j = 0;
        for (int i = 0; i < array.length; ++i) {
            t += Character.isDigit(array[i].charAt(0)) ? 1 : -1;
            if (seen.containsKey(t)) {
                if (mx < i - seen.get(t)) {
                    mx = i - seen.get(t);
                    j = seen.get(t) + 1;
                }
            } else {
                seen.put(t, i);
            }
        }
        String[] ans = new String[mx];
        for (int i = 0; i < mx; ++i) {
            ans[i] = array[i + j];
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<string> findLongestSubarray(vector<string>& array) {
        unordered_map<int, int> seen;
        seen[0] = -1;
        int t = 0, mx = 0, j = 0;
        for (int i = 0; i < array.size(); ++i) {
            t += isdigit(array[i][0]) ? 1 : -1;
            if (seen.count(t)) {
                if (mx < i - seen[t]) {
                    mx = i - seen[t];
                    j = seen[t] + 1;
                }
            } else {
                seen[t] = i;
            }
        }
        return {array.begin() + j, array.begin() + j + mx};
    }
};
```

### **Go**

```go
func findLongestSubarray(array []string) []string {
	seen := map[int]int{0: -1}
	t, mx, j := 0, 0, 0
	for i, s := range array {
		if unicode.IsDigit(rune(s[0])) {
			t++
		} else {
			t--
		}
		if k, ok := seen[t]; ok {
			if mx < i-k {
				mx = i - k
				j = k + 1
			}
		} else {
			seen[t] = i
		}
	}
	return array[j : j+mx]
}
```

### **...**

```

```

<!-- tabs:end -->
