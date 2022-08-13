# [2053. 数组中第 K 个独一无二的字符串](https://leetcode.cn/problems/kth-distinct-string-in-an-array)

[English Version](/solution/2000-2099/2053.Kth%20Distinct%20String%20in%20an%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p><strong>独一无二的字符串</strong>&nbsp;指的是在一个数组中只出现过 <strong>一次</strong>&nbsp;的字符串。</p>

<p>给你一个字符串数组&nbsp;<code>arr</code>&nbsp;和一个整数&nbsp;<code>k</code>&nbsp;，请你返回&nbsp;<code>arr</code>&nbsp;中第&nbsp;<code>k</code>&nbsp;个&nbsp;<strong>独一无二的字符串</strong>&nbsp;。如果&nbsp;<strong>少于</strong>&nbsp;<code>k</code>&nbsp;个独一无二的字符串，那么返回&nbsp;<strong>空字符串</strong>&nbsp;<code>""</code>&nbsp;。</p>

<p>注意，按照字符串在原数组中的 <strong>顺序</strong>&nbsp;找到第 <code>k</code>&nbsp;个独一无二字符串。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre><b>输入：</b>arr = ["d","b","c","b","c","a"], k = 2
<b>输出：</b>"a"
<strong>解释：</strong>
arr 中独一无二字符串包括 "d" 和 "a"<code>&nbsp;。</code>
"d" 首先出现，所以它是第 1 个独一无二字符串。
"a" 第二个出现，所以它是 2 个独一无二字符串。
由于 k == 2 ，返回 "a" 。
</pre>

<p><strong>示例 2:</strong></p>

<pre><b>输入：</b>arr = ["aaa","aa","a"], k = 1
<b>输出：</b>"aaa"
<strong>解释：</strong>
arr 中所有字符串都是独一无二的，所以返回第 1 个字符串 "aaa" 。
</pre>

<p><strong>示例 3：</strong></p>

<pre><b>输入：</b>arr = ["a","b","a"], k = 3
<b>输出：</b>""
<strong>解释：</strong>
唯一一个独一无二字符串是 "b" 。由于少于 3 个独一无二字符串，我们返回空字符串 "" 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= arr.length &lt;= 1000</code></li>
	<li><code>1 &lt;= arr[i].length &lt;= 5</code></li>
	<li><code>arr[i]</code>&nbsp;只包含小写英文字母。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

哈希表计数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def kthDistinct(self, arr: List[str], k: int) -> str:
        counter = Counter(arr)
        for v in arr:
            if counter[v] == 1:
                k -= 1
                if k == 0:
                    return v
        return ''
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String kthDistinct(String[] arr, int k) {
        Map<String, Integer> counter = new HashMap<>();
        for (String v : arr) {
            counter.put(v, counter.getOrDefault(v, 0) + 1);
        }
        for (String v : arr) {
            if (counter.get(v) == 1) {
                --k;
                if (k == 0) {
                    return v;
                }
            }
        }
        return "";
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string kthDistinct(vector<string>& arr, int k) {
        unordered_map<string, int> counter;
        for (auto& v : arr) ++counter[v];
        for (auto& v : arr) {
            if (counter[v] == 1) {
                --k;
                if (k == 0) return v;
            }
        }
        return "";
    }
};
```

### **Go**

```go
func kthDistinct(arr []string, k int) string {
	counter := make(map[string]int)
	for _, v := range arr {
		counter[v]++
	}
	for _, v := range arr {
		if counter[v] == 1 {
			k--
			if k == 0 {
				return v
			}
		}
	}
	return ""
}
```

### **...**

```

```

<!-- tabs:end -->
