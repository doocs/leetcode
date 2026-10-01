---
comments: true
difficulty: Hard
tags:
    - Backtracking
    - Algorithm X
---

<!-- problem:start -->

# [52. N-Queens II](https://leetcode.com/problems/n-queens-ii)

[中文文档](/solution/0000-0099/0052.N-Queens%20II/README.md)

## Mô tả

<!-- description:start -->

<p>Bài toán <strong>n-queens</strong> là bài toán đặt <code>n</code> quân hậu lên một bàn cờ <code>n x n</code> sao cho không có hai quân hậu nào tấn công lẫn nhau.</p>

<p>Cho một số nguyên <code>n</code>, hãy trả về <em>số lượng lời giải khác nhau của <strong>bài toán n-queens</strong></em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0052.N-Queens%20II/images/queens.jpg" style="width: 600px; height: 268px;" />
<pre>
<strong>Đầu vào:</strong> n = 4
<strong>Đầu ra:</strong> 2
<strong>Giải thích:</strong> Có hai lời giải khác nhau cho bài toán 4 quân hậu như hình minh họa.
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> n = 1
<strong>Đầu ra:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 9</code></li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Quay lui

<!-- thinking:start -->

> **Tư duy**
>
> Ý tưởng đầu tiên là tạo mọi bàn cờ như trong bài N-Queens rồi đếm chúng. $n \le 9$ vẫn đáp ứng được giới hạn, nhưng bài toán này chỉ yêu cầu số lượng, nên việc tạo chuỗi và sao chép lưới là công việc thừa.
>
> Phần lãng phí nằm ở việc vẽ các lời giải. Ràng buộc giống N-Queens: mỗi hàng có một quân hậu, không có hai quân cùng cột hoặc cùng đường chéo. Cột, đường chéo chính và đường chéo phụ vẫn có thể được đánh dấu trong $O(1)$.
>
> Vì vậy, chúng ta quay lui theo từng hàng như cũ, và khi đến hàng $n$ thì chỉ tăng đáp án lên một.

<!-- thinking:end -->

Chúng ta thiết kế một hàm $dfs(i)$, biểu thị việc bắt đầu tìm kiếm từ hàng thứ $i$, và kết quả của lần tìm kiếm được cộng vào đáp án.

Trong hàng thứ $i$, chúng ta liệt kê từng cột của hàng thứ $i$. Nếu cột hiện tại không xung đột với các quân hậu đã đặt trước đó, chúng ta có thể đặt một quân hậu, sau đó tiếp tục tìm kiếm ở hàng tiếp theo, tức là gọi $dfs(i + 1)$.

Nếu xảy ra xung đột, chúng ta bỏ qua cột hiện tại và tiếp tục liệt kê cột tiếp theo.

Để xác định có xảy ra xung đột hay không, chúng ta cần sử dụng ba mảng để ghi nhận lần lượt việc một quân hậu đã được đặt trong mỗi cột, mỗi đường chéo dương và mỗi đường chéo âm hay chưa.

Cụ thể, chúng ta sử dụng mảng $cols$ để ghi nhận việc một quân hậu đã được đặt trong mỗi cột hay chưa, mảng $dg$ để ghi nhận việc một quân hậu đã được đặt trong mỗi đường chéo dương hay chưa, và mảng $udg$ để ghi nhận việc một quân hậu đã được đặt trong mỗi đường chéo âm hay chưa.

Độ phức tạp thời gian là $O(n!)$, và độ phức tạp không gian là $O(n)$. Trong đó, $n$ là số lượng quân hậu.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def totalNQueens(self, n: int) -> int:
        def dfs(i: int):
            if i == n:
                nonlocal ans
                ans += 1
                return
            for j in range(n):
                a, b = i + j, i - j + n
                if cols[j] or dg[a] or udg[b]:
                    continue
                cols[j] = dg[a] = udg[b] = True
                dfs(i + 1)
                cols[j] = dg[a] = udg[b] = False

        cols = [False] * 10
        dg = [False] * 20
        udg = [False] * 20
        ans = 0
        dfs(0)
        return ans
```

#### Java

```java
class Solution {
    private int n;
    private int ans;
    private boolean[] cols = new boolean[10];
    private boolean[] dg = new boolean[20];
    private boolean[] udg = new boolean[20];

    public int totalNQueens(int n) {
        this.n = n;
        dfs(0);
        return ans;
    }

    private void dfs(int i) {
        if (i == n) {
            ++ans;
            return;
        }
        for (int j = 0; j < n; ++j) {
            int a = i + j, b = i - j + n;
            if (cols[j] || dg[a] || udg[b]) {
                continue;
            }
            cols[j] = true;
            dg[a] = true;
            udg[b] = true;
            dfs(i + 1);
            cols[j] = false;
            dg[a] = false;
            udg[b] = false;
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    int totalNQueens(int n) {
        bitset<10> cols;
        bitset<20> dg;
        bitset<20> udg;
        int ans = 0;
        function<void(int)> dfs = [&](int i) {
            if (i == n) {
                ++ans;
                return;
            }
            for (int j = 0; j < n; ++j) {
                int a = i + j, b = i - j + n;
                if (cols[j] || dg[a] || udg[b]) continue;
                cols[j] = dg[a] = udg[b] = 1;
                dfs(i + 1);
                cols[j] = dg[a] = udg[b] = 0;
            }
        };
        dfs(0);
        return ans;
    }
};
```

#### Go

```go
func totalNQueens(n int) (ans int) {
	cols := [10]bool{}
	dg := [20]bool{}
	udg := [20]bool{}
	var dfs func(int)
	dfs = func(i int) {
		if i == n {
			ans++
			return
		}
		for j := 0; j < n; j++ {
			a, b := i+j, i-j+n
			if cols[j] || dg[a] || udg[b] {
				continue
			}
			cols[j], dg[a], udg[b] = true, true, true
			dfs(i + 1)
			cols[j], dg[a], udg[b] = false, false, false
		}
	}
	dfs(0)
	return
}
```

#### TypeScript

```ts
function totalNQueens(n: number): number {
    const cols: boolean[] = Array(10).fill(false);
    const dg: boolean[] = Array(20).fill(false);
    const udg: boolean[] = Array(20).fill(false);
    let ans = 0;
    const dfs = (i: number) => {
        if (i === n) {
            ++ans;
            return;
        }
        for (let j = 0; j < n; ++j) {
            let [a, b] = [i + j, i - j + n];
            if (cols[j] || dg[a] || udg[b]) {
                continue;
            }
            cols[j] = dg[a] = udg[b] = true;
            dfs(i + 1);
            cols[j] = dg[a] = udg[b] = false;
        }
    };
    dfs(0);
    return ans;
}
```

#### JavaScript

```js
function totalNQueens(n) {
    const cols = Array(10).fill(false);
    const dg = Array(20).fill(false);
    const udg = Array(20).fill(false);
    let ans = 0;
    const dfs = i => {
        if (i === n) {
            ++ans;
            return;
        }
        for (let j = 0; j < n; ++j) {
            let [a, b] = [i + j, i - j + n];
            if (cols[j] || dg[a] || udg[b]) {
                continue;
            }
            cols[j] = dg[a] = udg[b] = true;
            dfs(i + 1);
            cols[j] = dg[a] = udg[b] = false;
        }
    };
    dfs(0);
    return ans;
}
```

#### C#

```cs
public class Solution {
    public int TotalNQueens(int n) {
        bool[] cols = new bool[10];
        bool[] dg = new bool[20];
        bool[] udg = new bool[20];
        int ans = 0;

        void dfs(int i) {
            if (i == n) {
                ans++;
                return;
            }
            for (int j = 0; j < n; j++) {
                int a = i + j, b = i - j + n;
                if (cols[j] || dg[a] || udg[b]) {
                    continue;
                }
                cols[j] = dg[a] = udg[b] = true;
                dfs(i + 1);
                cols[j] = dg[a] = udg[b] = false;
            }
        }

        dfs(0);
        return ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
