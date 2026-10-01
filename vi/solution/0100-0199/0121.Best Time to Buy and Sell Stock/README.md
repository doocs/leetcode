---
comments: true
difficulty: Easy
tags:
    - Array
    - Dynamic Programming
---

<!-- problem:start -->

# [121. Best Time to Buy and Sell Stock](https://leetcode.com/problems/best-time-to-buy-and-sell-stock)

[中文文档](/solution/0100-0199/0121.Best%20Time%20to%20Buy%20and%20Sell%20Stock/README.md)

## Mô tả

<!-- description:start -->

<p>Bạn được cho một mảng <code>prices</code>, trong đó <code>prices[i]</code> là giá của một cổ phiếu vào ngày thứ <code>i<sup>th</sup></code>.</p>

<p>Bạn muốn tối đa hóa lợi nhuận bằng cách chọn một <strong>ngày duy nhất</strong> để mua một cổ phiếu và chọn một <strong>ngày khác trong tương lai</strong> để bán cổ phiếu đó.</p>

<p>Hãy trả về <em>lợi nhuận tối đa bạn có thể đạt được từ giao dịch này</em>. Nếu không thể đạt được bất kỳ lợi nhuận nào, hãy trả về <code>0</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong> prices = [7,1,5,3,6,4]
<strong>Đầu ra:</strong> 5
<strong>Giải thích:</strong> Mua vào ngày 2 (giá = 1) và bán vào ngày 5 (giá = 6), lợi nhuận = 6-1 = 5.
Lưu ý rằng không được mua vào ngày 2 và bán vào ngày 1 vì bạn phải mua trước khi bán.
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> prices = [7,6,4,3,1]
<strong>Đầu ra:</strong> 0
<strong>Giải thích:</strong> Trong trường hợp này, không có giao dịch nào được thực hiện và lợi nhuận tối đa = 0.
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
    <li><code>1 &lt;= prices.length &lt;= 10<sup>5</sup></code></li>
    <li><code>0 &lt;= prices[i] &lt;= 10<sup>4</sup></code></li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Liệt kê + Duy trì Giá trị Nhỏ nhất của Tiền tố

<!-- thinking:start -->

> **Tư duy**
>
> Một lần mua và một lần bán; thử mọi cặp mất $O(n^2)$ và $n$ có thể đạt $10^5$. Với một ngày bán cố định, giá mua tốt nhất là giá nhỏ nhất trước ngày đó. Duyệt mảng trong khi duy trì giá trị nhỏ nhất của tiền tố, cập nhật đáp án bằng giá hôm nay trừ đi giá trị nhỏ nhất đó, rồi đưa giá hôm nay vào tiền tố.

<!-- thinking:end -->

Chúng ta có thể liệt kê từng phần tử của mảng $nums$ làm giá bán. Khi đó, chúng ta cần tìm giá trị nhỏ nhất ở phía trước nó làm giá mua để tối đa hóa lợi nhuận.

Do đó, chúng ta sử dụng một biến $mi$ để duy trì giá trị nhỏ nhất của tiền tố của mảng $nums$. Sau đó, chúng ta duyệt mảng $nums$ và với mỗi phần tử $v$, tính hiệu giữa nó và giá trị nhỏ nhất $mi$ ở phía trước nó, rồi cập nhật đáp án thành giá trị lớn nhất của hiệu đó. Sau đó cập nhật $mi = min(mi, v)$. Tiếp tục duyệt mảng $nums$ cho đến khi kết thúc.

Cuối cùng, trả về đáp án.

Độ phức tạp thời gian là $O(n)$, trong đó $n$ là độ dài của mảng $nums$. Độ phức tạp không gian là $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        ans, mi = 0, inf
        for v in prices:
            ans = max(ans, v - mi)
            mi = min(mi, v)
        return ans
```

#### Java

```java
class Solution {
    public int maxProfit(int[] prices) {
        int ans = 0, mi = prices[0];
        for (int v : prices) {
            ans = Math.max(ans, v - mi);
            mi = Math.min(mi, v);
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
        int ans = 0, mi = prices[0];
        for (int& v : prices) {
            ans = max(ans, v - mi);
            mi = min(mi, v);
        }
        return ans;
    }
};
```

#### Go

```go
func maxProfit(prices []int) (ans int) {
	mi := prices[0]
	for _, v := range prices {
		ans = max(ans, v-mi)
		mi = min(mi, v)
	}
	return
}
```

#### TypeScript

```ts
function maxProfit(prices: number[]): number {
    let ans = 0;
    let mi = prices[0];
    for (const v of prices) {
        ans = Math.max(ans, v - mi);
        mi = Math.min(mi, v);
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn max_profit(prices: Vec<i32>) -> i32 {
        let mut ans = 0;
        let mut mi = prices[0];
        for &v in &prices {
            ans = ans.max(v - mi);
            mi = mi.min(v);
        }
        ans
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
    let mi = prices[0];
    for (const v of prices) {
        ans = Math.max(ans, v - mi);
        mi = Math.min(mi, v);
    }
    return ans;
};
```

#### C#

```cs
public class Solution {
    public int MaxProfit(int[] prices) {
        int ans = 0, mi = prices[0];
        foreach (int v in prices) {
            ans = Math.Max(ans, v - mi);
            mi = Math.Min(mi, v);
        }
        return ans;
    }
}
```

#### PHP

```php
class Solution {
    /**
     * @param Integer[] $prices
     * @return Integer
     */
    function maxProfit($prices) {
        $ans = 0;
        $mi = $prices[0];
        foreach ($prices as $v) {
            $ans = max($ans, $v - $mi);
            $mi = min($mi, $v);
        }
        return $ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
