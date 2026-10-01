---
comments: true
difficulty: Medium
tags:
    - Bit Manipulation
    - Math
    - Backtracking
---

<!-- problem:start -->

# [89. Gray Code](https://leetcode.com/problems/gray-code)

[中文文档](/solution/0000-0099/0089.Gray%20Code/README.md)

## Mô tả

<!-- description:start -->

<p>Một <strong>chuỗi mã Gray n-bit</strong> là một chuỗi gồm <code>2<sup>n</sup></code> số nguyên, trong đó:</p>

<ul>
	<li>Mọi số nguyên đều nằm trong <strong>phạm vi bao gồm cả hai đầu mút</strong> <code>[0, 2<sup>n</sup> - 1]</code>,</li>
	<li>Số nguyên đầu tiên là <code>0</code>,</li>
	<li>Một số nguyên xuất hiện <strong>không quá một lần</strong> trong chuỗi,</li>
	<li>Biểu diễn nhị phân của mọi cặp số nguyên <strong>liền kề</strong> khác nhau <strong>chính xác một bit</strong>, và</li>
	<li>Biểu diễn nhị phân của số nguyên <strong>đầu tiên</strong> và <strong>cuối cùng</strong> khác nhau <strong>chính xác một bit</strong>.</li>
</ul>

<p>Cho một số nguyên <code>n</code>, hãy trả về <em>bất kỳ <strong>chuỗi mã Gray n-bit</strong> hợp lệ nào</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong> n = 2
<strong>Đầu ra:</strong> [0,1,3,2]
<strong>Giải thích:</strong>
Biểu diễn nhị phân của [0,1,3,2] là [00,01,11,10].
- 0<u>0</u> và 0<u>1</u> khác nhau một bit
- <u>0</u>1 và <u>1</u>1 khác nhau một bit
- 1<u>1</u> và 1<u>0</u> khác nhau một bit
- 1<u>0</u> và <u>0</u>0 khác nhau một bit
[0,2,3,1] cũng là một chuỗi mã Gray hợp lệ, với biểu diễn nhị phân là [00,10,11,01].
- <u>0</u>0 và <u>1</u>0 khác nhau một bit
- 1<u>0</u> và 1<u>1</u> khác nhau một bit
- <u>1</u>1 và <u>0</u>1 khác nhau một bit
- 0<u>1</u> và 0<u>0</u> khác nhau một bit
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> n = 1
<strong>Đầu ra:</strong> [0,1]
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 16</code></li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Chuyển đổi từ nhị phân sang mã Gray

<!-- thinking:start -->

> **Tư duy**
>
> Ý tưởng đầu tiên là quay lui: bắt đầu từ $0$, lật một bit chưa dùng ở mỗi bước, cho đến khi có $2^n$ số. Cách này đúng, và $n \le 16$ thì sẽ vượt qua, nhưng chúng ta phải loại trùng và cũng phải bảo đảm số đầu tiên và số cuối cùng khác nhau ở một bit.
>
> Nút thắt nằm ở việc duy trì điều kiện “các mã liền kề khác nhau ở một bit” bằng cách thủ công. Mã Gray phản chiếu nhị phân có công thức đóng: $i$ ánh xạ thành $i \oplus (i \gg 1)$.
>
> Công thức đó đã bảo đảm các số nguyên liền kề khác nhau ở một bit, còn $0$ và $2^n - 1$ chỉ khác nhau ở bit cao nhất. Vì vậy, chỉ cần ánh xạ $[0, 2^n)$ — không cần tìm kiếm.

<!-- thinking:end -->

Mã Gray là một loại phương pháp mã hóa mà chúng ta thường gặp trong kỹ thuật. Đặc điểm cơ bản của nó là chỉ có một bit của số nhị phân khác nhau giữa bất kỳ hai mã liền kề nào.

Quy tắc chuyển đổi mã nhị phân sang mã Gray là giữ lại bit cao nhất của mã nhị phân làm bit cao nhất của mã Gray, còn bit cao thứ hai của mã Gray là phép XOR của bit cao nhất và bit cao thứ hai của mã nhị phân. Việc tính toán các bit còn lại của mã Gray cũng tương tự như với bit cao thứ hai.

Giả sử một số nhị phân được biểu diễn là $B_{n-1}B_{n-2}...B_2B_1B_0$, và mã Gray của nó được biểu diễn là $G_{n-1}G_{n-2}...G_2G_1G_0$. Bit cao nhất được giữ nguyên, do đó $G_{n-1} = B_{n-1}$; còn các bit khác $G_i = B_{i+1} \oplus B_{i}$, trong đó $i=0,1,2..,n-2$.

Do đó, với một số nguyên $x$, chúng ta có thể sử dụng hàm $gray(x)$ để lấy mã Gray của nó:

```java
int gray(x) {
    return x ^ (x >> 1);
}
```

Chúng ta ánh xạ trực tiếp các số nguyên $[0,..2^n - 1]$ sang các mã Gray tương ứng để thu được mảng kết quả.

Độ phức tạp thời gian là $O(2^n)$, trong đó $n$ là số nguyên được cho trong đề bài. Bỏ qua mức tiêu thụ không gian của đáp án, độ phức tạp không gian là $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def grayCode(self, n: int) -> List[int]:
        return [i ^ (i >> 1) for i in range(1 << n)]
```

#### Java

```java
class Solution {
    public List<Integer> grayCode(int n) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < 1 << n; ++i) {
            ans.add(i ^ (i >> 1));
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> grayCode(int n) {
        vector<int> ans;
        for (int i = 0; i < 1 << n; ++i) {
            ans.push_back(i ^ (i >> 1));
        }
        return ans;
    }
};
```

#### Go

```go
func grayCode(n int) (ans []int) {
	for i := 0; i < 1<<n; i++ {
		ans = append(ans, i^(i>>1))
	}
	return
}
```

#### JavaScript

```js
/**
 * @param {number} n
 * @return {number[]}
 */
var grayCode = function (n) {
    const ans = [];
    for (let i = 0; i < 1 << n; ++i) {
        ans.push(i ^ (i >> 1));
    }
    return ans;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
