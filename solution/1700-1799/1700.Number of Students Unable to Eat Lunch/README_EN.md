# [1700. Number of Students Unable to Eat Lunch](https://leetcode.com/problems/number-of-students-unable-to-eat-lunch)

[中文文档](/solution/1700-1799/1700.Number%20of%20Students%20Unable%20to%20Eat%20Lunch/README.md)

## Description

<p>The school cafeteria offers circular and square sandwiches at lunch break, referred to by numbers <code>0</code> and <code>1</code> respectively. All students stand in a queue. Each student either prefers square or circular sandwiches.</p>

<p>The number of sandwiches in the cafeteria is equal to the number of students. The sandwiches are placed in a <strong>stack</strong>. At each step:</p>

<ul>
	<li>If the student at the front of the queue <strong>prefers</strong> the sandwich on the top of the stack, they will <strong>take it</strong> and leave the queue.</li>
	<li>Otherwise, they will <strong>leave it</strong> and go to the queue&#39;s end.</li>
</ul>

<p>This continues until none of the queue students want to take the top sandwich and are thus unable to eat.</p>

<p>You are given two integer arrays <code>students</code> and <code>sandwiches</code> where <code>sandwiches[i]</code> is the type of the <code>i<sup>​​​​​​th</sup></code> sandwich in the stack (<code>i = 0</code> is the top of the stack) and <code>students[j]</code> is the preference of the <code>j<sup>​​​​​​th</sup></code> student in the initial queue (<code>j = 0</code> is the front of the queue). Return <em>the number of students that are unable to eat.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> students = [1,1,0,0], sandwiches = [0,1,0,1]
<strong>Output:</strong> 0<strong> 
Explanation:</strong>
- Front student leaves the top sandwich and returns to the end of the line making students = [1,0,0,1].
- Front student leaves the top sandwich and returns to the end of the line making students = [0,0,1,1].
- Front student takes the top sandwich and leaves the line making students = [0,1,1] and sandwiches = [1,0,1].
- Front student leaves the top sandwich and returns to the end of the line making students = [1,1,0].
- Front student takes the top sandwich and leaves the line making students = [1,0] and sandwiches = [0,1].
- Front student leaves the top sandwich and returns to the end of the line making students = [0,1].
- Front student takes the top sandwich and leaves the line making students = [1] and sandwiches = [1].
- Front student takes the top sandwich and leaves the line making students = [] and sandwiches = [].
Hence all students are able to eat.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> students = [1,1,1,0,0,1], sandwiches = [1,0,0,0,1,1]
<strong>Output:</strong> 3
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= students.length, sandwiches.length &lt;= 100</code></li>
	<li><code>students.length == sandwiches.length</code></li>
	<li><code>sandwiches[i]</code> is <code>0</code> or <code>1</code>.</li>
	<li><code>students[i]</code> is <code>0</code> or <code>1</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def countStudents(self, students: List[int], sandwiches: List[int]) -> int:
        cnt = Counter(students)
        for v in sandwiches:
            if cnt[v] == 0:
                return cnt[v ^ 1]
            cnt[v] -= 1
        return 0
```

### **Java**

```java
class Solution {
    public int countStudents(int[] students, int[] sandwiches) {
        int[] cnt = new int[2];
        for (int v : students) {
            ++cnt[v];
        }
        for (int v : sandwiches) {
            if (cnt[v]-- == 0) {
                return cnt[v ^ 1];
            }
        }
        return 0;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int countStudents(vector<int>& students, vector<int>& sandwiches) {
        int cnt[2] = {0};
        for (int& v : students) ++cnt[v];
        for (int& v : sandwiches) {
            if (cnt[v]-- == 0) {
                return cnt[v ^ 1];
            }
        }
        return 0;
    }
};
```

### **Go**

```go
func countStudents(students []int, sandwiches []int) int {
	cnt := [2]int{}
	for _, v := range students {
		cnt[v]++
	}
	for _, v := range sandwiches {
		if cnt[v] == 0 {
			return cnt[v^1]
		}
		cnt[v]--
	}
	return 0
}
```

### **C**

```c
int countStudents(int *students, int studentsSize, int *sandwiches, int sandwichesSize) {
    int count[2] = {0};
    for (int i = 0; i < studentsSize; i++) {
        count[students[i]]++;
    }
    for (int i = 0; i < sandwichesSize; i++) {
        int j = sandwiches[i];
        if (count[j] == 0) {
            return count[j ^ 1];
        }
        count[j]--;
    }
    return 0;
}
```

### **TypeScript**

```ts
function countStudents(students: number[], sandwiches: number[]): number {
    const count = [0, 0];
    for (const v of students) {
        count[v]++;
    }
    for (const v of sandwiches) {
        if (count[v] === 0) {
            return count[v ^ 1];
        }
        count[v]--;
    }
    return 0;
}
```

### **Rust**

```rust
impl Solution {
    pub fn count_students(students: Vec<i32>, sandwiches: Vec<i32>) -> i32 {
        let mut count = [0, 0];
        for &v in students.iter() {
            count[v as usize] += 1;
        }
        for &v in sandwiches.iter() {
            let v = v as usize;
            if count[v as usize] == 0 {
                return count[v ^ 1];
            }
            count[v] -= 1;
        }
        0
    }
}
```

### **...**

```

```

<!-- tabs:end -->
