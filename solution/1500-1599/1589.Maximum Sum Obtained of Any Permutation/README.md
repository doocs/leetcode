# [1589. 所有排列中的最大和](https://leetcode.cn/problems/maximum-sum-obtained-of-any-permutation)

[English Version](/solution/1500-1599/1589.Maximum%20Sum%20Obtained%20of%20Any%20Permutation/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>有一个整数数组&nbsp;<code>nums</code>&nbsp;，和一个查询数组&nbsp;<code>requests</code>&nbsp;，其中&nbsp;<code>requests[i] = [start<sub>i</sub>, end<sub>i</sub>]</code>&nbsp;。第&nbsp;<code>i</code>&nbsp;个查询求&nbsp;<code>nums[start<sub>i</sub>] + nums[start<sub>i</sub> + 1] + ... + nums[end<sub>i</sub> - 1] + nums[end<sub>i</sub>]</code>&nbsp;的结果&nbsp;，<code>start<sub>i</sub></code> 和&nbsp;<code>end<sub>i</sub></code>&nbsp;数组索引都是 <strong>从 0 开始</strong> 的。</p>

<p>你可以任意排列 <code>nums</code>&nbsp;中的数字，请你返回所有查询结果之和的最大值。</p>

<p>由于答案可能会很大，请你将它对&nbsp;<code>10<sup>9</sup> + 7</code>&nbsp;<strong>取余</strong>&nbsp;后返回。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums = [1,2,3,4,5], requests = [[1,3],[0,1]]
<strong>输出：</strong>19
<strong>解释：</strong>一个可行的 nums 排列为 [2,1,3,4,5]，并有如下结果：
requests[0] -&gt; nums[1] + nums[2] + nums[3] = 1 + 3 + 4 = 8
requests[1] -&gt; nums[0] + nums[1] = 2 + 1 = 3
总和为：8 + 3 = 11。
一个总和更大的排列为 [3,5,4,2,1]，并有如下结果：
requests[0] -&gt; nums[1] + nums[2] + nums[3] = 5 + 4 + 2 = 11
requests[1] -&gt; nums[0] + nums[1] = 3 + 5  = 8
总和为： 11 + 8 = 19，这个方案是所有排列中查询之和最大的结果。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums = [1,2,3,4,5,6], requests = [[0,1]]
<strong>输出：</strong>11
<strong>解释：</strong>一个总和最大的排列为 [6,5,4,3,2,1] ，查询和为 [11]。</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>nums = [1,2,3,4,5,10], requests = [[0,2],[1,3],[1,1]]
<strong>输出：</strong>47
<strong>解释：</strong>一个和最大的排列为 [4,10,5,3,2,1] ，查询结果分别为 [19,18,10]。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i]&nbsp;&lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= requests.length &lt;=&nbsp;10<sup>5</sup></code></li>
	<li><code>requests[i].length == 2</code></li>
	<li><code>0 &lt;= start<sub>i</sub>&nbsp;&lt;= end<sub>i</sub>&nbsp;&lt;&nbsp;n</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：差分数组 + 排序 + 贪心**

我们观察发现，对于一次查询操作，会返回该查询区间 $[l, r]$ 中的所有元素之和。而题目要求的是所有查询操作的结果之和的最大值，也即是说，我们要累计所有查询操作的结果，使得这些结果之和最大。因此，如果一个下标 $i$ 在查询操作中出现的次数越多，那么我们就应该赋给下标 $i$ 一个较大的值，这样才能使得所有查询操作的结果之和最大。

因此，我们可以用差分数组的思想，统计每个下标在查询操作中出现的次数，然后对这些次数从小到大进行排序，然后对数组 $nums$ 也从小到大进行排序，这样就能保证每个下标 $i$ 在查询操作中出现的次数越多，该下标对应的值 $nums[i]$ 就越大。接下来，我们只需要将这些下标对应的值 $nums[i]$ 与其在查询操作中出现的次数相乘，然后累加起来，就是所有查询操作的结果之和的最大值。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $nums$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxSumRangeQuery(self, nums: List[int], requests: List[List[int]]) -> int:
        n = len(nums)
        d = [0] * n
        for l, r in requests:
            d[l] += 1
            if r + 1 < n:
                d[r + 1] -= 1
        for i in range(1, n):
            d[i] += d[i - 1]
        nums.sort()
        d.sort()
        mod = 10**9 + 7
        return sum(a * b for a, b in zip(nums, d)) % mod
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maxSumRangeQuery(int[] nums, int[][] requests) {
        int n = nums.length;
        int[] d = new int[n];
        for (var req : requests) {
            int l = req[0], r = req[1];
            d[l]++;
            if (r + 1 < n) {
                d[r + 1]--;
            }
        }
        for (int i = 1; i < n; ++i) {
            d[i] += d[i - 1];
        }
        Arrays.sort(nums);
        Arrays.sort(d);
        final int mod = (int) 1e9 + 7;
        long ans = 0;
        for (int i = 0; i < n; ++i) {
            ans = (ans + 1L * nums[i] * d[i]) % mod;
        }
        return (int) ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxSumRangeQuery(vector<int>& nums, vector<vector<int>>& requests) {
        int n = nums.size();
        int d[n];
        memset(d, 0, sizeof(d));
        for (auto& req : requests) {
            int l = req[0], r = req[1];
            d[l]++;
            if (r + 1 < n) {
                d[r + 1]--;
            }
        }
        for (int i = 1; i < n; ++i) {
            d[i] += d[i - 1];
        }
        sort(nums.begin(), nums.end());
        sort(d, d + n);
        long long ans = 0;
        const int mod = 1e9 + 7;
        for (int i = 0; i < n; ++i) {
            ans = (ans + 1LL * nums[i] * d[i]) % mod;
        }
        return ans;
    }
};
```

### **Go**

```go
func maxSumRangeQuery(nums []int, requests [][]int) (ans int) {
	n := len(nums)
	d := make([]int, n)
	for _, req := range requests {
		l, r := req[0], req[1]
		d[l]++
		if r+1 < n {
			d[r+1]--
		}
	}
	for i := 1; i < n; i++ {
		d[i] += d[i-1]
	}
	sort.Ints(nums)
	sort.Ints(d)
	const mod = 1e9 + 7
	for i, a := range nums {
		b := d[i]
		ans = (ans + a*b) % mod
	}
	return
```

### **TypeScript**

```ts
function maxSumRangeQuery(nums: number[], requests: number[][]): number {
    const n = nums.length;
    const d = new Array(n).fill(0);
    for (const [l, r] of requests) {
        d[l]++;
        if (r + 1 < n) {
            d[r + 1]--;
        }
    }
    for (let i = 1; i < n; ++i) {
        d[i] += d[i - 1];
    }
    nums.sort((a, b) => a - b);
    d.sort((a, b) => a - b);
    let ans = 0;
    const mod = 10 ** 9 + 7;
    for (let i = 0; i < n; ++i) {
        ans = (ans + nums[i] * d[i]) % mod;
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
