# [2542. 最大子序列的分数](https://leetcode.cn/problems/maximum-subsequence-score)

[English Version](/solution/2500-2599/2542.Maximum%20Subsequence%20Score/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>nums1</code>&nbsp;和&nbsp;<code>nums2</code>&nbsp;，两者长度都是&nbsp;<code>n</code>&nbsp;，再给你一个正整数&nbsp;<code>k</code>&nbsp;。你必须从&nbsp;<code>nums1</code>&nbsp;中选一个长度为 <code>k</code>&nbsp;的 <strong>子序列</strong>&nbsp;对应的下标。</p>

<p>对于选择的下标&nbsp;<code>i<sub>0</sub></code>&nbsp;，<code>i<sub>1</sub></code>&nbsp;，...，&nbsp;<code>i<sub>k - 1</sub></code>&nbsp;，你的&nbsp;<strong>分数</strong>&nbsp;定义如下：</p>

<ul>
	<li><code>nums1</code>&nbsp;中下标对应元素求和，乘以&nbsp;<code>nums2</code>&nbsp;中下标对应元素的&nbsp;<strong>最小值</strong>&nbsp;。</li>
	<li>用公示表示：&nbsp;<code>(nums1[i<sub>0</sub>] + nums1[i<sub>1</sub>] +...+ nums1[i<sub>k - 1</sub>]) * min(nums2[i<sub>0</sub>] , nums2[i<sub>1</sub>], ... ,nums2[i<sub>k - 1</sub>])</code>&nbsp;。</li>
</ul>

<p>请你返回 <strong>最大</strong>&nbsp;可能的分数。</p>

<p>一个数组的 <strong>子序列</strong>&nbsp;下标是集合&nbsp;<code>{0, 1, ..., n-1}</code>&nbsp;中删除若干元素得到的剩余集合，也可以不删除任何元素。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>nums1 = [1,3,3,2], nums2 = [2,1,3,4], k = 3
<b>输出：</b>12
<b>解释：</b>
四个可能的子序列分数为：
- 选择下标 0 ，1 和 2 ，得到分数 (1+3+3) * min(2,1,3) = 7 。
- 选择下标 0 ，1 和 3 ，得到分数 (1+3+2) * min(2,1,4) = 6 。
- 选择下标 0 ，2 和 3 ，得到分数 (1+3+2) * min(2,3,4) = 12 。
- 选择下标 1 ，2 和 3 ，得到分数 (3+3+2) * min(1,3,4) = 8 。
所以最大分数为 12 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>nums1 = [4,2,3,1,1], nums2 = [7,5,10,9,6], k = 1
<b>输出：</b>30
<b>解释：</b>
选择下标 2 最优：nums1[2] * nums2[2] = 3 * 10 = 30 是最大可能分数。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums1.length == nums2.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums1[i], nums2[j] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= n</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：排序 + 优先队列（小根堆）**

将 `nums2` 与 `nums1` 按照 `nums2` 降序排序，然后从前往后遍历，维护一个小根堆，堆中存储 `nums1` 中的元素，堆中元素个数不超过 $k$ 个，同时维护一个变量 $s$，表示堆中元素的和，遍历过程中不断更新答案。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 `nums1` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxScore(self, nums1: List[int], nums2: List[int], k: int) -> int:
        nums = sorted(zip(nums2, nums1), reverse=True)
        q = []
        ans = s = 0
        for a, b in nums:
            s += b
            heappush(q, b)
            if len(q) == k:
                ans = max(ans, s * a)
                s -= heappop(q)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public long maxScore(int[] nums1, int[] nums2, int k) {
        int n = nums1.length;
        int[][] nums = new int[n][2];
        for (int i = 0; i < n; ++i) {
            nums[i] = new int[] {nums1[i], nums2[i]};
        }
        Arrays.sort(nums, (a, b) -> b[1] - a[1]);
        long ans = 0, s = 0;
        PriorityQueue<Integer> q = new PriorityQueue<>();
        for (int i = 0; i < n; ++i) {
            s += nums[i][0];
            q.offer(nums[i][0]);
            if (q.size() == k) {
                ans = Math.max(ans, s * nums[i][1]);
                s -= q.poll();
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
    long long maxScore(vector<int>& nums1, vector<int>& nums2, int k) {
        int n = nums1.size();
        vector<pair<int, int>> nums(n);
        for (int i = 0; i < n; ++i) {
            nums[i] = {-nums2[i], nums1[i]};
        }
        sort(nums.begin(), nums.end());
        priority_queue<int, vector<int>, greater<int>> q;
        long long ans = 0, s = 0;
        for (auto& [a, b] : nums) {
            s += b;
            q.push(b);
            if (q.size() == k) {
                ans = max(ans, s * -a);
                s -= q.top();
                q.pop();
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func maxScore(nums1 []int, nums2 []int, k int) int64 {
	type pair struct{ a, b int }
	nums := []pair{}
	for i, a := range nums1 {
		b := nums2[i]
		nums = append(nums, pair{a, b})
	}
	sort.Slice(nums, func(i, j int) bool { return nums[i].b > nums[j].b })
	q := hp{}
	var ans, s int
	for _, e := range nums {
		a, b := e.a, e.b
		s += a
		heap.Push(&q, a)
		if q.Len() == k {
			ans = max(ans, s*b)
			s -= heap.Pop(&q).(int)
		}
	}
	return int64(ans)
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

type hp struct{ sort.IntSlice }

func (h hp) Less(i, j int) bool  { return h.IntSlice[i] < h.IntSlice[j] }
func (h *hp) Push(v interface{}) { h.IntSlice = append(h.IntSlice, v.(int)) }
func (h *hp) Pop() interface{} {
	a := h.IntSlice
	v := a[len(a)-1]
	h.IntSlice = a[:len(a)-1]
	return v
}
```

### **...**

```

```

<!-- tabs:end -->
