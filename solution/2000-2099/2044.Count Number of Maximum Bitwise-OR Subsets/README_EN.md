# [2044. Count Number of Maximum Bitwise-OR Subsets](https://leetcode.com/problems/count-number-of-maximum-bitwise-or-subsets)

[中文文档](/solution/2000-2099/2044.Count%20Number%20of%20Maximum%20Bitwise-OR%20Subsets/README.md)

## Description

<p>Given an integer array <code>nums</code>, find the <strong>maximum</strong> possible <strong>bitwise OR</strong> of a subset of <code>nums</code> and return <em>the <strong>number of different non-empty subsets</strong> with the maximum bitwise OR</em>.</p>

<p>An array <code>a</code> is a <strong>subset</strong> of an array <code>b</code> if <code>a</code> can be obtained from <code>b</code> by deleting some (possibly zero) elements of <code>b</code>. Two subsets are considered <strong>different</strong> if the indices of the elements chosen are different.</p>

<p>The bitwise OR of an array <code>a</code> is equal to <code>a[0] <strong>OR</strong> a[1] <strong>OR</strong> ... <strong>OR</strong> a[a.length - 1]</code> (<strong>0-indexed</strong>).</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,1]
<strong>Output:</strong> 2
<strong>Explanation:</strong> The maximum possible bitwise OR of a subset is 3. There are 2 subsets with a bitwise OR of 3:
- [3]
- [3,1]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,2,2]
<strong>Output:</strong> 7
<strong>Explanation:</strong> All non-empty subsets of [2,2,2] have a bitwise OR of 2. There are 2<sup>3</sup> - 1 = 7 total subsets.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,2,1,5]
<strong>Output:</strong> 6
<strong>Explanation:</strong> The maximum possible bitwise OR of a subset is 7. There are 6 subsets with a bitwise OR of 7:
- [3,5]
- [3,1,5]
- [3,2,5]
- [3,2,1,5]
- [2,5]
- [2,1,5]</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 16</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

DFS.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def countMaxOrSubsets(self, nums: List[int]) -> int:
        mx = ans = 0
        for x in nums:
            mx |= x

        def dfs(i, t):
            nonlocal mx, ans
            if i == len(nums):
                if t == mx:
                    ans += 1
                return
            dfs(i + 1, t)
            dfs(i + 1, t | nums[i])

        dfs(0, 0)
        return ans
```

```python
class Solution:
    def countMaxOrSubsets(self, nums: List[int]) -> int:
        def dfs(u, t):
            nonlocal ans, mx
            if u == len(nums):
                if t > mx:
                    mx, ans = t, 1
                elif t == mx:
                    ans += 1
                return
            dfs(u + 1, t | nums[u])
            dfs(u + 1, t)

        ans = mx = 0
        dfs(0, 0)
        return ans
```

```python
class Solution:
    def countMaxOrSubsets(self, nums: List[int]) -> int:
        n = len(nums)
        ans = 0
        mx = 0
        for mask in range(1 << n):
            t = 0
            for i, v in enumerate(nums):
                if (mask >> i) & 1:
                    t |= v
            if mx < t:
                mx = t
                ans = 1
            elif mx == t:
                ans += 1
        return ans
```

### **Java**

```java
class Solution {
    private int mx;
    private int ans;
    private int[] nums;

    public int countMaxOrSubsets(int[] nums) {
        mx = 0;
        for (int x : nums) {
            mx |= x;
        }
        this.nums = nums;
        dfs(0, 0);
        return ans;
    }

    private void dfs(int i, int t) {
        if (i == nums.length) {
            if (t == mx) {
                ++ans;
            }
            return;
        }
        dfs(i + 1, t);
        dfs(i + 1, t | nums[i]);
    }
}
```

```java
class Solution {
    private int mx;
    private int ans;
    private int[] nums;

    public int countMaxOrSubsets(int[] nums) {
        this.nums = nums;
        dfs(0, 0);
        return ans;
    }

    private void dfs(int u, int t) {
        if (u == nums.length) {
            if (t > mx) {
                mx = t;
                ans = 1;
            } else if (t == mx) {
                ++ans;
            }
            return;
        }
        dfs(u + 1, t);
        dfs(u + 1, t | nums[u]);
    }
}
```

```java
class Solution {
    public int countMaxOrSubsets(int[] nums) {
        int n = nums.length;
        int ans = 0;
        int mx = 0;
        for (int mask = 1; mask < 1 << n; ++mask) {
            int t = 0;
            for (int i = 0; i < n; ++i) {
                if (((mask >> i) & 1) == 1) {
                    t |= nums[i];
                }
            }
            if (mx < t) {
                mx = t;
                ans = 1;
            } else if (mx == t) {
                ++ans;
            }
        }
        return ans;
    }
}
```

### **TypeScript**

```ts
function countMaxOrSubsets(nums: number[]): number {
    let n = nums.length;
    let max = 0;
    for (let i = 0; i < n; i++) {
        max |= nums[i];
    }
    let ans = 0;
    function dfs(pre: number, depth: number): void {
        if (depth == n) {
            if (pre == max) ++ans;
            return;
        }
        dfs(pre, depth + 1);
        dfs(pre | nums[depth], depth + 1);
    }
    dfs(0, 0);
    return ans;
}
```

```ts
function countMaxOrSubsets(nums: number[]): number {
    const n = nums.length;
    let res = 0;
    let max = -Infinity;
    const dfs = (i: number, sum: number) => {
        for (let j = i; j < n; j++) {
            const num = sum | nums[j];
            if (num >= max) {
                if (num > max) {
                    max = num;
                    res = 0;
                }
                res++;
            }
            dfs(j + 1, num);
        }
    };
    dfs(0, 0);

    return res;
}
```

### **C++**

```cpp
class Solution {
public:
    int mx;
    int ans;
    vector<int> nums;

    int countMaxOrSubsets(vector<int>& nums) {
        this->nums = nums;
        mx = 0;
        ans = 0;
        for (int x : nums) mx |= x;
        dfs(0, 0);
        return ans;
    }

    void dfs(int i, int t) {
        if (i == nums.size()) {
            if (t == mx) ++ans;
            return;
        }
        dfs(i + 1, t);
        dfs(i + 1, t | nums[i]);
    }
};
```

```cpp
class Solution {
public:
    int mx;
    int ans;

    int countMaxOrSubsets(vector<int>& nums) {
        dfs(0, 0, nums);
        return ans;
    }

    void dfs(int u, int t, vector<int>& nums) {
        if (u == nums.size())
        {
            if (t > mx)
            {
                mx = t;
                ans = 1;
            }
            else if (t == mx) ++ans;
            return;
        }
        dfs(u + 1, t, nums);
        dfs(u + 1, t | nums[u], nums);
    }
};
```

```cpp
class Solution {
public:
    int countMaxOrSubsets(vector<int>& nums) {
        int n = nums.size();
        int ans = 0;
        int mx = 0;
        for (int mask = 1; mask < 1 << n; ++mask)
        {
            int t = 0;
            for (int i = 0; i < n; ++i)
            {
                if ((mask >> i) & 1)
                {
                    t |= nums[i];
                }
            }
            if (mx < t)
            {
                mx = t;
                ans = 1;
            }
            else if (mx == t) ++ans;
        }
        return ans;
    }
};
```

### **Go**

```go
func countMaxOrSubsets(nums []int) int {
	mx, ans := 0, 0
	for _, x := range nums {
		mx |= x
	}

	var dfs func(i, t int)
	dfs = func(i, t int) {
		if i == len(nums) {
			if t == mx {
				ans++
			}
			return
		}
		dfs(i+1, t)
		dfs(i+1, t|nums[i])
	}

	dfs(0, 0)
	return ans
}
```

```go
func countMaxOrSubsets(nums []int) int {
	mx, ans := 0, 0
	var dfs func(u, t int)
	dfs = func(u, t int) {
		if u == len(nums) {
			if t > mx {
				mx, ans = t, 1
			} else if t == mx {
				ans++
			}
			return
		}
		dfs(u+1, t)
		dfs(u+1, t|nums[u])
	}
	dfs(0, 0)
	return ans
}
```

```go
func countMaxOrSubsets(nums []int) int {
	n := len(nums)
	ans := 0
	mx := 0
	for mask := 1; mask < 1<<n; mask++ {
		t := 0
		for i, v := range nums {
			if ((mask >> i) & 1) == 1 {
				t |= v
			}
		}
		if mx < t {
			mx = t
			ans = 1
		} else if mx == t {
			ans++
		}
	}
	return ans
}
```

### **Rust**

```rust
impl Solution {
    fn dfs(nums: &Vec<i32>, i: usize, sum: i32) -> (i32, i32) {
        let n = nums.len();
        let mut max = i32::MIN;
        let mut res = 0;
        for j in i..n {
            let num = sum | nums[j];
            if num >= max {
                if num > max {
                    max = num;
                    res = 0;
                }
                res += 1;
            }
            let (r_max, r_res) = Self::dfs(nums, j + 1, num);
            if r_max >= max {
                if r_max > max {
                    max = r_max;
                    res = 0;
                }
                res += r_res;
            }
        }
        (max, res)
    }

    pub fn count_max_or_subsets(nums: Vec<i32>) -> i32 {
        Self::dfs(&nums, 0, 0).1
    }
}
```

### **...**

```

```

<!-- tabs:end -->
