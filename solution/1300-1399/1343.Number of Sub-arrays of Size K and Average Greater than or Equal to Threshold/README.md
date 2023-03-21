# [1343. 大小为 K 且平均值大于等于阈值的子数组数目](https://leetcode.cn/problems/number-of-sub-arrays-of-size-k-and-average-greater-than-or-equal-to-threshold)

[English Version](/solution/1300-1399/1343.Number%20of%20Sub-arrays%20of%20Size%20K%20and%20Average%20Greater%20than%20or%20Equal%20to%20Threshold/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组&nbsp;<code>arr</code>&nbsp;和两个整数 <code>k</code>&nbsp;和 <code>threshold</code>&nbsp;。</p>

<p>请你返回长度为 <code>k</code>&nbsp;且平均值大于等于&nbsp;<code>threshold</code>&nbsp;的子数组数目。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>arr = [2,2,2,2,5,5,5,8], k = 3, threshold = 4
<strong>输出：</strong>3
<strong>解释：</strong>子数组 [2,5,5],[5,5,5] 和 [5,5,8] 的平均值分别为 4，5 和 6 。其他长度为 3 的子数组的平均值都小于 4 （threshold 的值)。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>arr = [11,13,17,23,29,31,7,5,2,3], k = 3, threshold = 5
<strong>输出：</strong>6
<strong>解释：</strong>前 6 个长度为 3 的子数组平均值都大于 5 。注意平均值不是整数。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= arr[i] &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= k &lt;= arr.length</code></li>
	<li><code>0 &lt;= threshold &lt;= 10<sup>4</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：滑动窗口**

我们可以维护一个长度为 $k$ 的滑动窗口，窗口内的元素之和为 $s$，每次判断 $\frac{s}{k}$ 是否大于等于 $threshold$，如果大于等于，则满足条件的子数组个数加一。

最后返回满足条件的子数组个数即可。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为数组 $arr$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def numOfSubarrays(self, arr: List[int], k: int, threshold: int) -> int:
        s = sum(arr[:k])
        ans = int(s / k >= threshold)
        for i in range(k, len(arr)):
            s += arr[i]
            s -= arr[i - k]
            ans += int(s / k >= threshold)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int numOfSubarrays(int[] arr, int k, int threshold) {
        int s = 0;
        for (int i = 0; i < k; ++i) {
            s += arr[i];
        }
        int ans = s / k >= threshold ? 1 : 0;
        for (int i = k; i < arr.length; ++i) {
            s += arr[i] - arr[i - k];
            ans += s / k >= threshold ? 1 : 0;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int numOfSubarrays(vector<int>& arr, int k, int threshold) {
        int s = accumulate(arr.begin(), arr.begin() + k, 0);
        int ans = s >= k * threshold;
        for (int i = k; i < arr.size(); ++i) {
            s += arr[i] - arr[i - k];
            ans += s >= k * threshold;
        }
        return ans;
    }
};
```

### **Go**

```go
func numOfSubarrays(arr []int, k int, threshold int) (ans int) {
	s := 0
	for _, x := range arr[:k] {
		s += x
	}
	if s/k >= threshold {
		ans++
	}
	for i := k; i < len(arr); i++ {
		s += arr[i] - arr[i-k]
		if s/k >= threshold {
			ans++
		}
	}
	return
}
```

### **TypeScript**

```ts
function numOfSubarrays(arr: number[], k: number, threshold: number): number {
    let s = arr.slice(0, k).reduce((acc, cur) => acc + cur, 0);
    let ans = s >= k * threshold ? 1 : 0;
    for (let i = k; i < arr.length; ++i) {
        s += arr[i] - arr[i - k];
        ans += s >= k * threshold ? 1 : 0;
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
