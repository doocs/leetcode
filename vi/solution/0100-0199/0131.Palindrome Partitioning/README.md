---
comments: true
difficulty: Medium
tags:
    - String
    - Dynamic Programming
    - Backtracking
---

<!-- problem:start -->

# [131. Palindrome Partitioning](https://leetcode.com/problems/palindrome-partitioning)

[中文文档](/solution/0100-0199/0131.Palindrome%20Partitioning/README.md)

## Mô tả

<!-- description:start -->

<p>Cho một chuỗi <code>s</code>, hãy phân hoạch <code>s</code> sao cho mọi <span data-keyword="substring-nonempty">chuỗi con</span> trong phân hoạch đều là <span data-keyword="palindrome-string"><strong>palindrome</strong></span>. Trả về <em>mọi cách phân hoạch palindrome có thể có của </em><code>s</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>
<pre><strong>Đầu vào:</strong> s = "aab"
<strong>Đầu ra:</strong> [["a","a","b"],["aa","b"]]
</pre><p><strong class="example">Ví dụ 2:</strong></p>
<pre><strong>Đầu vào:</strong> s = "a"
<strong>Đầu ra:</strong> [["a"]]
</pre>
<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 16</code></li>
	<li><code>s</code> chỉ chứa các chữ cái tiếng Anh viết thường.</li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Tiền xử lý + DFS (Quay lui)

<!-- thinking:start -->

> **Tư duy**
>
> Liệt kê mọi cách cắt $s$ thành các phần palindrome. Vì $n\le 16$, có nhiều nhất $2^{n-1}$ phân hoạch; quay lui là phù hợp. Việc kiểm tra palindrome ở mỗi lần cắt sẽ quét lại cùng các đoạn.
>
> Tiền xử lý $f[i][j]$ để xác định liệu $s[i..j]$ có phải là palindrome hay không, sau đó chỉ thử lần cắt tiếp theo khi $f[i][j]$ là true.

<!-- thinking:end -->

Chúng ta có thể sử dụng quy hoạch động để tiền xử lý xem có chuỗi con nào trong chuỗi là palindrome hay không, tức là $f[i][j]$ cho biết liệu chuỗi con $s[i..j]$ có phải là palindrome hay không.

Tiếp theo, chúng ta thiết kế một hàm $dfs(i)$, biểu diễn việc bắt đầu từ ký tự thứ $i$ của chuỗi và phân hoạch nó thành một số chuỗi con palindrome, với phân hoạch hiện tại là $t$.

Nếu $i = |s|$, điều đó có nghĩa là việc phân hoạch đã hoàn tất, vì vậy chúng ta thêm $t$ vào mảng kết quả rồi trả về.

Nếu không, chúng ta có thể bắt đầu từ $i$ và liệt kê vị trí kết thúc $j$ theo thứ tự từ nhỏ đến lớn. Nếu $s[i..j]$ là một palindrome, chúng ta thêm $s[i..j]$ vào $t$, rồi tiếp tục gọi đệ quy $dfs(j+1)$. Khi quay lui, chúng ta cần lấy $s[i..j]$ ra.

Độ phức tạp thời gian là $O(n \times 2^n)$, và độ phức tạp không gian là $O(n^2)$. Trong đó, $n$ là độ dài của chuỗi.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def partition(self, s: str) -> List[List[str]]:
        def dfs(i: int):
            if i == n:
                ans.append(t[:])
                return
            for j in range(i, n):
                if f[i][j]:
                    t.append(s[i : j + 1])
                    dfs(j + 1)
                    t.pop()

        n = len(s)
        f = [[True] * n for _ in range(n)]
        for i in range(n - 1, -1, -1):
            for j in range(i + 1, n):
                f[i][j] = s[i] == s[j] and f[i + 1][j - 1]
        ans = []
        t = []
        dfs(0)
        return ans
```

#### Java

```java
class Solution {
    private int n;
    private String s;
    private boolean[][] f;
    private List<String> t = new ArrayList<>();
    private List<List<String>> ans = new ArrayList<>();

    public List<List<String>> partition(String s) {
        n = s.length();
        f = new boolean[n][n];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(f[i], true);
        }
        for (int i = n - 1; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                f[i][j] = s.charAt(i) == s.charAt(j) && f[i + 1][j - 1];
            }
        }
        this.s = s;
        dfs(0);
        return ans;
    }

    private void dfs(int i) {
        if (i == s.length()) {
            ans.add(new ArrayList<>(t));
            return;
        }
        for (int j = i; j < n; ++j) {
            if (f[i][j]) {
                t.add(s.substring(i, j + 1));
                dfs(j + 1);
                t.remove(t.size() - 1);
            }
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<vector<string>> partition(string s) {
        int n = s.size();
        bool f[n][n];
        memset(f, true, sizeof(f));
        for (int i = n - 1; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                f[i][j] = s[i] == s[j] && f[i + 1][j - 1];
            }
        }
        vector<vector<string>> ans;
        vector<string> t;
        auto dfs = [&](this auto&& dfs, int i) -> void {
            if (i == n) {
                ans.emplace_back(t);
                return;
            }
            for (int j = i; j < n; ++j) {
                if (f[i][j]) {
                    t.emplace_back(s.substr(i, j - i + 1));
                    dfs(j + 1);
                    t.pop_back();
                }
            }
        };
        dfs(0);
        return ans;
    }
};
```

#### Go

```go
func partition(s string) (ans [][]string) {
	n := len(s)
	f := make([][]bool, n)
	for i := range f {
		f[i] = make([]bool, n)
		for j := range f[i] {
			f[i][j] = true
		}
	}
	for i := n - 1; i >= 0; i-- {
		for j := i + 1; j < n; j++ {
			f[i][j] = s[i] == s[j] && f[i+1][j-1]
		}
	}
	t := []string{}
	var dfs func(int)
	dfs = func(i int) {
		if i == n {
			ans = append(ans, append([]string(nil), t...))
			return
		}
		for j := i; j < n; j++ {
			if f[i][j] {
				t = append(t, s[i:j+1])
				dfs(j + 1)
				t = t[:len(t)-1]
			}
		}
	}
	dfs(0)
	return
}
```

#### TypeScript

```ts
function partition(s: string): string[][] {
    const n = s.length;
    const f: boolean[][] = Array.from({ length: n }, () => Array(n).fill(true));
    for (let i = n - 1; i >= 0; --i) {
        for (let j = i + 1; j < n; ++j) {
            f[i][j] = s[i] === s[j] && f[i + 1][j - 1];
        }
    }
    const ans: string[][] = [];
    const t: string[] = [];
    const dfs = (i: number) => {
        if (i === n) {
            ans.push(t.slice());
            return;
        }
        for (let j = i; j < n; ++j) {
            if (f[i][j]) {
                t.push(s.slice(i, j + 1));
                dfs(j + 1);
                t.pop();
            }
        }
    };
    dfs(0);
    return ans;
}
```

#### C#

```cs
public class Solution {
    private int n;
    private string s;
    private bool[,] f;
    private IList<IList<string>> ans = new List<IList<string>>();
    private IList<string> t = new List<string>();

    public IList<IList<string>> Partition(string s) {
        n = s.Length;
        this.s = s;
        f = new bool[n, n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j <= i; ++j) {
                f[i, j] = true;
            }
        }
        for (int i = n - 1; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                f[i, j] = s[i] == s[j] && f[i + 1, j - 1];
            }
        }
        dfs(0);
        return ans;
    }

    private void dfs(int i) {
        if (i == n) {
            ans.Add(new List<string>(t));
            return;
        }
        for (int j = i; j < n; ++j) {
            if (f[i, j]) {
                t.Add(s.Substring(i, j + 1 - i));
                dfs(j + 1);
                t.RemoveAt(t.Count - 1);
            }
        }
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
