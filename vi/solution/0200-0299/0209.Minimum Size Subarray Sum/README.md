---
comments: true
difficulty: Medium
tags:
    - Array
    - Binary Search
    - Prefix Sum
    - Sliding Window
---

<!-- problem:start -->

# [209. Minimum Size Subarray Sum](https://leetcode.com/problems/minimum-size-subarray-sum)

[中文文档](/solution/0200-0299/0209.Minimum%20Size%20Subarray%20Sum/README.md)

## Mô tả

<!-- description:start -->

<p>Cho một mảng các số nguyên dương <code>nums</code> và một số nguyên dương <code>target</code>, hãy trả về <em>độ dài <strong>nhỏ nhất</strong> của một </em><span data-keyword="subarray-nonempty"><em>mảng con</em></span><em> có tổng lớn hơn hoặc bằng</em> <code>target</code>. Nếu không tồn tại mảng con như vậy, hãy trả về <code>0</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong> target = 7, nums = [2,3,1,2,4,3]
<strong>Đầu ra:</strong> 2
<strong>Giải thích:</strong> Mảng con [4,3] có độ dài nhỏ nhất thỏa mãn ràng buộc của bài toán.
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> target = 4, nums = [1,4,4]
<strong>Đầu ra:</strong> 1
</pre>

<p><strong class="example">Ví dụ 3:</strong></p>

<pre>
<strong>Đầu vào:</strong> target = 11, nums = [1,1,1,1,1,1,1,1]
<strong>Đầu ra:</strong> 0
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= target &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
</ul>

<p>&nbsp;</p>
<strong>Câu hỏi mở rộng:</strong> Nếu đã tìm ra lời giải có độ phức tạp thời gian <code>O(n)</code>, hãy thử viết một lời giải khác có độ phức tạp thời gian <code>O(n log(n))</code>.

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Tổng tiền tố + Tìm kiếm nhị phân

<!-- thinking:start -->

> **Tư duy**
>
> Việc tính tổng của mọi mảng con có độ phức tạp $O(n^2)$. Vì tất cả phần tử đều dương, tổng tiền tố có tính đơn điệu và có thể dùng tìm kiếm nhị phân để tìm điểm kết thúc ngắn nhất với mỗi điểm bắt đầu cố định.
>
> Sau khi xây dựng $s$, với mỗi $s[i]$, chúng ta tìm $j$ nhỏ nhất sao cho $s[j] \ge s[i]+\textit{target}$ rồi cập nhật độ dài bằng $j-i$.

<!-- thinking:end -->

Đầu tiên, chúng ta tiền xử lý mảng tổng tiền tố $s$ của mảng $nums$, trong đó $s[i]$ biểu diễn tổng của $i$ phần tử đầu tiên trong mảng $nums$. Vì tất cả phần tử trong mảng $nums$ đều là số nguyên dương, mảng $s$ cũng tăng đơn điệu. Đồng thời, chúng ta khởi tạo đáp án $ans = n + 1$, trong đó $n$ là độ dài của mảng $nums$.

Tiếp theo, chúng ta duyệt qua mảng tổng tiền tố $s$. Với mỗi phần tử $s[i]$, chúng ta có thể tìm chỉ số nhỏ nhất $j$ thỏa mãn $s[j] \geq s[i] + target$ bằng tìm kiếm nhị phân. Nếu $j \leq n$, điều đó có nghĩa là tồn tại một mảng con thỏa mãn điều kiện, và chúng ta có thể cập nhật đáp án, tức là $ans = min(ans, j - i)$.

Cuối cùng, nếu $ans \leq n$, điều đó có nghĩa là tồn tại một mảng con thỏa mãn điều kiện, hãy trả về $ans$; nếu không, trả về $0$.

Độ phức tạp thời gian là $O(n \times \log n)$ và độ phức tạp không gian là $O(n)$. Trong đó, $n$ là độ dài của mảng $nums$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minSubArrayLen(self, target: int, nums: List[int]) -> int:
        n = len(nums)
        s = list(accumulate(nums, initial=0))
        ans = n + 1
        for i, x in enumerate(s):
            j = bisect_left(s, x + target)
            if j <= n:
                ans = min(ans, j - i)
        return ans if ans <= n else 0
```

#### Java

```java
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        long[] s = new long[n + 1];
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + nums[i];
        }
        int ans = n + 1;
        for (int i = 0; i <= n; ++i) {
            int j = search(s, s[i] + target);
            if (j <= n) {
                ans = Math.min(ans, j - i);
            }
        }
        return ans <= n ? ans : 0;
    }

    private int search(long[] nums, long x) {
        int l = 0, r = nums.length;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (nums[mid] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minSubArrayLen(int target, vector<int>& nums) {
        int n = nums.size();
        vector<long long> s(n + 1);
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + nums[i];
        }
        int ans = n + 1;
        for (int i = 0; i <= n; ++i) {
            int j = lower_bound(s.begin(), s.end(), s[i] + target) - s.begin();
            if (j <= n) {
                ans = min(ans, j - i);
            }
        }
        return ans <= n ? ans : 0;
    }
};
```

#### Go

```go
func minSubArrayLen(target int, nums []int) int {
	n := len(nums)
	s := make([]int, n+1)
	for i, x := range nums {
		s[i+1] = s[i] + x
	}
	ans := n + 1
	for i, x := range s {
		j := sort.SearchInts(s, x+target)
		if j <= n {
			ans = min(ans, j-i)
		}
	}
	if ans == n+1 {
		return 0
	}
	return ans
}
```

#### TypeScript

```ts
function minSubArrayLen(target: number, nums: number[]): number {
    const n = nums.length;
    const s: number[] = new Array(n + 1).fill(0);
    for (let i = 0; i < n; ++i) {
        s[i + 1] = s[i] + nums[i];
    }
    let ans = n + 1;
    const search = (x: number) => {
        let l = 0;
        let r = n + 1;
        while (l < r) {
            const mid = (l + r) >>> 1;
            if (s[mid] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    };
    for (let i = 0; i <= n; ++i) {
        const j = search(s[i] + target);
        if (j <= n) {
            ans = Math.min(ans, j - i);
        }
    }
    return ans === n + 1 ? 0 : ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn min_sub_array_len(target: i32, nums: Vec<i32>) -> i32 {
        let n = nums.len();
        let mut res = n + 1;
        let mut sum = 0;
        let mut i = 0;
        for j in 0..n {
            sum += nums[j];

            while sum >= target {
                res = res.min(j - i + 1);
                sum -= nums[i];
                i += 1;
            }
        }
        if res == n + 1 {
            return 0;
        }
        res as i32
    }
}
```

#### C#

```cs
public class Solution {
    public int MinSubArrayLen(int target, int[] nums) {
        int n = nums.Length;
        long s = 0;
        int ans = n + 1;
        for (int i = 0, j = 0; i < n; ++i) {
            s += nums[i];
            while (s >= target) {
                ans = Math.Min(ans, i - j + 1);
                s -= nums[j++];
            }
        }
        return ans == n + 1 ? 0 : ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Lời giải 2: Hai con trỏ

<!-- thinking:start -->

> **Tư duy**
>
> Tổng tiền tố vẫn sử dụng $O(n)$ không gian và có thêm thừa số log. Tính dương của các phần tử khiến tổng cửa sổ tăng khi điểm kết thúc dịch sang phải và giảm khi điểm bắt đầu dịch sang phải.
>
> Do đó, chỉ cần dùng hai con trỏ: thêm phần tử ở bên phải, và khi tổng lớn hơn hoặc bằng $\textit{target}$ thì thu hẹp từ bên trái rồi ghi nhận độ dài ngắn nhất.

<!-- thinking:end -->

Chúng ta có thể sử dụng hai con trỏ $j$ và $i$ để duy trì một cửa sổ, trong đó tổng của tất cả phần tử trong cửa sổ nhỏ hơn $target$. Ban đầu, $j = 0$ và đáp án $ans = n + 1$, trong đó $n$ là độ dài của mảng $nums$.

Tiếp theo, con trỏ $i$ bắt đầu di chuyển sang phải từ $0$, mỗi lần di chuyển một bước. Chúng ta thêm phần tử tương ứng với con trỏ $i$ vào cửa sổ và cập nhật tổng các phần tử trong cửa sổ. Nếu tổng các phần tử trong cửa sổ lớn hơn hoặc bằng $target$, điều đó có nghĩa là mảng con hiện tại thỏa mãn điều kiện, và chúng ta có thể cập nhật đáp án, tức là $ans = \min(ans, i - j + 1)$. Sau đó, chúng ta liên tục loại bỏ phần tử $nums[j]$ khỏi cửa sổ cho đến khi tổng các phần tử trong cửa sổ nhỏ hơn $target$, rồi lặp lại quy trình trên.

Cuối cùng, nếu $ans \leq n$, điều đó có nghĩa là tồn tại một mảng con thỏa mãn điều kiện, hãy trả về $ans$; nếu không, trả về $0$.

Độ phức tạp thời gian là $O(n)$ và độ phức tạp không gian là $O(1)$. Trong đó, $n$ là độ dài của mảng $nums$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minSubArrayLen(self, target: int, nums: List[int]) -> int:
        l = s = 0
        ans = inf
        for r, x in enumerate(nums):
            s += x
            while s >= target:
                ans = min(ans, r - l + 1)
                s -= nums[l]
                l += 1
        return 0 if ans == inf else ans
```

#### Java

```java
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int l = 0, n = nums.length;
        long s = 0;
        int ans = n + 1;
        for (int r = 0; r < n; ++r) {
            s += nums[r];
            while (s >= target) {
                ans = Math.min(ans, r - l + 1);
                s -= nums[l++];
            }
        }
        return ans > n ? 0 : ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minSubArrayLen(int target, vector<int>& nums) {
        int l = 0, n = nums.size();
        long long s = 0;
        int ans = n + 1;
        for (int r = 0; r < n; ++r) {
            s += nums[r];
            while (s >= target) {
                ans = min(ans, r - l + 1);
                s -= nums[l++];
            }
        }
        return ans > n ? 0 : ans;
    }
};
```

#### Go

```go
func minSubArrayLen(target int, nums []int) int {
	l, n := 0, len(nums)
	s, ans := 0, n+1
	for r, x := range nums {
		s += x
		for s >= target {
			ans = min(ans, r-l+1)
			s -= nums[l]
			l++
		}
	}
	if ans > n {
		return 0
	}
	return ans
}
```

#### TypeScript

```ts
function minSubArrayLen(target: number, nums: number[]): number {
    const n = nums.length;
    let [s, ans] = [0, n + 1];
    for (let l = 0, r = 0; r < n; ++r) {
        s += nums[r];
        while (s >= target) {
            ans = Math.min(ans, r - l + 1);
            s -= nums[l++];
        }
    }
    return ans > n ? 0 : ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
