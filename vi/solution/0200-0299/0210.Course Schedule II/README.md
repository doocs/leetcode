---
comments: true
difficulty: Medium
tags:
    - Depth-First Search
    - Breadth-First Search
    - Graph
    - Topological Sort
---

<!-- problem:start -->

# [210. Course Schedule II](https://leetcode.com/problems/course-schedule-ii)

[中文文档](/solution/0200-0299/0210.Course%20Schedule%20II/README.md)

## Mô tả

<!-- description:start -->

<p>Có tổng cộng <code>numCourses</code> khóa học bạn phải học, được đánh số từ <code>0</code> đến <code>numCourses - 1</code>. Bạn được cung cấp một mảng <code>prerequisites</code>, trong đó <code>prerequisites[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> cho biết bạn <strong>phải</strong> học khóa <code>b<sub>i</sub></code> trước nếu muốn học khóa <code>a<sub>i</sub></code>.</p>

<ul>
	<li>Ví dụ, cặp <code>[0, 1]</code> cho biết để học khóa <code>0</code>, bạn phải học khóa <code>1</code> trước.</li>
</ul>

<p>Hãy trả về <em>thứ tự các khóa học bạn nên học để hoàn thành tất cả các khóa học</em>. Nếu có nhiều đáp án hợp lệ, hãy trả về <strong>bất kỳ đáp án nào</strong>. Nếu không thể hoàn thành tất cả các khóa học, hãy trả về <strong>một mảng rỗng</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong> numCourses = 2, prerequisites = [[1,0]]
<strong>Đầu ra:</strong> [0,1]
<strong>Giải thích:</strong> Có tổng cộng 2 khóa học cần học. Để học khóa 1, bạn nên hoàn thành khóa 0 trước. Vì vậy, thứ tự khóa học đúng là [0,1].
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
<strong>Đầu ra:</strong> [0,2,1,3]
<strong>Giải thích:</strong> Có tổng cộng 4 khóa học cần học. Để học khóa 3, bạn nên hoàn thành cả khóa 1 và khóa 2 trước. Cả khóa 1 và khóa 2 đều nên được học sau khi bạn đã hoàn thành khóa 0.
Vì vậy, một thứ tự khóa học đúng là [0,1,2,3]. Một thứ tự đúng khác là [0,2,1,3].
</pre>

<p><strong class="example">Ví dụ 3:</strong></p>

<pre>
<strong>Đầu vào:</strong> numCourses = 1, prerequisites = []
<strong>Đầu ra:</strong> [0]
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= numCourses &lt;= 2000</code></li>
	<li><code>0 &lt;= prerequisites.length &lt;= numCourses * (numCourses - 1)</code></li>
	<li><code>prerequisites[i].length == 2</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt; numCourses</code></li>
	<li><code>a<sub>i</sub> != b<sub>i</sub></code></li>
	<li>Tất cả các cặp <code>[a<sub>i</sub>, b<sub>i</sub>]</code> đều <strong>khác nhau</strong>.</li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1

<!-- thinking:start -->

> **Tư duy**
>
> Giống như phiên bản quyết định, các quan hệ tiên quyết tạo thành một đồ thị có hướng; ở đây chúng ta cũng cần một thứ tự hợp lệ. Thứ tự dequeue của phép sắp xếp topo là một chuỗi như vậy.
>
> Nếu có ít hơn $numCourses$ đỉnh được đưa vào hàng đợi, thì tồn tại một chu trình và chúng ta trả về một mảng rỗng.

<!-- thinking:end -->

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findOrder(self, numCourses: int, prerequisites: List[List[int]]) -> List[int]:
        g = defaultdict(list)
        indeg = [0] * numCourses
        for a, b in prerequisites:
            g[b].append(a)
            indeg[a] += 1
        ans = []
        q = deque(i for i, x in enumerate(indeg) if x == 0)
        while q:
            i = q.popleft()
            ans.append(i)
            for j in g[i]:
                indeg[j] -= 1
                if indeg[j] == 0:
                    q.append(j)
        return ans if len(ans) == numCourses else []
```

#### Java

```java
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer>[] g = new List[numCourses];
        Arrays.setAll(g, k -> new ArrayList<>());
        int[] indeg = new int[numCourses];
        for (var p : prerequisites) {
            int a = p[0], b = p[1];
            g[b].add(a);
            ++indeg[a];
        }
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < numCourses; ++i) {
            if (indeg[i] == 0) {
                q.offer(i);
            }
        }
        int[] ans = new int[numCourses];
        int cnt = 0;
        while (!q.isEmpty()) {
            int i = q.poll();
            ans[cnt++] = i;
            for (int j : g[i]) {
                if (--indeg[j] == 0) {
                    q.offer(j);
                }
            }
        }
        return cnt == numCourses ? ans : new int[0];
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> findOrder(int numCourses, vector<vector<int>>& prerequisites) {
        vector<vector<int>> g(numCourses);
        vector<int> indeg(numCourses);
        for (auto& p : prerequisites) {
            int a = p[0], b = p[1];
            g[b].push_back(a);
            ++indeg[a];
        }
        queue<int> q;
        for (int i = 0; i < numCourses; ++i) {
            if (indeg[i] == 0) {
                q.push(i);
            }
        }
        vector<int> ans;
        while (!q.empty()) {
            int i = q.front();
            q.pop();
            ans.push_back(i);
            for (int j : g[i]) {
                if (--indeg[j] == 0) {
                    q.push(j);
                }
            }
        }
        return ans.size() == numCourses ? ans : vector<int>();
    }
};
```

#### Go

```go
func findOrder(numCourses int, prerequisites [][]int) []int {
	g := make([][]int, numCourses)
	indeg := make([]int, numCourses)
	for _, p := range prerequisites {
		a, b := p[0], p[1]
		g[b] = append(g[b], a)
		indeg[a]++
	}
	q := []int{}
	for i, x := range indeg {
		if x == 0 {
			q = append(q, i)
		}
	}
	ans := []int{}
	for len(q) > 0 {
		i := q[0]
		q = q[1:]
		ans = append(ans, i)
		for _, j := range g[i] {
			indeg[j]--
			if indeg[j] == 0 {
				q = append(q, j)
			}
		}
	}
	if len(ans) == numCourses {
		return ans
	}
	return []int{}
}
```

#### TypeScript

```ts
function findOrder(numCourses: number, prerequisites: number[][]): number[] {
    const g: number[][] = Array.from({ length: numCourses }, () => []);
    const indeg: number[] = new Array(numCourses).fill(0);
    for (const [a, b] of prerequisites) {
        g[b].push(a);
        indeg[a]++;
    }
    const q: number[] = [];
    for (let i = 0; i < numCourses; ++i) {
        if (indeg[i] === 0) {
            q.push(i);
        }
    }
    const ans: number[] = [];
    while (q.length) {
        const i = q.shift()!;
        ans.push(i);
        for (const j of g[i]) {
            if (--indeg[j] === 0) {
                q.push(j);
            }
        }
    }
    return ans.length === numCourses ? ans : [];
}
```

#### Rust

```rust
impl Solution {
    pub fn find_order(num_courses: i32, prerequisites: Vec<Vec<i32>>) -> Vec<i32> {
        let n = num_courses as usize;
        let mut adjacency = vec![vec![]; n];
        let mut entry = vec![0; n];
        // khởi tạo
        for iter in prerequisites.iter() {
            let (a, b) = (iter[0], iter[1]);
            adjacency[b as usize].push(a);
            entry[a as usize] += 1;
        }
        // tạo deque và kết quả
        let mut deque = std::collections::VecDeque::new();
        for index in 0..n {
            if entry[index] == 0 {
                deque.push_back(index);
            }
        }
        let mut result = vec![];
        // BFS
        while !deque.is_empty() {
            let head = deque.pop_front().unwrap();
            result.push(head as i32);
            // cập nhật bậc vào
            for &out_entry in adjacency[head].iter() {
                entry[out_entry as usize] -= 1;
                if entry[out_entry as usize] == 0 {
                    deque.push_back(out_entry as usize);
                }
            }
        }
        if result.len() == n {
            result
        } else {
            vec![]
        }
    }
}
```

#### C#

```cs
public class Solution {
    public int[] FindOrder(int numCourses, int[][] prerequisites) {
        var g = new List<int>[numCourses];
        for (int i = 0; i < numCourses; ++i) {
            g[i] = new List<int>();
        }
        var indeg = new int[numCourses];
        foreach (var p in prerequisites) {
            int a = p[0], b = p[1];
            g[b].Add(a);
            ++indeg[a];
        }
        var q = new Queue<int>();
        for (int i = 0; i < numCourses; ++i) {
            if (indeg[i] == 0) {
                q.Enqueue(i);
            }
        }
        var ans = new int[numCourses];
        var cnt = 0;
        while (q.Count > 0) {
            int i = q.Dequeue();
            ans[cnt++] = i;
            foreach (int j in g[i]) {
                if (--indeg[j] == 0) {
                    q.Enqueue(j);
                }
            }
        }
        return cnt == numCourses ? ans : new int[0];
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
