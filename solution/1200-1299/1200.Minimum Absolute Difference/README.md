# [1200. 最小绝对差](https://leetcode.cn/problems/minimum-absolute-difference)

[English Version](/solution/1200-1299/1200.Minimum%20Absolute%20Difference/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你个整数数组&nbsp;<code>arr</code>，其中每个元素都 <strong>不相同</strong>。</p>

<p>请你找到所有具有最小绝对差的元素对，并且按升序的顺序返回。</p>

<p>每对元素对 <code>[a,b</code>] 如下：</p>

<ul>
	<li><code>a ,&nbsp;b</code>&nbsp;均为数组&nbsp;<code>arr</code>&nbsp;中的元素</li>
	<li><code>a &lt; b</code></li>
	<li><code>b - a</code>&nbsp;等于 <code>arr</code> 中任意两个元素的最小绝对差</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>arr = [4,2,1,3]
<strong>输出：</strong>[[1,2],[2,3],[3,4]]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>arr = [1,3,6,10,15]
<strong>输出：</strong>[[1,3]]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>arr = [3,8,-10,23,19,-4,-14,27]
<strong>输出：</strong>[[-14,-10],[19,23],[23,27]]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= arr.length &lt;= 10^5</code></li>
	<li><code>-10^6 &lt;= arr[i] &lt;= 10^6</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：排序**

时间复杂度 $O(nlogn)$，其中 $n$ 表示 $arr$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minimumAbsDifference(self, arr: List[int]) -> List[List[int]]:
        arr.sort()
        ans = []
        mi = inf
        for a, b in pairwise(arr):
            d = b - a
            if d < mi:
                ans = [(a, b)]
                mi = d
            elif d == mi:
                ans.append((a, b))
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        Arrays.sort(arr);
        List<List<Integer>> ans = new ArrayList<>();
        int n = arr.length;
        int mi = Integer.MAX_VALUE;
        for (int i = 0; i < n - 1; ++i) {
            int a = arr[i], b = arr[i + 1];
            int d = b - a;
            if (d < mi) {
                ans.clear();
                ans.add(Arrays.asList(a, b));
                mi = d;
            } else if (d == mi) {
                ans.add(Arrays.asList(a, b));
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
    vector<vector<int>> minimumAbsDifference(vector<int>& arr) {
        sort(arr.begin(), arr.end());
        int mi = INT_MAX;
        int n = arr.size();
        vector<vector<int>> ans;
        for (int i = 0; i < n - 1; ++i) {
            int a = arr[i], b = arr[i + 1];
            int d = b - a;
            if (d < mi) {
                mi = d;
                ans.clear();
                ans.push_back({a, b});
            } else if (d == mi)
                ans.push_back({a, b});
        }
        return ans;
    }
};
```

### **Go**

```go
func minimumAbsDifference(arr []int) [][]int {
	sort.Ints(arr)
	mi := math.MaxInt32
	var ans [][]int
	for i, a := range arr[:len(arr)-1] {
		b := arr[i+1]
		d := b - a
		if d < mi {
			mi = d
			ans = [][]int{[]int{a, b}}
		} else if d == mi {
			ans = append(ans, []int{a, b})
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
