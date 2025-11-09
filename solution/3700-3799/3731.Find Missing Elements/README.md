---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3731.Find%20Missing%20Elements/README.md
rating: 1217
source: 第 474 场周赛 Q1
---

<!-- problem:start -->

# [3731. 找出缺失的元素](https://leetcode.cn/problems/find-missing-elements)

[English Version](/solution/3700-3799/3731.Find%20Missing%20Elements/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code> ，数组由若干&nbsp;<b>互不相同</b> 的整数组成。</p>

<p>数组 <code>nums</code> 原本包含了某个范围内的&nbsp;<strong>所有整数&nbsp;</strong>。但现在，其中可能 <strong>缺失</strong> 部分整数。</p>

<p>该范围内的&nbsp;<strong>最小&nbsp;</strong>整数和&nbsp;<strong>最大&nbsp;</strong>整数仍然存在于 <code>nums</code> 中。</p>

<p>返回一个&nbsp;<strong>有序&nbsp;</strong>列表，包含该范围内缺失的所有整数，并&nbsp;<strong>按从小到大排序</strong>。如果没有缺失的整数，返回一个&nbsp;<strong>空&nbsp;</strong>列表。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,4,2,5]</span></p>

<p><strong>输出：</strong> <span class="example-io">[3]</span></p>

<p><strong>解释：</strong></p>

<p>最小整数为 1，最大整数为 5，因此完整的范围应为 <code>[1,2,3,4,5]</code>。其中只有 3 缺失。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [7,8,6,9]</span></p>

<p><strong>输出：</strong> <span class="example-io">[]</span></p>

<p><strong>解释：</strong></p>

<p>最小整数为 6，最大整数为 9，因此完整的范围为 <code>[6,7,8,9]</code>。所有整数均已存在，因此没有缺失的整数。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [5,1]</span></p>

<p><strong>输出：</strong> <span class="example-io">[2,3,4]</span></p>

<p><strong>解释：</strong></p>

<p>最小整数为 1，最大整数为 5，因此完整的范围应为 <code>[1,2,3,4,5]</code>。缺失的整数为 2、3 和 4。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表

我们先找出数组 $\textit{nums}$ 中的最小值和最大值，记为 $\textit{mn}$ 和 $\textit{mx}$。然后我们使用哈希表存储数组 $\textit{nums}$ 中的所有元素。

接下来，我们遍历区间 $[\textit{mn} + 1, \textit{mx} - 1]$，对于每个整数 $x$，如果 $x$ 不在哈希表中，我们就将其加入答案列表中。

时间复杂度 $O(n)$，空间复杂度 $O(n)$，其中 $n$ 是数组 $\textit{nums}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findMissingElements(self, nums: List[int]) -> List[int]:
        mn, mx = min(nums), max(nums)
        s = set(nums)
        return [x for x in range(mn + 1, mx) if x not in s]
```

#### Java

```java
class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        int mn = 100, mx = 0;
        Set<Integer> s = new HashSet<>();
        for (int x : nums) {
            mn = Math.min(mn, x);
            mx = Math.max(mx, x);
            s.add(x);
        }
        List<Integer> ans = new ArrayList<>();
        for (int x = mn + 1; x < mx; ++x) {
            if (!s.contains(x)) {
                ans.add(x);
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
    vector<int> findMissingElements(vector<int>& nums) {
        int mn = 100, mx = 0;
        unordered_set<int> s;
        for (int x : nums) {
            mn = min(mn, x);
            mx = max(mx, x);
            s.insert(x);
        }
        vector<int> ans;
        for (int x = mn + 1; x < mx; ++x) {
            if (!s.count(x)) {
                ans.push_back(x);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func findMissingElements(nums []int) (ans []int) {
	mn, mx := 100, 0
	s := make(map[int]bool)
	for _, x := range nums {
        mn = min(mn, x)
        mx = max(mx, x)
		s[x] = true
	}
	for x := mn + 1; x < mx; x++ {
		if !s[x] {
			ans = append(ans, x)
		}
	}
	return
}
```

#### TypeScript

```ts
function findMissingElements(nums: number[]): number[] {
    let [mn, mx] = [100, 0];
    const s = new Set<number>();
    for (const x of nums) {
        mn = Math.min(mn, x);
        mx = Math.max(mx, x);
        s.add(x);
    }
    const ans: number[] = [];
    for (let x = mn + 1; x < mx; ++x) {
        if (!s.has(x)) {
            ans.push(x);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
