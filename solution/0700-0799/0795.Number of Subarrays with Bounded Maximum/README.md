# [795. 区间子数组个数](https://leetcode.cn/problems/number-of-subarrays-with-bounded-maximum)

[English Version](/solution/0700-0799/0795.Number%20of%20Subarrays%20with%20Bounded%20Maximum/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>nums</code> 和两个整数：<code>left</code> 及 <code>right</code> 。找出 <code>nums</code> 中连续、非空且其中最大元素在范围&nbsp;<code>[left, right]</code> 内的子数组，并返回满足条件的子数组的个数。</p>

<p>生成的测试用例保证结果符合 <strong>32-bit</strong> 整数范围。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,1,4,3], left = 2, right = 3
<strong>输出：</strong>3
<strong>解释：</strong>满足条件的三个子数组：[2], [2, 1], [3]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,9,2,5,6], left = 2, right = 8
<strong>输出：</strong>7
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= left &lt;= right &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：区间计数**

题目要我们统计数组 `nums` 中，最大值在区间 $[left, right]$ 范围内的子数组个数。

对于区间 $[left,..right]$ 问题，我们可以考虑将其转换为 $[0,..right]$ 然后再减去 $[0,..left-1]$ 的问题。也就是说，所有最大元素不超过 $right$ 的子数组个数，减去所有最大元素不超过 $left-1$ 的子数组个数，剩下的就是最大元素在区间 $[left,..right]$ 范围内的子数组个数，即题目要求的结果。

$$
ans = \sum_{i=0}^{right} ans_i -  \sum_{i=0}^{left-1} ans_i
$$

对于本题，我们设计一个函数 $f(x)$，表示数组 `nums` 中，最大值不超过 $x$ 的子数组个数。那么答案为 $f(right) - f(left-1)$。函数 $f(x)$ 的执行逻辑如下：

-   用变量 $cnt$ 记录最大值不超过 $x$ 的子数组的个数，用 $t$ 记录当前子数组的长度。
-   遍历数组 `nums`，对于每个元素 $nums[i]$，如果 $nums[i] \leq x$，则当前子数组的长度加一，即 $t=t+1$，否则当前子数组的长度重置为 0，即 $t=0$。然后将当前子数组的长度加到 $cnt$ 中，即 $cnt = cnt + t$。
-   遍历结束，将 $cnt$ 返回即可。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为数组 `nums` 的长度。

**方法二：单调栈 + 枚举元素计算贡献**

我们还可以枚举数组中每个元素 $nums[i]$ 作为子数组的最大值，然后统计以该元素为最大值的子数组的个数。问题转化为求出每个元素 $nums[i]$ 左侧第一个大于该元素的下标 $l[i]$，右侧第一个大于等于该元素的下标 $r[i]$，则以该元素为最大值的子数组的个数为 $(i - l[i]) \times (r[i] - i)$。

我们可以使用单调栈方便地求出 $l[i]$ 和 $r[i]$。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。

相似题目：[907. 子数组的最小值之和](/solution/0900-0999/0907.Sum%20of%20Subarray%20Minimums/README.md)

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def numSubarrayBoundedMax(self, nums: List[int], left: int, right: int) -> int:
        def f(x):
            cnt = t = 0
            for v in nums:
                t = 0 if v > x else t + 1
                cnt += t
            return cnt

        return f(right) - f(left - 1)
```

```python
class Solution:
    def numSubarrayBoundedMax(self, nums: List[int], left: int, right: int) -> int:
        n = len(nums)
        l, r = [-1] * n, [n] * n
        stk = []
        for i, v in enumerate(nums):
            while stk and nums[stk[-1]] <= v:
                stk.pop()
            if stk:
                l[i] = stk[-1]
            stk.append(i)
        stk = []
        for i in range(n - 1, -1, -1):
            while stk and nums[stk[-1]] < nums[i]:
                stk.pop()
            if stk:
                r[i] = stk[-1]
            stk.append(i)
        return sum((i - l[i]) * (r[i] - i) for i, v in enumerate(nums) if left <= v <= right)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int numSubarrayBoundedMax(int[] nums, int left, int right) {
        return f(nums, right) - f(nums, left - 1);
    }

    private int f(int[] nums, int x) {
        int cnt = 0, t = 0;
        for (int v : nums) {
            t = v > x ? 0 : t + 1;
            cnt += t;
        }
        return cnt;
    }
}
```

```java
class Solution {
    public int numSubarrayBoundedMax(int[] nums, int left, int right) {
        int n = nums.length;
        int[] l = new int[n];
        int[] r = new int[n];
        Arrays.fill(l, -1);
        Arrays.fill(r, n);
        Deque<Integer> stk = new ArrayDeque<>();
        for (int i = 0; i < n; ++i) {
            int v = nums[i];
            while (!stk.isEmpty() && nums[stk.peek()] <= v) {
                stk.pop();
            }
            if (!stk.isEmpty()) {
                l[i] = stk.peek();
            }
            stk.push(i);
        }
        stk.clear();
        for (int i = n - 1; i >= 0; --i) {
            int v = nums[i];
            while (!stk.isEmpty() && nums[stk.peek()] < v) {
                stk.pop();
            }
            if (!stk.isEmpty()) {
                r[i] = stk.peek();
            }
            stk.push(i);
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            if (left <= nums[i] && nums[i] <= right) {
                ans += (i - l[i]) * (r[i] - i);
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int numSubarrayBoundedMax(vector<int>& nums, int left, int right) {
        auto f = [&](int x) {
            int cnt = 0, t = 0;
            for (int& v : nums) {
                t = v > x ? 0 : t + 1;
                cnt += t;
            }
            return cnt;
        };
        return f(right) - f(left -1);
    }
};
```

```cpp
class Solution {
public:
    int numSubarrayBoundedMax(vector<int>& nums, int left, int right) {
        int n = nums.size();
        vector<int> l(n, -1);
        vector<int> r(n, n);
        stack<int> stk;
        for (int i = 0; i < n; ++i) {
            int v = nums[i];
            while (!stk.empty() && nums[stk.top()] <= v) stk.pop();
            if (!stk.empty()) l[i] = stk.top();
            stk.push(i);
        }
        stk = stack<int>();
        for (int i = n - 1; ~i; --i) {
            int v = nums[i];
            while (!stk.empty() && nums[stk.top()] < v) stk.pop();
            if (!stk.empty()) r[i] = stk.top();
            stk.push(i);
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            if (left <= nums[i] && nums[i] <= right) {
                ans += (i - l[i]) * (r[i] - i);
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func numSubarrayBoundedMax(nums []int, left int, right int) int {
	f := func(x int) (cnt int) {
		t := 0
		for _, v := range nums {
			t++
			if v > x {
				t = 0
			}
			cnt += t
		}
		return
	}
	return f(right) - f(left-1)
}
```

```go
func numSubarrayBoundedMax(nums []int, left int, right int) (ans int) {
	n := len(nums)
	l := make([]int, n)
	r := make([]int, n)
	for i := range l {
		l[i], r[i] = -1, n
	}
	stk := []int{}
	for i, v := range nums {
		for len(stk) > 0 && nums[stk[len(stk)-1]] <= v {
			stk = stk[:len(stk)-1]
		}
		if len(stk) > 0 {
			l[i] = stk[len(stk)-1]
		}
		stk = append(stk, i)
	}
	stk = []int{}
	for i := n - 1; i >= 0; i-- {
		v := nums[i]
		for len(stk) > 0 && nums[stk[len(stk)-1]] < v {
			stk = stk[:len(stk)-1]
		}
		if len(stk) > 0 {
			r[i] = stk[len(stk)-1]
		}
		stk = append(stk, i)
	}
	for i, v := range nums {
		if left <= v && v <= right {
			ans += (i - l[i]) * (r[i] - i)
		}
	}
	return
}
```

### **...**

```

```

<!-- tabs:end -->
