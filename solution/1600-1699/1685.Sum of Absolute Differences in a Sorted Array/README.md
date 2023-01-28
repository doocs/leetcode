# [1685. 有序数组中差绝对值之和](https://leetcode.cn/problems/sum-of-absolute-differences-in-a-sorted-array)

[English Version](/solution/1600-1699/1685.Sum%20of%20Absolute%20Differences%20in%20a%20Sorted%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个 <strong>非递减 </strong>有序整数数组 <code>nums</code> 。</p>

<p>请你建立并返回一个整数数组<em> </em><code>result</code>，它跟<em> </em><code>nums</code> 长度相同，且<code>result[i]</code> 等于<em> </em><code>nums[i]</code> 与数组中所有其他元素差的绝对值之和。</p>

<p>换句话说， <code>result[i]</code> 等于 <code>sum(|nums[i]-nums[j]|)</code> ，其中 <code>0 <= j < nums.length</code> 且 <code>j != i</code> （下标从 0 开始）。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [2,3,5]
<b>输出：</b>[4,3,5]
<b>解释：</b>假设数组下标从 0 开始，那么
result[0] = |2-2| + |2-3| + |2-5| = 0 + 1 + 3 = 4，
result[1] = |3-2| + |3-3| + |3-5| = 1 + 0 + 2 = 3，
result[2] = |5-2| + |5-3| + |5-5| = 3 + 2 + 0 = 5。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [1,4,6,8,10]
<b>输出：</b>[24,15,13,15,21]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 <= nums.length <= 10<sup>5</sup></code></li>
	<li><code>1 <= nums[i] <= nums[i + 1] <= 10<sup>4</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：求和 + 枚举**

我们先求出数组 `nums` 所有元素的和，记为 $s$，用变量 $t$ 记录当前已经枚举过的元素之和。

接下来枚举 $nums[i]$，那么 $ans[i] = nums[i] \times i - t + s - t - nums[i] \times (n - i)$，然后我们更新 $t$，即 $t = t + nums[i]$。继续枚举下一个元素，直到枚举完所有元素。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为数组 `nums` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def getSumAbsoluteDifferences(self, nums: List[int]) -> List[int]:
        ans = []
        s, t = sum(nums), 0
        for i, x in enumerate(nums):
            v = x * i - t + s - t - x * (len(nums) - i)
            ans.append(v)
            t += x
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] getSumAbsoluteDifferences(int[] nums) {
        // int s = Arrays.stream(nums).sum();
        int s = 0, t = 0;
        for (int x : nums) {
            s += x;
        }
        int n = nums.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            int v = nums[i] * i - t + s - t - nums[i] * (n - i);
            ans[i] = v;
            t += nums[i];
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> getSumAbsoluteDifferences(vector<int>& nums) {
        int s = accumulate(nums.begin(), nums.end(), 0), t = 0;
        int n = nums.size();
        vector<int> ans(n);
        for (int i = 0; i < n; ++i) {
            int v = nums[i] * i - t + s - t - nums[i] * (n - i);
            ans[i] = v;
            t += nums[i];
        }
        return ans;
    }
};
```

### **Go**

```go
func getSumAbsoluteDifferences(nums []int) (ans []int) {
	var s, t int
	for _, x := range nums {
		s += x
	}
	for i, x := range nums {
		v := x*i - t + s - t - x*(len(nums)-i)
		ans = append(ans, v)
		t += x
	}
	return
}
```

### **TypeScript**

```ts
function getSumAbsoluteDifferences(nums: number[]): number[] {
    const s = nums.reduce((a, b) => a + b);
    let t = 0;
    const n = nums.length;
    const ans = new Array(n);
    for (let i = 0; i < n; ++i) {
        const v = nums[i] * i - t + s - t - nums[i] * (n - i);
        ans[i] = v;
        t += nums[i];
    }
    return ans;
}
```

### **C#**

```cs
public class Solution {
    public int[] GetSumAbsoluteDifferences(int[] nums) {
        int s = 0, t = 0;
        foreach (int x in nums) {
            s += x;
        }
        int n = nums.Length;
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            int v = nums[i] * i - t + s - t - nums[i] * (n - i);
            ans[i] = v;
            t += nums[i];
        }
        return ans;
    }
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @return {number[]}
 */
var getSumAbsoluteDifferences = function (nums) {
    const s = nums.reduce((a, b) => a + b);
    let t = 0;
    const n = nums.length;
    const ans = new Array(n);
    for (let i = 0; i < n; ++i) {
        const v = nums[i] * i - t + s - t - nums[i] * (n - i);
        ans[i] = v;
        t += nums[i];
    }
    return ans;
};
```

### **...**

```

```

<!-- tabs:end -->
