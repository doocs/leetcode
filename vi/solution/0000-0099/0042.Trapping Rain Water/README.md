---
comments: true
difficulty: Hard
tags:
    - Stack
    - Array
    - Two Pointers
    - Dynamic Programming
    - Monotonic Stack
---

<!-- problem:start -->

# [42. Trapping Rain Water](https://leetcode.com/problems/trapping-rain-water)

[中文文档](/solution/0000-0099/0042.Trapping%20Rain%20Water/README.md)

## Mô tả

<!-- description:start -->

<p>Cho <code>n</code> số nguyên không âm biểu diễn một bản đồ độ cao, trong đó độ rộng của mỗi cột là <code>1</code>, hãy tính lượng nước mà bản đồ có thể giữ lại sau khi mưa.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0042.Trapping%20Rain%20Water/images/rainwatertrap.png" style="width: 412px; height: 161px;" />
<pre>
<strong>Đầu vào:</strong> height = [0,1,0,2,1,0,1,3,2,1,2,1]
<strong>Đầu ra:</strong> 6
<strong>Giải thích:</strong> Bản đồ độ cao ở trên (phần màu đen) được biểu diễn bằng mảng [0,1,0,2,1,0,1,3,2,1,2,1]. Trong trường hợp này, 6 đơn vị nước mưa (phần màu xanh) được giữ lại.
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> height = [4,2,0,3,2,5]
<strong>Đầu ra:</strong> 9
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>n == height.length</code></li>
	<li><code>1 &lt;= n &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= height[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Quy hoạch động

<!-- thinking:start -->

> **Tư duy**
>
> Ý tưởng đầu tiên là, với mỗi $i$, quét sang trái và phải để tìm các cột cao nhất; lượng nước là $\min(\textit{leftMax}, \textit{rightMax}) - \textit{height}[i]$. Cách này đúng, nhưng có độ phức tạp $O(n^2)$. Với $n \le 2 \times 10^4$, nó có thể bị quá thời gian.
>
> Nút thắt là phải tính lại giá trị lớn nhất bên trái/bên phải tại mỗi $i$. Các giá trị lớn nhất đó chỉ phụ thuộc vào một phía và có thể được tính trước trong thời gian tuyến tính.
>
> Tại mỗi chỉ số, lưu cột cao nhất ở bên trái (tính cả vị trí đó) và ở bên phải (tính cả vị trí đó), sau đó tính tổng. Mực nước là chiều cao nhỏ hơn trong hai vách — đó là trạng thái DP.

<!-- thinking:end -->

Chúng ta định nghĩa $left[i]$ là chiều cao của cột cao nhất ở bên trái vị trí có chỉ số $i$ và tính cả vị trí đó, còn $right[i]$ là chiều cao của cột cao nhất ở bên phải vị trí có chỉ số $i$ và tính cả vị trí đó. Do đó, lượng nước mưa có thể giữ lại tại vị trí $i$ là $min(left[i], right[i]) - height[i]$. Chúng ta duyệt mảng để tính $left[i]$ và $right[i]$, và đáp án cuối cùng là $\sum_{i=0}^{n-1} \min(left[i], right[i]) - height[i]$.

Độ phức tạp thời gian là $O(n)$, còn độ phức tạp không gian là $O(n)$. Trong đó, $n$ là độ dài của mảng.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def trap(self, height: List[int]) -> int:
        n = len(height)
        left = [height[0]] * n
        right = [height[-1]] * n
        for i in range(1, n):
            left[i] = max(left[i - 1], height[i])
            right[n - i - 1] = max(right[n - i], height[n - i - 1])
        return sum(min(l, r) - h for l, r, h in zip(left, right, height))
```

#### Java

```java
class Solution {
    public int trap(int[] height) {
        int n = height.length;
        int[] left = new int[n];
        int[] right = new int[n];
        left[0] = height[0];
        right[n - 1] = height[n - 1];
        for (int i = 1; i < n; ++i) {
            left[i] = Math.max(left[i - 1], height[i]);
            right[n - i - 1] = Math.max(right[n - i], height[n - i - 1]);
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans += Math.min(left[i], right[i]) - height[i];
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int trap(vector<int>& height) {
        int n = height.size();
        int left[n], right[n];
        left[0] = height[0];
        right[n - 1] = height[n - 1];
        for (int i = 1; i < n; ++i) {
            left[i] = max(left[i - 1], height[i]);
            right[n - i - 1] = max(right[n - i], height[n - i - 1]);
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans += min(left[i], right[i]) - height[i];
        }
        return ans;
    }
};
```

#### Go

```go
func trap(height []int) (ans int) {
	n := len(height)
	left := make([]int, n)
	right := make([]int, n)
	left[0], right[n-1] = height[0], height[n-1]
	for i := 1; i < n; i++ {
		left[i] = max(left[i-1], height[i])
		right[n-i-1] = max(right[n-i], height[n-i-1])
	}
	for i, h := range height {
		ans += min(left[i], right[i]) - h
	}
	return
}
```

#### TypeScript

```ts
function trap(height: number[]): number {
    const n = height.length;
    const left: number[] = new Array(n).fill(height[0]);
    const right: number[] = new Array(n).fill(height[n - 1]);
    for (let i = 1; i < n; ++i) {
        left[i] = Math.max(left[i - 1], height[i]);
        right[n - i - 1] = Math.max(right[n - i], height[n - i - 1]);
    }
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        ans += Math.min(left[i], right[i]) - height[i];
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    #[allow(dead_code)]
    pub fn trap(height: Vec<i32>) -> i32 {
        let n = height.len();
        let mut left: Vec<i32> = vec![0; n];
        let mut right: Vec<i32> = vec![0; n];

        left[0] = height[0];
        right[n - 1] = height[n - 1];

        // Initialize the left & right vector
        for i in 1..n {
            left[i] = std::cmp::max(left[i - 1], height[i]);
            right[n - i - 1] = std::cmp::max(right[n - i], height[n - i - 1]);
        }

        let mut ans = 0;

        // Calculate the ans
        for i in 0..n {
            ans += std::cmp::min(left[i], right[i]) - height[i];
        }

        ans
    }
}
```

#### C#

```cs
public class Solution {
    public int Trap(int[] height) {
        int n = height.Length;
        int[] left = new int[n];
        int[] right = new int[n];
        left[0] = height[0];
        right[n - 1] = height[n - 1];
        for (int i = 1; i < n; ++i) {
            left[i] = Math.Max(left[i - 1], height[i]);
            right[n - i - 1] = Math.Max(right[n - i], height[n - i - 1]);
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans += Math.Min(left[i], right[i]) - height[i];
        }
        return ans;
    }
}
```

#### PHP

```php
class Solution {
    /**
     * @param integer[] $height
     * @return integer
     */

    function trap($height) {
        $n = count($height);

        if ($n == 0) {
            return 0;
        }

        $left = 0;
        $right = $n - 1;
        $leftMax = 0;
        $rightMax = 0;
        $ans = 0;

        while ($left < $right) {
            if ($height[$left] < $height[$right]) {
                if ($height[$left] > $leftMax) {
                    $leftMax = $height[$left];
                } else {
                    $ans += $leftMax - $height[$left];
                }
                $left++;
            } else {
                if ($height[$right] > $rightMax) {
                    $rightMax = $height[$right];
                } else {
                    $ans += $rightMax - $height[$right];
                }
                $right--;
            }
        }
        return $ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
