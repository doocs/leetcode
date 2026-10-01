---
comments: true
difficulty: Medium
tags:
    - Array
    - Backtracking
---

<!-- problem:start -->

# [39. Combination Sum](https://leetcode.com/problems/combination-sum)

[中文文档](/solution/0000-0099/0039.Combination%20Sum/README.md)

## Mô tả

<!-- description:start -->

<p>Cho một mảng các số nguyên <strong>phân biệt</strong> <code>candidates</code> và một số nguyên đích <code>target</code>, hãy trả về <em>danh sách tất cả các <strong>tổ hợp duy nhất</strong> của </em><code>candidates</code><em> sao cho tổng các số được chọn bằng </em><code>target</code><em>.</em> Bạn có thể trả về các tổ hợp theo <strong>bất kỳ thứ tự nào</strong>.</p>

<p>Có thể chọn <strong>cùng một</strong> số trong <code>candidates</code> <strong>vô hạn lần</strong>. Hai tổ hợp là duy nhất nếu <span data-keyword="frequency-array">tần suất</span> của ít nhất một trong các số được chọn là khác nhau.</p>

<p>Các trường hợp kiểm thử được tạo sao cho số lượng các tổ hợp duy nhất có tổng bằng <code>target</code> nhỏ hơn <code>150</code> tổ hợp với dữ liệu đầu vào đã cho.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong> candidates = [2,3,6,7], target = 7
<strong>Đầu ra:</strong> [[2,2,3],[7]]
<strong>Giải thích:</strong>
2 và 3 là các ứng viên, và 2 + 2 + 3 = 7. Lưu ý rằng 2 có thể được sử dụng nhiều lần.
7 là một ứng viên, và 7 = 7.
Đây là hai tổ hợp duy nhất.
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> candidates = [2,3,5], target = 8
<strong>Đầu ra:</strong> [[2,2,2,2],[2,3,3],[3,5]]
</pre>

<p><strong class="example">Ví dụ 3:</strong></p>

<pre>
<strong>Đầu vào:</strong> candidates = [2], target = 1
<strong>Đầu ra:</strong> []
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= candidates.length &lt;= 30</code></li>
	<li><code>2 &lt;= candidates[i] &lt;= 40</code></li>
	<li>Tất cả các phần tử của <code>candidates</code> đều <strong>phân biệt</strong>.</li>
	<li><code>1 &lt;= target &lt;= 40</code></li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Sắp xếp + Cắt tỉa + Quay lui

<!-- thinking:start -->

> **Tư duy**
>
> Ý tưởng đầu tiên là sử dụng mỗi số nhiều lần tùy ý và thu thập mọi dãy có tổng bằng $target$. Với $n \le 30$ và $target \le 40$, việc tìm kiếm không cắt tỉa sẽ lãng phí các nhánh đã vượt quá target, đồng thời các thứ tự khác nhau của cùng một đa tập sẽ bị đếm hai lần.
>
> Một tổ hợp không phụ thuộc vào thứ tự, vì vậy mỗi đa tập chỉ nên xuất hiện một lần. Trước tiên hãy sắp xếp, rồi chỉ lấy các ứng viên từ chỉ số hiện tại trở về sau.
>
> Sau khi sắp xếp, nếu phần còn lại $s$ đã nhỏ hơn $candidates[i]$, mọi phần tử phía sau đều lớn hơn và nhánh đó kết thúc. $dfs(i,s)$ duyệt $j$ từ $i$, còn lời gọi đệ quy vẫn ở $j$ để có thể sử dụng lại cùng một giá trị.

<!-- thinking:end -->

Trước tiên, chúng ta có thể sắp xếp mảng để thuận tiện cho việc cắt tỉa.

Tiếp theo, chúng ta thiết kế một hàm $dfs(i, s)$, biểu thị việc bắt đầu tìm kiếm từ chỉ số $i$ với giá trị đích còn lại là $s$. Ở đây, cả $i$ và $s$ đều là các số nguyên không âm, đường đi tìm kiếm hiện tại là $t$, còn đáp án là $ans$.

Trong hàm $dfs(i, s)$, trước tiên chúng ta kiểm tra xem $s$ có bằng $0$ hay không. Nếu có, chúng ta thêm đường đi tìm kiếm hiện tại $t$ vào đáp án $ans$, sau đó return. Nếu $s \lt candidates[i]$, điều đó có nghĩa là các phần tử từ chỉ số hiện tại và các chỉ số phía sau đều lớn hơn giá trị đích còn lại $s$, nên đường đi không hợp lệ và chúng ta return ngay. Nếu không, chúng ta bắt đầu tìm kiếm từ chỉ số $i$, với phạm vi chỉ số tìm kiếm là $j \in [i, n)$, trong đó $n$ là độ dài của mảng $candidates$. Trong quá trình tìm kiếm, chúng ta thêm phần tử ở chỉ số hiện tại vào đường đi tìm kiếm $t$, gọi đệ quy hàm $dfs(j, s - candidates[j])$, và sau khi kết thúc đệ quy, xóa phần tử ở chỉ số hiện tại khỏi đường đi tìm kiếm $t$.

Trong hàm chính, chúng ta chỉ cần gọi hàm $dfs(0, target)$ để nhận được đáp án.

Độ phức tạp thời gian là $O(2^n \times n)$, và độ phức tạp không gian là $O(n)$. Trong đó, $n$ là độ dài của mảng $candidates$. Nhờ việc cắt tỉa, độ phức tạp thời gian thực tế nhỏ hơn nhiều so với $O(2^n \times n)$.

Các bài toán tương tự:

- [40. Combination Sum II](https://github.com/doocs/leetcode/blob/main/solution/0000-0099/0040.Combination%20Sum%20II/README_EN.md)
- [77. Combinations](https://github.com/doocs/leetcode/blob/main/solution/0000-0099/0077.Combinations/README_EN.md)
- [216. Combination Sum III](https://github.com/doocs/leetcode/blob/main/solution/0200-0299/0216.Combination%20Sum%20III/README_EN.md)

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def combinationSum(self, candidates: List[int], target: int) -> List[List[int]]:
        def dfs(i: int, s: int):
            if s == 0:
                ans.append(t[:])
                return
            if s < candidates[i]:
                return
            for j in range(i, len(candidates)):
                t.append(candidates[j])
                dfs(j, s - candidates[j])
                t.pop()

        candidates.sort()
        t = []
        ans = []
        dfs(0, target)
        return ans
```

#### Java

```java
class Solution {
    private List<List<Integer>> ans = new ArrayList<>();
    private List<Integer> t = new ArrayList<>();
    private int[] candidates;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        this.candidates = candidates;
        dfs(0, target);
        return ans;
    }

    private void dfs(int i, int s) {
        if (s == 0) {
            ans.add(new ArrayList(t));
            return;
        }
        if (s < candidates[i]) {
            return;
        }
        for (int j = i; j < candidates.length; ++j) {
            t.add(candidates[j]);
            dfs(j, s - candidates[j]);
            t.remove(t.size() - 1);
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<vector<int>> combinationSum(vector<int>& candidates, int target) {
        sort(candidates.begin(), candidates.end());
        vector<vector<int>> ans;
        vector<int> t;
        function<void(int, int)> dfs = [&](int i, int s) {
            if (s == 0) {
                ans.emplace_back(t);
                return;
            }
            if (s < candidates[i]) {
                return;
            }
            for (int j = i; j < candidates.size(); ++j) {
                t.push_back(candidates[j]);
                dfs(j, s - candidates[j]);
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
func combinationSum(candidates []int, target int) (ans [][]int) {
	sort.Ints(candidates)
	t := []int{}
	var dfs func(i, s int)
	dfs = func(i, s int) {
		if s == 0 {
			ans = append(ans, slices.Clone(t))
			return
		}
		if s < candidates[i] {
			return
		}
		for j := i; j < len(candidates); j++ {
			t = append(t, candidates[j])
			dfs(j, s-candidates[j])
			t = t[:len(t)-1]
		}
	}
	dfs(0, target)
	return
}
```

#### TypeScript

```ts
function combinationSum(candidates: number[], target: number): number[][] {
    candidates.sort((a, b) => a - b);
    const ans: number[][] = [];
    const t: number[] = [];
    const dfs = (i: number, s: number) => {
        if (s === 0) {
            ans.push(t.slice());
            return;
        }
        if (s < candidates[i]) {
            return;
        }
        for (let j = i; j < candidates.length; ++j) {
            t.push(candidates[j]);
            dfs(j, s - candidates[j]);
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
        if s < candidates[i] {
            return;
        }
        for j in i..candidates.len() {
            t.push(candidates[j]);
            Self::dfs(j, s - candidates[j], candidates, t, ans);
            t.pop();
        }
    }

    pub fn combination_sum(mut candidates: Vec<i32>, target: i32) -> Vec<Vec<i32>> {
        candidates.sort();
        let mut ans = Vec::new();
        Self::dfs(0, target, &candidates, &mut vec![], &mut ans);
        ans
    }
}
```

#### C#

```cs
public class Solution {
    private List<IList<int>> ans = new List<IList<int>>();
    private List<int> t = new List<int>();
    private int[] candidates;

    public IList<IList<int>> CombinationSum(int[] candidates, int target) {
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
        if (s < candidates[i]) {
            return;
        }
        for (int j = i; j < candidates.Length; ++j) {
            t.Add(candidates[j]);
            dfs(j, s - candidates[j]);
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
> Phương pháp 1 đã cắt tỉa đúng bằng cách lặp qua "phần tử kế tiếp". Cách viết khác là chọn-hoặc-bỏ qua tại chỉ số hiện tại: bỏ qua thì chuyển đến $i+1$; chọn thì vẫn ở $i$ để có thể sử dụng lại cùng một giá trị. Cây tìm kiếm trông khác nhau, nhưng các đáp án thì giống nhau. Đây là cùng một thuật toán được triển khai dưới dạng cây nhị phân.

<!-- thinking:end -->

Chúng ta cũng có thể thay đổi logic triển khai của hàm $dfs(i, s)$ sang một dạng khác. Trong hàm $dfs(i, s)$, trước tiên chúng ta kiểm tra xem $s$ có bằng $0$ hay không. Nếu có, chúng ta thêm đường đi tìm kiếm hiện tại $t$ vào đáp án $ans$, sau đó return. Nếu $i \geq n$ hoặc $s \lt candidates[i]$, đường đi không hợp lệ, nên chúng ta return ngay. Nếu không, chúng ta xem xét hai trường hợp: một là không chọn phần tử ở chỉ số hiện tại, tức là gọi đệ quy hàm $dfs(i + 1, s)$, và trường hợp còn lại là chọn phần tử ở chỉ số hiện tại, tức là gọi đệ quy hàm $dfs(i, s - candidates[i])$.

Độ phức tạp thời gian là $O(2^n \times n)$, và độ phức tạp không gian là $O(n)$. Trong đó, $n$ là độ dài của mảng $candidates$. Nhờ việc cắt tỉa, độ phức tạp thời gian thực tế nhỏ hơn nhiều so với $O(2^n \times n)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def combinationSum(self, candidates: List[int], target: int) -> List[List[int]]:
        def dfs(i: int, s: int):
            if s == 0:
                ans.append(t[:])
                return
            if i >= len(candidates) or s < candidates[i]:
                return
            dfs(i + 1, s)
            t.append(candidates[i])
            dfs(i, s - candidates[i])
            t.pop()

        candidates.sort()
        t = []
        ans = []
        dfs(0, target)
        return ans
```

#### Java

```java
class Solution {
    private List<List<Integer>> ans = new ArrayList<>();
    private List<Integer> t = new ArrayList<>();
    private int[] candidates;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        this.candidates = candidates;
        dfs(0, target);
        return ans;
    }

    private void dfs(int i, int s) {
        if (s == 0) {
            ans.add(new ArrayList(t));
            return;
        }
        if (i >= candidates.length || s < candidates[i]) {
            return;
        }
        dfs(i + 1, s);
        t.add(candidates[i]);
        dfs(i, s - candidates[i]);
        t.remove(t.size() - 1);
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<vector<int>> combinationSum(vector<int>& candidates, int target) {
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
            dfs(i + 1, s);
            t.push_back(candidates[i]);
            dfs(i, s - candidates[i]);
            t.pop_back();
        };
        dfs(0, target);
        return ans;
    }
};
```

#### Go

```go
func combinationSum(candidates []int, target int) (ans [][]int) {
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
		dfs(i+1, s)
		t = append(t, candidates[i])
		dfs(i, s-candidates[i])
		t = t[:len(t)-1]
	}
	dfs(0, target)
	return
}
```

#### TypeScript

```ts
function combinationSum(candidates: number[], target: number): number[][] {
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
        dfs(i + 1, s);
        t.push(candidates[i]);
        dfs(i, s - candidates[i]);
        t.pop();
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
        Self::dfs(i + 1, s, candidates, t, ans);
        t.push(candidates[i]);
        Self::dfs(i, s - candidates[i], candidates, t, ans);
        t.pop();
    }

    pub fn combination_sum(mut candidates: Vec<i32>, target: i32) -> Vec<Vec<i32>> {
        candidates.sort();
        let mut ans = Vec::new();
        Self::dfs(0, target, &candidates, &mut vec![], &mut ans);
        ans
    }
}
```

#### C#

```cs
public class Solution {
    private List<IList<int>> ans = new List<IList<int>>();
    private List<int> t = new List<int>();
    private int[] candidates;

    public IList<IList<int>> CombinationSum(int[] candidates, int target) {
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
        dfs(i + 1, s);
        t.Add(candidates[i]);
        dfs(i, s - candidates[i]);
        t.RemoveAt(t.Count - 1);
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

    function combinationSum($candidates, $target) {
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
            $currentCombination[] = $num;

            $this->findCombinations($candidates, $target - $num, $i, $currentCombination, $result);
            array_pop($currentCombination);
        }
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
