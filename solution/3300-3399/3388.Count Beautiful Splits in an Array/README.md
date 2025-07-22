---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3388.Count%20Beautiful%20Splits%20in%20an%20Array/README.md
rating: 2364
source: 第 428 场周赛 Q3
tags:
    - 数组
    - 动态规划
---

<!-- problem:start -->

# [3388. 统计数组中的美丽分割](https://leetcode.cn/problems/count-beautiful-splits-in-an-array)

[English Version](/solution/3300-3399/3388.Count%20Beautiful%20Splits%20in%20an%20Array/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组&nbsp;<code>nums</code>&nbsp;。</p>

<p>如果数组&nbsp;<code>nums</code>&nbsp;的一个分割满足以下条件，我们称它是一个 <strong>美丽</strong>&nbsp;分割：</p>

<ol>
	<li>数组&nbsp;<code>nums</code>&nbsp;分为三段 <span data-keyword="subarray-nonempty">非空子数组</span>：<code>nums1</code>&nbsp;，<code>nums2</code>&nbsp;和&nbsp;<code>nums3</code>&nbsp;，三个数组&nbsp;<code>nums1</code>&nbsp;，<code>nums2</code>&nbsp;和&nbsp;<code>nums3</code>&nbsp;按顺序连接可以得到 <code>nums</code>&nbsp;。</li>
	<li>子数组&nbsp;<code>nums1</code>&nbsp;是子数组&nbsp;<code>nums2</code>&nbsp;的 <span data-keyword="array-prefix">前缀</span> <strong>或者</strong>&nbsp;<code>nums2</code>&nbsp;是&nbsp;<code>nums3</code>&nbsp;的 <span data-keyword="array-prefix">前缀</span>。</li>
</ol>

<p>请你返回满足以上条件的分割 <strong>数目</strong>&nbsp;。</p>

<p><strong>子数组</strong>&nbsp;指的是一个数组里一段连续 <strong>非空</strong>&nbsp;的元素。</p>

<p><strong>前缀</strong>&nbsp;指的是一个数组从头开始到中间某个元素结束的子数组。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [1,1,2,1]</span></p>

<p><span class="example-io"><b>输出：</b>2</span></p>

<p><b>解释：</b></p>

<p>美丽分割如下：</p>

<ol>
	<li><code>nums1 = [1]</code>&nbsp;，<code>nums2 = [1,2]</code>&nbsp;，<code>nums3 = [1]</code>&nbsp;。</li>
	<li><code>nums1 = [1]</code>&nbsp;，<code>nums2 = [1]</code>&nbsp;，<code>nums3 = [2,1]</code>&nbsp;。</li>
</ol>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [1,2,3,4]</span></p>

<p><span class="example-io"><b>输出：</b>0</span></p>

<p><strong>解释：</strong></p>

<p>没有美丽分割。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 5000</code></li>
	<li><code><font face="monospace">0 &lt;= nums[i] &lt;= 50</font></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：LCP + 枚举

我们可以预处理 $\text{LCP}[i][j]$ 表示 $\textit{nums}[i:]$ 和 $\textit{nums}[j:]$ 的最长公共前缀长度。初始时 $\text{LCP}[i][j] = 0$。

接下来，我们倒序枚举 $i$ 和 $j$，对于每一对 $i$ 和 $j$，如果 $\textit{nums}[i] = \textit{nums}[j]$，那么我们可以得到 $\text{LCP}[i][j] = \text{LCP}[i + 1][j + 1] + 1$。

最后，我们枚举第一个子数组的结尾位置 $i$（不包括位置 $i$），以及第二个子数组的结尾位置 $j$（不包括位置 $j$），那么第一个子数组的长度为 $i$，第二个子数组的长度为 $j - i$，第三个子数组的长度为 $n - j$。如果 $i \leq j - i$ 且 $\text{LCP}[0][i] \geq i$，或者 $j - i \leq n - j$ 且 $\text{LCP}[i][j] \geq j - i$，那么这个分割是美丽的，答案加一。

枚举结束后，答案即为美丽的分割数目。

时间复杂度 $O(n^2)$，空间复杂度 $O(n^2)$。其中 $n$ 为数组 $\textit{nums}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def beautifulSplits(self, nums: List[int]) -> int:
        n = len(nums)
        lcp = [[0] * (n + 1) for _ in range(n + 1)]
        for i in range(n - 1, -1, -1):
            for j in range(n - 1, i - 1, -1):
                if nums[i] == nums[j]:
                    lcp[i][j] = lcp[i + 1][j + 1] + 1
        ans = 0
        for i in range(1, n - 1):
            for j in range(i + 1, n):
                a = i <= j - i and lcp[0][i] >= i
                b = j - i <= n - j and lcp[i][j] >= j - i
                ans += int(a or b)
        return ans
```

#### Java

```java
class Solution {
    public int beautifulSplits(int[] nums) {
        int n = nums.length;
        int[][] lcp = new int[n + 1][n + 1];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j > i; j--) {
                if (nums[i] == nums[j]) {
                    lcp[i][j] = lcp[i + 1][j + 1] + 1;
                }
            }
        }

        int ans = 0;
        for (int i = 1; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                boolean a = (i <= j - i) && (lcp[0][i] >= i);
                boolean b = (j - i <= n - j) && (lcp[i][j] >= j - i);
                if (a || b) {
                    ans++;
                }
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
    int beautifulSplits(vector<int>& nums) {
        int n = nums.size();
        vector<vector<int>> lcp(n + 1, vector<int>(n + 1, 0));

        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j > i; j--) {
                if (nums[i] == nums[j]) {
                    lcp[i][j] = lcp[i + 1][j + 1] + 1;
                }
            }
        }

        int ans = 0;
        for (int i = 1; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                bool a = (i <= j - i) && (lcp[0][i] >= i);
                bool b = (j - i <= n - j) && (lcp[i][j] >= j - i);
                if (a || b) {
                    ans++;
                }
            }
        }

        return ans;
    }
};
```

#### Go

```go
func beautifulSplits(nums []int) (ans int) {
    n := len(nums)
    lcp := make([][]int, n+1)
    for i := range lcp {
        lcp[i] = make([]int, n+1)
    }

    for i := n - 1; i >= 0; i-- {
        for j := n - 1; j > i; j-- {
            if nums[i] == nums[j] {
                lcp[i][j] = lcp[i+1][j+1] + 1
            }
        }
    }

    for i := 1; i < n-1; i++ {
        for j := i + 1; j < n; j++ {
            a := i <= j-i && lcp[0][i] >= i
            b := j-i <= n-j && lcp[i][j] >= j-i
            if a || b {
                ans++
            }
        }
    }

    return
}
```

#### TypeScript

```ts
function beautifulSplits(nums: number[]): number {
    const n = nums.length;
    const lcp: number[][] = Array.from({ length: n + 1 }, () => Array(n + 1).fill(0));

    for (let i = n - 1; i >= 0; i--) {
        for (let j = n - 1; j > i; j--) {
            if (nums[i] === nums[j]) {
                lcp[i][j] = lcp[i + 1][j + 1] + 1;
            }
        }
    }

    let ans = 0;
    for (let i = 1; i < n - 1; i++) {
        for (let j = i + 1; j < n; j++) {
            const a = i <= j - i && lcp[0][i] >= i;
            const b = j - i <= n - j && lcp[i][j] >= j - i;
            if (a || b) {
                ans++;
            }
        }
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
