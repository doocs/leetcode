---
comments: true
difficulty: Hard
tags:
    - Array
    - Binary Search
    - Divide and Conquer
---

<!-- problem:start -->

# [4. Median of Two Sorted Arrays](https://leetcode.com/problems/median-of-two-sorted-arrays)

[中文文档](/solution/0000-0099/0004.Median%20of%20Two%20Sorted%20Arrays/README.md)

## Mô tả

<!-- description:start -->

<p>Cho hai mảng đã sắp xếp <code>nums1</code> và <code>nums2</code> có kích thước lần lượt là <code>m</code> và <code>n</code>, hãy trả về <strong>trung vị</strong> của hai mảng đã sắp xếp.</p>

<p>Độ phức tạp thời gian chạy tổng thể phải là <code>O(log (m+n))</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong> nums1 = [1,3], nums2 = [2]
<strong>Đầu ra:</strong> 2.00000
<strong>Giải thích:</strong> mảng đã gộp = [1,2,3] và trung vị là 2.
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> nums1 = [1,2], nums2 = [3,4]
<strong>Đầu ra:</strong> 2.50000
<strong>Giải thích:</strong> mảng đã gộp = [1,2,3,4] và trung vị là (2 + 3) / 2 = 2.5.
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>nums1.length == m</code></li>
	<li><code>nums2.length == n</code></li>
	<li><code>0 &lt;= m &lt;= 1000</code></li>
	<li><code>0 &lt;= n &lt;= 1000</code></li>
	<li><code>1 &lt;= m + n &lt;= 2000</code></li>
	<li><code>-10<sup>6</sup> &lt;= nums1[i], nums2[i] &lt;= 10<sup>6</sup></code></li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Chia để trị

<!-- thinking:start -->

> **Tư duy**
>
> Gộp hai mảng đã sắp xếp rồi đọc trung vị là cách tiếp cận trực tiếp, với $O(m+n)$. Với $m,n \le 10^3$ thì cách này vẫn đủ để vượt qua, nhưng đề bài yêu cầu $O(\log(m+n))$, nên việc quét tuyến tính không phù hợp.
>
> Nút thắt nằm ở chỗ trung vị chỉ quan tâm đến một hoặc hai vị trí ở giữa sau khi gộp. Chỉ cần đếm các giá trị nhỏ hơn, không cần liệt kê chúng. Cả hai mảng đều đã sắp xếp, vì vậy việc so sánh phần tử thứ $\left\lfloor k/2 \right\rfloor$ ở mỗi phía cho biết nửa đầu nào không thể chứa phần tử nhỏ thứ $k$, từ đó có thể loại bỏ ngay nửa đó.
>
> Vì vậy, chúng ta không bao giờ xây dựng mảng đã gộp; thay vào đó, chúng ta tìm phần tử nhỏ thứ $k$ trong phần còn lại. Trung vị là trung bình của phần tử nhỏ thứ $\left\lfloor (m+n+1)/2 \right\rfloor$ và phần tử nhỏ thứ $\left\lfloor (m+n+2)/2 \right\rfloor$, bao quát độ dài lẻ và chẵn bằng cùng một đoạn mã. Nếu một phía có ít hơn $\left\lfloor k/2 \right\rfloor$ phần tử, hãy coi nó là $+\infty$ và loại bỏ phần tương ứng từ phía kia.

<!-- thinking:end -->

Đề bài yêu cầu độ phức tạp thời gian của thuật toán là $O(\log (m + n))$, vì vậy chúng ta không thể duyệt trực tiếp hai mảng mà cần sử dụng phương pháp tìm kiếm nhị phân.

Nếu $m + n$ là số lẻ, trung vị là số thứ $\left\lfloor\frac{m + n + 1}{2}\right\rfloor$; nếu $m + n$ là số chẵn, trung vị là trung bình của số thứ $\left\lfloor\frac{m + n + 1}{2}\right\rfloor$ và số thứ $\left\lfloor\frac{m + n + 2}{2}\right\rfloor$. Thực tế, chúng ta có thể thống nhất thành trung bình của số thứ $\left\lfloor\frac{m + n + 1}{2}\right\rfloor$ và số thứ $\left\lfloor\frac{m + n + 2}{2}\right\rfloor$.

Do đó, chúng ta có thể thiết kế một hàm $f(i, j, k)$, biểu diễn số nhỏ thứ $k$ trong khoảng $[i, m)$ của mảng $nums1$ và khoảng $[j, n)$ của mảng $nums2$. Trung vị là trung bình của $f(0, 0, \left\lfloor\frac{m + n + 1}{2}\right\rfloor)$ và $f(0, 0, \left\lfloor\frac{m + n + 2}{2}\right\rfloor)$.

Ý tưởng triển khai hàm $f(i, j, k)$ như sau:

- Nếu $i \geq m$, điều đó có nghĩa là khoảng $[i, m)$ của mảng $nums1$ rỗng, nên trả về trực tiếp $nums2[j + k - 1]$;
- Nếu $j \geq n$, điều đó có nghĩa là khoảng $[j, n)$ của mảng $nums2$ rỗng, nên trả về trực tiếp $nums1[i + k - 1]$;
- Nếu $k = 1$, điều đó có nghĩa là cần tìm số đầu tiên, nên chỉ cần trả về giá trị nhỏ hơn giữa $nums1[i]$ và $nums2[j]$;
- Nếu không, chúng ta tìm số thứ $\left\lfloor\frac{k}{2}\right\rfloor$ trong hai mảng, lần lượt ký hiệu là $x$ và $y$. (Lưu ý, nếu một mảng không có số thứ $\left\lfloor\frac{k}{2}\right\rfloor$, thì chúng ta coi số thứ $\left\lfloor\frac{k}{2}\right\rfloor$ là $+\infty$.) So sánh giá trị của $x$ và $y$:
    - Nếu $x \leq y$, điều đó có nghĩa là số thứ $\left\lfloor\frac{k}{2}\right\rfloor$ của mảng $nums1$ không thể là số nhỏ thứ $k$, nên chúng ta có thể loại bỏ khoảng $[i, i + \left\lfloor\frac{k}{2}\right\rfloor)$ của mảng $nums1$, rồi gọi đệ quy $f(i + \left\lfloor\frac{k}{2}\right\rfloor, j, k - \left\lfloor\frac{k}{2}\right\rfloor)$.
    - Nếu $x > y$, điều đó có nghĩa là số thứ $\left\lfloor\frac{k}{2}\right\rfloor$ của mảng $nums2$ không thể là số nhỏ thứ $k$, nên chúng ta có thể loại bỏ khoảng $[j, j + \left\lfloor\frac{k}{2}\right\rfloor)$ của mảng $nums2$, rồi gọi đệ quy $f(i, j + \left\lfloor\frac{k}{2}\right\rfloor, k - \left\lfloor\frac{k}{2}\right\rfloor)$.

Độ phức tạp thời gian là $O(\log(m + n))$, và độ phức tạp không gian là $O(\log(m + n))$. Trong đó, $m$ và $n$ lần lượt là độ dài của các mảng $nums1$ và $nums2$.

<!-- tabs:start -->

#### Python3

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

#### Java

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

#### C++

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

#### Go

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

#### TypeScript

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

#### JavaScript

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

#### C#

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

#### PHP

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

#### Nim

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

#### C

```c
int findKth(int* nums1, int m, int i, int* nums2, int n, int j, int k) {
    if (i >= m)
        return nums2[j + k - 1];
    if (j >= n)
        return nums1[i + k - 1];
    if (k == 1)
        return nums1[i] < nums2[j] ? nums1[i] : nums2[j];

    int p = k / 2;

    int x = (i + p - 1 < m) ? nums1[i + p - 1] : INT_MAX;
    int y = (j + p - 1 < n) ? nums2[j + p - 1] : INT_MAX;

    if (x < y)
        return findKth(nums1, m, i + p, nums2, n, j, k - p);
    else
        return findKth(nums1, m, i, nums2, n, j + p, k - p);
}

double findMedianSortedArrays(int* nums1, int m, int* nums2, int n) {
    int total = m + n;
    int a = findKth(nums1, m, 0, nums2, n, 0, (total + 1) / 2);
    int b = findKth(nums1, m, 0, nums2, n, 0, (total + 2) / 2);
    return (a + b) / 2.0;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
