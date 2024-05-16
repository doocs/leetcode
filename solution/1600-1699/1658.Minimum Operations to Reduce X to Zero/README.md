---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1600-1699/1658.Minimum%20Operations%20to%20Reduce%20X%20to%20Zero/README.md
rating: 1817
source: 第 215 场周赛 Q3
tags:
    - 数组
    - 哈希表
    - 二分查找
    - 前缀和
    - 滑动窗口
---

<!-- problem:start -->

# [1658. 将 x 减到 0 的最小操作数](https://leetcode.cn/problems/minimum-operations-to-reduce-x-to-zero)

[English Version](/solution/1600-1699/1658.Minimum%20Operations%20to%20Reduce%20X%20to%20Zero/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code> 和一个整数 <code>x</code> 。每一次操作时，你应当移除数组 <code>nums</code> 最左边或最右边的元素，然后从 <code>x</code> 中减去该元素的值。请注意，需要 <strong>修改</strong> 数组以供接下来的操作使用。</p>

<p>如果可以将 <code>x</code> <strong>恰好</strong> 减到 <code>0</code> ，返回<strong> 最小操作数 </strong>；否则，返回 <code>-1</code> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,1,4,2,3], x = 5
<strong>输出：</strong>2
<strong>解释：</strong>最佳解决方案是移除后两个元素，将 x 减到 0 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [5,6,7,8,9], x = 4
<strong>输出：</strong>-1
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [3,2,20,1,1,3], x = 10
<strong>输出：</strong>5
<strong>解释：</strong>最佳解决方案是移除后三个元素和前两个元素（总共 5 次操作），将 x 减到 0 。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= nums.length <= 10<sup>5</sup></code></li>
	<li><code>1 <= nums[i] <= 10<sup>4</sup></code></li>
	<li><code>1 <= x <= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表 + 前缀和

根据题目描述，我们需要移除数组 $nums$ 左右两端的元素，使得移除的元素和等于 $x$，且移除的元素个数最少。我们可以将问题转化为：找到数组 $nums$ 中最长的连续子数组，使得子数组的和 $s = \sum_{i=0}^{n} nums[i] - x$。这样，我们就可以将问题转化为求解数组 $nums$ 中和为 $s$ 的最长连续子数组的长度 $mx$，答案即为 $n - mx$。

我们初始化 $mx = -1$，然后使用哈希表 $vis$ 来存储前缀和，键为前缀和，值为前缀和对应的下标。

遍历数组 $nums$，对于当前元素 $nums[i]$，计算前缀和 $t$，如果 $t$ 不在哈希表中，则将 $t$ 加入哈希表；如果 $t - s$ 在哈希表中，则更新 $mx = \max(mx, i - vis[t - s])$。

最后，如果 $mx = -1$，则返回 $-1$，否则返回 $n - mx$。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $nums$ 的长度。

<!-- tabs:start -->

```python
class Solution:
    def minOperations(self, nums: List[int], x: int) -> int:
        s = sum(nums) - x
        vis = {0: -1}
        mx, t = -1, 0
        for i, v in enumerate(nums):
            t += v
            if t not in vis:
                vis[t] = i
            if t - s in vis:
                mx = max(mx, i - vis[t - s])
        return -1 if mx == -1 else len(nums) - mx
```

```java
class Solution {
    public int minOperations(int[] nums, int x) {
        int s = -x;
        for (int v : nums) {
            s += v;
        }
        Map<Integer, Integer> vis = new HashMap<>();
        vis.put(0, -1);
        int mx = -1, t = 0;
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            t += nums[i];
            vis.putIfAbsent(t, i);
            if (vis.containsKey(t - s)) {
                mx = Math.max(mx, i - vis.get(t - s));
            }
        }
        return mx == -1 ? -1 : n - mx;
    }
}
```

```cpp
class Solution {
public:
    int minOperations(vector<int>& nums, int x) {
        int s = accumulate(nums.begin(), nums.end(), 0) - x;
        unordered_map<int, int> vis = {{0, -1}};
        int mx = -1, t = 0;
        int n = nums.size();
        for (int i = 0; i < n; ++i) {
            t += nums[i];
            if (!vis.contains(t)) {
                vis[t] = i;
            }
            if (vis.contains(t - s)) {
                mx = max(mx, i - vis[t - s]);
            }
        }
        return mx == -1 ? -1 : n - mx;
    }
};
```

```go
func minOperations(nums []int, x int) int {
	s := -x
	for _, v := range nums {
		s += v
	}
	vis := map[int]int{0: -1}
	mx, t := -1, 0
	for i, v := range nums {
		t += v
		if _, ok := vis[t]; !ok {
			vis[t] = i
		}
		if j, ok := vis[t-s]; ok {
			mx = max(mx, i-j)
		}
	}
	if mx == -1 {
		return -1
	}
	return len(nums) - mx
}
```

```ts
function minOperations(nums: number[], x: number): number {
    const s = nums.reduce((acc, cur) => acc + cur, -x);
    const vis: Map<number, number> = new Map([[0, -1]]);
    let [mx, t] = [-1, 0];
    const n = nums.length;
    for (let i = 0; i < n; ++i) {
        t += nums[i];
        if (!vis.has(t)) {
            vis.set(t, i);
        }
        if (vis.has(t - s)) {
            mx = Math.max(mx, i - vis.get(t - s)!);
        }
    }
    return ~mx ? n - mx : -1;
}
```

```rust
use std::collections::HashMap;

impl Solution {
    pub fn min_operations(nums: Vec<i32>, x: i32) -> i32 {
        let s = nums.iter().sum::<i32>() - x;
        let mut vis: HashMap<i32, i32> = HashMap::new();
        vis.insert(0, -1);
        let mut mx = -1;
        let mut t = 0;
        for (i, v) in nums.iter().enumerate() {
            t += v;
            if !vis.contains_key(&t) {
                vis.insert(t, i as i32);
            }
            if let Some(&j) = vis.get(&(t - s)) {
                mx = mx.max((i as i32) - j);
            }
        }
        if mx == -1 {
            -1
        } else {
            (nums.len() as i32) - mx
        }
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：双指针

基于方法一的分析，我们需要求解数组 $nums$ 中和为 $s$ 的最长连续子数组的长度 $mx$。由于数组 $nums$ 中的元素都是正整数，数组的前缀和只会单调递增，因此我们可以使用双指针来求解。

我们初始化指针 $j = 0$，前缀和 $t = 0$，最长连续子数组的长度 $mx = -1$。

遍历数组 $nums$，对于当前元素 $nums[i]$，计算前缀和 $t += nums[i]$，如果 $t > s$，则移动指针 $j$，直到 $t \leq s$。如果 $t = s$，则更新 $mx = \max(mx, i - j + 1)$。

最后，如果 $mx = -1$，则返回 $-1$，否则返回 $n - mx$。

时间复杂度 $O(n)$，其中 $n$ 为数组 $nums$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def minOperations(self, nums: List[int], x: int) -> int:
        s = sum(nums) - x
        j = t = 0
        mx = -1
        for i, x in enumerate(nums):
            t += x
            while j <= i and t > s:
                t -= nums[j]
                j += 1
            if t == s:
                mx = max(mx, i - j + 1)
        return -1 if mx == -1 else len(nums) - mx
```

```java
class Solution {
    public int minOperations(int[] nums, int x) {
        int s = -x;
        for (int v : nums) {
            s += v;
        }
        int mx = -1, t = 0;
        int n = nums.length;
        for (int i = 0, j = 0; i < n; ++i) {
            t += nums[i];
            while (j <= i && t > s) {
                t -= nums[j++];
            }
            if (t == s) {
                mx = Math.max(mx, i - j + 1);
            }
        }
        return mx == -1 ? -1 : n - mx;
    }
}
```

```cpp
class Solution {
public:
    int minOperations(vector<int>& nums, int x) {
        int s = accumulate(nums.begin(), nums.end(), 0) - x;
        int mx = -1, t = 0;
        int n = nums.size();
        for (int i = 0, j = 0; i < n; ++i) {
            t += nums[i];
            while (j <= i && t > s) {
                t -= nums[j++];
            }
            if (t == s) {
                mx = max(mx, i - j + 1);
            }
        }
        return mx == -1 ? -1 : n - mx;
    }
};
```

```go
func minOperations(nums []int, x int) int {
	s := -x
	for _, v := range nums {
		s += v
	}
	mx, t, j := -1, 0, 0
	for i, v := range nums {
		t += v
		for ; j <= i && t > s; j++ {
			t -= nums[j]
		}
		if t == s {
			mx = max(mx, i-j+1)
		}
	}
	if mx == -1 {
		return -1
	}
	return len(nums) - mx
}
```

```ts
function minOperations(nums: number[], x: number): number {
    const s = nums.reduce((acc, cur) => acc + cur, -x);
    let [mx, t] = [-1, 0];
    const n = nums.length;
    for (let i = 0, j = 0; i < n; ++i) {
        t += nums[i];
        while (t > s) {
            t -= nums[j++];
        }
        if (t === s) {
            mx = Math.max(mx, i - j + 1);
        }
    }
    return ~mx ? n - mx : -1;
}
```

```rust
impl Solution {
    pub fn min_operations(nums: Vec<i32>, x: i32) -> i32 {
        let s: i32 = nums.iter().sum::<i32>() - x;
        let mut j: usize = 0;
        let mut t: i32 = 0;
        let mut mx: i32 = -1;
        for (i, &v) in nums.iter().enumerate() {
            t += v;
            while j <= i && t > s {
                t -= nums[j];
                j += 1;
            }
            if t == s {
                mx = mx.max((i - j + 1) as i32);
            }
        }
        if mx == -1 {
            -1
        } else {
            (nums.len() as i32) - mx
        }
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
