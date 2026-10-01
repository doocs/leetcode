---
comments: true
difficulty: Hard
tags:
    - Array
    - Dynamic Programming
---

<!-- problem:start -->

# [123. Best Time to Buy and Sell Stock III](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii)

[中文文档](/solution/0100-0199/0123.Best%20Time%20to%20Buy%20and%20Sell%20Stock%20III/README.md)

## Mô tả

<!-- description:start -->

<p>Bạn được cho một mảng <code>prices</code>, trong đó <code>prices[i]</code> là giá của một cổ phiếu vào ngày thứ <code>i<sup>th</sup></code>.</p>

<p>Hãy tìm lợi nhuận tối đa bạn có thể đạt được. Bạn có thể thực hiện <strong>tối đa hai giao dịch</strong>.</p>

<p><strong>Lưu ý:</strong> Bạn không được thực hiện nhiều giao dịch đồng thời (tức là bạn phải bán cổ phiếu trước khi mua lại).</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong> prices = [3,3,5,0,0,3,1,4]
<strong>Đầu ra:</strong> 6
<strong>Giải thích:</strong> Mua vào ngày 4 (giá = 0) và bán vào ngày 6 (giá = 3), lợi nhuận = 3-0 = 3.
Sau đó mua vào ngày 7 (giá = 1) và bán vào ngày 8 (giá = 4), lợi nhuận = 4-1 = 3.</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> prices = [1,2,3,4,5]
<strong>Đầu ra:</strong> 4
<strong>Giải thích:</strong> Mua vào ngày 1 (giá = 1) và bán vào ngày 5 (giá = 5), lợi nhuận = 5-1 = 4.
Lưu ý rằng bạn không thể mua vào ngày 1, mua vào ngày 2 rồi bán chúng sau đó, vì bạn đang thực hiện nhiều giao dịch cùng lúc. Bạn phải bán trước khi mua lại.
</pre>

<p><strong class="example">Ví dụ 3:</strong></p>

<pre>
<strong>Đầu vào:</strong> prices = [7,6,4,3,1]
<strong>Đầu ra:</strong> 0
<strong>Giải thích:</strong> Trong trường hợp này, không thực hiện giao dịch nào, tức là lợi nhuận tối đa = 0.
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= prices.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= prices[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Quy hoạch động

<!-- thinking:start -->

> **Tư duy**
>
> Tối đa hai giao dịch, vì vậy trạng thái giao dịch không giới hạn của bài trước là chưa đủ. DP 3 chiều theo ngày, số giao dịch đã hoàn tất và trạng thái nắm giữ sẽ nặng nề hơn mức cần thiết. Thứ tự hợp lệ là buy1, sell1, buy2, sell2. Bốn biến được cập nhật theo thứ tự đó mỗi ngày, mỗi biến sử dụng giai đoạn trước. Mua và bán trong cùng một ngày tạo ra $0$ và không làm ảnh hưởng đến đáp án tối ưu.

<!-- thinking:end -->

Chúng ta định nghĩa các biến sau:

- `f1` biểu diễn lợi nhuận tối đa sau khi mua cổ phiếu lần thứ nhất;
- `f2` biểu diễn lợi nhuận tối đa sau khi bán cổ phiếu lần thứ nhất;
- `f3` biểu diễn lợi nhuận tối đa sau khi mua cổ phiếu lần thứ hai;
- `f4` biểu diễn lợi nhuận tối đa sau khi bán cổ phiếu lần thứ hai.

Trong quá trình duyệt, chúng ta trực tiếp tính `f1`, `f2`, `f3`, `f4`. Chúng ta xét rằng việc mua và bán trong cùng một ngày sẽ tạo ra lợi nhuận $0$, điều này không ảnh hưởng đến đáp án.

Cuối cùng, trả về `f4`.

Độ phức tạp thời gian là $O(n)$, trong đó $n$ là độ dài của mảng `prices`. Độ phức tạp không gian là $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        # 第一次买入，第一次卖出，第二次买入，第二次卖出
        f1, f2, f3, f4 = -prices[0], 0, -prices[0], 0
        for price in prices[1:]:
            f1 = max(f1, -price)
            f2 = max(f2, f1 + price)
            f3 = max(f3, f2 - price)
            f4 = max(f4, f3 + price)
        return f4
```

#### Java

```java
class Solution {
    public int maxProfit(int[] prices) {
        // 第一次买入，第一次卖出，第二次买入，第二次卖出
        int f1 = -prices[0], f2 = 0, f3 = -prices[0], f4 = 0;
        for (int i = 1; i < prices.length; ++i) {
            f1 = Math.max(f1, -prices[i]);
            f2 = Math.max(f2, f1 + prices[i]);
            f3 = Math.max(f3, f2 - prices[i]);
            f4 = Math.max(f4, f3 + prices[i]);
        }
        return f4;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxProfit(vector<int>& prices) {
        int f1 = -prices[0], f2 = 0, f3 = -prices[0], f4 = 0;
        for (int i = 1; i < prices.size(); ++i) {
            f1 = max(f1, -prices[i]);
            f2 = max(f2, f1 + prices[i]);
            f3 = max(f3, f2 - prices[i]);
            f4 = max(f4, f3 + prices[i]);
        }
        return f4;
    }
};
```

#### Go

```go
func maxProfit(prices []int) int {
	f1, f2, f3, f4 := -prices[0], 0, -prices[0], 0
	for i := 1; i < len(prices); i++ {
		f1 = max(f1, -prices[i])
		f2 = max(f2, f1+prices[i])
		f3 = max(f3, f2-prices[i])
		f4 = max(f4, f3+prices[i])
	}
	return f4
}
```

#### TypeScript

```ts
function maxProfit(prices: number[]): number {
    let [f1, f2, f3, f4] = [-prices[0], 0, -prices[0], 0];
    for (let i = 1; i < prices.length; ++i) {
        f1 = Math.max(f1, -prices[i]);
        f2 = Math.max(f2, f1 + prices[i]);
        f3 = Math.max(f3, f2 - prices[i]);
        f4 = Math.max(f4, f3 + prices[i]);
    }
    return f4;
}
```

#### Rust

```rust
impl Solution {
    #[allow(dead_code)]
    pub fn max_profit(prices: Vec<i32>) -> i32 {
        let mut f1 = -prices[0];
        let mut f2 = 0;
        let mut f3 = -prices[0];
        let mut f4 = 0;
        let n = prices.len();

        for i in 1..n {
            f1 = std::cmp::max(f1, -prices[i]);
            f2 = std::cmp::max(f2, f1 + prices[i]);
            f3 = std::cmp::max(f3, f2 - prices[i]);
            f4 = std::cmp::max(f4, f3 + prices[i]);
        }

        f4
    }
}
```

#### C#

```cs
public class Solution {
    public int MaxProfit(int[] prices) {
        int f1 = -prices[0], f2 = 0, f3 = -prices[0], f4 = 0;
        for (int i = 1; i < prices.Length; ++i) {
            f1 = Math.Max(f1, -prices[i]);
            f2 = Math.Max(f2, f1 + prices[i]);
            f3 = Math.Max(f3, f2 - prices[i]);
            f4 = Math.Max(f4, f3 + prices[i]);
        }
        return f4;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
