---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2282.Number%20of%20People%20That%20Can%20Be%20Seen%20in%20a%20Grid/README.md
tags:
    - 栈
    - 数组
    - 矩阵
    - 单调栈
---

<!-- problem:start -->

# [2282. 在一个网格中可以看到的人数 🔒](https://leetcode.cn/problems/number-of-people-that-can-be-seen-in-a-grid)

[English Version](/solution/2200-2299/2282.Number%20of%20People%20That%20Can%20Be%20Seen%20in%20a%20Grid/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个 <code>m x n</code> <strong>下标从 0 开始</strong>的二维正整数数组 <code>heights</code>，其中 <code>heights[i][j]</code> 是站在位置 <code>(i, j)</code>&nbsp;上的人的高度。</p>

<p>站在 <code>(row<sub>1</sub>, col<sub>1</sub>)</code>&nbsp;位置的人可以看到站在 <code>(row<sub>2</sub>, col<sub>2</sub>)</code> 位置的人，前提是:</p>

<ul>
	<li><code>(row<sub>2</sub>, col<sub>2</sub>)</code>&nbsp;的人在&nbsp;<code>(row<sub>1</sub>, col<sub>1</sub>)</code> 的人的右边&nbsp;<strong>或&nbsp;</strong>下面。更正式地说，要么 <code>row<sub>1</sub> == row<sub>2</sub></code>&nbsp;时&nbsp;<code>col<sub>1</sub> &lt; col<sub>2</sub></code>，要么&nbsp;<code>row<sub>1</sub> &lt; row<sub>2</sub></code><sub>&nbsp;</sub>时 <code>col<sub>1</sub> == col<sub>2</sub></code>。</li>
	<li>他们中间的人&nbsp;<strong>都&nbsp;</strong>比他们两个矮。</li>
</ul>

<p>返回<em>一个&nbsp;<code>m x n</code> 的二维整数数组<code>answer</code>，其中&nbsp;<code>answer[i][j]</code>&nbsp;是位于&nbsp;<code>(i, j)</code> 位置的人可以看到的人数。</em></p>

<p>&nbsp;</p>

<p><strong class="example">示例 1:</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2282.Number%20of%20People%20That%20Can%20Be%20Seen%20in%20a%20Grid/images/image-20220524180458-1.png" style="width: 700px; height: 164px;" />
<pre>
<strong>输入:</strong> heights = [[3,1,4,2,5]]
<strong>输出:</strong> [[2,1,2,1,0]]
<strong>解释:</strong>
- (0,0) 上的人可以看到 (0,1) 和 (0,2) 的人。
  注意，他看不到 (0,4) 上的人，因为 (0,2) 上的人比他高。
- (0,1) 上的人可以看到 (0,2) 上的人。
- (0,2) 上的人可以看到 (0,3) 和 (0,4) 的人。
- (0,3) 上的人可以看到 (0,4) 上的人。
- (0,4) 上的人看不到任何人。</pre>

<p><strong class="example">示例 2:</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2282.Number%20of%20People%20That%20Can%20Be%20Seen%20in%20a%20Grid/images/image-20220523113533-2.png" style="width: 400px; height: 249px;" />
<pre>
<strong>输入:</strong> heights = [[5,1],[3,1],[4,1]]
<strong>输出:</strong> [[3,1],[2,1],[1,0]]
<strong>解释:</strong>
- (0,0) 上的人可以看到 (0,1)、(1,0) 和 (2,0) 的人。
- (0,1) 上的人可以看到 (1,1) 上的人。
- (1,0) 上的人可以看到 (1,1) 和 (2,0) 的人。
- (1,1) 上的人可以看到 (2,1) 上的人。
- (2,0) 上的人可以看到 (2,1) 上的人。
- (2,1) 上的人看不到任何人。</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= heights.length &lt;= 400</code></li>
	<li><code>1 &lt;= heights[i].length &lt;= 400</code></li>
	<li><code>1 &lt;= heights[i][j] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：单调栈

我们观察发现，对于第 $i$ 个人来说，他能看到的人一定是按从左到右（或者从上到下）高度严格单调递增的。

因此，对于每一行，我们可以用单调栈来求出每个人能看到的人数。

具体地，我们可以倒序遍历数组，用一个从栈顶到栈底单调递增的栈 $stk$ 记录已经遍历过的人的高度。

对于第 $i$ 个人，如果栈不为空并且栈顶元素小于 $heights[i]$，累加当前第 $i$ 个人能看到的人数，然后将栈顶元素出栈，直到栈为空或者栈顶元素大于等于 $heights[i]$。如果此时栈不为空，说明栈顶元素大于等于 $heights[i]$，那么第 $i$ 个人能看到的人数还要再加 $1$。接下来，如果栈不为空并且栈顶元素等于 $heights[i]$，那么栈顶元素出栈。最后，将 $heights[i]$ 入栈，继续遍历下一个人。

这样处理过后，我们就可以得到每一行每个人能看到的人数。

同理，我们可以对每一列进行处理，得到每一列每个人能看到的人数。最后，我们将每一行和每一列的答案相加，就可以得到最终的答案。

时间复杂度 $O(m \times n)$，空间复杂度 $O(\max(m, n))$。其中 $m$ 和 $n$ 分别是数组 $heights$ 的行数和列数。

相似题目：

-   [1944. 队列中可以看到的人数](https://github.com/doocs/leetcode/blob/main/solution/1900-1999/1944.Number%20of%20Visible%20People%20in%20a%20Queue/README.md)

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def seePeople(self, heights: List[List[int]]) -> List[List[int]]:
        def f(nums: List[int]) -> List[int]:
            n = len(nums)
            stk = []
            ans = [0] * n
            for i in range(n - 1, -1, -1):
                while stk and stk[-1] < nums[i]:
                    ans[i] += 1
                    stk.pop()
                if stk:
                    ans[i] += 1
                while stk and stk[-1] == nums[i]:
                    stk.pop()
                stk.append(nums[i])
            return ans

        ans = [f(row) for row in heights]
        m, n = len(heights), len(heights[0])
        for j in range(n):
            add = f([heights[i][j] for i in range(m)])
            for i in range(m):
                ans[i][j] += add[i]
        return ans
```

#### Java

```java
class Solution {
    public int[][] seePeople(int[][] heights) {
        int m = heights.length, n = heights[0].length;
        int[][] ans = new int[m][0];
        for (int i = 0; i < m; ++i) {
            ans[i] = f(heights[i]);
        }
        for (int j = 0; j < n; ++j) {
            int[] nums = new int[m];
            for (int i = 0; i < m; ++i) {
                nums[i] = heights[i][j];
            }
            int[] add = f(nums);
            for (int i = 0; i < m; ++i) {
                ans[i][j] += add[i];
            }
        }
        return ans;
    }

    private int[] f(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        Deque<Integer> stk = new ArrayDeque<>();
        for (int i = n - 1; i >= 0; --i) {
            while (!stk.isEmpty() && stk.peek() < nums[i]) {
                stk.pop();
                ++ans[i];
            }
            if (!stk.isEmpty()) {
                ++ans[i];
            }
            while (!stk.isEmpty() && stk.peek() == nums[i]) {
                stk.pop();
            }
            stk.push(nums[i]);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<vector<int>> seePeople(vector<vector<int>>& heights) {
        int m = heights.size(), n = heights[0].size();
        auto f = [](vector<int>& nums) {
            int n = nums.size();
            vector<int> ans(n);
            stack<int> stk;
            for (int i = n - 1; ~i; --i) {
                while (stk.size() && stk.top() < nums[i]) {
                    ++ans[i];
                    stk.pop();
                }
                if (stk.size()) {
                    ++ans[i];
                }
                while (stk.size() && stk.top() == nums[i]) {
                    stk.pop();
                }
                stk.push(nums[i]);
            }
            return ans;
        };
        vector<vector<int>> ans;
        for (auto& row : heights) {
            ans.push_back(f(row));
        }
        for (int j = 0; j < n; ++j) {
            vector<int> col;
            for (int i = 0; i < m; ++i) {
                col.push_back(heights[i][j]);
            }
            vector<int> add = f(col);
            for (int i = 0; i < m; ++i) {
                ans[i][j] += add[i];
            }
        }
        return ans;
    }
};
```

#### Go

```go
func seePeople(heights [][]int) (ans [][]int) {
	f := func(nums []int) []int {
		n := len(nums)
		ans := make([]int, n)
		stk := []int{}
		for i := n - 1; i >= 0; i-- {
			for len(stk) > 0 && stk[len(stk)-1] < nums[i] {
				ans[i]++
				stk = stk[:len(stk)-1]
			}
			if len(stk) > 0 {
				ans[i]++
			}
			for len(stk) > 0 && stk[len(stk)-1] == nums[i] {
				stk = stk[:len(stk)-1]
			}
			stk = append(stk, nums[i])
		}
		return ans
	}
	for _, row := range heights {
		ans = append(ans, f(row))
	}
	n := len(heights[0])
	for j := 0; j < n; j++ {
		col := make([]int, len(heights))
		for i := range heights {
			col[i] = heights[i][j]
		}
		for i, v := range f(col) {
			ans[i][j] += v
		}
	}
	return
}
```

#### TypeScript

```ts
function seePeople(heights: number[][]): number[][] {
    const f = (nums: number[]): number[] => {
        const n = nums.length;
        const ans: number[] = new Array(n).fill(0);
        const stk: number[] = [];
        for (let i = n - 1; ~i; --i) {
            while (stk.length && stk.at(-1) < nums[i]) {
                stk.pop();
                ++ans[i];
            }
            if (stk.length) {
                ++ans[i];
            }
            while (stk.length && stk.at(-1) === nums[i]) {
                stk.pop();
            }
            stk.push(nums[i]);
        }
        return ans;
    };
    const ans: number[][] = [];
    for (const row of heights) {
        ans.push(f(row));
    }
    const n = heights[0].length;
    for (let j = 0; j < n; ++j) {
        const col: number[] = [];
        for (const row of heights) {
            col.push(row[j]);
        }
        const add = f(col);
        for (let i = 0; i < ans.length; ++i) {
            ans[i][j] += add[i];
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
