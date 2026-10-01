---
comments: true
difficulty: Medium
tags:
    - Array
    - Dynamic Programming
---

<!-- problem:start -->

# [198. House Robber](https://leetcode.com/problems/house-robber)

[中文文档](/solution/0100-0199/0198.House%20Robber/README.md)

## Mô tả

<!-- description:start -->

<p>Bạn là một tên trộm chuyên nghiệp đang lên kế hoạch cướp các ngôi nhà dọc theo một con phố. Mỗi ngôi nhà có một khoản tiền nhất định được cất giấu, và ràng buộc duy nhất ngăn bạn cướp tất cả chúng là các hệ thống an ninh của những ngôi nhà liền kề được kết nối với nhau và <b>hệ thống sẽ tự động báo cảnh sát nếu hai ngôi nhà liền kề bị đột nhập trong cùng một đêm</b>.</p>

<p>Cho một mảng số nguyên <code>nums</code> biểu diễn số tiền của mỗi ngôi nhà, hãy trả về <em>số tiền lớn nhất bạn có thể cướp tối nay <b>mà không báo động cảnh sát</b></em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong> nums = [1,2,3,1]
<strong>Đầu ra:</strong> 4
<strong>Giải thích:</strong> Cướp ngôi nhà 1 (số tiền = 1), sau đó cướp ngôi nhà 3 (số tiền = 3).
Tổng số tiền bạn có thể cướp = 1 + 3 = 4.
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> nums = [2,7,9,3,1]
<strong>Đầu ra:</strong> 12
<strong>Giải thích:</strong> Cướp ngôi nhà 1 (số tiền = 2), cướp ngôi nhà 3 (số tiền = 9) và cướp ngôi nhà 5 (số tiền = 1).
Tổng số tiền bạn có thể cướp = 2 + 9 + 1 = 12.
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 400</code></li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Tìm kiếm có ghi nhớ

<!-- thinking:start -->

> **Tư duy**
>
> Không thể cướp đồng thời các ngôi nhà liền kề; mục tiêu là tối đa hóa số tiền lấy được. Việc liệt kê các tập con phải đảm bảo khoảng cách giữa các ngôi nhà, đồng thời các hậu tố chồng lấn sẽ bị tính lại. Từ chỉ số $i$, cướp và chuyển đến $i+2$, hoặc bỏ qua và chuyển đến $i+1$, rồi chọn phương án tốt hơn. Tìm kiếm có ghi nhớ tính kết quả của mỗi chỉ số đúng một lần.

<!-- thinking:end -->

Chúng ta xây dựng một hàm $\textit{dfs}(i)$, biểu diễn số tiền lớn nhất có thể cướp bắt đầu từ ngôi nhà thứ $i$. Do đó, đáp án là $\textit{dfs}(0)$.

Quá trình thực thi hàm $\textit{dfs}(i)$ như sau:

- Nếu $i \ge \textit{len}(\textit{nums})$, nghĩa là tất cả các ngôi nhà đã được xét, chúng ta trả về trực tiếp $0$;
- Nếu không, xét việc cướp ngôi nhà thứ $i$, khi đó $\textit{dfs}(i) = \textit{nums}[i] + \textit{dfs}(i+2)$; nếu không cướp ngôi nhà thứ $i$, thì $\textit{dfs}(i) = \textit{dfs}(i+1)$.
- Trả về $\max(\textit{nums}[i] + \textit{dfs}(i+2), \textit{dfs}(i+1))$.

Để tránh tính toán lặp lại, chúng ta sử dụng tìm kiếm có ghi nhớ. Kết quả của $\textit{dfs}(i)$ được lưu trong một mảng hoặc bảng băm. Trước mỗi lần tính toán, trước tiên chúng ta kiểm tra xem kết quả đã được tính hay chưa. Nếu rồi, chúng ta trả về trực tiếp kết quả đó.

Độ phức tạp thời gian là $O(n)$, và độ phức tạp không gian là $O(n)$, trong đó $n$ là độ dài của mảng.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def rob(self, nums: List[int]) -> int:
        @cache
        def dfs(i: int) -> int:
            if i >= len(nums):
                return 0
            return max(nums[i] + dfs(i + 2), dfs(i + 1))

        return dfs(0)
```

#### Java

```java
class Solution {
    private Integer[] f;
    private int[] nums;

    public int rob(int[] nums) {
        this.nums = nums;
        f = new Integer[nums.length];
        return dfs(0);
    }

    private int dfs(int i) {
        if (i >= nums.length) {
            return 0;
        }
        if (f[i] == null) {
            f[i] = Math.max(nums[i] + dfs(i + 2), dfs(i + 1));
        }
        return f[i];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int rob(vector<int>& nums) {
        int n = nums.size();
        int f[n];
        memset(f, -1, sizeof(f));
        auto dfs = [&](this auto&& dfs, int i) -> int {
            if (i >= n) {
                return 0;
            }
            if (f[i] < 0) {
                f[i] = max(nums[i] + dfs(i + 2), dfs(i + 1));
            }
            return f[i];
        };
        return dfs(0);
    }
};
```

#### Go

```go
func rob(nums []int) int {
	n := len(nums)
	f := make([]int, n)
	for i := range f {
		f[i] = -1
	}
	var dfs func(int) int
	dfs = func(i int) int {
		if i >= n {
			return 0
		}
		if f[i] < 0 {
			f[i] = max(nums[i]+dfs(i+2), dfs(i+1))
		}
		return f[i]
	}
	return dfs(0)
}
```

#### TypeScript

```ts
function rob(nums: number[]): number {
    const n = nums.length;
    const f: number[] = Array(n).fill(-1);
    const dfs = (i: number): number => {
        if (i >= n) {
            return 0;
        }
        if (f[i] < 0) {
            f[i] = Math.max(nums[i] + dfs(i + 2), dfs(i + 1));
        }
        return f[i];
    };
    return dfs(0);
}
```

#### Rust

```rust
impl Solution {
    pub fn rob(nums: Vec<i32>) -> i32 {
        fn dfs(i: usize, nums: &Vec<i32>, f: &mut Vec<i32>) -> i32 {
            if i >= nums.len() {
                return 0;
            }
            if f[i] < 0 {
                f[i] = (nums[i] + dfs(i + 2, nums, f)).max(dfs(i + 1, nums, f));
            }
            f[i]
        }

        let n = nums.len();
        let mut f = vec![-1; n];
        dfs(0, &nums, &mut f)
    }
}
```

#### JavaScript

```js
function rob(nums) {
    const n = nums.length;
    const f = Array(n).fill(-1);
    const dfs = i => {
        if (i >= n) {
            return 0;
        }
        if (f[i] < 0) {
            f[i] = Math.max(nums[i] + dfs(i + 2), dfs(i + 1));
        }
        return f[i];
    };
    return dfs(0);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Lời giải 2: Quy hoạch động

<!-- thinking:start -->

> **Tư duy**
>
> Lời giải 1 sử dụng đệ quy. $f[i]$ là tổng lớn nhất trong $i$ ngôi nhà đầu tiên: cướp ngôi nhà $i$ và cộng thêm $f[i-2]$, hoặc lấy $f[i-1]$. Chúng ta điền bảng theo thứ tự từ dưới lên.

<!-- thinking:end -->

Chúng ta định nghĩa $f[i]$ là tổng số tiền lớn nhất có thể cướp từ $i$ ngôi nhà đầu tiên, ban đầu $f[0]=0$, $f[1]=nums[0]$.

Xét trường hợp $i \gt 1$, ngôi nhà thứ $i$ có hai lựa chọn:

- Không cướp ngôi nhà thứ $i$, tổng số tiền cướp được là $f[i-1]$;
- Cướp ngôi nhà thứ $i$, tổng số tiền cướp được là $f[i-2]+nums[i-1]$;

Do đó, chúng ta có thể nhận được phương trình chuyển trạng thái:

$$
f[i]=
\begin{cases}
0, & i=0 \\
nums[0], & i=1 \\
\max(f[i-1],f[i-2]+nums[i-1]), & i \gt 1
\end{cases}
$$

Đáp án cuối cùng là $f[n]$, trong đó $n$ là độ dài của mảng.

Độ phức tạp thời gian là $O(n)$, và độ phức tạp không gian là $O(n)$. Trong đó $n$ là độ dài của mảng.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def rob(self, nums: List[int]) -> int:
        n = len(nums)
        f = [0] * (n + 1)
        f[1] = nums[0]
        for i in range(2, n + 1):
            f[i] = max(f[i - 1], f[i - 2] + nums[i - 1])
        return f[n]
```

#### Java

```java
class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        int[] f = new int[n + 1];
        f[1] = nums[0];
        for (int i = 2; i <= n; ++i) {
            f[i] = Math.max(f[i - 1], f[i - 2] + nums[i - 1]);
        }
        return f[n];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int rob(vector<int>& nums) {
        int n = nums.size();
        int f[n + 1];
        memset(f, 0, sizeof(f));
        f[1] = nums[0];
        for (int i = 2; i <= n; ++i) {
            f[i] = max(f[i - 1], f[i - 2] + nums[i - 1]);
        }
        return f[n];
    }
};
```

#### Go

```go
func rob(nums []int) int {
	n := len(nums)
	f := make([]int, n+1)
	f[1] = nums[0]
	for i := 2; i <= n; i++ {
		f[i] = max(f[i-1], f[i-2]+nums[i-1])
	}
	return f[n]
}
```

#### TypeScript

```ts
function rob(nums: number[]): number {
    const n = nums.length;
    const f: number[] = Array(n + 1).fill(0);
    f[1] = nums[0];
    for (let i = 2; i <= n; ++i) {
        f[i] = Math.max(f[i - 1], f[i - 2] + nums[i - 1]);
    }
    return f[n];
}
```

#### Rust

```rust
impl Solution {
    pub fn rob(nums: Vec<i32>) -> i32 {
        let n = nums.len();
        let mut f = vec![0; n + 1];
        f[1] = nums[0];
        for i in 2..=n {
            f[i] = f[i - 1].max(f[i - 2] + nums[i - 1]);
        }
        f[n]
    }
}
```

#### JavaScript

```js
function rob(nums) {
    const n = nums.length;
    const f = Array(n + 1).fill(0);
    f[1] = nums[0];
    for (let i = 2; i <= n; ++i) {
        f[i] = Math.max(f[i - 1], f[i - 2] + nums[i - 1]);
    }
    return f[n];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Lời giải 3: Quy hoạch động (Tối ưu không gian)

<!-- thinking:start -->

> **Tư duy**
>
> $f[i]$ trong Lời giải 2 chỉ phụ thuộc vào hai giá trị trước đó, vì vậy hai biến cuộn có thể giảm không gian xuống $O(1)$.

<!-- thinking:end -->

Chúng ta nhận thấy khi $i \gt 2$, $f[i]$ chỉ liên quan đến $f[i-1]$ và $f[i-2]$. Do đó, chúng ta có thể dùng hai biến thay cho một mảng để giảm độ phức tạp không gian xuống $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def rob(self, nums: List[int]) -> int:
        f = g = 0
        for x in nums:
            f, g = max(f, g), f + x
        return max(f, g)
```

#### Java

```java
class Solution {
    public int rob(int[] nums) {
        int f = 0, g = 0;
        for (int x : nums) {
            int ff = Math.max(f, g);
            g = f + x;
            f = ff;
        }
        return Math.max(f, g);
    }
}
```

#### C++

```cpp
class Solution {
public:
    int rob(vector<int>& nums) {
        int f = 0, g = 0;
        for (int& x : nums) {
            int ff = max(f, g);
            g = f + x;
            f = ff;
        }
        return max(f, g);
    }
};
```

#### Go

```go
func rob(nums []int) int {
	f, g := 0, 0
	for _, x := range nums {
		f, g = max(f, g), f+x
	}
	return max(f, g)
}
```

#### TypeScript

```ts
function rob(nums: number[]): number {
    let [f, g] = [0, 0];
    for (const x of nums) {
        [f, g] = [Math.max(f, g), f + x];
    }
    return Math.max(f, g);
}
```

#### Rust

```rust
impl Solution {
    pub fn rob(nums: Vec<i32>) -> i32 {
        let mut f = [0, 0];
        for x in nums {
            f = [f[0].max(f[1]), f[0] + x];
        }
        f[0].max(f[1])
    }
}
```

#### JavaScript

```js
function rob(nums) {
    let [f, g] = [0, 0];
    for (const x of nums) {
        [f, g] = [Math.max(f, g), f + x];
    }
    return Math.max(f, g);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
