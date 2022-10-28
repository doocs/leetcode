# [907. 子数组的最小值之和](https://leetcode.cn/problems/sum-of-subarray-minimums)

[English Version](/solution/0900-0999/0907.Sum%20of%20Subarray%20Minimums/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个整数数组 <code>arr</code>，找到 <code>min(b)</code> 的总和，其中 <code>b</code> 的范围为 <code>arr</code> 的每个（连续）子数组。</p>

<p>由于答案可能很大，因此<strong> 返回答案模 <code>10^9 + 7</code></strong> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>arr = [3,1,2,4]
<strong>输出：</strong>17
<strong>解释：
</strong>子数组为<strong> </strong>[3]，[1]，[2]，[4]，[3,1]，[1,2]，[2,4]，[3,1,2]，[1,2,4]，[3,1,2,4]。 
最小值为 3，1，2，4，1，1，2，1，1，1，和为 17。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>arr = [11,81,94,43,3]
<strong>输出：</strong>444
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= arr.length <= 3 * 10<sup>4</sup></code></li>
	<li><code>1 <= arr[i] <= 3 * 10<sup>4</sup></code></li>
</ul>

<p> </p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：单调栈**

题目要求的是每个子数组的最小值之和，实际上相当于，对于每个元素 $arr[i]$，求以 $arr[i]$ 为最小值的子数组的个数，然后乘以 $arr[i]$，最后求和。

因此，题目的重点转换为：求以 $arr[i]$ 为最小值的子数组的个数。对于 $arr[i]$，我们找出其左边第一个小于 $arr[i]$ 的位置 $left[i]$，右侧第一个小于等于 $arr[i]$ 的位置 $right[i]$，则以 $arr[i]$ 为最小值的子数组的个数为 $(i - left[i]) \times (right[i] - i)$。

注意，这里为什么要求右侧第一个小于等于 $arr[i]$ 的位置 $right[i]$，而不是小于 $arr[i]$ 的位置呢？这是因为，如果是右侧第一个小于 $arr[i]$ 的位置 $right[i]$，则会导致重复计算。

我们可以举个例子来说明，对于以下数组：

下标为 $3$ 的元素大小为 $2$，左侧第一个小于 $2$ 的元素下标为 $0$，如果我们求右侧第一个小于 $2$ 的元素下标，可以得到下标为 $7$。也即是说，子数组区间为 $(0, 7)$。注意，这里是开区间。

```
0 4 3 2 5 3 2 1
*     ^       *
```

按照同样的方法，我们可以求出下标为 $6$ 的元素的子数组区间，可以发现，其子数组区间也为 $(0, 7)$，也即是说，下标为 $3$ 和下标为 $6$ 的元素的子数组区间是重复的。这样就造成了重复计算。

```
0 4 3 2 5 3 2 1
*           ^ *
```

如果我们求的是右侧第一个小于等于其值的下标，就不会有重复问题，因为下标为 $3$ 的子数组区间变为 $(0, 6)$，下标为 $6$ 的子数组区间为 $(0, 7)$，两者不重复。

回到这道题上，我们只需要遍历数组，对于每个元素 $arr[i]$，利用单调栈求出其左侧第一个小于 $arr[i]$ 的位置 $left[i]$，右侧第一个小于等于 $arr[i]$ 的位置 $right[i]$，则以 $arr[i]$ 为最小值的子数组的个数为 $(i - left[i]) \times (right[i] - i)$，然后乘以 $arr[i]$，最后求和即可。

注意数据的溢出以及取模操作。

时间复杂度 $O(n)$，其中 $n$ 表示数组 $arr$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def sumSubarrayMins(self, arr: List[int]) -> int:
        n = len(arr)
        left = [-1] * n
        right = [n] * n
        stk = []
        for i, v in enumerate(arr):
            while stk and arr[stk[-1]] >= v:
                stk.pop()
            if stk:
                left[i] = stk[-1]
            stk.append(i)

        stk = []
        for i in range(n - 1, -1, -1):
            while stk and arr[stk[-1]] > arr[i]:
                stk.pop()
            if stk:
                right[i] = stk[-1]
            stk.append(i)
        mod = 10**9 + 7
        return sum((i - left[i]) * (right[i] - i) * v for i, v in enumerate(arr)) % mod
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int sumSubarrayMins(int[] arr) {
        int n = arr.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(left, -1);
        Arrays.fill(right, n);
        Deque<Integer> stk = new ArrayDeque<>();
        for (int i = 0; i < n; ++i) {
            while (!stk.isEmpty() && arr[stk.peek()] >= arr[i]) {
                stk.pop();
            }
            if (!stk.isEmpty()) {
                left[i] = stk.peek();
            }
            stk.push(i);
        }
        stk.clear();
        for (int i = n - 1; i >= 0; --i) {
            while (!stk.isEmpty() && arr[stk.peek()] > arr[i]) {
                stk.pop();
            }
            if (!stk.isEmpty()) {
                right[i] = stk.peek();
            }
            stk.push(i);
        }
        int mod = (int) 1e9 + 7;
        long ans = 0;
        for (int i = 0; i < n; ++i) {
            ans += (long) (i - left[i]) * (right[i] - i) % mod * arr[i] % mod;
            ans %= mod;
        }
        return (int) ans;
    }
}
```

### **C++**

```cpp
using ll = long long;
const int mod = 1e9 + 7;

class Solution {
public:
    int sumSubarrayMins(vector<int>& arr) {
        int n = arr.size();
        vector<int> left(n, -1);
        vector<int> right(n, n);
        stack<int> stk;
        for (int i = 0; i < n; ++i) {
            while (!stk.empty() && arr[stk.top()] >= arr[i]) stk.pop();
            if (!stk.empty()) left[i] = stk.top();
            stk.push(i);
        }
        stk = stack<int>();
        for (int i = n - 1; i >= 0; --i) {
            while (!stk.empty() && arr[stk.top()] > arr[i]) stk.pop();
            if (!stk.empty()) right[i] = stk.top();
            stk.push(i);
        }
        ll ans = 0;
        for (int i = 0; i < n; ++i) {
            ans += (ll)(i - left[i]) * (right[i] - i) * arr[i] % mod;
            ans %= mod;
        }
        return ans;
    }
};
```

### **Go**

```go
func sumSubarrayMins(arr []int) int {
	mod := int(1e9) + 7
	n := len(arr)
	left := make([]int, n)
	right := make([]int, n)
	for i := range left {
		left[i] = -1
		right[i] = n
	}
	stk := []int{}
	for i, v := range arr {
		for len(stk) > 0 && arr[stk[len(stk)-1]] >= v {
			stk = stk[:len(stk)-1]
		}
		if len(stk) > 0 {
			left[i] = stk[len(stk)-1]
		}
		stk = append(stk, i)
	}
	stk = []int{}
	for i := n - 1; i >= 0; i-- {
		for len(stk) > 0 && arr[stk[len(stk)-1]] > arr[i] {
			stk = stk[:len(stk)-1]
		}
		if len(stk) > 0 {
			right[i] = stk[len(stk)-1]
		}
		stk = append(stk, i)
	}
	ans := 0
	for i, v := range arr {
		ans += (i - left[i]) * (right[i] - i) * v % mod
		ans %= mod
	}
	return ans
}
```

### **TypeScript**

```ts
function sumSubarrayMins(arr: number[]): number {
    const n = arr.length;
    function getEle(i: number): number {
        if (i == -1 || i == n) return Number.MIN_SAFE_INTEGER;
        return arr[i];
    }
    let ans = 0;
    const mod = 10 ** 9 + 7;
    let stack = [];
    for (let i = -1; i <= n; i++) {
        while (stack.length && getEle(stack[0]) > getEle(i)) {
            const idx = stack.shift();
            ans = (ans + arr[idx] * (idx - stack[0]) * (i - idx)) % mod;
        }
        stack.unshift(i);
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
