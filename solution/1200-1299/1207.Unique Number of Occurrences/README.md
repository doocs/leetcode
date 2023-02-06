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

**方法一：哈希表**

我们用哈希表 `cnt` 统计数组 `arr` 中每个数的出现次数，然后用哈希表 `vis` 统计出现次数的种类，最后判断 `cnt` 和 `vis` 的大小是否相等即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 `arr` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def uniqueOccurrences(self, arr: List[int]) -> bool:
        cnt = Counter(arr)
        return len(set(cnt.values())) == len(cnt)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int x : arr) {
            cnt.merge(x, 1, Integer::sum);
        }
        return new HashSet<>(cnt.values()).size() == cnt.size();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool uniqueOccurrences(vector<int>& arr) {
        unordered_map<int, int> cnt;
        for (int& x : arr) {
            ++cnt[x];
        }
        unordered_set<int> vis;
        for (auto& [_, v] : cnt) {
            if (vis.count(v)) {
                return false;
            }
            vis.insert(v);
        }
        return true;
    }
};
```

### **Go**

```go
func uniqueOccurrences(arr []int) bool {
	cnt := map[int]int{}
	for _, x := range arr {
		cnt[x]++
	}
	vis := map[int]bool{}
	for _, v := range cnt {
		if vis[v] {
			return false
		}
		vis[v] = true
	}
	return true
}
```

### **...**

```

```

<!-- tabs:end -->
