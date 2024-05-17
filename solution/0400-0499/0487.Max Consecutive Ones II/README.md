---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0400-0499/0487.Max%20Consecutive%20Ones%20II/README.md
tags:
    - 数组
    - 动态规划
    - 滑动窗口
---

<!-- problem:start -->

# [487. 最大连续 1 的个数 II 🔒](https://leetcode.cn/problems/max-consecutive-ones-ii)

[English Version](/solution/0400-0499/0487.Max%20Consecutive%20Ones%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个二进制数组 <code>nums</code> ，如果最多可以翻转一个 <code>0</code> ，则返回数组中连续 <code>1</code> 的最大个数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,0,1,1,0]
<strong>输出：</strong>4
<strong>解释：</strong>翻转第一个 0 可以得到最长的连续 1。
&nbsp;    当翻转以后，最大连续 1 的个数为 4。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<b>输入：</b>nums = [1,0,1,1,0,1]
<b>输出：</b>4
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>nums[i]</code>&nbsp;不是&nbsp;<code>0</code>&nbsp;就是&nbsp;<code>1</code>.</li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>如果输入的数字是作为<strong> 无限流 </strong>逐个输入如何处理？换句话说，内存不能存储下所有从流中输入的数字。您可以有效地解决吗？</p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：预处理 + 枚举

定义 `left`, `right` 数组表示以第 $i$ 个元素结尾（开头），往前（往后）累计的最大连续 $1$ 的个数。

先遍历 `nums`，预处理出 `left` 和 `right`。

然后枚举 `nums` 每个位置 $i$，统计以 $i$ 为分界点，左右两边最大连续 $1$ 的个数之和，取最大值即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为 `nums` 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findMaxConsecutiveOnes(self, nums: List[int]) -> int:
        ans = nums.count(1)
        n = len(nums)
        left = [0] * n
        right = [0] * n
        for i, v in enumerate(nums):
            if v:
                left[i] = 1 if i == 0 else left[i - 1] + 1
        for i in range(n - 1, -1, -1):
            v = nums[i]
            if v:
                right[i] = 1 if i == n - 1 else right[i + 1] + 1
        ans = 0
        for i, v in enumerate(nums):
            t = 0
            if i:
                t += left[i - 1]
            if i < n - 1:
                t += right[i + 1]
            ans = max(ans, t + 1)
        return ans
```

#### Java

```java
class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int n = nums.length;
        int[] left = new int[n];
        int[] right = new int[n];
        for (int i = 0; i < n; ++i) {
            if (nums[i] == 1) {
                left[i] = i == 0 ? 1 : left[i - 1] + 1;
            }
        }
        for (int i = n - 1; i >= 0; --i) {
            if (nums[i] == 1) {
                right[i] = i == n - 1 ? 1 : right[i + 1] + 1;
            }
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int t = 0;
            if (i > 0) {
                t += left[i - 1];
            }
            if (i < n - 1) {
                t += right[i + 1];
            }
            ans = Math.max(ans, t + 1);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int findMaxConsecutiveOnes(vector<int>& nums) {
        int n = nums.size();
        vector<int> left(n), right(n);
        for (int i = 0; i < n; ++i) {
            if (nums[i]) {
                left[i] = i == 0 ? 1 : left[i - 1] + 1;
            }
        }
        for (int i = n - 1; ~i; --i) {
            if (nums[i]) {
                right[i] = i == n - 1 ? 1 : right[i + 1] + 1;
            }
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int t = 0;
            if (i) {
                t += left[i - 1];
            }
            if (i < n - 1) {
                t += right[i + 1];
            }
            ans = max(ans, t + 1);
        }
        return ans;
    }
};
```

#### Go

```go
func findMaxConsecutiveOnes(nums []int) int {
	n := len(nums)
	left := make([]int, n)
	right := make([]int, n)
	for i, v := range nums {
		if v == 1 {
			if i == 0 {
				left[i] = 1
			} else {
				left[i] = left[i-1] + 1
			}
		}
	}
	for i := n - 1; i >= 0; i-- {
		if nums[i] == 1 {
			if i == n-1 {
				right[i] = 1
			} else {
				right[i] = right[i+1] + 1
			}
		}
	}
	ans := 0
	for i := range nums {
		t := 0
		if i > 0 {
			t += left[i-1]
		}
		if i < n-1 {
			t += right[i+1]
		}
		ans = max(ans, t+1)
	}
	return ans
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：滑动窗口

找出最大的窗口，使得窗口内的 $0$ 的个数不超过 $1$ 个。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为 `nums` 的长度。

相似题目：

-   [1004. 最大连续 1 的个数 II 🔒I](https://github.com/doocs/leetcode/blob/main/solution/1000-1099/1004.Max%20Consecutive%20Ones%20III/README.md)

以下是滑动窗口的优化版本。

维护一个单调变长的窗口。这种窗口经常出现在寻求“最大窗口”的问题中：因为求的是“最大”，所以我们没有必要缩短窗口，于是代码就少了缩短窗口的部分；从另一个角度讲，本题里的 K 是资源数，一旦透支，窗口就不能再增长了。

-   l 是窗口左端点，负责移动起始位置
-   r 是窗口右端点，负责扩展窗口
-   k 是资源数，每次要替换 0，k 减 1，同时 r 向右移动
-   `r++` 每次都会执行，`l++` 只有资源 `k < 0` 时才触发，因此 `r - l` 的值只会单调递增（或保持不变）
-   移动左端点时，如果当前元素是 0，说明可以释放一个资源，k 加 1

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findMaxConsecutiveOnes(self, nums: List[int]) -> int:
        ans = 1
        cnt = j = 0
        for i, v in enumerate(nums):
            if v == 0:
                cnt += 1
            while cnt > 1:
                if nums[j] == 0:
                    cnt -= 1
                j += 1
            ans = max(ans, i - j + 1)
        return ans
```

#### Java

```java
class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int j = 0, cnt = 0;
        int ans = 1;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] == 0) {
                ++cnt;
            }
            while (cnt > 1) {
                if (nums[j++] == 0) {
                    --cnt;
                }
            }
            ans = Math.max(ans, i - j + 1);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int findMaxConsecutiveOnes(vector<int>& nums) {
        int ans = 1;
        int cnt = 0, j = 0;
        for (int i = 0; i < nums.size(); ++i) {
            if (nums[i] == 0) {
                ++cnt;
            }
            while (cnt > 1) {
                if (nums[j++] == 0) {
                    --cnt;
                }
            }
            ans = max(ans, i - j + 1);
        }
        return ans;
    }
};
```

#### Go

```go
func findMaxConsecutiveOnes(nums []int) int {
	ans := 1
	j, cnt := 0, 0
	for i, v := range nums {
		if v == 0 {
			cnt++
		}
		for cnt > 1 {
			if nums[j] == 0 {
				cnt--
			}
			j++
		}
		ans = max(ans, i-j+1)
	}
	return ans
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法三

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findMaxConsecutiveOnes(self, nums: List[int]) -> int:
        l = r = 0
        k = 1
        while r < len(nums):
            if nums[r] == 0:
                k -= 1
            if k < 0:
                if nums[l] == 0:
                    k += 1
                l += 1
            r += 1
        return r - l
```

#### Java

```java
class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int l = 0, r = 0;
        int k = 1;
        while (r < nums.length) {
            if (nums[r++] == 0) {
                --k;
            }
            if (k < 0 && nums[l++] == 0) {
                ++k;
            }
        }
        return r - l;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int findMaxConsecutiveOnes(vector<int>& nums) {
        int l = 0, r = 0;
        int k = 1;
        while (r < nums.size()) {
            if (nums[r++] == 0) {
                --k;
            }
            if (k < 0 && nums[l++] == 0) {
                ++k;
            }
        }
        return r - l;
    }
};
```

#### Go

```go
func findMaxConsecutiveOnes(nums []int) int {
	l, r := 0, 0
	k := 1
	for ; r < len(nums); r++ {
		if nums[r] == 0 {
			k--
		}
		if k < 0 {
			if nums[l] == 0 {
				k++
			}
			l++
		}
	}
	return r - l
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
