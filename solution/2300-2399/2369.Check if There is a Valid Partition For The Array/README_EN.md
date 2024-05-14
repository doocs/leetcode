---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2300-2399/2369.Check%20if%20There%20is%20a%20Valid%20Partition%20For%20The%20Array/README_EN.md
rating: 1779
tags:
    - Array
    - Dynamic Programming
---

# [2369. Check if There is a Valid Partition For The Array](https://leetcode.com/problems/check-if-there-is-a-valid-partition-for-the-array)

[中文文档](/solution/2300-2399/2369.Check%20if%20There%20is%20a%20Valid%20Partition%20For%20The%20Array/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code>. You have to partition the array into one or more <strong>contiguous</strong> subarrays.</p>

<p>We call a partition of the array <strong>valid</strong> if each of the obtained subarrays satisfies <strong>one</strong> of the following conditions:</p>

<ol>
	<li>The subarray consists of <strong>exactly</strong> <code>2,</code> equal elements. For example, the subarray <code>[2,2]</code> is good.</li>
	<li>The subarray consists of <strong>exactly</strong> <code>3,</code> equal elements. For example, the subarray <code>[4,4,4]</code> is good.</li>
	<li>The subarray consists of <strong>exactly</strong> <code>3</code> consecutive increasing elements, that is, the difference between adjacent elements is <code>1</code>. For example, the subarray <code>[3,4,5]</code> is good, but the subarray <code>[1,3,5]</code> is not.</li>
</ol>

<p>Return <code>true</code><em> if the array has <strong>at least</strong> one valid partition</em>. Otherwise, return <code>false</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [4,4,4,5,6]
<strong>Output:</strong> true
<strong>Explanation:</strong> The array can be partitioned into the subarrays [4,4] and [4,5,6].
This partition is valid, so we return true.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,1,2]
<strong>Output:</strong> false
<strong>Explanation:</strong> There is no valid partition for this array.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
</ul>

## Solutions

### Solution 1: Memoization Search

We design a function $dfs(i)$, which represents whether there is a valid partition starting from index $i$. So the answer is $dfs(0)$.

The execution process of the function $dfs(i)$ is as follows:

-   If $i \ge n$, return $true$.
-   If the elements at index $i$ and $i+1$ are equal, we can choose to make $i$ and $i+1$ a subarray, and recursively call $dfs(i+2)$.
-   If the elements at index $i$, $i+1$ and $i+2$ are equal, we can choose to make $i$, $i+1$ and $i+2$ a subarray, and recursively call $dfs(i+3)$.
-   If the elements at index $i$, $i+1$ and $i+2$ increase by $1$ in turn, we can choose to make $i$, $i+1$ and $i+2$ a subarray, and recursively call $dfs(i+3)$.
-   If none of the above conditions are met, return $false$, otherwise return $true$.

That is:

$$
dfs(i) = \text{OR}
\begin{cases}
true,&i \ge n\\
dfs(i+2),&i+1 < n\ \text{and}\ \textit{nums}[i] = \textit{nums}[i+1]\\
dfs(i+3),&i+2 < n\ \text{and}\ \textit{nums}[i] = \textit{nums}[i+1] = \textit{nums}[i+2]\\
dfs(i+3),&i+2 < n\ \text{and}\ \textit{nums}[i+1] - \textit{nums}[i] = 1\ \text{and}\ \textit{nums}[i+2] - \textit{nums}[i+1] = 1
\end{cases}
$$

To avoid repeated calculations, we use the method of memoization search.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Where $n$ is the length of the array.

<!-- tabs:start -->

```python
class Solution:
    def validPartition(self, nums: List[int]) -> bool:
        @cache
        def dfs(i: int) -> bool:
            if i >= n:
                return True
            a = i + 1 < n and nums[i] == nums[i + 1]
            b = i + 2 < n and nums[i] == nums[i + 1] == nums[i + 2]
            c = (
                i + 2 < n
                and nums[i + 1] - nums[i] == 1
                and nums[i + 2] - nums[i + 1] == 1
            )
            return (a and dfs(i + 2)) or ((b or c) and dfs(i + 3))

        n = len(nums)
        return dfs(0)
```

```java
class Solution {
    private int n;
    private int[] nums;
    private Boolean[] f;

    public boolean validPartition(int[] nums) {
        n = nums.length;
        this.nums = nums;
        f = new Boolean[n];
        return dfs(0);
    }

    private boolean dfs(int i) {
        if (i >= n) {
            return true;
        }
        if (f[i] != null) {
            return f[i];
        }
        boolean a = i + 1 < n && nums[i] == nums[i + 1];
        boolean b = i + 2 < n && nums[i] == nums[i + 1] && nums[i + 1] == nums[i + 2];
        boolean c = i + 2 < n && nums[i + 1] - nums[i] == 1 && nums[i + 2] - nums[i + 1] == 1;
        return f[i] = ((a && dfs(i + 2)) || ((b || c) && dfs(i + 3)));
    }
}
```

```cpp
class Solution {
public:
    bool validPartition(vector<int>& nums) {
        n = nums.size();
        this->nums = nums;
        f.assign(n, -1);
        return dfs(0);
    }

private:
    int n;
    vector<int> f;
    vector<int> nums;

    bool dfs(int i) {
        if (i >= n) {
            return true;
        }
        if (f[i] != -1) {
            return f[i] == 1;
        }
        bool a = i + 1 < n && nums[i] == nums[i + 1];
        bool b = i + 2 < n && nums[i] == nums[i + 1] && nums[i + 1] == nums[i + 2];
        bool c = i + 2 < n && nums[i + 1] - nums[i] == 1 && nums[i + 2] - nums[i + 1] == 1;
        f[i] = ((a && dfs(i + 2)) || ((b || c) && dfs(i + 3))) ? 1 : 0;
        return f[i] == 1;
    }
};
```

```go
func validPartition(nums []int) bool {
	n := len(nums)
	f := make([]int, n)
	for i := range f {
		f[i] = -1
	}
	var dfs func(int) bool
	dfs = func(i int) bool {
		if i == n {
			return true
		}
		if f[i] != -1 {
			return f[i] == 1
		}
		a := i+1 < n && nums[i] == nums[i+1]
		b := i+2 < n && nums[i] == nums[i+1] && nums[i+1] == nums[i+2]
		c := i+2 < n && nums[i+1]-nums[i] == 1 && nums[i+2]-nums[i+1] == 1
		f[i] = 0
		if a && dfs(i+2) || b && dfs(i+3) || c && dfs(i+3) {
			f[i] = 1
		}
		return f[i] == 1
	}
	return dfs(0)
}
```

```ts
function validPartition(nums: number[]): boolean {
    const n = nums.length;
    const f: number[] = Array(n).fill(-1);
    const dfs = (i: number): boolean => {
        if (i >= n) {
            return true;
        }
        if (f[i] !== -1) {
            return f[i] === 1;
        }
        const a = i + 1 < n && nums[i] == nums[i + 1];
        const b = i + 2 < n && nums[i] == nums[i + 1] && nums[i + 1] == nums[i + 2];
        const c = i + 2 < n && nums[i + 1] - nums[i] == 1 && nums[i + 2] - nums[i + 1] == 1;
        f[i] = (a && dfs(i + 2)) || ((b || c) && dfs(i + 3)) ? 1 : 0;
        return f[i] == 1;
    };
    return dfs(0);
}
```

<!-- tabs:end -->

### Solution 2: Dynamic Programming

We can convert the memoization search in Solution 1 into dynamic programming.

Let $f[i]$ represent whether there is a valid partition for the first $i$ elements of the array. Initially, $f[0] = true$, and the answer is $f[n]$.

The state transition equation is as follows:

$$
f[i] = \text{OR}
\begin{cases}
true,&i = 0\\
f[i-2],&i-2 \ge 0\ \text{and}\ \textit{nums}[i-1] = \textit{nums}[i-2]\\
f[i-3],&i-3 \ge 0\ \text{and}\ \textit{nums}[i-1] = \textit{nums}[i-2] = \textit{nums}[i-3]\\
f[i-3],&i-3 \ge 0\ \text{and}\ \textit{nums}[i-1] - \textit{nums}[i-2] = 1\ \text{and}\ \textit{nums}[i-2] - \textit{nums}[i-3] = 1
\end{cases}
$$

The time complexity is $O(n)$, and the space complexity is $O(n)$. Where $n$ is the length of the array.

<!-- tabs:start -->

```python
class Solution:
    def validPartition(self, nums: List[int]) -> bool:
        n = len(nums)
        f = [True] + [False] * n
        for i, x in enumerate(nums, 1):
            a = i - 2 >= 0 and nums[i - 2] == x
            b = i - 3 >= 0 and nums[i - 3] == nums[i - 2] == x
            c = i - 3 >= 0 and x - nums[i - 2] == 1 and nums[i - 2] - nums[i - 3] == 1
            f[i] = (a and f[i - 2]) or ((b or c) and f[i - 3])
        return f[n]
```

```java
class Solution {
    public boolean validPartition(int[] nums) {
        int n = nums.length;
        boolean[] f = new boolean[n + 1];
        f[0] = true;
        for (int i = 1; i <= n; ++i) {
            boolean a = i - 2 >= 0 && nums[i - 1] == nums[i - 2];
            boolean b = i - 3 >= 0 && nums[i - 1] == nums[i - 2] && nums[i - 2] == nums[i - 3];
            boolean c
                = i - 3 >= 0 && nums[i - 1] - nums[i - 2] == 1 && nums[i - 2] - nums[i - 3] == 1;
            f[i] = (a && f[i - 2]) || ((b || c) && f[i - 3]);
        }
        return f[n];
    }
}
```

```cpp
class Solution {
public:
    bool validPartition(vector<int>& nums) {
        int n = nums.size();
        vector<bool> f(n + 1);
        f[0] = true;
        for (int i = 1; i <= n; ++i) {
            bool a = i - 2 >= 0 && nums[i - 1] == nums[i - 2];
            bool b = i - 3 >= 0 && nums[i - 1] == nums[i - 2] && nums[i - 2] == nums[i - 3];
            bool c = i - 3 >= 0 && nums[i - 1] - nums[i - 2] == 1 && nums[i - 2] - nums[i - 3] == 1;
            f[i] = (a && f[i - 2]) || ((b || c) && f[i - 3]);
        }
        return f[n];
    }
};
```

```go
func validPartition(nums []int) bool {
	n := len(nums)
	f := make([]bool, n+1)
	f[0] = true
	for i := 1; i <= n; i++ {
		x := nums[i-1]
		a := i-2 >= 0 && nums[i-2] == x
		b := i-3 >= 0 && nums[i-3] == nums[i-2] && nums[i-2] == x
		c := i-3 >= 0 && x-nums[i-2] == 1 && nums[i-2]-nums[i-3] == 1
		f[i] = (a && f[i-2]) || ((b || c) && f[i-3])
	}
	return f[n]
}
```

```ts
function validPartition(nums: number[]): boolean {
    const n = nums.length;
    const f: boolean[] = Array(n + 1).fill(false);
    f[0] = true;
    for (let i = 1; i <= n; ++i) {
        const a = i - 2 >= 0 && nums[i - 1] === nums[i - 2];
        const b = i - 3 >= 0 && nums[i - 1] === nums[i - 2] && nums[i - 2] === nums[i - 3];
        const c = i - 3 >= 0 && nums[i - 1] - nums[i - 2] === 1 && nums[i - 2] - nums[i - 3] === 1;
        f[i] = (a && f[i - 2]) || ((b || c) && f[i - 3]);
    }
    return f[n];
}
```

<!-- tabs:end -->

<!-- end -->
