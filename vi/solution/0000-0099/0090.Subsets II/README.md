---
comments: true
difficulty: Medium
tags:
    - Bit Manipulation
    - Array
    - Backtracking
---

<!-- problem:start -->

# [90. Subsets II](https://leetcode.com/problems/subsets-ii)

[中文文档](/solution/0000-0099/0090.Subsets%20II/README.md)

## Mô tả

<!-- description:start -->

<p>Với một mảng số nguyên <code>nums</code> có thể chứa các phần tử trùng lặp, hãy trả về <em>tất cả các</em> <span data-keyword="subset"><em>tập con</em></span><em> có thể có (tập lũy thừa)</em>.</p>

<p>Tập kết quả <strong>không được</strong> chứa các tập con trùng lặp. Trả về kết quả theo <strong>bất kỳ thứ tự nào</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>
<pre><strong>Đầu vào:</strong> nums = [1,2,2]
<strong>Đầu ra:</strong> [[],[1],[1,2],[1,2,2],[2],[2,2]]
</pre><p><strong class="example">Ví dụ 2:</strong></p>
<pre><strong>Đầu vào:</strong> nums = [0]
<strong>Đầu ra:</strong> [[],[0]]
</pre>
<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10</code></li>
	<li><code>-10 &lt;= nums[i] &lt;= 10</code></li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Sắp xếp + DFS

<!-- thinking:start -->

> **Tư duy**
>
> Ý tưởng đầu tiên giống với DFS của bài Subsets: chọn hoặc bỏ qua từng chỉ số. Cách này đúng, nhưng các phần tử trùng lặp khiến $[1, 2]$ xuất hiện hai lần. $n \le 10$ là nhỏ, tuy nhiên tập kết quả phải không chứa các tập con trùng lặp.
>
> Nút thắt là việc coi các giá trị bằng nhau ở những chỉ số khác nhau là các lựa chọn riêng biệt. Hãy sắp xếp để các giá trị bằng nhau nằm cạnh nhau; bỏ qua cùng một giá trị ở nhánh “không chọn” để mỗi đa tập được sinh đúng một lần.
>
> Sau khi sắp xếp, DFS sẽ chọn $i$, rồi trên đường bỏ qua sẽ nhảy qua mọi $\textit{nums}[i]$ bằng nhau ở phía sau. Số bản sao được chọn do độ sâu đệ quy quyết định, vì vậy không bao giờ tạo ra hai tập con giống hệt nhau cạnh nhau.

<!-- thinking:end -->

Trước hết, chúng ta có thể sắp xếp mảng $\textit{nums}$ để thuận tiện khử trùng lặp.

Sau đó, chúng ta thiết kế một hàm $\textit{dfs}(i)$, biểu thị việc tìm kiếm các tập con bắt đầu từ phần tử thứ $i$. Logic thực thi của hàm $\textit{dfs}(i)$ như sau:

Nếu $i \geq n$, điều đó có nghĩa là tất cả các phần tử đã được tìm kiếm; thêm tập con hiện tại vào mảng đáp án, rồi kết thúc đệ quy.

Nếu $i < n$, thêm phần tử thứ $i$ vào tập con, thực hiện $\textit{dfs}(i + 1)$, sau đó loại bỏ phần tử thứ $i$ khỏi tập con. Tiếp theo, chúng ta kiểm tra xem phần tử thứ $i$ có giống phần tử kế tiếp hay không. Nếu giống nhau, bỏ qua các phần tử trong một vòng lặp cho đến khi tìm thấy phần tử đầu tiên khác với phần tử thứ $i$, rồi thực hiện $\textit{dfs}(i + 1)$.

Cuối cùng, chúng ta chỉ cần gọi $\textit{dfs}(0)$ và trả về mảng đáp án.

Độ phức tạp thời gian là $O(n \times 2^n)$, và độ phức tạp không gian là $O(n)$. Trong đó, $n$ là độ dài của mảng $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def subsetsWithDup(self, nums: List[int]) -> List[List[int]]:
        def dfs(i: int):
            if i == len(nums):
                ans.append(t[:])
                return
            t.append(nums[i])
            dfs(i + 1)
            x = t.pop()
            while i + 1 < len(nums) and nums[i + 1] == x:
                i += 1
            dfs(i + 1)

        nums.sort()
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

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        this.nums = nums;
        dfs(0);
        return ans;
    }

    private void dfs(int i) {
        if (i >= nums.length) {
            ans.add(new ArrayList<>(t));
            return;
        }
        t.add(nums[i]);
        dfs(i + 1);
        int x = t.remove(t.size() - 1);
        while (i + 1 < nums.length && nums[i + 1] == x) {
            ++i;
        }
        dfs(i + 1);
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<vector<int>> subsetsWithDup(vector<int>& nums) {
        ranges::sort(nums);
        vector<vector<int>> ans;
        vector<int> t;
        int n = nums.size();
        auto dfs = [&](this auto&& dfs, int i) {
            if (i >= n) {
                ans.push_back(t);
                return;
            }
            t.push_back(nums[i]);
            dfs(i + 1);
            t.pop_back();
            while (i + 1 < n && nums[i + 1] == nums[i]) {
                ++i;
            }
            dfs(i + 1);
        };
        dfs(0);
        return ans;
    }
};
```

#### Go

```go
func subsetsWithDup(nums []int) (ans [][]int) {
	slices.Sort(nums)
	n := len(nums)
	t := []int{}
	var dfs func(int)
	dfs = func(i int) {
		if i >= n {
			ans = append(ans, slices.Clone(t))
			return
		}
		t = append(t, nums[i])
		dfs(i + 1)
		t = t[:len(t)-1]
		for i+1 < n && nums[i+1] == nums[i] {
			i++
		}
		dfs(i + 1)
	}
	dfs(0)
	return
}
```

#### TypeScript

```ts
function subsetsWithDup(nums: number[]): number[][] {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    const t: number[] = [];
    const ans: number[][] = [];
    const dfs = (i: number): void => {
        if (i >= n) {
            ans.push([...t]);
            return;
        }
        t.push(nums[i]);
        dfs(i + 1);
        t.pop();
        while (i + 1 < n && nums[i] === nums[i + 1]) {
            i++;
        }
        dfs(i + 1);
    };
    dfs(0);
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn subsets_with_dup(nums: Vec<i32>) -> Vec<Vec<i32>> {
        let mut nums = nums;
        nums.sort();
        let mut ans = Vec::new();
        let mut t = Vec::new();

        fn dfs(i: usize, nums: &Vec<i32>, t: &mut Vec<i32>, ans: &mut Vec<Vec<i32>>) {
            if i >= nums.len() {
                ans.push(t.clone());
                return;
            }
            t.push(nums[i]);
            dfs(i + 1, nums, t, ans);
            t.pop();
            let mut i = i;
            while i + 1 < nums.len() && nums[i + 1] == nums[i] {
                i += 1;
            }
            dfs(i + 1, nums, t, ans);
        }

        dfs(0, &nums, &mut t, &mut ans);
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
var subsetsWithDup = function (nums) {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    const t = [];
    const ans = [];
    const dfs = i => {
        if (i >= n) {
            ans.push([...t]);
            return;
        }
        t.push(nums[i]);
        dfs(i + 1);
        t.pop();
        while (i + 1 < n && nums[i] === nums[i + 1]) {
            i++;
        }
        dfs(i + 1);
    };
    dfs(0);
    return ans;
};
```

#### C#

```cs
public class Solution {
    private IList<IList<int>> ans = new List<IList<int>>();
    private IList<int> t = new List<int>();
    private int[] nums;

    public IList<IList<int>> SubsetsWithDup(int[] nums) {
        Array.Sort(nums);
        this.nums = nums;
        Dfs(0);
        return ans;
    }

    private void Dfs(int i) {
        if (i >= nums.Length) {
            ans.Add(new List<int>(t));
            return;
        }
        t.Add(nums[i]);
        Dfs(i + 1);
        t.RemoveAt(t.Count - 1);
        while (i + 1 < nums.Length && nums[i + 1] == nums[i]) {
            ++i;
        }
        Dfs(i + 1);
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Lời giải 2: Sắp xếp + Liệt kê nhị phân

<!-- thinking:start -->

> **Tư duy**
>
> Lời giải 1 đã khử trùng lặp, nhưng đệ quy phải sử dụng ngăn xếp và logic bỏ qua rất dễ viết sai. $n \le 10$, vì vậy chúng ta có thể liệt kê tất cả các mask $2^n$.
>
> Điều còn thiếu là biểu diễn “chọn hoặc bỏ qua” bằng các bit. Bit $i$ của $\textit{mask}$ biểu thị việc chọn $\textit{nums}[i]$; khi chọn $i$ mà không chọn $i - 1$ trong trường hợp hai giá trị bằng nhau, ta đang bỏ qua bản sao xuất hiện trước, tức là tạo ra một bản trùng lặp, nên loại bỏ nó. Cùng độ phức tạp tiệm cận, nhưng chỉ cần một vòng lặp.

<!-- thinking:end -->

Tương tự như Lời giải 1, trước hết chúng ta sắp xếp mảng $\textit{nums}$ để thuận tiện khử trùng lặp.

Tiếp theo, chúng ta liệt kê một số nhị phân $\textit{mask}$ trong phạm vi $[0, 2^n)$, trong đó biểu diễn nhị phân của $\textit{mask}$ là một chuỗi bit gồm $n$ bit. Nếu bit thứ $i$ của $\textit{mask}$ là $1$, điều đó có nghĩa là chọn $\textit{nums}[i]$, còn $0$ có nghĩa là không chọn $\textit{nums}[i]$. Lưu ý rằng nếu bit thứ $(i - 1)$ của $\textit{mask}$ là $0$ và $\textit{nums}[i] = \textit{nums}[i - 1]$, điều đó có nghĩa là phần tử thứ $i$ giống với phần tử thứ $(i - 1)$ trong cách liệt kê hiện tại. Để tránh trùng lặp, chúng ta bỏ qua trường hợp này. Nếu không, chúng ta thêm tập con tương ứng với $\textit{mask}$ vào mảng đáp án.

Sau khi liệt kê xong, chúng ta trả về mảng đáp án.

Độ phức tạp thời gian là $O(n \times 2^n)$, và độ phức tạp không gian là $O(n)$. Trong đó, $n$ là độ dài của mảng $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def subsetsWithDup(self, nums: List[int]) -> List[List[int]]:
        nums.sort()
        n = len(nums)
        ans = []
        for mask in range(1 << n):
            ok = True
            t = []
            for i in range(n):
                if mask >> i & 1:
                    if i and (mask >> (i - 1) & 1) == 0 and nums[i] == nums[i - 1]:
                        ok = False
                        break
                    t.append(nums[i])
            if ok:
                ans.append(t)
        return ans
```

#### Java

```java
class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        List<List<Integer>> ans = new ArrayList<>();
        for (int mask = 0; mask < 1 << n; ++mask) {
            List<Integer> t = new ArrayList<>();
            boolean ok = true;
            for (int i = 0; i < n; ++i) {
                if ((mask >> i & 1) == 1) {
                    if (i > 0 && (mask >> (i - 1) & 1) == 0 && nums[i] == nums[i - 1]) {
                        ok = false;
                        break;
                    }
                    t.add(nums[i]);
                }
            }
            if (ok) {
                ans.add(t);
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
    vector<vector<int>> subsetsWithDup(vector<int>& nums) {
        ranges::sort(nums);
        int n = nums.size();
        vector<vector<int>> ans;
        for (int mask = 0; mask < 1 << n; ++mask) {
            vector<int> t;
            bool ok = true;
            for (int i = 0; i < n; ++i) {
                if ((mask >> i & 1) == 1) {
                    if (i > 0 && (mask >> (i - 1) & 1) == 0 && nums[i] == nums[i - 1]) {
                        ok = false;
                        break;
                    }
                    t.push_back(nums[i]);
                }
            }
            if (ok) {
                ans.push_back(t);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func subsetsWithDup(nums []int) (ans [][]int) {
	sort.Ints(nums)
	n := len(nums)
	for mask := 0; mask < 1<<n; mask++ {
		t := []int{}
		ok := true
		for i := 0; i < n; i++ {
			if mask>>i&1 == 1 {
				if i > 0 && mask>>(i-1)&1 == 0 && nums[i] == nums[i-1] {
					ok = false
					break
				}
				t = append(t, nums[i])
			}
		}
		if ok {
			ans = append(ans, t)
		}
	}
	return
}
```

#### TypeScript

```ts
function subsetsWithDup(nums: number[]): number[][] {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    const ans: number[][] = [];
    for (let mask = 0; mask < 1 << n; ++mask) {
        const t: number[] = [];
        let ok: boolean = true;
        for (let i = 0; i < n; ++i) {
            if (((mask >> i) & 1) === 1) {
                if (i && ((mask >> (i - 1)) & 1) === 0 && nums[i] === nums[i - 1]) {
                    ok = false;
                    break;
                }
                t.push(nums[i]);
            }
        }
        if (ok) {
            ans.push(t);
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn subsets_with_dup(nums: Vec<i32>) -> Vec<Vec<i32>> {
        let mut nums = nums;
        nums.sort();
        let n = nums.len();
        let mut ans = Vec::new();
        for mask in 0..1 << n {
            let mut t = Vec::new();
            let mut ok = true;
            for i in 0..n {
                if ((mask >> i) & 1) == 1 {
                    if i > 0 && ((mask >> (i - 1)) & 1) == 0 && nums[i] == nums[i - 1] {
                        ok = false;
                        break;
                    }
                    t.push(nums[i]);
                }
            }
            if ok {
                ans.push(t);
            }
        }
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
var subsetsWithDup = function (nums) {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    const ans = [];
    for (let mask = 0; mask < 1 << n; ++mask) {
        const t = [];
        let ok = true;
        for (let i = 0; i < n; ++i) {
            if (((mask >> i) & 1) === 1) {
                if (i && ((mask >> (i - 1)) & 1) === 0 && nums[i] === nums[i - 1]) {
                    ok = false;
                    break;
                }
                t.push(nums[i]);
            }
        }
        if (ok) {
            ans.push(t);
        }
    }
    return ans;
};
```

#### C#

```cs
public class Solution {
    public IList<IList<int>> SubsetsWithDup(int[] nums) {
        Array.Sort(nums);
        int n = nums.Length;
        IList<IList<int>> ans = new List<IList<int>>();
        for (int mask = 0; mask < 1 << n; ++mask) {
            IList<int> t = new List<int>();
            bool ok = true;
            for (int i = 0; i < n; ++i) {
                if ((mask >> i & 1) == 1) {
                    if (i > 0 && (mask >> (i - 1) & 1) == 0 && nums[i] == nums[i - 1]) {
                        ok = false;
                        break;
                    }
                    t.Add(nums[i]);
                }
            }
            if (ok) {
                ans.Add(t);
            }
        }
        return ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
