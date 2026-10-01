---
comments: true
difficulty: Hard
tags:
    - Array
    - Dynamic Programming
    - Matrix
---

<!-- problem:start -->

# [174. Dungeon Game](https://leetcode.com/problems/dungeon-game)

[中文文档](/solution/0100-0199/0174.Dungeon%20Game/README.md)

## Mô tả

<!-- description:start -->

<p>Những con quỷ đã bắt cóc công chúa và giam nàng trong <strong>góc dưới bên phải</strong> của một <code>dungeon</code>. <code>dungeon</code> gồm các căn phòng kích thước <code>m x n</code> được sắp xếp trên một lưới 2D. Hiệp sĩ dũng cảm của chúng ta ban đầu đứng ở <strong>căn phòng trên cùng bên trái</strong> và phải chiến đấu vượt qua <code>dungeon</code> để giải cứu công chúa.</p>

<p>Hiệp sĩ có một lượng sinh lực ban đầu được biểu diễn bằng một số nguyên dương. Nếu tại bất kỳ thời điểm nào lượng sinh lực của anh ta giảm xuống <code>0</code> hoặc thấp hơn, anh ta sẽ chết ngay lập tức.</p>

<p>Một số căn phòng được canh giữ bởi quỷ (được biểu diễn bằng các số nguyên âm), nên hiệp sĩ mất sinh lực khi bước vào những căn phòng này; các căn phòng khác hoặc là trống (được biểu diễn bằng 0) hoặc chứa những quả cầu ma thuật làm tăng sinh lực của hiệp sĩ (được biểu diễn bằng các số nguyên dương).</p>

<p>Để đến chỗ công chúa nhanh nhất có thể, hiệp sĩ quyết định chỉ di chuyển <strong>sang phải</strong> hoặc <strong>xuống dưới</strong> trong mỗi bước.</p>

<p>Hãy trả về <em>lượng sinh lực ban đầu tối thiểu của hiệp sĩ để anh ta có thể giải cứu công chúa</em>.</p>

<p><strong>Lưu ý</strong> rằng bất kỳ căn phòng nào cũng có thể chứa mối đe dọa hoặc vật tăng sức mạnh, kể cả căn phòng đầu tiên mà hiệp sĩ bước vào và căn phòng dưới cùng bên phải nơi công chúa bị giam.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0174.Dungeon%20Game/images/dungeon-grid-1.jpg" style="width: 253px; height: 253px;" />
<pre>
<strong>Đầu vào:</strong> dungeon = [[-2,-3,3],[-5,-10,1],[10,30,-5]]
<strong>Đầu ra:</strong> 7
<strong>Giải thích:</strong> Lượng sinh lực ban đầu của hiệp sĩ phải ít nhất là 7 nếu anh ta đi theo đường đi tối ưu: RIGHT-&gt; RIGHT -&gt; DOWN -&gt; DOWN.
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> dungeon = [[0]]
<strong>Đầu ra:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>m == dungeon.length</code></li>
	<li><code>n == dungeon[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 200</code></li>
	<li><code>-1000 &lt;= dungeon[i][j] &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Quy hoạch động

<!-- thinking:start -->

> **Tư duy**
>
> Hiệp sĩ chỉ có thể di chuyển sang phải hoặc xuống dưới và phải duy trì ít nhất $1$ HP; ta cần lượng HP ban đầu nhỏ nhất. Việc liệt kê các đường đi trên lưới $200\times 200$ là bất khả thi. Lượng HP cần thiết ở phía trước quyết định yêu cầu tại ô hiện tại, vì vậy ta làm ngược từ chỗ công chúa: lấy lượng cần ở ô tiếp theo trừ đi giá trị của ô hiện tại, sau đó lấy ít nhất là $1$. Chọn lối ra có yêu cầu nhỏ hơn trong hai lối ra.

<!-- thinking:end -->

Ta định nghĩa $dp[i][j]$ là giá trị ban đầu tối thiểu cần có để đi từ $(i, j)$ đến điểm cuối. Giá trị của $dp[i][j]$ có thể được tính từ $dp[i+1][j]$ và $dp[i][j+1]$, cụ thể là:

$$
dp[i][j] = \max(\min(dp[i+1][j], dp[i][j+1]) - dungeon[i][j], 1)
$$

Ban đầu, cả $dp[m][n-1]$ và $dp[m-1][n]$ đều bằng $1$, còn giá trị tại các vị trí khác là giá trị lớn nhất.

Độ phức tạp thời gian là $O(m \times n)$, và độ phức tạp không gian là $O(m \times n)$. Trong đó $m$ và $n$ lần lượt là số hàng và số cột của dungeon.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def calculateMinimumHP(self, dungeon: List[List[int]]) -> int:
        m, n = len(dungeon), len(dungeon[0])
        dp = [[inf] * (n + 1) for _ in range(m + 1)]
        dp[m][n - 1] = dp[m - 1][n] = 1
        for i in range(m - 1, -1, -1):
            for j in range(n - 1, -1, -1):
                dp[i][j] = max(1, min(dp[i + 1][j], dp[i][j + 1]) - dungeon[i][j])
        return dp[0][0]
```

#### Java

```java
class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length, n = dungeon[0].length;
        int[][] dp = new int[m + 1][n + 1];
        for (var e : dp) {
            Arrays.fill(e, 1 << 30);
        }
        dp[m][n - 1] = dp[m - 1][n] = 1;
        for (int i = m - 1; i >= 0; --i) {
            for (int j = n - 1; j >= 0; --j) {
                dp[i][j] = Math.max(1, Math.min(dp[i + 1][j], dp[i][j + 1]) - dungeon[i][j]);
            }
        }
        return dp[0][0];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int calculateMinimumHP(vector<vector<int>>& dungeon) {
        int m = dungeon.size(), n = dungeon[0].size();
        int dp[m + 1][n + 1];
        memset(dp, 0x3f, sizeof dp);
        dp[m][n - 1] = dp[m - 1][n] = 1;
        for (int i = m - 1; ~i; --i) {
            for (int j = n - 1; ~j; --j) {
                dp[i][j] = max(1, min(dp[i + 1][j], dp[i][j + 1]) - dungeon[i][j]);
            }
        }
        return dp[0][0];
    }
};
```

#### Go

```go
func calculateMinimumHP(dungeon [][]int) int {
	m, n := len(dungeon), len(dungeon[0])
	dp := make([][]int, m+1)
	for i := range dp {
		dp[i] = make([]int, n+1)
		for j := range dp[i] {
			dp[i][j] = 1 << 30
		}
	}
	dp[m][n-1], dp[m-1][n] = 1, 1
	for i := m - 1; i >= 0; i-- {
		for j := n - 1; j >= 0; j-- {
			dp[i][j] = max(1, min(dp[i+1][j], dp[i][j+1])-dungeon[i][j])
		}
	}
	return dp[0][0]
}
```

#### C#

```cs
public class Solution {
    public int CalculateMinimumHP(int[][] dungeon) {
        int m = dungeon.Length, n = dungeon[0].Length;
        int[][] dp = new int[m + 1][];
        for (int i = 0; i < m + 1; ++i) {
            dp[i] = new int[n + 1];
            Array.Fill(dp[i], 1 << 30);
        }
        dp[m][n - 1] = dp[m - 1][n] = 1;
        for (int i = m - 1; i >= 0; --i) {
            for (int j = n - 1; j >= 0; --j) {
                dp[i][j] = Math.Max(1, Math.Min(dp[i + 1][j], dp[i][j + 1]) - dungeon[i][j]);
            }
        }
        return dp[0][0];
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
