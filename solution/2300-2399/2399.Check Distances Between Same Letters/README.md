# [2399. 检查相同字母间的距离](https://leetcode.cn/problems/check-distances-between-same-letters)

[English Version](/solution/2300-2399/2399.Check%20Distances%20Between%20Same%20Letters/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong> 开始的字符串 <code>s</code> ，该字符串仅由小写英文字母组成，<code>s</code> 中的每个字母都 <strong>恰好</strong> 出现 <strong>两次</strong> 。另给你一个下标从 <strong>0</strong> 开始、长度为 <code>26</code> 的的整数数组 <code>distance</code> 。</p>

<p>字母表中的每个字母按从 <code>0</code> 到 <code>25</code> 依次编号（即，<code>'a' -&gt; 0</code>, <code>'b' -&gt; 1</code>, <code>'c' -&gt; 2</code>, ... , <code>'z' -&gt; 25</code>）。</p>

<p>在一个 <strong>匀整</strong> 字符串中，第 <code>i</code> 个字母的两次出现之间的字母数量是 <code>distance[i]</code> 。如果第 <code>i</code> 个字母没有在 <code>s</code> 中出现，那么 <code>distance[i]</code> 可以 <strong>忽略</strong> 。</p>

<p>如果 <code>s</code> 是一个 <strong>匀整</strong> 字符串，返回 <code>true</code> ；否则，返回 <code>false</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>s = "abaccb", distance = [1,3,0,5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
<strong>输出：</strong>true
<strong>解释：</strong>
- 'a' 在下标 0 和下标 2 处出现，所以满足 distance[0] = 1 。
- 'b' 在下标 1 和下标 5 处出现，所以满足 distance[1] = 3 。
- 'c' 在下标 3 和下标 4 处出现，所以满足 distance[2] = 0 。
注意 distance[3] = 5 ，但是由于 'd' 没有在 s 中出现，可以忽略。
因为 s 是一个匀整字符串，返回 true 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>s = "aa", distance = [1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
<strong>输出：</strong>false
<strong>解释：</strong>
- 'a' 在下标 0 和 1 处出现，所以两次出现之间的字母数量为 0 。
但是 distance[0] = 1 ，s 不是一个匀整字符串。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= s.length &lt;= 52</code></li>
	<li><code>s</code> 仅由小写英文字母组成</li>
	<li><code>s</code> 中的每个字母恰好出现两次</li>
	<li><code>distance.length == 26</code></li>
	<li><code>0 &lt;= distance[i] &lt;= 50</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：哈希表**

用哈希表记录每个字母出现的下标，然后遍历哈希表，判断每个字母的下标之差是否等于 `distance` 中对应的值。

时间复杂度 $O(n)$，其中 $n$ 为字符串 `s` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def checkDistances(self, s: str, distance: List[int]) -> bool:
        d = [0] * 26
        for i, c in enumerate(s):
            j = ord(c) - ord("a")
            if d[j] and i - d[j] != distance[j]:
                return False
            d[j] = i + 1
        return True
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean checkDistances(String s, int[] distance) {
        int[] d = new int[26];
        for (int i = 0; i < s.length(); ++i) {
            int j = s.charAt(i) - 'a';
            if (d[j] > 0 && i - d[j] != distance[j]) {
                return false;
            }
            d[j] = i + 1;
        }
        return true;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool checkDistances(string s, vector<int>& distance) {
        vector<int> d(26);
        for (int i = 0; i < s.size(); ++i) {
            int j = s[i] - 'a';
            if (d[j] && i - d[j] != distance[j]) {
                return false;
            }
            d[j] = i + 1;
        }
        return true;
    }
};
```

### **Go**

```go
func checkDistances(s string, distance []int) bool {
	d := make([]int, 26)
	for i, c := range s {
		j := c - 'a'
		if d[j] > 0 && i-d[j] != distance[j] {
			return false
		}
		d[j] = i + 1
	}
	return true
}
```

### **TypeScript**

```ts

```

### **...**

```


```

<!-- tabs:end -->
