---
comments: true
difficulty: Medium
tags:
    - Array
    - Backtracking
    - Sorting
---

<!-- problem:start -->

# [47. Permutations II](https://leetcode.com/problems/permutations-ii)

[中文文档](/solution/0000-0099/0047.Permutations%20II/README.md)

## Mô tả

<!-- description:start -->

<p>Cho một tập hợp các số, <code>nums</code>, có thể chứa các phần tử trùng lặp, hãy trả về <em>tất cả các hoán vị duy nhất có thể có <strong>theo bất kỳ thứ tự nào</strong>.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong> nums = [1,1,2]
<strong>Đầu ra:</strong>
[[1,1,2],
 [1,2,1],
 [2,1,1]]
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> nums = [1,2,3]
<strong>Đầu ra:</strong> [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 8</code></li>
	<li><code>-10 &lt;= nums[i] &lt;= 10</code></li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Sắp xếp + Quay lui

<!-- thinking:start -->

> **Tư duy**
>
> Ý tưởng đầu tiên là dùng cùng cách quay lui như với các hoán vị duy nhất, sau đó dùng một tập hợp để loại bỏ các kết quả trùng lặp. Cách này đúng, nhưng khi $n \le 8$ và có các số lặp lại, các nhánh con của cây bị sao chép; lọc sau cùng gây lãng phí thời gian và không gian.
>
> Điểm nghẽn nằm ở những nhánh trùng lặp đó. Hoán đổi hai giá trị bằng nhau ở cùng một độ sâu sẽ tạo ra cùng một hoán vị — mỗi giá trị chỉ được chọn một lần ở mỗi tầng.
>
> Hãy sắp xếp để các giá trị bằng nhau nằm cạnh nhau, sau đó bỏ qua một ứng viên khi phần tử bằng nó trước đó vẫn chưa được sử dụng. Mỗi giá trị phân biệt chỉ mở rộng một lần tại một vị trí; mọi hoán vị được tạo ra đều là duy nhất.

<!-- thinking:end -->

Trước tiên, chúng ta có thể sắp xếp mảng để các số trùng lặp nằm cạnh nhau, giúp việc loại bỏ trùng lặp dễ dàng hơn.

Sau đó, chúng ta thiết kế một hàm $\textit{dfs}(i)$, biểu thị số hiện tại cần được đặt vào vị trí thứ $i$. Cách triển khai cụ thể của hàm như sau:

- Nếu $i = n$, điều đó có nghĩa là chúng ta đã điền đầy đủ tất cả các vị trí, thêm hoán vị hiện tại vào mảng kết quả, rồi trả về.
- Nếu không, chúng ta liệt kê số $nums[j]$ cho vị trí thứ $i$, trong đó phạm vi của $j$ là $[0, n - 1]$. Chúng ta cần đảm bảo rằng $nums[j]$ chưa được sử dụng và khác với số vừa được liệt kê trước đó để bảo đảm hoán vị hiện tại không bị trùng lặp. Nếu thỏa mãn các điều kiện, chúng ta có thể đặt $nums[j]$ rồi tiếp tục điền đệ quy vị trí tiếp theo bằng cách gọi $\textit{dfs}(i + 1)$. Sau khi lời gọi đệ quy kết thúc, chúng ta cần đánh dấu $nums[j]$ là chưa được sử dụng để phục vụ cho các lần liệt kê tiếp theo.

Trong hàm chính, trước tiên chúng ta sắp xếp mảng, sau đó gọi $\textit{dfs}(0)$ để bắt đầu điền từ vị trí thứ 0, và cuối cùng trả về mảng kết quả.

Độ phức tạp thời gian là $O(n \times n!)$, và độ phức tạp không gian là $O(n)$. Trong đó, $n$ là độ dài của mảng. Chúng ta cần thực hiện $n!$ lần liệt kê, và mỗi lần liệt kê cần $O(n)$ thời gian để kiểm tra trùng lặp. Ngoài ra, chúng ta cần một mảng đánh dấu để đánh dấu xem mỗi vị trí đã được sử dụng hay chưa, do đó độ phức tạp không gian là $O(n)$.

Các bài tương tự:

- [46. Permutations](https://github.com/doocs/leetcode/blob/main/solution/0000-0099/0046.Permutations/README_EN.md)

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def permuteUnique(self, nums: List[int]) -> List[List[int]]:
        def dfs(i: int):
            if i == n:
                ans.append(t[:])
                return
            for j in range(n):
                if vis[j] or (j and nums[j] == nums[j - 1] and not vis[j - 1]):
                    continue
                t[i] = nums[j]
                vis[j] = True
                dfs(i + 1)
                vis[j] = False

        n = len(nums)
        nums.sort()
        ans = []
        t = [0] * n
        vis = [False] * n
        dfs(0)
        return ans
```

#### Java

```java
class Solution {
    private List<List<Integer>> ans = new ArrayList<>();
    private List<Integer> t = new ArrayList<>();
    private int[] nums;
    private boolean[] vis;

    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        this.nums = nums;
        vis = new boolean[nums.length];
        dfs(0);
        return ans;
    }

    private void dfs(int i) {
        if (i == nums.length) {
            ans.add(new ArrayList<>(t));
            return;
        }
        for (int j = 0; j < nums.length; ++j) {
            if (vis[j] || (j > 0 && nums[j] == nums[j - 1] && !vis[j - 1])) {
                continue;
            }
            t.add(nums[j]);
            vis[j] = true;
            dfs(i + 1);
            vis[j] = false;
            t.remove(t.size() - 1);
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<vector<int>> permuteUnique(vector<int>& nums) {
        ranges::sort(nums);
        int n = nums.size();
        vector<vector<int>> ans;
        vector<int> t(n);
        vector<bool> vis(n);
        auto dfs = [&](this auto&& dfs, int i) {
            if (i == n) {
                ans.emplace_back(t);
                return;
            }
            for (int j = 0; j < n; ++j) {
                if (vis[j] || (j && nums[j] == nums[j - 1] && !vis[j - 1])) {
                    continue;
                }
                t[i] = nums[j];
                vis[j] = true;
                dfs(i + 1);
                vis[j] = false;
            }
        };
        dfs(0);
        return ans;
    }
};
```

#### Go

```go
func permuteUnique(nums []int) (ans [][]int) {
	slices.Sort(nums)
	n := len(nums)
	t := make([]int, n)
	vis := make([]bool, n)
	var dfs func(int)
	dfs = func(i int) {
		if i == n {
			ans = append(ans, slices.Clone(t))
			return
		}
		for j := 0; j < n; j++ {
			if vis[j] || (j > 0 && nums[j] == nums[j-1] && !vis[j-1]) {
				continue
			}
			vis[j] = true
			t[i] = nums[j]
			dfs(i + 1)
			vis[j] = false
		}
	}
	dfs(0)
	return
}
```

#### TypeScript

```ts
function permuteUnique(nums: number[]): number[][] {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    const ans: number[][] = [];
    const t: number[] = Array(n);
    const vis: boolean[] = Array(n).fill(false);
    const dfs = (i: number) => {
        if (i === n) {
            ans.push(t.slice());
            return;
        }
        for (let j = 0; j < n; ++j) {
            if (vis[j] || (j > 0 && nums[j] === nums[j - 1] && !vis[j - 1])) {
                continue;
            }
            t[i] = nums[j];
            vis[j] = true;
            dfs(i + 1);
            vis[j] = false;
        }
    };
    dfs(0);
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn permute_unique(mut nums: Vec<i32>) -> Vec<Vec<i32>> {
        nums.sort();
        let n = nums.len();
        let mut ans = Vec::new();
        let mut t = vec![0; n];
        let mut vis = vec![false; n];

        fn dfs(
            nums: &Vec<i32>,
            t: &mut Vec<i32>,
            vis: &mut Vec<bool>,
            ans: &mut Vec<Vec<i32>>,
            i: usize,
        ) {
            if i == nums.len() {
                ans.push(t.clone());
                return;
            }
            for j in 0..nums.len() {
                if vis[j] || (j > 0 && nums[j] == nums[j - 1] && !vis[j - 1]) {
                    continue;
                }
                t[i] = nums[j];
                vis[j] = true;
                dfs(nums, t, vis, ans, i + 1);
                vis[j] = false;
            }
        }

        dfs(&nums, &mut t, &mut vis, &mut ans, 0);
        ans
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums
 * @return {number[][]}
 */
var permuteUnique = function (nums) {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    const ans = [];
    const t = Array(n);
    const vis = Array(n).fill(false);
    const dfs = i => {
        if (i === n) {
            ans.push(t.slice());
            return;
        }
        for (let j = 0; j < n; ++j) {
            if (vis[j] || (j > 0 && nums[j] === nums[j - 1] && !vis[j - 1])) {
                continue;
            }
            t[i] = nums[j];
            vis[j] = true;
            dfs(i + 1);
            vis[j] = false;
        }
    };
    dfs(0);
    return ans;
};
```

#### C#

```cs
public class Solution {
    private List<IList<int>> ans = new List<IList<int>>();
    private List<int> t = new List<int>();
    private int[] nums;
    private bool[] vis;

    public IList<IList<int>> PermuteUnique(int[] nums) {
        Array.Sort(nums);
        int n = nums.Length;
        vis = new bool[n];
        this.nums = nums;
        dfs(0);
        return ans;
    }

    private void dfs(int i) {
        if (i == nums.Length) {
            ans.Add(new List<int>(t));
            return;
        }
        for (int j = 0; j < nums.Length; ++j) {
            if (vis[j] || (j > 0 && nums[j] == nums[j - 1] && !vis[j - 1])) {
                continue;
            }
            vis[j] = true;
            t.Add(nums[j]);
            dfs(i + 1);
            t.RemoveAt(t.Count - 1);
            vis[j] = false;
        }
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
