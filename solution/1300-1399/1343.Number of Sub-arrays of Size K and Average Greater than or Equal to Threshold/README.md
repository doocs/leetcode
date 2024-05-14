# [1343. 大小为 K 且平均值大于等于阈值的子数组数目](https://leetcode.cn/problems/number-of-sub-arrays-of-size-k-and-average-greater-than-or-equal-to-threshold)

[English Version](/solution/1300-1399/1343.Number%20of%20Sub-arrays%20of%20Size%20K%20and%20Average%20Greater%20than%20or%20Equal%20to%20Threshold/README_EN.md)

<!-- tags:数组,滑动窗口 -->

<!-- difficulty:中等 -->

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

### 方法一：滑动窗口

不妨将 `threshold` 乘以 $k$，这样我们就可以直接比较窗口内的和与 `threshold` 的大小关系。

我们维护一个长度为 $k$ 的滑动窗口，每次计算窗口内的和 $s$，如果 $s$ 大于等于 `threshold`，则答案加一。

时间复杂度 $O(n)$，其中 $n$ 为数组 `arr` 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def numOfSubarrays(self, arr: List[int], k: int, threshold: int) -> int:
        threshold *= k
        s = sum(arr[:k])
        ans = int(s >= threshold)
        for i in range(k, len(arr)):
            s += arr[i] - arr[i - k]
            ans += int(s >= threshold)
        return ans
```

```java
class Solution {
    public int numOfSubarrays(int[] arr, int k, int threshold) {
        threshold *= k;
        int s = 0;
        for (int i = 0; i < k; ++i) {
            s += arr[i];
        }
        int ans = s >= threshold ? 1 : 0;
        for (int i = k; i < arr.length; ++i) {
            s += arr[i] - arr[i - k];
            ans += s >= threshold ? 1 : 0;
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int numOfSubarrays(vector<int>& arr, int k, int threshold) {
        threshold *= k;
        int s = accumulate(arr.begin(), arr.begin() + k, 0);
        int ans = s >= threshold;
        for (int i = k; i < arr.size(); ++i) {
            s += arr[i] - arr[i - k];
            ans += s >= threshold;
        }
        return ans;
    }
};
```

```go
func numOfSubarrays(arr []int, k int, threshold int) (ans int) {
	threshold *= k
	s := 0
	for _, x := range arr[:k] {
		s += x
	}
	if s >= threshold {
		ans++
	}
	for i := k; i < len(arr); i++ {
		s += arr[i] - arr[i-k]
		if s >= threshold {
			ans++
		}
	}
	return
}
```

```ts
function numOfSubarrays(arr: number[], k: number, threshold: number): number {
    threshold *= k;
    let s = arr.slice(0, k).reduce((acc, cur) => acc + cur, 0);
    let ans = s >= threshold ? 1 : 0;
    for (let i = k; i < arr.length; ++i) {
        s += arr[i] - arr[i - k];
        ans += s >= threshold ? 1 : 0;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
