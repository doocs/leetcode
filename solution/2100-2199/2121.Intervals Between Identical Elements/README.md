# [2121. 相同元素的间隔之和](https://leetcode.cn/problems/intervals-between-identical-elements)

[English Version](/solution/2100-2199/2121.Intervals%20Between%20Identical%20Elements/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong> 开始、由 <code>n</code> 个整数组成的数组 <code>arr</code> 。</p>

<p><code>arr</code> 中两个元素的 <strong>间隔</strong> 定义为它们下标之间的 <strong>绝对差</strong> 。更正式地，<code>arr[i]</code> 和 <code>arr[j]</code> 之间的间隔是 <code>|i - j|</code> 。</p>

<p>返回一个长度为 <code>n</code> 的数组&nbsp;<code>intervals</code> ，其中 <code>intervals[i]</code> 是<em> </em><code>arr[i]</code><em> </em>和<em> </em><code>arr</code><em> </em>中每个相同元素（与 <code>arr[i]</code> 的值相同）的 <strong>间隔之和</strong> <em>。</em></p>

<p><strong>注意：</strong><code>|x|</code> 是 <code>x</code> 的绝对值。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>arr = [2,1,3,1,2,3,3]
<strong>输出：</strong>[4,2,7,2,4,4,5]
<strong>解释：</strong>
- 下标 0 ：另一个 2 在下标 4 ，|0 - 4| = 4
- 下标 1 ：另一个 1 在下标 3 ，|1 - 3| = 2
- 下标 2 ：另两个 3 在下标 5 和 6 ，|2 - 5| + |2 - 6| = 7
- 下标 3 ：另一个 1 在下标 1 ，|3 - 1| = 2
- 下标 4 ：另一个 2 在下标 0 ，|4 - 0| = 4
- 下标 5 ：另两个 3 在下标 2 和 6 ，|5 - 2| + |5 - 6| = 4
- 下标 6 ：另两个 3 在下标 2 和 5 ，|6 - 2| + |6 - 5| = 5
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>arr = [10,5,10,10]
<strong>输出：</strong>[5,0,3,4]
<strong>解释：</strong>
- 下标 0 ：另两个 10 在下标 2 和 3 ，|0 - 2| + |0 - 3| = 5
- 下标 1 ：只有这一个 5 在数组中，所以到相同元素的间隔之和是 0
- 下标 2 ：另两个 10 在下标 0 和 3 ，|2 - 0| + |2 - 3| = 3
- 下标 3 ：另两个 10 在下标 0 和 2 ，|3 - 0| + |3 - 2| = 4
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == arr.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= arr[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

先用哈希表记录相同元素出现的位置。遍历哈希表，先计算最左侧元素的间隔和，然后逐个计算下个元素的间隔和。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def getDistances(self, arr: List[int]) -> List[int]:
        d = defaultdict(list)
        n = len(arr)
        for i, v in enumerate(arr):
            d[v].append(i)
        ans = [0] * n
        for v in d.values():
            m = len(v)
            val = sum(v) - v[0] * m
            for i, p in enumerate(v):
                delta = v[i] - v[i - 1] if i >= 1 else 0
                val += i * delta - (m - i) * delta
                ans[p] = val
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public long[] getDistances(int[] arr) {
        Map<Integer, List<Integer>> d = new HashMap<>();
        int n = arr.length;
        for (int i = 0; i < n; ++i) {
            d.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);
        }
        long[] ans = new long[n];
        for (List<Integer> v : d.values()) {
            int m = v.size();
            long val = 0;
            for (int e : v) {
                val += e;
            }
            val -= (m * v.get(0));
            for (int i = 0; i < v.size(); ++i) {
                int delta = i >= 1 ? v.get(i) - v.get(i - 1) : 0;
                val += i * delta - (m - i) * delta;
                ans[v.get(i)] = val;
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
    vector<long long> getDistances(vector<int>& arr) {
        unordered_map<int, vector<int>> d;
        int n = arr.size();
        for (int i = 0; i < n; ++i) d[arr[i]].push_back(i);
        vector<long long> ans(n);
        for (auto& item : d) {
            auto& v = item.second;
            int m = v.size();
            long long val = 0;
            for (int e : v) val += e;
            val -= m * v[0];
            for (int i = 0; i < v.size(); ++i) {
                int delta = i >= 1 ? v[i] - v[i - 1] : 0;
                val += i * delta - (m - i) * delta;
                ans[v[i]] = val;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func getDistances(arr []int) []int64 {
	d := make(map[int][]int)
	n := len(arr)
	for i, v := range arr {
		d[v] = append(d[v], i)
	}
	ans := make([]int64, n)
	for _, v := range d {
		m := len(v)
		val := 0
		for _, e := range v {
			val += e
		}
		val -= m * v[0]
		for i, p := range v {
			delta := 0
			if i >= 1 {
				delta = v[i] - v[i-1]
			}
			val += i*delta - (m-i)*delta
			ans[p] = int64(val)
		}
	}
	return ans
}
```

### **TypeScript**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```ts

```

### **...**

```

```

<!-- tabs:end -->
