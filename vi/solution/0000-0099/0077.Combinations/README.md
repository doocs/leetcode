---
comments: true
difficulty: Medium
tags:
    - Backtracking
---

<!-- problem:start -->

# [77. Combinations](https://leetcode.com/problems/combinations)

[中文文档](/solution/0000-0099/0077.Combinations/README.md)

## Mô tả

<!-- description:start -->

<p>Cho hai số nguyên <code>n</code> và <code>k</code>, hãy trả về <em>tất cả các tổ hợp có thể gồm</em> <code>k</code> <em>số được chọn từ phạm vi</em> <code>[1, n]</code>.</p>

<p>Bạn có thể trả về đáp án theo <strong>bất kỳ thứ tự nào</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong> n = 4, k = 2
<strong>Đầu ra:</strong> [[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]
<strong>Giải thích:</strong> Có tổng cộng 4 choose 2 = 6 tổ hợp.
Lưu ý rằng các tổ hợp không có thứ tự, tức là [1,2] và [2,1] được coi là cùng một tổ hợp.
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> n = 1, k = 1
<strong>Đầu ra:</strong> [[1]]
<strong>Giải thích:</strong> Có tổng cộng 1 choose 1 = 1 tổ hợp.
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 20</code></li>
	<li><code>1 &lt;= k &lt;= n</code></li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Quay lui (Hai cách)

<!-- thinking:start -->

> **Tư duy**
>
> Ý tưởng đầu tiên là liệt kê tất cả $2^n$ tập con của $\{1,\ldots,n\}$, rồi giữ lại những tập có độ dài $k$. Với $n \le 20$, cách này có thể vượt qua, nhưng phần lớn các tập con có kích thước không đúng.
>
> Thứ tự không quan trọng; mỗi số hoặc được chọn hoặc không, và chúng ta có thể dừng một nhánh khi độ dài của nó đạt $k$. Vì vậy, $dfs(i)$ xử lý số $i$: thêm vào, đệ quy với $i+1$, xóa ra, rồi bỏ qua. Việc liệt kê $j$ tiếp theo trong các số còn lại tạo thành cây kia; cả hai đều cho cùng các tổ hợp. Đoạn code dưới đây dùng dạng thứ nhất.

<!-- thinking:end -->

Chúng ta thiết kế một hàm $dfs(i)$, biểu diễn việc bắt đầu tìm kiếm từ số $i$, với đường đi tìm kiếm hiện tại là $t$ và đáp án là $ans$.

Logic thực thi của hàm $dfs(i)$ như sau:

- Nếu độ dài của đường đi tìm kiếm hiện tại $t$ bằng $k$, thêm đường đi tìm kiếm hiện tại vào đáp án rồi trả về.
- Nếu $i \gt n$, điều đó có nghĩa là quá trình tìm kiếm đã kết thúc, hãy trả về.
- Nếu không, chúng ta có thể chọn thêm số $i$ vào đường đi tìm kiếm $t$, sau đó tiếp tục tìm kiếm, tức là thực thi $dfs(i + 1)$, rồi xóa số $i$ khỏi đường đi tìm kiếm $t$; hoặc không thêm số $i$ vào đường đi tìm kiếm $t$ mà trực tiếp thực thi $dfs(i + 1)$.

Cách làm trên thực chất là liệt kê việc chọn hoặc không chọn số hiện tại, sau đó đệ quy tìm kiếm số tiếp theo. Chúng ta cũng có thể liệt kê số $j$ tiếp theo cần chọn, trong đó $i \leq j \leq n$. Nếu số tiếp theo được chọn là $j$, chúng ta thêm số $j$ vào đường đi tìm kiếm $t$, sau đó tiếp tục tìm kiếm, tức là thực thi $dfs(j + 1)$, rồi xóa số $j$ khỏi đường đi tìm kiếm $t$.

Trong hàm chính, chúng ta bắt đầu tìm kiếm từ số $1$, tức là thực thi $dfs(1)$.

Độ phức tạp thời gian là $(C_n^k \times k)$, và độ phức tạp không gian là $O(k)$. Trong đó, $C_n^k$ biểu diễn số tổ hợp.

Các bài tương tự:

- [39. Combination Sum](https://github.com/doocs/leetcode/blob/main/solution/0000-0099/0039.Combination%20Sum/README_EN.md)
- [40. Combination Sum II](https://github.com/doocs/leetcode/blob/main/solution/0000-0099/0040.Combination%20Sum%20II/README_EN.md)
- [216. Combination Sum III](https://github.com/doocs/leetcode/blob/main/solution/0200-0299/0216.Combination%20Sum%20III/README_EN.md)

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def combine(self, n: int, k: int) -> List[List[int]]:
        def dfs(i: int):
            if len(t) == k:
                ans.append(t[:])
                return
            if i > n:
                return
            t.append(i)
            dfs(i + 1)
            t.pop()
            dfs(i + 1)

        ans = []
        t = []
        dfs(1)
        return ans
```

#### Java

```java
class Solution {
    private List<List<Integer>> ans = new ArrayList<>();
    private List<Integer> t = new ArrayList<>();
    private int n;
    private int k;

    public List<List<Integer>> combine(int n, int k) {
        this.n = n;
        this.k = k;
        dfs(1);
        return ans;
    }

    private void dfs(int i) {
        if (t.size() == k) {
            ans.add(new ArrayList<>(t));
            return;
        }
        if (i > n) {
            return;
        }
        t.add(i);
        dfs(i + 1);
        t.remove(t.size() - 1);
        dfs(i + 1);
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<vector<int>> combine(int n, int k) {
        vector<vector<int>> ans;
        vector<int> t;
        function<void(int)> dfs = [&](int i) {
            if (t.size() == k) {
                ans.emplace_back(t);
                return;
            }
            if (i > n) {
                return;
            }
            t.emplace_back(i);
            dfs(i + 1);
            t.pop_back();
            dfs(i + 1);
        };
        dfs(1);
        return ans;
    }
};
```

#### Go

```go
func combine(n int, k int) (ans [][]int) {
	t := []int{}
	var dfs func(int)
	dfs = func(i int) {
		if len(t) == k {
			ans = append(ans, slices.Clone(t))
			return
		}
		if i > n {
			return
		}
		t = append(t, i)
		dfs(i + 1)
		t = t[:len(t)-1]
		dfs(i + 1)
	}
	dfs(1)
	return
}
```

#### TypeScript

```ts
function combine(n: number, k: number): number[][] {
    const ans: number[][] = [];
    const t: number[] = [];
    const dfs = (i: number) => {
        if (t.length === k) {
            ans.push(t.slice());
            return;
        }
        if (i > n) {
            return;
        }
        t.push(i);
        dfs(i + 1);
        t.pop();
        dfs(i + 1);
    };
    dfs(1);
    return ans;
}
```

#### Rust

```rust
impl Solution {
    fn dfs(i: i32, n: i32, k: i32, t: &mut Vec<i32>, ans: &mut Vec<Vec<i32>>) {
        if t.len() == (k as usize) {
            ans.push(t.clone());
            return;
        }
        if i > n {
            return;
        }
        t.push(i);
        Self::dfs(i + 1, n, k, t, ans);
        t.pop();
        Self::dfs(i + 1, n, k, t, ans);
    }

    pub fn combine(n: i32, k: i32) -> Vec<Vec<i32>> {
        let mut ans = vec![];
        Self::dfs(1, n, k, &mut vec![], &mut ans);
        ans
    }
}
```

#### C#

```cs
public class Solution {
    private List<IList<int>> ans = new List<IList<int>>();
    private List<int> t = new List<int>();
    private int n;
    private int k;

    public IList<IList<int>> Combine(int n, int k) {
        this.n = n;
        this.k = k;
        dfs(1);
        return ans;
    }

    private void dfs(int i) {
        if (t.Count == k) {
            ans.Add(new List<int>(t));
            return;
        }
        if (i > n) {
            return;
        }
        t.Add(i);
        dfs(i + 1);
        t.RemoveAt(t.Count - 1);
        dfs(i + 1);
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Lời giải 2

<!-- thinking:start -->

> **Tư duy**
>
> Phương pháp 1 đi qua cây chọn/bỏ qua có độ sâu $n$; các nhánh bỏ qua vẫn quét phần còn lại của các số. Phương pháp 2 liệt kê $j \in [i,n]$ được chọn tiếp theo, vì vậy độ sâu đệ quy bằng số lượng phần tử đã chọn, và cây gần với các tổ hợp độ dài $k$ hơn. Đáp án giống nhau; vòng lặp chỉ triển khai các ứng viên còn lại.

<!-- thinking:end -->

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def combine(self, n: int, k: int) -> List[List[int]]:
        def dfs(i: int):
            if len(t) == k:
                ans.append(t[:])
                return
            if i > n:
                return
            for j in range(i, n + 1):
                t.append(j)
                dfs(j + 1)
                t.pop()

        ans = []
        t = []
        dfs(1)
        return ans
```

#### Java

```java
class Solution {
    private List<List<Integer>> ans = new ArrayList<>();
    private List<Integer> t = new ArrayList<>();
    private int n;
    private int k;

    public List<List<Integer>> combine(int n, int k) {
        this.n = n;
        this.k = k;
        dfs(1);
        return ans;
    }

    private void dfs(int i) {
        if (t.size() == k) {
            ans.add(new ArrayList<>(t));
            return;
        }
        if (i > n) {
            return;
        }
        for (int j = i; j <= n; ++j) {
            t.add(j);
            dfs(j + 1);
            t.remove(t.size() - 1);
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<vector<int>> combine(int n, int k) {
        vector<vector<int>> ans;
        vector<int> t;
        function<void(int)> dfs = [&](int i) {
            if (t.size() == k) {
                ans.emplace_back(t);
                return;
            }
            if (i > n) {
                return;
            }
            for (int j = i; j <= n; ++j) {
                t.emplace_back(j);
                dfs(j + 1);
                t.pop_back();
            }
        };
        dfs(1);
        return ans;
    }
};
```

#### Go

```go
func combine(n int, k int) (ans [][]int) {
	t := []int{}
	var dfs func(int)
	dfs = func(i int) {
		if len(t) == k {
			ans = append(ans, slices.Clone(t))
			return
		}
		if i > n {
			return
		}
		for j := i; j <= n; j++ {
			t = append(t, j)
			dfs(j + 1)
			t = t[:len(t)-1]
		}
	}
	dfs(1)
	return
}
```

#### TypeScript

```ts
function combine(n: number, k: number): number[][] {
    const ans: number[][] = [];
    const t: number[] = [];
    const dfs = (i: number) => {
        if (t.length === k) {
            ans.push(t.slice());
            return;
        }
        if (i > n) {
            return;
        }
        for (let j = i; j <= n; ++j) {
            t.push(j);
            dfs(j + 1);
            t.pop();
        }
    };
    dfs(1);
    return ans;
}
```

#### Rust

```rust
impl Solution {
    fn dfs(i: i32, n: i32, k: i32, t: &mut Vec<i32>, ans: &mut Vec<Vec<i32>>) {
        if t.len() == (k as usize) {
            ans.push(t.clone());
            return;
        }
        if i > n {
            return;
        }
        for j in i..=n {
            t.push(j);
            Self::dfs(j + 1, n, k, t, ans);
            t.pop();
        }
    }

    pub fn combine(n: i32, k: i32) -> Vec<Vec<i32>> {
        let mut ans = vec![];
        Self::dfs(1, n, k, &mut vec![], &mut ans);
        ans
    }
}
```

#### C#

```cs
public class Solution {
    private List<IList<int>> ans = new List<IList<int>>();
    private List<int> t = new List<int>();
    private int n;
    private int k;

    public IList<IList<int>> Combine(int n, int k) {
        this.n = n;
        this.k = k;
        dfs(1);
        return ans;
    }

    private void dfs(int i) {
        if (t.Count == k) {
            ans.Add(new List<int>(t));
            return;
        }
        if (i > n) {
            return;
        }
        for (int j = i; j <= n; ++j) {
            t.Add(j);
            dfs(j + 1);
            t.RemoveAt(t.Count - 1);
        }
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
