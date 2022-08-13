# [2344. 使数组可以被整除的最少删除次数](https://leetcode.cn/problems/minimum-deletions-to-make-array-divisible)

[English Version](/solution/2300-2399/2344.Minimum%20Deletions%20to%20Make%20Array%20Divisible/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个正整数数组&nbsp;<code>nums</code> 和&nbsp;<code>numsDivide</code>&nbsp;。你可以从&nbsp;<code>nums</code>&nbsp;中删除任意数目的元素。</p>

<p>请你返回使 <code>nums</code>&nbsp;中 <strong>最小</strong>&nbsp;元素可以整除 <code>numsDivide</code>&nbsp;中所有元素的 <strong>最少</strong>&nbsp;删除次数。如果无法得到这样的元素，返回 <code>-1</code>&nbsp;。</p>

<p>如果&nbsp;<code>y % x == 0</code>&nbsp;，那么我们说整数&nbsp;<code>x</code>&nbsp;整除&nbsp;<code>y</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>nums = [2,3,2,4,3], numsDivide = [9,6,9,3,15]
<b>输出：</b>2
<b>解释：</b>
[2,3,2,4,3] 中最小元素是 2 ，它无法整除 numsDivide 中所有元素。
我们从 nums 中删除 2 个大小为 2 的元素，得到 nums = [3,4,3] 。
[3,4,3] 中最小元素为 3 ，它可以整除 numsDivide 中所有元素。
可以证明 2 是最少删除次数。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>nums = [4,3,6], numsDivide = [8,2,6,10]
<b>输出：</b>-1
<b>解释：</b>
我们想 nums 中的最小元素可以整除 numsDivide 中的所有元素。
没有任何办法可以达到这一目的。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length, numsDivide.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i], numsDivide[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minOperations(self, nums: List[int], numsDivide: List[int]) -> int:
        x = numsDivide[0]
        for v in numsDivide[1:]:
            x = gcd(x, v)
        nums.sort()
        for i, v in enumerate(nums):
            if x % v == 0:
                return i
        return -1
```

```python
class Solution:
    def minOperations(self, nums: List[int], numsDivide: List[int]) -> int:
        x = reduce(gcd, numsDivide)
        nums.sort()
        return next((i for i, v in enumerate(nums) if x % v == 0), -1)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minOperations(int[] nums, int[] numsDivide) {
        int x = numsDivide[0];
        for (int i = 1; i < numsDivide.length; ++i) {
            x = gcd(x, numsDivide[i]);
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; ++i) {
            if (x % nums[i] == 0) {
                return i;
            }
        }
        return -1;
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
    int minOperations(vector<int>& nums, vector<int>& numsDivide) {
        int x = numsDivide[0];
        for (int i = 1; i < numsDivide.size(); ++i) x = gcd(x, numsDivide[i]);
        sort(nums.begin(), nums.end());
        for (int i = 0; i < nums.size(); ++i)
            if (x % nums[i] == 0) return i;
        return -1;
    }

    int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
};
```

### **Go**

```go
func minOperations(nums []int, numsDivide []int) int {
	x := numsDivide[0]
	for _, v := range numsDivide[1:] {
		x = gcd(x, v)
	}
	sort.Ints(nums)
	for i, v := range nums {
		if x%v == 0 {
			return i
		}
	}
	return -1
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

```

### **...**

```

```

<!-- tabs:end -->
