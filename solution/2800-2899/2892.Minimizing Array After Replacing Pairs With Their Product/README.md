# [2892. Minimizing Array After Replacing Pairs With Their Product](https://leetcode.cn/problems/minimizing-array-after-replacing-pairs-with-their-product)

[English Version](/solution/2800-2899/2892.Minimizing%20Array%20After%20Replacing%20Pairs%20With%20Their%20Product/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>Given an integer array <code>nums</code> and an integer <code>k</code>, you can perform the following operation on the array any number of times:</p>

<ul>
	<li>Select two <strong>adjacent</strong> elements of the array like <code>x</code> and <code>y</code>, such that <code>x * y &lt;= k</code>, and replace both of them with a <strong>single element</strong> with value <code>x * y</code> (e.g. in one operation the array <code>[1, 2, 2, 3]</code> with <code>k = 5</code> can become <code>[1, 4, 3]</code> or <code>[2, 2, 3]</code>, but can&#39;t become <code>[1, 2, 6]</code>).</li>
</ul>

<p>Return <em>the <strong>minimum</strong> possible length of </em><code>nums</code><em> after any number of operations</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,3,3,7,3,5], k = 20
<strong>Output:</strong> 3
<strong>Explanation:</strong> We perform these operations:
1. [<u>2,3</u>,3,7,3,5] -&gt; [<u>6</u>,3,7,3,5]
2. [<u>6,3</u>,7,3,5] -&gt; [<u>18</u>,7,3,5]
3. [18,7,<u>3,5</u>] -&gt; [18,7,<u>15</u>]
It can be shown that 3 is the minimum length possible to achieve with the given operation.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,3,3,3], k = 6
<strong>Output:</strong> 4
<strong>Explanation:</strong> We can&#39;t perform any operations since the product of every two adjacent elements is greater than 6.
Hence, the answer is 4.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：贪心**

我们用一个变量 $ans$ 记录当前数组的长度，用一个变量 $y$ 记录当前数组的乘积，初始时 $ans = 1$, $y = nums[0]$。

我们从数组的第二个元素开始遍历，设当前元素为 $x$：

-   如果 $x = 0$，那么整个数组的乘积为 $0 \le k$，因此答案数组的最小长度为 $1$，直接返回即可。
-   如果 $x \times y \le k$，那么我们可以将 $x$ 与 $y$ 合并，即 $y = x \times y$。
-   如果 $x \times y \gt k$，那么我们无法将 $x$ 与 $y$ 合并，因此我们需要将 $x$ 单独作为一个元素，即 $ans = ans + 1$，并且 $y = x$。

最终答案即为 $ans$。

时间复杂度 $O(n)$，其中 $n$ 为数组的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minArrayLength(self, nums: List[int], k: int) -> int:
        ans, y = 1, nums[0]
        for x in nums[1:]:
            if x == 0:
                return 1
            if x * y <= k:
                y *= x
            else:
                y = x
                ans += 1
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minArrayLength(int[] nums, int k) {
        int ans = 1;
        long y = nums[0];
        for (int i = 1; i < nums.length; ++i) {
            int x = nums[i];
            if (x == 0) {
                return 1;
            }
            if (x * y <= k) {
                y *= x;
            } else {
                y = x;
                ++ans;
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
    int minArrayLength(vector<int>& nums, int k) {
        int ans = 1;
        long long y = nums[0];
        for (int i = 1; i < nums.size(); ++i) {
            int x = nums[i];
            if (x == 0) {
                return 1;
            }
            if (x * y <= k) {
                y *= x;
            } else {
                y = x;
                ++ans;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func minArrayLength(nums []int, k int) int {
	ans, y := 1, nums[0]
	for _, x := range nums[1:] {
		if x == 0 {
			return 1
		}
		if x*y <= k {
			y *= x
		} else {
			y = x
			ans++
		}
	}
	return ans
}
```

### **TypeScript**

```ts
function minArrayLength(nums: number[], k: number): number {
    let [ans, y] = [1, nums[0]];
    for (const x of nums.slice(1)) {
        if (x === 0) {
            return 1;
        }
        if (x * y <= k) {
            y *= x;
        } else {
            y = x;
            ++ans;
        }
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
