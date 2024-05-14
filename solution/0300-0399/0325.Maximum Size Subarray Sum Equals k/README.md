# [325. 和等于 k 的最长子数组长度 🔒](https://leetcode.cn/problems/maximum-size-subarray-sum-equals-k)

[English Version](/solution/0300-0399/0325.Maximum%20Size%20Subarray%20Sum%20Equals%20k/README_EN.md)

<!-- tags:数组,哈希表,前缀和 -->

<!-- difficulty:中等 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个数组 <code><em>nums</em></code> 和一个目标值 <code><em>k</em></code>，找到和等于<em> <code>k</code> </em>的最长连续<span data-keyword="subarray">子数组</span>长度。如果不存在任意一个符合要求的子数组，则返回 <code>0</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入: </strong><em>nums</em> = <code>[1,-1,5,-2,3]</code>, <em>k</em> = <code>3</code>
<strong>输出: </strong>4 
<strong>解释: </strong>子数组 <code>[1, -1, 5, -2]</code> 和等于 3，且长度最长。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入: </strong><em>nums</em> = <code>[-2,-1,2,1]</code>, <em>k</em> = <code>1</code>
<strong>输出: </strong>2 <strong>
解释: </strong>子数组<code> [-1, 2]</code> 和等于 1，且长度最长。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 2 * 10<sup>5</sup></code></li>
	<li><code>-10<sup>4</sup>&nbsp;&lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
	<li><code>-10<sup>9</sup>&nbsp;&lt;= k &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

### 方法一：哈希表 + 前缀和

我们可以用一个哈希表 $d$ 记录数组 $nums$ 中每个前缀和第一次出现的下标，初始时 $d[0] = -1$。另外定义一个变量 $s$ 记录前缀和。

接下来，遍历数组 $nums$，对于当前遍历到的数字 $nums[i]$，我们更新前缀和 $s = s + nums[i]$，如果 $s-k$ 在哈希表 $d$ 中存在，不妨记 $j = d[s - k]$，那么以 $nums[i]$ 结尾的符合条件的子数组的长度为 $i - j$，我们使用一个变量 $ans$ 来维护最长的符合条件的子数组的长度。然后，如果 $s$ 在哈希表中不存在，我们记录 $s$ 和对应的下标 $i$，即 $d[s] = i$，否则我们不更新 $d[s]$。需要注意的是，可能会有多个位置 $i$ 都满足 $s$ 的值，因此我们只记录最小的 $i$，这样就能保证子数组的长度最长。

遍历结束之后，我们返回 $ans$ 即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是数组 $nums$ 的长度。

<!-- tabs:start -->

```python
class Solution:
    def maxSubArrayLen(self, nums: List[int], k: int) -> int:
        d = {0: -1}
        ans = s = 0
        for i, x in enumerate(nums):
            s += x
            if s - k in d:
                ans = max(ans, i - d[s - k])
            if s not in d:
                d[s] = i
        return ans
```

```java
class Solution {
    public int maxSubArrayLen(int[] nums, int k) {
        Map<Long, Integer> d = new HashMap<>();
        d.put(0L, -1);
        int ans = 0;
        long s = 0;
        for (int i = 0; i < nums.length; ++i) {
            s += nums[i];
            ans = Math.max(ans, i - d.getOrDefault(s - k, i));
            d.putIfAbsent(s, i);
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int maxSubArrayLen(vector<int>& nums, int k) {
        unordered_map<long long, int> d{{0, -1}};
        int ans = 0;
        long long s = 0;
        for (int i = 0; i < nums.size(); ++i) {
            s += nums[i];
            if (d.count(s - k)) {
                ans = max(ans, i - d[s - k]);
            }
            if (!d.count(s)) {
                d[s] = i;
            }
        }
        return ans;
    }
};
```

```go
func maxSubArrayLen(nums []int, k int) (ans int) {
	d := map[int]int{0: -1}
	s := 0
	for i, x := range nums {
		s += x
		if j, ok := d[s-k]; ok && ans < i-j {
			ans = i - j
		}
		if _, ok := d[s]; !ok {
			d[s] = i
		}
	}
	return
}
```

```ts
function maxSubArrayLen(nums: number[], k: number): number {
    const d: Map<number, number> = new Map();
    d.set(0, -1);
    let ans = 0;
    let s = 0;
    for (let i = 0; i < nums.length; ++i) {
        s += nums[i];
        if (d.has(s - k)) {
            ans = Math.max(ans, i - d.get(s - k)!);
        }
        if (!d.has(s)) {
            d.set(s, i);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
