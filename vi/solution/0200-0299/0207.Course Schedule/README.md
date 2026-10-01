---
comments: true
difficulty: Medium
tags:
    - Depth-First Search
    - Breadth-First Search
    - Graph
    - Topological Sort
    - Directed Acyclic Graph
---

<!-- problem:start -->

# [207. Course Schedule](https://leetcode.com/problems/course-schedule)

[中文文档](/solution/0200-0299/0207.Course%20Schedule/README.md)

## Mô tả

<!-- description:start -->

<p>Có tổng cộng <code>numCourses</code> khóa học bạn phải học, được đánh số từ <code>0</code> đến <code>numCourses - 1</code>. Bạn được cho một mảng <code>prerequisites</code>, trong đó <code>prerequisites[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> cho biết rằng bạn <strong>phải</strong> học khóa học <code>b<sub>i</sub></code> trước nếu muốn học khóa học <code>a<sub>i</sub></code>.</p>

<ul>
	<li>Ví dụ, cặp <code>[0, 1]</code> cho biết rằng để học khóa học <code>0</code>, bạn phải học khóa học <code>1</code> trước.</li>
</ul>

<p>Hãy trả về <code>true</code> nếu bạn có thể hoàn thành tất cả các khóa học. Nếu không, hãy trả về <code>false</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong> numCourses = 2, prerequisites = [[1,0]]
<strong>Đầu ra:</strong> true
<strong>Giải thích:</strong> Có tổng cộng 2 khóa học cần học.
Để học khóa học 1, bạn nên hoàn thành khóa học 0 trước. Vì vậy, điều này là khả thi.
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> numCourses = 2, prerequisites = [[1,0],[0,1]]
<strong>Đầu ra:</strong> false
<strong>Giải thích:</strong> Có tổng cộng 2 khóa học cần học.
Để học khóa học 1, bạn nên hoàn thành khóa học 0 trước, và để học khóa học 0, bạn cũng nên hoàn thành khóa học 1 trước. Vì vậy, điều này là không khả thi.
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= numCourses &lt;= 2000</code></li>
	<li><code>0 &lt;= prerequisites.length &lt;= 5000</code></li>
	<li><code>prerequisites[i].length == 2</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt; numCourses</code></li>
	<li>Tất cả các cặp prerequisites[i] đều <strong>duy nhất</strong>.</li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Sắp xếp topo

<!-- thinking:start -->

> **Tư duy**
>
> Quan hệ tiên quyết tạo thành một đồ thị có hướng; có thể hoàn thành mọi khóa học khi và chỉ khi đồ thị không có chu trình. Việc liệt kê các thứ tự là không khả thi với kích thước đã cho.
>
> Thuật toán Kahn liên tục lấy một đỉnh có bậc vào $0$ và giảm bậc vào của các đỉnh kế tiếp. Nếu mọi khóa học đều được đưa vào hàng đợi thì đồ thị không có chu trình.

<!-- thinking:end -->

Trong bài toán này, chúng ta có thể coi các khóa học là các nút trong một đồ thị và các môn tiên quyết là các cạnh trong đồ thị. Vì vậy, chúng ta có thể chuyển bài toán thành việc xác định xem đồ thị có hướng có chu trình hay không.

Cụ thể, chúng ta có thể sử dụng ý tưởng sắp xếp topo. Với mỗi nút có bậc vào bằng $0$, chúng ta giảm bậc vào của các nút kế tiếp đi $1$, cho đến khi tất cả các nút đều đã được duyệt.

Nếu tất cả các nút đều đã được duyệt, điều đó có nghĩa là đồ thị không có chu trình và chúng ta có thể hoàn thành tất cả các khóa học; nếu không, chúng ta không thể hoàn thành tất cả các khóa học.

Độ phức tạp thời gian là $O(n + m)$, và độ phức tạp không gian là $O(n + m)$. Trong đó, $n$ và $m$ lần lượt là số khóa học và số môn tiên quyết.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def canFinish(self, numCourses: int, prerequisites: List[List[int]]) -> bool:
        g = [[] for _ in range(numCourses)]
        indeg = [0] * numCourses
        for a, b in prerequisites:
            g[b].append(a)
            indeg[a] += 1
        q = [i for i, x in enumerate(indeg) if x == 0]
        for i in q:
            numCourses -= 1
            for j in g[i]:
                indeg[j] -= 1
                if indeg[j] == 0:
                    q.append(j)
        return numCourses == 0
```

#### Java

```java
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
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
        while (!q.isEmpty()) {
            int i = q.poll();
            --numCourses;
            for (int j : g[i]) {
                if (--indeg[j] == 0) {
                    q.offer(j);
                }
            }
        }
        return numCourses == 0;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool canFinish(int numCourses, vector<vector<int>>& prerequisites) {
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
        while (!q.empty()) {
            int i = q.front();
            q.pop();
            --numCourses;
            for (int j : g[i]) {
                if (--indeg[j] == 0) {
                    q.push(j);
                }
            }
        }
        return numCourses == 0;
    }
};
```

#### Go

```go
func canFinish(numCourses int, prerequisites [][]int) bool {
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
	for len(q) > 0 {
		i := q[0]
		q = q[1:]
		numCourses--
		for _, j := range g[i] {
			indeg[j]--
			if indeg[j] == 0 {
				q = append(q, j)
			}
		}
	}
	return numCourses == 0
}
```

#### TypeScript

```ts
function canFinish(numCourses: number, prerequisites: number[][]): boolean {
    const g: number[][] = Array.from({ length: numCourses }, () => []);
    const indeg: number[] = Array(numCourses).fill(0);
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
    for (const i of q) {
        --numCourses;
        for (const j of g[i]) {
            if (--indeg[j] === 0) {
                q.push(j);
            }
        }
    }
    return numCourses === 0;
}
```

#### Rust

```rust
use std::collections::VecDeque;

impl Solution {
    pub fn can_finish(mut num_courses: i32, prerequisites: Vec<Vec<i32>>) -> bool {
        let mut g: Vec<Vec<i32>> = vec![vec![]; num_courses as usize];
        let mut indeg: Vec<i32> = vec![0; num_courses as usize];

        for p in prerequisites {
            let a = p[0] as usize;
            let b = p[1] as usize;
            g[b].push(a as i32);
            indeg[a] += 1;
        }

        let mut q: VecDeque<usize> = VecDeque::new();
        for i in 0..num_courses {
            if indeg[i as usize] == 0 {
                q.push_back(i as usize);
            }
        }

        while let Some(i) = q.pop_front() {
            num_courses -= 1;
            for &j in &g[i] {
                let j = j as usize;
                indeg[j] -= 1;
                if indeg[j] == 0 {
                    q.push_back(j);
                }
            }
        }

        num_courses == 0
    }
}
```

#### C#

```cs
public class Solution {
    public bool CanFinish(int numCourses, int[][] prerequisites) {
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
        while (q.Count > 0) {
            int i = q.Dequeue();
            --numCourses;
            foreach (int j in g[i]) {
                if (--indeg[j] == 0) {
                    q.Enqueue(j);
                }
            }
        }
        return numCourses == 0;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
