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

“差分数组 + 贪心 + 排序”实现。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxSumRangeQuery(self, nums: List[int], requests: List[List[int]]) -> int:
        n = 100010
        delta = [0] * n
        for start, end in requests:
            delta[start] += 1
            delta[end + 1] -= 1
        for i in range(1, n):
            delta[i] += delta[i - 1]
        nums.sort()
        delta.sort()
        i, j, res = n - 1, len(nums) - 1, 0
        while i > 0 and delta[i] > 0:
            res += delta[i] * nums[j]
            i -= 1
            j -= 1
        return res % 1000000007
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maxSumRangeQuery(int[] nums, int[][] requests) {
        int n = 100010;
        int[] delta = new int[n];
        for (int[] request : requests) {
            ++delta[request[0]];
            --delta[request[1] + 1];
        }
        for (int i = 1; i < n; ++i) {
            delta[i] += delta[i - 1];
        }
        Arrays.sort(nums);
        Arrays.sort(delta);
        long res = 0;
        for (int i = n - 1, j = nums.length - 1; i >= 0 && delta[i] > 0; --i, --j) {
            res += (long) delta[i] * nums[j];
        }
        return (int) (res % 1000000007);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxSumRangeQuery(vector<int>& nums, vector<vector<int>>& requests) {
        int n = 100010;
        vector<int> delta(n);
        for (auto request : requests) {
            ++delta[request[0]];
            --delta[request[1] + 1];
        }
        for (int i = 1; i < n; ++i) {
            delta[i] += delta[i - 1];
        }
        sort(nums.begin(), nums.end());
        sort(delta.begin(), delta.end());
        long long res = 0;
        for (int i = n - 1, j = nums.size() - 1; i >= 0 && delta[i] > 0; --i, --j) {
            res += (long long)delta[i] * nums[j];
        }
        return (int)(res % 1000000007);
    }
};
```

### **Go**

```go
func maxSumRangeQuery(nums []int, requests [][]int) int {
	n := 100010
	delta := make([]int, n)
	for _, request := range requests {
		delta[request[0]]++
		delta[request[1]+1]--
	}
	for i := 1; i < n; i++ {
		delta[i] += delta[i-1]
	}
	sort.Ints(nums)
	sort.Ints(delta)
	i, j, res := n-1, len(nums)-1, 0
	for i >= 0 && delta[i] > 0 {
		res += delta[i] * nums[j]
		i--
		j--
	}
	return res % 1000000007
}
```

### **...**

```

```

<!-- tabs:end -->
