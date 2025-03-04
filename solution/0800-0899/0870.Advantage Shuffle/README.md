---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0800-0899/0870.Advantage%20Shuffle/README.md
tags:
    - 贪心
    - 数组
    - 双指针
    - 排序
---

<!-- problem:start -->

# [870. 优势洗牌](https://leetcode.cn/problems/advantage-shuffle)

[English Version](/solution/0800-0899/0870.Advantage%20Shuffle/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定两个长度相等的数组&nbsp;<code>nums1</code>&nbsp;和&nbsp;<code>nums2</code>，<code>nums1</code>&nbsp;相对于 <code>nums2</code> 的<em>优势</em>可以用满足&nbsp;<code>nums1[i] &gt; nums2[i]</code>&nbsp;的索引 <code>i</code>&nbsp;的数目来描述。</p>

<p>返回 <code>nums1</code> 的&nbsp;<strong>任意&nbsp;</strong>排列，使其相对于 <code>nums2</code>&nbsp;的优势最大化。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums1 = [2,7,11,15], nums2 = [1,10,4,11]
<strong>输出：</strong>[2,11,7,15]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums1 = [12,24,8,32], nums2 = [13,25,32,11]
<strong>输出：</strong>[24,32,8,12]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums1.length &lt;= 10<sup>5</sup></code></li>
	<li><code>nums2.length == nums1.length</code></li>
	<li><code>0 &lt;= nums1[i], nums2[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心 + 排序

类似田忌赛马。将 $nums1$, $nums2$ 按照升序排列。然后遍历 $nums1$ 中的每个元素 $v$，若在 $nums2[i..j]$ 中找不到比 $v$ 小的，则将 $v$ 与当前 $nums2[i..j]$ 中的最大元素匹配。

时间复杂度 $O(nlogn)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def advantageCount(self, nums1: List[int], nums2: List[int]) -> List[int]:
        nums1.sort()
        t = sorted((v, i) for i, v in enumerate(nums2))
        n = len(nums2)
        ans = [0] * n
        i, j = 0, n - 1
        for v in nums1:
            if v <= t[i][0]:
                ans[t[j][1]] = v
                j -= 1
            else:
                ans[t[i][1]] = v
                i += 1
        return ans
```

#### Java

```java
class Solution {
    public int[] advantageCount(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[][] t = new int[n][2];
        for (int i = 0; i < n; ++i) {
            t[i] = new int[] {nums2[i], i};
        }
        Arrays.sort(t, (a, b) -> a[0] - b[0]);
        Arrays.sort(nums1);
        int[] ans = new int[n];
        int i = 0, j = n - 1;
        for (int v : nums1) {
            if (v <= t[i][0]) {
                ans[t[j--][1]] = v;
            } else {
                ans[t[i++][1]] = v;
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> advantageCount(vector<int>& nums1, vector<int>& nums2) {
        int n = nums1.size();
        vector<pair<int, int>> t;
        for (int i = 0; i < n; ++i) t.push_back({nums2[i], i});
        sort(t.begin(), t.end());
        sort(nums1.begin(), nums1.end());
        int i = 0, j = n - 1;
        vector<int> ans(n);
        for (int v : nums1) {
            if (v <= t[i].first)
                ans[t[j--].second] = v;
            else
                ans[t[i++].second] = v;
        }
        return ans;
    }
};
```

#### Go

```go
func advantageCount(nums1 []int, nums2 []int) []int {
	n := len(nums1)
	t := make([][]int, n)
	for i, v := range nums2 {
		t[i] = []int{v, i}
	}
	sort.Slice(t, func(i, j int) bool {
		return t[i][0] < t[j][0]
	})
	sort.Ints(nums1)
	ans := make([]int, n)
	i, j := 0, n-1
	for _, v := range nums1 {
		if v <= t[i][0] {
			ans[t[j][1]] = v
			j--
		} else {
			ans[t[i][1]] = v
			i++
		}
	}
	return ans
}
```

#### TypeScript

```ts
function advantageCount(nums1: number[], nums2: number[]): number[] {
    const n = nums1.length;
    const idx = Array.from({ length: n }, (_, i) => i);
    idx.sort((i, j) => nums2[i] - nums2[j]);
    nums1.sort((a, b) => a - b);

    const ans = new Array(n).fill(0);
    let left = 0;
    let right = n - 1;
    for (let i = 0; i < n; i++) {
        if (nums1[i] > nums2[idx[left]]) {
            ans[idx[left]] = nums1[i];
            left++;
        } else {
            ans[idx[right]] = nums1[i];
            right--;
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn advantage_count(mut nums1: Vec<i32>, nums2: Vec<i32>) -> Vec<i32> {
        let n = nums1.len();
        let mut idx = (0..n).collect::<Vec<usize>>();
        idx.sort_by(|&i, &j| nums2[i].cmp(&nums2[j]));
        nums1.sort();
        let mut res = vec![0; n];
        let mut left = 0;
        let mut right = n - 1;
        for &num in nums1.iter() {
            if num > nums2[idx[left]] {
                res[idx[left]] = num;
                left += 1;
            } else {
                res[idx[right]] = num;
                right -= 1;
            }
        }
        res
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
