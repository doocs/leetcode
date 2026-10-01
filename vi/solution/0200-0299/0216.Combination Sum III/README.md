---
comments: true
difficulty: Medium
tags:
    - Array
    - Backtracking
---

<!-- problem:start -->

# [216. Combination Sum III](https://leetcode.com/problems/combination-sum-iii)

[中文文档](/solution/0200-0299/0216.Combination%20Sum%20III/README.md)

## Mô tả

<!-- description:start -->

<p>Tìm tất cả các tổ hợp hợp lệ gồm <code>k</code> số có tổng bằng <code>n</code> sao cho thỏa mãn các điều kiện sau:</p>

<ul>
	<li>Chỉ sử dụng các số từ <code>1</code> đến <code>9</code>.</li>
	<li>Mỗi số được sử dụng <strong>nhiều nhất một lần</strong>.</li>
</ul>

<p>Trả về <em>danh sách tất cả các tổ hợp hợp lệ có thể có</em>. Danh sách không được chứa cùng một tổ hợp hai lần và các tổ hợp có thể được trả về theo bất kỳ thứ tự nào.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong> k = 3, n = 7
<strong>Đầu ra:</strong> [[1,2,4]]
<strong>Giải thích:</strong>
1 + 2 + 4 = 7
Không có tổ hợp hợp lệ nào khác.</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> k = 3, n = 9
<strong>Đầu ra:</strong> [[1,2,6],[1,3,5],[2,3,4]]
<strong>Giải thích:</strong>
1 + 2 + 6 = 9
1 + 3 + 5 = 9
2 + 3 + 4 = 9
Không có tổ hợp hợp lệ nào khác.
</pre>

<p><strong class="example">Ví dụ 3:</strong></p>

<pre>
<strong>Đầu vào:</strong> k = 4, n = 1
<strong>Đầu ra:</strong> []
<strong>Giải thích:</strong> Không có tổ hợp hợp lệ nào.
Khi sử dụng 4 số khác nhau trong phạm vi [1,9], tổng nhỏ nhất có thể nhận được là 1+2+3+4 = 10 và vì 10 &gt; 1 nên không có tổ hợp hợp lệ nào.
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>2 &lt;= k &lt;= 9</code></li>
	<li><code>1 &lt;= n &lt;= 60</code></li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Cắt tỉa + Quay lui (Hai cách tiếp cận)

<!-- thinking:start -->

> **Tư duy**
>
> Chúng ta chọn $k$ số từ $1$ đến $9$ có tổng bằng $n$. Tập ứng viên rất nhỏ, nên chúng ta có thể liệt kê. Quyết định chọn hoặc bỏ qua theo thứ tự tăng dần, đồng thời cắt tỉa khi tổng còn lại, số lượng phần tử hoặc số tiếp theo không hợp lệ.
>
> $dfs(i,s)$ xét số nguyên $i$ với tổng còn lại $s$: chọn nó bằng cách gọi $dfs(i+1,s-i)$ hoặc bỏ qua bằng cách gọi $dfs(i+1,s)$.

<!-- thinking:end -->

Chúng ta thiết kế một hàm $dfs(i, s)$, biểu diễn việc hiện đang liệt kê số $i$ và vẫn còn các số có tổng bằng $s$ cần được liệt kê. Đường đi tìm kiếm hiện tại là $t$, còn đáp án là $ans$.

Logic thực thi của hàm $dfs(i, s)$ như sau:

Cách một:

- Nếu $s = 0$ và độ dài đường đi tìm kiếm hiện tại $t$ là $k$, điều đó có nghĩa là đã tìm được một nhóm đáp án. Thêm $t$ vào $ans$, sau đó trả về.
- Nếu $i \gt 9$ hoặc $i \gt s$ hoặc độ dài đường đi tìm kiếm hiện tại $t$ lớn hơn $k$, điều đó có nghĩa là đường đi tìm kiếm hiện tại không thể là đáp án, nên trả về ngay.
- Nếu không, chúng ta có thể chọn thêm số $i$ vào đường đi tìm kiếm $t$, sau đó tiếp tục tìm kiếm, tức là thực thi $dfs(i + 1, s - i)$. Sau khi tìm kiếm xong, xóa $i$ khỏi đường đi tìm kiếm $t$; chúng ta cũng có thể chọn không thêm số $i$ vào đường đi tìm kiếm $t$ và thực thi trực tiếp $dfs(i + 1, s)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def combinationSum3(self, k: int, n: int) -> List[List[int]]:
        def dfs(i: int, s: int):
            if s == 0:
                if len(t) == k:
                    ans.append(t[:])
                return
            if i > 9 or i > s or len(t) >= k:
                return
            t.append(i)
            dfs(i + 1, s - i)
            t.pop()
            dfs(i + 1, s)

        ans = []
        t = []
        dfs(1, n)
        return ans
```

#### Java

```java
class Solution {
    private List<List<Integer>> ans = new ArrayList<>();
    private List<Integer> t = new ArrayList<>();
    private int k;

    public List<List<Integer>> combinationSum3(int k, int n) {
        this.k = k;
        dfs(1, n);
        return ans;
    }

    private void dfs(int i, int s) {
        if (s == 0) {
            if (t.size() == k) {
                ans.add(new ArrayList<>(t));
            }
            return;
        }
        if (i > 9 || i > s || t.size() >= k) {
            return;
        }
        t.add(i);
        dfs(i + 1, s - i);
        t.remove(t.size() - 1);
        dfs(i + 1, s);
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<vector<int>> combinationSum3(int k, int n) {
        vector<vector<int>> ans;
        vector<int> t;
        function<void(int, int)> dfs = [&](int i, int s) {
            if (s == 0) {
                if (t.size() == k) {
                    ans.emplace_back(t);
                }
                return;
            }
            if (i > 9 || i > s || t.size() >= k) {
                return;
            }
            t.emplace_back(i);
            dfs(i + 1, s - i);
            t.pop_back();
            dfs(i + 1, s);
        };
        dfs(1, n);
        return ans;
    }
};
```

#### Go

```go
func combinationSum3(k int, n int) (ans [][]int) {
	t := []int{}
	var dfs func(i, s int)
	dfs = func(i, s int) {
		if s == 0 {
			if len(t) == k {
				ans = append(ans, slices.Clone(t))
			}
			return
		}
		if i > 9 || i > s || len(t) >= k {
			return
		}
		t = append(t, i)
		dfs(i+1, s-i)
		t = t[:len(t)-1]
		dfs(i+1, s)
	}
	dfs(1, n)
	return
}
```

#### TypeScript

```ts
function combinationSum3(k: number, n: number): number[][] {
    const ans: number[][] = [];
    const t: number[] = [];
    const dfs = (i: number, s: number) => {
        if (s === 0) {
            if (t.length === k) {
                ans.push(t.slice());
            }
            return;
        }
        if (i > 9 || i > s || t.length >= k) {
            return;
        }
        t.push(i);
        dfs(i + 1, s - i);
        t.pop();
        dfs(i + 1, s);
    };
    dfs(1, n);
    return ans;
}
```

#### JavaScript

```js
function combinationSum3(k, n) {
    const ans = [];
    const t = [];
    const dfs = (i, s) => {
        if (s === 0) {
            if (t.length === k) {
                ans.push(t.slice());
            }
            return;
        }
        if (i > 9 || i > s || t.length >= k) {
            return;
        }
        t.push(i);
        dfs(i + 1, s - i);
        t.pop();
        dfs(i + 1, s);
    };
    dfs(1, n);
    return ans;
}
```

#### Rust

```rust
impl Solution {
    #[allow(dead_code)]
    pub fn combination_sum3(k: i32, n: i32) -> Vec<Vec<i32>> {
        let mut ret = Vec::new();
        let mut candidates = (1..=9).collect();
        let mut cur_vec = Vec::new();
        Self::dfs(n, k, 0, 0, &mut cur_vec, &mut candidates, &mut ret);
        ret
    }

    #[allow(dead_code)]
    fn dfs(
        target: i32,
        length: i32,
        cur_index: usize,
        cur_sum: i32,
        cur_vec: &mut Vec<i32>,
        candidates: &Vec<i32>,
        ans: &mut Vec<Vec<i32>>,
    ) {
        if cur_sum > target || cur_vec.len() > (length as usize) {
            // Không có đáp án cho trường hợp này
            return;
        }
        if cur_sum == target && cur_vec.len() == (length as usize) {
            // Tìm được một đáp án
            ans.push(cur_vec.clone());
            return;
        }
        for i in cur_index..candidates.len() {
            cur_vec.push(candidates[i]);
            Self::dfs(
                target,
                length,
                i + 1,
                cur_sum + candidates[i],
                cur_vec,
                candidates,
                ans,
            );
            cur_vec.pop().unwrap();
        }
    }
}
```

#### C#

```cs
public class Solution {
    private List<IList<int>> ans = new List<IList<int>>();
    private List<int> t = new List<int>();
    private int k;

    public IList<IList<int>> CombinationSum3(int k, int n) {
        this.k = k;
        dfs(1, n);
        return ans;
    }

    private void dfs(int i, int s) {
        if (s == 0) {
            if (t.Count == k) {
                ans.Add(new List<int>(t));
            }
            return;
        }
        if (i > 9 || i > s || t.Count >= k) {
            return;
        }
        t.Add(i);
        dfs(i + 1, s - i);
        t.RemoveAt(t.Count - 1);
        dfs(i + 1, s);
    }
}
```

<!-- tabs:end -->

Cách tiếp cận khác:

- Nếu $s = 0$ và độ dài đường đi tìm kiếm hiện tại $t$ là $k$, điều đó có nghĩa là đã tìm được một nhóm đáp án. Thêm $t$ vào $ans$, sau đó trả về.
- Nếu $i \gt 9$ hoặc $i \gt s$ hoặc độ dài đường đi tìm kiếm hiện tại $t$ lớn hơn $k$, điều đó có nghĩa là đường đi tìm kiếm hiện tại không thể là đáp án, nên trả về ngay.
- Nếu không, chúng ta liệt kê số tiếp theo $j$, tức là $j \in [i, 9]$, thêm số $j$ vào đường đi tìm kiếm $t$, sau đó tiếp tục tìm kiếm, tức là thực thi $dfs(j + 1, s - j)$. Sau khi tìm kiếm xong, xóa $j$ khỏi đường đi tìm kiếm $t$.

Trong hàm chính, chúng ta gọi $dfs(1, n)$, tức là bắt đầu liệt kê từ số $1$ và cần liệt kê các số còn lại có tổng bằng $n$. Sau khi tìm kiếm xong, chúng ta có thể nhận được tất cả đáp án.

Độ phức tạp thời gian là $(C_{9}^k \times k)$, còn độ phức tạp không gian là $O(k)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def combinationSum3(self, k: int, n: int) -> List[List[int]]:
        def dfs(i: int, s: int):
            if s == 0:
                if len(t) == k:
                    ans.append(t[:])
                return
            if i > 9 or i > s or len(t) >= k:
                return
            for j in range(i, 10):
                t.append(j)
                dfs(j + 1, s - j)
                t.pop()

        ans = []
        t = []
        dfs(1, n)
        return ans
```

#### Java

```java
class Solution {
    private List<List<Integer>> ans = new ArrayList<>();
    private List<Integer> t = new ArrayList<>();
    private int k;

    public List<List<Integer>> combinationSum3(int k, int n) {
        this.k = k;
        dfs(1, n);
        return ans;
    }

    private void dfs(int i, int s) {
        if (s == 0) {
            if (t.size() == k) {
                ans.add(new ArrayList<>(t));
            }
            return;
        }
        if (i > 9 || i > s || t.size() >= k) {
            return;
        }
        for (int j = i; j <= 9; ++j) {
            t.add(j);
            dfs(j + 1, s - j);
            t.remove(t.size() - 1);
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<vector<int>> combinationSum3(int k, int n) {
        vector<vector<int>> ans;
        vector<int> t;
        function<void(int, int)> dfs = [&](int i, int s) {
            if (s == 0) {
                if (t.size() == k) {
                    ans.emplace_back(t);
                }
                return;
            }
            if (i > 9 || i > s || t.size() >= k) {
                return;
            }
            for (int j = i; j <= 9; ++j) {
                t.emplace_back(j);
                dfs(j + 1, s - j);
                t.pop_back();
            }
        };
        dfs(1, n);
        return ans;
    }
};
```

#### Go

```go
func combinationSum3(k int, n int) (ans [][]int) {
	t := []int{}
	var dfs func(i, s int)
	dfs = func(i, s int) {
		if s == 0 {
			if len(t) == k {
				ans = append(ans, slices.Clone(t))
			}
			return
		}
		if i > 9 || i > s || len(t) >= k {
			return
		}
		for j := i; j <= 9; j++ {
			t = append(t, j)
			dfs(j+1, s-j)
			t = t[:len(t)-1]
		}
	}
	dfs(1, n)
	return
}
```

#### TypeScript

```ts
function combinationSum3(k: number, n: number): number[][] {
    const ans: number[][] = [];
    const t: number[] = [];
    const dfs = (i: number, s: number) => {
        if (s === 0) {
            if (t.length === k) {
                ans.push(t.slice());
            }
            return;
        }
        if (i > 9 || i > s || t.length >= k) {
            return;
        }
        for (let j = i; j <= 9; ++j) {
            t.push(j);
            dfs(j + 1, s - j);
            t.pop();
        }
    };
    dfs(1, n);
    return ans;
}
```

#### JavaScript

```js
function combinationSum3(k, n) {
    const ans = [];
    const t = [];
    const dfs = (i, s) => {
        if (s === 0) {
            if (t.length === k) {
                ans.push(t.slice());
            }
            return;
        }
        if (i > 9 || i > s || t.length >= k) {
            return;
        }
        for (let j = i; j <= 9; ++j) {
            t.push(j);
            dfs(j + 1, s - j);
            t.pop();
        }
    };
    dfs(1, n);
    return ans;
}
```

#### C#

```cs
public class Solution {
    private List<IList<int>> ans = new List<IList<int>>();
    private List<int> t = new List<int>();
    private int k;

    public IList<IList<int>> CombinationSum3(int k, int n) {
        this.k = k;
        dfs(1, n);
        return ans;
    }

    private void dfs(int i, int s) {
        if (s == 0) {
            if (t.Count == k) {
                ans.Add(new List<int>(t));
            }
            return;
        }
        if (i > 9 || i > s || t.Count >= k) {
            return;
        }
        for (int j = i; j <= 9; ++j) {
            t.Add(j);
            dfs(j + 1, s - j);
            t.RemoveAt(t.Count - 1);
        }
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Lời giải 2: Liệt kê nhị phân

<!-- thinking:start -->

> **Tư duy**
>
> Quay lui sử dụng ngăn xếp lời gọi. Chỉ có $2^9$ tập con của $\{1,\ldots,9\}$, nên một mask gồm $9$ bit có thể liệt kê tất cả chúng.
>
> Nếu mask có đúng $k$ bit được đặt và các số tương ứng có tổng bằng $n$, chúng ta ghi nhận tập con đó.

<!-- thinking:end -->

Chúng ta có thể sử dụng một số nguyên nhị phân có độ dài $9$ để biểu diễn việc chọn các số từ $1$ đến $9$, trong đó bit thứ $i$ của số nguyên nhị phân biểu diễn việc số $i + 1$ có được chọn hay không. Nếu bit thứ $i$ là $1$, điều đó có nghĩa là số $i + 1$ được chọn; ngược lại, số $i + 1$ không được chọn.

Chúng ta liệt kê các số nguyên nhị phân trong phạm vi $[0, 2^9)$. Với số nguyên nhị phân đang được liệt kê $mask$, nếu số lượng số $1$ trong biểu diễn nhị phân của $mask$ là $k$ và tổng các số tương ứng với các số $1$ trong biểu diễn nhị phân của $mask$ là $n$, điều đó có nghĩa là cách chọn số tương ứng với $mask$ là một nhóm đáp án. Chúng ta có thể thêm cách chọn số tương ứng với $mask$ vào đáp án.

Độ phức tạp thời gian là $O(2^9 \times 9)$, còn độ phức tạp không gian là $O(k)$.

Các bài toán tương tự:

- [39. Combination Sum](https://github.com/doocs/leetcode/blob/main/solution/0000-0099/0039.Combination%20Sum/README_EN.md)
- [40. Combination Sum II](https://github.com/doocs/leetcode/blob/main/solution/0000-0099/0040.Combination%20Sum%20II/README_EN.md)
- [77. Combinations](https://github.com/doocs/leetcode/blob/main/solution/0000-0099/0077.Combinations/README_EN.md)

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def combinationSum3(self, k: int, n: int) -> List[List[int]]:
        ans = []
        for mask in range(1 << 9):
            if mask.bit_count() == k:
                t = [i + 1 for i in range(9) if mask >> i & 1]
                if sum(t) == n:
                    ans.append(t)
        return ans
```

#### Java

```java
class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> ans = new ArrayList<>();
        for (int mask = 0; mask < 1 << 9; ++mask) {
            if (Integer.bitCount(mask) == k) {
                List<Integer> t = new ArrayList<>();
                int s = 0;
                for (int i = 0; i < 9; ++i) {
                    if ((mask >> i & 1) == 1) {
                        s += (i + 1);
                        t.add(i + 1);
                    }
                }
                if (s == n) {
                    ans.add(t);
                }
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<vector<int>> combinationSum3(int k, int n) {
        vector<vector<int>> ans;
        for (int mask = 0; mask < 1 << 9; ++mask) {
            if (__builtin_popcount(mask) == k) {
                int s = 0;
                vector<int> t;
                for (int i = 0; i < 9; ++i) {
                    if (mask >> i & 1) {
                        t.push_back(i + 1);
                        s += i + 1;
                    }
                }
                if (s == n) {
                    ans.emplace_back(t);
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func combinationSum3(k int, n int) (ans [][]int) {
	for mask := 0; mask < 1<<9; mask++ {
		if bits.OnesCount(uint(mask)) == k {
			t := []int{}
			s := 0
			for i := 0; i < 9; i++ {
				if mask>>i&1 == 1 {
					s += i + 1
					t = append(t, i+1)
				}
			}
			if s == n {
				ans = append(ans, t)
			}
		}
	}
	return
}
```

#### TypeScript

```ts
function combinationSum3(k: number, n: number): number[][] {
    const ans: number[][] = [];
    for (let mask = 0; mask < 1 << 9; ++mask) {
        if (bitCount(mask) === k) {
            const t: number[] = [];
            let s = 0;
            for (let i = 0; i < 9; ++i) {
                if (mask & (1 << i)) {
                    t.push(i + 1);
                    s += i + 1;
                }
            }
            if (s === n) {
                ans.push(t);
            }
        }
    }
    return ans;
}

function bitCount(i: number): number {
    i = i - ((i >>> 1) & 0x55555555);
    i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
    i = (i + (i >>> 4)) & 0x0f0f0f0f;
    i = i + (i >>> 8);
    i = i + (i >>> 16);
    return i & 0x3f;
}
```

#### JavaScript

```js
function combinationSum3(k, n) {
    const ans = [];
    for (let mask = 0; mask < 1 << 9; ++mask) {
        if (bitCount(mask) === k) {
            const t = [];
            let s = 0;
            for (let i = 0; i < 9; ++i) {
                if (mask & (1 << i)) {
                    t.push(i + 1);
                    s += i + 1;
                }
            }
            if (s === n) {
                ans.push(t);
            }
        }
    }
    return ans;
}

function bitCount(i) {
    i = i - ((i >>> 1) & 0x55555555);
    i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
    i = (i + (i >>> 4)) & 0x0f0f0f0f;
    i = i + (i >>> 8);
    i = i + (i >>> 16);
    return i & 0x3f;
}
```

#### C#

```cs
public class Solution {
    public IList<IList<int>> CombinationSum3(int k, int n) {
        List<IList<int>> ans = new List<IList<int>>();
        for (int mask = 0; mask < 1 << 9; ++mask) {
            if (bitCount(mask) == k) {
                List<int> t = new List<int>();
                int s = 0;
                for (int i = 0; i < 9; ++i) {
                    if ((mask >> i & 1) == 1) {
                        s += i + 1;
                        t.Add(i + 1);
                    }
                }
                if (s == n) {
                    ans.Add(t);
                }
            }
        }
        return ans;
    }

    private int bitCount(int x) {
        int cnt = 0;
        while (x > 0) {
            x -= x & -x;
            ++cnt;
        }
        return cnt;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
