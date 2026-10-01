---
comments: true
difficulty: Medium
tags:
    - String
    - Dynamic Programming
    - Backtracking
    - Parentheses
---

<!-- problem:start -->

# [22. Generate Parentheses](https://leetcode.com/problems/generate-parentheses)

[中文文档](/solution/0000-0099/0022.Generate%20Parentheses/README.md)

## Mô tả

<!-- description:start -->

<p>Cho <code>n</code> cặp dấu ngoặc, hãy viết một hàm để <em>tạo ra tất cả các kết hợp dấu ngoặc đúng</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>
<pre><strong>Đầu vào:</strong> n = 3
<strong>Đầu ra:</strong> ["((()))","(()())","(())()","()(())","()()()"]
</pre><p><strong class="example">Ví dụ 2:</strong></p>
<pre><strong>Đầu vào:</strong> n = 1
<strong>Đầu ra:</strong> ["()"]
</pre>
<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 8</code></li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: DFS + Cắt tỉa

<!-- thinking:start -->

> **Tư duy**
>
> Ý tưởng đầu tiên là liệt kê mọi chuỗi có độ dài $2n$ và giữ lại những chuỗi hợp lệ. Với $n \le 8$, ta có $2^{16}$ ứng viên, nên cách này sẽ vượt qua được, nhưng hầu hết các tiền tố đã không hợp lệ ngay từ đầu.
>
> Điểm nghẽn nằm ở việc tạo rồi kiểm tra: một khi một tiền tố có số lượng `)` nhiều hơn `(`, hoặc một trong hai số đếm vượt quá $n$, thì không có hậu tố nào có thể cứu vãn nó.
>
> Một tiền tố hợp lệ chỉ cần hai số đếm $l$ và $r$: duy trì $l \ge r$ và cả hai đều không vượt quá $n$. Khi $l=r=n$, ghi lại chuỗi.
>
> Vì vậy, DFS thử thêm `(` hoặc `)` và cắt tỉa dựa trên ba bất đẳng thức đó. Cây tìm kiếm nhỏ hơn nhiều so với việc liệt kê toàn bộ; không gian bổ sung chỉ là chuỗi hiện tại, $O(n)$.

<!-- thinking:end -->

Miền giá trị của $n$ trong đề bài là $[1, 8]$, vì vậy chúng ta có thể trực tiếp giải bài toán này bằng "tìm kiếm vét cạn + cắt tỉa".

Chúng ta thiết kế một hàm $dfs(l, r, t)$, trong đó $l$ và $r$ lần lượt biểu thị số lượng ngoặc trái và ngoặc phải, còn $t$ biểu thị chuỗi ngoặc hiện tại. Khi đó, ta có thể nhận được cấu trúc đệ quy như sau:

- Nếu $l \gt n$ hoặc $r \gt n$ hoặc $l \lt r$, thì tổ hợp ngoặc hiện tại $t$ không hợp lệ, trả về trực tiếp;
- Nếu $l = n$ và $r = n$, thì tổ hợp ngoặc hiện tại $t$ hợp lệ, thêm nó vào mảng kết quả `ans`, rồi trả về trực tiếp;
- Ta có thể chọn thêm một ngoặc trái, rồi thực hiện đệ quy `dfs(l + 1, r, t + "(")`;
- Ta cũng có thể chọn thêm một ngoặc phải, rồi thực hiện đệ quy `dfs(l, r + 1, t + ")")`.

Độ phức tạp thời gian là $O(2^{n\times 2} \times n)$, và độ phức tạp không gian là $O(n)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def generateParenthesis(self, n: int) -> List[str]:
        def dfs(l: int, r: int, t: str):
            if l > n or r > n or l < r:
                return
            if l == n and r == n:
                ans.append(t)
                return
            dfs(l + 1, r, t + "(")
            dfs(l, r + 1, t + ")")

        ans = []
        dfs(0, 0, "")
        return ans
```

#### Java

```java
class Solution {
    private List<String> ans = new ArrayList<>();
    private int n;

    public List<String> generateParenthesis(int n) {
        this.n = n;
        dfs(0, 0, "");
        return ans;
    }

    private void dfs(int l, int r, String t) {
        if (l > n || r > n || l < r) {
            return;
        }
        if (l == n && r == n) {
            ans.add(t);
            return;
        }
        dfs(l + 1, r, t + "(");
        dfs(l, r + 1, t + ")");
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<string> generateParenthesis(int n) {
        vector<string> ans;
        auto dfs = [&](this auto&& dfs, int l, int r, string t) -> void {
            if (l > n || r > n || l < r) {
                return;
            }
            if (l == n && r == n) {
                ans.push_back(t);
                return;
            }
            dfs(l + 1, r, t + "(");
            dfs(l, r + 1, t + ")");
        };
        dfs(0, 0, "");
        return ans;
    }
};
```

#### Go

```go
func generateParenthesis(n int) (ans []string) {
	var dfs func(int, int, string)
	dfs = func(l, r int, t string) {
		if l > n || r > n || l < r {
			return
		}
		if l == n && r == n {
			ans = append(ans, t)
			return
		}
		dfs(l+1, r, t+"(")
		dfs(l, r+1, t+")")
	}
	dfs(0, 0, "")
	return ans
}
```

#### TypeScript

```ts
function generateParenthesis(n: number): string[] {
    const dfs = (l: number, r: number, t: string) => {
        if (l > n || r > n || l < r) {
            return;
        }
        if (l == n && r == n) {
            ans.push(t);
            return;
        }
        dfs(l + 1, r, t + '(');
        dfs(l, r + 1, t + ')');
    };
    const ans: string[] = [];
    dfs(0, 0, '');
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn generate_parenthesis(n: i32) -> Vec<String> {
        let mut ans = Vec::new();

        fn dfs(ans: &mut Vec<String>, l: i32, r: i32, t: String, n: i32) {
            if l > n || r > n || l < r {
                return;
            }
            if l == n && r == n {
                ans.push(t);
                return;
            }
            dfs(ans, l + 1, r, format!("{}(", t), n);
            dfs(ans, l, r + 1, format!("{})", t), n);
        }

        dfs(&mut ans, 0, 0, String::new(), n);
        ans
    }
}
```

#### JavaScript

```js
/**
 * @param {number} n
 * @return {string[]}
 */
var generateParenthesis = function (n) {
    const dfs = (l, r, t) => {
        if (l > n || r > n || l < r) {
            return;
        }
        if (l == n && r == n) {
            ans.push(t);
            return;
        }
        dfs(l + 1, r, t + '(');
        dfs(l, r + 1, t + ')');
    };
    const ans = [];
    dfs(0, 0, '');
    return ans;
};
```

#### C#

```cs
public class Solution {
    private List<string> ans = new List<string>();
    private int n;

    public List<string> GenerateParenthesis(int n) {
        this.n = n;
        Dfs(0, 0, "");
        return ans;
    }

    private void Dfs(int l, int r, string t) {
        if (l > n || r > n || l < r) {
            return;
        }
        if (l == n && r == n) {
            ans.Add(t);
            return;
        }
        Dfs(l + 1, r, t + "(");
        Dfs(l, r + 1, t + ")");
    }
}
```

#### PHP

```php
class Solution {
    private $ans = [];
    private $n = 0;

    /**
     * @param Integer $n
     * @return String[]
     */
    function generateParenthesis($n) {
        $this->n = $n;
        $this->ans = [];
        $this->dfs(0, 0, '');
        return $this->ans;
    }

    private function dfs($l, $r, $t) {
        if ($l > $this->n || $r > $this->n || $l < $r) {
            return;
        }
        if ($l == $this->n && $r == $this->n) {
            $this->ans[] = $t;
            return;
        }
        $this->dfs($l + 1, $r, $t . '(');
        $this->dfs($l, $r + 1, $t . ')');
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
