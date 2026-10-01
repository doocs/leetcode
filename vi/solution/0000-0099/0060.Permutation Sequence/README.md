---
comments: true
difficulty: Hard
tags:
    - Recursion
    - Math
---

<!-- problem:start -->

# [60. Permutation Sequence](https://leetcode.com/problems/permutation-sequence)

[中文文档](/solution/0000-0099/0060.Permutation%20Sequence/README.md)

## Mô tả

<!-- description:start -->

<p>Tập hợp <code>[1, 2, 3, ...,&nbsp;n]</code> chứa tổng cộng <code>n!</code> hoán vị khác nhau.</p>

<p>Bằng cách liệt kê và đánh số tất cả các hoán vị theo thứ tự, ta nhận được dãy sau với <code>n = 3</code>:</p>

<ol>
	<li><code>&quot;123&quot;</code></li>
	<li><code>&quot;132&quot;</code></li>
	<li><code>&quot;213&quot;</code></li>
	<li><code>&quot;231&quot;</code></li>
	<li><code>&quot;312&quot;</code></li>
	<li><code>&quot;321&quot;</code></li>
</ol>

<p>Với <code>n</code> và <code>k</code>, hãy trả về dãy hoán vị thứ <code>k<sup>th</sup></code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>
<pre><strong>Đầu vào:</strong> n = 3, k = 3
<strong>Đầu ra:</strong> "213"
</pre><p><strong class="example">Ví dụ 2:</strong></p>
<pre><strong>Đầu vào:</strong> n = 4, k = 9
<strong>Đầu ra:</strong> "2314"
</pre><p><strong class="example">Ví dụ 3:</strong></p>
<pre><strong>Đầu vào:</strong> n = 3, k = 1
<strong>Đầu ra:</strong> "123"
</pre>
<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 9</code></li>
	<li><code>1 &lt;= k &lt;= n!</code></li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Liệt kê

<!-- thinking:start -->

> **Tư duy**
>
> Ý tưởng đầu tiên là sinh tất cả $n!$ hoán vị rồi chọn hoán vị thứ $k$. Vì $n \le 9$, $9!$ vẫn chạy được, nhưng chúng ta không bao giờ cần đến các hoán vị còn lại.
>
> Điểm lãng phí là liệt kê rồi mới chọn. Sau khi chữ số đầu tiên được cố định, phần còn lại tạo thành $(n-1)!$ hoán vị; so sánh $k$ với kích thước khối đó cho biết số chưa dùng nào sẽ được đặt ở đây.
>
> Vì vậy, chúng ta liệt kê từng vị trí từ trái sang phải, bỏ qua toàn bộ các khối bằng cách dùng giai thừa, và đánh dấu các số đã dùng trong $\textit{vis}$. Thời gian là $O(n^2)$.

<!-- thinking:end -->

Chúng ta biết rằng tập hợp $[1,2,..n]$ có tổng cộng $n!$ hoán vị. Nếu xác định chữ số đầu tiên, số hoán vị mà các chữ số còn lại có thể tạo thành là $(n-1)!$.

Do đó, chúng ta liệt kê từng chữ số $i$. Nếu $k$ lớn hơn số hoán vị sau khi vị trí hiện tại được xác định, chúng ta có thể trực tiếp trừ đi số này; nếu không, điều đó có nghĩa là chúng ta đã tìm được số ở vị trí hiện tại.

Với mỗi chữ số $i$, trong đó $0 \leq i < n$, số hoán vị mà các chữ số còn lại có thể tạo thành là $(n-i-1)!$, được ký hiệu là $fact$. Các số đã dùng trong quá trình này được ghi lại trong `vis`.

Độ phức tạp thời gian là $O(n^2)$ và độ phức tạp không gian là $O(n)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def getPermutation(self, n: int, k: int) -> str:
        ans = []
        vis = [False] * (n + 1)
        for i in range(n):
            fact = 1
            for j in range(1, n - i):
                fact *= j
            for j in range(1, n + 1):
                if not vis[j]:
                    if k > fact:
                        k -= fact
                    else:
                        ans.append(str(j))
                        vis[j] = True
                        break
        return ''.join(ans)
```

#### Java

```java
class Solution {
    public String getPermutation(int n, int k) {
        StringBuilder ans = new StringBuilder();
        boolean[] vis = new boolean[n + 1];
        for (int i = 0; i < n; ++i) {
            int fact = 1;
            for (int j = 1; j < n - i; ++j) {
                fact *= j;
            }
            for (int j = 1; j <= n; ++j) {
                if (!vis[j]) {
                    if (k > fact) {
                        k -= fact;
                    } else {
                        ans.append(j);
                        vis[j] = true;
                        break;
                    }
                }
            }
        }
        return ans.toString();
    }
}
```

#### C++

```cpp
class Solution {
public:
    string getPermutation(int n, int k) {
        string ans;
        bitset<10> vis;
        for (int i = 0; i < n; ++i) {
            int fact = 1;
            for (int j = 1; j < n - i; ++j) fact *= j;
            for (int j = 1; j <= n; ++j) {
                if (vis[j]) continue;
                if (k > fact)
                    k -= fact;
                else {
                    ans += to_string(j);
                    vis[j] = 1;
                    break;
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func getPermutation(n int, k int) string {
	ans := make([]byte, n)
	vis := make([]bool, n+1)
	for i := 0; i < n; i++ {
		fact := 1
		for j := 1; j < n-i; j++ {
			fact *= j
		}
		for j := 1; j <= n; j++ {
			if !vis[j] {
				if k > fact {
					k -= fact
				} else {
					ans[i] = byte('0' + j)
					vis[j] = true
					break
				}
			}
		}
	}
	return string(ans)
}
```

#### Rust

```rust
impl Solution {
    pub fn get_permutation(n: i32, k: i32) -> String {
        let mut k = k;
        let mut ans = String::new();
        let mut fact = vec![1; n as usize];
        for i in 1..n as usize {
            fact[i] = fact[i - 1] * (i as i32);
        }
        let mut vis = vec![false; n as usize + 1];

        for i in 0..n as usize {
            let cnt = fact[(n as usize) - i - 1];
            for j in 1..=n {
                if vis[j as usize] {
                    continue;
                }
                if k > cnt {
                    k -= cnt;
                } else {
                    ans.push_str(&j.to_string());
                    vis[j as usize] = true;
                    break;
                }
            }
        }

        ans
    }
}
```

#### C#

```cs
public class Solution {
    public string GetPermutation(int n, int k) {
        var ans = new StringBuilder();
        int vis = 0;
        for (int i = 0; i < n; ++i) {
            int fact = 1;
            for (int j = 1; j < n - i; ++j) {
                fact *= j;
            }
            for (int j = 1; j <= n; ++j) {
                if (((vis >> j) & 1) == 0) {
                    if (k > fact) {
                        k -= fact;
                    } else {
                        ans.Append(j);
                        vis |= 1 << j;
                        break;
                    }
                }
            }
        }
        return ans.ToString();
    }
}
```

#### TypeScript

```ts
function getPermutation(n: number, k: number): string {
    let ans = '';
    const vis = Array.from({ length: n + 1 }, () => false);
    for (let i = 0; i < n; i++) {
        let fact = 1;
        for (let j = 1; j < n - i; j++) {
            fact *= j;
        }
        for (let j = 1; j <= n; j++) {
            if (!vis[j]) {
                if (k > fact) {
                    k -= fact;
                } else {
                    ans += j;
                    vis[j] = true;
                    break;
                }
            }
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
