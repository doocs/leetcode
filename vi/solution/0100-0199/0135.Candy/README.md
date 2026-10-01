---
comments: true
difficulty: Hard
tags:
    - Greedy
    - Array
---

<!-- problem:start -->

# [135. Candy](https://leetcode.com/problems/candy)

[中文文档](/solution/0100-0199/0135.Candy/README.md)

## Mô tả

<!-- description:start -->

<p>Có <code>n</code> đứa trẻ đứng thành một hàng.</p>

<p>Mỗi đứa trẻ được gán một giá trị đánh giá trong mảng số nguyên <code>ratings</code>.</p>

<p>Bạn phát kẹo cho những đứa trẻ này theo các yêu cầu sau:</p>

<ul>
	<li>Mỗi đứa trẻ phải có <strong>ít nhất</strong> một viên kẹo.</li>
	<li>Những đứa trẻ có đánh giá <strong>cao hơn</strong> nhận nhiều kẹo hơn những đứa trẻ hàng xóm.</li>
</ul>

<p>Hãy trả về số lượng kẹo <strong>ít nhất</strong> cần có để phân phát cho những đứa trẻ.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong> ratings = [1,0,2]
<strong>Đầu ra:</strong> 5
<strong>Giải thích:</strong> Bạn có thể lần lượt phát cho đứa trẻ thứ nhất, thứ hai và thứ ba số kẹo là 2, 1, 2.
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> ratings = [1,2,2]
<strong>Đầu ra:</strong> 4
<strong>Giải thích:</strong> Bạn có thể lần lượt phát cho đứa trẻ thứ nhất, thứ hai và thứ ba số kẹo là 1, 2, 1.
Đứa trẻ thứ ba nhận 1 viên kẹo vì thỏa mãn hai điều kiện nêu trên.
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= n == ratings.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= ratings[i] &lt;= 5 * 10<sup>4</sup></code></li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Hai lần duyệt

<!-- thinking:start -->

> **Tư duy**
>
> Một đứa trẻ có đánh giá cao hơn phải nhận nhiều kẹo hơn hàng xóm; mục tiêu là tối thiểu hóa tổng số kẹo. $n\le 5\times 10^4$. Có thể sắp xếp theo đánh giá, nhưng các đánh giá bằng nhau khá rắc rối. Các ràng buộc bên trái và bên phải là độc lập: một lượt duyệt từ trái sang phải áp đặt điều kiện “nhiều hơn hàng xóm bên trái”, một lượt duyệt từ phải sang trái áp đặt điều kiện bên phải, và mỗi đứa trẻ nhận giá trị lớn nhất. Cả hai chuỗi tăng đều được giữ lại.

<!-- thinking:end -->

Chúng ta khởi tạo hai mảng $left$ và $right$, trong đó $left[i]$ biểu thị số kẹo ít nhất mà đứa trẻ hiện tại cần nhận khi điểm số của đứa trẻ hiện tại cao hơn điểm số của đứa trẻ bên trái, còn $right[i]$ biểu thị số kẹo ít nhất mà đứa trẻ hiện tại cần nhận khi điểm số của đứa trẻ hiện tại cao hơn điểm số của đứa trẻ bên phải. Ban đầu, $left[i]=1$, $right[i]=1$.

Chúng ta duyệt mảng từ trái sang phải một lần, và nếu điểm số của đứa trẻ hiện tại cao hơn điểm số của đứa trẻ bên trái thì $left[i]=left[i-1]+1$; tương tự, chúng ta duyệt mảng từ phải sang trái một lần, và nếu điểm số của đứa trẻ hiện tại cao hơn điểm số của đứa trẻ bên phải thì $right[i]=right[i+1]+1$.

Cuối cùng, chúng ta duyệt mảng điểm số một lần, số kẹo ít nhất mà mỗi đứa trẻ cần nhận là giá trị lớn hơn giữa $left[i]$ và $right[i]$, rồi cộng chúng lại để được đáp án.

Độ phức tạp thời gian là $O(n)$, độ phức tạp không gian là $O(n)$. Trong đó $n$ là độ dài của mảng điểm số.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def candy(self, ratings: List[int]) -> int:
        n = len(ratings)
        left = [1] * n
        right = [1] * n
        for i in range(1, n):
            if ratings[i] > ratings[i - 1]:
                left[i] = left[i - 1] + 1
        for i in range(n - 2, -1, -1):
            if ratings[i] > ratings[i + 1]:
                right[i] = right[i + 1] + 1
        return sum(max(a, b) for a, b in zip(left, right))
```

#### Java

```java
class Solution {
    public int candy(int[] ratings) {
        int n = ratings.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(left, 1);
        Arrays.fill(right, 1);
        for (int i = 1; i < n; ++i) {
            if (ratings[i] > ratings[i - 1]) {
                left[i] = left[i - 1] + 1;
            }
        }
        for (int i = n - 2; i >= 0; --i) {
            if (ratings[i] > ratings[i + 1]) {
                right[i] = right[i + 1] + 1;
            }
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans += Math.max(left[i], right[i]);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int candy(vector<int>& ratings) {
        int n = ratings.size();
        vector<int> left(n, 1);
        vector<int> right(n, 1);
        for (int i = 1; i < n; ++i) {
            if (ratings[i] > ratings[i - 1]) {
                left[i] = left[i - 1] + 1;
            }
        }
        for (int i = n - 2; ~i; --i) {
            if (ratings[i] > ratings[i + 1]) {
                right[i] = right[i + 1] + 1;
            }
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans += max(left[i], right[i]);
        }
        return ans;
    }
};
```

#### Go

```go
func candy(ratings []int) int {
	n := len(ratings)
	left := make([]int, n)
	right := make([]int, n)
	for i := range left {
		left[i] = 1
		right[i] = 1
	}
	for i := 1; i < n; i++ {
		if ratings[i] > ratings[i-1] {
			left[i] = left[i-1] + 1
		}
	}
	for i := n - 2; i >= 0; i-- {
		if ratings[i] > ratings[i+1] {
			right[i] = right[i+1] + 1
		}
	}
	ans := 0
	for i, a := range left {
		b := right[i]
		ans += max(a, b)
	}
	return ans
}
```

#### TypeScript

```ts
function candy(ratings: number[]): number {
    const n = ratings.length;
    const left = new Array(n).fill(1);
    const right = new Array(n).fill(1);
    for (let i = 1; i < n; ++i) {
        if (ratings[i] > ratings[i - 1]) {
            left[i] = left[i - 1] + 1;
        }
    }
    for (let i = n - 2; i >= 0; --i) {
        if (ratings[i] > ratings[i + 1]) {
            right[i] = right[i + 1] + 1;
        }
    }
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        ans += Math.max(left[i], right[i]);
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn candy(ratings: Vec<i32>) -> i32 {
        let n = ratings.len();
        let mut left = vec![1; n];
        let mut right = vec![1; n];

        for i in 1..n {
            if ratings[i] > ratings[i - 1] {
                left[i] = left[i - 1] + 1;
            }
        }

        for i in (0..n - 1).rev() {
            if ratings[i] > ratings[i + 1] {
                right[i] = right[i + 1] + 1;
            }
        }

        ratings.iter()
            .enumerate()
            .map(|(i, _)| left[i].max(right[i]) as i32)
            .sum()
    }
}
```

#### C#

```cs
public class Solution {
    public int Candy(int[] ratings) {
        int n = ratings.Length;
        int[] left = new int[n];
        int[] right = new int[n];
        Array.Fill(left, 1);
        Array.Fill(right, 1);
        for (int i = 1; i < n; ++i) {
            if (ratings[i] > ratings[i - 1]) {
                left[i] = left[i - 1] + 1;
            }
        }
        for (int i = n - 2; i >= 0; --i) {
            if (ratings[i] > ratings[i + 1]) {
                right[i] = right[i + 1] + 1;
            }
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans += Math.Max(left[i], right[i]);
        }
        return ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
