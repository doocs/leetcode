---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2800-2899/2863.Maximum%20Length%20of%20Semi-Decreasing%20Subarrays/README.md
tags:
    - 数组
    - 哈希表
    - 排序
---

# [2863. 最长半递减数组 🔒](https://leetcode.cn/problems/maximum-length-of-semi-decreasing-subarrays)

[English Version](/solution/2800-2899/2863.Maximum%20Length%20of%20Semi-Decreasing%20Subarrays/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个整数数组 <code>nums</code>。</p>

<p>返回 <code>nums</code> 的&nbsp;<em><strong>最长半递减子数组&nbsp;</strong></em>的长度，如果没有这样的子数组则返回 <code>0</code>。</p>

<ul>
	<li><strong>子数组</strong> 是数组内的连续非空元素序列。</li>
	<li>一个非空数组是 <strong>半递减</strong> 的，如果它的第一个元素 <strong>严格大于</strong> 它的最后一个元素。</li>
</ul>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong> nums = [7,6,5,4,3,2,1,6,10,11]
<b>输出：</b> 8
<b>解释：</b> 取子数组 [7,6,5,4,3,2,1,6]。
第一个元素是 7，最后一个元素是 6，因此满足条件。
因此，答案是子数组的长度，即 8。
可以证明，在给定条件下，没有长度大于 8 的子数组。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b> nums = [57,55,50,60,61,58,63,59,64,60,63]
<b>输出：</b> 6
<b>解释：</b> 取子数组 [61,58,63,59,64,60].
第一个元素是 61，最后一个元素是 60，因此满足条件。
因此，答案是子数组的长度，即 6。
可以证明，在给定条件下，没有长度大于 6 的子数组。
</pre>

<p><b>示例 3:</b></p>

<pre>
<b>输入：</b> nums = [1,2,3,4]
<b>输出：</b> 0
<b>解释：</b> 由于给定数组中没有半递减子数组，答案为 0。
</pre>

<p>&nbsp;</p>

<p><b>提示：</b></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

### 方法一：哈希表 + 排序

题目实际上是求逆序对的最大长度，我们不妨用哈希表 $d$ 记录数组中每个数字 $x$ 对应的下标 $i$。

接下来，我们按照数字从大到小的顺序遍历哈希表的键，用一个数字 $k$ 维护此前出现过的最小的下标，那么对于当前的数字 $x$，我们可以得到一个最大的逆序对长度 $d[x][|d[x]|-1]-k + 1$，其中 $|d[x]|$ 表示数组 $d[x]$ 的长度，即数字 $x$ 在原数组中出现的次数，我们更新答案即可。然后，我们将 $k$ 更新为 $d[x][0]$，即数字 $x$ 在原数组中第一次出现的下标。继续遍历哈希表的键，直到遍历完所有的键。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 是数组 $nums$ 的长度。

<!-- tabs:start -->

```python
class Solution:
    def maxSubarrayLength(self, nums: List[int]) -> int:
        d = defaultdict(list)
        for i, x in enumerate(nums):
            d[x].append(i)
        ans, k = 0, inf
        for x in sorted(d, reverse=True):
            ans = max(ans, d[x][-1] - k + 1)
            k = min(k, d[x][0])
        return ans
```

```java
class Solution {
    public int maxSubarrayLength(int[] nums) {
        TreeMap<Integer, List<Integer>> d = new TreeMap<>(Comparator.reverseOrder());
        for (int i = 0; i < nums.length; ++i) {
            d.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }
        int ans = 0, k = 1 << 30;
        for (List<Integer> idx : d.values()) {
            ans = Math.max(ans, idx.get(idx.size() - 1) - k + 1);
            k = Math.min(k, idx.get(0));
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int maxSubarrayLength(vector<int>& nums) {
        map<int, vector<int>, greater<int>> d;
        for (int i = 0; i < nums.size(); ++i) {
            d[nums[i]].push_back(i);
        }
        int ans = 0, k = 1 << 30;
        for (auto& [_, idx] : d) {
            ans = max(ans, idx.back() - k + 1);
            k = min(k, idx[0]);
        }
        return ans;
    }
};
```

```go
func maxSubarrayLength(nums []int) (ans int) {
	d := map[int][]int{}
	for i, x := range nums {
		d[x] = append(d[x], i)
	}
	keys := []int{}
	for x := range d {
		keys = append(keys, x)
	}
	sort.Slice(keys, func(i, j int) bool { return keys[i] > keys[j] })
	k := 1 << 30
	for _, x := range keys {
		idx := d[x]
		ans = max(ans, idx[len(idx)-1]-k+1)
		k = min(k, idx[0])
	}
	return ans
}
```

```ts
function maxSubarrayLength(nums: number[]): number {
    const d: Map<number, number[]> = new Map();
    for (let i = 0; i < nums.length; ++i) {
        if (!d.has(nums[i])) {
            d.set(nums[i], []);
        }
        d.get(nums[i])!.push(i);
    }
    const keys = Array.from(d.keys()).sort((a, b) => b - a);
    let ans = 0;
    let k = Infinity;
    for (const x of keys) {
        const idx = d.get(x)!;
        ans = Math.max(ans, idx.at(-1) - k + 1);
        k = Math.min(k, idx[0]);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
