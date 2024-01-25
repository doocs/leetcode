# [1944. 队列中可以看到的人数](https://leetcode.cn/problems/number-of-visible-people-in-a-queue)

[English Version](/solution/1900-1999/1944.Number%20of%20Visible%20People%20in%20a%20Queue/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>有&nbsp;<code>n</code>&nbsp;个人排成一个队列，<strong>从左到右</strong>&nbsp;编号为&nbsp;<code>0</code>&nbsp;到&nbsp;<code>n - 1</code>&nbsp;。给你以一个整数数组&nbsp;<code>heights</code>&nbsp;，每个整数 <strong>互不相同</strong>，<code>heights[i]</code>&nbsp;表示第&nbsp;<code>i</code>&nbsp;个人的高度。</p>

<p>一个人能 <strong>看到</strong> 他右边另一个人的条件是这两人之间的所有人都比他们两人 <strong>矮</strong>&nbsp;。更正式的，第&nbsp;<code>i</code>&nbsp;个人能看到第&nbsp;<code>j</code>&nbsp;个人的条件是&nbsp;<code>i &lt; j</code>&nbsp;且&nbsp;<code>min(heights[i], heights[j]) &gt; max(heights[i+1], heights[i+2], ..., heights[j-1])</code>&nbsp;。</p>

<p>请你返回一个长度为 <code>n</code>&nbsp;的数组<em>&nbsp;</em><code>answer</code><em>&nbsp;</em>，其中<em>&nbsp;</em><code>answer[i]</code><em>&nbsp;</em>是第&nbsp;<code>i</code>&nbsp;个人在他右侧队列中能&nbsp;<strong>看到</strong>&nbsp;的&nbsp;<strong>人数</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1944.Number%20of%20Visible%20People%20in%20a%20Queue/images/queue-plane.jpg" style="width: 600px; height: 247px;" /></p>

<pre>
<b>输入：</b>heights = [10,6,8,5,11,9]
<b>输出：</b>[3,1,2,1,1,0]
<strong>解释：</strong>
第 0 个人能看到编号为 1 ，2 和 4 的人。
第 1 个人能看到编号为 2 的人。
第 2 个人能看到编号为 3 和 4 的人。
第 3 个人能看到编号为 4 的人。
第 4 个人能看到编号为 5 的人。
第 5 个人谁也看不到因为他右边没人。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>heights = [5,1,2,3,10]
<b>输出：</b>[4,1,1,1,0]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == heights.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= heights[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>heights</code>&nbsp;中所有数 <strong>互不相同</strong>&nbsp;。</li>
</ul>

## 解法

### 方法一：单调栈

我们观察发现，对于第 $i$ 个人来说，他能看到的人一定是按从左到右高度严格单调递增的。

因此，我们可以倒序遍历数组 $heights$，用一个从栈顶到栈底单调递增的栈 $stk$ 记录已经遍历过的人的高度。

对于第 $i$ 个人，如果栈不为空并且栈顶元素小于 $heights[i]$，累加当前第 $i$ 个人能看到的人数，然后将栈顶元素出栈，直到栈为空或者栈顶元素大于等于 $heights[i]$。如果此时栈不为空，说明栈顶元素大于等于 $heights[i]$，那么第 $i$ 个人能看到的人数还要再加 $1$。

接下来，我们将 $heights[i]$ 入栈，继续遍历下一个人。

遍历结束后，返回答案数组 $ans$。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是数组 $heights$ 的长度。

相似题目：

-   [2282. 在一个网格中可以看到的人数](https://github.com/doocs/leetcode/blob/main/solution/2200-2299/2282.Number%20of%20People%20That%20Can%20Be%20Seen%20in%20a%20Grid/README.md)

<!-- tabs:start -->

```python
class Solution:
    def canSeePersonsCount(self, heights: List[int]) -> List[int]:
        n = len(heights)
        ans = [0] * n
        stk = []
        for i in range(n - 1, -1, -1):
            while stk and stk[-1] < heights[i]:
                ans[i] += 1
                stk.pop()
            if stk:
                ans[i] += 1
            stk.append(heights[i])
        return ans
```

```java
class Solution {
    public int[] canSeePersonsCount(int[] heights) {
        int n = heights.length;
        int[] ans = new int[n];
        Deque<Integer> stk = new ArrayDeque<>();
        for (int i = n - 1; i >= 0; --i) {
            while (!stk.isEmpty() && stk.peek() < heights[i]) {
                stk.pop();
                ++ans[i];
            }
            if (!stk.isEmpty()) {
                ++ans[i];
            }
            stk.push(heights[i]);
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    vector<int> canSeePersonsCount(vector<int>& heights) {
        int n = heights.size();
        vector<int> ans(n);
        stack<int> stk;
        for (int i = n - 1; ~i; --i) {
            while (stk.size() && stk.top() < heights[i]) {
                ++ans[i];
                stk.pop();
            }
            if (stk.size()) {
                ++ans[i];
            }
            stk.push(heights[i]);
        }
        return ans;
    }
};
```

```go
func canSeePersonsCount(heights []int) []int {
	n := len(heights)
	ans := make([]int, n)
	stk := []int{}
	for i := n - 1; i >= 0; i-- {
		for len(stk) > 0 && stk[len(stk)-1] < heights[i] {
			ans[i]++
			stk = stk[:len(stk)-1]
		}
		if len(stk) > 0 {
			ans[i]++
		}
		stk = append(stk, heights[i])
	}
	return ans
}
```

```ts
function canSeePersonsCount(heights: number[]): number[] {
    const n = heights.length;
    const ans: number[] = new Array(n).fill(0);
    const stk: number[] = [];
    for (let i = n - 1; ~i; --i) {
        while (stk.length && stk.at(-1) < heights[i]) {
            ++ans[i];
            stk.pop();
        }
        if (stk.length) {
            ++ans[i];
        }
        stk.push(heights[i]);
    }
    return ans;
}
```

```rust
impl Solution {
    pub fn can_see_persons_count(heights: Vec<i32>) -> Vec<i32> {
        let n = heights.len();
        let mut ans = vec![0; n];
        let mut stack = Vec::new();
        for i in (0..n).rev() {
            while !stack.is_empty() {
                ans[i] += 1;
                if heights[i] <= heights[*stack.last().unwrap()] {
                    break;
                }
                stack.pop();
            }
            stack.push(i);
        }
        ans
    }
}
```

```c
/**
 * Note: The returned array must be malloced, assume caller calls free().
 */
int* canSeePersonsCount(int* heights, int heightsSize, int* returnSize) {
    int* ans = malloc(sizeof(int) * heightsSize);
    memset(ans, 0, sizeof(int) * heightsSize);
    int stack[heightsSize];
    int i = 0;
    for (int j = heightsSize - 1; j >= 0; j--) {
        while (i) {
            ans[j]++;
            if (heights[j] <= heights[stack[i - 1]]) {
                break;
            }
            i--;
        }
        stack[i++] = j;
    }
    *returnSize = heightsSize;
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
