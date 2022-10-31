# [1664. 生成平衡数组的方案数](https://leetcode.cn/problems/ways-to-make-a-fair-array)

[English Version](/solution/1600-1699/1664.Ways%20to%20Make%20a%20Fair%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>nums</code> 。你需要选择 <strong>恰好</strong> 一个下标（下标从 <strong>0</strong> 开始）并删除对应的元素。请注意剩下元素的下标可能会因为删除操作而发生改变。</p>

<p>比方说，如果 <code>nums = [6,1,7,4,1]</code> ，那么：</p>

<ul>
	<li>选择删除下标 <code>1</code> ，剩下的数组为 <code>nums = [6,7,4,1]</code> 。</li>
	<li>选择删除下标 <code>2</code> ，剩下的数组为 <code>nums = [6,1,4,1]</code> 。</li>
	<li>选择删除下标 <code>4</code> ，剩下的数组为 <code>nums = [6,1,7,4]</code> 。</li>
</ul>

<p>如果一个数组满足奇数下标元素的和与偶数下标元素的和相等，该数组就是一个 <strong>平衡数组</strong> 。</p>

<p>请你返回删除操作后，剩下的数组<em> </em><code>nums</code><em> </em>是 <strong>平衡数组</strong> 的 <strong>方案数</strong> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [2,1,6,4]
<b>输出：</b>1
<strong>解释：</strong>
删除下标 0 ：[1,6,4] -> 偶数元素下标为：1 + 4 = 5 。奇数元素下标为：6 。不平衡。
删除下标 1 ：[2,6,4] -> 偶数元素下标为：2 + 4 = 6 。奇数元素下标为：6 。平衡。
删除下标 2 ：[2,1,4] -> 偶数元素下标为：2 + 4 = 6 。奇数元素下标为：1 。不平衡。
删除下标 3 ：[2,1,6] -> 偶数元素下标为：2 + 6 = 8 。奇数元素下标为：1 。不平衡。
只有一种让剩余数组成为平衡数组的方案。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [1,1,1]
<b>输出：</b>3
<b>解释：</b>你可以删除任意元素，剩余数组都是平衡数组。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>nums = [1,2,3]
<b>输出：</b>0
<b>解释：</b>不管删除哪个元素，剩下数组都不是平衡数组。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= nums.length <= 10<sup>5</sup></code></li>
	<li><code>1 <= nums[i] <= 10<sup>4</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：预处理 + 枚举**

我们先预处理得到数组 `nums` 的奇数下标元素之和 $x$ 以及偶数下标元素之和 $y$。

然后从前往后枚举数组 `nums` 的每个元素 $v$，用变量 $a$ 和 $b$ 分别记录已遍历的奇数下标元素之和以及偶数下标元素之和。

我们观察发现，对于当前遍历到的元素 $v$，如果删除了，那么该元素之后的奇偶下标元素之和会发生交换。此时，我们先判断该位置下标 $i$ 是奇数还是偶数。

如果是奇数下标，删除该元素后，数组的偶数下标元素之和为 $x-v-a+b$，而奇数下标元素之和为 $y-b+a$，如果这两个和相等，那么就是一个平衡数组，答案加一。

如果是偶数下标，删除该元素后，数组的奇数下标元素之和为 $y-v-b+a$，而偶数下标元素之和为 $x-a+b$，如果这两个和相等，那么就是一个平衡数组，答案加一。

然后我们更新 $a$ 和 $b$，继续遍历下一个元素。

遍历完数组后，即可得到答案。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为数组的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def waysToMakeFair(self, nums: List[int]) -> int:
        x, y = sum(nums[1::2]), sum(nums[::2])
        ans = 0
        a = b = 0
        for i, v in enumerate(nums):
            if (i & 1) and x - v - a + b == y - b + a:
                ans += 1
            elif (i & 1) == 0 and y - v - b + a == x - a + b:
                ans += 1
            if i & 1:
                a += v
            else:
                b += v
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int waysToMakeFair(int[] nums) {
        int n = nums.length;
        int x = 0, y = 0;
        for (int i = 0; i < n; ++i) {
            if (i % 2 == 1) {
                x += nums[i];
            } else {
                y += nums[i];
            }
        }
        int ans = 0;
        int a = 0, b = 0;
        for (int i = 0; i < n; ++i) {
            int v = nums[i];
            if (i % 2 == 1 && x - v - a + b == y - b + a) {
                ++ans;
            } else if (i % 2 == 0 && y - v - b + a == x - a + b) {
                ++ans;
            }
            if (i % 2 == 1) {
                a += v;
            } else {
                b += v;
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
    int waysToMakeFair(vector<int>& nums) {
        int n = nums.size();
        int x = 0, y = 0;
        for (int i = 0; i < n; ++i) {
            if (i & 1)
                x += nums[i];
            else
                y += nums[i];
        }
        int ans = 0;
        int a = 0, b = 0;
        for (int i = 0; i < n; ++i) {
            int v = nums[i];
            if (i % 2 == 1 && x - v - a + b == y - b + a) ++ans;
            if (i % 2 == 0 && y - v - b + a == x - a + b) ++ans;
            if (i % 2 == 1)
                a += v;
            else
                b += v;
        }
        return ans;
    }
};
```

### **Go**

```go
func waysToMakeFair(nums []int) (ans int) {
	x, y := 0, 0
	for i, v := range nums {
		if i%2 == 1 {
			x += v
		} else {
			y += v
		}
	}
	a, b := 0, 0
	for i, v := range nums {
		if i%2 == 1 && x-v-a+b == y-b+a {
			ans++
		}
		if i%2 == 0 && y-v-b+a == x-a+b {
			ans++
		}
		if i%2 == 1 {
			a += v
		} else {
			b += v
		}
	}
	return
}
```

### **...**

```

```

<!-- tabs:end -->
