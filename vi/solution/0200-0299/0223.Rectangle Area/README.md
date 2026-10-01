---
comments: true
difficulty: Medium
tags:
    - Geometry
    - Math
---

<!-- problem:start -->

# [223. Rectangle Area](https://leetcode.com/problems/rectangle-area)

[中文文档](/solution/0200-0299/0223.Rectangle%20Area/README.md)

## Mô tả

<!-- description:start -->

<p>Cho tọa độ của hai hình chữ nhật có các cạnh <strong>song song với trục tọa độ</strong> trong mặt phẳng 2D, hãy trả về <em>tổng diện tích mà hai hình chữ nhật bao phủ</em>.</p>

<p>Hình chữ nhật thứ nhất được xác định bởi góc <strong>dưới bên trái</strong> <code>(ax1, ay1)</code> và góc <strong>trên bên phải</strong> <code>(ax2, ay2)</code>.</p>

<p>Hình chữ nhật thứ hai được xác định bởi góc <strong>dưới bên trái</strong> <code>(bx1, by1)</code> và góc <strong>trên bên phải</strong> <code>(bx2, by2)</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>
<img alt="Rectangle Area" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0200-0299/0223.Rectangle%20Area/images/rectangle-plane.png" style="width: 700px; height: 365px;" />
<pre>
<strong>Đầu vào:</strong> ax1 = -3, ay1 = 0, ax2 = 3, ay2 = 4, bx1 = 0, by1 = -1, bx2 = 9, by2 = 2
<strong>Đầu ra:</strong> 45
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> ax1 = -2, ay1 = -2, ax2 = 2, ay2 = 2, bx1 = -2, by1 = -2, bx2 = 2, by2 = 2
<strong>Đầu ra:</strong> 16
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>-10<sup>4</sup> &lt;= ax1 &lt;= ax2 &lt;= 10<sup>4</sup></code></li>
	<li><code>-10<sup>4</sup> &lt;= ay1 &lt;= ay2 &lt;= 10<sup>4</sup></code></li>
	<li><code>-10<sup>4</sup> &lt;= bx1 &lt;= bx2 &lt;= 10<sup>4</sup></code></li>
	<li><code>-10<sup>4</sup> &lt;= by1 &lt;= by2 &lt;= 10<sup>4</sup></code></li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Tính diện tích phần giao nhau

<!-- thinking:start -->

> **Tư duy**
>
> Diện tích hợp là tổng diện tích của hai hình chữ nhật trừ đi phần giao nhau. Chiều rộng phần giao nhau là cạnh phải nhỏ hơn trừ cạnh trái lớn hơn (tương tự với chiều cao); giá trị âm nghĩa là không có phần giao nhau.
>
> Tính diện tích của cả hai hình chữ nhật, sau đó trừ $\max(\textit{width},0)\times\max(\textit{height},0)$.

<!-- thinking:end -->

Đầu tiên, chúng ta tính riêng diện tích của hai hình chữ nhật, lần lượt ký hiệu là $a$ và $b$. Sau đó, chúng ta tính chiều rộng $width$ và chiều cao $height$ của phần giao nhau. Diện tích phần giao nhau là $max(width, 0) \times max(height, 0)$. Cuối cùng, chúng ta trừ diện tích phần giao nhau khỏi $a$ và $b$.

Độ phức tạp thời gian là $O(1)$, và độ phức tạp không gian là $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def computeArea(
        self,
        ax1: int,
        ay1: int,
        ax2: int,
        ay2: int,
        bx1: int,
        by1: int,
        bx2: int,
        by2: int,
    ) -> int:
        a = (ax2 - ax1) * (ay2 - ay1)
        b = (bx2 - bx1) * (by2 - by1)
        width = min(ax2, bx2) - max(ax1, bx1)
        height = min(ay2, by2) - max(ay1, by1)
        return a + b - max(height, 0) * max(width, 0)
```

#### Java

```java
class Solution {
    public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        int a = (ax2 - ax1) * (ay2 - ay1);
        int b = (bx2 - bx1) * (by2 - by1);
        int width = Math.min(ax2, bx2) - Math.max(ax1, bx1);
        int height = Math.min(ay2, by2) - Math.max(ay1, by1);
        return a + b - Math.max(height, 0) * Math.max(width, 0);
    }
}
```

#### C++

```cpp
class Solution {
public:
    int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        int a = (ax2 - ax1) * (ay2 - ay1);
        int b = (bx2 - bx1) * (by2 - by1);
        int width = min(ax2, bx2) - max(ax1, bx1);
        int height = min(ay2, by2) - max(ay1, by1);
        return a + b - max(height, 0) * max(width, 0);
    }
};
```

#### Go

```go
func computeArea(ax1 int, ay1 int, ax2 int, ay2 int, bx1 int, by1 int, bx2 int, by2 int) int {
	a := (ax2 - ax1) * (ay2 - ay1)
	b := (bx2 - bx1) * (by2 - by1)
	width := min(ax2, bx2) - max(ax1, bx1)
	height := min(ay2, by2) - max(ay1, by1)
	return a + b - max(height, 0)*max(width, 0)
}
```

#### TypeScript

```ts
function computeArea(
    ax1: number,
    ay1: number,
    ax2: number,
    ay2: number,
    bx1: number,
    by1: number,
    bx2: number,
    by2: number,
): number {
    const a = (ax2 - ax1) * (ay2 - ay1);
    const b = (bx2 - bx1) * (by2 - by1);
    const width = Math.min(ax2, bx2) - Math.max(ax1, bx1);
    const height = Math.min(ay2, by2) - Math.max(ay1, by1);
    return a + b - Math.max(width, 0) * Math.max(height, 0);
}
```

#### C#

```cs
public class Solution {
    public int ComputeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        int a = (ax2 - ax1) * (ay2 - ay1);
        int b = (bx2 - bx1) * (by2 - by1);
        int width = Math.Min(ax2, bx2) - Math.Max(ax1, bx1);
        int height = Math.Min(ay2, by2) - Math.Max(ay1, by1);
        return a + b - Math.Max(height, 0) * Math.Max(width, 0);
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
