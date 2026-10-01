---
comments: true
difficulty: Medium
tags:
    - Array
---

<!-- problem:start -->

# [57. Insert Interval](https://leetcode.com/problems/insert-interval)

[中文文档](/solution/0000-0099/0057.Insert%20Interval/README.md)

## Mô tả

<!-- description:start -->

<p>Bạn được cho một mảng các khoảng không chồng lấn <code>intervals</code>, trong đó <code>intervals[i] = [start<sub>i</sub>, end<sub>i</sub>]</code> biểu thị điểm bắt đầu và điểm kết thúc của khoảng thứ <code>i<sup>th</sup></code>, và <code>intervals</code> được sắp xếp theo thứ tự tăng dần của <code>start<sub>i</sub></code>. Bạn cũng được cho một khoảng <code>newInterval = [start, end]</code> biểu thị điểm bắt đầu và điểm kết thúc của một khoảng khác.</p>

<p>Hai khoảng được coi là chồng lấn nếu chúng có chung <strong>ít nhất</strong> một điểm.</p>

<p>Chèn <code>newInterval</code> vào <code>intervals</code> sao cho <code>intervals</code> vẫn được sắp xếp theo thứ tự tăng dần của <code>start<sub>i</sub></code> và <code>intervals</code> vẫn không có các khoảng chồng lấn (gộp các khoảng chồng lấn nếu cần).</p>

<p>Trả về <code>intervals</code><em> sau khi chèn</em>.</p>

<p><strong>Lưu ý</strong> rằng bạn không cần sửa đổi <code>intervals</code> ngay tại chỗ. Bạn có thể tạo một mảng mới và trả về mảng đó.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong> intervals = [[1,3],[6,9]], newInterval = [2,5]
<strong>Đầu ra:</strong> [[1,5],[6,9]]
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
<strong>Đầu ra:</strong> [[1,2],[3,10],[12,16]]
<strong>Giải thích:</strong> Vì khoảng mới [4,8] chồng lấn với [3,5],[6,7],[8,10].
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>0 &lt;= intervals.length &lt;= 10<sup>4</sup></code></li>
	<li><code>intervals[i].length == 2</code></li>
	<li><code>0 &lt;= start<sub>i</sub> &lt;= end<sub>i</sub> &lt;= 10<sup>5</sup></code></li>
	<li><code>intervals</code> được sắp xếp theo <code>start<sub>i</sub></code> theo thứ tự <strong>tăng dần</strong>.</li>
	<li><code>newInterval.length == 2</code></li>
	<li><code>0 &lt;= start &lt;= end &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Sắp xếp + Gộp khoảng

<!-- thinking:start -->

> **Tư duy**
>
> Ý tưởng đầu tiên là chèn $\textit{newInterval}$ vào danh sách rồi tận dụng cách gộp khoảng thông thường. Vì $n \le 10^4$, việc sắp xếp $O(n \log n)$ sẽ đủ để vượt qua các bộ kiểm thử.
>
> Danh sách đã được sắp xếp và không chồng lấn, nhưng tái sử dụng cách gộp khoảng thông thường là cách có ít mã bổ sung nhất: thêm vào, sắp xếp, rồi gộp. Cách này đúng; chỉ là bỏ qua thứ tự đã cho.

<!-- thinking:end -->

Trước tiên, chúng ta có thể thêm khoảng mới `newInterval` vào danh sách khoảng `intervals`, sau đó gộp theo phương pháp gộp khoảng thông thường.

Độ phức tạp thời gian là $O(n \times \log n)$, và độ phức tạp không gian là $O(n)$. Trong đó, $n$ là số lượng khoảng.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def insert(
        self, intervals: List[List[int]], newInterval: List[int]
    ) -> List[List[int]]:
        def merge(intervals: List[List[int]]) -> List[List[int]]:
            intervals.sort()
            ans = [intervals[0]]
            for s, e in intervals[1:]:
                if ans[-1][1] < s:
                    ans.append([s, e])
                else:
                    ans[-1][1] = max(ans[-1][1], e)
            return ans

        intervals.append(newInterval)
        return merge(intervals)
```

#### Java

```java
class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int[][] newIntervals = new int[intervals.length + 1][2];
        for (int i = 0; i < intervals.length; ++i) {
            newIntervals[i] = intervals[i];
        }
        newIntervals[intervals.length] = newInterval;
        return merge(newIntervals);
    }

    private int[][] merge(int[][] intervals) {
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
    vector<vector<int>> insert(vector<vector<int>>& intervals, vector<int>& newInterval) {
        intervals.emplace_back(newInterval);
        return merge(intervals);
    }

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
func insert(intervals [][]int, newInterval []int) [][]int {
	merge := func(intervals [][]int) (ans [][]int) {
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
	intervals = append(intervals, newInterval)
	return merge(intervals)
}
```

#### TypeScript

```ts
function insert(intervals: number[][], newInterval: number[]): number[][] {
    const merge = (intervals: number[][]): number[][] => {
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
    };

    intervals.push(newInterval);
    return merge(intervals);
}
```

#### Rust

```rust
impl Solution {
    pub fn insert(intervals: Vec<Vec<i32>>, new_interval: Vec<i32>) -> Vec<Vec<i32>> {
        let mut merged_intervals = intervals.clone();
        merged_intervals.push(vec![new_interval[0], new_interval[1]]);
        // sort by elem[0]
        merged_intervals.sort_by_key(|elem| elem[0]);
        // merge interval
        let mut result = vec![];

        for interval in merged_intervals {
            if result.is_empty() {
                result.push(interval);
                continue;
            }

            let last_elem = result.last_mut().unwrap();
            if interval[0] > last_elem[1] {
                result.push(interval);
            } else {
                last_elem[1] = last_elem[1].max(interval[1]);
            }
        }
        result
    }
}
```

#### C#

```cs
public class Solution {
    public int[][] Insert(int[][] intervals, int[] newInterval) {
        int[][] newIntervals = new int[intervals.Length + 1][];
        for (int i = 0; i < intervals.Length; ++i) {
            newIntervals[i] = intervals[i];
        }
        newIntervals[intervals.Length] = newInterval;
        return Merge(newIntervals);
    }

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

### Lời giải 2: Duyệt một lần

<!-- thinking:start -->

> **Tư duy**
>
> Lời giải 1 sắp xếp lại một lần nữa và bỏ qua thứ tự đã có, nên phải trả thêm một thừa số $\log n$.
>
> Điều còn thiếu là một lần duyệt duy nhất: ghi lại khoảng hiện tại nếu nó nằm hoàn toàn bên trái khoảng mới; nếu nó nằm hoàn toàn bên phải, chèn khoảng mới trước; nếu không, mở rộng hai đầu của khoảng mới. Nếu khoảng mới chưa từng được chèn thì thêm nó vào cuối. Độ phức tạp là tuyến tính, không cần sắp xếp.

<!-- thinking:end -->

Chúng ta có thể duyệt qua danh sách khoảng `intervals`, gọi khoảng hiện tại là `interval`; với mỗi khoảng có ba trường hợp:

- Khoảng hiện tại nằm bên phải khoảng mới, tức là $newInterval[1] < interval[0]$. Khi đó, nếu khoảng mới chưa được thêm, trước tiên thêm khoảng mới vào đáp án, sau đó thêm khoảng hiện tại vào đáp án.
- Khoảng hiện tại nằm bên trái khoảng mới, tức là $interval[1] < newInterval[0]$. Khi đó, thêm khoảng hiện tại vào đáp án.
- Nếu không, điều đó có nghĩa là khoảng hiện tại và khoảng mới giao nhau. Chúng ta lấy giá trị nhỏ nhất giữa điểm đầu trái của khoảng hiện tại và điểm đầu trái của khoảng mới, cùng giá trị lớn nhất giữa điểm đầu phải của khoảng hiện tại và điểm đầu phải của khoảng mới, làm điểm đầu trái và phải của khoảng mới, rồi tiếp tục duyệt danh sách khoảng.

Sau khi duyệt xong, nếu khoảng mới chưa được thêm, thì thêm khoảng mới vào đáp án.

Độ phức tạp thời gian là $O(n)$, trong đó $n$ là số lượng khoảng. Bỏ qua phần không gian mà mảng đáp án sử dụng, độ phức tạp không gian là $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def insert(
        self, intervals: List[List[int]], newInterval: List[int]
    ) -> List[List[int]]:
        st, ed = newInterval
        ans = []
        insert = False
        for s, e in intervals:
            if ed < s:
                if not insert:
                    ans.append([st, ed])
                    insert = True
                ans.append([s, e])
            elif e < st:
                ans.append([s, e])
            else:
                st = min(st, s)
                ed = max(ed, e)
        if not insert:
            ans.append([st, ed])
        return ans
```

#### Java

```java
class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> ans = new ArrayList<>();
        int st = newInterval[0], ed = newInterval[1];
        boolean insert = false;
        for (int[] interval : intervals) {
            int s = interval[0], e = interval[1];
            if (ed < s) {
                if (!insert) {
                    ans.add(new int[] {st, ed});
                    insert = true;
                }
                ans.add(interval);
            } else if (e < st) {
                ans.add(interval);
            } else {
                st = Math.min(st, s);
                ed = Math.max(ed, e);
            }
        }
        if (!insert) {
            ans.add(new int[] {st, ed});
        }
        return ans.toArray(new int[ans.size()][]);
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<vector<int>> insert(vector<vector<int>>& intervals, vector<int>& newInterval) {
        vector<vector<int>> ans;
        int st = newInterval[0], ed = newInterval[1];
        bool insert = false;
        for (auto& interval : intervals) {
            int s = interval[0], e = interval[1];
            if (ed < s) {
                if (!insert) {
                    ans.push_back({st, ed});
                    insert = true;
                }
                ans.push_back(interval);
            } else if (e < st) {
                ans.push_back(interval);
            } else {
                st = min(st, s);
                ed = max(ed, e);
            }
        }
        if (!insert) {
            ans.push_back({st, ed});
        }
        return ans;
    }
};
```

#### Go

```go
func insert(intervals [][]int, newInterval []int) (ans [][]int) {
	st, ed := newInterval[0], newInterval[1]
	insert := false
	for _, interval := range intervals {
		s, e := interval[0], interval[1]
		if ed < s {
			if !insert {
				ans = append(ans, []int{st, ed})
				insert = true
			}
			ans = append(ans, interval)
		} else if e < st {
			ans = append(ans, interval)
		} else {
			st = min(st, s)
			ed = max(ed, e)
		}
	}
	if !insert {
		ans = append(ans, []int{st, ed})
	}
	return
}
```

#### TypeScript

```ts
function insert(intervals: number[][], newInterval: number[]): number[][] {
    let [st, ed] = newInterval;
    const ans: number[][] = [];
    let insert = false;
    for (const [s, e] of intervals) {
        if (ed < s) {
            if (!insert) {
                ans.push([st, ed]);
                insert = true;
            }
            ans.push([s, e]);
        } else if (e < st) {
            ans.push([s, e]);
        } else {
            st = Math.min(st, s);
            ed = Math.max(ed, e);
        }
    }
    if (!insert) {
        ans.push([st, ed]);
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn insert(intervals: Vec<Vec<i32>>, new_interval: Vec<i32>) -> Vec<Vec<i32>> {
        let mut inserted = false;
        let mut result = vec![];

        let (mut start, mut end) = (new_interval[0], new_interval[1]);
        for iter in intervals.iter() {
            let (cur_st, cur_ed) = (iter[0], iter[1]);
            if cur_ed < start {
                result.push(vec![cur_st, cur_ed]);
            } else if cur_st > end {
                if !inserted {
                    inserted = true;
                    result.push(vec![start, end]);
                }
                result.push(vec![cur_st, cur_ed]);
            } else {
                start = std::cmp::min(start, cur_st);
                end = std::cmp::max(end, cur_ed);
            }
        }

        if !inserted {
            result.push(vec![start, end]);
        }
        result
    }
}
```

#### C#

```cs
public class Solution {
    public int[][] Insert(int[][] intervals, int[] newInterval) {
        var ans = new List<int[]>();
        int st = newInterval[0], ed = newInterval[1];
        bool insert = false;
        foreach (var interval in intervals) {
            int s = interval[0], e = interval[1];
            if (ed < s) {
                if (!insert) {
                    ans.Add(new int[]{st, ed});
                    insert = true;
                }
                ans.Add(interval);
            } else if (st > e) {
                ans.Add(interval);
            } else {
                st = Math.Min(st, s);
                ed = Math.Max(ed, e);
            }
        }
        if (!insert) {
            ans.Add(new int[]{st, ed});
        }
        return ans.ToArray();
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
