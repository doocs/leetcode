---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3346.Maximum%20Frequency%20of%20an%20Element%20After%20Performing%20Operations%20I/README.md
rating: 1864
source: 第 143 场双周赛 Q2
tags:
    - 数组
    - 二分查找
    - 前缀和
    - 排序
    - 滑动窗口
---

<!-- problem:start -->

# [3346. 执行操作后元素的最高频率 I](https://leetcode.cn/problems/maximum-frequency-of-an-element-after-performing-operations-i)

[English Version](/solution/3300-3399/3346.Maximum%20Frequency%20of%20an%20Element%20After%20Performing%20Operations%20I/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组&nbsp;<code>nums</code>&nbsp;和两个整数&nbsp;<code>k</code> 和&nbsp;<code>numOperations</code>&nbsp;。</p>

<p>你必须对 <code>nums</code>&nbsp;执行 <strong>操作</strong>&nbsp; <code>numOperations</code>&nbsp;次。每次操作中，你可以：</p>

<ul>
	<li>选择一个下标&nbsp;<code>i</code>&nbsp;，它在之前的操作中 <strong>没有</strong>&nbsp;被选择过。</li>
	<li>将 <code>nums[i]</code>&nbsp;增加范围&nbsp;<code>[-k, k]</code>&nbsp;中的一个整数。</li>
</ul>

<p>在执行完所有操作以后，请你返回 <code>nums</code>&nbsp;中出现 <strong>频率最高</strong>&nbsp;元素的出现次数。</p>

<p>一个元素 <code>x</code>&nbsp;的 <strong>频率</strong>&nbsp;指的是它在数组中出现的次数。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [1,4,5], k = 1, numOperations = 2</span></p>

<p><span class="example-io"><b>输出：</b>2</span></p>

<p><strong>解释：</strong></p>

<p>通过以下操作得到最高频率 2 ：</p>

<ul>
	<li>将&nbsp;<code>nums[1]</code>&nbsp;增加 0 ，<code>nums</code> 变为&nbsp;<code>[1, 4, 5]</code>&nbsp;。</li>
	<li>将&nbsp;<code>nums[2]</code>&nbsp;增加 -1 ，<code>nums</code> 变为&nbsp;<code>[1, 4, 4]</code>&nbsp;。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [5,11,20,20], k = 5, numOperations = 1</span></p>

<p><span class="example-io"><b>输出：</b>2</span></p>

<p><strong>解释：</strong></p>

<p>通过以下操作得到最高频率 2 ：</p>

<ul>
	<li>将 <code>nums[1]</code> 增加 0 。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= k &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= numOperations &lt;= nums.length</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxFrequency(self, nums: List[int], k: int, numOperations: int) -> int:
        cnt = defaultdict(int)
        d = defaultdict(int)
        for x in nums:
            cnt[x] += 1
            d[x] += 0
            d[x - k] += 1
            d[x + k + 1] -= 1
        ans = s = 0
        for x, t in sorted(d.items()):
            s += t
            ans = max(ans, min(s, cnt[x] + numOperations))
        return ans
```

#### Java

```java
class Solution {
    public int maxFrequency(int[] nums, int k, int numOperations) {
        Map<Integer, Integer> cnt = new HashMap<>();
        TreeMap<Integer, Integer> d = new TreeMap<>();
        for (int x : nums) {
            cnt.merge(x, 1, Integer::sum);
            d.putIfAbsent(x, 0);
            d.merge(x - k, 1, Integer::sum);
            d.merge(x + k + 1, -1, Integer::sum);
        }
        int ans = 0, s = 0;
        for (var e : d.entrySet()) {
            int x = e.getKey(), t = e.getValue();
            s += t;
            ans = Math.max(ans, Math.min(s, cnt.getOrDefault(x, 0) + numOperations));
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxFrequency(vector<int>& nums, int k, int numOperations) {
        unordered_map<int, int> cnt;
        map<int, int> d;

        for (int x : nums) {
            cnt[x]++;
            d[x];
            d[x - k]++;
            d[x + k + 1]--;
        }

        int ans = 0, s = 0;
        for (const auto& [x, t] : d) {
            s += t;
            ans = max(ans, min(s, cnt[x] + numOperations));
        }

        return ans;
    }
};
```

#### Go

```go
func maxFrequency(nums []int, k int, numOperations int) (ans int) {
	cnt := make(map[int]int)
	d := make(map[int]int)
	for _, x := range nums {
		cnt[x]++
		d[x] = d[x]
		d[x-k]++
		d[x+k+1]--
	}

	s := 0
	keys := make([]int, 0, len(d))
	for key := range d {
		keys = append(keys, key)
	}
	sort.Ints(keys)
	for _, x := range keys {
		s += d[x]
		ans = max(ans, min(s, cnt[x]+numOperations))
	}

	return
}
```

#### TypeScript

```ts
function maxFrequency(nums: number[], k: number, numOperations: number): number {
    const cnt: Record<number, number> = {};
    const d: Record<number, number> = {};
    for (const x of nums) {
        cnt[x] = (cnt[x] || 0) + 1;
        d[x] = d[x] || 0;
        d[x - k] = (d[x - k] || 0) + 1;
        d[x + k + 1] = (d[x + k + 1] || 0) - 1;
    }
    let [ans, s] = [0, 0];
    const keys = Object.keys(d)
        .map(Number)
        .sort((a, b) => a - b);
    for (const x of keys) {
        s += d[x];
        ans = Math.max(ans, Math.min(s, (cnt[x] || 0) + numOperations));
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
