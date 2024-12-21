---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3205.Maximum%20Array%20Hopping%20Score%20I/README_EN.md
tags:
    - Stack
    - Greedy
    - Array
    - Dynamic Programming
    - Monotonic Stack
---

<!-- problem:start -->

# [3205. Maximum Array Hopping Score I 🔒](https://leetcode.com/problems/maximum-array-hopping-score-i)

[中文文档](/solution/3200-3299/3205.Maximum%20Array%20Hopping%20Score%20I/README.md)

## Description

<!-- description:start -->

<p>Given an array <code>nums</code>, you have to get the <strong>maximum</strong> score starting from index 0 and <strong>hopping</strong> until you reach the last element of the array.</p>

<p>In each <strong>hop</strong>, you can jump from index <code>i</code> to an index <code>j &gt; i</code>, and you get a <strong>score</strong> of <code>(j - i) * nums[j]</code>.</p>

<p>Return the <em>maximum score</em> you can get.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,5,8]</span></p>

<p><strong>Output:</strong> <span class="example-io">16</span></p>

<p><strong>Explanation:</strong></p>

<p>There are two possible ways to reach the last element:</p>

<ul>
	<li><code>0 -&gt; 1 -&gt; 2</code> with a score of&nbsp;<code>(1 - 0) * 5 + (2 - 1) * 8 = 13</code>.</li>
	<li><code>0 -&gt; 2</code> with a score of&nbsp;<code>(2 - 0) * 8 =&nbsp;16</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [4,5,2,8,9,1,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">42</span></p>

<p><strong>Explanation:</strong></p>

<p>We can do the hopping <code>0 -&gt; 4 -&gt; 6</code> with a score of&nbsp;<code>(4 - 0) * 9 + (6 - 4) * 3 = 42</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 10<sup>3</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Memoization Search

We design a function $\textit{dfs}(i)$, which represents the maximum score that can be obtained starting from index $i$. Therefore, the answer is $\textit{dfs}(0)$.

The execution process of the function $\textit{dfs}(i)$ is as follows:

We enumerate the next jump position $j$. Thus, the score that can be obtained starting from index $i$ is $(j - i) \times \textit{nums}[j]$, plus the maximum score that can be obtained starting from index $j$, making the total score $(j - i) \times \textit{nums}[j] + \textit{dfs}(j)$. We enumerate all possible $j$ and take the maximum score.

To avoid redundant calculations, we use memoization search. We save the calculated value of $\textit{dfs}(i)$, so it can be directly returned next time.

The time complexity is $O(n^2)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxScore(self, nums: List[int]) -> int:
        @cache
        def dfs(i: int) -> int:
            return max(
                [(j - i) * nums[j] + dfs(j) for j in range(i + 1, len(nums))] or [0]
            )

        return dfs(0)
```

#### Java

```java
class Solution {
    private Integer[] f;
    private int[] nums;
    private int n;

    public int maxScore(int[] nums) {
        n = nums.length;
        f = new Integer[n];
        this.nums = nums;
        return dfs(0);
    }

    private int dfs(int i) {
        if (f[i] != null) {
            return f[i];
        }
        f[i] = 0;
        for (int j = i + 1; j < n; ++j) {
            f[i] = Math.max(f[i], (j - i) * nums[j] + dfs(j));
        }
        return f[i];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxScore(vector<int>& nums) {
        int n = nums.size();
        vector<int> f(n);
        auto dfs = [&](this auto&& dfs, int i) -> int {
            if (f[i]) {
                return f[i];
            }
            for (int j = i + 1; j < n; ++j) {
                f[i] = max(f[i], (j - i) * nums[j] + dfs(j));
            }
            return f[i];
        };
        return dfs(0);
    }
};
```

#### Go

```go
func maxScore(nums []int) int {
	n := len(nums)
	f := make([]int, n)
	var dfs func(int) int
	dfs = func(i int) int {
		if f[i] > 0 {
			return f[i]
		}
		for j := i + 1; j < n; j++ {
			f[i] = max(f[i], (j-i)*nums[j]+dfs(j))
		}
		return f[i]
	}
	return dfs(0)
}
```

#### TypeScript

```ts
function maxScore(nums: number[]): number {
    const n = nums.length;
    const f: number[] = Array(n).fill(0);
    const dfs = (i: number): number => {
        if (f[i]) {
            return f[i];
        }
        for (let j = i + 1; j < n; ++j) {
            f[i] = Math.max(f[i], (j - i) * nums[j] + dfs(j));
        }
        return f[i];
    };
    return dfs(0);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Dynamic Programming

We can transform the memoization search from Solution 1 into dynamic programming.

Define $f[j]$ as the maximum score that can be obtained starting from index $0$ and ending at index $j$. Therefore, the answer is $f[n - 1]$.

The state transition equation is:

$$
f[j] = \max_{0 \leq i < j} \{ f[i] + (j - i) \times \textit{nums}[j] \}
$$

The time complexity is $O(n^2)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxScore(self, nums: List[int]) -> int:
        n = len(nums)
        f = [0] * n
        for j in range(1, n):
            for i in range(j):
                f[j] = max(f[j], f[i] + (j - i) * nums[j])
        return f[n - 1]
```

#### Java

```java
class Solution {
    public int maxScore(int[] nums) {
        int n = nums.length;
        int[] f = new int[n];
        for (int j = 1; j < n; ++j) {
            for (int i = 0; i < j; ++i) {
                f[j] = Math.max(f[j], f[i] + (j - i) * nums[j]);
            }
        }
        return f[n - 1];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxScore(vector<int>& nums) {
        int n = nums.size();
        vector<int> f(n);
        for (int j = 1; j < n; ++j) {
            for (int i = 0; i < j; ++i) {
                f[j] = max(f[j], f[i] + (j - i) * nums[j]);
            }
        }
        return f[n - 1];
    }
};
```

#### Go

```go
func maxScore(nums []int) int {
	n := len(nums)
	f := make([]int, n)
	for j := 1; j < n; j++ {
		for i := 0; i < j; i++ {
			f[j] = max(f[j], f[i]+(j-i)*nums[j])
		}
	}
	return f[n-1]
}
```

#### TypeScript

```ts
function maxScore(nums: number[]): number {
    const n = nums.length;
    const f: number[] = Array(n).fill(0);
    for (let j = 1; j < n; ++j) {
        for (let i = 0; i < j; ++i) {
            f[j] = Math.max(f[j], f[i] + (j - i) * nums[j]);
        }
    }
    return f[n - 1];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 3: Monotonic Stack

We observe that for the current position $i$, we should jump to the next position $j$ with the maximum value to obtain the maximum score.

Therefore, we traverse the array $\textit{nums}$, maintaining a stack $\textit{stk}$ that is monotonically decreasing from the bottom to the top of the stack. For the current position $i$ being traversed, if the value corresponding to the top element of the stack is less than or equal to $\textit{nums}[i]$, we continuously pop the top element of the stack until the stack is empty or the value corresponding to the top element of the stack is greater than $\textit{nums}[i]$, and then push $i$ into the stack.

Next, we initialize the answer $\textit{ans}$ and the current position $i = 0$, traverse the elements in the stack, each time taking out the top element $j$, updating the answer $\textit{ans} += \textit{nums}[j] \times (j - i)$, and then updating $i = j$.

Finally, return the answer $\textit{ans}$.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxScore(self, nums: List[int]) -> int:
        stk = []
        for i, x in enumerate(nums):
            while stk and nums[stk[-1]] <= x:
                stk.pop()
            stk.append(i)
        ans = i = 0
        for j in stk:
            ans += nums[j] * (j - i)
            i = j
        return ans
```

#### Java

```java
class Solution {
    public int maxScore(int[] nums) {
        Deque<Integer> stk = new ArrayDeque<>();
        for (int i = 0; i < nums.length; ++i) {
            while (!stk.isEmpty() && nums[stk.peek()] <= nums[i]) {
                stk.pop();
            }
            stk.push(i);
        }
        int ans = 0, i = 0;
        while (!stk.isEmpty()) {
            int j = stk.pollLast();
            ans += (j - i) * nums[j];
            i = j;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxScore(vector<int>& nums) {
        vector<int> stk;
        for (int i = 0; i < nums.size(); ++i) {
            while (stk.size() && nums[stk.back()] <= nums[i]) {
                stk.pop_back();
            }
            stk.push_back(i);
        }
        int ans = 0, i = 0;
        for (int j : stk) {
            ans += (j - i) * nums[j];
            i = j;
        }
        return ans;
    }
};
```

#### Go

```go
func maxScore(nums []int) (ans int) {
	stk := []int{}
	for i, x := range nums {
		for len(stk) > 0 && nums[stk[len(stk)-1]] <= x {
			stk = stk[:len(stk)-1]
		}
		stk = append(stk, i)
	}
	i := 0
	for _, j := range stk {
		ans += (j - i) * nums[j]
		i = j
	}
	return
}
```

#### TypeScript

```ts
function maxScore(nums: number[]): number {
    const stk: number[] = [];
    for (let i = 0; i < nums.length; ++i) {
        while (stk.length && nums[stk.at(-1)!] <= nums[i]) {
            stk.pop();
        }
        stk.push(i);
    }
    let ans = 0;
    let i = 0;
    for (const j of stk) {
        ans += (j - i) * nums[j];
        i = j;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
