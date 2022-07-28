# [1331. 数组序号转换](https://leetcode.cn/problems/rank-transform-of-an-array)

[English Version](/solution/1300-1399/1331.Rank%20Transform%20of%20an%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组&nbsp;<code>arr</code> ，请你将数组中的每个元素替换为它们排序后的序号。</p>

<p>序号代表了一个元素有多大。序号编号的规则如下：</p>

<ul>
	<li>序号从 1 开始编号。</li>
	<li>一个元素越大，那么序号越大。如果两个元素相等，那么它们的序号相同。</li>
	<li>每个数字的序号都应该尽可能地小。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>arr = [40,10,20,30]
<strong>输出：</strong>[4,1,2,3]
<strong>解释：</strong>40 是最大的元素。 10 是最小的元素。 20 是第二小的数字。 30 是第三小的数字。</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>arr = [100,100,100]
<strong>输出：</strong>[1,1,1]
<strong>解释：</strong>所有元素有相同的序号。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>arr = [37,12,28,9,100,56,80,5,12]
<strong>输出：</strong>[5,3,4,2,8,6,7,1,3]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= arr.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>9</sup>&nbsp;&lt;= arr[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：离散化**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def arrayRankTransform(self, arr: List[int]) -> List[int]:
        t = sorted(set(arr))
        return [bisect_left(t, x) + 1 for x in arr]
```

```python
class Solution:
    def arrayRankTransform(self, arr: List[int]) -> List[int]:
        m = {v: i for i, v in enumerate(sorted(set(arr)), 1)}
        return [m[v] for v in arr]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] arrayRankTransform(int[] arr) {
        Set<Integer> s = new HashSet<>();
        for (int v : arr) {
            s.add(v);
        }
        List<Integer> alls = new ArrayList<>(s);
        alls.sort((a, b) -> a - b);
        Map<Integer, Integer> m = new HashMap<>();
        for (int i = 0; i < alls.size(); ++i) {
            m.put(alls.get(i), i + 1);
        }
        int[] ans = new int[arr.length];
        for (int i = 0; i < arr.length; ++i) {
            ans[i] = m.get(arr[i]);
        }
        return ans;
    }
}
```

```java
class Solution {
    public int[] arrayRankTransform(int[] arr) {
        Set<Integer> s = new HashSet<>();
        for (int v : arr) {
            s.add(v);
        }
        List<Integer> alls = new ArrayList<>(s);
        alls.sort((a, b) -> a - b);
        int n = arr.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            ans[i] = Collections.binarySearch(alls, arr[i]) + 1;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> arrayRankTransform(vector<int>& arr) {
        unordered_set<int> s(arr.begin(), arr.end());
        vector<int> alls(s.begin(), s.end());
        sort(alls.begin(), alls.end());
        unordered_map<int, int> m;
        for (int i = 0; i < alls.size(); ++i) m[alls[i]] = i + 1;
        vector<int> ans;
        for (int v : arr) ans.push_back(m[v]);
        return ans;
    }
};
```

```cpp
class Solution {
public:
    vector<int> arrayRankTransform(vector<int>& arr) {
        unordered_set<int> s(arr.begin(), arr.end());
        vector<int> alls(s.begin(), s.end());
        sort(alls.begin(), alls.end());
        vector<int> ans;
        for (int v: arr) ans.push_back(lower_bound(alls.begin(), alls.end(), v) - alls.begin() + 1);
        return ans;
    }
};
```

### **Go**

```go
func arrayRankTransform(arr []int) []int {
	s := make(map[int]bool)
	for _, v := range arr {
		s[v] = true
	}
	var alls []int
	for v := range s {
		alls = append(alls, v)
	}
	sort.Ints(alls)
	m := make(map[int]int)
	for i, v := range alls {
		m[v] = i + 1
	}
	var ans []int
	for _, v := range arr {
		ans = append(ans, m[v])
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
