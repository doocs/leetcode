# [2044. 统计按位或能得到最大值的子集数目](https://leetcode.cn/problems/count-number-of-maximum-bitwise-or-subsets)

[English Version](/solution/2000-2099/2044.Count%20Number%20of%20Maximum%20Bitwise-OR%20Subsets/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>nums</code> ，请你找出 <code>nums</code> 子集 <strong>按位或</strong> 可能得到的<strong> </strong><strong>最大值</strong> ，并返回按位或能得到最大值的 <strong>不同非空子集的数目</strong> 。</p>

<p>如果数组 <code>a</code> 可以由数组 <code>b</code> 删除一些元素（或不删除）得到，则认为数组 <code>a</code> 是数组 <code>b</code> 的一个 <strong>子集</strong> 。如果选中的元素下标位置不一样，则认为两个子集 <strong>不同</strong> 。</p>

<p>对数组 <code>a</code> 执行 <strong>按位或</strong>&nbsp;，结果等于 <code>a[0] <strong>OR</strong> a[1] <strong>OR</strong> ... <strong>OR</strong> a[a.length - 1]</code>（下标从 <strong>0</strong> 开始）。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [3,1]
<strong>输出：</strong>2
<strong>解释：</strong>子集按位或能得到的最大值是 3 。有 2 个子集按位或可以得到 3 ：
- [3]
- [3,1]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,2,2]
<strong>输出：</strong>7
<strong>解释：</strong>[2,2,2] 的所有非空子集的按位或都可以得到 2 。总共有 2<sup>3</sup> - 1 = 7 个子集。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [3,2,1,5]
<strong>输出：</strong>6
<strong>解释：</strong>子集按位或可能的最大值是 7 。有 6 个子集按位或可以得到 7 ：
- [3,5]
- [3,1,5]
- [3,2,5]
- [3,2,1,5]
- [2,5]
- [2,1,5]</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 16</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：DFS**

简单 DFS。可以预先算出按位或的最大值 mx，然后 DFS 搜索按位或结果等于 mx 的所有子集数。也可以在 DFS 搜索中逐渐更新 mx 与对应的子集数。

时间复杂度 $O(2^n)$。

**方法二：二进制枚举**

时间复杂度 $O(n*2^n)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
