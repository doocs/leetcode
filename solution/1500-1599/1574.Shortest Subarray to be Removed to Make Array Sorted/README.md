# [1574. 删除最短的子数组使剩余数组有序](https://leetcode.cn/problems/shortest-subarray-to-be-removed-to-make-array-sorted)

[English Version](/solution/1500-1599/1574.Shortest%20Subarray%20to%20be%20Removed%20to%20Make%20Array%20Sorted/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>arr</code>&nbsp;，请你删除一个子数组（可以为空），使得 <code>arr</code>&nbsp;中剩下的元素是 <strong>非递减</strong> 的。</p>

<p>一个子数组指的是原数组中连续的一个子序列。</p>

<p>请你返回满足题目要求的最短子数组的长度。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>arr = [1,2,3,10,4,2,3,5]
<strong>输出：</strong>3
<strong>解释：</strong>我们需要删除的最短子数组是 [10,4,2] ，长度为 3 。剩余元素形成非递减数组 [1,2,3,3,5] 。
另一个正确的解为删除子数组 [3,10,4] 。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>arr = [5,4,3,2,1]
<strong>输出：</strong>4
<strong>解释：</strong>由于数组是严格递减的，我们只能保留一个元素。所以我们需要删除长度为 4 的子数组，要么删除 [5,4,3,2]，要么删除 [4,3,2,1]。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>arr = [1,2,3]
<strong>输出：</strong>0
<strong>解释：</strong>数组已经是非递减的了，我们不需要删除任何元素。
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>arr = [1]
<strong>输出：</strong>0
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 10^5</code></li>
	<li><code>0 &lt;= arr[i] &lt;= 10^9</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：双指针 + 二分查找**

我们先找出数组的最长非递减前缀和最长非递减后缀，分别记为 $nums[0..i]$ 和 $nums[j..n-1]$。

如果 $i \geq j$，说明数组本身就是非递减的，返回 $0$。

否则，我们可以选择删除右侧后缀，也可以选择删除左侧前缀，因此初始时答案为 $min(n - i - 1, j)$。

接下来，我们枚举左侧前缀的最右端点 $l$，对于每个 $l$，我们可以通过二分查找，在 $nums[j..n-1]$ 中找到第一个大于等于 $nums[l]$ 的位置，记为 $r$，此时我们可以删除 $nums[l+1..r-1]$，并且更新答案 $ans = min(ans, r - l - 1)$。继续枚举 $l$，最终得到答案。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(1)$。其中 $n$ 为数组长度。

**方法二：双指针**

与方法一类似，我们先找出数组的最长非递减前缀和最长非递减后缀，分别记为 $nums[0..i]$ 和 $nums[j..n-1]$。

如果 $i \geq j$，说明数组本身就是非递减的，返回 $0$。

否则，我们可以选择删除右侧后缀，也可以选择删除左侧前缀，因此初始时答案为 $min(n - i - 1, j)$。

接下来，我们枚举左侧前缀的最右端点 $l$，对于每个 $l$，我们直接利用双指针找到第一个大于等于 $nums[l]$ 的位置，记为 $r$，此时我们可以删除 $nums[l+1..r-1]$，并且更新答案 $ans = min(ans, r - l - 1)$。继续枚举 $l$，最终得到答案。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为数组长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findLengthOfShortestSubarray(self, arr: List[int]) -> int:
        n = len(arr)
        i, j = 0, n - 1
        while i + 1 < n and arr[i] <= arr[i + 1]:
            i += 1
        while j - 1 >= 0 and arr[j - 1] <= arr[j]:
            j -= 1
        if i >= j:
            return 0
        ans = min(n - i - 1, j)
        for l in range(i + 1):
            r = bisect_left(arr, arr[l], lo=j)
            ans = min(ans, r - l - 1)
        return ans
```

```python
class Solution:
    def findLengthOfShortestSubarray(self, arr: List[int]) -> int:
        n = len(arr)
        i, j = 0, n - 1
        while i + 1 < n and arr[i] <= arr[i + 1]:
            i += 1
        while j - 1 >= 0 and arr[j - 1] <= arr[j]:
            j -= 1
        if i >= j:
            return 0
        ans = min(n - i - 1, j)
        r = j
        for l in range(i + 1):
            while r < n and arr[r] < arr[l]:
                r += 1
            ans = min(ans, r - l - 1)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int findLengthOfShortestSubarray(int[] arr) {
        int n = arr.length;
        int i = 0, j = n - 1;
        while (i + 1 < n && arr[i] <= arr[i + 1]) {
            ++i;
        }
        while (j - 1 >= 0 && arr[j - 1] <= arr[j]) {
            --j;
        }
        if (i >= j) {
            return 0;
        }
        int ans = Math.min(n - i - 1, j);
        for (int l = 0; l <= i; ++l) {
            int r = search(arr, arr[l], j);
            ans = Math.min(ans, r - l - 1);
        }
        return ans;
    }

    private int search(int[] arr, int x, int left) {
        int right = arr.length;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (arr[mid] >= x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
```

```java
class Solution {
    public int findLengthOfShortestSubarray(int[] arr) {
        int n = arr.length;
        int i = 0, j = n - 1;
        while (i + 1 < n && arr[i] <= arr[i + 1]) {
            ++i;
        }
        while (j - 1 >= 0 && arr[j - 1] <= arr[j]) {
            --j;
        }
        if (i >= j) {
            return 0;
        }
        int ans = Math.min(n - i - 1, j);
        for (int l = 0, r = j; l <= i; ++l) {
            while (r < n && arr[r] < arr[l]) {
                ++r;
            }
            ans = Math.min(ans, r - l - 1);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int findLengthOfShortestSubarray(vector<int>& arr) {
        int n = arr.size();
        int i = 0, j = n - 1;
        while (i + 1 < n && arr[i] <= arr[i + 1]) {
            ++i;
        }
        while (j - 1 >= 0 && arr[j - 1] <= arr[j]) {
            --j;
        }
        if (i >= j) {
            return 0;
        }
        int ans = min(n - 1 - i, j);
        for (int l = 0; l <= i; ++l) {
            int r = lower_bound(arr.begin() + j, arr.end(), arr[l]) - arr.begin();
            ans = min(ans, r - l - 1);
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    int findLengthOfShortestSubarray(vector<int>& arr) {
        int n = arr.size();
        int i = 0, j = n - 1;
        while (i + 1 < n && arr[i] <= arr[i + 1]) {
            ++i;
        }
        while (j - 1 >= 0 && arr[j - 1] <= arr[j]) {
            --j;
        }
        if (i >= j) {
            return 0;
        }
        int ans = min(n - 1 - i, j);
        for (int l = 0, r = j; l <= i; ++l) {
            while (r < n && arr[r] < arr[l]) {
                ++r;
            }
            ans = min(ans, r - l - 1);
        }
        return ans;
    }
};
```

### **Go**

```go
func findLengthOfShortestSubarray(arr []int) int {
	n := len(arr)
	i, j := 0, n-1
	for i+1 < n && arr[i] <= arr[i+1] {
		i++
	}
	for j-1 >= 0 && arr[j-1] <= arr[j] {
		j--
	}
	if i >= j {
		return 0
	}
	ans := min(n-i-1, j)
	for l := 0; l <= i; l++ {
		r := j + sort.SearchInts(arr[j:], arr[l])
		ans = min(ans, r-l-1)
	}
	return ans
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

```go
func findLengthOfShortestSubarray(arr []int) int {
	n := len(arr)
	i, j := 0, n-1
	for i+1 < n && arr[i] <= arr[i+1] {
		i++
	}
	for j-1 >= 0 && arr[j-1] <= arr[j] {
		j--
	}
	if i >= j {
		return 0
	}
	ans := min(n-i-1, j)
	r := j
	for l := 0; l <= i; l++ {
		for r < n && arr[r] < arr[l] {
			r += 1
		}
		ans = min(ans, r-l-1)
	}
	return ans
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
