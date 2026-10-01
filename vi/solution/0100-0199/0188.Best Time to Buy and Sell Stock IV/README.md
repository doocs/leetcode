---
comments: true
difficulty: Hard
tags:
    - Array
    - Dynamic Programming
---

<!-- problem:start -->

# [188. Best Time to Buy and Sell Stock IV](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv)

[中文文档](/solution/0100-0199/0188.Best%20Time%20to%20Buy%20and%20Sell%20Stock%20IV/README.md)

## Mô tả

<!-- description:start -->

<p>Bạn được cung cấp một mảng số nguyên <code>prices</code>, trong đó <code>prices[i]</code> là giá của một cổ phiếu vào ngày thứ <code>i<sup>th</sup></code>, và một số nguyên <code>k</code>.</p>

<p>Hãy tìm lợi nhuận lớn nhất bạn có thể đạt được. Bạn có thể thực hiện nhiều nhất <code>k</code> giao dịch: nghĩa là bạn có thể mua nhiều nhất <code>k</code> lần và bán nhiều nhất <code>k</code> lần.</p>

<p><strong>Lưu ý:</strong> Bạn không được thực hiện nhiều giao dịch đồng thời (nghĩa là bạn phải bán cổ phiếu trước khi mua lại).</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong> k = 2, prices = [2,4,1]
<strong>Đầu ra:</strong> 2
<strong>Giải thích:</strong> Mua vào ngày 1 (giá = 2) và bán vào ngày 2 (giá = 4), lợi nhuận = 4-2 = 2.
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> k = 2, prices = [3,2,6,5,0,3]
<strong>Đầu ra:</strong> 7
<strong>Giải thích:</strong> Mua vào ngày 2 (giá = 2) và bán vào ngày 3 (giá = 6), lợi nhuận = 6-2 = 4. Sau đó mua vào ngày 5 (giá = 0) và bán vào ngày 6 (giá = 3), lợi nhuận = 3-0 = 3.
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= 100</code></li>
	<li><code>1 &lt;= prices.length &lt;= 1000</code></li>
	<li><code>0 &lt;= prices[i] &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Tìm kiếm có ghi nhớ

<!-- thinking:start -->

> **Tư duy**
>
> Nhiều nhất $k$ giao dịch khái quát hóa bài toán hai giao dịch. Với $n\le 1000$, $k\le 100$, một trạng thái 3 chiều là phù hợp. $\textit{dfs}(i,j,\textit{hold})$ bắt đầu ở ngày $i$ với $j$ lần mua còn lại. Bỏ qua luôn là hợp lệ; nếu đang nắm giữ thì có thể bán (số lần mua không thay đổi); nếu đang không nắm giữ và còn số lần mua thì có thể mua và sử dụng một lần mua. Ghi nhớ $(i,j,\textit{hold})$.

<!-- thinking:end -->

Ta xây dựng một hàm $dfs(i, j, k)$ biểu diễn lợi nhuận lớn nhất có thể đạt được khi bắt đầu từ ngày $i$, hoàn thành nhiều nhất $j$ giao dịch và nắm giữ cổ phiếu với trạng thái hiện tại là $k$ (không nắm giữ cổ phiếu được biểu diễn bằng $0$, còn đang nắm giữ cổ phiếu được biểu diễn bằng $1$). Đáp án là $dfs(0, k, 0)$.

Logic thực thi của hàm $dfs(i, j, k)$ như sau:

- Nếu $i$ lớn hơn hoặc bằng $n$, trả về $0$ trực tiếp.
- Ngày thứ i có thể chọn không làm gì, khi đó $dfs(i, j, k) = dfs(i + 1, j, k)$.
- Nếu $k > 0$, ngày thứ i có thể chọn bán cổ phiếu, khi đó $dfs(i, j, k) = \max(dfs(i + 1, j - 1, 0) + prices[i], dfs(i + 1, j, k))$.
- Nếu không, khi $j > 0$, ngày thứ i có thể chọn mua cổ phiếu, khi đó $dfs(i, j, k) = \max(dfs(i + 1, j - 1, 1) - prices[i], dfs(i + 1, j, k))$.

Giá trị của $dfs(i, j, k)$ là giá trị lớn nhất trong ba trường hợp trên.

Trong quá trình này, chúng ta có thể sử dụng tìm kiếm có ghi nhớ để lưu kết quả của mỗi phép tính, nhằm tránh tính toán lặp lại.

Độ phức tạp thời gian là $O(n \times k)$, và độ phức tạp không gian là $O(n \times k)$, trong đó $n$ và $k$ lần lượt là độ dài của mảng prices và giá trị của $k$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxProfit(self, k: int, prices: List[int]) -> int:
        @cache
        def dfs(i: int, j: int, k: int) -> int:
            if i >= len(prices):
                return 0
            ans = dfs(i + 1, j, k)
            if k:
                ans = max(ans, prices[i] + dfs(i + 1, j, 0))
            elif j:
                ans = max(ans, -prices[i] + dfs(i + 1, j - 1, 1))
            return ans

        return dfs(0, k, 0)
```

#### Java

```java
class Solution {
    private Integer[][][] f;
    private int[] prices;
    private int n;

    public int maxProfit(int k, int[] prices) {
        n = prices.length;
        this.prices = prices;
        f = new Integer[n][k + 1][2];
        return dfs(0, k, 0);
    }

    private int dfs(int i, int j, int k) {
        if (i >= n) {
            return 0;
        }
        if (f[i][j][k] != null) {
            return f[i][j][k];
        }
        int ans = dfs(i + 1, j, k);
        if (k > 0) {
            ans = Math.max(ans, prices[i] + dfs(i + 1, j, 0));
        } else if (j > 0) {
            ans = Math.max(ans, -prices[i] + dfs(i + 1, j - 1, 1));
        }
        return f[i][j][k] = ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxProfit(int k, vector<int>& prices) {
        int n = prices.size();
        int f[n][k + 1][2];
        memset(f, -1, sizeof(f));
        function<int(int, int, int)> dfs = [&](int i, int j, int k) -> int {
            if (i >= n) {
                return 0;
            }
            if (f[i][j][k] != -1) {
                return f[i][j][k];
            }
            int ans = dfs(i + 1, j, k);
            if (k) {
                ans = max(ans, prices[i] + dfs(i + 1, j, 0));
            } else if (j) {
                ans = max(ans, -prices[i] + dfs(i + 1, j - 1, 1));
            }
            return f[i][j][k] = ans;
        };
        return dfs(0, k, 0);
    }
};
```

#### Go

```go
func maxProfit(k int, prices []int) int {
	n := len(prices)
	f := make([][][2]int, n)
	for i := range f {
		f[i] = make([][2]int, k+1)
		for j := range f[i] {
			f[i][j] = [2]int{-1, -1}
		}
	}
	var dfs func(i, j, k int) int
	dfs = func(i, j, k int) int {
		if i >= n {
			return 0
		}
		if f[i][j][k] != -1 {
			return f[i][j][k]
		}
		ans := dfs(i+1, j, k)
		if k > 0 {
			ans = max(ans, prices[i]+dfs(i+1, j, 0))
		} else if j > 0 {
			ans = max(ans, -prices[i]+dfs(i+1, j-1, 1))
		}
		f[i][j][k] = ans
		return ans
	}
	return dfs(0, k, 0)
}
```

#### TypeScript

```ts
function maxProfit(k: number, prices: number[]): number {
    const n = prices.length;
    const f = Array.from({ length: n }, () =>
        Array.from({ length: k + 1 }, () => Array.from({ length: 2 }, () => -1)),
    );
    const dfs = (i: number, j: number, k: number): number => {
        if (i >= n) {
            return 0;
        }
        if (f[i][j][k] !== -1) {
            return f[i][j][k];
        }
        let ans = dfs(i + 1, j, k);
        if (k) {
            ans = Math.max(ans, prices[i] + dfs(i + 1, j, 0));
        } else if (j) {
            ans = Math.max(ans, -prices[i] + dfs(i + 1, j - 1, 1));
        }
        return (f[i][j][k] = ans);
    };
    return dfs(0, k, 0);
}
```

#### C#

```cs
public class Solution {
    private int[,,] f;
    private int[] prices;
    private int n;

    public int MaxProfit(int k, int[] prices) {
        n = prices.Length;
        f = new int[n, k + 1, 2];
        this.prices = prices;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j <= k; ++j) {
                f[i, j, 0] = -1;
                f[i, j, 1] = -1;
            }
        }
        return dfs(0, k, 0);
    }

    private int dfs(int i, int j, int k) {
        if (i >= n) {
            return 0;
        }
        if (f[i, j, k] != -1) {
            return f[i, j, k];
        }
        int ans = dfs(i + 1, j, k);
        if (k > 0) {
            ans = Math.Max(ans, prices[i] + dfs(i + 1, j, 0));
        }
        else if (j > 0) {
            ans = Math.Max(ans, -prices[i] + dfs(i + 1, j - 1, 1));
        }
        return f[i, j, k] = ans;
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
> Lời giải 1 là đệ quy có ghi nhớ. Theo hướng từ dưới lên, $f[i][j][0/1]$ là lợi nhuận tốt nhất sau ngày $i$ với $j$ lần mua đã sử dụng. Việc mua vào ngày $0$ có giá trị là $-\textit{prices}[0]$; các bước chuyển tiếp sau đó là bán, giữ trạng thái không nắm giữ, mua hoặc tiếp tục nắm giữ — cùng một không gian trạng thái.

<!-- thinking:end -->

Chúng ta cũng có thể sử dụng quy hoạch động để định nghĩa $f[i][j][k]$ là lợi nhuận lớn nhất có thể đạt được khi hoàn thành nhiều nhất j giao dịch (ở đây chúng ta định nghĩa số giao dịch là số lần mua), và nắm giữ cổ phiếu với trạng thái hiện tại là k vào ngày thứ i. Giá trị ban đầu của $f[i][j][k]$ là 0. Đáp án là $f[n - 1][k][0]$.

Khi $i = 0$, giá cổ phiếu là $prices[0]$. Với mọi $j$ \in [1, k]$, we have $f[0][j][1] = -prices[0]$, which means buying the stock on the 0-th day with a profit of $-prices[0]$.

Khi $i > 0$:

- Nếu ngày thứ i không nắm giữ cổ phiếu, có thể là cổ phiếu đã được nắm giữ vào ngày thứ i-1 và được bán vào ngày thứ i, hoặc cổ phiếu không được nắm giữ vào ngày thứ i-1 và không có thao tác nào được thực hiện vào ngày thứ i. Do đó, $f[i][j][0] = \max(f[i - 1][j][1] + prices[i], f[i - 1][j][0])$.
- Nếu ngày thứ i nắm giữ cổ phiếu, có thể là cổ phiếu không được nắm giữ vào ngày thứ i-1 và được mua vào ngày thứ i, hoặc cổ phiếu đã được nắm giữ vào ngày thứ i-1 và không có thao tác nào được thực hiện vào ngày thứ i. Do đó, $f[i][j][1] = max(f[i - 1][j - 1][0] - prices[i], f[i - 1][j][1])$.

Vì vậy, khi $i > 0$, chúng ta có thể nhận được công thức chuyển trạng thái:

$$
\begin{aligned}
f[i][j][0] &= \max(f[i - 1][j][1] + prices[i], f[i - 1][j][0]) \\
f[i][j][1] &= \max(f[i - 1][j - 1][0] - prices[i], f[i - 1][j][1])
\end{aligned}
$$

Đáp án cuối cùng là $f[n - 1][k][0]$.

Độ phức tạp thời gian là $O(n \times k)$, và độ phức tạp không gian là $O(n \times k)$, trong đó $n$ và $k$ lần lượt là độ dài của mảng prices và giá trị của $k$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxProfit(self, k: int, prices: List[int]) -> int:
        n = len(prices)
        f = [[[0] * 2 for _ in range(k + 1)] for _ in range(n)]
        for j in range(1, k + 1):
            f[0][j][1] = -prices[0]
        for i, x in enumerate(prices[1:], 1):
            for j in range(1, k + 1):
                f[i][j][0] = max(f[i - 1][j][1] + x, f[i - 1][j][0])
                f[i][j][1] = max(f[i - 1][j - 1][0] - x, f[i - 1][j][1])
        return f[n - 1][k][0]
```

#### Java

```java
class Solution {
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        int[][][] f = new int[n][k + 1][2];
        for (int j = 1; j <= k; ++j) {
            f[0][j][1] = -prices[0];
        }
        for (int i = 1; i < n; ++i) {
            for (int j = 1; j <= k; ++j) {
                f[i][j][0] = Math.max(f[i - 1][j][1] + prices[i], f[i - 1][j][0]);
                f[i][j][1] = Math.max(f[i - 1][j - 1][0] - prices[i], f[i - 1][j][1]);
            }
        }
        return f[n - 1][k][0];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxProfit(int k, vector<int>& prices) {
        int n = prices.size();
        int f[n][k + 1][2];
        memset(f, 0, sizeof(f));
        for (int j = 1; j <= k; ++j) {
            f[0][j][1] = -prices[0];
        }
        for (int i = 1; i < n; ++i) {
            for (int j = 1; j <= k; ++j) {
                f[i][j][0] = max(f[i - 1][j][1] + prices[i], f[i - 1][j][0]);
                f[i][j][1] = max(f[i - 1][j - 1][0] - prices[i], f[i - 1][j][1]);
            }
        }
        return f[n - 1][k][0];
    }
};
```

#### Go

```go
func maxProfit(k int, prices []int) int {
	n := len(prices)
	f := make([][][2]int, n)
	for i := range f {
		f[i] = make([][2]int, k+1)
	}
	for j := 1; j <= k; j++ {
		f[0][j][1] = -prices[0]
	}
	for i := 1; i < n; i++ {
		for j := 1; j <= k; j++ {
			f[i][j][0] = max(f[i-1][j][1]+prices[i], f[i-1][j][0])
			f[i][j][1] = max(f[i-1][j-1][0]-prices[i], f[i-1][j][1])
		}
	}
	return f[n-1][k][0]
}
```

#### TypeScript

```ts
function maxProfit(k: number, prices: number[]): number {
    const n = prices.length;
    const f = Array.from({ length: n }, () =>
        Array.from({ length: k + 1 }, () => Array.from({ length: 2 }, () => 0)),
    );
    for (let j = 1; j <= k; ++j) {
        f[0][j][1] = -prices[0];
    }
    for (let i = 1; i < n; ++i) {
        for (let j = 1; j <= k; ++j) {
            f[i][j][0] = Math.max(f[i - 1][j][1] + prices[i], f[i - 1][j][0]);
            f[i][j][1] = Math.max(f[i - 1][j - 1][0] - prices[i], f[i - 1][j][1]);
        }
    }
    return f[n - 1][k][0];
}
```

#### C#

```cs
public class Solution {
    public int MaxProfit(int k, int[] prices) {
        int n = prices.Length;
        int[,,] f = new int[n, k + 1, 2];
        for (int j = 1; j <= k; ++j) {
            f[0, j, 1] = -prices[0];
        }
        for (int i = 1; i < n; ++i) {
            for (int j = 1; j <= k; ++j) {
                f[i, j, 0] = Math.Max(f[i - 1, j, 1] + prices[i], f[i - 1, j, 0]);
                f[i, j, 1] = Math.Max(f[i - 1, j - 1, 0] - prices[i], f[i - 1, j, 1]);
            }
        }
        return f[n - 1, k, 0];
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Lời giải 3: Quy hoạch động (Tối ưu không gian)

<!-- thinking:start -->

> **Tư duy**
>
> Ngày $i$ trong Lời giải 2 chỉ phụ thuộc vào ngày $i-1$. Loại bỏ chiều ngày và cuộn $f[j][2]$ với không gian $O(k)$.

<!-- thinking:end -->

$f[i][j][k]$ chỉ phụ thuộc vào ngày trước đó, vì vậy một bảng có kích thước $(k+1) \times 2$ là đủ. Độ phức tạp không gian là $O(k)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxProfit(self, k: int, prices: List[int]) -> int:
        f = [[0] * 2 for _ in range(k + 1)]
        for j in range(1, k + 1):
            f[j][1] = -prices[0]
        for x in prices[1:]:
            for j in range(k, 0, -1):
                f[j][0] = max(f[j][1] + x, f[j][0])
                f[j][1] = max(f[j - 1][0] - x, f[j][1])
        return f[k][0]
```

#### Java

```java
class Solution {
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        int[][] f = new int[k + 1][2];
        for (int j = 1; j <= k; ++j) {
            f[j][1] = -prices[0];
        }
        for (int i = 1; i < n; ++i) {
            for (int j = k; j > 0; --j) {
                f[j][0] = Math.max(f[j][1] + prices[i], f[j][0]);
                f[j][1] = Math.max(f[j - 1][0] - prices[i], f[j][1]);
            }
        }
        return f[k][0];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxProfit(int k, vector<int>& prices) {
        int n = prices.size();
        int f[k + 1][2];
        memset(f, 0, sizeof(f));
        for (int j = 1; j <= k; ++j) {
            f[j][1] = -prices[0];
        }
        for (int i = 1; i < n; ++i) {
            for (int j = k; j; --j) {
                f[j][0] = max(f[j][1] + prices[i], f[j][0]);
                f[j][1] = max(f[j - 1][0] - prices[i], f[j][1]);
            }
        }
        return f[k][0];
    }
};
```

#### Go

```go
func maxProfit(k int, prices []int) int {
	f := make([][2]int, k+1)
	for j := 1; j <= k; j++ {
		f[j][1] = -prices[0]
	}
	for _, x := range prices[1:] {
		for j := k; j > 0; j-- {
			f[j][0] = max(f[j][1]+x, f[j][0])
			f[j][1] = max(f[j-1][0]-x, f[j][1])
		}
	}
	return f[k][0]
}
```

#### TypeScript

```ts
function maxProfit(k: number, prices: number[]): number {
    const f = Array.from({ length: k + 1 }, () => Array.from({ length: 2 }, () => 0));
    for (let j = 1; j <= k; ++j) {
        f[j][1] = -prices[0];
    }
    for (const x of prices.slice(1)) {
        for (let j = k; j; --j) {
            f[j][0] = Math.max(f[j][1] + x, f[j][0]);
            f[j][1] = Math.max(f[j - 1][0] - x, f[j][1]);
        }
    }
    return f[k][0];
}
```

#### C#

```cs
public class Solution {
    public int MaxProfit(int k, int[] prices) {
        int n = prices.Length;
        int[,] f = new int[k + 1, 2];
        for (int j = 1; j <= k; ++j) {
            f[j, 1] = -prices[0];
        }
        for (int i = 1; i < n; ++i) {
            for (int j = k; j > 0; --j) {
                f[j, 0] = Math.Max(f[j, 1] + prices[i], f[j, 0]);
                f[j, 1] = Math.Max(f[j - 1, 0] - prices[i], f[j, 1]);
            }
        }
        return f[k, 0];
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
