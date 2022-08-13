# [1283. 使结果不超过阈值的最小除数](https://leetcode.cn/problems/find-the-smallest-divisor-given-a-threshold)

[English Version](/solution/1200-1299/1283.Find%20the%20Smallest%20Divisor%20Given%20a%20Threshold/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组&nbsp;<code>nums</code> 和一个正整数&nbsp;<code>threshold</code> &nbsp;，你需要选择一个正整数作为除数，然后将数组里每个数都除以它，并对除法结果求和。</p>

<p>请你找出能够使上述结果小于等于阈值&nbsp;<code>threshold</code>&nbsp;的除数中 <strong>最小</strong> 的那个。</p>

<p>每个数除以除数后都向上取整，比方说 7/3 = 3 ， 10/2 = 5 。</p>

<p>题目保证一定有解。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,5,9], threshold = 6
<strong>输出：</strong>5
<strong>解释：</strong>如果除数为 1 ，我们可以得到和为 17 （1+2+5+9）。
如果除数为 4 ，我们可以得到和为 7 (1+1+2+3) 。如果除数为 5 ，和为 5 (1+1+1+2)。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,3,5,7,11], threshold = 11
<strong>输出：</strong>3
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [19], threshold = 5
<strong>输出：</strong>4
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 5 * 10^4</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10^6</code></li>
	<li><code>nums.length &lt;=&nbsp;threshold &lt;= 10^6</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

二分查找。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def smallestDivisor(self, nums: List[int], threshold: int) -> int:
        left, right = 1, 10**6
        while left < right:
            mid = (left + right) >> 1
            s = sum((v + mid - 1) // mid for v in nums)
            if s <= threshold:
                right = mid
            else:
                left = mid + 1
        return left
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int smallestDivisor(int[] nums, int threshold) {
        int left = 1, right = 1000000;
        while (left < right) {
            int mid = (left + right) >> 1;
            int s = 0;
            for (int v : nums) {
                s += (v + mid - 1) / mid;
            }
            if (s <= threshold) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int smallestDivisor(vector<int>& nums, int threshold) {
        int left = 1, right = 1e6;
        while (left < right) {
            int mid = (left + right) >> 1;
            int s = 0;
            for (int& v : nums) s += (v + mid - 1) / mid;
            if (s <= threshold)
                right = mid;
            else
                left = mid + 1;
        }
        return left;
    }
};
```

### **Go**

```go
func smallestDivisor(nums []int, threshold int) int {
	left, right := 1, 1000000
	for left < right {
		mid := (left + right) >> 1
		s := 0
		for _, v := range nums {
			s += (v + mid - 1) / mid
		}
		if s <= threshold {
			right = mid
		} else {
			left = mid + 1
		}
	}
	return left
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @param {number} threshold
 * @return {number}
 */
var smallestDivisor = function (nums, threshold) {
    let left = 1,
        right = 1000000;
    while (left < right) {
        const mid = (left + right) >> 1;
        let s = 0;
        for (let v of nums) {
            s += Math.ceil(v / mid);
        }
        if (s <= threshold) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    return left;
};
```

### **TypeScript**

```ts
function smallestDivisor(nums: number[], threshold: number): number {
    let left = 1,
        right = 1000000;
    while (left < right) {
        const mid = (left + right) >> 1;
        let s = 0;
        for (let v of nums) {
            s += Math.ceil(v / mid);
        }
        if (s <= threshold) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    return left;
}
```

### **...**

```

```

<!-- tabs:end -->
