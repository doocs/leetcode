---
comments: true
difficulty: Medium
tags:
    - Greedy
    - Array
    - Dynamic Programming
---

<!-- problem:start -->

# [122. Best Time to Buy and Sell Stock II](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii)

[中文文档](/solution/0100-0199/0122.Best%20Time%20to%20Buy%20and%20Sell%20Stock%20II/README.md)

## Mô tả

<!-- description:start -->

<p>Bạn được cung cấp một mảng số nguyên <code>prices</code>, trong đó <code>prices[i]</code> là giá của một cổ phiếu vào ngày thứ <code>i<sup>th</sup></code>.</p>

<p>Mỗi ngày, bạn có thể quyết định mua và/hoặc bán cổ phiếu. Bạn chỉ có thể nắm giữ <strong>tối đa một</strong> cổ phiếu tại bất kỳ thời điểm nào. Tuy nhiên, bạn có thể bán và mua cổ phiếu nhiều lần trong <strong>cùng một ngày</strong>, miễn là bạn không bao giờ nắm giữ quá một cổ phiếu.</p>

<p>Hãy tìm và trả về <em>lợi nhuận <strong>tối đa</strong> mà bạn có thể đạt được</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong> prices = [7,1,5,3,6,4]
<strong>Đầu ra:</strong> 7
<strong>Giải thích:</strong> Mua vào ngày 2 (giá = 1) và bán vào ngày 3 (giá = 5), lợi nhuận = 5-1 = 4.
Sau đó mua vào ngày 4 (giá = 3) và bán vào ngày 5 (giá = 6), lợi nhuận = 6-3 = 3.
Tổng lợi nhuận là 4 + 3 = 7.
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> prices = [1,2,3,4,5]
<strong>Đầu ra:</strong> 4
<strong>Giải thích:</strong> Mua vào ngày 1 (giá = 1) và bán vào ngày 5 (giá = 5), lợi nhuận = 5-1 = 4.
Tổng lợi nhuận là 4.
</pre>

<p><strong class="example">Ví dụ 3:</strong></p>

<pre>
<strong>Đầu vào:</strong> prices = [7,6,4,3,1]
<strong>Đầu ra:</strong> 0
<strong>Giải thích:</strong> Không có cách nào tạo ra lợi nhuận dương, vì vậy chúng ta không mua cổ phiếu để đạt lợi nhuận tối đa là 0.
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= prices.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= prices[i] &lt;= 10<sup>4</sup></code></li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Thuật toán tham lam

<!-- thinking:start -->

> **Tư duy**
>
> Chúng ta có thể giao dịch bao nhiêu lần tùy thích, nhưng không thể nắm giữ hai cổ phiếu. Cộng mọi mức tăng liên tiếp tương đương với việc mua ở mỗi đáy và bán ở đỉnh tiếp theo. $n \le 3\times 10^4$. Duyệt qua các ngày liên tiếp và chỉ cộng các hiệu dương.

<!-- thinking:end -->

Bắt đầu từ ngày thứ hai, nếu giá cổ phiếu cao hơn ngày trước đó, hãy mua vào ngày trước đó và bán vào ngày hiện tại để thu lợi nhuận. Nếu giá cổ phiếu thấp hơn ngày trước đó, không mua hoặc bán. Nói cách khác, mua và bán vào tất cả các ngày giao dịch có xu hướng tăng, và không giao dịch vào tất cả các ngày giao dịch có xu hướng giảm. Lợi nhuận cuối cùng sẽ là lớn nhất.

Độ phức tạp thời gian là $O(n)$, trong đó $n$ là độ dài của mảng `prices`. Độ phức tạp không gian là $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        return sum(max(0, b - a) for a, b in pairwise(prices))
```

#### Java

```java
class Solution {
    public int maxProfit(int[] prices) {
        int ans = 0;
        for (int i = 1; i < prices.length; ++i) {
            ans += Math.max(0, prices[i] - prices[i - 1]);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxProfit(vector<int>& prices) {
        int ans = 0;
        for (int i = 1; i < prices.size(); ++i) ans += max(0, prices[i] - prices[i - 1]);
        return ans;
    }
};
```

#### Go

```go
func maxProfit(prices []int) (ans int) {
	for i, v := range prices[1:] {
		t := v - prices[i]
		if t > 0 {
			ans += t
		}
	}
	return
}
```

#### TypeScript

```ts
function maxProfit(prices: number[]): number {
    let ans = 0;
    for (let i = 1; i < prices.length; i++) {
        ans += Math.max(0, prices[i] - prices[i - 1]);
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn max_profit(prices: Vec<i32>) -> i32 {
        let mut res = 0;
        for i in 1..prices.len() {
            res += (0).max(prices[i] - prices[i - 1]);
        }
        res
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} prices
 * @return {number}
 */
var maxProfit = function (prices) {
    let ans = 0;
    for (let i = 1; i < prices.length; i++) {
        ans += Math.max(0, prices[i] - prices[i - 1]);
    }
    return ans;
};
```

#### C#

```cs
public class Solution {
    public int MaxProfit(int[] prices) {
        int ans = 0;
        for (int i = 1; i < prices.Length; ++i) {
            ans += Math.Max(0, prices[i] - prices[i - 1]);
        }
        return ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Lời giải 2: Quy hoạch động

<!-- thinking:start -->

> **Tư duy**
>
> Dạng tham lam không có state rõ ràng, vì vậy không mở rộng tốt cho số giao dịch bị giới hạn hoặc giai đoạn cooldown. $f[i][0/1]$ là lợi nhuận tốt nhất vào ngày $i$ khi đang nắm giữ hoặc không; các chuyển trạng thái là giữ nguyên, mua hôm nay, giữ trạng thái không nắm giữ, hoặc bán hôm nay. Cho cùng đáp án như Lời giải 1, nhưng state tổng quát hơn.

<!-- thinking:end -->

Chúng ta định nghĩa $f[i][j]$ là lợi nhuận tối đa sau khi giao dịch vào ngày thứ $i$, trong đó $j$ cho biết hiện tại chúng ta có nắm giữ cổ phiếu hay không. Khi đang nắm giữ cổ phiếu, $j=0$, còn khi không nắm giữ cổ phiếu, $j=1$. Trạng thái ban đầu là $f[0][0]=-prices[0]$, và tất cả các trạng thái khác là $0$.

Nếu hiện tại chúng ta đang nắm giữ cổ phiếu, có thể là chúng ta đã nắm giữ cổ phiếu vào ngày hôm trước và không làm gì hôm nay, tức là $f[i][0]=f[i-1][0]$. Hoặc có thể là chúng ta không nắm giữ cổ phiếu vào ngày hôm trước và đã mua cổ phiếu hôm nay, tức là $f[i][0]=f[i-1][1]-prices[i]$.

Nếu hiện tại chúng ta không nắm giữ cổ phiếu, có thể là chúng ta đã không nắm giữ cổ phiếu vào ngày hôm trước và không làm gì hôm nay, tức là $f[i][1]=f[i-1][1]$. Hoặc có thể là chúng ta đã nắm giữ cổ phiếu vào ngày hôm trước và đã bán cổ phiếu hôm nay, tức là $f[i][1]=f[i-1][0]+prices[i]$.

Do đó, chúng ta có thể viết phương trình chuyển trạng thái như sau:

$$
\begin{cases}
f[i][0]=\max(f[i-1][0],f[i-1][1]-prices[i])\\
f[i][1]=\max(f[i-1][1],f[i-1][0]+prices[i])
\end{cases}
$$

Đáp án cuối cùng là $f[n-1][1]$, trong đó $n$ là độ dài của mảng `prices`.

Độ phức tạp thời gian là $O(n)$, và độ phức tạp không gian là $O(n)$. Ở đây, $n$ là độ dài của mảng `prices`.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        n = len(prices)
        f = [[0] * 2 for _ in range(n)]
        f[0][0] = -prices[0]
        for i in range(1, n):
            f[i][0] = max(f[i - 1][0], f[i - 1][1] - prices[i])
            f[i][1] = max(f[i - 1][1], f[i - 1][0] + prices[i])
        return f[n - 1][1]
```

#### Java

```java
class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] f = new int[n][2];
        f[0][0] = -prices[0];
        for (int i = 1; i < n; ++i) {
            f[i][0] = Math.max(f[i - 1][0], f[i - 1][1] - prices[i]);
            f[i][1] = Math.max(f[i - 1][1], f[i - 1][0] + prices[i]);
        }
        return f[n - 1][1];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxProfit(vector<int>& prices) {
        int n = prices.size();
        int f[n][2];
        f[0][0] = -prices[0];
        f[0][1] = 0;
        for (int i = 1; i < n; ++i) {
            f[i][0] = max(f[i - 1][0], f[i - 1][1] - prices[i]);
            f[i][1] = max(f[i - 1][1], f[i - 1][0] + prices[i]);
        }
        return f[n - 1][1];
    }
};
```

#### Go

```go
func maxProfit(prices []int) int {
	n := len(prices)
	f := make([][2]int, n)
	f[0][0] = -prices[0]
	for i := 1; i < n; i++ {
		f[i][0] = max(f[i-1][0], f[i-1][1]-prices[i])
		f[i][1] = max(f[i-1][1], f[i-1][0]+prices[i])
	}
	return f[n-1][1]
}
```

#### C#

```cs
public class Solution {
    public int MaxProfit(int[] prices) {
        int f1 = -prices[0], f2 = 0;
        for (int i = 1; i < prices.Length; ++i)
        {
            f1 = Math.Max(f1, f2 - prices[i]);
            f2 = Math.Max(f2, f1 + prices[i]);
        }
        return f2;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Lời giải 3: Quy hoạch động (Tối ưu hóa không gian)

<!-- thinking:start -->

> **Tư duy**
>
> Trạng thái ngày $i$ trong Lời giải 2 chỉ phụ thuộc vào hai trạng thái của ngày $i-1$, vì vậy hai biến luân phiên giảm không gian xuống $O(1)$.

<!-- thinking:end -->

Chúng ta có thể thấy rằng trong Lời giải 2, trạng thái của ngày thứ $i$ chỉ liên quan đến trạng thái của ngày thứ $i-1$. Do đó, chúng ta chỉ cần dùng hai biến để duy trì trạng thái của ngày thứ $i-1$, qua đó tối ưu hóa độ phức tạp không gian xuống $O(1)$.

Độ phức tạp thời gian là $O(n)$, trong đó $n$ là độ dài của mảng `prices`. Độ phức tạp không gian là $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        n = len(prices)
        f = [-prices[0], 0]
        for i in range(1, n):
            g = [0] * 2
            g[0] = max(f[0], f[1] - prices[i])
            g[1] = max(f[1], f[0] + prices[i])
            f = g
        return f[1]
```

#### Java

```java
class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[] f = new int[] {-prices[0], 0};
        for (int i = 1; i < n; ++i) {
            int[] g = new int[2];
            g[0] = Math.max(f[0], f[1] - prices[i]);
            g[1] = Math.max(f[1], f[0] + prices[i]);
            f = g;
        }
        return f[1];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxProfit(vector<int>& prices) {
        int n = prices.size();
        int f[2] = {-prices[0], 0};
        for (int i = 1; i < n; ++i) {
            int g[2];
            g[0] = max(f[0], f[1] - prices[i]);
            g[1] = max(f[1], f[0] + prices[i]);
            f[0] = g[0], f[1] = g[1];
        }
        return f[1];
    }
};
```

#### Go

```go
func maxProfit(prices []int) int {
	n := len(prices)
	f := [2]int{-prices[0], 0}
	for i := 1; i < n; i++ {
		g := [2]int{}
		g[0] = max(f[0], f[1]-prices[i])
		g[1] = max(f[1], f[0]+prices[i])
		f = g
	}
	return f[1]
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
