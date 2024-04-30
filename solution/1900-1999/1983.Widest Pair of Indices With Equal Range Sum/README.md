# [1983. 范围和相等的最宽索引对 🔒](https://leetcode.cn/problems/widest-pair-of-indices-with-equal-range-sum)

[English Version](/solution/1900-1999/1983.Widest%20Pair%20of%20Indices%20With%20Equal%20Range%20Sum/README_EN.md)

<!-- tags:数组,哈希表,前缀和 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给定两个 <strong>以0为索引</strong> 的二进制数组 <code>nums1</code> 和 <code>nums2</code> 。找出 <strong>最宽</strong> 的索引对 <code>(i, j)</code> ，使的&nbsp;<code>i &lt;= j</code>&nbsp;并且&nbsp;<code>nums1[i] + nums1[i+1] + ... + nums1[j] == nums2[i] + nums2[i+1] + ... + nums2[j]</code>。</p>

<p><strong>最宽</strong> 的指标对是指在 <code>i </code>和<code> j </code>之间的 <strong>距离最大</strong> 的指标对。一对指标之间的 <strong>距离</strong> 定义为<code> j - i + 1</code> 。</p>

<p>返回 <strong>最宽</strong> 索引对的 <strong>距离</strong> 。如果没有满足条件的索引对，则返回 <code>0</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> nums1 = [1,1,0,1], nums2 = [0,1,1,0]
<strong>输出:</strong> 3
<strong>解释:</strong>
如果i = 1, j = 3:
Nums1 [1] + Nums1 [2] + Nums1[3] = 1 + 0 + 1 = 2。
Nums2 [1] + Nums2 [2] + Nums2[3] = 1 + 1 + 0 = 2。
i和j之间的距离是j - i + 1 = 3 - 1 + 1 = 3。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> nums1 = [0,1], nums2 = [1,1]
<strong>输出:</strong> 1
<strong>解释:
</strong>If i = 1 and j = 1:
nums1[1] = 1。
nums2[1] = 1。
i和j之间的距离是j - i + 1 = 1 - 1 + 1 = 1。
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> nums1 = [0], nums2 = [1]
<strong>输出:</strong> 0
<strong>解释:
</strong>没有满足要求的索引对。
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>n == nums1.length == nums2.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>nums1[i]</code>&nbsp;仅为&nbsp;<code>0</code>&nbsp;或&nbsp;<code>1</code>.</li>
	<li><code>nums2[i]</code>&nbsp;仅为&nbsp;<code>0</code>&nbsp;或&nbsp;<code>1</code>.</li>
</ul>

## 解法

### 方法一：前缀和 + 哈希表

我们观察到，对于任意的索引对 $(i, j)$，如果 $nums1[i] + nums1[i+1] + ... + nums1[j] = nums2[i] + nums2[i+1] + ... + nums2[j]$，那么 $nums1[i] - nums2[i] + nums1[i+1] - nums2[i+1] + ... + nums1[j] - nums2[j] = 0$。如果我们将数组 $nums1$ 与数组 $nums2$ 对应位置的元素相减，得到一个新的数组 $nums$，那么问题转换为在数组 $nums$ 中找到一个最长的子数组，使得子数组的和为 $0$。这可以通过前缀和 + 哈希表的方法求解。

我们定义一个变量 $s$ 表示当前 $nums$ 的前缀和，用一个哈希表 $d$ 保存每个前缀和第一次出现的位置。初始时 $s = 0$, $d[0] = -1$。

接下来，我们遍历数组 $nums$ 中的每个元素 $x$，计算 $s$ 的值，然后检查哈希表中是否存在 $s$，如果哈希表存在 $s$，那么说明存在一个子数组 $nums[d[s]+1,..i]$，使得子数组的和为 $0$，我们更新答案为 $\max(ans, i - d[s])$。否则，我们将 $s$ 的值加入哈希表中，表示 $s$ 第一次出现的位置为 $i$。

遍历结束，即可得到最终的答案。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $nums$ 的长度。

<!-- tabs:start -->

```python
class Solution:
    def widestPairOfIndices(self, nums1: List[int], nums2: List[int]) -> int:
        d = {0: -1}
        ans = s = 0
        for i, (a, b) in enumerate(zip(nums1, nums2)):
            s += a - b
            if s in d:
                ans = max(ans, i - d[s])
            else:
                d[s] = i
        return ans
```

```java
class Solution {
    public int widestPairOfIndices(int[] nums1, int[] nums2) {
        Map<Integer, Integer> d = new HashMap<>();
        d.put(0, -1);
        int n = nums1.length;
        int s = 0;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            s += nums1[i] - nums2[i];
            if (d.containsKey(s)) {
                ans = Math.max(ans, i - d.get(s));
            } else {
                d.put(s, i);
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int widestPairOfIndices(vector<int>& nums1, vector<int>& nums2) {
        unordered_map<int, int> d;
        d[0] = -1;
        int ans = 0, s = 0;
        int n = nums1.size();
        for (int i = 0; i < n; ++i) {
            s += nums1[i] - nums2[i];
            if (d.count(s)) {
                ans = max(ans, i - d[s]);
            } else {
                d[s] = i;
            }
        }
        return ans;
    }
};
```

```go
func widestPairOfIndices(nums1 []int, nums2 []int) (ans int) {
	d := map[int]int{0: -1}
	s := 0
	for i := range nums1 {
		s += nums1[i] - nums2[i]
		if j, ok := d[s]; ok {
			ans = max(ans, i-j)
		} else {
			d[s] = i
		}
	}
	return
}
```

```ts
function widestPairOfIndices(nums1: number[], nums2: number[]): number {
    const d: Map<number, number> = new Map();
    d.set(0, -1);
    const n: number = nums1.length;
    let s: number = 0;
    let ans: number = 0;
    for (let i = 0; i < n; ++i) {
        s += nums1[i] - nums2[i];
        if (d.has(s)) {
            ans = Math.max(ans, i - (d.get(s) as number));
        } else {
            d.set(s, i);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
