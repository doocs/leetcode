---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2400-2499/2465.Number%20of%20Distinct%20Averages/README.md
rating: 1250
source: 第 91 场双周赛 Q1
tags:
    - 数组
    - 哈希表
    - 双指针
    - 排序
---

<!-- problem:start -->

# [2465. 不同的平均值数目](https://leetcode.cn/problems/number-of-distinct-averages)

[English Version](/solution/2400-2499/2465.Number%20of%20Distinct%20Averages/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始长度为 <strong>偶数</strong>&nbsp;的整数数组&nbsp;<code>nums</code>&nbsp;。</p>

<p>只要&nbsp;<code>nums</code> <strong>不是</strong>&nbsp;空数组，你就重复执行以下步骤：</p>

<ul>
	<li>找到&nbsp;<code>nums</code>&nbsp;中的最小值，并删除它。</li>
	<li>找到&nbsp;<code>nums</code>&nbsp;中的最大值，并删除它。</li>
	<li>计算删除两数的平均值。</li>
</ul>

<p>两数 <code>a</code>&nbsp;和 <code>b</code>&nbsp;的 <strong>平均值</strong>&nbsp;为&nbsp;<code>(a + b) / 2</code>&nbsp;。</p>

<ul>
	<li>比方说，<code>2</code>&nbsp;和&nbsp;<code>3</code>&nbsp;的平均值是&nbsp;<code>(2 + 3) / 2 = 2.5</code>&nbsp;。</li>
</ul>

<p>返回上述过程能得到的 <strong>不同</strong>&nbsp;平均值的数目。</p>

<p><strong>注意</strong>&nbsp;，如果最小值或者最大值有重复元素，可以删除任意一个。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>nums = [4,1,4,0,3,5]
<b>输出：</b>2
<strong>解释：</strong>
1. 删除 0 和 5 ，平均值是 (0 + 5) / 2 = 2.5 ，现在 nums = [4,1,4,3] 。
2. 删除 1 和 4 ，平均值是 (1 + 4) / 2 = 2.5 ，现在 nums = [4,3] 。
3. 删除 3 和 4 ，平均值是 (3 + 4) / 2 = 3.5 。
2.5 ，2.5 和 3.5 之中总共有 2 个不同的数，我们返回 2 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>nums = [1,100]
<b>输出：</b>1
<strong>解释：</strong>
删除 1 和 100 后只有一个平均值，所以我们返回 1 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 100</code></li>
	<li><code>nums.length</code>&nbsp;是偶数。</li>
	<li><code>0 &lt;= nums[i] &lt;= 100</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：排序

题目中要求每次找到数组 $nums$ 中的最小值和最大值，然后删除它们，再计算删除两数的平均值。因此，我们可以先对数组 $nums$ 进行排序，然后每次取数组的首尾元素，计算它们的和，用哈希表或数组 $cnt$ 记录每个和出现的次数，最后统计不同的和的个数即可。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 是数组 $nums$ 的长度。

<!-- tabs:start -->

```python
class Solution:
    def distinctAverages(self, nums: List[int]) -> int:
        nums.sort()
        return len(set(nums[i] + nums[-i - 1] for i in range(len(nums) >> 1)))
```

```java
class Solution {
    public int distinctAverages(int[] nums) {
        Arrays.sort(nums);
        Set<Integer> s = new HashSet<>();
        int n = nums.length;
        for (int i = 0; i < n >> 1; ++i) {
            s.add(nums[i] + nums[n - i - 1]);
        }
        return s.size();
    }
}
```

```cpp
class Solution {
public:
    int distinctAverages(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        unordered_set<int> s;
        int n = nums.size();
        for (int i = 0; i < n >> 1; ++i) {
            s.insert(nums[i] + nums[n - i - 1]);
        }
        return s.size();
    }
};
```

```go
func distinctAverages(nums []int) (ans int) {
	sort.Ints(nums)
	n := len(nums)
	s := map[int]struct{}{}
	for i := 0; i < n>>1; i++ {
		s[nums[i]+nums[n-i-1]] = struct{}{}
	}
	return len(s)
}
```

```ts
function distinctAverages(nums: number[]): number {
    nums.sort((a, b) => a - b);
    const s: Set<number> = new Set();
    const n = nums.length;
    for (let i = 0; i < n >> 1; ++i) {
        s.add(nums[i] + nums[n - i - 1]);
    }
    return s.size;
}
```

```rust
impl Solution {
    pub fn distinct_averages(nums: Vec<i32>) -> i32 {
        let mut nums = nums;
        nums.sort();
        let n = nums.len();
        let mut cnt = vec![0; 201];
        let mut ans = 0;

        for i in 0..n >> 1 {
            let x = (nums[i] + nums[n - i - 1]) as usize;
            cnt[x] += 1;

            if cnt[x] == 1 {
                ans += 1;
            }
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二

<!-- tabs:start -->

```python
class Solution:
    def distinctAverages(self, nums: List[int]) -> int:
        nums.sort()
        ans = 0
        cnt = Counter()
        for i in range(len(nums) >> 1):
            x = nums[i] + nums[-i - 1]
            cnt[x] += 1
            if cnt[x] == 1:
                ans += 1
        return ans
```

```java
class Solution {
    public int distinctAverages(int[] nums) {
        Arrays.sort(nums);
        int[] cnt = new int[201];
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < n >> 1; ++i) {
            if (++cnt[nums[i] + nums[n - i - 1]] == 1) {
                ++ans;
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int distinctAverages(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        int cnt[201]{};
        int n = nums.size();
        int ans = 0;
        for (int i = 0; i < n >> 1; ++i) {
            if (++cnt[nums[i] + nums[n - i - 1]] == 1) {
                ++ans;
            }
        }
        return ans;
    }
};
```

```go
func distinctAverages(nums []int) (ans int) {
	sort.Ints(nums)
	n := len(nums)
	cnt := [201]int{}
	for i := 0; i < n>>1; i++ {
		x := nums[i] + nums[n-i-1]
		cnt[x]++
		if cnt[x] == 1 {
			ans++
		}
	}
	return
}
```

```ts
function distinctAverages(nums: number[]): number {
    nums.sort((a, b) => a - b);
    const cnt: number[] = Array(201).fill(0);
    let ans = 0;
    const n = nums.length;
    for (let i = 0; i < n >> 1; ++i) {
        if (++cnt[nums[i] + nums[n - i - 1]] === 1) {
            ++ans;
        }
    }
    return ans;
}
```

```rust
use std::collections::HashMap;

impl Solution {
    pub fn distinct_averages(nums: Vec<i32>) -> i32 {
        let mut h = HashMap::new();
        let mut nums = nums;
        let mut ans = 0;
        let n = nums.len();
        nums.sort();

        for i in 0..n >> 1 {
            let x = nums[i] + nums[n - i - 1];
            *h.entry(x).or_insert(0) += 1;

            if *h.get(&x).unwrap() == 1 {
                ans += 1;
            }
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法三

<!-- tabs:start -->

```rust
use std::collections::HashSet;

impl Solution {
    pub fn distinct_averages(nums: Vec<i32>) -> i32 {
        let mut set = HashSet::new();
        let mut ans = 0;
        let n = nums.len();
        let mut nums = nums;
        nums.sort();

        for i in 0..n >> 1 {
            let x = nums[i] + nums[n - i - 1];

            if set.contains(&x) {
                continue;
            }

            set.insert(x);
            ans += 1;
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
