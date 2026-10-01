---
comments: true
difficulty: Medium
tags:
    - Tree
    - Binary Search Tree
    - Math
    - Dynamic Programming
    - Binary Tree
---

<!-- problem:start -->

# [96. Unique Binary Search Trees](https://leetcode.com/problems/unique-binary-search-trees)

[中文文档](/solution/0000-0099/0096.Unique%20Binary%20Search%20Trees/README.md)

## Mô tả

<!-- description:start -->

<p>Với một số nguyên <code>n</code>, hãy trả về <em>số lượng <strong>BST</strong> (cây tìm kiếm nhị phân) khác nhau về cấu trúc có đúng </em><code>n</code><em> nút với các giá trị khác nhau từ</em> <code>1</code> <em> đến</em> <code>n</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0096.Unique%20Binary%20Search%20Trees/images/uniquebstn3.jpg" style="width: 600px; height: 148px;" />
<pre>
<strong>Đầu vào:</strong> n = 3
<strong>Đầu ra:</strong> 5
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> n = 1
<strong>Đầu ra:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 19</code></li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Quy hoạch động

<!-- thinking:start -->

> **Tư duy**
>
> Bài trước liệt kê mọi cây; ở đây chúng ta chỉ cần số lượng. Cấu trúc vẫn giống nhau: chọn một nút gốc, nhân số lượng cây con trái và phải, rồi cộng theo mọi nút gốc. Các hình dạng được quy về “số lượng nút”: mọi $i$ số nguyên liên tiếp đều cho cùng một số BST. Gọi $f[i]$ là số lượng cây có $i$ nút, $f[0]=1$, và xây dựng từ $i$ nhỏ đến lớn để tính mỗi kích thước đúng một lần.

<!-- thinking:end -->

Chúng ta định nghĩa $f[i]$ là số lượng cây tìm kiếm nhị phân có thể được tạo từ $[1, i]$. Ban đầu, $f[0] = 1$, và đáp án là $f[n]$.

Ta có thể duyệt qua số lượng nút $i$, sau đó là số lượng nút trong cây con trái $j \in [0, i - 1]$, và số lượng nút trong cây con phải $k = i - j - 1$. Số cách kết hợp số lượng nút trong cây con trái và cây con phải là $f[j] \times f[k]$, do đó $f[i] = \sum_{j = 0}^{i - 1} f[j] \times f[i - j - 1]$.

Cuối cùng, trả về $f[n]$.

Độ phức tạp thời gian là $O(n)$, và độ phức tạp không gian là $O(n)$. Trong đó, $n$ là số lượng nút.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numTrees(self, n: int) -> int:
        f = [1] + [0] * n
        for i in range(n + 1):
            for j in range(i):
                f[i] += f[j] * f[i - j - 1]
        return f[n]
```

#### Java

```java
class Solution {
    public int numTrees(int n) {
        int[] f = new int[n + 1];
        f[0] = 1;
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j < i; ++j) {
                f[i] += f[j] * f[i - j - 1];
            }
        }
        return f[n];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int numTrees(int n) {
        vector<int> f(n + 1);
        f[0] = 1;
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j < i; ++j) {
                f[i] += f[j] * f[i - j - 1];
            }
        }
        return f[n];
    }
};
```

#### Go

```go
func numTrees(n int) int {
	f := make([]int, n+1)
	f[0] = 1
	for i := 1; i <= n; i++ {
		for j := 0; j < i; j++ {
			f[i] += f[j] * f[i-j-1]
		}
	}
	return f[n]
}
```

#### TypeScript

```ts
function numTrees(n: number): number {
    const f: number[] = Array(n + 1).fill(0);
    f[0] = 1;
    for (let i = 1; i <= n; ++i) {
        for (let j = 0; j < i; ++j) {
            f[i] += f[j] * f[i - j - 1];
        }
    }
    return f[n];
}
```

#### Rust

```rust
impl Solution {
    pub fn num_trees(n: i32) -> i32 {
        let n = n as usize;
        let mut f = vec![0; n + 1];
        f[0] = 1;
        for i in 1..=n {
            for j in 0..i {
                f[i] += f[j] * f[i - j - 1];
            }
        }
        f[n] as i32
    }
}
```

#### C#

```cs
public class Solution {
    public int NumTrees(int n) {
        int[] f = new int[n + 1];
        f[0] = 1;
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j < i; ++j) {
                f[i] += f[j] * f[i - j - 1];
            }
        }
        return f[n];
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
