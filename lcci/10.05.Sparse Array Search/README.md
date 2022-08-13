# [面试题 10.05. 稀疏数组搜索](https://leetcode.cn/problems/sparse-array-search-lcci)

[中文文档](/lcci/10.05.Sparse%20Array%20Search/README.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>稀疏数组搜索。有个排好序的字符串数组，其中散布着一些空字符串，编写一种方法，找出给定字符串的位置。</p>

<p><strong>示例1:</strong></p>

<pre><strong> 输入</strong>: words = [&quot;at&quot;, &quot;&quot;, &quot;&quot;, &quot;&quot;, &quot;ball&quot;, &quot;&quot;, &quot;&quot;, &quot;car&quot;, &quot;&quot;, &quot;&quot;,&quot;dad&quot;, &quot;&quot;, &quot;&quot;], s = &quot;ta&quot;
<strong> 输出</strong>：-1
<strong> 说明</strong>: 不存在返回-1。
</pre>

<p><strong>示例2:</strong></p>

<pre><strong> 输入</strong>：words = [&quot;at&quot;, &quot;&quot;, &quot;&quot;, &quot;&quot;, &quot;ball&quot;, &quot;&quot;, &quot;&quot;, &quot;car&quot;, &quot;&quot;, &quot;&quot;,&quot;dad&quot;, &quot;&quot;, &quot;&quot;], s = &quot;ball&quot;
<strong> 输出</strong>：4
</pre>

<p><strong>提示:</strong></p>

<ol>
	<li>words的长度在[1, 1000000]之间</li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findString(self, words: List[str], s: str) -> int:
        left, right = 0, len(words) - 1
        while left < right:
            mid = (left + right) >> 1
            while left < mid and words[mid] == '':
                mid -= 1
            if s <= words[mid]:
                right = mid
            else:
                left = mid + 1
        return -1 if words[left] != s else left
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int findString(String[] words, String s) {
        int left = 0, right = words.length - 1;
        while (left < right) {
            int mid = (left + right) >> 1;
            while (left < mid && "".equals(words[mid])) {
                --mid;
            }
            if (s.compareTo(words[mid]) <= 0) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return s.equals(words[left]) ? left : -1;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int findString(vector<string>& words, string s) {
        int left = 0, right = words.size() - 1;
        while (left < right) {
            int mid = left + right >> 1;
            while (left < mid && words[mid] == "") --mid;
            if (s <= words[mid])
                right = mid;
            else
                left = mid + 1;
        }
        return words[left] == s ? left : -1;
    }
};
```

### **Go**

```go
func findString(words []string, s string) int {
	left, right := 0, len(words)-1
	for left < right {
		mid := (left + right) >> 1
		for left < mid && words[mid] == "" {
			mid--
		}
		if s <= words[mid] {
			right = mid
		} else {
			left = mid + 1
		}
	}
	if words[left] == s {
		return left
	}
	return -1
}
```

### **...**

```

```

<!-- tabs:end -->
