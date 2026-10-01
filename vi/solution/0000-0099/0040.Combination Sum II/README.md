---
comments: true
difficulty: Medium
tags:
    - Array
    - Backtracking
---

<!-- problem:start -->

# [40. Combination Sum II](https://leetcode.com/problems/combination-sum-ii)

[中文文档](/solution/0000-0099/0040.Combination%20Sum%20II/README.md)

## Mô tả

<!-- description:start -->

<p>Cho một tập hợp các số ứng viên (<code>candidates</code>) và một số đích (<code>target</code>), hãy tìm tất cả các tổ hợp duy nhất trong <code>candidates</code> sao cho tổng các số trong tổ hợp bằng <code>target</code>.</p>

<p>Mỗi số trong <code>candidates</code> chỉ có thể được sử dụng <strong>một lần</strong> trong tổ hợp.</p>

<p><strong>Lưu ý:</strong>&nbsp;Tập nghiệm không được chứa các tổ hợp trùng lặp.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong> candidates = [10,1,2,7,6,1,5], target = 8
<strong>Đầu ra:</strong> 
[
[1,1,6],
[1,2,5],
[1,7],
[2,6]
]
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> candidates = [2,5,2,1,2], target = 5
<strong>Đầu ra:</strong> 
[
[1,2,2],
[5]
]
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;=&nbsp;candidates.length &lt;= 100</code></li>
	<li><code>1 &lt;=&nbsp;candidates[i] &lt;= 50</code></li>
	<li><code>1 &lt;= target &lt;= 30</code></li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Sắp xếp + Cắt tỉa + Quay lui

<!-- thinking:start -->

> **Tư duy**
>
> Khác với bài trước, mỗi số chỉ có thể được sử dụng một lần, và $candidates$ có thể chứa các giá trị trùng lặp. Nếu không loại bỏ trùng lặp, cùng một đa tập sẽ được tìm thấy qua các tổ hợp chỉ số khác nhau. $n \le 100$ và $target \le 30$ khiến việc cắt tỉa càng quan trọng hơn.
>
> Sắp xếp để các giá trị bằng nhau nằm cạnh nhau. Ở cùng một độ sâu, nếu $j \gt i$ và $candidates[j]$ bằng giá trị trước đó, giá trị đó đã được thử làm “lựa chọn của cấp này”, nên chúng ta bỏ qua nó.
>
> Lời gọi đệ quy là $dfs(j+1,\ldots)$: chỉ số này không thể được sử dụng lại. Nếu phần dư $s$ đã nhỏ hơn giá trị hiện tại, toàn bộ nhánh sẽ dừng lại.

<!-- thinking:end -->

Trước hết, chúng ta có thể sắp xếp mảng để thuận tiện cho việc cắt tỉa và bỏ qua các số trùng lặp.

Tiếp theo, chúng ta thiết kế một hàm $dfs(i, s)$, biểu thị việc bắt đầu tìm kiếm từ chỉ số $i$ với giá trị target còn lại là $s$. Ở đây, $i$ và $s$ đều là các số nguyên không âm, đường đi tìm kiếm hiện tại là $t$, và đáp án là $ans$.

Trong hàm $dfs(i, s)$, trước tiên chúng ta kiểm tra xem $s$ có bằng $0$ hay không. Nếu có, chúng ta thêm đường đi tìm kiếm hiện tại $t$ vào đáp án $ans$, sau đó trả về. Nếu $i \geq n$ hoặc $s \lt candidates[i]$, đường đi không hợp lệ, nên chúng ta trả về ngay. Nếu không, chúng ta bắt đầu tìm kiếm từ chỉ số $i$, và phạm vi chỉ số tìm kiếm là $j \in [i, n)$, trong đó $n$ là độ dài của mảng $candidates$. Trong quá trình tìm kiếm, nếu $j \gt i$ và $candidates[j] = candidates[j - 1]$, điều đó có nghĩa là số hiện tại giống với số trước đó, chúng ta có thể bỏ qua số hiện tại vì số trước đó đã được tìm kiếm. Nếu không, chúng ta thêm số hiện tại vào đường đi tìm kiếm $t$, gọi đệ quy hàm $dfs(j + 1, s - candidates[j])$, và sau khi đệ quy kết thúc, chúng ta xóa số hiện tại khỏi đường đi tìm kiếm $t$.

Chúng ta cũng có thể thay đổi logic triển khai của hàm $dfs(i, s)$ thành một dạng khác. Nếu chọn số hiện tại, chúng ta thêm số hiện tại vào đường đi tìm kiếm $t$, sau đó gọi đệ quy hàm $dfs(i + 1, s - candidates[i])$, và sau khi đệ quy kết thúc, chúng ta xóa số hiện tại khỏi đường đi tìm kiếm $t$. Nếu không chọn số hiện tại, chúng ta có thể bỏ qua tất cả các số giống với số hiện tại, sau đó gọi đệ quy hàm $dfs(j, s)$, trong đó $j$ là chỉ số của số đầu tiên khác với số hiện tại.

Trong hàm chính, chúng ta chỉ cần gọi hàm $dfs(0, target)$ để nhận được đáp án.

Độ phức tạp thời gian là $O(2^n \times n)$, và độ phức tạp không gian là $O(n)$. Trong đó, $n$ là độ dài của mảng $candidates$. Nhờ việc cắt tỉa, độ phức tạp thời gian thực tế nhỏ hơn nhiều so với $O(2^n \times n)$.

Các bài tương tự:

- [39. Combination Sum](https://github.com/doocs/leetcode/blob/main/solution/0000-0099/0039.Combination%20Sum/README_EN.md)
- [77. Combinations](https://github.com/doocs/leetcode/blob/main/solution/0000-0099/0077.Combinations/README_EN.md)
- [216. Combination Sum III](https://github.com/doocs/leetcode/blob/main/solution/0200-0299/0216.Combination%20Sum%20III/README_EN.md)

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def combinationSum2(self, candidates: List[int], target: int) -> List[List[int]]:
        def dfs(i: int, s: int):
            if s == 0:
                ans.append(t[:])
                return
            if i >= len(candidates) or s < candidates[i]:
                return
            for j in range(i, len(candidates)):
                if j > i and candidates[j] == candidates[j - 1]:
                    continue
                t.append(candidates[j])
                dfs(j + 1, s - candidates[j])
                t.pop()

        candidates.sort()
        ans = []
        t = []
        dfs(0, target)
        return ans
```

#### Java

```java
class Solution {
    private List<List<Integer>> ans = new ArrayList<>();
    private List<Integer> t = new ArrayList<>();
    private int[] candidates;

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        this.candidates = candidates;
        dfs(0, target);
        return ans;
    }

    private void dfs(int i, int s) {
        if (s == 0) {
            ans.add(new ArrayList<>(t));
            return;
        }
        if (i >= candidates.length || s < candidates[i]) {
            return;
        }
        for (int j = i; j < candidates.length; ++j) {
            if (j > i && candidates[j] == candidates[j - 1]) {
                continue;
            }
            t.add(candidates[j]);
            dfs(j + 1, s - candidates[j]);
            t.remove(t.size() - 1);
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<vector<int>> combinationSum2(vector<int>& candidates, int target) {
        sort(candidates.begin(), candidates.end());
        vector<vector<int>> ans;
        vector<int> t;
        function<void(int, int)> dfs = [&](int i, int s) {
            if (s == 0) {
                ans.emplace_back(t);
                return;
            }
            if (i >= candidates.size() || s < candidates[i]) {
                return;
            }
            for (int j = i; j < candidates.size(); ++j) {
                if (j > i && candidates[j] == candidates[j - 1]) {
                    continue;
                }
                t.emplace_back(candidates[j]);
                dfs(j + 1, s - candidates[j]);
                t.pop_back();
            }
        };
        dfs(0, target);
        return ans;
    }
};
```

#### Go

```go
func combinationSum2(candidates []int, target int) (ans [][]int) {
	sort.Ints(candidates)
	t := []int{}
	var dfs func(i, s int)
	dfs = func(i, s int) {
		if s == 0 {
			ans = append(ans, slices.Clone(t))
			return
		}
		if i >= len(candidates) || s < candidates[i] {
			return
		}
		for j := i; j < len(candidates); j++ {
			if j > i && candidates[j] == candidates[j-1] {
				continue
			}
			t = append(t, candidates[j])
			dfs(j+1, s-candidates[j])
			t = t[:len(t)-1]
		}
	}
	dfs(0, target)
	return
}
```

#### TypeScript

```ts
function combinationSum2(candidates: number[], target: number): number[][] {
    candidates.sort((a, b) => a - b);
    const ans: number[][] = [];
    const t: number[] = [];
    const dfs = (i: number, s: number) => {
        if (s === 0) {
            ans.push(t.slice());
            return;
        }
        if (i >= candidates.length || s < candidates[i]) {
            return;
        }
        for (let j = i; j < candidates.length; j++) {
            if (j > i && candidates[j] === candidates[j - 1]) {
                continue;
            }
            t.push(candidates[j]);
            dfs(j + 1, s - candidates[j]);
            t.pop();
        }
    };
    dfs(0, target);
    return ans;
}
```

#### Rust

```rust
impl Solution {
    fn dfs(i: usize, s: i32, candidates: &Vec<i32>, t: &mut Vec<i32>, ans: &mut Vec<Vec<i32>>) {
        if s == 0 {
            ans.push(t.clone());
            return;
        }
        if i >= candidates.len() || s < candidates[i] {
            return;
        }
        for j in i..candidates.len() {
            if j > i && candidates[j] == candidates[j - 1] {
                continue;
            }
            t.push(candidates[j]);
            Self::dfs(j + 1, s - candidates[j], candidates, t, ans);
            t.pop();
        }
    }

    pub fn combination_sum2(mut candidates: Vec<i32>, target: i32) -> Vec<Vec<i32>> {
        candidates.sort();
        let mut ans = Vec::new();
        Self::dfs(0, target, &candidates, &mut vec![], &mut ans);
        ans
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} candidates
 * @param {number} target
 * @return {number[][]}
 */
var combinationSum2 = function (candidates, target) {
    candidates.sort((a, b) => a - b);
    const ans = [];
    const t = [];
    const dfs = (i, s) => {
        if (s === 0) {
            ans.push(t.slice());
            return;
        }
        if (i >= candidates.length || s < candidates[i]) {
            return;
        }
        for (let j = i; j < candidates.length; ++j) {
            if (j > i && candidates[j] === candidates[j - 1]) {
                continue;
            }
            t.push(candidates[j]);
            dfs(j + 1, s - candidates[j]);
            t.pop();
        }
    };
    dfs(0, target);
    return ans;
};
```

#### C#

```cs
public class Solution {
    private List<IList<int>> ans = new List<IList<int>>();
    private List<int> t = new List<int>();
    private int[] candidates;

    public IList<IList<int>> CombinationSum2(int[] candidates, int target) {
        Array.Sort(candidates);
        this.candidates = candidates;
        dfs(0, target);
        return ans;
    }

    private void dfs(int i, int s) {
        if (s == 0) {
            ans.Add(new List<int>(t));
            return;
        }
        if (i >= candidates.Length || s < candidates[i]) {
            return;
        }
        for (int j = i; j < candidates.Length; ++j) {
            if (j > i && candidates[j] == candidates[j - 1]) {
                continue;
            }
            t.Add(candidates[j]);
            dfs(j + 1, s - candidates[j]);
            t.RemoveAt(t.Count - 1);
        }
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Lời giải 2: Sắp xếp + Cắt tỉa + Quay lui (Dạng khác)

<!-- thinking:start -->

> **Tư duy**
>
> Cách 1 bỏ qua các phần tử trùng ở cùng độ sâu bên trong vòng lặp. Thay vào đó, chúng ta có thể chọn hoặc bỏ qua: khi lấy $x$ thì chỉ số tăng lên một; khi bỏ qua thì nhảy qua toàn bộ dãy liên tiếp của $x$, vì vậy cùng một tổ hợp không thể xuất hiện lại qua các chỉ số khác nhau. Độ phức tạp không đổi; việc loại trùng chỉ được chuyển sang nhánh “bỏ qua”.

<!-- thinking:end -->

Chúng ta cũng có thể thay đổi logic triển khai của hàm $dfs(i, s)$ thành một dạng khác. Nếu chọn số hiện tại, chúng ta thêm số hiện tại vào đường đi tìm kiếm $t$, sau đó gọi đệ quy hàm $dfs(i + 1, s - candidates[i])$, và sau khi đệ quy kết thúc, chúng ta xóa số hiện tại khỏi đường đi tìm kiếm $t$. Nếu không chọn số hiện tại, chúng ta có thể bỏ qua tất cả các số giống với số hiện tại, sau đó gọi đệ quy hàm $dfs(j, s)$, trong đó $j$ là chỉ số của số đầu tiên khác với số hiện tại.

Độ phức tạp thời gian là $O(2^n \times n)$, và độ phức tạp không gian là $O(n)$. Trong đó, $n$ là độ dài của mảng $candidates$. Nhờ việc cắt tỉa, độ phức tạp thời gian thực tế nhỏ hơn nhiều so với $O(2^n \times n)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def combinationSum2(self, candidates: List[int], target: int) -> List[List[int]]:
        def dfs(i: int, s: int):
            if s == 0:
                ans.append(t[:])
                return
            if i >= len(candidates) or s < candidates[i]:
                return
            x = candidates[i]
            t.append(x)
            dfs(i + 1, s - x)
            t.pop()
            while i < len(candidates) and candidates[i] == x:
                i += 1
            dfs(i, s)

        candidates.sort()
        ans = []
        t = []
        dfs(0, target)
        return ans
```

#### Java

```java
class Solution {
    private List<List<Integer>> ans = new ArrayList<>();
    private List<Integer> t = new ArrayList<>();
    private int[] candidates;

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        this.candidates = candidates;
        dfs(0, target);
        return ans;
    }

    private void dfs(int i, int s) {
        if (s == 0) {
            ans.add(new ArrayList<>(t));
            return;
        }
        if (i >= candidates.length || s < candidates[i]) {
            return;
        }
        int x = candidates[i];
        t.add(x);
        dfs(i + 1, s - x);
        t.remove(t.size() - 1);
        while (i < candidates.length && candidates[i] == x) {
            ++i;
        }
        dfs(i, s);
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<vector<int>> combinationSum2(vector<int>& candidates, int target) {
        sort(candidates.begin(), candidates.end());
        vector<vector<int>> ans;
        vector<int> t;
        function<void(int, int)> dfs = [&](int i, int s) {
            if (s == 0) {
                ans.emplace_back(t);
                return;
            }
            if (i >= candidates.size() || s < candidates[i]) {
                return;
            }
            int x = candidates[i];
            t.emplace_back(x);
            dfs(i + 1, s - x);
            t.pop_back();
            while (i < candidates.size() && candidates[i] == x) {
                ++i;
            }
            dfs(i, s);
        };
        dfs(0, target);
        return ans;
    }
};
```

#### Go

```go
func combinationSum2(candidates []int, target int) (ans [][]int) {
	sort.Ints(candidates)
	t := []int{}
	var dfs func(i, s int)
	dfs = func(i, s int) {
		if s == 0 {
			ans = append(ans, slices.Clone(t))
			return
		}
		if i >= len(candidates) || s < candidates[i] {
			return
		}
		for j := i; j < len(candidates); j++ {
			if j > i && candidates[j] == candidates[j-1] {
				continue
			}
			t = append(t, candidates[j])
			dfs(j+1, s-candidates[j])
			t = t[:len(t)-1]
		}
	}
	dfs(0, target)
	return
}
```

#### TypeScript

```ts
function combinationSum2(candidates: number[], target: number): number[][] {
    candidates.sort((a, b) => a - b);
    const ans: number[][] = [];
    const t: number[] = [];
    const dfs = (i: number, s: number) => {
        if (s === 0) {
            ans.push(t.slice());
            return;
        }
        if (i >= candidates.length || s < candidates[i]) {
            return;
        }
        const x = candidates[i];
        t.push(x);
        dfs(i + 1, s - x);
        t.pop();
        while (i < candidates.length && candidates[i] === x) {
            ++i;
        }
        dfs(i, s);
    };
    dfs(0, target);
    return ans;
}
```

#### Rust

```rust
impl Solution {
    fn dfs(mut i: usize, s: i32, candidates: &Vec<i32>, t: &mut Vec<i32>, ans: &mut Vec<Vec<i32>>) {
        if s == 0 {
            ans.push(t.clone());
            return;
        }
        if i >= candidates.len() || s < candidates[i] {
            return;
        }
        let x = candidates[i];
        t.push(x);
        Self::dfs(i + 1, s - x, candidates, t, ans);
        t.pop();
        while i < candidates.len() && candidates[i] == x {
            i += 1;
        }
        Self::dfs(i, s, candidates, t, ans);
    }

    pub fn combination_sum2(mut candidates: Vec<i32>, target: i32) -> Vec<Vec<i32>> {
        candidates.sort();
        let mut ans = Vec::new();
        Self::dfs(0, target, &candidates, &mut vec![], &mut ans);
        ans
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} candidates
 * @param {number} target
 * @return {number[][]}
 */
var combinationSum2 = function (candidates, target) {
    candidates.sort((a, b) => a - b);
    const ans = [];
    const t = [];
    const dfs = (i, s) => {
        if (s === 0) {
            ans.push(t.slice());
            return;
        }
        if (i >= candidates.length || s < candidates[i]) {
            return;
        }
        const x = candidates[i];
        t.push(x);
        dfs(i + 1, s - x);
        t.pop();
        while (i < candidates.length && candidates[i] === x) {
            ++i;
        }
        dfs(i, s);
    };
    dfs(0, target);
    return ans;
};
```

#### C#

```cs
public class Solution {
    private List<IList<int>> ans = new List<IList<int>>();
    private List<int> t = new List<int>();
    private int[] candidates;

    public IList<IList<int>> CombinationSum2(int[] candidates, int target) {
        Array.Sort(candidates);
        this.candidates = candidates;
        dfs(0, target);
        return ans;
    }

    private void dfs(int i, int s) {
        if (s == 0) {
            ans.Add(new List<int>(t));
            return;
        }
        if (i >= candidates.Length || s < candidates[i]) {
            return;
        }
        int x = candidates[i];
        t.Add(x);
        dfs(i + 1, s - x);
        t.RemoveAt(t.Count - 1);
        while (i < candidates.Length && candidates[i] == x) {
            ++i;
        }
        dfs(i, s);
    }
}
```

#### PHP

```php
class Solution {
    /**
     * @param integer[] $candidates
     * @param integer $target
     * @return integer[][]
     */

    function combinationSum2($candidates, $target) {
        $result = [];
        $currentCombination = [];
        $startIndex = 0;

        sort($candidates);
        $this->findCombinations($candidates, $target, $startIndex, $currentCombination, $result);
        return $result;
    }

    function findCombinations($candidates, $target, $startIndex, $currentCombination, &$result) {
        if ($target === 0) {
            $result[] = $currentCombination;
            return;
        }

        for ($i = $startIndex; $i < count($candidates); $i++) {
            $num = $candidates[$i];
            if ($num > $target) {
                break;
            }

            if ($i > $startIndex && $candidates[$i] === $candidates[$i - 1]) {
                continue;
            }
            $currentCombination[] = $num;

            $this->findCombinations(
                $candidates,
                $target - $num,
                $i + 1,
                $currentCombination,
                $result,
            );
            array_pop($currentCombination);
        }
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
