---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2800-2899/2899.Last%20Visited%20Integers/README.md
rating: 1372
source: 第 115 场双周赛 Q1
tags:
    - 数组
    - 模拟
---

<!-- problem:start -->

# [2899. 上一个遍历的整数](https://leetcode.cn/problems/last-visited-integers)

[English Version](/solution/2800-2899/2899.Last%20Visited%20Integers/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组&nbsp;<code>nums</code>&nbsp;，其中&nbsp;<code>nums[i]</code>&nbsp;要么是一个正整数，要么是&nbsp;<code>-1</code>&nbsp;。我们需要为每个 <code>-1</code> 找到相应的正整数，我们称之为最后访问的整数。</p>

<p>为了达到这个目标，定义两个空数组：<code>seen</code>&nbsp;和&nbsp;<code>ans</code>。</p>

<p>从数组&nbsp;<code>nums</code>&nbsp;的头部开始遍历。</p>

<ul>
	<li>如果遇到正整数，把它添加到&nbsp;<code>seen</code>&nbsp;的&nbsp;<strong>头部</strong>。</li>
	<li>如果遇到 <code>-1</code>，则设 <code>k</code> 是到目前为止看到的 <strong>连续</strong> <code>-1</code> 的数目(包括当前 <code>-1</code>)，
	<ul>
		<li>如果&nbsp;<code>k</code>&nbsp;小于等于&nbsp;<code>seen</code>&nbsp;的长度，把&nbsp;<code>seen</code>&nbsp;的第&nbsp;<code>k</code>&nbsp;个元素添加到&nbsp;<code>ans</code>。</li>
		<li>如果&nbsp;<code>k</code>&nbsp;严格大于&nbsp;<code>seen</code>&nbsp;的长度，把&nbsp;<code>-1</code>&nbsp;添加到&nbsp;<code>ans</code>。</li>
	</ul>
	</li>
</ul>

<p>请你返回数组&nbsp;<code>ans</code>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [1,2,-1,-1,-1]
<b>输出：</b>[2,1,-1]
<b>解释：</b> 开始时 seen = [] 且 ans = []。
1.处理 nums[0]：nums 中的第一个元素是 1。我们将其放在 seen 的前面。现在，seen == [1]。
2.处理 nums[1]：下一个元素是 2。我们将其放在 seen 的前面。现在，seen == [2, 1]。
3.处理 nums[2]：下一个元素是 -1。这是 -1 的第一次出现，所以 k == 1。我们找到 seen 中的第一个元素，把 2 添加到 ans。现在，ans == [2]。
4.处理 nums[3]：又一个 -1。这是 -1 的第二次出现，所以 k == 2。seen 中的第二个元素是 1，所以我们把 1 添加到 ans。现在，ans == [2, 1]。
5.处理 nums[4]：又一个 -1。第三次出现，让 k = 3。然而，seen 中只有两个元素（[2, 1]）。因为 k 比 seen 中的元素数量更大，我们把 -1 添加到 ans。最终，ans == [2, 1, -1]。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [1,-1,2,-1,-1]
<b>输出：</b>[1,2,1]
<strong>解释：</strong> 开始时 seen = [] 且 ans = []。
1.处理 nums[0]：nums 中的第一个元素是 1。我们将其放在 seen 的前面。现在，seen == [1]。
2.处理 nums[1]：下一个元素是 -1。这是 -1 的第一次出现，所以 k == 1。我们找到 seen 中的第一个元素，即 1。把 1 添加到 ans。现在，ans == [1]。
3.处理 nums[2]：下一个元素是 2。我们将其放在 seen 的前面。现在，seen == [2, 1]。
4.处理 nums[3]：下一个元素是 -1。这个 -1 与 第一个 -1 不连续，因为中间有个 2。因此，k 重置为 1。seen 中的第一个元素是 2，所以我们把 2 添加到 ans。现在，ans == [1, 2]。
5.处理 nums[4]：又一个 -1。它与前一个 -1 相邻，所以 k == 2。seen 中的第 2 个元素是 1。把 1 添加到 ans。最终，ans == [1, 2, 1]。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>nums[i] == -1</code>&nbsp;或&nbsp;<code>1 &lt;= nums[i]&nbsp;&lt;= 100</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们直接根据题意模拟即可。

定义一个数组 $\textit{seen}$ 来存储我们看到的正整数，定义一个数组 $\textit{ans}$ 来存储答案。我们还需要一个变量 $k$ 来记录连续出现的 $-1$ 的数量。

我们遍历数组 $\textit{nums}$：

- 如果当前元素 $x = -1$，我们将 $k$ 加 1。如果 $k$ 大于 $\textit{seen}$ 的长度，我们在 $\textit{ans}$ 中添加 $-1$；否则，我们在 $\textit{ans}$ 中添加 $\textit{seen}$ 的倒数第 $k$ 个元素。
- 如果当前元素 $x$ 是一个正整数，我们将 $k$ 重置为 0，并将 $x$ 添加到 $\textit{seen}$ 的末尾。

时间复杂度 $O(n)$，空间复杂度 $O(n)$，其中 $n$ 是数组 $\textit{nums}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def lastVisitedIntegers(self, nums: List[int]) -> List[int]:
        seen = []
        ans = []
        k = 0
        for x in nums:
            if x == -1:
                k += 1
                ans.append(-1 if k > len(seen) else seen[-k])
            else:
                k = 0
                seen.append(x)
        return ans
```

#### Java

```java
class Solution {
    public List<Integer> lastVisitedIntegers(int[] nums) {
        List<Integer> seen = new ArrayList<>();
        List<Integer> ans = new ArrayList<>();
        int k = 0;
        for (int x : nums) {
            if (x == -1) {
                if (++k > seen.size()) {
                    ans.add(-1);
                } else {
                    ans.add(seen.get(seen.size() - k));
                }
            } else {
                k = 0;
                seen.add(x);
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
    vector<int> lastVisitedIntegers(vector<int>& nums) {
        vector<int> seen;
        vector<int> ans;
        int k = 0;

        for (int x : nums) {
            if (x == -1) {
                if (++k > seen.size()) {
                    ans.push_back(-1);
                } else {
                    ans.push_back(seen[seen.size() - k]);
                }
            } else {
                k = 0;
                seen.push_back(x);
            }
        }

        return ans;
    }
};
```

#### Go

```go
func lastVisitedIntegers(nums []int) []int {
	seen := []int{}
	ans := []int{}
	k := 0

	for _, x := range nums {
		if x == -1 {
			k++
			if k > len(seen) {
				ans = append(ans, -1)
			} else {
				ans = append(ans, seen[len(seen)-k])
			}
		} else {
			k = 0
			seen = append(seen, x)
		}
	}

	return ans
}
```

#### TypeScript

```ts
function lastVisitedIntegers(nums: number[]): number[] {
    const seen: number[] = [];
    const ans: number[] = [];
    let k = 0;

    for (const x of nums) {
        if (x === -1) {
            if (++k > seen.length) {
                ans.push(-1);
            } else {
                ans.push(seen.at(-k)!);
            }
        } else {
            k = 0;
            seen.push(x);
        }
    }

    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn last_visited_integers(nums: Vec<i32>) -> Vec<i32> {
        let mut seen: Vec<i32> = Vec::new();
        let mut ans: Vec<i32> = Vec::new();
        let mut k: i32 = 0;

        for x in nums {
            if x == -1 {
                k += 1;
                if k as usize > seen.len() {
                    ans.push(-1);
                } else {
                    ans.push(seen[seen.len() - k as usize]);
                }
            } else {
                k = 0;
                seen.push(x);
            }
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
