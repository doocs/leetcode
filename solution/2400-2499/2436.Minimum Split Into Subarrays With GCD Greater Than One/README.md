# [2436. 使子数组最大公约数大于一的最小分割数](https://leetcode.cn/problems/minimum-split-into-subarrays-with-gcd-greater-than-one)

[English Version](/solution/2400-2499/2436.Minimum%20Split%20Into%20Subarrays%20With%20GCD%20Greater%20Than%20One/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个由正整数组成的数组 <code>nums</code>。</p>

<p>将数组拆分为&nbsp;<strong>一个或多个&nbsp;</strong>不相连的子数组，如下所示:</p>

<ul>
	<li>数组中的每个元素都&nbsp;<strong>只属于一个&nbsp;</strong>子数组，并且</li>
	<li>每个子数组元素的 <strong>最大公约数</strong> 严格大于 <code>1</code>。</li>
</ul>

<p>返回<em>拆分后可获得的子数组的最小数目。</em></p>

<p><b>注意</b>:</p>

<ul>
	<li>子数组的 <strong>最大公约数&nbsp;</strong>是能将子数组中所有元素整除的最大正整数。</li>
	<li>
	<p data-group="1-1"><strong>子数组&nbsp;</strong>是数组的连续部分。</p>
	</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> nums = [12,6,3,14,8]
<strong>输出:</strong> 2
<strong>解释:</strong> 我们可以把数组分成子数组:[12,6,3] 和 [14,8]。
- 12、6、3 的最大公约数是 3，严格大于 1。
- 14 和 8 的最大公约数是 2，严格来说大于 1。
可以看出，如果不拆分数组将使最大公约数 = 1。
</pre>

<p><strong>示例&nbsp;2:</strong></p>

<pre>
<strong>输入:</strong> nums = [4,12,6,14]
<strong>输出:</strong> 1
<strong>解释:</strong> 我们可以将数组拆分为一个子数组，即整个数组。
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 2000</code></li>
	<li><code>2 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：贪心 + 数学**

对于数组中的每个元素，如果它与前面的元素的最大公约数为 $1$，那么它需要作为一个新的子数组的第一个元素。否则，它可以与前面的元素放在同一个子数组中。

因此，我们先初始化一个变量 $g$，表示当前子数组的最大公约数。初始时 $g=0$，答案变量 $ans=1$。

接下来，我们从前往后遍历数组，维护当前子数组的最大公约数 $g$。如果当前元素 $x$ 与 $g$ 的最大公约数为 $1$，那么我们需要将当前元素作为一个新的子数组的第一个元素，因此，答案加 $1$，并将 $g$ 更新为 $x$。否则，当前元素可以与前面的元素放在同一个子数组中。继续遍历数组，直到遍历结束。

时间复杂度 $O(n \times \log m)$，空间复杂度 $O(1)$。其中 $n$ 和 $m$ 分别是数组的长度和数组中元素的最大值。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minimumSplits(self, nums: List[int]) -> int:
        ans, g = 1, 0
        for x in nums:
            g = gcd(g, x)
            if g == 1:
                ans += 1
                g = x
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minimumSplits(int[] nums) {
        int ans = 1, g = 0;
        for (int x : nums) {
            g = gcd(g, x);
            if (g == 1) {
                ++ans;
                g = x;
            }
        }
        return ans;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minimumSplits(vector<int>& nums) {
        int ans = 1, g = 0;
        for (int x : nums) {
            g = gcd(g, x);
            if (g == 1) {
                ++ans;
                g = x;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func minimumSplits(nums []int) int {
	ans, g := 1, 0
	for _, x := range nums {
		g = gcd(g, x)
		if g == 1 {
			ans++
			g = x
		}
	}
	return ans
}

func gcd(a, b int) int {
	if b == 0 {
		return a
	}
	return gcd(b, a%b)
}
```

### **TypeScript**

```ts
function minimumSplits(nums: number[]): number {
    let ans = 1;
    let g = 0;
    for (const x of nums) {
        g = gcd(g, x);
        if (g == 1) {
            ++ans;
            g = x;
        }
    }
    return ans;
}

function gcd(a: number, b: number): number {
    return b ? gcd(b, a % b) : a;
}
```

### **...**

```

```

<!-- tabs:end -->
