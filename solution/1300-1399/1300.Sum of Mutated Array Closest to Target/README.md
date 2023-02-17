# [1300. 转变数组后最接近目标值的数组和](https://leetcode.cn/problems/sum-of-mutated-array-closest-to-target)

[English Version](/solution/1300-1399/1300.Sum%20of%20Mutated%20Array%20Closest%20to%20Target/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组&nbsp;<code>arr</code> 和一个目标值&nbsp;<code>target</code> ，请你返回一个整数&nbsp;<code>value</code>&nbsp;，使得将数组中所有大于&nbsp;<code>value</code> 的值变成&nbsp;<code>value</code> 后，数组的和最接近&nbsp; <code>target</code>&nbsp;（最接近表示两者之差的绝对值最小）。</p>

<p>如果有多种使得和最接近&nbsp;<code>target</code>&nbsp;的方案，请你返回这些整数中的最小值。</p>

<p>请注意，答案不一定是&nbsp;<code>arr</code> 中的数字。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>arr = [4,9,3], target = 10
<strong>输出：</strong>3
<strong>解释：</strong>当选择 value 为 3 时，数组会变成 [3, 3, 3]，和为 9 ，这是最接近 target 的方案。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>arr = [2,3,5], target = 10
<strong>输出：</strong>5
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>arr = [60864,25176,27249,21296,20204], target = 56803
<strong>输出：</strong>11361
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 10^4</code></li>
	<li><code>1 &lt;= arr[i], target &lt;= 10^5</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：排序 + 前缀和 + 二分查找 + 枚举**

我们注意到，题目中要把所有大于 `value` 的值变成 `value`，并且求和，因此我们可以考虑先对数组 `arr` 进行排序，然后求出前缀和数组 $s$，其中 $s[i]$ 表示数组前 $i$ 个元素之和。

接下来，我们可以从小到大枚举所有 `value` 值，对于每个 `value`，我们可以通过二分查找找到数组中第一个大于 `value` 的元素的下标 $i$，此时数组中大于 `value` 的元素个数为 $n - i$，因此数组中小于等于 `value` 的元素个数为 $i$，此时数组中小于等于 `value` 的元素之和为 $s[i]$，数组中大于 `value` 的元素之和为 $(n - i) \times value$，因此数组中所有元素之和为 $s[i] + (n - i) \times value$。如果 $s[i] + (n - i) \times value$ 与 `target` 的差的绝对值小于当前的最小差值 `diff`，则更新 `diff` 和 `ans`。

枚举完所有 `value` 后，即可得到最终答案 `ans`。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 `arr` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findBestValue(self, arr: List[int], target: int) -> int:
        arr.sort()
        s = list(accumulate(arr, initial=0))
        ans, diff = 0, inf
        for value in range(max(arr) + 1):
            i = bisect_right(arr, value)
            d = abs(s[i] + (len(arr) - i) * value - target)
            if diff > d:
                diff = d
                ans = value
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int findBestValue(int[] arr, int target) {
        Arrays.sort(arr);
        int n = arr.length;
        int[] s = new int[n + 1];
        int mx = 0;
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + arr[i];
            mx = Math.max(mx, arr[i]);
        }
        int ans = 0, diff = 1 << 30;
        for (int value = 0; value <= mx; ++value) {
            int i = search(arr, value);
            int d = Math.abs(s[i] + (n - i) * value - target);
            if (diff > d) {
                diff = d;
                ans = value;
            }
        }
        return ans;
    }

    private int search(int[] arr, int x) {
        int left = 0, right = arr.length;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (arr[mid] > x) {
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
    int findBestValue(vector<int>& arr, int target) {
        sort(arr.begin(), arr.end());
        int n = arr.size();
        int s[n + 1];
        s[0] = 0;
        int mx = 0;
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + arr[i];
            mx = max(mx, arr[i]);
        }
        int ans = 0, diff = 1 << 30;
        for (int value = 0; value <= mx; ++value) {
            int i = upper_bound(arr.begin(), arr.end(), value) - arr.begin();
            int d = abs(s[i] + (n - i) * value - target);
            if (diff > d) {
                diff = d;
                ans = value;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func findBestValue(arr []int, target int) (ans int) {
	sort.Ints(arr)
	n := len(arr)
	s := make([]int, n+1)
	mx := 0
	for i, x := range arr {
		s[i+1] = s[i] + x
		mx = max(mx, x)
	}
	diff := 1 << 30
	for value := 0; value <= mx; value++ {
		i := sort.SearchInts(arr, value+1)
		d := abs(s[i] + (n-i)*value - target)
		if diff > d {
			diff = d
			ans = value
		}
	}
	return
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
