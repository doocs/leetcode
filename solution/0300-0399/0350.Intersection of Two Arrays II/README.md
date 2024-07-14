---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0300-0399/0350.Intersection%20of%20Two%20Arrays%20II/README.md
tags:
    - 数组
    - 哈希表
    - 双指针
    - 二分查找
    - 排序
---

<!-- problem:start -->

# [350. 两个数组的交集 II](https://leetcode.cn/problems/intersection-of-two-arrays-ii)

[English Version](/solution/0300-0399/0350.Intersection%20of%20Two%20Arrays%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个整数数组&nbsp;<code>nums1</code> 和 <code>nums2</code> ，请你以数组形式返回两数组的交集。返回结果中每个元素出现的次数，应与元素在两个数组中都出现的次数一致（如果出现次数不一致，则考虑取较小值）。可以不考虑输出结果的顺序。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums1 = [1,2,2,1], nums2 = [2,2]
<strong>输出：</strong>[2,2]
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入：</strong>nums1 = [4,9,5], nums2 = [9,4,9,8,4]
<strong>输出：</strong>[4,9]</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums1.length, nums2.length &lt;= 1000</code></li>
	<li><code>0 &lt;= nums1[i], nums2[i] &lt;= 1000</code></li>
</ul>

<p>&nbsp;</p>

<p><strong><strong>进阶</strong>：</strong></p>

<ul>
	<li>如果给定的数组已经排好序呢？你将如何优化你的算法？</li>
	<li>如果&nbsp;<code>nums1</code><em>&nbsp;</em>的大小比&nbsp;<code>nums2</code> 小，哪种方法更优？</li>
	<li>如果&nbsp;<code>nums2</code><em>&nbsp;</em>的元素存储在磁盘上，内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表

我们可以用一个哈希表 $\text{cnt}$ 统计数组 $\text{nums1}$ 中每个元素出现的次数，然后遍历数组 $\text{nums2}$，如果元素 $x$ 在 $\text{cnt}$ 中，并且 $x$ 的出现次数大于 $0$，那么将 $x$ 加入答案，然后将 $x$ 的出现次数减一。

遍历结束后，返回答案数组即可。

时间复杂度 $O(m + n)$，空间复杂度 $O(m)$。其中 $m$ 和 $n$ 分别是数组 $\text{nums1}$ 和 $\text{nums2}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def intersect(self, nums1: List[int], nums2: List[int]) -> List[int]:
        cnt = Counter(nums1)
        ans = []
        for x in nums2:
            if cnt[x]:
                ans.append(x)
                cnt[x] -= 1
        return ans
```

#### Java

```java
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        int[] cnt = new int[1001];
        for (int x : nums1) {
            ++cnt[x];
        }
        List<Integer> ans = new ArrayList<>();
        for (int x : nums2) {
            if (cnt[x]-- > 0) {
                ans.add(x);
            }
        }
        return ans.stream().mapToInt(Integer::intValue).toArray();
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> intersect(vector<int>& nums1, vector<int>& nums2) {
        unordered_map<int, int> cnt;
        for (int x : nums1) {
            ++cnt[x];
        }
        vector<int> ans;
        for (int x : nums2) {
            if (cnt[x]-- > 0) {
                ans.push_back(x);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func intersect(nums1 []int, nums2 []int) (ans []int) {
	cnt := map[int]int{}
	for _, x := range nums1 {
		cnt[x]++
	}
	for _, x := range nums2 {
		if cnt[x] > 0 {
			ans = append(ans, x)
			cnt[x]--
		}
	}
	return
}
```

#### TypeScript

```ts
function intersect(nums1: number[], nums2: number[]): number[] {
    const cnt: Record<number, number> = {};
    for (const x of nums1) {
        cnt[x] = (cnt[x] || 0) + 1;
    }
    const ans: number[] = [];
    for (const x of nums2) {
        if (cnt[x]-- > 0) {
            ans.push(x);
        }
    }
    return ans;
}
```

#### Rust

```rust
use std::collections::HashMap;

impl Solution {
    pub fn intersect(nums1: Vec<i32>, nums2: Vec<i32>) -> Vec<i32> {
        let mut cnt = HashMap::new();
        for &x in &nums1 {
            *cnt.entry(x).or_insert(0) += 1;
        }
        let mut ans = Vec::new();
        for &x in &nums2 {
            if let Some(count) = cnt.get_mut(&x) {
                if *count > 0 {
                    ans.push(x);
                    *count -= 1;
                }
            }
        }
        ans
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums1
 * @param {number[]} nums2
 * @return {number[]}
 */
var intersect = function (nums1, nums2) {
    const cnt = {};
    for (const x of nums1) {
        cnt[x] = (cnt[x] || 0) + 1;
    }
    const ans = [];
    for (const x of nums2) {
        if (cnt[x]-- > 0) {
            ans.push(x);
        }
    }
    return ans;
};
```

#### C#

```cs
public class Solution {
    public int[] Intersect(int[] nums1, int[] nums2) {
        Dictionary<int, int> cnt = new Dictionary<int, int>();
        foreach (int x in nums1) {
            if (cnt.ContainsKey(x)) {
                cnt[x]++;
            } else {
                cnt[x] = 1;
            }
        }
        List<int> ans = new List<int>();
        foreach (int x in nums2) {
            if (cnt.ContainsKey(x) && cnt[x] > 0) {
                ans.Add(x);
                cnt[x]--;
            }
        }
        return ans.ToArray();
    }
}
```

#### PHP

```php
class Solution {
    /**
     * @param Integer[] $nums1
     * @param Integer[] $nums2
     * @return Integer[]
     */
    function intersect($nums1, $nums2) {
        $cnt = [];
        foreach ($nums1 as $x) {
            if (isset($cnt[$x])) {
                $cnt[$x]++;
            } else {
                $cnt[$x] = 1;
            }
        }

        $ans = [];
        foreach ($nums2 as $x) {
            if (isset($cnt[$x]) && $cnt[$x] > 0) {
                $ans[] = $x;
                $cnt[$x]--;
            }
        }

        return $ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
