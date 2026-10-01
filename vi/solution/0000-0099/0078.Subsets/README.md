---
comments: true
difficulty: Medium
tags:
    - Bit Manipulation
    - Array
    - Backtracking
---

<!-- problem:start -->

# [78. Subsets](https://leetcode.com/problems/subsets)

[中文文档](/solution/0000-0099/0078.Subsets/README.md)

## Mô tả

<!-- description:start -->

<p>Với một mảng số nguyên <code>nums</code> gồm các phần tử <strong>khác nhau</strong>, hãy trả về <em>tất cả các <span data-keyword="subset"><em>tập con</em></span> có thể có</em> <em>(tập lũy thừa)</em>.</p>

<p>Tập kết quả <strong>không được</strong> chứa các tập con trùng lặp. Trả về kết quả theo <strong>bất kỳ thứ tự nào</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong> nums = [1,2,3]
<strong>Đầu ra:</strong> [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> nums = [0]
<strong>Đầu ra:</strong> [[],[0]]
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10</code></li>
	<li><code>-10 &lt;= nums[i] &lt;= 10</code></li>
	<li>Tất cả các số trong&nbsp;<code>nums</code> đều <strong>khác nhau</strong>.</li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: DFS (Quay lui)

<!-- thinking:start -->

> **Tư duy**
>
> Một tập con tương ứng với việc chọn hoặc bỏ qua mỗi phần tử, có tất cả $2^n$ tập con. Vì $n \le 10$, việc liệt kê tất cả là phù hợp. Chúng ta có thể dựng lại từng tập từ mỗi mask hoặc dùng đệ quy.
>
> Đệ quy thể hiện trực tiếp: tại chỉ số $i$, bỏ qua $nums[i]$, sau đó chọn nó và pop. Khi $i=n$, sao chép đường đi vào đáp án. Các phần tử phân biệt nên không có tập con trùng lặp.

<!-- thinking:end -->

Chúng ta thiết kế một hàm $dfs(i)$, biểu thị việc bắt đầu tìm kiếm từ phần tử thứ $i$ của mảng cho tất cả các tập con. Logic thực thi của hàm $dfs(i)$ như sau:

- Nếu $i = n$, điều đó có nghĩa là lần tìm kiếm hiện tại đã kết thúc. Thêm tập con hiện tại $t$ vào mảng đáp án $ans$, sau đó trả về.
- Nếu không, chúng ta có thể chọn không chọn phần tử hiện tại và trực tiếp thực hiện $dfs(i + 1)$; hoặc chúng ta có thể chọn phần tử hiện tại, tức là thêm phần tử hiện tại $nums[i]$ vào tập con $t$, sau đó thực hiện $dfs(i + 1)$. Lưu ý rằng chúng ta cần loại bỏ $nums[i]$ khỏi tập con $t$ sau khi thực hiện $dfs(i + 1)$ (quay lui).

Trong hàm chính, chúng ta gọi $dfs(0)$, tức là bắt đầu tìm kiếm tất cả các tập con từ phần tử đầu tiên của mảng. Cuối cùng, trả về mảng đáp án $ans$.

Độ phức tạp thời gian là $O(n \times 2^n)$, và độ phức tạp không gian là $O(n)$. Trong đó, $n$ là độ dài của mảng. Có tổng cộng $2^n$ tập con, và mỗi tập con cần $O(n)$ thời gian để xây dựng.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def subsets(self, nums: List[int]) -> List[List[int]]:
        def dfs(i: int):
            if i == len(nums):
                ans.append(t[:])
                return
            dfs(i + 1)
            t.append(nums[i])
            dfs(i + 1)
            t.pop()

        ans = []
        t = []
        dfs(0)
        return ans
```

#### Java

```java
class Solution {
    private List<List<Integer>> ans = new ArrayList<>();
    private List<Integer> t = new ArrayList<>();
    private int[] nums;

    public List<List<Integer>> subsets(int[] nums) {
        this.nums = nums;
        dfs(0);
        return ans;
    }

    private void dfs(int i) {
        if (i == nums.length) {
            ans.add(new ArrayList<>(t));
            return;
        }
        dfs(i + 1);
        t.add(nums[i]);
        dfs(i + 1);
        t.remove(t.size() - 1);
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<vector<int>> subsets(vector<int>& nums) {
        vector<vector<int>> ans;
        vector<int> t;
        function<void(int)> dfs = [&](int i) -> void {
            if (i == nums.size()) {
                ans.push_back(t);
                return;
            }
            dfs(i + 1);
            t.push_back(nums[i]);
            dfs(i + 1);
            t.pop_back();
        };
        dfs(0);
        return ans;
    }
};
```

#### Go

```go
func subsets(nums []int) (ans [][]int) {
	t := []int{}
	var dfs func(int)
	dfs = func(i int) {
		if i == len(nums) {
			ans = append(ans, append([]int(nil), t...))
			return
		}
		dfs(i + 1)
		t = append(t, nums[i])
		dfs(i + 1)
		t = t[:len(t)-1]
	}
	dfs(0)
	return
}
```

#### TypeScript

```ts
function subsets(nums: number[]): number[][] {
    const ans: number[][] = [];
    const t: number[] = [];
    const dfs = (i: number) => {
        if (i === nums.length) {
            ans.push(t.slice());
            return;
        }
        dfs(i + 1);
        t.push(nums[i]);
        dfs(i + 1);
        t.pop();
    };
    dfs(0);
    return ans;
}
```

#### Rust

```rust
impl Solution {
    fn dfs(i: usize, t: &mut Vec<i32>, ans: &mut Vec<Vec<i32>>, nums: &Vec<i32>) {
        if i == nums.len() {
            ans.push(t.clone());
            return;
        }
        Self::dfs(i + 1, t, ans, nums);
        t.push(nums[i]);
        Self::dfs(i + 1, t, ans, nums);
        t.pop();
    }

    pub fn subsets(nums: Vec<i32>) -> Vec<Vec<i32>> {
        let mut ans = Vec::new();
        Self::dfs(0, &mut Vec::new(), &mut ans, &nums);
        ans
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
> Phương pháp 1 mở rộng lựa chọn chọn hoặc bỏ qua trên ngăn xếp lời gọi. $n$ nhỏ, vì vậy mỗi tập con có thể được biểu diễn bằng một mask trong $[0,2^n)$: bit $i$ bao gồm $nums[i]$. Không cần đệ quy; vẫn là cùng lượng công việc $O(n \times 2^n)$.

<!-- thinking:end -->

Chúng ta cũng có thể sử dụng phương pháp liệt kê nhị phân để lấy tất cả các tập con.

Chúng ta có thể sử dụng $2^n$ số nhị phân để biểu diễn tất cả các tập con của $n$ phần tử. Với số nhị phân hiện tại $mask$, nếu bit thứ $i$ là $1$, điều đó có nghĩa là phần tử thứ $i$ được chọn; nếu không thì có nghĩa là phần tử thứ $i$ không được chọn.

Độ phức tạp thời gian là $O(n \times 2^n)$, và độ phức tạp không gian là $O(n)$. Trong đó, $n$ là độ dài của mảng. Có tổng cộng $2^n$ tập con, và mỗi tập con cần $O(n)$ thời gian để xây dựng.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def subsets(self, nums: List[int]) -> List[List[int]]:
        ans = []
        for mask in range(1 << len(nums)):
            t = [x for i, x in enumerate(nums) if mask >> i & 1]
            ans.append(t)
        return ans
```

#### Java

```java
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        int n = nums.length;
        List<List<Integer>> ans = new ArrayList<>();
        for (int mask = 0; mask < 1 << n; ++mask) {
            List<Integer> t = new ArrayList<>();
            for (int i = 0; i < n; ++i) {
                if (((mask >> i) & 1) == 1) {
                    t.add(nums[i]);
                }
            }
            ans.add(t);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<vector<int>> subsets(vector<int>& nums) {
        int n = nums.size();
        vector<vector<int>> ans;
        for (int mask = 0; mask < 1 << n; ++mask) {
            vector<int> t;
            for (int i = 0; i < n; ++i) {
                if (mask >> i & 1) {
                    t.emplace_back(nums[i]);
                }
            }
            ans.emplace_back(t);
        }
        return ans;
    }
};
```

#### Go

```go
func subsets(nums []int) (ans [][]int) {
	n := len(nums)
	for mask := 0; mask < 1<<n; mask++ {
		t := []int{}
		for i, x := range nums {
			if mask>>i&1 == 1 {
				t = append(t, x)
			}
		}
		ans = append(ans, t)
	}
	return
}
```

#### TypeScript

```ts
function subsets(nums: number[]): number[][] {
    const n = nums.length;
    const ans: number[][] = [];
    for (let mask = 0; mask < 1 << n; ++mask) {
        const t: number[] = [];
        for (let i = 0; i < n; ++i) {
            if (((mask >> i) & 1) === 1) {
                t.push(nums[i]);
            }
        }
        ans.push(t);
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn subsets(nums: Vec<i32>) -> Vec<Vec<i32>> {
        let n = nums.len();
        let mut ans = Vec::new();
        for mask in 0..(1 << n) {
            let mut t = Vec::new();
            for i in 0..n {
                if (mask >> i) & 1 == 1 {
                    t.push(nums[i]);
                }
            }
            ans.push(t);
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
