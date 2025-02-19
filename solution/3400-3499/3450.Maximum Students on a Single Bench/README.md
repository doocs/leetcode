---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3450.Maximum%20Students%20on%20a%20Single%20Bench/README.md
tags:
    - 数组
    - 哈希表
---

<!-- problem:start -->

# [3450. 一张长椅上的最多学生 🔒](https://leetcode.cn/problems/maximum-students-on-a-single-bench)

[English Version](/solution/3400-3499/3450.Maximum%20Students%20on%20a%20Single%20Bench/README_EN.md)

## 题目描述

<!-- description:start -->

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表

我们用一个哈希表 $d$ 来存储每个长椅上的学生，键为长椅编号，值为一个集合，集合中存储着该长椅上的学生编号。

遍历学生数组 $\textit{students}$，将学生编号和长椅编号存入哈希表 $d$ 中。

最后，我们遍历哈希表 $d$ 的值，取出集合的大小的最大值即为一张长椅上坐着的不同学生的最大数量。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为学生数组 $\textit{students}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxStudentsOnBench(self, students: List[List[int]]) -> int:
        if not students:
            return 0
        d = defaultdict(set)
        for student_id, bench_id in students:
            d[bench_id].add(student_id)
        return max(map(len, d.values()))
```

#### Java

```java
class Solution {
    public int maxStudentsOnBench(int[][] students) {
        Map<Integer, Set<Integer>> d = new HashMap<>();
        for (var e : students) {
            int studentId = e[0], benchId = e[1];
            d.computeIfAbsent(benchId, k -> new HashSet<>()).add(studentId);
        }
        int ans = 0;
        for (var s : d.values()) {
            ans = Math.max(ans, s.size());
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxStudentsOnBench(vector<vector<int>>& students) {
        unordered_map<int, unordered_set<int>> d;
        for (const auto& e : students) {
            int studentId = e[0], benchId = e[1];
            d[benchId].insert(studentId);
        }
        int ans = 0;
        for (const auto& s : d) {
            ans = max(ans, (int) s.second.size());
        }
        return ans;
    }
};
```

#### Go

```go
func maxStudentsOnBench(students [][]int) (ans int) {
	d := make(map[int]map[int]struct{})
	for _, e := range students {
		studentId, benchId := e[0], e[1]
		if _, exists := d[benchId]; !exists {
			d[benchId] = make(map[int]struct{})
		}
		d[benchId][studentId] = struct{}{}
	}
	for _, s := range d {
		ans = max(ans, len(s))
	}
	return
}
```

#### TypeScript

```ts
function maxStudentsOnBench(students: number[][]): number {
    const d: Map<number, Set<number>> = new Map();
    for (const [studentId, benchId] of students) {
        if (!d.has(benchId)) {
            d.set(benchId, new Set());
        }
        d.get(benchId)?.add(studentId);
    }
    let ans = 0;
    for (const s of d.values()) {
        ans = Math.max(ans, s.size);
    }
    return ans;
}
```

#### Rust

```rust
use std::collections::{HashMap, HashSet};

impl Solution {
    pub fn max_students_on_bench(students: Vec<Vec<i32>>) -> i32 {
        let mut d: HashMap<i32, HashSet<i32>> = HashMap::new();
        for e in students {
            let student_id = e[0];
            let bench_id = e[1];
            d.entry(bench_id)
                .or_insert_with(HashSet::new)
                .insert(student_id);
        }
        let mut ans = 0;
        for s in d.values() {
            ans = ans.max(s.len() as i32);
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
