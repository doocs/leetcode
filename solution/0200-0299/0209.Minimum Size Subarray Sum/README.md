# [209. 长度最小的子数组](https://leetcode.cn/problems/minimum-size-subarray-sum)

[English Version](/solution/0200-0299/0209.Minimum%20Size%20Subarray%20Sum/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个含有 <code>n</code><strong> </strong>个正整数的数组和一个正整数 <code>target</code><strong> 。</strong></p>

<p>找出该数组中满足其和<strong> </strong><code>≥ target</code><strong> </strong>的长度最小的 <strong>连续子数组</strong> <code>[nums<sub>l</sub>, nums<sub>l+1</sub>, ..., nums<sub>r-1</sub>, nums<sub>r</sub>]</code> ，并返回其长度<strong>。</strong>如果不存在符合条件的子数组，返回 <code>0</code> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>target = 7, nums = [2,3,1,2,4,3]
<strong>输出：</strong>2
<strong>解释：</strong>子数组 <code>[4,3]</code> 是该条件下的长度最小的子数组。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>target = 4, nums = [1,4,4]
<strong>输出：</strong>1
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>target = 11, nums = [1,1,1,1,1,1,1,1]
<strong>输出：</strong>0
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= target <= 10<sup>9</sup></code></li>
	<li><code>1 <= nums.length <= 10<sup>5</sup></code></li>
	<li><code>1 <= nums[i] <= 10<sup>5</sup></code></li>
</ul>

<p> </p>

<p><strong>进阶：</strong></p>

<ul>
	<li>如果你已经实现<em> </em><code>O(n)</code> 时间复杂度的解法, 请尝试设计一个 <code>O(n log(n))</code> 时间复杂度的解法。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：前缀和 + 二分查找**

我们先预处理出数组 $nums$ 的前缀和数组 $s$，其中 $s[i]$ 表示数组 $nums$ 前 $i$ 项元素之和。由于数组 $nums$ 中的元素都是正整数，因此数组 $s$ 也是单调递增的。另外，我们初始化答案 $ans = n + 1$，其中 $n$ 为数组 $nums$ 的长度。

接下来，我们遍历前缀和数组 $s$，对于其中的每个元素 $s[i]$，我们可以通过二分查找的方法找到满足 $s[j] \geq s[i] + target$ 的最小下标 $j$，如果 $j \leq n$，则说明存在满足条件的子数组，我们可以更新答案，即 $ans = min(ans, j - i)$。

最后，如果 $ans \leq n$，则说明存在满足条件的子数组，返回 $ans$，否则返回 $0$。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $nums$ 的长度。

**方法二：双指针**

我们可以使用双指针 $j$ 和 $i$ 维护一个窗口，其中窗口中的所有元素之和小于 $target$。初始时 $j = 0$，答案 $ans = n + 1$，其中 $n$ 为数组 $nums$ 的长度。

接下来，指针 $i$ 从 $0$ 开始向右移动，每次移动一步，我们将指针 $i$ 对应的元素加入窗口，同时更新窗口中元素之和。如果窗口中元素之和大于等于 $target$，说明当前子数组满足条件，我们可以更新答案，即 $ans = min(ans, i - j + 1)$。然后我们不断地从窗口中移除元素 $nums[j]$，直到窗口中元素之和小于 $target$，然后重复上述过程。

最后，如果 $ans \leq n$，则说明存在满足条件的子数组，返回 $ans$，否则返回 $0$。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为数组 $nums$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minSubArrayLen(self, target: int, nums: List[int]) -> int:
        n = len(nums)
        s = list(accumulate(nums, initial=0))
        ans = n + 1
        for i, x in enumerate(s):
            j = bisect_left(s, x + target)
            if j <= n:
                ans = min(ans, j - i)
        return ans if ans <= n else 0
```

```python
class Solution:
    def minSubArrayLen(self, target: int, nums: List[int]) -> int:
        n = len(nums)
        ans = n + 1
        s = j = 0
        for i, x in enumerate(nums):
            s += x
            while j < n and s >= target:
                ans = min(ans, i - j + 1)
                s -= nums[j]
                j += 1
        return ans if ans <= n else 0
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        long[] s = new long[n + 1];
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + nums[i];
        }
        int ans = n + 1;
        for (int i = 0; i <= n; ++i) {
            int j = search(s, s[i] + target);
            if (j <= n) {
                ans = Math.min(ans, j - i);
            }
        }
        return ans <= n ? ans : 0;
    }

    private int search(long[] nums, long x) {
        int l = 0, r = nums.length;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (nums[mid] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
```

```java
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        long s = 0;
        int ans = n + 1;
        for (int i = 0, j = 0; i < n; ++i) {
            s += nums[i];
            while (j < n && s >= target) {
                ans = Math.min(ans, i - j + 1);
                s -= nums[j++];
            }
        }
        return ans <= n ? ans : 0;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minSubArrayLen(int target, vector<int>& nums) {
        int n = nums.size();
        vector<long long> s(n + 1);
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + nums[i];
        }
        int ans = n + 1;
        for (int i = 0; i <= n; ++i) {
            int j = lower_bound(s.begin(), s.end(), s[i] + target) - s.begin();
            if (j <= n) {
                ans = min(ans, j - i);
            }
        }
        return ans <= n ? ans : 0;
    }
};
```

```cpp
class Solution {
public:
    int minSubArrayLen(int target, vector<int>& nums) {
        int n = nums.size();
        long long s = 0;
        int ans = n + 1;
        for (int i = 0, j = 0; i < n; ++i) {
            s += nums[i];
            while (j < n && s >= target) {
                ans = min(ans, i - j + 1);
                s -= nums[j++];
            }
        }
        return ans == n + 1 ? 0 : ans;
    }
};
```

### **Go**

```go
func minSubArrayLen(target int, nums []int) int {
	n := len(nums)
	s := make([]int, n+1)
	for i, x := range nums {
		s[i+1] = s[i] + x
	}
	ans := n + 1
	for i, x := range s {
		j := sort.SearchInts(s, x+target)
		if j <= n {
			ans = min(ans, j-i)
		}
	}
	if ans == n+1 {
		return 0
	}
	return ans
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

```go
func minSubArrayLen(target int, nums []int) int {
	n := len(nums)
	s := 0
	ans := n + 1
	for i, j := 0, 0; i < n; i++ {
		s += nums[i]
		for s >= target {
			ans = min(ans, i-j+1)
			s -= nums[j]
			j++
		}
	}
	if ans == n+1 {
		return 0
	}
	return ans
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **C#**

```cs
public class Solution {
    public int MinSubArrayLen(int target, int[] nums) {
        int n = nums.Length;
        long s = 0;
        int ans = n + 1;
        for (int i = 0, j = 0; i < n; ++i) {
            s += nums[i];
            while (s >= target) {
                ans = Math.Min(ans, i - j + 1);
                s -= nums[j++];
            }
        }
        return ans == n + 1 ? 0 : ans;
    }
}
```

### **TypeScript**

```ts
function minSubArrayLen(target: number, nums: number[]): number {
    const n = nums.length;
    const s: number[] = new Array(n + 1).fill(0);
    for (let i = 0; i < n; ++i) {
        s[i + 1] = s[i] + nums[i];
    }
    let ans = n + 1;
    const search = (x: number) => {
        let l = 0;
        let r = n + 1;
        while (l < r) {
            const mid = (l + r) >>> 1;
            if (s[mid] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    };
    for (let i = 0; i <= n; ++i) {
        const j = search(s[i] + target);
        if (j <= n) {
            ans = Math.min(ans, j - i);
        }
    }
    return ans === n + 1 ? 0 : ans;
}
```

```ts
function minSubArrayLen(target: number, nums: number[]): number {
    const n = nums.length;
    let s = 0;
    let ans = n + 1;
    for (let i = 0, j = 0; i < n; ++i) {
        s += nums[i];
        while (s >= target) {
            ans = Math.min(ans, i - j + 1);
            s -= nums[j++];
        }
    }
    return ans === n + 1 ? 0 : ans;
}
```

### **Rust**

```rust
impl Solution {
    pub fn min_sub_array_len(target: i32, nums: Vec<i32>) -> i32 {
        let n = nums.len();
        let mut res = n + 1;
        let mut sum = 0;
        let mut i = 0;
        for j in 0..n {
            sum += nums[j];

            while sum >= target {
                res = res.min(j - i + 1);
                sum -= nums[i];
                i += 1;
            }
        }
        if res == n + 1 {
            return 0;
        }
        res as i32
    }
}
```

### **...**

```

```

<!-- tabs:end -->
