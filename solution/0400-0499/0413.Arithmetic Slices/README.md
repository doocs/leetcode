# [413. 等差数列划分](https://leetcode.cn/problems/arithmetic-slices)

[English Version](/solution/0400-0499/0413.Arithmetic%20Slices/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>如果一个数列 <strong>至少有三个元素</strong> ，并且任意两个相邻元素之差相同，则称该数列为等差数列。</p>

<ul>
	<li>例如，<code>[1,3,5,7,9]</code>、<code>[7,7,7,7]</code> 和 <code>[3,-1,-5,-9]</code> 都是等差数列。</li>
</ul>

<div class="original__bRMd">
<div>
<p>给你一个整数数组 <code>nums</code> ，返回数组 <code>nums</code> 中所有为等差数组的 <strong>子数组</strong> 个数。</p>

<p><strong>子数组</strong> 是数组中的一个连续序列。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,3,4]
<strong>输出：</strong>3
<strong>解释：</strong>nums 中有三个子等差数组：[1, 2, 3]、[2, 3, 4] 和 [1,2,3,4] 自身。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1]
<strong>输出：</strong>0
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= nums.length <= 5000</code></li>
	<li><code>-1000 <= nums[i] <= 1000</code></li>
</ul>
</div>
</div>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：遍历计数**

我们用 $d$ 表示当前相邻两个元素的差值，用 $cnt$ 表示当前等差数列的长度，初始时 $d = 3000$, $cnt = 2$。

遍历数组 `nums`，对于相邻的两个元素 $a$ 和 $b$，如果 $b - a = d$，则说明当前元素 $b$ 也属于当前等差数列，此时 $cnt$ 自增 1；否则说明当前元素 $b$ 不属于当前等差数列，此时更新 $d = b - a$，$cnt = 2$。如果 $cnt \ge 3$，则说明当前等差数列的长度至少为 3，此时等差数列的个数为 $cnt - 2$，将其加到答案中。

遍历结束后，即可得到答案。

在代码实现上，我们也可以将 $cnt$ 初始化为 $0$，重置 $cnt$ 时，直接将 $cnt$ 置为 $0$；累加答案时，直接累加 $cnt$ 即可。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 是数组 `nums` 的长度。

相似题目：

-   [1513. 仅含 1 的子串数](/solution/1500-1599/1513.Number%20of%20Substrings%20With%20Only%201s/README.md)
-   [2348. 全 0 子数组的数目](/solution/2300-2399/2348.Number%20of%20Zero-Filled%20Subarrays/README.md)

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def numberOfArithmeticSlices(self, nums: List[int]) -> int:
        ans, cnt = 0, 2
        d = 3000
        for a, b in pairwise(nums):
            if b - a == d:
                cnt += 1
            else:
                d = b - a
                cnt = 2
            ans += max(0, cnt - 2)
        return ans
```

```python
class Solution:
    def numberOfArithmeticSlices(self, nums: List[int]) -> int:
        ans = cnt = 0
        d = 3000
        for a, b in pairwise(nums):
            if b - a == d:
                cnt += 1
            else:
                d = b - a
                cnt = 0
            ans += cnt
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int numberOfArithmeticSlices(int[] nums) {
        int ans = 0, cnt = 0;
        int d = 3000;
        for (int i = 0; i < nums.length - 1; ++i) {
            if (nums[i + 1] - nums[i] == d) {
                ++cnt;
            } else {
                d = nums[i + 1] - nums[i];
                cnt = 0;
            }
            ans += cnt;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int numberOfArithmeticSlices(vector<int>& nums) {
        int ans = 0, cnt = 0;
        int d = 3000;
        for (int i = 0; i < nums.size() - 1; ++i) {
            if (nums[i + 1] - nums[i] == d) {
                ++cnt;
            } else {
                d = nums[i + 1] - nums[i];
                cnt = 0;
            }
            ans += cnt;
        }
        return ans;
    }
};
```

### **Go**

```go
func numberOfArithmeticSlices(nums []int) (ans int) {
	cnt, d := 0, 3000
	for i, b := range nums[1:] {
		a := nums[i]
		if b-a == d {
			cnt++
		} else {
			d = b - a
			cnt = 0
		}
		ans += cnt
	}
	return
}
```

### **TypeScript**

```ts
function numberOfArithmeticSlices(nums: number[]): number {
    let ans = 0;
    let cnt = 0;
    let d = 3000;
    for (let i = 0; i < nums.length - 1; ++i) {
        const a = nums[i];
        const b = nums[i + 1];
        if (b - a == d) {
            ++cnt;
        } else {
            d = b - a;
            cnt = 0;
        }
        ans += cnt;
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
