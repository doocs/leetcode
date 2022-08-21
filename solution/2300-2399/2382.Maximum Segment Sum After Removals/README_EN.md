# [2382. Maximum Segment Sum After Removals](https://leetcode.com/problems/maximum-segment-sum-after-removals)

[中文文档](/solution/2300-2399/2382.Maximum%20Segment%20Sum%20After%20Removals/README.md)

## Description

<p>You are given two <strong>0-indexed</strong> integer arrays <code>nums</code> and <code>removeQueries</code>, both of length <code>n</code>. For the <code>i<sup>th</sup></code> query, the element in <code>nums</code> at the index <code>removeQueries[i]</code> is removed, splitting <code>nums</code> into different segments.</p>

<p>A <strong>segment</strong> is a contiguous sequence of <strong>positive</strong> integers in <code>nums</code>. A <strong>segment sum</strong> is the sum of every element in a segment.</p>

<p>Return<em> an integer array </em><code>answer</code><em>, of length </em><code>n</code><em>, where </em><code>answer[i]</code><em> is the <strong>maximum</strong> segment sum after applying the </em><code>i<sup>th</sup></code> <em>removal.</em></p>

<p><strong>Note:</strong> The same index will <strong>not</strong> be removed more than once.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,5,6,1], removeQueries = [0,3,2,4,1]
<strong>Output:</strong> [14,7,2,2,0]
<strong>Explanation:</strong> Using 0 to indicate a removed element, the answer is as follows:
Query 1: Remove the 0th element, nums becomes [0,2,5,6,1] and the maximum segment sum is 14 for segment [2,5,6,1].
Query 2: Remove the 3rd element, nums becomes [0,2,5,0,1] and the maximum segment sum is 7 for segment [2,5].
Query 3: Remove the 2nd element, nums becomes [0,2,0,0,1] and the maximum segment sum is 2 for segment [2]. 
Query 4: Remove the 4th element, nums becomes [0,2,0,0,0] and the maximum segment sum is 2 for segment [2]. 
Query 5: Remove the 1st element, nums becomes [0,0,0,0,0] and the maximum segment sum is 0, since there are no segments.
Finally, we return [14,7,2,2,0].</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,2,11,1], removeQueries = [3,2,1,0]
<strong>Output:</strong> [16,5,3,0]
<strong>Explanation:</strong> Using 0 to indicate a removed element, the answer is as follows:
Query 1: Remove the 3rd element, nums becomes [3,2,11,0] and the maximum segment sum is 16 for segment [3,2,11].
Query 2: Remove the 2nd element, nums becomes [3,2,0,0] and the maximum segment sum is 5 for segment [3,2].
Query 3: Remove the 1st element, nums becomes [3,0,0,0] and the maximum segment sum is 3 for segment [3].
Query 4: Remove the 0th element, nums becomes [0,0,0,0] and the maximum segment sum is 0, since there are no segments.
Finally, we return [16,5,3,0].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums.length == removeQueries.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= removeQueries[i] &lt; n</code></li>
	<li>All the values of <code>removeQueries</code> are <strong>unique</strong>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
