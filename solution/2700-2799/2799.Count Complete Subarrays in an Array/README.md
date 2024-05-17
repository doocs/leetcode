---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2799.Count%20Complete%20Subarrays%20in%20an%20Array/README.md
rating: 1397
source: 第 356 场周赛 Q2
tags:
    - 数组
    - 哈希表
    - 滑动窗口
---

<!-- problem:start -->

# [2799. 统计完全子数组的数目](https://leetcode.cn/problems/count-complete-subarrays-in-an-array)

[English Version](/solution/2700-2799/2799.Count%20Complete%20Subarrays%20in%20an%20Array/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个由 <strong>正</strong> 整数组成的数组 <code>nums</code> 。</p>

<p>如果数组中的某个子数组满足下述条件，则称之为 <strong>完全子数组</strong> ：</p>

<ul>
	<li>子数组中 <strong>不同</strong> 元素的数目等于整个数组不同元素的数目。</li>
</ul>

<p>返回数组中 <strong>完全子数组</strong> 的数目。</p>

<p><strong>子数组</strong> 是数组中的一个连续非空序列。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums = [1,3,1,2,2]
<strong>输出：</strong>4
<strong>解释：</strong>完全子数组有：[1,3,1,2]、[1,3,1,2,2]、[3,1,2] 和 [3,1,2,2] 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums = [5,5,5,5]
<strong>输出：</strong>10
<strong>解释：</strong>数组仅由整数 5 组成，所以任意子数组都满足完全子数组的条件。子数组的总数为 10 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 2000</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表 + 枚举

我们先用哈希表统计数组中不同元素的数目，记为 $cnt$。

接下来，我们枚举子数组的左端点下标 $i$，并维护一个集合 $s$，用于存储子数组中的元素。每次向右移动右端点下标 $j$ 时，我们将 $nums[j]$ 加入集合 $s$ 中，并判断集合 $s$ 的大小是否等于 $cnt$。如果等于 $cnt$，则说明当前子数组是完全子数组，我们将答案增加 $1$。

枚举结束后，返回答案即可。

时间复杂度 $O(n^2)$，空间复杂度 $O(n)$。其中 $n$ 是数组的长度。

<!-- tabs:start -->

```python
class Solution:
    def countCompleteSubarrays(self, nums: List[int]) -> int:
        cnt = len(set(nums))
        ans, n = 0, len(nums)
        for i in range(n):
            s = set()
            for x in nums[i:]:
                s.add(x)
                if len(s) == cnt:
                    ans += 1
        return ans
```

```java
class Solution {
    public int countCompleteSubarrays(int[] nums) {
        Set<Integer> s = new HashSet<>();
        for (int x : nums) {
            s.add(x);
        }
        int cnt = s.size();
        int ans = 0, n = nums.length;
        for (int i = 0; i < n; ++i) {
            s.clear();
            for (int j = i; j < n; ++j) {
                s.add(nums[j]);
                if (s.size() == cnt) {
                    ++ans;
                }
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int countCompleteSubarrays(vector<int>& nums) {
        unordered_set<int> s(nums.begin(), nums.end());
        int cnt = s.size();
        int ans = 0, n = nums.size();
        for (int i = 0; i < n; ++i) {
            s.clear();
            for (int j = i; j < n; ++j) {
                s.insert(nums[j]);
                if (s.size() == cnt) {
                    ++ans;
                }
            }
        }
        return ans;
    }
};
```

```go
func countCompleteSubarrays(nums []int) (ans int) {
	s := map[int]bool{}
	for _, x := range nums {
		s[x] = true
	}
	cnt := len(s)
	for i := range nums {
		s = map[int]bool{}
		for _, x := range nums[i:] {
			s[x] = true
			if len(s) == cnt {
				ans++
			}
		}
	}
	return
}
```

```ts
function countCompleteSubarrays(nums: number[]): number {
    const s: Set<number> = new Set(nums);
    const cnt = s.size;
    const n = nums.length;
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        s.clear();
        for (let j = i; j < n; ++j) {
            s.add(nums[j]);
            if (s.size === cnt) {
                ++ans;
            }
        }
    }
    return ans;
}
```

```rust
use std::collections::HashSet;
impl Solution {
    pub fn count_complete_subarrays(nums: Vec<i32>) -> i32 {
        let mut set: HashSet<&i32> = nums.iter().collect();
        let n = nums.len();
        let m = set.len();
        let mut ans = 0;
        for i in 0..n {
            set.clear();
            for j in i..n {
                set.insert(&nums[j]);
                if set.len() == m {
                    ans += n - j;
                    break;
                }
            }
        }
        ans as i32
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：哈希表 + 双指针

与方法一类似，我们可以使用哈希表统计数组中不同元素的数目，记为 $cnt$。

接下来，我们使用双指针维护一个滑动窗口，滑动窗口的右端点下标为 $j$，左端点下标为 $i$。

每次固定左端点下标 $i$，然后向右移动右端点下标 $j$，当滑动窗口中的元素种类数等于 $cnt$ 时，这意味着从左端点下标 $i$ 到右端点下标 $j$ 以及右侧的所有子数组都是完全子数组，我们将答案增加 $n - j$，其中 $n$ 是数组的长度。然后我们将左端点下标 $i$ 右移一位，继续上述过程。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是数组的长度。

<!-- tabs:start -->

```python
class Solution:
    def countCompleteSubarrays(self, nums: List[int]) -> int:
        cnt = len(set(nums))
        d = Counter()
        ans, n = 0, len(nums)
        i = 0
        for j, x in enumerate(nums):
            d[x] += 1
            while len(d) == cnt:
                ans += n - j
                d[nums[i]] -= 1
                if d[nums[i]] == 0:
                    d.pop(nums[i])
                i += 1
        return ans
```

```java
class Solution {
    public int countCompleteSubarrays(int[] nums) {
        Map<Integer, Integer> d = new HashMap<>();
        for (int x : nums) {
            d.put(x, 1);
        }
        int cnt = d.size();
        int ans = 0, n = nums.length;
        d.clear();
        for (int i = 0, j = 0; j < n; ++j) {
            d.merge(nums[j], 1, Integer::sum);
            while (d.size() == cnt) {
                ans += n - j;
                if (d.merge(nums[i], -1, Integer::sum) == 0) {
                    d.remove(nums[i]);
                }
                ++i;
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int countCompleteSubarrays(vector<int>& nums) {
        unordered_map<int, int> d;
        for (int x : nums) {
            d[x] = 1;
        }
        int cnt = d.size();
        d.clear();
        int ans = 0, n = nums.size();
        for (int i = 0, j = 0; j < n; ++j) {
            d[nums[j]]++;
            while (d.size() == cnt) {
                ans += n - j;
                if (--d[nums[i]] == 0) {
                    d.erase(nums[i]);
                }
                ++i;
            }
        }
        return ans;
    }
};
```

```go
func countCompleteSubarrays(nums []int) (ans int) {
	d := map[int]int{}
	for _, x := range nums {
		d[x] = 1
	}
	cnt := len(d)
	i, n := 0, len(nums)
	d = map[int]int{}
	for j, x := range nums {
		d[x]++
		for len(d) == cnt {
			ans += n - j
			d[nums[i]]--
			if d[nums[i]] == 0 {
				delete(d, nums[i])
			}
			i++
		}
	}
	return
}
```

```ts
function countCompleteSubarrays(nums: number[]): number {
    const d: Map<number, number> = new Map();
    for (const x of nums) {
        d.set(x, (d.get(x) ?? 0) + 1);
    }
    const cnt = d.size;
    d.clear();
    const n = nums.length;
    let ans = 0;
    let i = 0;
    for (let j = 0; j < n; ++j) {
        d.set(nums[j], (d.get(nums[j]) ?? 0) + 1);
        while (d.size === cnt) {
            ans += n - j;
            d.set(nums[i], d.get(nums[i])! - 1);
            if (d.get(nums[i]) === 0) {
                d.delete(nums[i]);
            }
            ++i;
        }
    }
    return ans;
}
```

```rust
use std::collections::HashMap;
use std::collections::HashSet;
impl Solution {
    pub fn count_complete_subarrays(nums: Vec<i32>) -> i32 {
        let n = nums.len();
        let m = nums.iter().collect::<HashSet<&i32>>().len();
        let mut map = HashMap::new();
        let mut ans = 0;
        let mut i = 0;
        for j in 0..n {
            *map.entry(nums[j]).or_insert(0) += 1;
            while map.len() == m {
                ans += n - j;
                let v = map.entry(nums[i]).or_default();
                *v -= 1;
                if *v == 0 {
                    map.remove(&nums[i]);
                }
                i += 1;
            }
        }
        ans as i32
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
