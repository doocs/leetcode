---
comments: true
difficulty: Hard
tags:
    - Stack
    - Array
    - Monotonic Stack
    - Range Query
---

<!-- problem:start -->

# [84. Largest Rectangle in Histogram](https://leetcode.com/problems/largest-rectangle-in-histogram)

[中文文档](/solution/0000-0099/0084.Largest%20Rectangle%20in%20Histogram/README.md)

## Mô tả

<!-- description:start -->

<p>Cho một mảng số nguyên <code>heights</code> biểu diễn chiều cao các cột của biểu đồ, trong đó chiều rộng của mỗi cột là <code>1</code>, hãy trả về <em>diện tích của hình chữ nhật lớn nhất trong biểu đồ</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0084.Largest%20Rectangle%20in%20Histogram/images/histogram.jpg" style="width: 522px; height: 242px;" />
<pre>
<strong>Đầu vào:</strong> heights = [2,1,5,6,2,3]
<strong>Đầu ra:</strong> 10
<strong>Giải thích:</strong> Hình trên là một biểu đồ mà chiều rộng của mỗi cột là 1.
Hình chữ nhật lớn nhất được đánh dấu bằng vùng màu đỏ, có diện tích = 10 đơn vị.
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0084.Largest%20Rectangle%20in%20Histogram/images/histogram-1.jpg" style="width: 202px; height: 362px;" />
<pre>
<strong>Đầu vào:</strong> heights = [2,4]
<strong>Đầu ra:</strong> 4
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= heights.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= heights[i] &lt;= 10<sup>4</sup></code></li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Ngăn xếp đơn điệu

<!-- thinking:start -->

> **Tư duy**
>
> Ý tưởng đầu tiên là liệt kê mọi khoảng $[l, r]$, lấy chiều cao nhỏ nhất, diện tích $(r - l + 1) \times \min$. Cách này đúng, nhưng có độ phức tạp $O(n^2)$. Với $n \le 10^5$, cách này sẽ hết thời gian.
>
> Nút thắt nằm ở việc tính lại giá trị nhỏ nhất trên mọi khoảng. Hãy đảo cách liệt kê: coi cột $i$ là cột thấp nhất của hình chữ nhật; khi đó chiều rộng là đoạn kéo dài tới các cột gần nhất có chiều cao nhỏ hơn nghiêm ngặt ở hai bên.
>
> “Phần tử nhỏ hơn gần nhất ở bên trái/phải” là mô hình ngăn xếp đơn điệu. Các chỉ số trong ngăn xếp có chiều cao tăng dần; một cột thấp hơn sẽ lấy các cột khác ra khỏi ngăn xếp và trở thành biên phải của chúng, trong khi phần tử đầu ngăn xếp mới trở thành biên trái. Mỗi cột được thêm và lấy ra đúng một lần, nên chỉ cần một lượt duyệt tuyến tính.

<!-- thinking:end -->

Chúng ta có thể liệt kê chiều cao $h$ của mỗi cột như là chiều cao của hình chữ nhật. Sử dụng một ngăn xếp đơn điệu, chúng ta tìm chỉ số $left_i$, $right_i$ của cột đầu tiên có chiều cao nhỏ hơn $h$ ở bên trái và bên phải. Diện tích của hình chữ nhật tại thời điểm này là $h \times (right_i-left_i-1)$. Chúng ta có thể tìm giá trị lớn nhất.

Độ phức tạp thời gian là $O(n)$, và độ phức tạp không gian là $O(n)$. Ở đây, $n$ là độ dài của $heights$.

Mô hình phổ biến của ngăn xếp đơn điệu: Tìm số **gần nhất** ở bên trái/phải của mỗi số **lớn hơn/nhỏ hơn** nó. Mẫu:

```python
stk = []
for i in range(n):
    while stk and check(stk[-1], i):
        stk.pop()
    stk.append(i)
```

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def largestRectangleArea(self, heights: List[int]) -> int:
        n = len(heights)
        stk = []
        left = [-1] * n
        right = [n] * n
        for i, h in enumerate(heights):
            while stk and heights[stk[-1]] >= h:
                right[stk[-1]] = i
                stk.pop()
            if stk:
                left[i] = stk[-1]
            stk.append(i)
        return max(h * (right[i] - left[i] - 1) for i, h in enumerate(heights))
```

#### Java

```java
class Solution {
    public int largestRectangleArea(int[] heights) {
        int res = 0, n = heights.length;
        Deque<Integer> stk = new ArrayDeque<>();
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(right, n);
        for (int i = 0; i < n; ++i) {
            while (!stk.isEmpty() && heights[stk.peek()] >= heights[i]) {
                right[stk.pop()] = i;
            }
            left[i] = stk.isEmpty() ? -1 : stk.peek();
            stk.push(i);
        }
        for (int i = 0; i < n; ++i) {
            res = Math.max(res, heights[i] * (right[i] - left[i] - 1));
        }
        return res;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int largestRectangleArea(vector<int>& heights) {
        int res = 0, n = heights.size();
        stack<int> stk;
        vector<int> left(n, -1);
        vector<int> right(n, n);
        for (int i = 0; i < n; ++i) {
            while (!stk.empty() && heights[stk.top()] >= heights[i]) {
                right[stk.top()] = i;
                stk.pop();
            }
            if (!stk.empty()) left[i] = stk.top();
            stk.push(i);
        }
        for (int i = 0; i < n; ++i)
            res = max(res, heights[i] * (right[i] - left[i] - 1));
        return res;
    }
};
```

#### Go

```go
func largestRectangleArea(heights []int) int {
	res, n := 0, len(heights)
	var stk []int
	left, right := make([]int, n), make([]int, n)
	for i := range right {
		right[i] = n
	}
	for i, h := range heights {
		for len(stk) > 0 && heights[stk[len(stk)-1]] >= h {
			right[stk[len(stk)-1]] = i
			stk = stk[:len(stk)-1]
		}
		if len(stk) > 0 {
			left[i] = stk[len(stk)-1]
		} else {
			left[i] = -1
		}
		stk = append(stk, i)
	}
	for i, h := range heights {
		res = max(res, h*(right[i]-left[i]-1))
	}
	return res
}
```

#### Rust

```rust
impl Solution {
    #[allow(dead_code)]
    pub fn largest_rectangle_area(heights: Vec<i32>) -> i32 {
        let n = heights.len();
        let mut left = vec![-1; n];
        let mut right = vec![-1; n];
        let mut stack: Vec<(usize, i32)> = Vec::new();
        let mut ret = -1;

        // Build left vector
        for (i, h) in heights.iter().enumerate() {
            while !stack.is_empty() && stack.last().unwrap().1 >= *h {
                stack.pop();
            }
            if stack.is_empty() {
                left[i] = -1;
            } else {
                left[i] = stack.last().unwrap().0 as i32;
            }
            stack.push((i, *h));
        }

        stack.clear();

        // Build right vector
        for (i, h) in heights.iter().enumerate().rev() {
            while !stack.is_empty() && stack.last().unwrap().1 >= *h {
                stack.pop();
            }
            if stack.is_empty() {
                right[i] = n as i32;
            } else {
                right[i] = stack.last().unwrap().0 as i32;
            }
            stack.push((i, *h));
        }

        // Calculate the max area
        for (i, h) in heights.iter().enumerate() {
            ret = std::cmp::max(ret, (right[i] - left[i] - 1) * *h);
        }

        ret
    }
}
```

#### C#

```cs
public class Solution {
    public int LargestRectangleArea(int[] height) {
        var stack = new Stack<int>();
        var result = 0;
        var i = 0;
        while (i < height.Length || stack.Any()) {
            if (!stack.Any() || (i < height.Length && height[stack.Peek()] < height[i])) {
                stack.Push(i);
                ++i;
            }
            else {
                var previousIndex = stack.Pop();
                var area = height[previousIndex] * (stack.Any() ? (i - stack.Peek() - 1) : i);
                result = Math.Max(result, area);
            }
        }

        return result;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
