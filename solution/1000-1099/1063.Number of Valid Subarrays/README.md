# [1063. 有效子数组的数目 🔒](https://leetcode.cn/problems/number-of-valid-subarrays)

[English Version](/solution/1000-1099/1063.Number%20of%20Valid%20Subarrays/README_EN.md)

<!-- tags:栈,数组,单调栈 -->

<!-- difficulty:困难 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个整数数组&nbsp;<code>nums</code>&nbsp;，返回满足下面条件的&nbsp;<em>非空、连续</em><strong>&nbsp;子数组</strong>的数目：</p>

<ul>
	<li><strong>子数组&nbsp;</strong>是数组的 <strong>连续</strong> 部分。</li>
	<li><em>子数组最左边的元素不大于子数组中的其他元素</em>&nbsp;。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,4,2,5,3]
<strong>输出：</strong>11
<strong>解释：</strong>有 11 个有效子数组，分别是：[1],[4],[2],[5],[3],[1,4],[2,5],[1,4,2],[2,5,3],[1,4,2,5],[1,4,2,5,3] 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [3,2,1]
<strong>输出：</strong>3
<strong>解释：</strong>有 3 个有效子数组，分别是：[3],[2],[1] 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,2,2]
<strong>输出：</strong>6
<strong>解释：</strong>有 6 个有效子数组，分别为是：[2],[2],[2],[2,2],[2,2],[2,2,2] 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

### 方法一：单调栈

题目实际上是求解每个位置 $i$ 的右边第一个小于 $nums[i]$ 的位置 $j$，那么以 $i$ 为左端点的有效子数组的个数就是 $j - i$。

我们可以使用单调栈来求解右边第一个小于 $nums[i]$ 的位置 $j$，具体做法是从右往左遍历数组，维护一个从栈顶到栈底严格单调递减的栈。如果栈不为空，并且栈顶元素大于等于 $nums[i]$，那么就将栈顶元素出栈，直到栈为空或者栈顶元素小于 $nums[i]$，此时栈顶元素就是右边第一个小于 $nums[i]$ 的位置 $j$，如果栈为空，那么 $j = n$。

接下来，我们将 $i$ 入栈，继续遍历数组，直到遍历结束，最后我们就可以得到每个位置 $i$ 的右边第一个小于 $nums[i]$ 的位置 $j$，从而得到以 $i$ 为左端点的有效子数组的个数 $j-i$，将所有的 $j-i$ 累加即可得到答案。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是数组的长度。

<!-- tabs:start -->

```python
class Solution:
    def validSubarrays(self, nums: List[int]) -> int:
        n = len(nums)
        right = [n] * n
        stk = []
        for i in range(n - 1, -1, -1):
            while stk and nums[stk[-1]] >= nums[i]:
                stk.pop()
            if stk:
                right[i] = stk[-1]
            stk.append(i)
        return sum(j - i for i, j in enumerate(right))
```

```java
class Solution {
    public int validSubarrays(int[] nums) {
        int n = nums.length;
        int[] right = new int[n];
        Arrays.fill(right, n);
        Deque<Integer> stk = new ArrayDeque<>();
        for (int i = n - 1; i >= 0; --i) {
            while (!stk.isEmpty() && nums[stk.peek()] >= nums[i]) {
                stk.pop();
            }
            if (!stk.isEmpty()) {
                right[i] = stk.peek();
            }
            stk.push(i);
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans += right[i] - i;
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int validSubarrays(vector<int>& nums) {
        int n = nums.size();
        vector<int> right(n, n);
        stack<int> stk;
        for (int i = n - 1; ~i; --i) {
            while (stk.size() && nums[stk.top()] >= nums[i]) {
                stk.pop();
            }
            if (stk.size()) {
                right[i] = stk.top();
            }
            stk.push(i);
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans += right[i] - i;
        }
        return ans;
    }
};
```

```go
func validSubarrays(nums []int) (ans int) {
	n := len(nums)
	right := make([]int, n)
	for i := range right {
		right[i] = n
	}
	stk := []int{}
	for i := n - 1; i >= 0; i-- {
		for len(stk) > 0 && nums[stk[len(stk)-1]] >= nums[i] {
			stk = stk[:len(stk)-1]
		}
		if len(stk) > 0 {
			right[i] = stk[len(stk)-1]
		}
		stk = append(stk, i)
	}
	for i, j := range right {
		ans += j - i
	}
	return
}
```

```ts
function validSubarrays(nums: number[]): number {
    const n = nums.length;
    const right: number[] = Array(n).fill(n);
    const stk: number[] = [];
    for (let i = n - 1; ~i; --i) {
        while (stk.length && nums[stk.at(-1)] >= nums[i]) {
            stk.pop();
        }
        if (stk.length) {
            right[i] = stk.at(-1)!;
        }
        stk.push(i);
    }
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        ans += right[i] - i;
    }
    return ans;
}
```

<!-- tabs:end -->

### 方法二

<!-- tabs:start -->

```python
class Solution:
    def validSubarrays(self, nums: List[int]) -> int:
        n = len(nums)
        stk = []
        ans = 0
        for i in range(n - 1, -1, -1):
            while stk and nums[stk[-1]] >= nums[i]:
                stk.pop()
            ans += (stk[-1] if stk else n) - i
            stk.append(i)
        return ans
```

```java
class Solution {
    public int validSubarrays(int[] nums) {
        int n = nums.length;
        Deque<Integer> stk = new ArrayDeque<>();
        int ans = 0;
        for (int i = n - 1; i >= 0; --i) {
            while (!stk.isEmpty() && nums[stk.peek()] >= nums[i]) {
                stk.pop();
            }
            ans += (stk.isEmpty() ? n : stk.peek()) - i;

            stk.push(i);
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int validSubarrays(vector<int>& nums) {
        int n = nums.size();
        stack<int> stk;
        int ans = 0;
        for (int i = n - 1; ~i; --i) {
            while (stk.size() && nums[stk.top()] >= nums[i]) {
                stk.pop();
            }
            ans += (stk.size() ? stk.top() : n) - i;
            stk.push(i);
        }
        return ans;
    }
};
```

```go
func validSubarrays(nums []int) (ans int) {
	n := len(nums)
	stk := []int{}
	for i := n - 1; i >= 0; i-- {
		for len(stk) > 0 && nums[stk[len(stk)-1]] >= nums[i] {
			stk = stk[:len(stk)-1]
		}
		ans -= i
		if len(stk) > 0 {
			ans += stk[len(stk)-1]
		} else {
			ans += n
		}
		stk = append(stk, i)
	}
	return
}
```

```ts
function validSubarrays(nums: number[]): number {
    const n = nums.length;
    const stk: number[] = [];
    let ans = 0;
    for (let i = n - 1; ~i; --i) {
        while (stk.length && nums[stk.at(-1)!] >= nums[i]) {
            stk.pop();
        }
        ans += (stk.at(-1) ?? n) - i;
        stk.push(i);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
