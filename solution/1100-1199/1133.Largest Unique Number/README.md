# [1133. 最大唯一数](https://leetcode.cn/problems/largest-unique-number)

[English Version](/solution/1100-1199/1133.Largest%20Unique%20Number/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组&nbsp;<code>A</code>，请找出并返回在该数组中仅出现一次的最大整数。</p>

<p>如果不存在这个只出现一次的整数，则返回 -1。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>[5,7,3,9,4,9,8,3,1]
<strong>输出：</strong>8
<strong>解释： </strong>
数组中最大的整数是 9，但它在数组中重复出现了。而第二大的整数是 8，它只出现了一次，所以答案是 8。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>[9,9,8,8]
<strong>输出：</strong>-1
<strong>解释： </strong>
数组中不存在仅出现一次的整数。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>1 &lt;= A.length &lt;= 2000</code></li>
	<li><code>0 &lt;= A[i] &lt;= 1000</code></li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：计数 + 倒序遍历**

注意到题目的数据范围，我们可以使用一个长度为 $1001$ 的数组来统计每个数字出现的次数，然后倒序遍历数组，找到第一个出现次数为 $1$ 的数字即可。如果没有找到，则返回 $-1$。

时间复杂度 $O(n + M)$，空间复杂度 $O(M)$。其中 $n$ 为数组长度；而 $M$ 为数组中出现的最大数字，本题中 $M \leq 1000$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def largestUniqueNumber(self, nums: List[int]) -> int:
        cnt = Counter(nums)
        return next((x for x in range(1000, -1, -1) if cnt[x] == 1), -1)
```

```python
class Solution:
    def largestUniqueNumber(self, nums: List[int]) -> int:
        cnt = Counter(nums)
        return max((x for x, v in cnt.items() if v == 1), default=-1)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int largestUniqueNumber(int[] nums) {
        int[] cnt = new int[1001];
        for (int x : nums) {
            ++cnt[x];
        }
        for (int x = 1000; x >= 0; --x) {
            if (cnt[x] == 1) {
                return x;
            }
        }
        return -1;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int largestUniqueNumber(vector<int>& nums) {
        int cnt[1001]{};
        for (int& x : nums) {
            ++cnt[x];
        }
        for (int x = 1000; ~x; --x) {
            if (cnt[x] == 1) {
                return x;
            }
        }
        return -1;
    }
};
```

### **Go**

```go
func largestUniqueNumber(nums []int) int {
	cnt := [1001]int{}
	for _, x := range nums {
		cnt[x]++
	}
	for x := 1000; x >= 0; x-- {
		if cnt[x] == 1 {
			return x
		}
	}
	return -1
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var largestUniqueNumber = function (nums) {
    const cnt = new Array(1001).fill(0);
    for (const x of nums) {
        ++cnt[x];
    }
    for (let x = 1000; x >= 0; --x) {
        if (cnt[x] == 1) {
            return x;
        }
    }
    return -1;
};
```

### **TypeScript**

```ts
function largestUniqueNumber(nums: number[]): number {
    const cnt = new Array(1001).fill(0);
    for (const x of nums) {
        ++cnt[x];
    }
    for (let x = 1000; x >= 0; --x) {
        if (cnt[x] == 1) {
            return x;
        }
    }
    return -1;
}
```

### **...**

```

```

<!-- tabs:end -->
