# [1856. 子数组最小乘积的最大值](https://leetcode.cn/problems/maximum-subarray-min-product)

[English Version](/solution/1800-1899/1856.Maximum%20Subarray%20Min-Product/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>一个数组的 <strong>最小乘积</strong> 定义为这个数组中 <strong>最小值</strong> <strong>乘以 </strong>数组的 <strong>和</strong> 。</p>

<ul>
	<li>比方说，数组 <code>[3,2,5]</code> （最小值是 <code>2</code>）的最小乘积为 <code>2 * (3+2+5) = 2 * 10 = 20</code> 。</li>
</ul>

<p>给你一个正整数数组 <code>nums</code> ，请你返回 <code>nums</code> 任意 <strong>非空子数组</strong> 的<strong>最小乘积</strong> 的 <strong>最大值</strong> 。由于答案可能很大，请你返回答案对  <code>10<sup>9</sup> + 7</code> <strong>取余 </strong>的结果。</p>

<p>请注意，最小乘积的最大值考虑的是取余操作 <strong>之前</strong> 的结果。题目保证最小乘积的最大值在 <strong>不取余</strong> 的情况下可以用 <strong>64 位有符号整数</strong> 保存。</p>

<p><strong>子数组</strong> 定义为一个数组的 <strong>连续</strong> 部分。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [1,<strong>2,3,2</strong>]
<b>输出：</b>14
<b>解释：</b>最小乘积的最大值由子数组 [2,3,2] （最小值是 2）得到。
2 * (2+3+2) = 2 * 7 = 14 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [2,<strong>3,3</strong>,1,2]
<b>输出：</b>18
<b>解释：</b>最小乘积的最大值由子数组 [3,3] （最小值是 3）得到。
3 * (3+3) = 3 * 6 = 18 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>nums = [3,1,<strong>5,6,4</strong>,2]
<b>输出：</b>60
<b>解释：</b>最小乘积的最大值由子数组 [5,6,4] （最小值是 4）得到。
4 * (5+6+4) = 4 * 15 = 60 。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= nums.length <= 10<sup>5</sup></code></li>
	<li><code>1 <= nums[i] <= 10<sup>7</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：单调栈 + 前缀和**

我们可以枚举每个元素 $nums[i]$ 作为子数组的最小值，找出子数组的左右边界 $left[i]$ 和 $right[i]$。其中 $left[i]$ 表示 $i$ 左侧第一个严格小于 $nums[i]$ 的位置，而 $right[i]$ 表示 $i$ 右侧第一个小于等于 $nums[i]$ 的位置。

为了方便地算出子数组的和，我们可以预处理出前缀和数组 $s$，其中 $s[i]$ 表示 $nums$ 前 $i$ 个元素的和。

那么以 $nums[i]$ 作为子数组最小值的最小乘积为 $nums[i] \times s[right[i]] - s[left[i] + 1]$。我们可以枚举每个元素 $nums[i]$，求出以 $nums[i]$ 作为子数组最小值的最小乘积，然后取最大值即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $nums$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxSumMinProduct(self, nums: List[int]) -> int:
        n = len(nums)
        left = [-1] * n
        right = [n] * n
        stk = []
        for i, x in enumerate(nums):
            while stk and nums[stk[-1]] >= x:
                stk.pop()
            if stk:
                left[i] = stk[-1]
            stk.append(i)
        stk = []
        for i in range(n - 1, -1, -1):
            while stk and nums[stk[-1]] > nums[i]:
                stk.pop()
            if stk:
                right[i] = stk[-1]
            stk.append(i)
        s = list(accumulate(nums, initial=0))
        mod = 10**9 + 7
        return max((s[right[i]] - s[left[i] + 1]) * x for i, x in enumerate(nums)) % mod
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maxSumMinProduct(int[] nums) {
        int n = nums.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(left, -1);
        Arrays.fill(right, n);
        Deque<Integer> stk = new ArrayDeque<>();
        for (int i = 0; i < n; ++i) {
            while (!stk.isEmpty() && nums[stk.peek()] >= nums[i]) {
                stk.pop();
            }
            if (!stk.isEmpty()) {
                left[i] = stk.peek();
            }
            stk.push(i);
        }
        stk.clear();
        for (int i = n - 1; i >= 0; --i) {
            while (!stk.isEmpty() && nums[stk.peek()] > nums[i]) {
                stk.pop();
            }
            if (!stk.isEmpty()) {
                right[i] = stk.peek();
            }
            stk.push(i);
        }
        long[] s = new long[n + 1];
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + nums[i];
        }
        long ans = 0;
        for (int i = 0; i < n; ++i) {
            ans = Math.max(ans, nums[i] * (s[right[i]] - s[left[i] + 1]));
        }
        final int mod = (int) 1e9 + 7;
        return (int) (ans % mod);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxSumMinProduct(vector<int>& nums) {
        int n = nums.size();
        vector<int> left(n, -1);
        vector<int> right(n, n);
        stack<int> stk;
        for (int i = 0; i < n; ++i) {
            while (!stk.empty() && nums[stk.top()] >= nums[i]) {
                stk.pop();
            }
            if (!stk.empty()) {
                left[i] = stk.top();
            }
            stk.push(i);
        }
        stk = stack<int>();
        for (int i = n - 1; ~i; --i) {
            while (!stk.empty() && nums[stk.top()] > nums[i]) {
                stk.pop();
            }
            if (!stk.empty()) {
                right[i] = stk.top();
            }
            stk.push(i);
        }
        long long s[n + 1];
        s[0] = 0;
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + nums[i];
        }
        long long ans = 0;
        for (int i = 0; i < n; ++i) {
            ans = max(ans, nums[i] * (s[right[i]] - s[left[i] + 1]));
        }
        const int mod = 1e9 + 7;
        return ans % mod;
    }
};
```

### **Go**

```go
func maxSumMinProduct(nums []int) int {
	n := len(nums)
	left := make([]int, n)
	right := make([]int, n)
	for i := range left {
		left[i] = -1
		right[i] = n
	}
	stk := []int{}
	for i, x := range nums {
		for len(stk) > 0 && nums[stk[len(stk)-1]] >= x {
			stk = stk[:len(stk)-1]
		}
		if len(stk) > 0 {
			left[i] = stk[len(stk)-1]
		}
		stk = append(stk, i)
	}
	stk = []int{}
	for i := n - 1; i >= 0; i-- {
		for len(stk) > 0 && nums[stk[len(stk)-1]] > nums[i] {
			stk = stk[:len(stk)-1]
		}
		if len(stk) > 0 {
			right[i] = stk[len(stk)-1]
		}
		stk = append(stk, i)
	}
	s := make([]int, n+1)
	for i, x := range nums {
		s[i+1] = s[i] + x
	}
	ans := 0
	for i, x := range nums {
		if t := x * (s[right[i]] - s[left[i]+1]); ans < t {
			ans = t
		}
	}
	const mod = 1e9 + 7
	return ans % mod
}
```

### **TypeSript**

```ts
function maxSumMinProduct(nums: number[]): number {
    const n = nums.length;
    const left: number[] = new Array(n).fill(-1);
    const right: number[] = new Array(n).fill(n);
    let stk: number[] = [];
    for (let i = 0; i < n; ++i) {
        while (stk.length && nums[stk[stk.length - 1]] >= nums[i]) {
            stk.pop();
        }
        if (stk.length) {
            left[i] = stk[stk.length - 1];
        }
        stk.push(i);
    }
    stk = [];
    for (let i = n - 1; i >= 0; --i) {
        while (stk.length && nums[stk[stk.length - 1]] > nums[i]) {
            stk.pop();
        }
        if (stk.length) {
            right[i] = stk[stk.length - 1];
        }
        stk.push(i);
    }
    const s: number[] = new Array(n + 1).fill(0);
    for (let i = 0; i < n; ++i) {
        s[i + 1] = s[i] + nums[i];
    }
    let ans: bigint = 0n;
    const mod = 10 ** 9 + 7;
    for (let i = 0; i < n; ++i) {
        const t = BigInt(nums[i]) * BigInt(s[right[i]] - s[left[i] + 1]);
        if (ans < t) {
            ans = t;
        }
    }
    return Number(ans % BigInt(mod));
}
```

### **...**

```

```

<!-- tabs:end -->
