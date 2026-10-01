---
comments: true
difficulty: Hard
tags:
    - Stack
    - Array
    - Dynamic Programming
    - Matrix
    - Monotonic Stack
---

<!-- problem:start -->

# [85. Maximal Rectangle](https://leetcode.com/problems/maximal-rectangle)

[中文文档](/solution/0000-0099/0085.Maximal%20Rectangle/README.md)

## Mô tả

<!-- description:start -->

<p>Cho một <code>rows x cols</code> <code>matrix</code> nhị phân chứa các giá trị <code>0</code> và <code>1</code>, hãy tìm hình chữ nhật lớn nhất chỉ chứa các giá trị <code>1</code> và trả về <em>diện tích của nó</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0085.Maximal%20Rectangle/images/maximal.jpg" style="width: 402px; height: 322px;" />
<pre>
<strong>Đầu vào:</strong> matrix = [[&quot;1&quot;,&quot;0&quot;,&quot;1&quot;,&quot;0&quot;,&quot;0&quot;],[&quot;1&quot;,&quot;0&quot;,&quot;1&quot;,&quot;1&quot;,&quot;1&quot;],[&quot;1&quot;,&quot;1&quot;,&quot;1&quot;,&quot;1&quot;,&quot;1&quot;],[&quot;1&quot;,&quot;0&quot;,&quot;0&quot;,&quot;1&quot;,&quot;0&quot;]]
<strong>Đầu ra:</strong> 6
<strong>Giải thích:</strong> Hình chữ nhật lớn nhất được thể hiện trong hình trên.
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> matrix = [[&quot;0&quot;]]
<strong>Đầu ra:</strong> 0
</pre>

<p><strong class="example">Ví dụ 3:</strong></p>

<pre>
<strong>Đầu vào:</strong> matrix = [[&quot;1&quot;]]
<strong>Đầu ra:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>rows == matrix.length</code></li>
	<li><code>cols == matrix[i].length</code></li>
	<li><code>1 &lt;= rows, cols &lt;= 200</code></li>
	<li><code>matrix[i][j]</code> là <code>&#39;0&#39;</code> hoặc <code>&#39;1&#39;</code>.</li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Ngăn xếp đơn điệu

<!-- thinking:start -->

> **Tư duy**
>
> Ý tưởng đầu tiên là chọn hai ô làm hai góc đối diện và kiểm tra xem hình chữ nhật có toàn là các số $1$ hay không. Cách này đúng, nhưng có độ phức tạp $O(m^2 n^2)$. Với $m, n \le 200$, cách này sẽ vượt quá thời gian.
>
> Nút thắt nằm ở việc kiểm tra lại các vùng 2D. Nhận xét: số lượng các số $1$ liên tiếp tính từ hàng $i$ đi lên, tại cột $j$, chính là một thanh của histogram. Mỗi hàng trở thành bài toán “hình chữ nhật lớn nhất trong histogram.”
>
> Đối với mỗi hàng, duy trì $\textit{heights}[j]$: tăng khi gặp $1$, đặt lại khi gặp $0$, sau đó chạy ngăn xếp đơn điệu. Tổng thời gian là $O(mn)$.

<!-- thinking:end -->

Chúng ta có thể coi mỗi hàng là đáy của một histogram và tính diện tích lớn nhất của histogram cho mỗi hàng.

Cụ thể, chúng ta duy trì một mảng $\textit{heights}$ có cùng độ dài với số cột của ma trận, trong đó $\textit{heights}[j]$ biểu diễn chiều cao của cột tại vị trí thứ $j$ với hàng hiện tại làm đáy. Với mỗi hàng, chúng ta duyệt qua từng cột:

- Nếu phần tử hiện tại là '1', tăng $\textit{heights}[j]$ lên $1$.
- Nếu phần tử hiện tại là '0', đặt $\textit{heights}[j]$ về $0$.

Sau đó, chúng ta sử dụng thuật toán ngăn xếp đơn điệu để tính diện tích hình chữ nhật lớn nhất của histogram hiện tại và cập nhật đáp án.

Các bước cụ thể của ngăn xếp đơn điệu như sau:

1. Khởi tạo một ngăn xếp rỗng $\textit{stk}$ để lưu các chỉ số của các cột.
2. Khởi tạo hai mảng $\textit{left}$ và $\textit{right}$, lần lượt biểu diễn chỉ số của cột đầu tiên ở bên trái và bên phải của mỗi cột có chiều cao nhỏ hơn cột hiện tại.
3. Duyệt qua mảng $\textit{heights}$, trước tiên tính chỉ số của cột đầu tiên ở bên trái có chiều cao nhỏ hơn mỗi cột và lưu chỉ số đó vào $\textit{left}$.
4. Sau đó duyệt ngược mảng $\textit{heights}$, tính chỉ số của cột đầu tiên ở bên phải có chiều cao nhỏ hơn mỗi cột và lưu chỉ số đó vào $\textit{right}$.
5. Cuối cùng, tính diện tích hình chữ nhật lớn nhất cho mỗi cột và cập nhật đáp án.

Độ phức tạp thời gian là $O(m \times n)$, trong đó $m$ là số hàng trong $matrix$ và $n$ là số cột trong $matrix$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximalRectangle(self, matrix: List[List[str]]) -> int:
        heights = [0] * len(matrix[0])
        ans = 0
        for row in matrix:
            for j, v in enumerate(row):
                if v == "1":
                    heights[j] += 1
                else:
                    heights[j] = 0
            ans = max(ans, self.largestRectangleArea(heights))
        return ans

    def largestRectangleArea(self, heights: List[int]) -> int:
        n = len(heights)
        stk = []
        left = [-1] * n
        right = [n] * n
        for i, h in enumerate(heights):
            while stk and heights[stk[-1]] >= h:
                stk.pop()
            if stk:
                left[i] = stk[-1]
            stk.append(i)
        stk = []
        for i in range(n - 1, -1, -1):
            h = heights[i]
            while stk and heights[stk[-1]] >= h:
                stk.pop()
            if stk:
                right[i] = stk[-1]
            stk.append(i)
        return max(h * (right[i] - left[i] - 1) for i, h in enumerate(heights))
```

#### Java

```java
class Solution {
    public int maximalRectangle(char[][] matrix) {
        int n = matrix[0].length;
        int[] heights = new int[n];
        int ans = 0;
        for (var row : matrix) {
            for (int j = 0; j < n; ++j) {
                if (row[j] == '1') {
                    heights[j] += 1;
                } else {
                    heights[j] = 0;
                }
            }
            ans = Math.max(ans, largestRectangleArea(heights));
        }
        return ans;
    }

    private int largestRectangleArea(int[] heights) {
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
    int maximalRectangle(vector<vector<char>>& matrix) {
        int n = matrix[0].size();
        vector<int> heights(n);
        int ans = 0;
        for (auto& row : matrix) {
            for (int j = 0; j < n; ++j) {
                if (row[j] == '1')
                    ++heights[j];
                else
                    heights[j] = 0;
            }
            ans = max(ans, largestRectangleArea(heights));
        }
        return ans;
    }

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
func maximalRectangle(matrix [][]byte) int {
	n := len(matrix[0])
	heights := make([]int, n)
	ans := 0
	for _, row := range matrix {
		for j, v := range row {
			if v == '1' {
				heights[j]++
			} else {
				heights[j] = 0
			}
		}
		ans = max(ans, largestRectangleArea(heights))
	}
	return ans
}

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

#### TypeScript

```ts
function maximalRectangle(matrix: string[][]): number {
    const n = matrix[0].length;
    const heights: number[] = new Array(n).fill(0);
    let ans = 0;

    for (const row of matrix) {
        for (let j = 0; j < n; ++j) {
            if (row[j] === '1') {
                heights[j] += 1;
            } else {
                heights[j] = 0;
            }
        }
        ans = Math.max(ans, largestRectangleArea(heights));
    }

    return ans;
}

function largestRectangleArea(heights: number[]): number {
    let res = 0;
    const n = heights.length;
    const stk: number[] = [];
    const left: number[] = new Array(n);
    const right: number[] = new Array(n).fill(n);

    for (let i = 0; i < n; ++i) {
        while (stk.length && heights[stk[stk.length - 1]] >= heights[i]) {
            right[stk.pop()!] = i;
        }
        left[i] = stk.length === 0 ? -1 : stk[stk.length - 1];
        stk.push(i);
    }

    for (let i = 0; i < n; ++i) {
        res = Math.max(res, heights[i] * (right[i] - left[i] - 1));
    }

    return res;
}
```

#### Rust

```rust
impl Solution {
    pub fn maximal_rectangle(matrix: Vec<Vec<char>>) -> i32 {
        let n = matrix[0].len();
        let mut heights = vec![0; n];
        let mut ans = 0;

        for row in matrix {
            for j in 0..n {
                if row[j] == '1' {
                    heights[j] += 1;
                } else {
                    heights[j] = 0;
                }
            }
            ans = ans.max(Self::largest_rectangle_area(&heights));
        }

        ans
    }

    fn largest_rectangle_area(heights: &Vec<i32>) -> i32 {
        let mut res = 0;
        let n = heights.len();
        let mut stk: Vec<usize> = Vec::new();
        let mut left = vec![0; n];
        let mut right = vec![n; n];

        for i in 0..n {
            while let Some(&top) = stk.last() {
                if heights[top] >= heights[i] {
                    right[top] = i;
                    stk.pop();
                } else {
                    break;
                }
            }
            left[i] = if stk.is_empty() {
                -1
            } else {
                stk[stk.len() - 1] as i32
            };
            stk.push(i);
        }

        for i in 0..n {
            res = res.max(heights[i] * (right[i] as i32 - left[i] - 1));
        }

        res
    }
}
```

#### C#

```cs
public class Solution {
    public int MaximalRectangle(char[][] matrix) {
        int n = matrix[0].Length;
        int[] heights = new int[n];
        int ans = 0;

        foreach (var row in matrix) {
            for (int j = 0; j < n; ++j) {
                if (row[j] == '1') {
                    heights[j] += 1;
                } else {
                    heights[j] = 0;
                }
            }
            ans = Math.Max(ans, LargestRectangleArea(heights));
        }

        return ans;
    }

    private int LargestRectangleArea(int[] heights) {
        int res = 0, n = heights.Length;
        Stack<int> stk = new Stack<int>();
        int[] left = new int[n];
        int[] right = new int[n];

        Array.Fill(right, n);

        for (int i = 0; i < n; ++i) {
            while (stk.Count > 0 && heights[stk.Peek()] >= heights[i]) {
                right[stk.Pop()] = i;
            }
            left[i] = stk.Count == 0 ? -1 : stk.Peek();
            stk.Push(i);
        }

        for (int i = 0; i < n; ++i) {
            res = Math.Max(res, heights[i] * (right[i] - left[i] - 1));
        }

        return res;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
