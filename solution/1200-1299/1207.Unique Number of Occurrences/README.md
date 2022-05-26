# [1207. 独一无二的出现次数](https://leetcode.cn/problems/unique-number-of-occurrences)

[English Version](/solution/1200-1299/1207.Unique%20Number%20of%20Occurrences/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组&nbsp;<code>arr</code>，请你帮忙统计数组中每个数的出现次数。</p>

<p>如果每个数的出现次数都是独一无二的，就返回&nbsp;<code>true</code>；否则返回 <code>false</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>arr = [1,2,2,1,1,3]
<strong>输出：</strong>true
<strong>解释：</strong>在该数组中，1 出现了 3 次，2 出现了 2 次，3 只出现了 1 次。没有两个数的出现次数相同。</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>arr = [1,2]
<strong>输出：</strong>false
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>arr = [-3,0,1,-3,1,1,1,-3,10,0]
<strong>输出：</strong>true
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= arr.length&nbsp;&lt;= 1000</code></li>
	<li><code>-1000 &lt;= arr[i] &lt;= 1000</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

“哈希表 - 计数器”实现。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def uniqueOccurrences(self, arr: List[int]) -> bool:
        counter = Counter(arr)
        s = set()
        for num in counter.values():
            if num in s:
                return False
            s.add(num)
        return True
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> counter = new HashMap<>();
        for (int e : arr) {
            counter.put(e, counter.getOrDefault(e, 0) + 1);
        }
        Set<Integer> s = new HashSet<>();
        for (int num : counter.values()) {
            if (s.contains(num)) {
                return false;
            }
            s.add(num);
        }
        return true;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool uniqueOccurrences(vector<int>& arr) {
        unordered_map<int, int> counter;
        for (auto e : arr) {
            ++counter[e];
        }
        unordered_set<int> s;
        for (auto e : counter) {
            int num = e.second;
            if (s.count(num)) return false;
            s.insert(num);
        }
        return true;
    }
};
```

### **Go**

```go
func uniqueOccurrences(arr []int) bool {
	counter := make(map[int]int)
	for _, e := range arr {
		counter[e]++
	}
	s := make(map[int]bool)
	for _, num := range counter {
		if s[num] {
			return false
		}
		s[num] = true
	}
	return true
}
```

### **...**

```

```

<!-- tabs:end -->
