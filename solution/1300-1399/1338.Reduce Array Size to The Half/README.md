# [1338. 数组大小减半](https://leetcode.cn/problems/reduce-array-size-to-the-half)

[English Version](/solution/1300-1399/1338.Reduce%20Array%20Size%20to%20The%20Half/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组&nbsp;<code>arr</code>。你可以从中选出一个整数集合，并删除这些整数在数组中的每次出现。</p>

<p>返回&nbsp;<strong>至少</strong>&nbsp;能删除数组中的一半整数的整数集合的最小大小。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>arr = [3,3,3,3,5,5,5,2,2,7]
<strong>输出：</strong>2
<strong>解释：</strong>选择 {3,7} 使得结果数组为 [5,5,5,2,2]、长度为 5（原数组长度的一半）。
大小为 2 的可行集合有 {3,5},{3,2},{5,2}。
选择 {2,7} 是不可行的，它的结果数组为 [3,3,3,3,5,5,5]，新数组长度大于原数组的二分之一。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>arr = [7,7,7,7,7,7]
<strong>输出：</strong>1
<strong>解释：</strong>我们只能选择集合 {7}，结果数组为空。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 10<sup>5</sup></code></li>
	<li><code>arr.length</code>&nbsp;为偶数</li>
	<li><code>1 &lt;= arr[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

哈希表计数，按出现的频率倒序。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minSetSize(self, arr: List[int]) -> int:
        couter = Counter(arr)
        ans = n = 0
        for _, cnt in couter.most_common():
            n += cnt
            ans += 1
            if n * 2 >= len(arr):
                break
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minSetSize(int[] arr) {
        Map<Integer, Integer> counter = new HashMap<>();
        for (int v : arr) {
            counter.put(v, counter.getOrDefault(v, 0) + 1);
        }
        List<Integer> t = new ArrayList<>();
        for (int cnt : counter.values()) {
            t.add(cnt);
        }
        Collections.sort(t, Collections.reverseOrder());
        int ans = 0;
        int n = 0;
        for (int cnt : t) {
            n += cnt;
            ++ans;
            if (n * 2 >= arr.length) {
                break;
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minSetSize(vector<int>& arr) {
        unordered_map<int, int> counter;
        for (int v : arr) ++counter[v];
        vector<int> t;
        for (auto& [k, v] : counter) t.push_back(v);
        sort(t.begin(), t.end(), greater<int>());
        int ans = 0;
        int n = 0;
        for (int cnt : t) {
            n += cnt;
            ++ans;
            if (n * 2 >= arr.size()) break;
        }
        return ans;
    }
};
```

### **Go**

```go
func minSetSize(arr []int) int {
	counter := make(map[int]int)
	for _, v := range arr {
		counter[v]++
	}
	var t []int
	for _, v := range counter {
		t = append(t, v)
	}
	sort.Slice(t, func(i, j int) bool {
		return t[i] > t[j]
	})
	ans, n := 0, 0
	for _, cnt := range t {
		n += cnt
		ans++
		if n*2 >= len(arr) {
			break
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
