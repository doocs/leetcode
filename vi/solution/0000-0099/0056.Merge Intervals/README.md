---
comments: true
difficulty: Medium
tags:
    - Array
    - Sorting
    - Quick Sort
---

<!-- problem:start -->

# [56. Merge Intervals](https://leetcode.com/problems/merge-intervals)

[中文文档](/solution/0000-0099/0056.Merge%20Intervals/README.md)

## Mô tả

<!-- description:start -->

<p>Cho một mảng&nbsp;các <code>intervals</code>&nbsp;trong đó <code>intervals[i] = [start<sub>i</sub>, end<sub>i</sub>]</code>, hãy gộp tất cả các khoảng chồng lấn và trả về <em>một mảng các khoảng không chồng lấn bao phủ tất cả các khoảng trong đầu vào</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong> intervals = [[1,3],[2,6],[8,10],[15,18]]
<strong>Đầu ra:</strong> [[1,6],[8,10],[15,18]]
<strong>Giải thích:</strong> Vì các khoảng [1,3] và [2,6] chồng lấn, gộp chúng thành [1,6].
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> intervals = [[1,4],[4,5]]
<strong>Đầu ra:</strong> [[1,5]]
<strong>Giải thích:</strong> Các khoảng [1,4] và [4,5] được xem là chồng lấn.
</pre>

<p><strong class="example">Ví dụ 3:</strong></p>

<pre>
<strong>Đầu vào:</strong> intervals = [[4,7],[1,4]]
<strong>Đầu ra:</strong> [[1,7]]
<strong>Giải thích:</strong> Các khoảng [1,4] và [4,7] được xem là chồng lấn.
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= intervals.length &lt;= 10<sup>4</sup></code></li>
	<li><code>intervals[i].length == 2</code></li>
	<li><code>0 &lt;= start<sub>i</sub> &lt;= end<sub>i</sub> &lt;= 10<sup>4</sup></code></li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Sắp xếp + Duyệt một lượt

<!-- thinking:start -->

> **Tư duy**
>
> Ý tưởng đầu tiên là chọn một khoảng và quét các khoảng còn lại để tìm phần chồng lấn, gộp chúng cho đến khi không còn gì thay đổi. Cách này đúng, nhưng trong trường hợp xấu nhất có độ phức tạp $O(n^2)$ và thứ tự gộp khá rối. Với $n \le 10^4$, điều này là sát giới hạn.
>
> Vấn đề gây lãng phí là phải tìm phần chồng lấn trong đầu vào chưa được sắp xếp. Sau khi sắp xếp theo đầu mút trái, một khoảng chỉ có thể chồng lấn với khoảng mà chúng ta chưa đóng—các điểm bắt đầu về sau lớn hơn, nên chúng không thể bỏ qua phần giữa rồi lại chồng lấn.
>
> Vì vậy, chúng ta sắp xếp, sau đó quét một lần, giữ $\textit{st}, \textit{ed}$ là khoảng đang được gộp.

<!-- thinking:end -->

Chúng ta có thể sắp xếp các khoảng theo thứ tự tăng dần của đầu mút trái, sau đó duyệt qua các khoảng để thực hiện thao tác gộp.

Thao tác gộp cụ thể như sau.

Trước tiên, chúng ta thêm khoảng đầu tiên vào đáp án. Sau đó, lần lượt xét từng khoảng tiếp theo:

- Nếu đầu mút phải của khoảng cuối cùng trong mảng đáp án nhỏ hơn đầu mút trái của khoảng hiện tại, điều đó có nghĩa là hai khoảng không chồng lấn, nên chúng ta có thể thêm trực tiếp khoảng hiện tại vào cuối mảng đáp án;
- Ngược lại, hai khoảng chồng lấn. Chúng ta cần dùng đầu mút phải của khoảng hiện tại để cập nhật đầu mút phải của khoảng cuối cùng trong mảng đáp án, đặt nó thành giá trị lớn hơn trong hai giá trị.

Cuối cùng, chúng ta trả về mảng đáp án.

Độ phức tạp thời gian là $O(n \times \log n)$, và độ phức tạp không gian là $O(\log n)$. Ở đây, $n$ là số lượng khoảng.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def merge(self, intervals: List[List[int]]) -> List[List[int]]:
        intervals.sort()
        ans = []
        st, ed = intervals[0]
        for s, e in intervals[1:]:
            if ed < s:
                ans.append([st, ed])
                st, ed = s, e
            else:
                ed = max(ed, e)
        ans.append([st, ed])
        return ans
```

#### Java

```java
class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        int st = intervals[0][0], ed = intervals[0][1];
        List<int[]> ans = new ArrayList<>();
        for (int i = 1; i < intervals.length; ++i) {
            int s = intervals[i][0], e = intervals[i][1];
            if (ed < s) {
                ans.add(new int[] {st, ed});
                st = s;
                ed = e;
            } else {
                ed = Math.max(ed, e);
            }
        }
        ans.add(new int[] {st, ed});
        return ans.toArray(new int[ans.size()][]);
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<vector<int>> merge(vector<vector<int>>& intervals) {
        sort(intervals.begin(), intervals.end());
        int st = intervals[0][0], ed = intervals[0][1];
        vector<vector<int>> ans;
        for (int i = 1; i < intervals.size(); ++i) {
            if (ed < intervals[i][0]) {
                ans.push_back({st, ed});
                st = intervals[i][0];
                ed = intervals[i][1];
            } else {
                ed = max(ed, intervals[i][1]);
            }
        }
        ans.push_back({st, ed});
        return ans;
    }
};
```

#### Go

```go
func merge(intervals [][]int) (ans [][]int) {
	sort.Slice(intervals, func(i, j int) bool {
		return intervals[i][0] < intervals[j][0]
	})
	st, ed := intervals[0][0], intervals[0][1]
	for _, e := range intervals[1:] {
		if ed < e[0] {
			ans = append(ans, []int{st, ed})
			st, ed = e[0], e[1]
		} else if ed < e[1] {
			ed = e[1]
		}
	}
	ans = append(ans, []int{st, ed})
	return ans
}
```

#### TypeScript

```ts
function merge(intervals: number[][]): number[][] {
    intervals.sort((a, b) => a[0] - b[0]);
    const ans: number[][] = [];
    let [st, ed] = intervals[0];
    for (const [s, e] of intervals.slice(1)) {
        if (ed < s) {
            ans.push([st, ed]);
            [st, ed] = [s, e];
        } else {
            ed = Math.max(ed, e);
        }
    }
    ans.push([st, ed]);
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn merge(mut intervals: Vec<Vec<i32>>) -> Vec<Vec<i32>> {
        intervals.sort_unstable_by(|a, b| a[0].cmp(&b[0]));
        let n = intervals.len();
        let mut res = vec![];
        let mut i = 0;
        while i < n {
            let l = intervals[i][0];
            let mut r = intervals[i][1];
            i += 1;
            while i < n && r >= intervals[i][0] {
                r = r.max(intervals[i][1]);
                i += 1;
            }
            res.push(vec![l, r]);
        }
        res
    }
}
```

#### C#

```cs
public class Solution {
    public int[][] Merge(int[][] intervals) {
        intervals = intervals.OrderBy(a => a[0]).ToArray();
        int st = intervals[0][0], ed = intervals[0][1];
        var ans = new List<int[]>();
        for (int i = 1; i < intervals.Length; ++i) {
            if (ed < intervals[i][0]) {
                ans.Add(new int[] { st, ed });
                st = intervals[i][0];
                ed = intervals[i][1];
            } else {
                ed = Math.Max(ed, intervals[i][1]);
            }
        }
        ans.Add(new int[] { st, ed });
        return ans.ToArray();
    }
}
```

### JavaScript

```js
/**
 * @param {number[][]} intervals
 * @return {number[][]}
 */
var merge = function (intervals) {
    intervals.sort((a, b) => a[0] - b[0]);
    const result = [];
    const n = intervals.length;
    let i = 0;
    while (i < n) {
        const left = intervals[i][0];
        let right = intervals[i][1];
        while (true) {
            i++;
            if (i < n && right >= intervals[i][0]) {
                right = Math.max(right, intervals[i][1]);
            } else {
                result.push([left, right]);
                break;
            }
        }
    }
    return result;
};
```

#### Kotlin

```kotlin
class Solution {
    fun merge(intervals: Array<IntArray>): Array<IntArray> {
        intervals.sortBy { it[0] }
        val result = mutableListOf<IntArray>()
        val n = intervals.size
        var i = 0
        while (i < n) {
            val left = intervals[i][0]
            var right = intervals[i][1]
            while (true) {
                i++
                if (i < n && right >= intervals[i][0]) {
                    right = maxOf(right, intervals[i][1])
                } else {
                    result.add(intArrayOf(left, right))
                    break
                }
            }
        }
        return result.toTypedArray()
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
> Lời giải 1 đã có độ phức tạp $O(n \log n)$ và đúng. Tuy nhiên, nó vẫn giữ riêng $\textit{st}, \textit{ed}$, chỉ ghi khi khoảng đóng lại, rồi thêm một lần nữa ở cuối.
>
> Điều còn thiếu là lưu khoảng hiện tại trong đáp án: đưa khoảng đầu tiên vào $\textit{ans}$ ngay lập tức, sau đó mở rộng đầu mút phải của $\textit{ans}[-1]$ hoặc thêm một khoảng mới. Ít biến hơn, không cần thao tác đẩy phần tử cuối.

<!-- thinking:end -->

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def merge(self, intervals: List[List[int]]) -> List[List[int]]:
        intervals.sort()
        ans = [intervals[0]]
        for s, e in intervals[1:]:
            if ans[-1][1] < s:
                ans.append([s, e])
            else:
                ans[-1][1] = max(ans[-1][1], e)
        return ans
```

#### Java

```java
class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        List<int[]> ans = new ArrayList<>();
        ans.add(intervals[0]);
        for (int i = 1; i < intervals.length; ++i) {
            int s = intervals[i][0], e = intervals[i][1];
            if (ans.get(ans.size() - 1)[1] < s) {
                ans.add(intervals[i]);
            } else {
                ans.get(ans.size() - 1)[1] = Math.max(ans.get(ans.size() - 1)[1], e);
            }
        }
        return ans.toArray(new int[ans.size()][]);
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<vector<int>> merge(vector<vector<int>>& intervals) {
        sort(intervals.begin(), intervals.end());
        vector<vector<int>> ans;
        ans.emplace_back(intervals[0]);
        for (int i = 1; i < intervals.size(); ++i) {
            if (ans.back()[1] < intervals[i][0]) {
                ans.emplace_back(intervals[i]);
            } else {
                ans.back()[1] = max(ans.back()[1], intervals[i][1]);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func merge(intervals [][]int) (ans [][]int) {
	sort.Slice(intervals, func(i, j int) bool { return intervals[i][0] < intervals[j][0] })
	ans = append(ans, intervals[0])
	for _, e := range intervals[1:] {
		if ans[len(ans)-1][1] < e[0] {
			ans = append(ans, e)
		} else {
			ans[len(ans)-1][1] = max(ans[len(ans)-1][1], e[1])
		}
	}
	return
}
```

#### TypeScript

```ts
function merge(intervals: number[][]): number[][] {
    intervals.sort((a, b) => a[0] - b[0]);
    const ans: number[][] = [intervals[0]];
    for (let i = 1; i < intervals.length; ++i) {
        if (ans.at(-1)[1] < intervals[i][0]) {
            ans.push(intervals[i]);
        } else {
            ans.at(-1)[1] = Math.max(ans.at(-1)[1], intervals[i][1]);
        }
    }
    return ans;
}
```

#### C#

```cs
public class Solution {
    public int[][] Merge(int[][] intervals) {
        intervals = intervals.OrderBy(a => a[0]).ToArray();
        var ans = new List<int[]>();
        ans.Add(intervals[0]);
        for (int i = 1; i < intervals.Length; ++i) {
            if (ans[ans.Count - 1][1] < intervals[i][0]) {
                ans.Add(intervals[i]);
            } else {
                ans[ans.Count - 1][1] = Math.Max(ans[ans.Count - 1][1], intervals[i][1]);
            }
        }
        return ans.ToArray();
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Lời giải 3

<!-- thinking:start -->

> **Tư duy**
>
> Lời giải 2 đã thay đổi khoảng cuối cùng trong đáp án. Vòng lặp vẫn là kiểu “xem từng khoảng rồi vá nếu cần”.
>
> Điều còn thiếu là gộp theo nhóm: cố định $l$, lấy mọi khoảng chồng lấn trong một vòng lặp bên trong đồng thời kéo dài $r$, rồi thêm $[l, r]$ đúng một lần. Các đáp án đã ghi không bao giờ bị thay đổi.

<!-- thinking:end -->

<!-- tabs:start -->

#### TypeScript

```ts
function merge(intervals: number[][]): number[][] {
    intervals.sort((a, b) => a[0] - b[0]);
    const n = intervals.length;
    const res = [];
    let i = 0;
    while (i < n) {
        let [l, r] = intervals[i];
        i++;
        while (i < n && r >= intervals[i][0]) {
            r = Math.max(r, intervals[i][1]);
            i++;
        }
        res.push([l, r]);
    }
    return res;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
