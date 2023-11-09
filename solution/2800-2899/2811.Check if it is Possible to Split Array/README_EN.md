# [2811. Check if it is Possible to Split Array](https://leetcode.com/problems/check-if-it-is-possible-to-split-array)

[中文文档](/solution/2800-2899/2811.Check%20if%20it%20is%20Possible%20to%20Split%20Array/README.md)

## Description

<p>You are given an array <code>nums</code> of length <code>n</code> and an integer <code>m</code>. You need to determine if it is possible to split the array into <code>n</code> <strong>non-empty</strong> arrays by performing a series of steps.</p>

<p>In each step, you can select an existing array (which may be the result of previous steps) with a length of <strong>at least two</strong> and split it into <strong>two </strong>subarrays, if, <strong>for each </strong>resulting subarray, <strong>at least</strong> one of the following holds:</p>

<ul>
	<li>The length of the subarray is one, or</li>
	<li>The sum of elements of the subarray is <strong>greater than or equal</strong> to <code>m</code>.</li>
</ul>

<p>Return <code>true</code><em> if you can split the given array into </em><code>n</code><em> arrays, otherwise return</em> <code>false</code>.</p>

<p><strong>Note:</strong> A subarray is <em>a contiguous non-empty sequence of elements within an array</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [2, 2, 1], m = 4
<strong>Output:</strong> true
<strong>Explanation:</strong> We can split the array into [2, 2] and [1] in the first step. Then, in the second step, we can split [2, 2] into [2] and [2]. As a result, the answer is true.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [2, 1, 3], m = 5 
<strong>Output:</strong> false
<strong>Explanation: </strong>We can try splitting the array in two different ways: the first way is to have [2, 1] and [3], and the second way is to have [2] and [1, 3]. However, both of these ways are not valid. So, the answer is false.</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [2, 3, 3, 2, 3], m = 6
<strong>Output:</strong> true
<strong>Explanation:</strong> We can split the array into [2, 3, 3, 2] and [3] in the first step. Then, in the second step, we can split [2, 3, 3, 2] into [2, 3, 3] and [2]. Then, in the third step, we can split [2, 3, 3] into [2] and [3, 3]. And in the last step we can split [3, 3] into [3] and [3]. As a result, the answer is true.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
	<li><code>1 &lt;= m &lt;= 200</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def canSplitArray(self, nums: List[int], m: int) -> bool:
        @cache
        def dfs(i: int, j: int) -> bool:
            if i == j:
                return True
            for k in range(i, j):
                a = k == i or s[k + 1] - s[i] >= m
                b = k == j - 1 or s[j + 1] - s[k + 1] >= m
                if a and b and dfs(i, k) and dfs(k + 1, j):
                    return True
            return False

        s = list(accumulate(nums, initial=0))
        return dfs(0, len(nums) - 1)
```

### **Java**

```java
class Solution {
    private Boolean[][] f;
    private int[] s;
    private int m;

    public boolean canSplitArray(List<Integer> nums, int m) {
        int n = nums.size();
        f = new Boolean[n][n];
        s = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            s[i] = s[i - 1] + nums.get(i - 1);
        }
        this.m = m;
        return dfs(0, n - 1);
    }

    private boolean dfs(int i, int j) {
        if (i == j) {
            return true;
        }
        if (f[i][j] != null) {
            return f[i][j];
        }
        for (int k = i; k < j; ++k) {
            boolean a = k == i || s[k + 1] - s[i] >= m;
            boolean b = k == j - 1 || s[j + 1] - s[k + 1] >= m;
            if (a && b && dfs(i, k) && dfs(k + 1, j)) {
                return f[i][j] = true;
            }
        }
        return f[i][j] = false;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool canSplitArray(vector<int>& nums, int m) {
        int n = nums.size();
        vector<int> s(n + 1);
        for (int i = 1; i <= n; ++i) {
            s[i] = s[i - 1] + nums[i - 1];
        }
        int f[n][n];
        memset(f, -1, sizeof f);
        function<bool(int, int)> dfs = [&](int i, int j) {
            if (i == j) {
                return true;
            }
            if (f[i][j] != -1) {
                return f[i][j] == 1;
            }
            for (int k = i; k < j; ++k) {
                bool a = k == i || s[k + 1] - s[i] >= m;
                bool b = k == j - 1 || s[j + 1] - s[k + 1] >= m;
                if (a && b && dfs(i, k) && dfs(k + 1, j)) {
                    f[i][j] = 1;
                    return true;
                }
            }
            f[i][j] = 0;
            return false;
        };
        return dfs(0, n - 1);
    }
};
```

### **Go**

```go
func canSplitArray(nums []int, m int) bool {
	n := len(nums)
	f := make([][]int, n)
	s := make([]int, n+1)
	for i, x := range nums {
		s[i+1] = s[i] + x
	}
	for i := range f {
		f[i] = make([]int, n)
	}
	var dfs func(i, j int) bool
	dfs = func(i, j int) bool {
		if i == j {
			return true
		}
		if f[i][j] != 0 {
			return f[i][j] == 1
		}
		for k := i; k < j; k++ {
			a := k == i || s[k+1]-s[i] >= m
			b := k == j-1 || s[j+1]-s[k+1] >= m
			if a && b && dfs(i, k) && dfs(k+1, j) {
				f[i][j] = 1
				return true
			}
		}
		f[i][j] = -1
		return false
	}
	return dfs(0, n-1)
}
```

### **TypeScript**

```ts
function canSplitArray(nums: number[], m: number): boolean {
    const n = nums.length;
    const s: number[] = new Array(n + 1).fill(0);
    for (let i = 1; i <= n; ++i) {
        s[i] = s[i - 1] + nums[i - 1];
    }
    const f: number[][] = Array(n)
        .fill(0)
        .map(() => Array(n).fill(-1));
    const dfs = (i: number, j: number): boolean => {
        if (i === j) {
            return true;
        }
        if (f[i][j] !== -1) {
            return f[i][j] === 1;
        }
        for (let k = i; k < j; ++k) {
            const a = k === i || s[k + 1] - s[i] >= m;
            const b = k === j - 1 || s[j + 1] - s[k + 1] >= m;
            if (a && b && dfs(i, k) && dfs(k + 1, j)) {
                f[i][j] = 1;
                return true;
            }
        }
        f[i][j] = 0;
        return false;
    };
    return dfs(0, n - 1);
}
```

```ts
function canSplitArray(nums: number[], m: number): boolean {
    const n = nums.length;
    if (n <= 2) {
        return true;
    }
    for (let i = 1; i < n; i++) {
        if (nums[i - 1] + nums[i] >= m) {
            return true;
        }
    }
    return false;
}
```

### **Rust**

```rust
impl Solution {
    pub fn can_split_array(nums: Vec<i32>, m: i32) -> bool {
        let n = nums.len();
        if n <= 2 {
            return true;
        }
        for i in 1..n {
            if nums[i - 1] + nums[i] >= m {
                return true;
            }
        }
        false
    }
}
```

### **...**

```

```

<!-- tabs:end -->
