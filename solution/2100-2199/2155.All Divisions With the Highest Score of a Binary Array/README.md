---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2100-2199/2155.All%20Divisions%20With%20the%20Highest%20Score%20of%20a%20Binary%20Array/README.md
rating: 1390
source: 第 278 场周赛 Q2
tags:
    - 数组
---

<!-- problem:start -->

# [2155. 分组得分最高的所有下标](https://leetcode.cn/problems/all-divisions-with-the-highest-score-of-a-binary-array)

[English Version](/solution/2100-2199/2155.All%20Divisions%20With%20the%20Highest%20Score%20of%20a%20Binary%20Array/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个下标从 <strong>0</strong> 开始的二进制数组 <code>nums</code> ，数组长度为 <code>n</code> 。<code>nums</code> 可以按下标 <code>i</code>（ <code>0 &lt;= i &lt;= n</code> ）拆分成两个数组（可能为空）：<code>nums<sub>left</sub></code> 和 <code>nums<sub>right</sub></code> 。</p>

<ul>
	<li><code>nums<sub>left</sub></code> 包含 <code>nums</code> 中从下标 <code>0</code> 到 <code>i - 1</code> 的所有元素<strong>（包括 </strong><code>0</code><strong> 和 </strong><code>i - 1</code><strong> ）</strong>，而 <code>nums<sub>right</sub></code> 包含 <code>nums</code> 中从下标 <code>i</code> 到 <code>n - 1</code> 的所有元素<strong>（包括 </strong><code>i</code><strong> 和 </strong><code>n - 1</code><strong> ）。</strong></li>
	<li>如果 <code>i == 0</code> ，<code>nums<sub>left</sub></code> 为 <strong>空</strong> ，而 <code>nums<sub>right</sub></code> 将包含 <code>nums</code> 中的所有元素。</li>
	<li>如果 <code>i == n</code> ，<code>nums<sub>left</sub></code> 将包含 <code>nums</code> 中的所有元素，而 <code>nums<sub>right</sub></code> 为 <strong>空</strong> 。</li>
</ul>

<p>下标 <code>i</code><strong> </strong>的<strong> 分组得分</strong> 为 <code>nums<sub>left</sub></code> 中 <code>0</code> 的个数和 <code>nums<sub>right</sub></code> 中 <code>1</code> 的个数之<strong> 和</strong> 。</p>

<p>返回 <strong>分组得分 最高</strong> 的 <strong>所有不同下标</strong> 。你可以按 <strong>任意顺序</strong> 返回答案。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums = [0,0,1,0]
<strong>输出：</strong>[2,4]
<strong>解释：</strong>按下标分组
- 0 ：nums<sub>left</sub> 为 [] 。nums<sub>right</sub> 为 [0,0,<em><strong>1</strong></em>,0] 。得分为 0 + 1 = 1 。
- 1 ：nums<sub>left</sub> 为 [<em><strong>0</strong></em>] 。nums<sub>right</sub> 为 [0,<em><strong>1</strong></em>,0] 。得分为 1 + 1 = 2 。
- 2 ：nums<sub>left</sub> 为 [<em><strong>0</strong></em>,<em><strong>0</strong></em>] 。nums<sub>right</sub> 为 [<em><strong>1</strong></em>,0] 。得分为 2 + 1 = 3 。
- 3 ：nums<sub>left</sub> 为 [<em><strong>0</strong></em>,<em><strong>0</strong></em>,1] 。nums<sub>right</sub> 为 [0] 。得分为 2 + 0 = 2 。
- 4 ：nums<sub>left</sub> 为 [<em><strong>0</strong></em>,<em><strong>0</strong></em>,1,<em><strong>0</strong></em>] 。nums<sub>right</sub> 为 [] 。得分为 3 + 0 = 3 。
下标 2 和 4 都可以得到最高的分组得分 3 。
注意，答案 [4,2] 也被视为正确答案。</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums = [0,0,0]
<strong>输出：</strong>[3]
<strong>解释：</strong>按下标分组
- 0 ：nums<sub>left</sub> 为 [] 。nums<sub>right</sub> 为 [0,0,0] 。得分为 0 + 0 = 0 。
- 1 ：nums<sub>left</sub> 为 [<em><strong>0</strong></em>] 。nums<sub>right</sub> 为 [0,0] 。得分为 1 + 0 = 1 。
- 2 ：nums<sub>left</sub> 为 [<em><strong>0</strong></em>,<em><strong>0</strong></em>] 。nums<sub>right</sub> 为 [0] 。得分为 2 + 0 = 2 。
- 3 ：nums<sub>left</sub> 为 [<em><strong>0</strong></em>,<em><strong>0</strong></em>,<em><strong>0</strong></em>] 。nums<sub>right</sub> 为 [] 。得分为 3 + 0 = 3 。
只有下标 3 可以得到最高的分组得分 3 。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>nums = [1,1]
<strong>输出：</strong>[0]
<strong>解释：</strong>按下标分组
- 0 ：nums<sub>left</sub> 为 [] 。nums<sub>right</sub> 为 [<em><strong>1</strong></em>,<em><strong>1</strong></em>] 。得分为 0 + 2 = 2 。
- 1 ：nums<sub>left</sub> 为 [1] 。nums<sub>right</sub> 为 [<em><strong>1</strong></em>] 。得分为 0 + 1 = 1 。
- 2 ：nums<sub>left</sub> 为 [1,1] 。nums<sub>right</sub> 为 [] 。得分为 0 + 0 = 0 。
只有下标 0 可以得到最高的分组得分 2 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>nums[i]</code> 为 <code>0</code> 或 <code>1</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：前缀和

我们从 $i = 0$ 开始，用两个变量 $\textit{l0}$ 和 $\textit{r1}$ 分别记录 $i$ 左侧和右侧的 $1$ 的个数，初始时 $\textit{l0} = 0$，而 $\textit{r1} = \sum \textit{nums}$。

我们遍历数组 $\textit{nums}$，对于每个 $i$，更新 $\textit{l0}$ 和 $\textit{r1}$，计算当前分组得分 $t = \textit{l0} + \textit{r1}$，如果 $t$ 等于当前最大分组得分 $\textit{mx}$，则将 $i$ 加入答案数组，如果 $t$ 大于 $\textit{mx}$，则更新 $\textit{mx}$ 为 $t$，并将答案数组清空，然后将 $i$ 加入答案数组。

遍历结束后，返回答案数组。

时间复杂度 $O(n)$，其中 $n$ 为数组 $\textit{nums}$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxScoreIndices(self, nums: List[int]) -> List[int]:
        l0, r1 = 0, sum(nums)
        mx = r1
        ans = [0]
        for i, x in enumerate(nums, 1):
            l0 += x ^ 1
            r1 -= x
            t = l0 + r1
            if mx == t:
                ans.append(i)
            elif mx < t:
                mx = t
                ans = [i]
        return ans
```

#### Java

```java
class Solution {
    public List<Integer> maxScoreIndices(int[] nums) {
        int l0 = 0, r1 = Arrays.stream(nums).sum();
        int mx = r1;
        List<Integer> ans = new ArrayList<>();
        ans.add(0);
        for (int i = 1; i <= nums.length; ++i) {
            int x = nums[i - 1];
            l0 += x ^ 1;
            r1 -= x;
            int t = l0 + r1;
            if (mx == t) {
                ans.add(i);
            } else if (mx < t) {
                mx = t;
                ans.clear();
                ans.add(i);
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
    vector<int> maxScoreIndices(vector<int>& nums) {
        int l0 = 0, r1 = accumulate(nums.begin(), nums.end(), 0);
        int mx = r1;
        vector<int> ans = {0};
        for (int i = 1; i <= nums.size(); ++i) {
            int x = nums[i - 1];
            l0 += x ^ 1;
            r1 -= x;
            int t = l0 + r1;
            if (mx == t) {
                ans.push_back(i);
            } else if (mx < t) {
                mx = t;
                ans = {i};
            }
        }
        return ans;
    }
};
```

#### Go

```go
func maxScoreIndices(nums []int) []int {
	l0, r1 := 0, 0
	for _, x := range nums {
		r1 += x
	}
	mx := r1
	ans := []int{0}
	for i, x := range nums {
		l0 += x ^ 1
		r1 -= x
		t := l0 + r1
		if mx == t {
			ans = append(ans, i+1)
		} else if mx < t {
			mx = t
			ans = []int{i + 1}
		}
	}
	return ans
}
```

#### TypeScript

```ts
function maxScoreIndices(nums: number[]): number[] {
    const n = nums.length;
    let [l0, r1] = [0, nums.reduce((a, b) => a + b, 0)];
    let mx = r1;
    const ans: number[] = [0];
    for (let i = 1; i <= n; ++i) {
        const x = nums[i - 1];
        l0 += x ^ 1;
        r1 -= x;
        const t = l0 + r1;
        if (mx === t) {
            ans.push(i);
        } else if (mx < t) {
            mx = t;
            ans.length = 0;
            ans.push(i);
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn max_score_indices(nums: Vec<i32>) -> Vec<i32> {
        let mut l0 = 0;
        let mut r1: i32 = nums.iter().sum();
        let mut mx = r1;
        let mut ans = vec![0];

        for i in 1..=nums.len() {
            let x = nums[i - 1];
            l0 += x ^ 1;
            r1 -= x;
            let t = l0 + r1;
            if mx == t {
                ans.push(i as i32);
            } else if mx < t {
                mx = t;
                ans = vec![i as i32];
            }
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
