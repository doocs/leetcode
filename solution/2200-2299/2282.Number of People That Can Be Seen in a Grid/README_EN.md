---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2282.Number%20of%20People%20That%20Can%20Be%20Seen%20in%20a%20Grid/README_EN.md
tags:
    - Stack
    - Array
    - Matrix
    - Monotonic Stack
---

# [2282. Number of People That Can Be Seen in a Grid 🔒](https://leetcode.com/problems/number-of-people-that-can-be-seen-in-a-grid)

[中文文档](/solution/2200-2299/2282.Number%20of%20People%20That%20Can%20Be%20Seen%20in%20a%20Grid/README.md)

## Description

<p>You are given an <code>m x n</code> <strong>0-indexed</strong> 2D array of positive integers <code>heights</code> where <code>heights[i][j]</code> is the height of the person standing at position <code>(i, j)</code>.</p>

<p>A person standing at position <code>(row<sub>1</sub>, col<sub>1</sub>)</code> can see a person standing at position <code>(row<sub>2</sub>, col<sub>2</sub>)</code> if:</p>

<ul>
	<li>The person at <code>(row<sub>2</sub>, col<sub>2</sub>)</code> is to the right <strong>or</strong> below the person at <code>(row<sub>1</sub>, col<sub>1</sub>)</code>. More formally, this means that either <code>row<sub>1</sub> == row<sub>2</sub></code> and <code>col<sub>1</sub> &lt; col<sub>2</sub></code> <strong>or</strong> <code>row<sub>1</sub> &lt; row<sub>2</sub></code> and <code>col<sub>1</sub> == col<sub>2</sub></code>.</li>
	<li>Everyone in between them is shorter than <strong>both</strong> of them.</li>
</ul>

<p>Return<em> an </em><code>m x n</code><em> 2D array of integers </em><code>answer</code><em> where </em><code>answer[i][j]</code><em> is the number of people that the person at position </em><code>(i, j)</code><em> can see.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2282.Number%20of%20People%20That%20Can%20Be%20Seen%20in%20a%20Grid/images/image-20220524180458-1.png" style="width: 700px; height: 164px;" />
<pre>
<strong>Input:</strong> heights = [[3,1,4,2,5]]
<strong>Output:</strong> [[2,1,2,1,0]]
<strong>Explanation:</strong>
- The person at (0, 0) can see the people at (0, 1) and (0, 2).
  Note that he cannot see the person at (0, 4) because the person at (0, 2) is taller than him.
- The person at (0, 1) can see the person at (0, 2).
- The person at (0, 2) can see the people at (0, 3) and (0, 4).
- The person at (0, 3) can see the person at (0, 4).
- The person at (0, 4) cannot see anybody.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2282.Number%20of%20People%20That%20Can%20Be%20Seen%20in%20a%20Grid/images/image-20220523113533-2.png" style="width: 400px; height: 249px;" />
<pre>
<strong>Input:</strong> heights = [[5,1],[3,1],[4,1]]
<strong>Output:</strong> [[3,1],[2,1],[1,0]]
<strong>Explanation:</strong>
- The person at (0, 0) can see the people at (0, 1), (1, 0) and (2, 0).
- The person at (0, 1) can see the person at (1, 1).
- The person at (1, 0) can see the people at (1, 1) and (2, 0).
- The person at (1, 1) can see the person at (2, 1).
- The person at (2, 0) can see the person at (2, 1).
- The person at (2, 1) cannot see anybody.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= heights.length &lt;= 400</code></li>
	<li><code>1 &lt;= heights[i].length &lt;= 400</code></li>
	<li><code>1 &lt;= heights[i][j] &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

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

<!-- end -->
