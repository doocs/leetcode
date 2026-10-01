---
comments: true
difficulty: Easy
tags:
    - Array
    - Dynamic Programming
---

<!-- problem:start -->

# [119. Pascal's Triangle II](https://leetcode.com/problems/pascals-triangle-ii)

[中文文档](/solution/0100-0199/0119.Pascal%27s%20Triangle%20II/README.md)

## Mô tả

<!-- description:start -->

<p>Cho một số nguyên <code>rowIndex</code>, hãy trả về hàng thứ <code>rowIndex<sup>th</sup></code> (<strong>đánh chỉ số từ 0</strong>) của <strong>tam giác Pascal</strong>.</p>

<p>Trong <strong>tam giác Pascal</strong>, mỗi số là tổng của hai số nằm ngay phía trên nó như hình dưới đây:</p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0119.Pascal%27s%20Triangle%20II/images/PascalTriangleAnimated2.gif" style="height:240px; width:260px" />
<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>
<pre><strong>Đầu vào:</strong> rowIndex = 3
<strong>Đầu ra:</strong> [1,3,3,1]
</pre><p><strong class="example">Ví dụ 2:</strong></p>
<pre><strong>Đầu vào:</strong> rowIndex = 0
<strong>Đầu ra:</strong> [1]
</pre><p><strong class="example">Ví dụ 3:</strong></p>
<pre><strong>Đầu vào:</strong> rowIndex = 1
<strong>Đầu ra:</strong> [1,1]
</pre>
<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>0 &lt;= rowIndex &lt;= 33</code></li>
</ul>

<p>&nbsp;</p>
<p><strong>Câu hỏi mở rộng:</strong> Bạn có thể tối ưu thuật toán để chỉ sử dụng <code>O(rowIndex)</code> không gian bổ sung không?</p>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Đệ quy

<!-- thinking:start -->

> **Tư duy**
>
> Chúng ta chỉ cần hàng $r$, không phải toàn bộ tam giác. Câu hỏi mở rộng yêu cầu không gian $O(r)$. Cập nhật $f[j] += f[j-1]$ từ phải sang trái giữ hàng mới ở bên phải và hàng cũ ở bên trái, do đó một mảng duy nhất tiến dần đến $\textit{rowIndex}$.

<!-- thinking:end -->

Chúng ta tạo một mảng $f$ có độ dài $rowIndex + 1$, ban đầu tất cả phần tử đều bằng $1$.

Tiếp theo, bắt đầu từ hàng thứ hai, chúng ta tính giá trị của phần tử thứ $j$ trong hàng hiện tại từ cuối lên đầu, $f[j] = f[j] + f[j - 1]$, trong đó $j \in [1, i - 1]$.

Cuối cùng, trả về $f$.

Độ phức tạp thời gian là $O(n^2)$, và độ phức tạp không gian là $O(n)$. Trong đó, $n$ là số hàng được cho.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def getRow(self, rowIndex: int) -> List[int]:
        f = [1] * (rowIndex + 1)
        for i in range(2, rowIndex + 1):
            for j in range(i - 1, 0, -1):
                f[j] += f[j - 1]
        return f
```

#### Java

```java
class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> f = new ArrayList<>();
        for (int i = 0; i < rowIndex + 1; ++i) {
            f.add(1);
        }
        for (int i = 2; i < rowIndex + 1; ++i) {
            for (int j = i - 1; j > 0; --j) {
                f.set(j, f.get(j) + f.get(j - 1));
            }
        }
        return f;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> getRow(int rowIndex) {
        vector<int> f(rowIndex + 1, 1);
        for (int i = 2; i < rowIndex + 1; ++i) {
            for (int j = i - 1; j; --j) {
                f[j] += f[j - 1];
            }
        }
        return f;
    }
};
```

#### Go

```go
func getRow(rowIndex int) []int {
	f := make([]int, rowIndex+1)
	for i := range f {
		f[i] = 1
	}
	for i := 2; i < rowIndex+1; i++ {
		for j := i - 1; j > 0; j-- {
			f[j] += f[j-1]
		}
	}
	return f
}
```

#### TypeScript

```ts
function getRow(rowIndex: number): number[] {
    const f: number[] = Array(rowIndex + 1).fill(1);
    for (let i = 2; i < rowIndex + 1; ++i) {
        for (let j = i - 1; j; --j) {
            f[j] += f[j - 1];
        }
    }
    return f;
}
```

#### Rust

```rust
impl Solution {
    pub fn get_row(row_index: i32) -> Vec<i32> {
        let n = (row_index + 1) as usize;
        let mut f = vec![1; n];
        for i in 2..n {
            for j in (1..i).rev() {
                f[j] += f[j - 1];
            }
        }
        f
    }
}
```

#### JavaScript

```js
/**
 * @param {number} rowIndex
 * @return {number[]}
 */
var getRow = function (rowIndex) {
    const f = Array(rowIndex + 1).fill(1);
    for (let i = 2; i < rowIndex + 1; ++i) {
        for (let j = i - 1; j; --j) {
            f[j] += f[j - 1];
        }
    }
    return f;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
