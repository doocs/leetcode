# [4. 寻找两个正序数组的中位数](https://leetcode.cn/problems/median-of-two-sorted-arrays)

[English Version](/solution/0000-0099/0004.Median%20of%20Two%20Sorted%20Arrays/README_EN.md)

<!-- tags:数组,二分查找,分治 -->

<!-- difficulty:困难 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给定两个大小分别为 <code>m</code> 和 <code>n</code> 的正序（从小到大）数组&nbsp;<code>nums1</code> 和&nbsp;<code>nums2</code>。请你找出并返回这两个正序数组的 <strong>中位数</strong> 。</p>

<p>算法的时间复杂度应该为 <code>O(log (m+n))</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums1 = [1,3], nums2 = [2]
<strong>输出：</strong>2.00000
<strong>解释：</strong>合并数组 = [1,2,3] ，中位数 2
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums1 = [1,2], nums2 = [3,4]
<strong>输出：</strong>2.50000
<strong>解释：</strong>合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
</pre>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>nums1.length == m</code></li>
	<li><code>nums2.length == n</code></li>
	<li><code>0 &lt;= m &lt;= 1000</code></li>
	<li><code>0 &lt;= n &lt;= 1000</code></li>
	<li><code>1 &lt;= m + n &lt;= 2000</code></li>
	<li><code>-10<sup>6</sup> &lt;= nums1[i], nums2[i] &lt;= 10<sup>6</sup></code></li>
</ul>

## 解法

### 方法一：分治

题目要求算法的时间复杂度为 $O(\log (m + n))$，因此不能直接遍历两个数组，而是需要使用二分查找的方法。

如果 $m + n$ 是奇数，那么中位数就是第 $\left\lfloor\frac{m + n + 1}{2}\right\rfloor$ 个数；如果 $m + n$ 是偶数，那么中位数就是第 $\left\lfloor\frac{m + n + 1}{2}\right\rfloor$ 和第 $\left\lfloor\frac{m + n + 2}{2}\right\rfloor$ 个数的平均数。实际上，我们可以统一为求第 $\left\lfloor\frac{m + n + 1}{2}\right\rfloor$ 个数和第 $\left\lfloor\frac{m + n + 2}{2}\right\rfloor$ 个数的平均数。

因此，我们可以设计一个函数 $f(i, j, k)$，表示在数组 $nums1$ 的区间 $[i, m)$ 和数组 $nums2$ 的区间 $[j, n)$ 中，求第 $k$ 小的数。那么中位数就是 $f(0, 0, \left\lfloor\frac{m + n + 1}{2}\right\rfloor)$ 和 $f(0, 0, \left\lfloor\frac{m + n + 2}{2}\right\rfloor)$ 的平均数。

函数 $f(i, j, k)$ 的实现思路如下：

-   如果 $i \geq m$，说明数组 $nums1$ 的区间 $[i, m)$ 为空，因此直接返回 $nums2[j + k - 1]$；
-   如果 $j \geq n$，说明数组 $nums2$ 的区间 $[j, n)$ 为空，因此直接返回 $nums1[i + k - 1]$；
-   如果 $k = 1$，说明要找第一个数，因此只需要返回 $nums1[i]$ 和 $nums2[j]$ 中的最小值；
-   否则，我们分别在两个数组中查找第 $\left\lfloor\frac{k}{2}\right\rfloor$ 个数，设为 $x$ 和 $y$。（注意，如果某个数组不存在第 $\left\lfloor\frac{k}{2}\right\rfloor$ 个数，那么我们将第 $\left\lfloor\frac{k}{2}\right\rfloor$ 个数视为 $+\infty$。）比较 $x$ 和 $y$ 的大小：
    -   如果 $x \leq y$，则说明数组 $nums1$ 的第 $\left\lfloor\frac{k}{2}\right\rfloor$ 个数不可能是第 $k$ 小的数，因此我们可以排除数组 $nums1$ 的区间 $[i, i + \left\lfloor\frac{k}{2}\right\rfloor)$，递归调用 $f(i + \left\lfloor\frac{k}{2}\right\rfloor, j, k - \left\lfloor\frac{k}{2}\right\rfloor)$。
    -   如果 $x > y$，则说明数组 $nums2$ 的第 $\left\lfloor\frac{k}{2}\right\rfloor$ 个数不可能是第 $k$ 小的数，因此我们可以排除数组 $nums2$ 的区间 $[j, j + \left\lfloor\frac{k}{2}\right\rfloor)$，递归调用 $f(i, j + \left\lfloor\frac{k}{2}\right\rfloor, k - \left\lfloor\frac{k}{2}\right\rfloor)$。

时间复杂度 $O(\log(m + n))$，空间复杂度 $O(\log(m + n))$。其中 $m$ 和 $n$ 分别是数组 $nums1$ 和 $nums2$ 的长度。

<!-- tabs:start -->

```python
class Solution:
    def findMedianSortedArrays(self, nums1: List[int], nums2: List[int]) -> float:
        def f(i: int, j: int, k: int) -> int:
            if i >= m:
                return nums2[j + k - 1]
            if j >= n:
                return nums1[i + k - 1]
            if k == 1:
                return min(nums1[i], nums2[j])
            p = k // 2
            x = nums1[i + p - 1] if i + p - 1 < m else inf
            y = nums2[j + p - 1] if j + p - 1 < n else inf
            return f(i + p, j, k - p) if x < y else f(i, j + p, k - p)

        m, n = len(nums1), len(nums2)
        a = f(0, 0, (m + n + 1) // 2)
        b = f(0, 0, (m + n + 2) // 2)
        return (a + b) / 2
```

```java
class Solution {
    private int m;
    private int n;
    private int[] nums1;
    private int[] nums2;

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        m = nums1.length;
        n = nums2.length;
        this.nums1 = nums1;
        this.nums2 = nums2;
        int a = f(0, 0, (m + n + 1) / 2);
        int b = f(0, 0, (m + n + 2) / 2);
        return (a + b) / 2.0;
    }

    private int f(int i, int j, int k) {
        if (i >= m) {
            return nums2[j + k - 1];
        }
        if (j >= n) {
            return nums1[i + k - 1];
        }
        if (k == 1) {
            return Math.min(nums1[i], nums2[j]);
        }
        int p = k / 2;
        int x = i + p - 1 < m ? nums1[i + p - 1] : 1 << 30;
        int y = j + p - 1 < n ? nums2[j + p - 1] : 1 << 30;
        return x < y ? f(i + p, j, k - p) : f(i, j + p, k - p);
    }
}
```

```cpp
class Solution {
public:
    double findMedianSortedArrays(vector<int>& nums1, vector<int>& nums2) {
        int m = nums1.size(), n = nums2.size();
        function<int(int, int, int)> f = [&](int i, int j, int k) {
            if (i >= m) {
                return nums2[j + k - 1];
            }
            if (j >= n) {
                return nums1[i + k - 1];
            }
            if (k == 1) {
                return min(nums1[i], nums2[j]);
            }
            int p = k / 2;
            int x = i + p - 1 < m ? nums1[i + p - 1] : 1 << 30;
            int y = j + p - 1 < n ? nums2[j + p - 1] : 1 << 30;
            return x < y ? f(i + p, j, k - p) : f(i, j + p, k - p);
        };
        int a = f(0, 0, (m + n + 1) / 2);
        int b = f(0, 0, (m + n + 2) / 2);
        return (a + b) / 2.0;
    }
};
```

```go
func findMedianSortedArrays(nums1 []int, nums2 []int) float64 {
	m, n := len(nums1), len(nums2)
	var f func(i, j, k int) int
	f = func(i, j, k int) int {
		if i >= m {
			return nums2[j+k-1]
		}
		if j >= n {
			return nums1[i+k-1]
		}
		if k == 1 {
			return min(nums1[i], nums2[j])
		}
		p := k / 2
		x, y := 1<<30, 1<<30
		if ni := i + p - 1; ni < m {
			x = nums1[ni]
		}
		if nj := j + p - 1; nj < n {
			y = nums2[nj]
		}
		if x < y {
			return f(i+p, j, k-p)
		}
		return f(i, j+p, k-p)
	}
	a, b := f(0, 0, (m+n+1)/2), f(0, 0, (m+n+2)/2)
	return float64(a+b) / 2.0
}
```

```ts
function findMedianSortedArrays(nums1: number[], nums2: number[]): number {
    const m = nums1.length;
    const n = nums2.length;
    const f = (i: number, j: number, k: number): number => {
        if (i >= m) {
            return nums2[j + k - 1];
        }
        if (j >= n) {
            return nums1[i + k - 1];
        }
        if (k == 1) {
            return Math.min(nums1[i], nums2[j]);
        }
        const p = Math.floor(k / 2);
        const x = i + p - 1 < m ? nums1[i + p - 1] : 1 << 30;
        const y = j + p - 1 < n ? nums2[j + p - 1] : 1 << 30;
        return x < y ? f(i + p, j, k - p) : f(i, j + p, k - p);
    };
    const a = f(0, 0, Math.floor((m + n + 1) / 2));
    const b = f(0, 0, Math.floor((m + n + 2) / 2));
    return (a + b) / 2;
}
```

```js
/**
 * @param {number[]} nums1
 * @param {number[]} nums2
 * @return {number}
 */
var findMedianSortedArrays = function (nums1, nums2) {
    const m = nums1.length;
    const n = nums2.length;
    const f = (i, j, k) => {
        if (i >= m) {
            return nums2[j + k - 1];
        }
        if (j >= n) {
            return nums1[i + k - 1];
        }
        if (k == 1) {
            return Math.min(nums1[i], nums2[j]);
        }
        const p = Math.floor(k / 2);
        const x = i + p - 1 < m ? nums1[i + p - 1] : 1 << 30;
        const y = j + p - 1 < n ? nums2[j + p - 1] : 1 << 30;
        return x < y ? f(i + p, j, k - p) : f(i, j + p, k - p);
    };
    const a = f(0, 0, Math.floor((m + n + 1) / 2));
    const b = f(0, 0, Math.floor((m + n + 2) / 2));
    return (a + b) / 2;
};
```

```cs
public class Solution {
    private int m;
    private int n;
    private int[] nums1;
    private int[] nums2;

    public double FindMedianSortedArrays(int[] nums1, int[] nums2) {
        m = nums1.Length;
        n = nums2.Length;
        this.nums1 = nums1;
        this.nums2 = nums2;
        int a = f(0, 0, (m + n + 1) / 2);
        int b = f(0, 0, (m + n + 2) / 2);
        return (a + b) / 2.0;
    }

    private int f(int i, int j, int k) {
        if (i >= m) {
            return nums2[j + k - 1];
        }
        if (j >= n) {
            return nums1[i + k - 1];
        }
        if (k == 1) {
            return Math.Min(nums1[i], nums2[j]);
        }
        int p = k / 2;
        int x = i + p - 1 < m ? nums1[i + p - 1] : 1 << 30;
        int y = j + p - 1 < n ? nums2[j + p - 1] : 1 << 30;
        return x < y ? f(i + p, j, k - p) : f(i, j + p, k - p);
    }
}
```

```php
class Solution {
    /**
     * @param int[] $nums1
     * @param int[] $nums2
     * @return float
     */

    function findMedianSortedArrays($nums1, $nums2) {
        $arr = array_merge($nums1, $nums2);
        sort($arr);
        $cnt_arr = count($arr);

        if ($cnt_arr % 2) {
            return $arr[$cnt_arr / 2];
        } else {
            return ($arr[intdiv($cnt_arr, 2) - 1] + $arr[intdiv($cnt_arr, 2)]) / 2;
        }
    }
}
```

```nim
import std/[algorithm, sequtils]

proc medianOfTwoSortedArrays(nums1: seq[int], nums2: seq[int]): float =
  var
    fullList: seq[int] = concat(nums1, nums2)
    value: int = fullList.len div 2

  fullList.sort()

  if fullList.len mod 2 == 0:
    result = (fullList[value - 1] + fullList[value]) / 2
  else:
    result = fullList[value].toFloat()

# Driver Code

# var
#   arrA: seq[int] = @[1, 2]
#   arrB: seq[int] = @[3, 4, 5]
# echo medianOfTwoSortedArrays(arrA, arrB)
```

<!-- tabs:end -->

<!-- end -->
