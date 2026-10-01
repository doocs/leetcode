---
comments: true
difficulty: Medium
tags:
    - Greedy
    - Array
    - Two Pointers
---

<!-- problem:start -->

# [11. Container With Most Water](https://leetcode.com/problems/container-with-most-water)

[中文文档](/solution/0000-0099/0011.Container%20With%20Most%20Water/README.md)

## Mô tả

<!-- description:start -->

<p>Bạn được cung cấp một mảng số nguyên <code>height</code> có độ dài <code>n</code>. Có <code>n</code> đường thẳng đứng được vẽ sao cho hai đầu mút của đường thứ <code>i<sup>th</sup></code> là <code>(i, 0)</code> và <code>(i, height[i])</code>.</p>

<p>Hãy tìm hai đường thẳng cùng với trục x tạo thành một container sao cho container đó chứa được nhiều nước nhất.</p>

<p>Trả về <em>lượng nước tối đa mà một container có thể chứa</em>.</p>

<p><strong>Lưu ý</strong> rằng bạn không được làm nghiêng container.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0011.Container%20With%20Most%20Water/images/question_11.jpg" style="width: 600px; height: 287px;" />
<pre>
<strong>Đầu vào:</strong> height = [1,8,6,2,5,4,8,3,7]
<strong>Đầu ra:</strong> 49
<strong>Giải thích:</strong> Các đường thẳng đứng ở trên được biểu diễn bởi mảng [1,8,6,2,5,4,8,3,7]. Trong trường hợp này, diện tích nước tối đa (phần màu xanh) mà container có thể chứa là 49.
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> height = [1,1]
<strong>Đầu ra:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>n == height.length</code></li>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= height[i] &lt;= 10<sup>4</sup></code></li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Hai con trỏ

<!-- thinking:start -->

> **Tư duy**
>
> Ý tưởng đầu tiên là thử mọi cặp $(i,j)$ và lấy $\min(height[i],height[j])\times(j-i)$. Đúng, nhưng có độ phức tạp $O(n^2)$. Với $n \le 10^5$, cách này sẽ bị quá thời gian.
>
> Điểm nghẽn nằm ở việc xem mỗi cặp một cách độc lập và bỏ qua rằng chiều cao được quyết định bởi đường ngắn hơn. Hãy bắt đầu với khoảng rộng nhất $[0,n-1]$: khi đưa đầu cao hơn vào trong, độ rộng giảm trong khi chiều cao vẫn bị giới hạn bởi đường ngắn hơn, nên diện tích không thể tăng. Chúng ta phải bỏ đường ngắn hơn và tìm một đường cao hơn.
>
> Vì vậy, chúng ta thu hẹp từ cả hai đầu, luôn di chuyển phía ngắn hơn. Mỗi chỉ số được duyệt nhiều nhất một lần.

<!-- thinking:end -->

Chúng ta sử dụng hai con trỏ $l$ và $r$ lần lượt trỏ đến hai đầu trái và phải của mảng, tức là $l = 0$ và $r = n - 1$, trong đó $n$ là độ dài của mảng.

Tiếp theo, chúng ta sử dụng một biến $\textit{ans}$ để ghi nhận sức chứa lớn nhất của container, ban đầu được đặt bằng $0$.

Sau đó, chúng ta bắt đầu một vòng lặp. Trong mỗi lần lặp, chúng ta tính sức chứa hiện tại của container, tức là $\textit{min}(height[l], height[r]) \times (r - l)$, rồi so sánh với $\textit{ans}$ và gán giá trị lớn hơn cho $\textit{ans}$. Tiếp theo, chúng ta so sánh các giá trị của $height[l]$ và $height[r]$. Nếu $\textit{height}[l] < \textit{height}[r]$, việc di chuyển con trỏ $r$ sẽ không cải thiện kết quả vì chiều cao của container được quyết định bởi đường thẳng đứng ngắn hơn, nên chúng ta di chuyển con trỏ $l$. Ngược lại, chúng ta di chuyển con trỏ $r$.

Sau vòng lặp, chúng ta trả về $\textit{ans}$.

Độ phức tạp thời gian là $O(n)$, trong đó $n$ là độ dài của mảng $\textit{height}$. Độ phức tạp không gian là $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxArea(self, height: List[int]) -> int:
        l, r = 0, len(height) - 1
        ans = 0
        while l < r:
            t = min(height[l], height[r]) * (r - l)
            ans = max(ans, t)
            if height[l] < height[r]:
                l += 1
            else:
                r -= 1
        return ans
```

#### Java

```java
class Solution {
    public int maxArea(int[] height) {
        int l = 0, r = height.length - 1;
        int ans = 0;
        while (l < r) {
            int t = Math.min(height[l], height[r]) * (r - l);
            ans = Math.max(ans, t);
            if (height[l] < height[r]) {
                ++l;
            } else {
                --r;
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxArea(vector<int>& height) {
        int l = 0, r = height.size() - 1;
        int ans = 0;
        while (l < r) {
            int t = min(height[l], height[r]) * (r - l);
            ans = max(ans, t);
            if (height[l] < height[r]) {
                ++l;
            } else {
                --r;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func maxArea(height []int) (ans int) {
	l, r := 0, len(height)-1
	for l < r {
		t := min(height[l], height[r]) * (r - l)
		ans = max(ans, t)
		if height[l] < height[r] {
			l++
		} else {
			r--
		}
	}
	return
}
```

#### TypeScript

```ts
function maxArea(height: number[]): number {
    let [l, r] = [0, height.length - 1];
    let ans = 0;
    while (l < r) {
        const t = Math.min(height[l], height[r]) * (r - l);
        ans = Math.max(ans, t);
        if (height[l] < height[r]) {
            ++l;
        } else {
            --r;
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn max_area(height: Vec<i32>) -> i32 {
        let mut l = 0;
        let mut r = height.len() - 1;
        let mut ans = 0;
        while l < r {
            ans = ans.max(height[l].min(height[r]) * ((r - l) as i32));
            if height[l] < height[r] {
                l += 1;
            } else {
                r -= 1;
            }
        }
        ans
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} height
 * @return {number}
 */
var maxArea = function (height) {
    let [l, r] = [0, height.length - 1];
    let ans = 0;
    while (l < r) {
        const t = Math.min(height[l], height[r]) * (r - l);
        ans = Math.max(ans, t);
        if (height[l] < height[r]) {
            ++l;
        } else {
            --r;
        }
    }
    return ans;
};
```

#### C#

```cs
public class Solution {
    public int MaxArea(int[] height) {
        int l = 0, r = height.Length - 1;
        int ans = 0;
        while (l < r) {
            int t = Math.Min(height[l], height[r]) * (r - l);
            ans = Math.Max(ans, t);
            if (height[l] < height[r]) {
                ++l;
            } else {
                --r;
            }
        }
        return ans;
    }
}
```

#### PHP

```php
class Solution {
    /**
     * @param Integer[] $height
     * @return Integer
     */
    function maxArea($height) {
        $l = 0;
        $r = count($height) - 1;
        $ans = 0;
        while ($l < $r) {
            $t = min($height[$l], $height[$r]) * ($r - $l);
            $ans = max($ans, $t);
            if ($height[$l] < $height[$r]) {
                ++$l;
            } else {
                --$r;
            }
        }
        return $ans;
    }
}
```

#### C

```c
int min(int a, int b) {
    return a < b ? a : b;
}

int max(int a, int b) {
    return a > b ? a : b;
}

int maxArea(int* height, int heightSize) {
    int l = 0, r = heightSize - 1;
    int ans = 0;
    while (l < r) {
        int t = min(height[l], height[r]) * (r - l);
        ans = max(ans, t);
        if (height[l] < height[r]) {
            ++l;
        } else {
            --r;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
