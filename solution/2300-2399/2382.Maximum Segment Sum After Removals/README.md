# [2382. 删除操作后的最大子段和](https://leetcode.cn/problems/maximum-segment-sum-after-removals)

[English Version](/solution/2300-2399/2382.Maximum%20Segment%20Sum%20After%20Removals/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>nums</code> 和&nbsp;<code>removeQueries</code>&nbsp;，两者长度都为&nbsp;<code>n</code>&nbsp;。对于第&nbsp;<code>i</code>&nbsp;个查询，<code>nums</code>&nbsp;中位于下标&nbsp;<code>removeQueries[i]</code>&nbsp;处的元素被删除，将 <code>nums</code>&nbsp;分割成更小的子段。</p>

<p>一个 <strong>子段</strong>&nbsp;是 <code>nums</code>&nbsp;中连续 <strong>正</strong>&nbsp;整数形成的序列。<strong>子段和</strong>&nbsp;是子段中所有元素的和。</p>

<p>请你返回一个长度为 <code>n</code>&nbsp;的整数数组<em>&nbsp;</em><code>answer</code>&nbsp;，其中<em>&nbsp;</em><code>answer[i]</code>是第&nbsp;<code>i</code>&nbsp;次删除操作以后的&nbsp;<strong>最大</strong>&nbsp;子段和。</p>

<p><strong>注意：</strong>一个下标至多只会被删除一次。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>nums = [1,2,5,6,1], removeQueries = [0,3,2,4,1]
<b>输出：</b>[14,7,2,2,0]
<b>解释：</b>用 0 表示被删除的元素，答案如下所示：
查询 1 ：删除第 0 个元素，nums 变成 [0,2,5,6,1] ，最大子段和为子段 [2,5,6,1] 的和 14 。
查询 2 ：删除第 3 个元素，nums 变成 [0,2,5,0,1] ，最大子段和为子段 [2,5] 的和 7 。
查询 3 ：删除第 2 个元素，nums 变成 [0,2,0,0,1] ，最大子段和为子段 [2] 的和 2 。
查询 4 ：删除第 4 个元素，nums 变成 [0,2,0,0,0] ，最大子段和为子段 [2] 的和 2 。
查询 5 ：删除第 1 个元素，nums 变成 [0,0,0,0,0] ，最大子段和为 0 ，因为没有任何子段存在。
所以，我们返回 [14,7,2,2,0] 。</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>nums = [3,2,11,1], removeQueries = [3,2,1,0]
<b>输出：</b>[16,5,3,0]
<b>解释：</b>用 0 表示被删除的元素，答案如下所示：
查询 1 ：删除第 3 个元素，nums 变成 [3,2,11,0] ，最大子段和为子段 [3,2,11] 的和 16 。
查询 2 ：删除第 2 个元素，nums 变成 [3,2,0,0] ，最大子段和为子段 [3,2] 的和 5 。
查询 3 ：删除第 1 个元素，nums 变成 [3,0,0,0] ，最大子段和为子段 [3] 的和 3 。
查询 5 ：删除第 0 个元素，nums 变成 [0,0,0,0] ，最大子段和为 0 ，因为没有任何子段存在。
所以，我们返回 [16,5,3,0] 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums.length == removeQueries.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= removeQueries[i] &lt; n</code></li>
	<li><code>removeQueries</code>&nbsp;中所有数字 <strong>互不相同</strong>&nbsp;。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：逆向思维 + 并查集**

考虑**从后往前遍历**数组 $removeQueries$ 中的每个元素 $i$，用并查集来维护以 $nums[i]$ 所在的连续子数组的和。

遍历过程中：

对于 $removeQueries$ 中的每一个 $i$，若下标 $i-1$ 对应的元素遍历过，可以将 $i-1$ 与 $i$ 进行合并，同理，若下标 $i+1$ 对应的元素也遍历过了，将 $i$ 与 $i+1$ 合并。合并过程中更新连通块的元素和。

时间复杂度 $O(nlogn)$。其中 $n$ 是 $nums$ 中的元素个数。

相似题目：[2334. 元素值大于变化阈值的子数组](/solution/2300-2399/2334.Subarray%20With%20Elements%20Greater%20Than%20Varying%20Threshold/README.md)

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maximumSegmentSum(self, nums: List[int], removeQueries: List[int]) -> List[int]:
        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        def merge(a, b):
            pa, pb = find(a), find(b)
            p[pa] = pb
            s[pb] += s[pa]

        n = len(nums)
        p = list(range(n))
        s = [0] * n
        ans = [0] * n
        mx = 0
        for j in range(n - 1, 0, -1):
            i = removeQueries[j]
            s[i] = nums[i]
            if i and s[find(i - 1)]:
                merge(i, i - 1)
            if i < n - 1 and s[find(i + 1)]:
                merge(i, i + 1)
            mx = max(mx, s[find(i)])
            ans[j - 1] = mx
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int[] p;
    private long[] s;

    public long[] maximumSegmentSum(int[] nums, int[] removeQueries) {
        int n = nums.length;
        p = new int[n];
        s = new long[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
        }
        long[] ans = new long[n];
        long mx = 0;
        for (int j = n - 1; j > 0; --j) {
            int i = removeQueries[j];
            s[i] = nums[i];
            if (i > 0 && s[find(i - 1)] > 0) {
                merge(i, i - 1);
            }
            if (i < n - 1 && s[find(i + 1)] > 0) {
                merge(i, i + 1);
            }
            mx = Math.max(mx, s[find(i)]);
            ans[j - 1] = mx;
        }
        return ans;
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }

    private void merge(int a, int b) {
        int pa = find(a), pb = find(b);
        p[pa] = pb;
        s[pb] += s[pa];
    }
}
```

### **C++**

```cpp
using ll = long long;

class Solution {
public:
    vector<int> p;
    vector<ll> s;

    vector<long long> maximumSegmentSum(vector<int>& nums, vector<int>& removeQueries) {
        int n = nums.size();
        p.resize(n);
        for (int i = 0; i < n; ++i) p[i] = i;
        s.assign(n, 0);
        vector<ll> ans(n);
        ll mx = 0;
        for (int j = n - 1; j; --j) {
            int i = removeQueries[j];
            s[i] = nums[i];
            if (i && s[find(i - 1)]) merge(i, i - 1);
            if (i < n - 1 && s[find(i + 1)]) merge(i, i + 1);
            mx = max(mx, s[find(i)]);
            ans[j - 1] = mx;
        }
        return ans;
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }

    void merge(int a, int b) {
        int pa = find(a), pb = find(b);
        p[pa] = pb;
        s[pb] += s[pa];
    }
};
```

### **Go**

```go
func maximumSegmentSum(nums []int, removeQueries []int) []int64 {
	n := len(nums)
	p := make([]int, n)
	s := make([]int, n)
	for i := range p {
		p[i] = i
	}
	var find func(x int) int
	find = func(x int) int {
		if p[x] != x {
			p[x] = find(p[x])
		}
		return p[x]
	}
	merge := func(a, b int) {
		pa, pb := find(a), find(b)
		p[pa] = pb
		s[pb] += s[pa]
	}
	mx := 0
	ans := make([]int64, n)
	for j := n - 1; j > 0; j-- {
		i := removeQueries[j]
		s[i] = nums[i]
		if i > 0 && s[find(i-1)] > 0 {
			merge(i, i-1)
		}
		if i < n-1 && s[find(i+1)] > 0 {
			merge(i, i+1)
		}
		mx = max(mx, s[find(i)])
		ans[j-1] = int64(mx)
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts

```

### **...**

```


```

<!-- tabs:end -->
